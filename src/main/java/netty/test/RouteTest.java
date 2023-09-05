package netty.test;

import netty.gateway.Route;
import netty.common.RouteUtil;
import org.junit.Test;

import java.net.URLEncoder;

public class RouteTest {

    @Test
    public void fromJson(){
        String str = "{\"id\":1,\"order\":-2147483648,\"prefix\":\"/ettt\"}";
        Route route = RouteUtil.fromJson(str);
        System.out.println(URLEncoder.encode(route.toString()));;
//        Route route1 = new Gson().fromJson(str, Route.class);
//        System.out.println();
    }
}
