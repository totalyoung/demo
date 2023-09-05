package netty.gateway;

import java.io.Serializable;

public interface Route extends Comparable<Route>, Serializable {

    boolean match(Object object);

    int getOrder();

    String replace(String path);

    String nodePath();


}
