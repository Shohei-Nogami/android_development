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

public class FragmentMainMenu extends Fragment{

    private int cnt = 0;

    public static FragmentMainMenu newInstance(int count) {
        // Fragment01 インスタンス生成
        FragmentMainMenu fragment01 = new FragmentMainMenu();

        // Bundle にパラメータを設定
        Bundle args = new Bundle();
        args.putInt("Counter", count);
        fragment01.setArguments(args);

        return fragment01;
    }

    // FragmentのViewを生成して返す
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main_menu,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

        if (args != null) {
            int count = args.getInt("Counter");
            String str = "Fragment01: " + String.valueOf(count);
            cnt = count + 1;

            TextView textView = view.findViewById(R.id.textview_01);
            textView.setText(str);
        }

        Button buttonSelectCoffee = view.findViewById(R.id.buttonSelectCoffee);
        buttonSelectCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getFragmentManager();

                if (fragmentManager != null) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    // BackStackを設定
                    fragmentTransaction.addToBackStack(null);

                    fragmentTransaction.replace(R.id.container, FragmentSelectCoffee.newInstance(cnt));
                    fragmentTransaction.commit();
                }
            }
        });

        Button buttonOrderHistory = view.findViewById(R.id.buttonOrderHistory);
        buttonOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getFragmentManager();

                if (fragmentManager != null) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    // BackStackを設定
                    fragmentTransaction.addToBackStack(null);

                    fragmentTransaction.replace(R.id.container, FragmentOrderHistory.newInstance(cnt));
                    fragmentTransaction.commit();
                }
            }
        });
        Button buttonOrderModify = view.findViewById(R.id.buttonOrderModify);
        buttonOrderModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getFragmentManager();

                if (fragmentManager != null) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    // BackStackを設定
                    fragmentTransaction.addToBackStack(null);

                    fragmentTransaction.replace(R.id.container, FragmentModifyHistory.newInstance(cnt));
                    fragmentTransaction.commit();
                }
            }
        });
    }
}