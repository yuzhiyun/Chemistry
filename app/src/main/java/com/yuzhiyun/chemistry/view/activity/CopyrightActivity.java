package com.yuzhiyun.chemistry.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.model.util.toast;
import com.yuzhiyun.chemistry.view.base.BaseActivity;
import com.yuzhiyun.chemistry.view.fragment.BigPictureFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyrightActivity extends BaseActivity implements  View.OnClickListener{

    ImageView imgQrcode ;
    TextView tvUrl;
    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_copyright);
    }

    @Override
    protected void findView() {
        imgQrcode= (ImageView) findViewById(R.id.imgQrcode);
        tvUrl= (TextView) findViewById(R.id.tvUrl);

    }

    @Override
    protected void setListener() {
        imgQrcode.setOnClickListener(this);
        tvUrl.setOnClickListener(this);
    }

    /**
     * 图片保存到本地
     */
    private void copyToPhone() {
        String IMG_PATH= Environment.getExternalStorageDirectory().getPath();
        Log.i("IMG_PATH",IMG_PATH);
        String IMG_NAME="qrcode_me.jpg";
        if ((new File(IMG_PATH,IMG_NAME).exists()) == false)
        {
            File dir = new File(IMG_PATH);
            if (!dir.exists())
            {
                dir.mkdir();
            }
            try {
                InputStream is = null;
                try {
                    is = getAssets().open(IMG_NAME);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                OutputStream os = new FileOutputStream(IMG_PATH +"/"+ IMG_NAME);
                byte[] buffer = new byte[2014];
                int length;

                while ((length = is.read(buffer)) > 0)
                {
                    os.write(buffer,0,length);
                }
                os.flush();
                os.close();
                is.close();

                toast.ShowText("保存成功", CopyrightActivity.this);
            } catch (IOException e) {
                e.printStackTrace();
                toast.ShowText("保存失败", CopyrightActivity.this);
            }

        }
        else

        toast.ShowText("图片已经存在 ",CopyrightActivity.this);
    }

    @Override
    protected void initOther() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgQrcode:
                AlertDialog.Builder builder = new AlertDialog.Builder(CopyrightActivity.this);
                builder.setItems(new String[]{"查看大图", "保存到本地"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
//                                toast.ShowText("查看大图", CopyrightActivity.this);
                                FragmentManager fragmentManager = getSupportFragmentManager();
                                Fragment fragment = new BigPictureFragment();
                                fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.container_copyright,fragment).commit();

                                break;
                            case 1:

                                copyToPhone();
                                break;
                        }
                    }
                });
                builder.create().show();
             break;
            case R.id.tvUrl:
                Uri uri = Uri.parse("http://www.yuzhyun.me");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;
        }
    }
}
