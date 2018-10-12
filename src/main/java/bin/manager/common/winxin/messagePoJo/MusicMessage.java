package bin.manager.common.winxin.messagePoJo;

/**
 * Created by lenovo on 2018/10/12.
 */
public class MusicMessage extends Message {
    private Music Music;

    public MusicMessage() {
    }

    public MusicMessage(bin.manager.common.winxin.messagePoJo.Music music) {
        Music = music;
    }

    public MusicMessage(String toUserName, String fromUserName, String createTime, String msgType, bin.manager.common.winxin.messagePoJo.Music music) {
        super(toUserName, fromUserName, createTime, msgType);
        Music = music;
    }

    public bin.manager.common.winxin.messagePoJo.Music getMusic() {
        return Music;
    }

    public void setMusic(bin.manager.common.winxin.messagePoJo.Music music) {
        Music = music;
    }
}
