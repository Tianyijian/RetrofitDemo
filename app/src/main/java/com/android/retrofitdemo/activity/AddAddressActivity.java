package com.android.retrofitdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.retrofitdemo.R;
import com.android.retrofitdemo.bean.User;
import com.android.retrofitdemo.bean.UserAddress;
import com.android.retrofitdemo.network.Result;
import com.android.retrofitdemo.network.RetrofitClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AddAddressActivity extends AppCompatActivity {
    private User user;
    private String gender;
    private String label;

    private EditText et_name;
    private EditText et_phone;
    private EditText et_house;
    private TextView tv_addr_info;
    private TextView tv_label_man;
    private TextView tv_label_women;
    private TextView tv_label_home;
    private TextView tv_label_company;
    private TextView tv_label_school;
    private Button btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        user = (User)getIntent().getSerializableExtra("user");
        getUserAddress();
        initView();
    }

    private void initView() {
        et_name = (EditText)findViewById(R.id.et_name);
        et_phone = (EditText)findViewById(R.id.et_phone);
        et_house = (EditText)findViewById(R.id.et_house);
        tv_addr_info = (TextView)findViewById(R.id.tv_addr_info);
        tv_label_man = (TextView)findViewById(R.id.tv_label_men);
        tv_label_women = (TextView)findViewById(R.id.tv_label_women);
        tv_label_home = (TextView) findViewById(R.id.tv_label_home);
        tv_label_company = (TextView)findViewById(R.id.tv_label_company);
        tv_label_school = (TextView)findViewById(R.id.tv_label_school);
        btn_add = (Button)findViewById(R.id.btn_add);
        tv_addr_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo 这里可以跳转至地图定位界面，然后将获得的地图位置信息传回来显示
                tv_addr_info.setText("北京市酒快来科技有限公司");
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUserAddress();
            }
        });
        tv_label_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_label_man.setEnabled(false);
                tv_label_women.setEnabled(true);
                gender = "M";
            }
        });
        tv_label_women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_label_women.setEnabled(false);
                tv_label_man.setEnabled(true);
                gender = "F";
            }
        });
        tv_label_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_label_home.setEnabled(false);
                tv_label_company.setEnabled(true);
                tv_label_school.setEnabled(true);
                label = "家";
            }
        });
        tv_label_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_label_home.setEnabled(true);
                tv_label_company.setEnabled(false);
                tv_label_school.setEnabled(true);
                label = "公司";
            }
        });
        tv_label_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_label_home.setEnabled(true);
                tv_label_company.setEnabled(true);
                tv_label_school.setEnabled(false);
                label = "学校";
            }
        });
    }

    private void addUserAddress() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", et_name.getText().toString());
        map.put("phone", et_phone.getText().toString());
        map.put("addrHouse", et_house.getText().toString());
        map.put("addrInfo", tv_addr_info.getText().toString());
        map.put("userId", user.getUserId());
        map.put("gender", gender);
        map.put("label", label);
        map.put("isDefault", false);
        RetrofitClient.getInstance(getApplicationContext()).addUserAddress(map, observer);
    }
    private Observer<Result> observer = new Observer<Result>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Result value) {
            Toast.makeText(AddAddressActivity.this, value.getRemark(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(AddAddressActivity.this, "连接服务器出错", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete() {

        }
    };

    private void getUserAddress() {
        Toast.makeText(this, "获取地址中...", Toast.LENGTH_SHORT).show();
        RetrofitClient.getInstance(getApplicationContext()).getUserAddress(user.getUserId(), observer2);
    }
    private Observer<Result<List<UserAddress>>> observer2 = new Observer<Result<List<UserAddress>>>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Result<List<UserAddress>> value) {
            Toast.makeText(AddAddressActivity.this, value.getRemark(), Toast.LENGTH_SHORT).show();
            if (value.getStatus() == Result.SUCCESS) {
                Toast.makeText(AddAddressActivity.this, "共获得 " + value.getData().size() + " 个地址", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };
}
