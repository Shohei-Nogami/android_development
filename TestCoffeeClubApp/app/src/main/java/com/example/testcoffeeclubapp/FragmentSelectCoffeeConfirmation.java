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

public class FragmentSelectCoffeeConfirmation  extends Fragment {

    private int cnt = 0;

    public static FragmentSelectCoffeeConfirmation newInstance(int count){
        // Fragmnt02 インスタンス生成
        FragmentSelectCoffeeConfirmation FragmentSelectCoffeeConfirmation = new FragmentSelectCoffeeConfirmation();

        // Bundleにパラメータを設定
        Bundle barg = new Bundle();
        barg.putInt("Counter", count);
        FragmentSelectCoffeeConfirmation.setArguments(barg);

        return FragmentSelectCoffeeConfirmation;
    }

    // FragmentのViewを生成して返す
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_select_coffee_confirmation,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

        if(args != null ){
            int count = args.getInt("Counter");
            String str = "FragmentSelectCoffeeConfirmation: " + String.valueOf(count);
            cnt = count +1;

            TextView textView = view.findViewById(R.id.textview_02);
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
