package com.yuzhiyun.chemistry.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.model.proxy.UserProxy;
import com.yuzhiyun.chemistry.model.util.toast;
import com.yuzhiyun.chemistry.view.base.BaseActivity;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, UserProxy.ISignUpListener {
    AppCompatEditText etUserName;
    AppCompatEditText etUserPwd;
    AppCompatEditText etUserPwdAgain;
    TextView tvLogin;
    Button btnRegister;
    //用户代理，其中设置了多个接口
    UserProxy userProxy;

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void findView() {
        etUserName = (AppCompatEditText) findViewById(R.id.etUserName);
        etUserPwd = (AppCompatEditText) findViewById(R.id.etUserPwd);
        etUserPwdAgain = (AppCompatEditText) findViewById(R.id.etUserPwdAgain);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        tvLogin = (TextView) findViewById(R.id.tvLogin);
    }

    @Override
    protected void setListener() {
        btnRegister.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }

    @Override
    protected void initOther() {
        toolbar.setTitle("注册");
        userProxy = new UserProxy(RegisterActivity.this);
        userProxy.setOnSignUpListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                if (etUserPwd.getText().toString().trim().equals(etUserPwdAgain.getText().toString().trim()))
                    userProxy.signUp(etUserName.getText().toString().trim(), etUserPwd.getText().toString().trim());
                else
                    toast.ShowText("两次输入密码不一致", RegisterActivity.this);
                break;
            case R.id.tvLogin:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
            default:
                break;
        }
    }


    @Override
    public void onSignUpSuccess() {
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
    }

    @Override
    public void onSignUpFailure(String msg) {
        toast.ShowText("注册失败-" + msg, RegisterActivity.this);
    }
}
