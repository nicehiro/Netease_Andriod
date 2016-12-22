package org.nicehiro.musicplayer;

/**
 * Created by hiro on 16-12-17.
 */

public class MusicInfo {
    private long id;

    private long album_id;

    private String title;

    private String artist;

    private long duration;

    private long size;

    private String url;

    private String album;

    private int isMusic;

    private boolean isFavorite = false;

    public long getId() {
        return id;
    }

    public long getAlbum_id() {
        return album_id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public long getDuration() {
        return duration;
    }

    public long getSize() {
        return size;
    }

    public String getUrl() {
        return url;
    }

    public String getAlbum() {
        return album;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public MusicInfo(long id, long album_id, String title,
                     String artist, long duration, long size, String url, String album, int isMusic) {
        this.id = id;
        this.album_id = album_id;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.size = size;
        this.url = url;
        this.album = album;
        this.isMusic = isMusic;
    }
}
