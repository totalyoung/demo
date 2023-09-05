package netty.gateway;

import java.util.HashSet;
import java.util.Set;

public class RouteServer implements Comparable<RouteServer>{

    private Route route;

//    private Map<String,Endpoint> endpointMap = new HashMap<>();

    private Set<Host> hosts = new HashSet<>();

    public RouteServer(Route route) {
        this.route = route;
    }

    @Override
    public int compareTo(RouteServer o) {
        return this.route.compareTo(o.route);
    }

    @Override
    public int hashCode() {
        return route.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return route.equals(obj);
    }


    public void addHost(String hostStr){
        hosts.add(new Host(hostStr));
    }

    @Override
    public String toString() {
        return "RouteServer{" +
                "route=" + route +
                ", hosts=" + hosts +
                '}';
    }
}
