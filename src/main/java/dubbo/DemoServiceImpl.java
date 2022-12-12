package dubbo;

public class DemoServiceImpl implements DemoService {
    @Override
    public Demo getDomo() {
        return new Demo();
    }
}
