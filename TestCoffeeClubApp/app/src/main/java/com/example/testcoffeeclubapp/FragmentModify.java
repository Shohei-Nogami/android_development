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

public class FragmentModify extends Fragment {

    private int cnt = 0;

    public static FragmentModify newInstance(int count) {
        // FragmentModify インスタンス生成
        FragmentModify FragmentModify = new FragmentModify();

        // Bundleにパラメータを設定
        Bundle barg = new Bundle();
        barg.putInt("Counter", count);
        FragmentModify.setArguments(barg);

        return FragmentModify;
    }

    // FragmentのViewを生成して返す
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_modify,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

        if (args != null) {
            int count = args.getInt("Counter");
            String str = "Fragment02: " + String.valueOf(count);
            cnt = count + 1;

            TextView textView = view.findViewById(R.id.textView);
            textView.setText(str);
        }

        Button button02 = view.findViewById(R.id.buttonModifying);
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager != null) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    // BackStackを設定
                    fragmentTransaction.addToBackStack(null);

                    fragmentTransaction.replace(R.id.container, FragmentModifyConfirmation.newInstance(cnt));
                    fragmentTransaction.commit();
                }
            }
        });

        // BackStackで１つ戻す
        Button buttonBack = view.findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager != null) {
                    fragmentManager.popBackStack();
                }
            }
        });
    }
}
