package com.quickly.xqw.module.circle.profile;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.quickly.xqw.R;
import com.quickly.xqw.XQWApplication;
import com.quickly.xqw.model.circle.ArticleBean;
import com.quickly.xqw.module.base.BaseActivity;
import com.quickly.xqw.utils.StatusBarUtil;

public class PersonalProfileActivity extends BaseActivity {

    private static final String TAG = "PersonalProfileActivity";

    public static void launch(ArticleBean.DataBean.ListBean bean) {
        XQWApplication.appContext.startActivity(new Intent(XQWApplication.appContext, PersonalProfileActivity.class)
                .putExtra(TAG, bean)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_container);
        Intent intent = getIntent();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, PersonalProfileUIFragment.newInstance(intent.getParcelableExtra(TAG))).commit();
    }
}