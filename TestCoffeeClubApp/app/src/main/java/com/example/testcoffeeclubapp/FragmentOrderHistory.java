package com.example.testcoffeeclubapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentOrderHistory extends Fragment {

    private int cnt = 0;

    public static FragmentOrderHistory newInstance(int count){
        // FragmentOrderHistory インスタンス生成
        FragmentOrderHistory FragmentOrderHistory = new FragmentOrderHistory();

        // Bundleにパラメータを設定
        Bundle barg = new Bundle();
        barg.putInt("Counter", count);
        FragmentOrderHistory.setArguments(barg);

        return FragmentOrderHistory;
    }

    // FragmentのViewを生成して返す
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_ordering_history,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

        if(args != null ){
            int count = args.getInt("Counter");
            String str = "FragmentOrderHistory: " + String.valueOf(count);
            cnt = count +1;

            TextView textView = view.findViewById(R.id.textView);
            textView.setText(str);
        }

        // BackStackで１つ戻す
        Button buttonBack = view.findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FragmentManager fragmentManager = getFragmentManager();
                if(fragmentManager != null) {
                    fragmentManager.popBackStack();
                }
            }
        });
    }
}
