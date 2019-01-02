package ja.EeventListenerDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by totalyoung on 2018/11/7.
 */
public class EventManager {

    private List<ListenerDemo> listenerDemos;

    public EventManager( ) {
        this.listenerDemos = new ArrayList<>();
    }

    public void publishEvent(EventObjectDemo eventObjectDemo){
        for (ListenerDemo listenerDemo : listenerDemos) {
            listenerDemo.handEvent(eventObjectDemo);
        }
    }

    public void addListener(ListenerDemo listenerDemo){
        listenerDemos.add(listenerDemo);
    }
}
