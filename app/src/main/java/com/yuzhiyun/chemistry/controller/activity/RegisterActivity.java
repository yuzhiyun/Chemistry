package com.yuzhiyun.chemistry.controller.activity;

import android.content.Intent;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.controller.base.BaseActivity;
import com.yuzhiyun.chemistry.model.proxy.UserProxy;
import com.yuzhiyun.chemistry.model.util.toast;

import cn.bmob.v3.BmobInstallation;

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
                    //传入用户名、密码、设备id进行注册
                    userProxy.signUp(etUserName.getText().toString().trim(), etUserPwd.getText().toString().trim(), BmobInstallation.getInstallationId(context));
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
