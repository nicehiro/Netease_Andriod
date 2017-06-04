package org.nicehiro.mybook;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Calendar;

/**
 * Created by hiro on 17-3-17.
 */

public class BookDetail extends AppCompatActivity implements View.OnClickListener {

    private TextView title;
    private TextView author;
    private TextView translator;
    private TextView tag;
    private TextView price;
    private TextView publisher;
    private TextView pubDate;
    private TextView pages;
    private TextView isbn;
    private TextView summary;
    private ImageView image;
    private TextView comment;
    private Button commit;

    private Toolbar toolbar;
    private  ImageView star_image;
    private TextView save_text;

    private boolean saveFlag = false;

    private DatabaseHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        initView();

        dbHelper = new DatabaseHelper(this, "History.db", null, 1);
        db = dbHelper.getWritableDatabase();

        Intent intent = getIntent();
        setViewContent(intent);
    }

    private void initView() {
        star_image = (ImageView) this.findViewById(R.id.star_detail);
        star_image.setOnClickListener(this);

        save_text = (TextView) this.findViewById(R.id.save_detatil);
        save_text.setOnClickListener(this);

        toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(this.getString(R.string.book_detail));
        toolbar.setNavigationIcon(R.drawable.back2);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDetail.this, MainActivity.class);
                BookDetail.this.startActivity(intent);
            }
        });

        pages = (TextView) this.findViewById(R.id.text_pages_detail);
        title = (TextView) this.findViewById(R.id.text_title_detail);
        author = (TextView) this.findViewById(R.id.text_author_detail);
        translator = (TextView) this.findViewById(R.id.text_yizhe_detail);
        tag = (TextView) this.findViewById(R.id.text_tags_detail);
        price = (TextView) this.findViewById(R.id.text_price_detail);
        image = (ImageView) this.findViewById(R.id.image_detail);
        publisher = (TextView) this.findViewById(R.id.text_publisher_detail);
        pubDate = (TextView) this.findViewById(R.id.text_pubdate_detail);
        isbn = (TextView) this.findViewById(R.id.text_isbn_detail);
        summary = (TextView) this.findViewById(R.id.summary_detatil);

        comment = (TextView) this.findViewById(R.id.comment);
        commit = (Button) this.findViewById(R.id.commit_comment);
        commit.setOnClickListener(this);
    }

    private void setSaveFlag(int mark) {
        if (mark == 1) {
            save_text.setText(R.string.marked);
            star_image.setImageResource(R.drawable.star_green);
        } else {
            save_text.setText(R.string.mark);
            star_image.setImageResource(R.drawable.star_white);
        }
    }

    private void setViewContent(Intent intent) {
        title.setText(intent.getStringExtra("title"));
        author.setText(intent.getStringExtra("author"));
        translator.setText(intent.getStringExtra("translator"));
        price.setText(intent.getStringExtra("price"));

        if (intent.getStringExtra("image") == null) {
            image.setImageResource(R.drawable.mybook);
        } else {
            Glide.with(this)
                    .load(intent.getStringExtra("image"))
                    .asBitmap()
                    .placeholder(R.drawable.mybook)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .error(R.drawable.mybook)
                    .centerCrop()
                    .into(image);
        }

        publisher.setText(intent.getStringExtra("publisher"));
        pubDate.setText(intent.getStringExtra("pubDate"));
        isbn.setText(intent.getStringExtra("isbn"));

        Cursor cursor = db.rawQuery("select * from Book where isbn13 = ?", new String[] {isbn.getText().toString()});
        int mark = -1;
        while (cursor.moveToNext()) {
            mark = cursor.getInt(cursor.getColumnIndex("bookmark"));
        }
        Log.d("marked sign", "" + mark);
        setSaveFlag(mark);

        summary.setText(intent.getStringExtra("summary"));
        pages.setText(intent.getStringExtra("pages"));
        tag.setText(intent.getStringExtra("tags"));
    }

    private String getTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return year + "年" + month + "月" + day + "日" + hour + "时" + minute + "分";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.star_detail:
            case R.id.save_detatil:
                if (!saveFlag) {
                    saveFlag = true;
                    star_image.setImageResource(R.drawable.star_green);
                    save_text.setText(R.string.marked);
                    ContentValues values = new ContentValues();
                    values.put("bookmark", 1);
                    db.update("Book", values, "isbn13 = ?", new String[] {isbn.getText().toString()});

                    db.execSQL("insert into MarkedBook (isbn13, title, author, time) values(?, ?, ?, ?)",
                            new String[] {isbn.getText().toString(),
                                          title.getText().toString(),
                                          author.getText().toString(),
                                          getTime()});
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    saveFlag = false;
                    star_image.setImageResource(R.drawable.star_white);
                    save_text.setText(R.string.mark);
                    ContentValues values = new ContentValues();
                    values.put("bookmark", 0);
                    db.update("Book", values, "isbn13 = ?", new String[] {isbn.getText().toString()});

                    db.execSQL("delete from MarkedBook where isbn13 = ?", new String[] {isbn.getText().toString()});
                    Toast.makeText(this, "delete success", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.commit_comment:
                if (!TextUtils.isEmpty(comment.getText())) {
                    db.execSQL("insert into Comments (isbn13, title, comment) values(?, ?, ?)",
                            new String[] {isbn.getText().toString(),
                                          title.getText().toString(),
                                          comment.getText().toString()});
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Comment is null", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
