package netty.gateway.register;

public interface Client<T> extends Subscriber,Register{

    void connect();

    void init();

    T getClient();
}
