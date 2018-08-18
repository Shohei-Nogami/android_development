package com.example.testcoffeeclubapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;

public class FragmentModifyConfirmation extends Fragment {

    private int cnt = 0;
    //年月取得
    Time time;

    public static FragmentModifyConfirmation newInstance(int count,String userName,String modifyDate,String modifyCost){
        // FragmentModifyConfirmation インスタンス生成
        FragmentModifyConfirmation FragmentModifyConfirmation = new FragmentModifyConfirmation();

        // Bundleにパラメータを設定
        Bundle barg = new Bundle();
        barg.putInt("Counter", count);
        barg.putString("UserName", userName);
        barg.putString("ModifyDate", modifyDate);
        barg.putString("ModifyCost", modifyCost);

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
        //
        TextView textUserName = view.findViewById(R.id.textUserName);
        TextView textModifyDaily = view.findViewById(R.id.textModifyDaily);
        TextView textModifyValue = view.findViewById(R.id.textModifyValue);
        textUserName.setText(args.getString("UserName"));
        textModifyDaily.setText(args.getString("ModifyDate"));
        textModifyValue.setText(args.getString("ModifyCost"));
        // BackStackで１つ戻す
        Button buttonYesNo[] = new Button[2];
        buttonYesNo[0] = view.findViewById(R.id.buttonYes);
        buttonYesNo[0].setOnClickListener(new ConfirmationBtClick());
        buttonYesNo[1] = view.findViewById(R.id.buttonNo);
        buttonYesNo[1].setOnClickListener(new ConfirmationBtClick());
    }
    class ConfirmationBtClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            getNowDate();
            Bundle args = getArguments();
            String userName = args.getString("UserName");
            String modifyDate = args.getString("ModifyDate");
            String modifyDateList[] = modifyDate.split(",",0);
            String modifyCost = args.getString("ModifyCost");
            String writeMessage = modifyDateList[0]  + "," + modifyDateList[1] + "," + modifyDateList[2]  + "," + "--"+ "," + "--" + "," + "" + "," + "" + "," + modifyCost + "\n";
            String fileName = userName + "_" + modifyDateList[0] + "_" + modifyDateList[1] + ".txt";
            switch (view.getId()) {
                case R.id.buttonYes:
                    System.out.println(fileName + "," + writeMessage);
                    try{
                        FileOutputStream out = getActivity().openFileOutput(fileName, Context.MODE_PRIVATE | Context.MODE_APPEND );
                        out.write( writeMessage.getBytes()   );
                    }catch( IOException e ){
                        e.printStackTrace();
                    }

                case R.id.buttonNo:
                    FragmentManager fragmentManager = getFragmentManager();
                    if(fragmentManager != null) {
                        fragmentManager.popBackStack();
                    }

                    break;
            }
        }
    }
    public void getNowDate(){
        time = new Time("Asia/Tokyo");
        time.setToNow();
    }
}
