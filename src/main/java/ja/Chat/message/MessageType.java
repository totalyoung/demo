package ja.Chat.message;

public enum  MessageType {
    Login(1,"登录"),
    Quit(2,""),
    Chat(3,""),
    ;

    private int type;
    private String desc;

    MessageType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
