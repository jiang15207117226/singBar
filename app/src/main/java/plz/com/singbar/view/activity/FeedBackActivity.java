package plz.com.singbar.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import plz.com.singbar.R;

/**
 * Created by Administrator on 2016/9/5.
 */
public class FeedBackActivity extends Activity implements View.OnClickListener {
    private ViewHolder holder;
    private PopupWindow pop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_feedback, null);
        setContentView(view);
        init(view);
    }

    private void init(View view) {
        holder = new ViewHolder();
        holder.bindView(view);
        initPop();
        setClickListener();
    }

    private void initPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_feedback_commit, null);
        pop = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        pop.setAnimationStyle(R.style.feedbackPopAnimation);
        pop.setTouchable(false);
        pop.setFocusable(false);
        pop.setOutsideTouchable(false);
    }

    public void setClickListener() {
        holder.back.setOnClickListener(this);
        holder.commitBtn.setOnClickListener(this);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("result","handleMessage");
            switch (msg.what) {
                case 0x01:
                    Log.i("result","handleMessage"+msg.what);
                    pop.dismiss();
                    break;
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_feedback_back:
                //返回
                finish();
                break;

            case R.id.btn_feedback_commit:
                //提交
                if (holder.question.getText().toString().length() < 1) {
                    Toast.makeText(this, "您还没有输入任何反馈问题...", Toast.LENGTH_SHORT).show();
                } else {
                    pop.showAtLocation(holder.commitBtn, Gravity.CENTER, 0, 0);
                    holder.question.setText("");
                    holder.email.setText("");
                    handler.post(thread);
                }
                break;
        }
    }
    Thread thread=new Thread() {
        @Override
        public void run() {
            Log.i("result","currentTime-->"+System.currentTimeMillis());
            try {
                handler.sendEmptyMessage(0x01);
            } catch (Exception e) {
                e.printStackTrace();
            }
            super.run();
        }
    };
    private class ViewHolder {
        private ImageView back;
        private EditText question;
        private EditText email;
        private Button commitBtn;

        private void bindView(View view) {
            back = (ImageView) view.findViewById(R.id.iv_feedback_back);
            question = (EditText) view.findViewById(R.id.et_feedback_question);
            email = (EditText) view.findViewById(R.id.et_feedback_email);
            commitBtn = (Button) view.findViewById(R.id.btn_feedback_commit);
        }
    }
}
