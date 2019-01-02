package ja.EeventListenerDemo;

import java.util.*;

/**
 * Created by totalyoung on 2018/11/7.
 */
public class EventListenerTest {

    public static void main(String[] args) {
        GirlManager girlManager = new GirlManager();
        girlManager.addGirlListener(new GirlListener1());// 给门1增加监听器
        girlManager.addGirlListener(new GirlListener2());// 给门2增加监听器
        girlManager.fireWorkspaceBeauty();
//        girlManager.fireWorkspaceClosed();
    }
}

class GirlListener1 implements GirlListener{

    @Override
    public void girlIsBeautyEvent(GirlEvent event) {
        // TODO Auto-generated method stub
        if (event.getGirlState() != null && event.getGirlState().equals("beautiful")) {
            System.out.println("这个女孩很漂亮");
        } else {
            System.out.println("这个女孩很个性");
        }
    }

}

class GirlListener2 implements GirlListener {

    @Override
    public void girlIsBeautyEvent(GirlEvent event) {
        // TODO Auto-generated method stub
        if (event.getGirlState() != null && event.getGirlState().equals("temperament")) {
            System.out.println("这个女孩很漂亮并且很个性");
        } else {
            System.out.println("这个女孩很有气质");
        }
    }

}
interface GirlListener  extends EventListener {
    public void girlIsBeautyEvent(GirlEvent event);
}
class GirlEvent extends EventObject {

    private static final long serialVersionUID = 6496098798146410884L;

    private String girlState = "";// 表示门的状态，有“开”和“关”两种

    public GirlEvent(Object source, String doorState) {
        super(source);
        this.girlState = doorState;
    }

    public void setGirlState(String doorState) {
        this.girlState = doorState;
    }

    public String getGirlState() {
        return this.girlState;
    }

}

class GirlManager {
    private Collection listeners;
    /**  添加事件  */
    public void addGirlListener(GirlListener listener) {
        if (listeners == null) {
            listeners = new HashSet();
        }
        listeners.add(listener);
    }

    /** 移除事件  */
    public void removeGirlListener(GirlListener listener) {
        if (listeners == null)
            return;
        listeners.remove(listener);
    }
    /** 触发漂亮事件 */
    protected void fireWorkspaceBeauty() {
        if (listeners == null)
            return;
        GirlEvent event = new GirlEvent(this, "beautiful");
        notifyListeners(event);
    }

    /**  触发丑事件 */
    protected void fireWorkspaceClosed() {
        if (listeners == null)
            return;
        GirlEvent event = new GirlEvent(this, "temperament");
        notifyListeners(event);
    }

    /**  通知所有的DoorListener */
    private void notifyListeners(GirlEvent event) {
        Iterator iter = listeners.iterator();
        while (iter.hasNext()) {
            GirlListener listener = (GirlListener) iter.next();
            listener.girlIsBeautyEvent(event);

        }
    }
}
