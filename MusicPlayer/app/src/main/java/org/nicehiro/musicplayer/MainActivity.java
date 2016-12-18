package org.nicehiro.musicplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MUSICPLAYER";

    private ListView musicList;

    private List<MusicInfo> musicInfos;

    private ImageView musicAlbum;

    private TextView musicTitle;

    private TextView musicDuration;

    private TextView musicArtist;

    private ImageView musicPlayImg;

    private ImageView musicNextImg;

    private ProgressBar musicProgress;

    private int currentMusicPosition;

    private int currentPosition;

    private boolean isPlaying = false;

    private MediaPlayer mediaPlayer;

    private BarReceiver barReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicList = (ListView) findViewById(R.id.music_list);

        FindSong findSong = new FindSong();

        musicInfos = findSong.getMusicInfos(MainActivity.this.getContentResolver());

        initButton();

        initListView();

        mediaPlayer = PlayerService.mediaPlayer;

        IntentFilter intentFilter = new IntentFilter("org.nicehiro.intentToProgressBar");
        barReceiver = new BarReceiver();
        registerReceiver(barReceiver, intentFilter);
    }

    private void initButton() {
        musicTitle = (TextView) findViewById(R.id.music_title);
        musicArtist = (TextView) findViewById(R.id.music_artist);
        musicAlbum = (ImageView) findViewById(R.id.music_album);
        musicDuration = (TextView) findViewById(R.id.music_duration);

        musicProgress = (ProgressBar) findViewById(R.id.music_progreess_bar);

        musicPlayImg = (ImageView) findViewById(R.id.img_music_play);
        musicPlayImg.setOnClickListener(this);

        musicNextImg = (ImageView) findViewById(R.id.img_music_next);
        musicNextImg.setOnClickListener(this);
    }

    private void initListView() {
        MusicAdapter musicAdapter = new MusicAdapter(this, musicInfos);
        musicList.setAdapter(musicAdapter);

        musicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (musicInfos != null) {
                    if (musicInfos.get(position) != null) {
                        currentPosition = position;
                        playMusicService(position);
                        changeUI(position);
                        isPlaying = true;
                        Log.d(TAG, musicInfos.get(position).getTitle() + "开始播放");
                    }
                }
            }
        });
    }

    private void changeUI(int position) {
        MusicInfo musicInfo = musicInfos.get(position);
        String title = musicInfo.getTitle();
        String artist = musicInfo.getArtist();
        String duration = String.valueOf(MusicAdapter.formatTime(musicInfo.getDuration()));
        long albumId = musicInfo.getAlbum_id();
        long id = musicInfo.getId();

        musicTitle.setText(title);
        musicArtist.setText(artist);
        musicDuration.setText(duration);
        Bitmap bitmap = MediaUtil.getArtwork(this, id, albumId, true, true);
        musicAlbum.setImageBitmap(bitmap);

        musicPlayImg.setImageResource(R.drawable.pause);
    }

    private void playMusicService(int position) {
        MusicInfo musicInfo = musicInfos.get(position);
        Intent intent = new Intent(this, PlayerService.class);
        intent.putExtra("url", musicInfo.getUrl());
        intent.putExtra("title", musicInfo.getTitle());
        intent.putExtra("artist", musicInfo.getArtist());
        intent.putExtra("album", musicInfo.getAlbum());
        intent.putExtra("album_id", musicInfo.getAlbum_id());
        intent.putExtra("MSG", MusicConstant.MusicPlayer.START_PLAY);
        intent.putExtra("current_music_position", currentMusicPosition);

        musicProgress.setMax((int) musicInfo.getDuration());

        startService(intent);
    }

    private class BarReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            currentMusicPosition = intent.getIntExtra("current_music_position", 0);
            Log.d(TAG, currentMusicPosition + "");
            musicProgress.setProgress(currentMusicPosition);
        }
    }

    private void onBtnPlayClicked() {
        if (!isPlaying) {
            musicPlayImg.setImageResource(R.drawable.pause);
            playMusicService(currentPosition);
            isPlaying = true;
        } else {
            musicPlayImg.setImageResource(R.drawable.play);
            PlayerService.pauseMusic();
            currentMusicPosition = PlayerService.currentMusicPosition;
            isPlaying = false;
        }
    }

    private void onBtnNextClicked() {
        currentPosition ++;
        currentMusicPosition = 0;
        playMusicService(currentPosition);
        isPlaying = true;
        musicPlayImg.setImageResource(R.drawable.pause);
        changeUI(currentPosition);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_music_play:
                onBtnPlayClicked();
                break;
            case R.id.img_music_next:
                onBtnNextClicked();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(barReceiver);
    }
}
