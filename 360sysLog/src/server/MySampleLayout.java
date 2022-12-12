package server;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.LayoutBase;

/**
 * Created by admin on 2019/6/30.
 */
public class MySampleLayout extends LayoutBase<ILoggingEvent> {

    public String doLayout(ILoggingEvent event) {
        StringBuffer sbuf = new StringBuffer(1024);
        sbuf.append(event.getFormattedMessage());
        return sbuf.toString();
    }
}
