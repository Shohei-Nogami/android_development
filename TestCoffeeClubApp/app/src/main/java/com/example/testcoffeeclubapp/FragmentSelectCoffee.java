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

import static com.example.testcoffeeclubapp.FragmentSelectCoffee.defaultList.DEFAULT_COSTSUM;
import static com.example.testcoffeeclubapp.FragmentSelectCoffee.defaultList.DEFAULT_USERNAME;

public class FragmentSelectCoffee extends Fragment {

    private int cnt = 0;
    //ユーザ選択
    private TextView textUser;
    private Button buttonUser[] = new Button[5];
    //コーヒーの種類
    private TextView textKinds;
    private Button buttonCoffeeKind[] = new Button[2];
    //コーヒーのサイズ
    private TextView textSize;
    private Button buttonCoffeeSize[] = new Button[8];
    //合計金額と確定ボタン
    private TextView textSum;
    private Button buttonOrder;
    private Button buttonBack;

    //金額
    public float costKind;//コーヒーの種類に対する金額
    public double costSize;//コーヒーのサイズに対する金額倍率
    public double costSum;//合計金額
    //マクロ
    public class costKindList{
        private costKindList(){}
        public static final float COST_LOW = 30;
        public static final float COST_HIGH = 50;
        public static final float COST_NONE = 0;
    }
    public class costSizeList{
        private costSizeList(){}
        public static final double SIZE_SMALL = 1.0;
        public static final double SIZE_BIG = 1.5;
        public static final double SIZE_NONE = 0;
    }
    public class defaultList{
        private defaultList(){}
        public static final String DEFAULT_USERNAME = "ユーザ名";
        public static final float DEFAULT_COSTKIND = 0;
        public static final double DEFAULT_COSTSIZE = 0;
        public static final double DEFAULT_COSTSUM = 0;
    }
    public static FragmentSelectCoffee newInstance(int count){
        // FragmentSelectCoffee インスタンス生成
        FragmentSelectCoffee fragment02 = new FragmentSelectCoffee();

        // Bundleにパラメータを設定
        Bundle barg = new Bundle();
        barg.putInt("Counter", count);
        fragment02.setArguments(barg);

        return fragment02;
    }

