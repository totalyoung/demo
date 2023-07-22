package dubbo;

public class DemoServiceImpl implements DemoService {
    @Override
    public Demo getDomo(int id) {
        return new Demo(id);
    }

    @Override
    public Demo getTest(int id) {
        return new Demo(id);
    }
}
