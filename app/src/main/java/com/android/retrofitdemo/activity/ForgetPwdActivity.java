package com.android.retrofitdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.retrofitdemo.R;
import com.android.retrofitdemo.network.Result;
import com.android.retrofitdemo.network.RetrofitClient;
import com.android.retrofitdemo.utils.EncryptUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ForgetPwdActivity extends AppCompatActivity {

    private EditText et_user_info;
    private EditText et_new_pwd;
    private Button btn_change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        et_user_info = (EditText)findViewById(R.id.et_user_info);
        et_new_pwd = (EditText)findViewById(R.id.et_new_pwd);
        btn_change = (Button) findViewById(R.id.btn_change);

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPwd();

            }
        });
    }

    private void resetPwd() {
        String userInfo = et_user_info.getText().toString();
        String newPwd = EncryptUtils.md5Hex("JKL" + et_new_pwd.getText().toString());
        Toast.makeText(this, "发送请求", Toast.LENGTH_SHORT).show();
        RetrofitClient.getInstance(getApplicationContext()).resetPwd(userInfo, newPwd, observer);
    }
    private Observer<Result> observer = new Observer<Result>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Result value) {
            Toast.makeText(ForgetPwdActivity.this, value.getRemark(), Toast.LENGTH_SHORT).show();
            if (value.getStatus() == Result.SUCCESS) {
                finish();
            }
        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(ForgetPwdActivity.this, "连接服务器出错", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete() {

        }
    };

}
