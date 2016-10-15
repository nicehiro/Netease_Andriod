package org.nicehiro.recycleviewtest;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Message> messageList = new ArrayList<>();
        String time = Calendar.getInstance().getTime().toLocaleString();

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                TextMessage textMessage = new TextMessage(
                        R.drawable.icon_contact,
                        time,
                        "I'm the text message" + i
                );
                messageList.add(textMessage);
            } else {
                ImageMessage imageMessage = new ImageMessage(
                        R.drawable.icon_contact,
                        time,
                        R.drawable.image
                );
                messageList.add(imageMessage);
            }
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        MessageAdapter messageAdapter = new MessageAdapter(messageList);

        recyclerView.setAdapter(messageAdapter);

        messageAdapter.setOnItemClickListern(new MessageAdapter.OnItemClickListern() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, int position) {
                Log.i("MainActivity", "onItemClick position: " + position);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            private Drawable divider = getResources().getDrawable(R.drawable.decoration_drawable);
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);

                final int childCount = parent.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View child = parent.getChildAt(i);
                    int top = child.getBottom();
                    int left = child.getLeft();
                    final int bottom = top + 10;
                    int right = child.getRight();

                    divider.setBounds(left, top, right, bottom);
                    divider.draw(c);
                }
            }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);

//                final int childCount = parent.getChildCount();
//                for (int i = 0; i < childCount; i++) {
//                    View child = parent.getChildAt(i);
//                    int top = child.getBottom();
//                    final int bottom = top + 10;
//                    int left = child.getLeft();
//                    int right = child.getRight();
//
//                    divider.setBounds(left, top, right, bottom);
//                    divider.draw(c);
//                }
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom += 10;
            }
        });
    }
}
