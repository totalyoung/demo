package netty.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import netty.gateway.Route;
import netty.gateway.RouteJsonDeserializer;

public class RouteUtil {

    public static Route fromJson(String json){
        Gson gson = new GsonBuilder().registerTypeAdapter(Route.class, new RouteJsonDeserializer()).create();
        return gson.fromJson(json, Route.class);
    }
}
