package com.example.testcoffeeclubapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentModifyConfirmation extends Fragment {

    private int cnt = 0;

    public static FragmentModifyConfirmation newInstance(int count){
        // FragmentModifyConfirmation インスタンス生成
        FragmentModifyConfirmation FragmentModifyConfirmation = new FragmentModifyConfirmation();

        // Bundleにパラメータを設定
        Bundle barg = new Bundle();
        barg.putInt("Counter", count);
        FragmentModifyConfirmation.setArguments(barg);

        return FragmentModifyConfirmation;
    }

    // FragmentのViewを生成して返す
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_modify_confirmation,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

        if(args != null ){
            int count = args.getInt("Counter");
            String str = "FragmentModifyConfirmation: " + String.valueOf(count);
            cnt = count +1;

            TextView textView = view.findViewById(R.id.textView);
            textView.setText(str);
        }

        // BackStackで１つ戻す
        Button buttonYes = view.findViewById(R.id.buttonYes);
        buttonYes.setOnClickListener(new View.OnClickListener(){
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
