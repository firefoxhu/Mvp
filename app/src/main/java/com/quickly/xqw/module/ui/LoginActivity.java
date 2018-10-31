package com.quickly.xqw.module.ui;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.jakewharton.rxbinding2.view.RxView;
import com.luozm.captcha.Captcha;
import com.quickly.xqw.R;
import com.quickly.xqw.XQWApplication;
import com.quickly.xqw.api.IUserApi;
import com.quickly.xqw.model.login.LoginUserBean;
import com.quickly.xqw.module.base.BaseActivity;
import com.quickly.xqw.utils.RetrofitFactory8080;
import com.quickly.xqw.utils.SettingUtil;
import com.quickly.xqw.utils.UserSessionUtil;
import java.util.concurrent.TimeUnit;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity{


    private static final String TAG = "LoginActivity";

    private EditText editAccount;

    private EditText editPassword;

    private Button   buttonLogin;

    private String fromWhere;

    private Captcha mCaptcha;

    private boolean isValid = false;

    private UserSessionUtil sharedPreferences;

    public static void launch(String fromWhere) {
        XQWApplication.appContext.startActivity(new Intent(XQWApplication.appContext, LoginActivity.class)
                .putExtra(TAG, fromWhere)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fromWhere = getIntent().getStringExtra(TAG);
        this.initView();
    }

    private void initView(){
        editAccount  = findViewById(R.id.text_account);
        editPassword = findViewById(R.id.txt_password);
        buttonLogin  = findViewById(R.id.sign_in_button);
        mCaptcha     = findViewById(R.id.captCha);
        mCaptcha.setBitmap(R.mipmap.cat);
        mCaptcha.setSeekBarStyle(R.drawable.po_seekbar,R.drawable.thumb);

        sharedPreferences = new UserSessionUtil(getSharedPreferences("userInfo", Context.MODE_PRIVATE));

        // 默认加载用户名
        String username =sharedPreferences.getUsername();
        editAccount.setText(username);

        // 手势验证码
        mCaptcha.setCaptchaListener(new Captcha.CaptchaListener() {
            @Override
            public String onAccess(long time) {
                Toast.makeText(LoginActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                isValid = true;
                buttonLogin.setEnabled(true);
                buttonLogin.setBackgroundColor(getResources().getColor(R.color.Teal));
                buttonLogin.setTextColor(Color.WHITE);
                return "验证通过，傲~";
            }
            @Override
            public String onFailed(int count) {
                Toast.makeText(LoginActivity.this, "验证失败,失败次数" + count, Toast.LENGTH_SHORT).show();
                mCaptcha.reset(false);
                isValid = false;
                return "验证失败";
            }
            @Override
            public String onMaxFailed() {
                return "你失败的次数太多了，暂时限制登录。";
            }
        });

        RxView.clicks(buttonLogin).throttleFirst(3, TimeUnit.SECONDS).as(bindAutoDispose()).subscribe(x->{

            String account = this.editAccount == null ? null : this.editAccount.getText().toString();

            String password = this.editPassword == null ? null : this.editPassword.getText().toString();


            if(TextUtils.isEmpty(account)){
                this.editAccount.requestFocus();
                Toast.makeText(mContext, "账号不能为空", Toast.LENGTH_SHORT).show();
                return;
            }

            if(TextUtils.isEmpty(password)) {
                this.editPassword.requestFocus();
                Toast.makeText(mContext, "请输入密码", Toast.LENGTH_SHORT).show();
                return;
            }

            if(!isValid) {
                Toast.makeText(mContext, "请滑动完成验证码", Toast.LENGTH_SHORT).show();
                return;
            }

            RetrofitFactory8080.getRetrofit().create(IUserApi.class).login(account,password,"")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(resp->{
                if(resp.getCode() == 0) {
                    // 成功后跳转
                    this.redirectTo(resp.getData());
                }else {
                    if(resp.getCode() == 5555) {
                        // 用户不存在
                        this.editAccount.setText("");
                        this.editAccount.requestFocus();
                    }

                    if(resp.getCode() == 4444) {
                        this.editPassword.setText("");
                        this.editPassword.requestFocus();
                    }
                    Toast.makeText(this, resp.getMsg(), Toast.LENGTH_SHORT).show();
                }

            },throwable -> {
                Toast.makeText(this, "网络请求错误！", Toast.LENGTH_SHORT).show();
                throwable.printStackTrace();
            });

        });
    }


    /**
     * 隐藏键盘
     */
    private void hideKeyBord(){

        InputMethodManager systemService = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        View currentFocus = LoginActivity.this.getCurrentFocus();

        if(systemService != null && currentFocus != null) {
            systemService.hideSoftInputFromWindow(currentFocus.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        }

    }


    private void redirectTo(LoginUserBean.DataBean dataBean){
        // 保存用户信息
        sharedPreferences.saveSession(dataBean.getUsername(),"",dataBean.getGender(),dataBean.getUser_session_());

        //设置傲默认的存储中
        SettingUtil.getInstance().setSession(dataBean.getUser_session_());
        SettingUtil.getInstance().setUsername(dataBean.getUsername() == null ? dataBean.getNickname():dataBean.getUsername());
        SettingUtil.getInstance().setAvatar(dataBean.getAvatar());

        switch (fromWhere){
            case CircleContentFragment.TAG:
                finish();
                Toast.makeText(this, "登录成功返回到评论！", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
