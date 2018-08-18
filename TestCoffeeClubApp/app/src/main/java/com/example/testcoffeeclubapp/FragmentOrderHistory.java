package com.example.testcoffeeclubapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;

public class FragmentOrderHistory extends Fragment {

    private int cnt = 0;
    //ユーザ選択
//    private TextView textUser;
    private Button buttonUser[] = new Button[5];
    //ファイルネーム
    String fileName;
    String userName;
    //Time
    private String timeString;
    Time time;
    //ファイルの中身表示
    private  TextView textHistory;
    //月間請求金額
    private  TextView textChargeMonth;
    //debug用
    private  TextView textDebug;

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
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

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
        // ボタン入力取得(ユーザ名) by カスタムクラス
        for(int i = 0; i < buttonUser.length; i++){
            buttonUser[i] = view.findViewById(R.id.buttonUser1+i);
            buttonUser[i].setOnClickListener(new UserBtClick());
        }
        // BackStackで１つ戻す
        Button buttonBack = view.findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new BackBtClick());
        //履歴表示
        textHistory = view.findViewById(R.id.textOrderHistory);
        //月額請求金額
        textChargeMonth = view.findViewById(R.id.textChargeMonth);
        //debug用
        Button buttonDebug[] = new Button[2];
        buttonDebug[0] = view.findViewById(R.id.buttonshow);
        buttonDebug[0].setOnClickListener(new DebugBtClick());
        buttonDebug[1] = view.findViewById(R.id.buttondelete);
        buttonDebug[1].setOnClickListener(new DebugBtClick());
        textDebug =  view.findViewById(R.id.textDebug);
    }
    class UserBtClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String str;
            textHistory.setText("");
            textChargeMonth.setText("");

            switch (view.getId()) {
                case R.id.buttonUser1:
                    str = getString(R.string.user1);
                    System.out.println(str);
                    userName = str;
                    ShowHistory(str);
                    break;
                case R.id.buttonUser2:
                    str = getString(R.string.user2);
                    userName = str;
                    System.out.println(str);
                    ShowHistory(str);
                    break;
                case R.id.buttonUser3:
                    str = getString(R.string.user3);
                    userName = str;
                    System.out.println(str);
                    ShowHistory(str);
                    break;
                case R.id.buttonUser4:
                    str = getString(R.string.user4);
                    userName = str;
                    System.out.println(str);
                    ShowHistory(str);
                    break;
                case R.id.buttonUser5:
                    str = getString(R.string.user5);
                    userName = str;
                    System.out.println(str);

                    break;
            }

        }
    }
    class BackBtClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.buttonBack:
                    // BackStackで１つ戻す
                    FragmentManager fragmentManager = getFragmentManager();
                    if(fragmentManager != null) {
                        fragmentManager.popBackStack();
                    }
                    break;

            }
        }
    }
    public void ShowHistory(String userName0){
        getNowDate();
        fileName = userName0 + "_" + time.year + "_" + (time.month+1) + ".txt";
        try{
            FileInputStream fileInputStream;
            fileInputStream = getActivity().openFileInput(fileName);
            byte[] readBytes = new byte[fileInputStream.available()];
            fileInputStream.read(readBytes);
            String tmp = new String(readBytes);
            Log.v("readString", tmp);

            System.out.println(fileName);
            getFileContent(tmp);
        }catch( IOException e ) {
            e.printStackTrace();
        }
    }
    public void getFileContent(String str){
        String[] fileContentsList = str.split("\n");
        /*---fileContentsの中身
        String[0] : 年
        String[1] : 月
        String[2] : 日
        String[3] : 時
        String[4] : 分
        String[5] : 種類(安いの:30円, 高いの50円)
        String[6] : サイズ(小:1.0倍, 大:1.5倍)
        String[7] : 合計金額
         */
        System.out.println("kaigyo\n");
        System.out.println(str);
        System.out.println("kaigyo\n");
        int i = 0;
        double sum = 0;
        StringBuffer buf = new StringBuffer();
        do{
            System.out.println(String.valueOf(fileContentsList.length) + ":" + String.valueOf(i) );
            System.out.println(fileContentsList[i]);
            String fileContents[] = fileContentsList[i].split(",",0);
            sum += Double.parseDouble(fileContents[7]);
            buf.append(fileContents[0] + "年" + fileContents[1] + "月" + fileContents[2] + "日" + fileContents[3] + "時" + fileContents[4] + "分" + " " + fileContents[7] + " 円\n");
        }while(i++ < fileContentsList.length - 1);
        String strHistory = buf.toString();

        textHistory.setText(strHistory);
        String fileContents0[] = fileContentsList[0].split(",",0);
        textChargeMonth.setText(fileContents0[0] + "年" + fileContents0[1] + "月" + " " + String.valueOf(sum) + " 円\n");
    }
    public void getNowDate(){
        time = new Time("Asia/Tokyo");
        time.setToNow();
        timeString = time.year + "," + (time.month+1) + "," + time.monthDay + "," + time.hour + "," + time.minute;
    }

    class DebugBtClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            getNowDate();
            String fileName = userName + "_" + time.year + "_" + (time.month+1) + ".txt";
            textDebug.setText(" ");
            switch (view.getId()) {
                case R.id.buttonshow:
                    try{
                        FileInputStream fileInputStream;
                        fileInputStream = getActivity().openFileInput(fileName);
                        byte[] readBytes = new byte[fileInputStream.available()];
                        fileInputStream.read(readBytes);
                        String tmp = new String(readBytes);
                        Log.v("readString", tmp);

                        System.out.println(fileName);
                        textDebug.setText(tmp);
                    }catch( IOException e ) {
                        e.printStackTrace();
                    }

                    break;
                case R.id.buttondelete:
                    getActivity().deleteFile( fileName );
                    System.out.println(fileName);

                    break;

            }
        }
    }
}
