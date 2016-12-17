package org.nicehiro.musicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hiro on 16-12-17.
 */

public class MusicAdapter extends BaseAdapter {

    private Context context;
    private List<MusicInfo> musicInfos;

    public MusicAdapter(Context context, List<MusicInfo> musicInfos) {
        this.context = context;
        this.musicInfos = musicInfos;
    }

    @Override
    public int getCount() {
        return musicInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate
                    (R.layout.item_music_list, null);
            viewHolder.musicArtist = (TextView) convertView.findViewById(R.id.music_artist);
            viewHolder.musicDuration = (TextView) convertView.findViewById(R.id.music_duration);
            viewHolder.musicTitle = (TextView) convertView.findViewById(R.id.music_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MusicInfo music = musicInfos.get(position);

        viewHolder.musicTitle.setText(music.getTitle());
        viewHolder.musicDuration.setText(String.valueOf(formatTime(music.getDuration())));
        viewHolder.musicArtist.setText(music.getArtist());

        return convertView;
    }

    private class ViewHolder {
        private TextView musicTitle;
        private TextView musicArtist;
        private TextView musicDuration;
    }

    private static String formatTime(Long time) {
        String min = time / (1000 * 60) + "";
        String sec = time % (1000 * 60) + "";

        if (min.length() < 2) {
            min = "0" + min;
        }

        switch (sec.length()) {
            case 4:
                sec = "0" + sec;
                break;
            case 3:
                sec = "00" + sec;
            case 2:
                sec = "000" + sec;
                break;
            case 1:
                sec = "0000" + sec;
                break;
        }

        return min + ":" + sec.trim().substring(0, 2);
    }
}
