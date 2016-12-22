package org.nicehiro.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

public class PlayerService extends Service {

    private static final String TAG = "PlayService";

    public static MediaPlayer mediaPlayer = new MediaPlayer();

    public static int currentMusicPosition;

    private Intent intentToProgressBar = new Intent("org.nicehiro.intentToProgressBar");

    private void sendBroadcastToProgressBar() {
        currentMusicPosition = mediaPlayer.getCurrentPosition();
        intentToProgressBar.putExtra("current_music_position", currentMusicPosition);
        sendBroadcast(intentToProgressBar);
    }

    public PlayerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int msg = intent.getIntExtra("MSG", 0);

        if (MusicConstant.MusicPlayer.START_PLAY == msg) {
            String musicPath = intent.getStringExtra("url");
            currentMusicPosition = intent.getIntExtra("current_music_position", 0);
            playMusic(musicPath, currentMusicPosition);
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    sendBroadcastToProgressBar();
                    Log.d(TAG, "sending...");
                }
            }
        });
        thread.start();

        return super.onStartCommand(intent, flags, startId);
    }

    private void playMusic(String path, final int position) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    if (position > 0) {
                        mediaPlayer.seekTo(position);
                    }
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void pauseMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            currentMusicPosition = mediaPlayer.getCurrentPosition();
        }
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
