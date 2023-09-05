package netty.gateway;

import java.util.Objects;

public class Host {

    protected String host;

    protected int port;

    public Host(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Host(String hostStr){
        String[] split = hostStr.split(":");
        this.host=split[0];
        this.port=Integer.valueOf(split[1]);
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getHostStr(){
        return host+":"+port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Host host1 = (Host) o;
        return port == host1.port &&
                Objects.equals(host, host1.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port);
    }
}
