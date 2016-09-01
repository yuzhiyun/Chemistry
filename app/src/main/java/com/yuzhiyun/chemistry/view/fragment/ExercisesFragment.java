package com.yuzhiyun.chemistry.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.model.dao.db;
import com.yuzhiyun.chemistry.model.entity.Exercise;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExercisesFragment extends Fragment implements View.OnClickListener{
    public String TAG = "ExercisesFragment";
    int position;
    int chapter;
    public TextView tvContent;
    public TextView tvAnswer1;
    public TextView tvAnswer2;
    public TextView tvAnswer3;
    public TextView tvAnswer4;
    public TextView tvAnswer5;
    public Exercise exercise ;
    public Button btnShowAnswer;
    public ExercisesFragment() {
        // Required empty public constructor
    }

    public ExercisesFragment(int position ,int chapter) {
        super();
        this.position = position;
        this.chapter = chapter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        findView(view);

        Log.i(TAG, "onCreateView");
        return view;
    }

    private void findView(View view) {
        tvContent = (TextView) view.findViewById(R.id.tvContent);
        tvAnswer1 = (TextView) view.findViewById(R.id.tvAnswer1);
        tvAnswer2 = (TextView) view.findViewById(R.id.tvAnswer2);
        tvAnswer3 = (TextView) view.findViewById(R.id.tvAnswer3);
        tvAnswer4 = (TextView) view.findViewById(R.id.tvAnswer4);
        tvAnswer5 = (TextView) view.findViewById(R.id.tvAnswer5);
        btnShowAnswer= (Button) view.findViewById(R.id.btnShowAnswer);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
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
        db database=new db();
        ArrayList<Exercise> exerciseArrayList=database.getChoiceList(chapter+1);
        Log.i("exerciseArrayList的size=",exerciseArrayList.size()+"");
        exercise=exerciseArrayList.get(position);
        Log.i("exercise",exercise.getQuestion()+"");
        tvContent.setText(position + 1 + "、 " + exercise.getQuestion());
        tvAnswer1.setText("A、"+exercise.getAnswer1());
        tvAnswer2.setText("B、"+exercise.getAnswer2());
        tvAnswer3.setText("C、"+exercise.getAnswer3());
        tvAnswer4.setText("D、"+exercise.getAnswer4());
        tvAnswer5.setText("D、"+exercise.getAnswer5());
//        Log.e("答案initView", exercise.getRightAnswer() + " --答案");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnShowAnswer:
                exercise=new db().getChoiceList(chapter+1).get(position);
                int rightAnswer=exercise.getRightAnswer();
//                Log.e("答案",rightAnswer+" --答案");
                switch (rightAnswer){
                    case 1:
                        tvAnswer1.setTextColor(getResources().getColor(R.color.primary));
                        tvAnswer1.setTextSize(30);
                        break;
                    case 2:
                        tvAnswer2.setTextColor(getResources().getColor(R.color.primary));
                        tvAnswer2.setTextSize(30);
                        break;
                    case 3:
                        tvAnswer3.setTextColor(getResources().getColor(R.color.primary));
                        tvAnswer3.setTextSize(30);
                        break;
                    case 4:
                        tvAnswer4.setTextColor(getResources().getColor(R.color.primary));
                        tvAnswer4.setTextSize(30);
                        break;
                    case 5:
                        tvAnswer5.setTextColor(getResources().getColor(R.color.primary));
                        tvAnswer5.setTextSize(30);
                        break;
                    default:
                        Toast.makeText(getActivity(),"没有正确答案",Toast.LENGTH_LONG).show();
                        break;
                }
                break;
             default:
                break;
        }
    }
}

