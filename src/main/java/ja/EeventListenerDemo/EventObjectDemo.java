package ja.EeventListenerDemo;

import java.util.EventObject;

/**
 * Created by totalyoung on 2018/11/6.
 */
public class EventObjectDemo extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public EventObjectDemo(Object source) {
        super(source);
        testEvent();
    }

    public Object testEvent(){
        return source;
    }
}
