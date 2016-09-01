package com.yuzhiyun.chemistry.view.activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.model.Application.App;
import com.yuzhiyun.chemistry.model.entity.bmobEntity.Record;
import com.yuzhiyun.chemistry.model.entity.bmobEntity.User;
import com.yuzhiyun.chemistry.model.util.CONSTANT;
import com.yuzhiyun.chemistry.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;

public class DataActivity extends BaseActivity implements View.OnClickListener {

    //    private static final int DEFAULT_DATA = 0;
//    private static final int SUBCOLUMNS_DATA = 1;
//    private static final int STACKED_DATA = 2;
//    private static final int NEGATIVE_SUBCOLUMNS_DATA = 3;
//    private static final int NEGATIVE_STACKED_DATA = 4;
    //activity之间传递数据的key 值
    private static final String KEY_POSITION = "position";
    //activity传递过来的
    int position;
    //指定用户的Record数据
    List<Record> recordList = new ArrayList<Record>();
    //    List<List<Record>> a=new ArrayList<>();
    private ColumnChartView chart;
    private ColumnChartData data;
    //选定用户
    TextView title;
    ImageView imgLeft;
    TextView tvTime;
    ImageView imgRight;


    //    有坐标系
    private boolean hasAxis = true;
    //    有坐标系名称
    private boolean hasAxisNames = true;
    //    每个柱体上方有值的大小
    private boolean hasLabels = true;
    private boolean hasLabelForSelected = false;
    //    private int dataType = STACKED_DATA;
    //指定用户
    String userName;

    //用map好像很麻烦，我决定使用一个int数组来记录dayOfMonth，貌似会简单很多
    List<Integer> listDayOfMonth = new ArrayList<>();

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_data);
    }

    @Override
    protected void findView() {
        chart = (ColumnChartView) findViewById(R.id.chart);
        title = (TextView) findViewById(R.id.title);

        tvTime = (TextView) findViewById(R.id.tvTime);
        imgLeft = (ImageView) findViewById(R.id.imgLeft);
        imgRight = (ImageView) findViewById(R.id.imgRight);
    }

    @Override
    protected void setListener() {
        imgLeft.setOnClickListener(this);
        imgRight.setOnClickListener(this);
    }

    @Override
    protected void initOther() {
//        设置title为上一个activity的选中的用户名
        position = getIntent().getExtras().getInt(KEY_POSITION, 0);
        userName = CONSTANT.userList.get(position).getUsername().toString();
        title.setText(userName);
        fetchData();
    }


    /**
     * 显示数据到Stack柱状图
     */
    private void setStackedData() {
        //一个月31天，所以31个柱体
        int numColumns = 31;
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            if (listDayOfMonth.indexOf(i) >= 0) {
//                Log.i("listDayOfMonth", i + "");
//                Log.i("recordList   for循环之前", recordList.size()+"");

                for (Record record : recordList) {
                    Log.i("recordList for循环 ", dateGetDayofMonth(record.getStartTime()) + "");
                    if (i == dateGetDayofMonth(record.getStartTime())) {
                        values.add(new SubcolumnValue(record.getTimeSpan() / 60000, ChartUtils.pickColor()));
                    }
                }
            }
            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);
        }

        data = new ColumnChartData(columns);

        // Set stacked flag.
        data.setStacked(true);

        if (hasAxis) {
            Axis axisX = Axis.generateAxisFromRange(1, 31, 1);

            Axis axisY = new Axis().setHasLines(true);

//            axisX.setAutoGenerated(true);
            if (hasAxisNames) {
                axisY.setName("做题时长/minute");
                axisX.setName("日期     注:点击上方按钮可切换月份");
            }
//            axisX.generateAxisFromRange(1,31,1);
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        chart.setColumnChartData(data);
    }

    /**
     * 从网络获取做题时间数据
     */
    private void fetchData() {

//进度条
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("加载数据...");
        progressDialog.show();
        BmobQuery<Record> query = new BmobQuery<>();
        query.addWhereRelatedTo("relationRecord", new BmobPointer(CONSTANT.userList.get(position)));
        query.findObjects(context, new FindListener<Record>() {
            @Override
            public void onSuccess(List<Record> list) {
                progressDialog.dismiss();
                recordList = list;
                //这种循环挺好玩的
                for (Record tem : recordList) {
//                    Log.i("recordList", tem.getTimeSpan() + "");
                }
                setDataToMap(list);
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    private void setDataToMap(List<Record> list) {
        setListDayOfMonth(list);
        setStackedData();
    }

    /**
     * 把某个月份的day都记录在listDayOfMonth里面
     *
     * @param list
     */
    //所要查看数据的年
    int year = 2016;
    //所要查看数据的月份
    int month = 8;

    private void setListDayOfMonth(List<Record> list) {
        Log.i("date",year+"年"+month+"月");
        listDayOfMonth.clear();
        for (int i = 0; i < list.size(); i++) {
            Record record = list.get(i);

            if (dateGetYear(record.getStartTime()) == year)
                if (dateGetMonth(record.getStartTime()) == month) {
                    int day = dateGetDayofMonth(record.getStartTime());
                    //如果day不在listDayOfMonth里面，就添加进去
                    if (listDayOfMonth.indexOf(day) < 0)
                        listDayOfMonth.add(day);

                }
        }
        for (int i = 0; i < listDayOfMonth.size(); i++)
            Log.i("listDayOfMonth [" + i + "] :", listDayOfMonth.get(i) + "");
    }

    int dateGetYear(long time) {
        Date date = new Date(time);
        return date.getYear() + 1900;
    }

    int dateGetMonth(long time) {
        Date date = new Date(time);
        return date.getMonth() + 1;
    }

    int dateGetDayofMonth(long time) {
        Date date = new Date(time);
        return date.getDate();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgLeft:
                if (1 == month) {
                    month = 12;
                    year -= 1;
                } else
                    month -= 1;
                setTimeToTextView();

                //更新数据
                update();

                break;
            case R.id.imgRight:
                if (12 == month) {
                    month = 1;
                    year += 1;
                } else
                    month += 1;
                setTimeToTextView();
                //更新数据
                update();
                break;
        }
    }

    /**
     * 修改月份之后
     */
    private void setTimeToTextView() {
        if (month < 10)
            tvTime.setText(year + "--0" + month);
        else
            tvTime.setText(year + "--" + month);
    }

    //修改月份之后，重新设置数据集到柱状图里面
    private void update() {
        setDataToMap(recordList);
    }
}
