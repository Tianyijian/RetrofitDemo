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

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SignUpActivity extends AppCompatActivity {

    private EditText et_user_phone;
    private Button btn_sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_user_phone = (EditText)findViewById(R.id.et_user_phone);

        btn_sign_up = (Button)findViewById(R.id.btn_change);

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signUp() {
        String userPhone = et_user_phone.getText().toString();
        Toast.makeText(this, "发送请求", Toast.LENGTH_SHORT).show();
        RetrofitClient.getInstance(getApplicationContext()).signUp(userPhone, observer);
    }
    private Observer<Result<User>> observer = new Observer<Result<User>>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Result<User> value) {
            //可直接Toast打印服务器返回的消息
            Toast.makeText(SignUpActivity.this, value.getRemark(), Toast.LENGTH_SHORT).show();
            if (value.getStatus() == Result.SUCCESS) {
                User user = value.getData();        //获取数据
                Toast.makeText(SignUpActivity.this, user.toString(), Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(SignUpActivity.this, "连接服务器出错", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete() {

        }
    };
}
