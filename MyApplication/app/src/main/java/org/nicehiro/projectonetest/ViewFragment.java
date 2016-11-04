package org.nicehiro.projectonetest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by root on 16-11-4.
 */

public class ViewFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private int position;

    public static ViewFragment newInstance(int position) {
        ViewFragment viewFragment = new ViewFragment();
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

        final List<Message> data = new ArrayList<>(100);

        for (int i=0; i<100; i++) {
            String time = "" + Calendar.getInstance().get(Calendar.HOUR) + ":" + Calendar.getInstance().get(Calendar.MINUTE);

            data.add(new Message(
                    R.drawable.icon_contact,
                    "Android Develop",
                    "I'm a android developer",
                    time
            ));
        }

        final BaseAdapter adapter = new MessageAdapter(data, this.getContext());
        listView.setAdapter(adapter);

        View header = LayoutInflater.from(this.getContext()).inflate(R.layout.header_view, listView, false);
        listView.addHeaderView(header);
    }
}
