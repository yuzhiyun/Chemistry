package com.yuzhiyun.chemistry.controller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.model.dao.db;
import com.yuzhiyun.chemistry.model.entity.Exercise;
import com.yuzhiyun.chemistry.model.entity.NoChoiceExercise;
import com.yuzhiyun.chemistry.model.util.UtilHtml;

import java.util.ArrayList;

public class NoChoiceExerciseFragment extends Fragment implements View.OnClickListener {
    public String TAG = "NoChoiceExerciseFragment";
    int position;
    int type;
    int chapter;
    NoChoiceExercise noChoiceExercise;

    TextView tvQuestion;
    TextView tvAnswer;
    Button btnShowAnswer;

    public NoChoiceExerciseFragment(int position, int type, int chapter) {
        super();
        this.position = position;
        this.type = type;
        this.chapter = chapter;
    }

    public NoChoiceExerciseFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_no_choice_exercise, container, false);
        findView(view);

        Log.i(TAG, "onCreateView");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        initView();
        setListener();
    }

    private void setListener() {
        btnShowAnswer.setOnClickListener(this);

    }

    private void initView() {
        db database = new db();
        ArrayList<NoChoiceExercise> exerciseArrayList = database.getList(type, chapter + 1);
        Log.i("exerciseArrayList的size=", exerciseArrayList.size() + "");
        noChoiceExercise = exerciseArrayList.get(position);
        String a = noChoiceExercise.getQuestion();
//        tvQuestion.setText(position + 1 + "、 " + a);
        tvQuestion.setText(Html.fromHtml(UtilHtml.createHtml(position + 1 + "、 " +a)+"" , UtilHtml.imageGetter, null));

        String b = noChoiceExercise.getAnswer().replace("\\n", "\n").replace("\\t", "\t");
//数据库中的数据应该先写\n再写\t
        //如果是判断题，0代表错误，1代表正确
        if (4 == type)
            if (b.equals("0"))
                tvAnswer.setText("错");
            else
                tvAnswer.setText("对");
        else
            tvAnswer.setText("" + b);
        String c = "name\\nname";
        Log.i("检测c", c);
        Log.i("检测c", c.replace("\\n", "\n"));
    }

    private void findView(View view) {
        tvQuestion = (TextView) view.findViewById(R.id.tvQuestion);
        tvAnswer = (TextView) view.findViewById(R.id.tvAnswer);

        btnShowAnswer = (Button) view.findViewById(R.id.btnShowAnswer);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnShowAnswer:
                tvAnswer.setVisibility(View.VISIBLE);
                break;
        }
    }
}
