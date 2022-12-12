package ja.SPIDemo;

/**
 * Created by totalyoung on 2018/11/25.
 */
public class TwoSPI implements SPI {
    @Override
    public void doSpi() {
        System.out.println(this.getClass().getSimpleName());
    }
}
