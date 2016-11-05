package org.nicehiro.projectonetest;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
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
 * Created by root on 16-11-4.
 */

public class ListViewFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private int position;

    public static ListViewFragment newInstance(int position) {
        ListViewFragment viewFragment = new ListViewFragment();
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
        return inflater.inflate(R.layout.fragment_listview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listView = (ListView) view.findViewById(R.id.list_view);

        final List<Message> data = new ArrayList<>(20);

        String time = "" + Calendar.getInstance().get(Calendar.HOUR) + ":" + Calendar.getInstance().get(Calendar.MINUTE);
        for (int i=0; i<20; i++) {

            if (i % 2 == 0) {
                data.add(new Message(
                        R.drawable.icon_contact,
                        "Android Develop1",
                        "I'm a android developer",
                        time
                ));
            } else {
                data.add(new Message(
                        R.drawable.android_icon,
                        "Android Develop2",
                        "I'm also a android developer",
                        time
                ));
            }
        }

        final BaseAdapter adapter = new MessageAdapter(data, this.getContext());
        listView.setAdapter(adapter);

        final View header = LayoutInflater.from(this.getContext()).inflate(R.layout.header_view, listView, false);
        listView.addHeaderView(header);

        final TextView one = (TextView) header.findViewById(R.id.one);
        final TextView two = (TextView) header.findViewById(R.id.two);
        final TextView three = (TextView) header.findViewById(R.id.three);
        final TextView four = (TextView) header.findViewById(R.id.four);

        header.findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paticalRefresh(header, one, two, three, four);
            }
        });

        View footer = LayoutInflater.from(this.getContext()).inflate(R.layout.footer, listView, false);
        listView.addFooterView(footer);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Message message = (Message) parent.getItemAtPosition(position);
                String title = message.getTitle();
                String subTitle = message.getSubTitle();

                NotificationCompat.Builder builder = new NotificationCompat.Builder(parent.getContext());
                builder.setContentTitle(title)
                        .setContentText(subTitle)
                .setSmallIcon(R.drawable.ic_status_bar_lollipop)
                .setContentIntent(demoActivityIntent(parent.getContext(), message));
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(parent.getContext());
                notificationManagerCompat.notify(1, builder.build());
            }
        });
    }

    private void paticalRefresh(View header, TextView one, TextView two, TextView three, TextView four) {
        String temp = (String) four.getText();
        four.setText(three.getText());
        three.setText(two.getText());
        two.setText(one.getText());
        one.setText(temp);

        header.refreshDrawableState();
    }

    private PendingIntent demoActivityIntent(Context context, Message message) {
        Intent intent = new Intent(context, DemoActivity.class);
        String[] demoMessage = {message.getTitle(), message.getSubTitle()};
        intent.putExtra("message", demoMessage);
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
