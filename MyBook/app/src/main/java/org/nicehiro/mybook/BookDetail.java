package org.nicehiro.mybook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

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

    private Toolbar toolbar;
    private  ImageView star_image;
    private TextView save_text;

    private boolean saveFlag = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        initView();

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
        summary.setText(intent.getStringExtra("summary"));
        pages.setText(intent.getStringExtra("pages"));
        tag.setText(intent.getStringExtra("tags"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.star_detail:
            case R.id.save_detatil:
                if (!saveFlag) {
                    saveFlag = true;
                    star_image.setImageResource(R.drawable.star_green);
                } else {
                    saveFlag = false;
                    star_image.setImageResource(R.drawable.star_white);
                }
                break;
        }
    }
}
