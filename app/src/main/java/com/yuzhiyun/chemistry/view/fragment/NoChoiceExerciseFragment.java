package com.yuzhiyun.chemistry.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;

public class NoChoiceExerciseFragment extends Fragment {
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

    public NoChoiceExerciseFragment( ) {
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


    }

    private void initView() {
        db database=new db();
        ArrayList<NoChoiceExercise> exerciseArrayList=database.getList(type, chapter + 1);
        Log.i("exerciseArrayList的size=",exerciseArrayList.size()+"");
        noChoiceExercise=exerciseArrayList.get(position);
        String a=noChoiceExercise.getQuestion();
        tvQuestion.setText(position + 1 + "、 " + a);
        String b=noChoiceExercise.getAnswer().replace("\\n","\n").replace("\\t","\t");
//数据库中的数据应该先写\n再写\t
        tvAnswer.setText(""+b);
        String c="name\\nname";
        Log.i("检测c",c);
        Log.i("检测c",c.replace("\\n","\n"));
    }

    private void findView(View view) {
        tvQuestion= (TextView) view.findViewById(R.id.tvQuestion);
        tvAnswer= (TextView) view.findViewById(R.id.tvAnswer);

        btnShowAnswer= (Button) view.findViewById(R.id.btnShowAnswer);
    }

}
