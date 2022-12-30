package dubbo;

public class DemoServiceImpl implements DemoService {
    @Override
    public Demo getDomo(int id) {
        return new Demo(id);
    }
}
