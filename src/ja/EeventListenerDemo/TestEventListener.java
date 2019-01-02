package ja.EeventListenerDemo;

/**
 * Created by totalyoung on 2018/11/7.
 */
public class TestEventListener {


    public static void main(String[] args) {
        EventManager eventManager = new EventManager();
        eventManager.addListener(new ListenerDemo("one"));
        eventManager.addListener(new ListenerDemo("two"));
        eventManager.publishEvent(new EventObjectDemo("eventObject"));
    }
}
