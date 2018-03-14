package com.example.mayn.mvp_demo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mayn.mvp_demo.R;
import com.example.mayn.mvp_demo.model.User;
import com.example.mayn.mvp_demo.presenter.MainPresenterImpl;

/**
 * Created by FANGDINGJIE
 * 2018/3/14.
 */

public class MainActivity extends AppCompatActivity implements BaseView {
    private MainPresenterImpl presenter;

    private EditText username;
    private EditText password;

    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenterImpl();
        presenter.attachView(this);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new User(username.getText().toString(),password.getText().toString());
                presenter.login(user);
            }
        });
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        showToast("登录成功");
    }

    @Override
    public void loginFailue() {
        showToast("登录失败");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
