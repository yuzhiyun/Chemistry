package com.yuzhiyun.chemistry.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.model.proxy.UserProxy;
import com.yuzhiyun.chemistry.model.util.toast;
import com.yuzhiyun.chemistry.view.base.BaseActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener, UserProxy.ILoginListener {

    AppCompatEditText etUserName;
    AppCompatEditText etUserPwd;

    Button btnLogin;
    TextView tvRegister;
    UserProxy userProxy;

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void findView() {
        etUserName = (AppCompatEditText) findViewById(R.id.etUserName);
        etUserPwd = (AppCompatEditText) findViewById(R.id.etUserPwd);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvRegister = (TextView) findViewById(R.id.tvRegister);

    }

    @Override
    protected void setListener() {
        userProxy = new UserProxy(context);
        userProxy.setOnLoginListener(this);

        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    @Override
    protected void initOther() {
        toolbar.setTitle("登录");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                userProxy.login(etUserName.getText().toString().trim(), etUserPwd.getText().toString().trim());
                break;
            case R.id.tvRegister:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onLoginSuccess() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void onLoginFailure(String msg) {
        toast.ShowText("登录失败-" + msg, LoginActivity.this);
    }
}
