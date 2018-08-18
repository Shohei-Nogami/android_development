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


public class FragmentSelectCoffeeConfirmation  extends Fragment {

    private int cnt = 0;
    private String timeString;
    Time time;
    TextView textView;
    /*
    FragmentSelectCoffeeConfirmation (
                                    int count,
                                    float costKind,
                                    double costSize,
                                    double costSum
                                    );
    float costKind;//コーヒーの種類に対する金額
    double costSize;//コーヒーのサイズに対する金額倍率
    double costSum;//合計金額
     */
    public static FragmentSelectCoffeeConfirmation newInstance(int count,String userName,float costKind,double costSize,double costSum,String costKindStr,String costSizeStr,String costSumStr){
        // FragmentSelectCoffeeConfirmation インスタンス生成
        FragmentSelectCoffeeConfirmation FragmentSelectCoffeeConfirmation = new FragmentSelectCoffeeConfirmation();


        // Bundleにパラメータを設定
        Bundle barg = new Bundle();
        barg.putInt("Counter", count);
        barg.putString("UserName",userName);
        barg.putString("CostKind",String.valueOf(costKind));
        barg.putString("CostSize",String.valueOf(costSize));
        barg.putString("CostSum",String.valueOf(costSum));
        barg.putString("CostKindStr",costKindStr);
        barg.putString("CostSizeStr",costSizeStr);
        barg.putString("CostSumStr",costSumStr);
        FragmentSelectCoffeeConfirmation.setArguments(barg);

        return FragmentSelectCoffeeConfirmation;
    }
    public void getNowDate(){
        time = new Time("Asia/Tokyo");
        time.setToNow();
        timeString = time.year + "," + (time.month+1) + "," + time.monthDay + "," + time.hour + "," + time.minute;
    }
    // FragmentのViewを生成して返す
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_select_coffee_confirmation,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onActivityCreated(savedInstanceState);

        Bundle args = getArguments();

        if(args != null ){
            int count = args.getInt("Counter");
            String str = "FragmentSelectCoffeeConfirmation: " + String.valueOf(count);
            cnt = count +1;

            TextView textView = view.findViewById(R.id.textview_02);
            textView.setText(str);
        }
        //入力情報の表示
        TextView textUserName = view.findViewById(R.id.textUserName);
        TextView textCoffeeKind = view.findViewById(R.id.textCoffeeKind);
        TextView textCoffeeSize = view.findViewById(R.id.textCoffeeSize);
        TextView textSum = view.findViewById(R.id.textSum);
        textUserName.setText(args.getString("UserName") );
        textCoffeeKind.setText(args.getString("CostKindStr") );
        textCoffeeSize.setText(args.getString("CostSizeStr") );
        textSum.setText(args.getString("CostSumStr") );

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
            String costKind = args.getString("CostKind");
            String costSize = args.getString("CostSize");
            String costSum = args.getString("CostSum");
            String writeMessage = timeString + "," + costKind + "," + costSize + "," + costSum + "\n";
            String fileName = userName + "_" + time.year + "_" + (time.month+1) + ".txt";
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
}
