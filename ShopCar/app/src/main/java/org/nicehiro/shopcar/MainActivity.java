package org.nicehiro.shopcar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.nicehiro.ShopCar.DaoMaster;
import org.nicehiro.ShopCar.DaoSession;
import org.nicehiro.ShopCar.shopcar;
import org.nicehiro.ShopCar.shopcarDao;

import java.util.Date;
import java.util.List;

import de.greenrobot.dao.Property;
import de.greenrobot.dao.query.QueryBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_add;
    private Button btn_query;
    private TextView tv_show;

    private DaoMaster.DevOpenHelper dbHelper;
    private SQLiteDatabase db;
    private DaoSession daoSession;
    private DaoMaster daoMaster;
    private shopcarDao mshopcarDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        dbHelper = new DaoMaster.DevOpenHelper(this, "shopcar", null);
        db = dbHelper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        mshopcarDao = daoSession.getShopcarDao();
    }

    private void initView() {
        btn_add = (Button) findViewById(R.id.add);
        btn_query = (Button) findViewById(R.id.query);
        tv_show = (TextView) findViewById(R.id.show_result);

        btn_query.setOnClickListener(this);
        btn_add.setOnClickListener(this);
    }

    private void add() {
        for (int i=0; i<10; i++) {
            shopcar shopcar = new shopcar(Util.getRandomByteArray(10),
                    Util.getRandomByteArray(10),
                    (float) 20.00,
                    Util.getRandomByteArray(10),
                    5,
                    new Date(2005, Util.getRandomNumber(12)+1, Util.getRandomNumber(30)+1, Util.getRandomNumber(24),
                            Util.getRandomNumber(60), Util.getRandomNumber(60)));
            Log.d("TAG", shopcar.toString());
            mshopcarDao.insert(shopcar);
        }
    }

    private String query() {
        StringBuilder sb = new StringBuilder();
        QueryBuilder<shopcar> qb = mshopcarDao.queryBuilder().orderDesc(shopcarDao.Properties.Time);
        List<shopcar> list = qb.list();
        for (shopcar s : list) {
            sb = sb.append(s);
        }
        return sb.toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                add();
                Toast.makeText(this, "Insert compelit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.query:
                String result = query();
                tv_show.setText(result);
                break;
        }
    }
}
