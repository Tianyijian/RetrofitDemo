package com.android.retrofitdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.retrofitdemo.R;
import com.android.retrofitdemo.network.Result;
import com.android.retrofitdemo.bean.User;
import com.android.retrofitdemo.network.RetrofitClient;
import com.android.retrofitdemo.utils.EncryptUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class MainActivity extends AppCompatActivity {

    private Button login;
    private EditText et_username;
    private EditText et_pwd;
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button)findViewById(R.id.login);
        et_username = (EditText)findViewById(R.id.et_username);
        et_pwd = (EditText)findViewById(R.id.et_pwd);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               login();
            }
        });
    }

    private void login() {
        String userName = et_username.getText().toString();
        String userPwd = EncryptUtils.md5Hex("JKL" + et_pwd.getText().toString());
        Toast.makeText(MainActivity.this, "发送请求", Toast.LENGTH_SHORT).show();
        RetrofitClient.getInstance(MainActivity.this).login(userName, userPwd, observer);
    }

    private Observer<Result<User>> observer = new Observer<Result<User>>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Result<User> value) {
            if (value.getStatus() == Result.SUCCESS) {
                Toast.makeText(MainActivity.this, value.getRemark(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, value.getData().toString(), Toast.LENGTH_SHORT).show();
                //TODO 登陆成功，跳转到主界面
            } else if (value.getStatus() == Result.ERROR) {
                Toast.makeText(MainActivity.this, value.getRemark(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "login: onError: " + e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    };

}
