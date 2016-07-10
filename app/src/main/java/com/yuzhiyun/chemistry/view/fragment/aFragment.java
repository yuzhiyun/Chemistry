package com.yuzhiyun.chemistry.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.yuzhiyun.chemistry.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class aFragment extends Fragment {
    public String TAG = "aFragment";
    int position;
    public TextView tv;


    public aFragment() {
        // Required empty public constructor
    }
//
    public aFragment(int position) {
        super();
        this.position = position;
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
        tv = (TextView) view.findViewById(R.id.tv);
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
    }

    private void initView() {
        tv.setText("position --"+position);
    }
}
