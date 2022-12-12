package ja.SPIDemo;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by totalyoung on 2018/11/24.
 */
public class TestSPI {
    public static void main(String[] args) {
        ServiceLoader<SPI> loads = ServiceLoader.load(SPI.class);
        Iterator<SPI> iterator = loads.iterator();
        while(iterator.hasNext()){
            SPI next = iterator.next();
            System.out.println(next.getClass().getSimpleName());
            next.doSpi();
        }
    }
}
