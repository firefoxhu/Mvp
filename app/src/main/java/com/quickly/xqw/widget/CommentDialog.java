package com.quickly.xqw.widget;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.jakewharton.rxbinding2.view.RxView;
import com.quickly.xqw.R;
import java.util.concurrent.TimeUnit;

public class CommentDialog extends Dialog{

    private Context mContext;

    private EditText mEditText;

    private TextView mTextView;

    private OnSendListener mOnSendListener;

    public interface OnSendListener {
        void sendComment(String content);
    }

    public CommentDialog(@NonNull Context context,OnSendListener onSendListener) {
        super(context, R.style.inputDialog);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.mContext = context;
        this.mOnSendListener = onSendListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(mContext).inflate(R.layout.comment_input,null);
        this.initView(view);
        setContentView(view);

        this.setLayout();

        setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                mEditText.setFocusableInTouchMode(true);
                mEditText.requestFocus();

                InputMethodManager inputMethodManager = (InputMethodManager) mEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                inputMethodManager.showSoftInput(mEditText,InputMethodManager.SHOW_IMPLICIT);
            }
        });


    }

    private void initView(View view){
        this.mEditText = view.findViewById(R.id.comment_content);
        this.mTextView = view.findViewById(R.id.text_send);

        RxView.clicks(this.mTextView).throttleFirst(800, TimeUnit.MILLISECONDS)
                .subscribe(o -> {
                    String content = this.mEditText.getText().toString().trim();

                    if(TextUtils.isEmpty(content)){
                        Toast.makeText(mContext, "请输入评论", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(this.mOnSendListener != null) {
                        this.mOnSendListener.sendComment(content);
                    }

                    this.dismiss();
                });
    }

    private void setLayout(){
        getWindow().setGravity(Gravity.BOTTOM);
        //WindowManager m = getWindow().getWindowManager();
        //Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = WindowManager.LayoutParams.MATCH_PARENT;
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(p);

    }
}
