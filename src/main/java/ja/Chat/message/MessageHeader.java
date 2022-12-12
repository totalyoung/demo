package ja.Chat.message;

/**
 * Created by admin on 2020/4/14.
 */
public class MessageHeader {

    public static final int size=200;

    private byte type;

    private long srcId;

    private long toId;

    private long time;

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public long getSrcId() {
        return srcId;
    }

    public void setSrcId(long srcId) {
        this.srcId = srcId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getToId() {
        return toId;
    }

    public void setToId(long toId) {
        this.toId = toId;
    }
}
