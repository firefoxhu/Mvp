package com.quickly.xqw.widget;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.quickly.xqw.R;
public class CommentDialogFragment  extends Dialog {

    //点击发表，内容不为空时的回调
    public SendBackListener sendBackListener;
    public interface  SendBackListener{
        void sendBack(String inputText);
    }

    private ProgressDialog progressDialog;
    private String texthint;

    private Dialog dialog;
    private EditText inputDlg;
    private int numconut=300;
    private String tag=null;


    private Context context;

    public CommentDialogFragment(Context context) {
        this(context, R.style.inputDialog);
        this.context = context;
    }
    public CommentDialogFragment(Context context, int themeResId) {
        super(context, themeResId);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_input);
    }

}
