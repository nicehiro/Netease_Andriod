package org.nicehiro.projectonetest;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by root on 16-11-5.
 */

public class RecycleViewFragment extends Fragment {


    private static final String ARG_POSITION = "position";
    private int position;

    private MessageForRecycleViewAdapter adapter;

    public static RecycleViewFragment newInstance(int position) {
        RecycleViewFragment viewFragment = new RecycleViewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITION, position);
        viewFragment.setArguments(bundle);
        return viewFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getArguments().getInt(ARG_POSITION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycleview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        final List<Message> messageList = new ArrayList<>(20);

        String time = "" + Calendar.getInstance().get(Calendar.HOUR) + ":" + Calendar.getInstance().get(Calendar.MINUTE);
        for (int i = 0; i < 20; i++) {

            if (i % 2 == 0) {
                messageList.add(new Message(
                        R.drawable.icon_contact,
                        "Android Develop1",
                        "I'm a android developer",
                        time
                ));
            } else {
                messageList.add(new Message(
                        R.drawable.android_icon,
                        "Android Develop2",
                        "I'm also a android developer",
                        time
                ));
            }
        }

        adapter = new MessageForRecycleViewAdapter(messageList);
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerViewOnClickListener(this.getContext(), recyclerView, new RecyclerViewOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0 || position == messageList.size()+1) {
                    return;
                } else {
                    Message message = messageList.get(position);
                    String title = message.getTitle();
                    String subTitle = message.getSubTitle();

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(recyclerView.getContext());
                    builder.setContentTitle(title)
                            .setContentText(subTitle)
                            .setSmallIcon(R.drawable.ic_status_bar_lollipop)
                            .setContentIntent(demoActivityIntent(recyclerView.getContext(), message));
                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(recyclerView.getContext());
                    notificationManagerCompat.notify(1, builder.build());
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    private PendingIntent demoActivityIntent(Context context, Message message) {
        Intent intent = new Intent(context, DemoActivity.class);
        String[] demoMessage = {message.getTitle(), message.getSubTitle()};
        intent.putExtra("message", demoMessage);
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
