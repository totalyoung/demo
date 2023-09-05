package netty.gateway.register;

import netty.gateway.listener.Listener;

public interface Subscriber {

    void subscribe(Listener listener);
}
