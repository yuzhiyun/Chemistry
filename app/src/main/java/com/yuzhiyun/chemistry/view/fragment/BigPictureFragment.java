package com.yuzhiyun.chemistry.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yuzhiyun.chemistry.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BigPictureFragment extends Fragment {


    public BigPictureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_big_picture,container,false);

        ImageView imgQrcode = (ImageView) view.findViewById(R.id.imgQrcode);
        imgQrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }

}
