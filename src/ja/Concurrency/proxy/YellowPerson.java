package ja.Concurrency.proxy;

public class YellowPerson implements Person{

	@Override
	public String getName() {
		System.out.println("YellowPerson");
		return null;
	}

	@Override
	public int getAge() {
		System.out.println("YellowPerson age 9");
		return 0;
	}

	@Override
	public void eat() {
		System.out.println("YellowPerson eat");
	}

	public void test() {
		System.out.println("test");
	}


}
