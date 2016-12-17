package org.nicehiro.musicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView musicList;

    private FindSong findSong;

    private List<MusicInfo> musicInfos;

    private MusicAdapter musicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicList = (ListView) findViewById(R.id.music_list);
        findViewById(R.id.btn_find).setOnClickListener(this);

        findSong = new FindSong();

        musicInfos = findSong.getMusicInfos(MainActivity.this.getContentResolver());

        musicAdapter = new MusicAdapter(this, musicInfos);
        musicList.setAdapter(musicAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_find:
                break;
        }
    }
}
