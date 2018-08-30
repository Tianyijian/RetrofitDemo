package com.android.retrofitdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.retrofitdemo.R;
import com.android.retrofitdemo.bean.User;
import com.android.retrofitdemo.network.Result;
import com.android.retrofitdemo.network.RetrofitClient;
import com.android.retrofitdemo.utils.EncryptUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ChangePwdActivity extends AppCompatActivity {

    private User user;
    private EditText et_old_pwd;
    private EditText et_new_pwd;
    private Button changeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);

        user = (User)getIntent().getSerializableExtra("user");
        et_old_pwd = (EditText)findViewById(R.id.et_old_pwd);
        et_new_pwd = (EditText)findViewById(R.id.et_new_pwd);
        changeBtn = (Button)findViewById(R.id.btn_change);

        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePwd();
            }
        });
    }

    private void changePwd() {
        String oldPwd = EncryptUtils.md5Hex("JKL" + et_old_pwd.getText().toString());
        String newPwd = EncryptUtils.md5Hex("JKL" + et_new_pwd.getText().toString());
        Toast.makeText(this, "发送请求", Toast.LENGTH_SHORT).show();
        RetrofitClient.getInstance(getApplicationContext()).changePwd(user.getUserId(),oldPwd, newPwd,observer );
    }
    private Observer<Result<String>> observer = new Observer<Result<String>>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Result<String> value) {
            Toast.makeText(ChangePwdActivity.this, value.getRemark(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(ChangePwdActivity.this, "网络出错", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete() {

        }
    };
}
