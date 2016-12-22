package org.nicehiro.myshopcar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView queryResult;

    private List<Goods> goodsList;
    private MyHelper myHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myHelper = new MyHelper(this);

        findViewById(R.id.insert_goods).setOnClickListener(this);
        findViewById(R.id.query_goods).setOnClickListener(this);

        queryResult = (TextView) findViewById(R.id.query_result);
    }

    private void insert() {
        for (int i=0; i<50; i++) {
            String id = Util.getRandomString(16);
            String desc = Util.getRandomString(16);
            float price = i * 100;
            String head_link = Util.getRandomString(16);
            int remain = 50 - i;
            String time = Util.getRandomNumber(2) + ":" + Util.getRandomNumber(2) + ":" +
                    Util.getRandomNumber(2);

            Goods goods = new Goods(id, desc, price, head_link, remain, time);
            myHelper.insert(goods);
        }
    }

    private void query() {
        goodsList = myHelper.query();
        StringBuilder stringBuilder = new StringBuilder();
        for (Goods goods : goodsList) {
            stringBuilder.append(goods.toString()).append("\n");
        }
        queryResult.setText(stringBuilder.toString());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.insert_goods:
                insert();
                break;
            case R.id.query_goods:
                query();
                break;
        }
    }
}
