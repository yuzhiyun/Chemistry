package com.yuzhiyun.chemistry.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.controller.ExamActivityController;
import com.yuzhiyun.chemistry.model.Adapter.ExamAdapter;
import com.yuzhiyun.chemistry.model.Application.App;
import com.yuzhiyun.chemistry.model.entity.bmobEntity.Record;
import com.yuzhiyun.chemistry.model.entity.bmobEntity.User;
import com.yuzhiyun.chemistry.model.util.CONSTANT;
import com.yuzhiyun.chemistry.model.util.toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class ExamActivity extends AppCompatActivity {

    //做题时间记录
    Record record;
    public Toolbar toolbar;
    private static final int MESSAGE_WHAT = 100;
    String KEY_CHAPTER = "Chapter";
    String KEY_TYPE = "type";
    TextView tvTitle;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private ExamActivityController examActivityController;
    Bundle data;
    // 做题时间
    private long exerciseTimer = 0;
    // 开始时间
    private long startTimer = 0;
    //
    private long tempTime = 0;

    private Thread thread;

    //更新做题时间到tvTitle
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            switch (msg.what) {
//                case MESSAGE_WHAT:
            tvTitle.setText(getFormatTime(exerciseTimer));
//                    break;
//            }

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutView();
        Bmob.initialize(this, CONSTANT.BMOB_APP_ID);
        initToolBar();
        findView();
        setListener();
        initOther();
        initTimer();

    }

    void initToolBar() {
        //由于在下习惯在activity中使用toolbar，所以在此处处理了一下，请注意，继承这个BaseActivity的时候，布局文件一定要加上toolbar,不然空指针异常
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //之所以设置为“”，是因为我们通常需要把toolbar的title居中显示，由于没有函数直接居中显示，所以把title设置为空字符串，然后有必要的话再在布局文件的toolbar中添加一个居中显示的textView即可
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void setLayoutView() {
        setContentView(R.layout.activity_exam);
    }

    void findView() {
        pager = (ViewPager) findViewById(R.id.pager);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
    }

    void setListener() {
    }


    void initOther() {
        record = new Record();

//        toolbar.setLogo(R.drawable.icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        data = getIntent().getExtras();
        //获取章的名称
        int num = data.getInt(KEY_CHAPTER) + 1;
        if (data != null)
            tvTitle.setText("第" + num + "章");
        else
            tvTitle.setText("未获取到章节信息");
//        toolbar.setTitle(data.getString(KEY_CHAPTER));
        pager.setAdapter(getPagerAdapter());
    }

    /**
     * 开始计时
     */
    private void initTimer() {
        //当前时间
        startTimer = System.currentTimeMillis();
        //记录初始时间
        record.setStartTime(startTimer);
        Log.i("ExamActivity", "onCreate");
        if (null == thread) {
            thread = new Thread() {
                @Override
                public void run() {
                    super.run();
                    Log.i("ExamActivity", "run");
                    while (true) {
                        try {
                            sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        exerciseTimer = System.currentTimeMillis() - startTimer;
//                        Log.i("ExamActivity", "做题时间" + exerciseTimer);
                        handler.sendEmptyMessage(MESSAGE_WHAT);
                    }


                }
            };
            thread.start();
        }
    }

    public PagerAdapter getPagerAdapter() {
//        传递题型、章进去
        int chapter = data.getInt(KEY_CHAPTER);
        int type = data.getInt(KEY_TYPE);
        Log.i(" 传递题型、章", chapter + "  " + type);
        examActivityController = new ExamActivityController(chapter, type);
        pagerAdapter = new ExamAdapter(getSupportFragmentManager(), examActivityController.getFragmentArrayList());
        return pagerAdapter;
    }

    /**
     * 得到一个格式化的时间
     *
     * @param time 时间 毫秒
     * @return 时：分：秒：毫秒
     */
    private String getFormatTime(long time) {
        time = time / 1000;
        long second = time % 60;
        long minute = (time % 3600) / 60;
        long hour = time / 3600;

        // 毫秒秒显示两位
        // String strMillisecond = "" + (millisecond / 10);
        // 秒显示两位
        String strSecond = ("00" + second)
                .substring(("00" + second).length() - 2);
        // 分显示两位
        String strMinute = ("00" + minute)
                .substring(("00" + minute).length() - 2);
        // 时显示两位
        String strHour = ("00" + hour).substring(("00" + hour).length() - 2);

        return strHour + ":" + strMinute + ":" + strSecond;
        // + strMillisecond;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        record.setEndTime(System.currentTimeMillis());
        record.setTimeSpan(System.currentTimeMillis() - startTimer);
        record.setUser(App.getInstance().getCurrentUser());
        if ((System.currentTimeMillis() - startTimer) > 60000)
            record.save(ExamActivity.this, new SaveListener() {
                @Override
                public void onSuccess() {
                    User user = App.getInstance().getCurrentUser();
                    toast.ShowText("数据记录成功" + record.getUser().getUsername(), ExamActivity.this);
                    //更新user表，添加一个relation
                    BmobRelation relationRecord = new BmobRelation();
                    relationRecord.add(record);
                    user.setRelationRecord(relationRecord);
                    user.update(ExamActivity.this, new UpdateListener() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onFailure(int i, String s) {

                        }
                    });


                }

                @Override
                public void onFailure(int i, String s) {
                    toast.ShowText("数据记录失败 " + s, ExamActivity.this);
                    Log.e("onFailure", "数据记录失败 " + s);
                }
            });
    }
}
