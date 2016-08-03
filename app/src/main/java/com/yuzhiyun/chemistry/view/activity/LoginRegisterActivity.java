package com.yuzhiyun.chemistry.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.view.base.BaseActivity;

public class LoginRegisterActivity extends BaseActivity implements View.OnClickListener {
    Button btnLogin;
    Button btnRegister;

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_login_register);
    }

    @Override
    protected void findView() {
        btnLogin= (Button) findViewById(R.id.btnLogin);
        btnRegister= (Button) findViewById(R.id.btnRegister);
    }

    @Override
    protected void setListener() {
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    protected void initOther() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                startActivity(new Intent(context,LoginActivity.class));
                break;
            case R.id.btnRegister:
                startActivity(new Intent(context,RegisterActivity.class));
                break;
            default:
                break;
        }
    }
}
