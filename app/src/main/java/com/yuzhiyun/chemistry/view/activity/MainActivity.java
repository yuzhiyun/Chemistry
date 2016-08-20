package com.yuzhiyun.chemistry.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.model.Application.App;
import com.yuzhiyun.chemistry.view.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    Button btnExam;
    FloatingActionButton fabAbout;
    FloatingActionButton fabSwitchAccount;
    FloatingActionButton fabAdmin;
    FloatingActionButton fabAboutme;
    FloatingActionButton fabTestSend;
    TextView tvUser;
    String KEY_WHICH_FAB="whichFab";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void findView() {
        btnExam= (Button) findViewById(R.id.btnExam);
        fabAbout= (FloatingActionButton) findViewById(R.id.fabAbout);
        fabSwitchAccount= (FloatingActionButton) findViewById(R.id.fabSwitchAccount);
        fabAdmin= (FloatingActionButton) findViewById(R.id.fabAdmin);
        fabAboutme= (FloatingActionButton) findViewById(R.id.fabAboutme);
        fabTestSend= (FloatingActionButton) findViewById(R.id.fabTestSend);

        tvUser= (TextView) findViewById(R.id.tvUser);

    }

    @Override
    protected void setListener() {
        btnExam.setOnClickListener(this);

        fabAbout.setOnClickListener(this);
        fabSwitchAccount.setOnClickListener(this);
        fabAdmin.setOnClickListener(this);
        fabAboutme.setOnClickListener(this);
        fabTestSend.setOnClickListener(this);
    }

    @Override
    protected void initOther() {
        toolbar.setTitle("习题宝典");
        tvUser.setText("亲爱的"+ App.getInstance().getCurrentUser().getUsername()+"，欢迎光临");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnExam:
                startActivity(new Intent(context,ChaptersActivity.class));
                break;
            case R.id.fabAbout:
                startActivity(new Intent(context,OutlineActivity.class));
                break;
            case R.id.fabSwitchAccount:
                startActivity(new Intent(context,LoginRegisterActivity.class));
                break;
//            case R.id.btnTest:
//                startActivity(new Intent(context,TestActivity.class));
//                break;
            case R.id.fabAdmin:
                Intent intent=new Intent(context,UserListActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString(KEY_WHICH_FAB,"fabAdmin");
                intent.putExtras(bundle);
                startActivity(intent );
                break;
            case R.id.fabAboutme:
                startActivity(new Intent(context,CopyrightActivity.class));
                break;
            case R.id.fabTestSend:
                Intent intent2=new Intent(context,UserListActivity.class);
                Bundle bundle2=new Bundle();
                bundle2.putString(KEY_WHICH_FAB,"fabTestSend");
                intent2.putExtras(bundle2);
                startActivity(intent2 );
                break;


        }
    }
}
