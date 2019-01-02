package ja.Concurrency.proxy;

public class BlackPerson implements Person {

	@Override
	public String getName() {
		System.out.println("BlackPerson");
		return null;
	}

	@Override
	public int getAge() {
		System.out.println("BlackPerson age 0");
		return 0;
	}

	@Override
	public void eat() {
		System.out.println("BlackPerson eat");
	}
}
