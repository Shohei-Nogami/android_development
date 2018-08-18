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
import android.widget.EditText;
import android.widget.TextView;

public class FragmentModify extends Fragment {

    private int cnt = 0;
    String userName = "";
    //ユーザ選択
    private Button buttonUser[] = new Button[5];
    //訂正内容
    EditText textModifyDate;
    EditText textModifyCost;
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

        //訂正内容取得textの設定
        textModifyDate = getActivity().findViewById(R.id.editTextModifyDaily);
        textModifyCost = getActivity().findViewById(R.id.textModifyValue);

        Button button02 = view.findViewById(R.id.buttonModifying);
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String modifyDateTemp = textModifyDate.getText().toString();
                String[] modifyDateList = modifyDateTemp.split(",");
                String modifyCostTemp = textModifyCost.getText().toString();
                boolean breakFlag = false;
                for(int i = 0; i < modifyDateList.length; i++){
                    if(!isNumber(modifyDateList[i])){
                        breakFlag = true;
                        break;
                    }
                }
                if(!isNumber(modifyCostTemp)){
                    breakFlag = true;
                }
//                System.out.println(breakFlag);
//                System.out.println(userName.equals(""));
//                System.out.println(modifyDateList.length);
//                System.out.println(modifyCostTemp.equals(""));
                if(userName.equals("") || modifyDateList.length != 3 || modifyCostTemp.equals("") || breakFlag == true){
                    return ;
                }

                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager != null) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    // BackStackを設定
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.container, FragmentModifyConfirmation.newInstance(cnt,userName,textModifyDate.getText().toString(),textModifyCost.getText().toString()));
                    fragmentTransaction.commit();
                }
            }
        });
        // ボタン入力取得(ユーザ名) by カスタムクラス
        for(int i = 0; i < buttonUser.length; i++){
            buttonUser[i] = view.findViewById(R.id.buttonUser1+i);
            buttonUser[i].setOnClickListener(new UserBtClick());
        }
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
    class UserBtClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.buttonUser1:
                    userName = getString(R.string.user1);
                    break;
                case R.id.buttonUser2:
                    userName = getString(R.string.user2);
                    break;
                case R.id.buttonUser3:
                    userName = getString(R.string.user3);
                    break;
                case R.id.buttonUser4:
                    userName = getString(R.string.user4);
                    break;
                case R.id.buttonUser5:
                    userName = getString(R.string.user5);
                    break;
            }
        }
    }
    public boolean isNumber(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
