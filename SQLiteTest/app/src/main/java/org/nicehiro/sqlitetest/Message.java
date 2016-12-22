package org.nicehiro.sqlitetest;

/**
 * Created by root on 16-11-11.
 */

public class Message {

    private long id;
    private String fromName;
    private String toName;
    private long time;
    private String content;
    private int state;

    public interface State {
        int SUCCESS = 0;
        int FAIL = 1;
    }

    public Message(long id, String fromName, String toName, long time, String content, int state) {
        this.id = id;
        this.fromName = fromName;
        this.toName = toName;
        this.time = time;
        this.content = content;
        this.state = state;
    }

    @Override
    public String toString() {
        return getId()
                + " " + getFromName()
                + " " + getToName()
                + " " + getTime()
                + " " + getContent()
                + " " + getState();
    }

    public long getId() {
        return id;
    }

    public String getFromName() {
        return fromName;
    }

    public String getToName() {
        return toName;
    }

    public long getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public int getState() {
        return state;
    }
}
