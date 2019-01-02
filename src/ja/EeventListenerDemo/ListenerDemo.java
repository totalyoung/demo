package ja.EeventListenerDemo;

import javax.swing.event.EventListenerList;
import java.util.EventListener;

/**
 * Created by totalyoung on 2018/11/6.
 */
public class ListenerDemo implements EventListener {

    private String name;

    public ListenerDemo(String name) {
        this.name = name;
    }

    public void handEvent(EventObjectDemo eventObjectDemo){
        eventObjectDemo.testEvent();
        System.out.println(name +" listener has start!");
//        System.out.println("handEvent!!!!!!");
    }
}