    // FragmentのViewを生成して返す
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_coffee,
                container, false);
    }

    class UserBtClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String str;
            switch (view.getId()) {
                case R.id.buttonUser1:
                    str = getString(R.string.user1);
                    textUser.setText(str);
                    break;
                case R.id.buttonUser2:
                    str = getString(R.string.user2);
                    textUser.setText(str);
                    break;
                case R.id.buttonUser3:
                    str = getString(R.string.user3);
                    textUser.setText(str);
                    break;
                case R.id.buttonUser4:
                    str = getString(R.string.user4);
                    textUser.setText(str);
                    break;
                case R.id.buttonUser5:
                    str = getString(R.string.user5);
                    textUser.setText(str);
                    break;
            }
        }
    }
    class KindBtClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String str;
            switch (view.getId()) {
                case R.id.buttonHighValue:
                    str = getString(R.string.kind1);
                    textKinds.setText(str);
                    costKind = costKindList.COST_HIGH;
                    break;
                case R.id.buttonLowValue:
                    str = getString(R.string.kind2);
                    textKinds.setText(str);
                    costKind = costKindList.COST_LOW;
                    break;
                default:
                    costKind = costKindList.COST_NONE;
            }
            costSum = costKind * costSize;
            textSum.setText(String.valueOf(costSum));
        }
    }
    class SizeBtClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String str;
            switch (view.getId()) {
                case R.id.buttonSizeBig1:
                    str = getString(R.string.sizeBig1);
                    textSize.setText(str);
                    costSize = costSizeList.SIZE_BIG * 1;
                    break;
                case R.id.buttonSizeBig2:
                    str = getString(R.string.sizeBig2);
                    textSize.setText(str);
                    costSize = costSizeList.SIZE_BIG * 2;
                    break;
                case R.id.buttonSizeBig3:
                    str = getString(R.string.sizeBig3);
                    textSize.setText(str);
                    costSize = costSizeList.SIZE_BIG * 3;
                    break;
                case R.id.buttonSizeSmall1:
                    str = getString(R.string.sizeSmall1);
                    textSize.setText(str);
                    costSize = costSizeList.SIZE_SMALL * 1;
                    break;
                case R.id.buttonSizeSmall2:
                    str = getString(R.string.sizeSmall2);
                    textSize.setText(str);
                    costSize = costSizeList.SIZE_SMALL * 2;
                    break;
                case R.id.buttonSizeSmall3:
                    str = getString(R.string.sizeSmall3);
                    textSize.setText(str);
                    costSize = costSizeList.SIZE_SMALL * 3;
                    break;
                case R.id.buttonSizeSmall4:
                    str = getString(R.string.sizeSmall4);
                    textSize.setText(str);
                    costSize = costSizeList.SIZE_SMALL * 4;
                    break;
                case R.id.buttonSizeSmall5:
                    str = getString(R.string.sizeSmall5);
                    textSize.setText(str);
                    costSize = costSizeList.SIZE_SMALL * 5;
                    break;
                default:
                    costSize = costSizeList.SIZE_NONE;
            }
            costSum = costKind * costSize;
            textSum.setText(String.valueOf(costSum));
        }
    }

    class OrderBtClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.buttonOrdering:
                    if(textUser.getText().toString().equals(DEFAULT_USERNAME) || costSum == DEFAULT_COSTSUM){

                        break;
                    }
                    FragmentManager fragmentManager = getFragmentManager();
                    if(fragmentManager != null) {
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        // BackStackを設定
                        fragmentTransaction.addToBackStack(null);

                        fragmentTransaction.replace(R.id.container, FragmentSelectCoffeeConfirmation.newInstance(
                                cnt,
                                textUser.getText().toString(),
                                costKind,
                                costSize,
                                costSum,
                                textKinds.getText().toString(),
                                textSize.getText().toString(),
                                textSum.getText().toString() ));
                        fragmentTransaction.commit();
                    }
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
                    if (fragmentManager != null) {
                        fragmentManager.popBackStack();
                    }
                    break;

            }
        }
    }
    
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

        if(args != null ){
            int count = args.getInt("Counter");
            String str = "Fragment02: " + String.valueOf(count);
            cnt = count +1;

            TextView textView = view.findViewById(R.id.textview_02);
            textView.setText(str);
        }

        textUser = view.findViewById(R.id.textUserName);
        // ボタン入力取得(ユーザ名) by カスタムクラス
        for(int i = 0; i < buttonUser.length; i++){
            buttonUser[i] = view.findViewById(R.id.buttonUser1+i);
            buttonUser[i].setOnClickListener(new UserBtClick());
        }
        textKinds = view.findViewById(R.id.textCoffeeKind);
        // ボタン入力取得(コーヒーの種類) by カスタムクラス
        for(int i = 0; i < buttonCoffeeKind.length; i++){
            buttonCoffeeKind[i] = view.findViewById(R.id.buttonHighValue+i);
            buttonCoffeeKind[i].setOnClickListener(new KindBtClick());
        }
        textSize = view.findViewById(R.id.textCoffeeSize);
        // ボタン入力取得(コーヒーの量) by カスタムクラス
        for(int i = 0; i < buttonCoffeeSize.length; i++) {
            buttonCoffeeSize[i] = view.findViewById(R.id.buttonSizeBig1 + i);
            buttonCoffeeSize[i].setOnClickListener(new SizeBtClick());
        }
        //合計金額と確定ボタン
        textSum = view.findViewById(R.id.textSum);
        buttonOrder = view.findViewById(R.id.buttonOrdering);
        buttonOrder.setOnClickListener(new OrderBtClick());
        buttonBack = view.findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new BackBtClick());
        //金額初期化
        costKind = costKindList.COST_NONE;
        costSize = costSizeList.SIZE_NONE;
        costSum = costKind * costSize;
        textSum.setText(String.valueOf(costSum));

    }
}
