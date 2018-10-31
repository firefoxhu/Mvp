package com.quickly.xqw.module.ui.index;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.quickly.xqw.R;
import com.quickly.xqw.XQWApplication;
import com.quickly.xqw.module.base.BaseActivity;

public class CategoryNewsActivity extends BaseActivity {


    private static final String TAG = "CategoryNewsActivity";
    private static final String IMG = "IMG";
    private static final String TITLE = "Title";

    public static void launch(String categoryId,String title,String url) {
        XQWApplication.appContext.startActivity(new Intent(XQWApplication.appContext, CategoryNewsActivity.class)
                .putExtra(TAG, categoryId)
                .putExtra(IMG,url)
                .putExtra(TITLE,title)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_container);
        Intent intent = getIntent();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,
                        NewsCategoryView.newInstance(intent.getStringExtra(TAG),intent.getStringExtra(TITLE),intent.getStringExtra(IMG)))
                .commit();
    }

}
