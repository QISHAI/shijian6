package com.example.wangyiwen.work6;

/**
 * Created by WangYiWen on 2018/5/9.
 */

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HandlerActivity extends Activity implements View.OnClickListener {
    private static final int UPDATE_TEXT =1 ;
    private Button   btn_handler;
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        initView();
        initListener();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATE_TEXT:
                    textview.setText("文本内容被改变了！");
            }
        }
    };

    private void initListener() {
        btn_handler.setOnClickListener(this);
    }

    private void initView() {
        btn_handler = (Button) findViewById(R.id.btn_handler);
        textview = (TextView) findViewById(R.id.textview);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_handler:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg=new Message();
                        msg.what=UPDATE_TEXT;
                        handler.sendMessage(msg);
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}