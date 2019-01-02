package ja.Concurrency.ThreadLocalDemo;

import ja.Concurrency.proxy.YellowPerson;
import ja.Concurrency.ThreadLocalDemo.Entity;

public class CloseThread implements Runnable {

	private static int index = 0;
	private static Entity entity = new Entity("123","yangst");
	private static ThreadLocal<Entity> entityHolder = new ThreadLocal<Entity>() {
		@Override
		// 首次调用get方法，就会调用initialValue来获得初始值
		protected Entity initialValue() {
			super.initialValue();
			return entity;
		}
	};

	private static YellowPerson person = new YellowPerson();
	private static ThreadLocal<YellowPerson> personHolder = new ThreadLocal<YellowPerson>() {
		@Override
		// 首次调用get方法，就会调用initialValue来获得初始值
		protected YellowPerson initialValue() {
			super.initialValue();
			return person;
		}
	};

	public Entity getEntity() {
		return entityHolder.get();
	}

	public YellowPerson getPerson() {
		return personHolder.get();
	}


	@Override
	public void run() {
		Entity entity = getEntity();
		entity.setName(Thread.currentThread().getName());
		for (int i = 0; i < 5; i++) {
			System.out.println(entity);
//			System.out.println("index : " + index++);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new CloseThread());
		Thread thread2 = new Thread(new CloseThread());
		thread.start();
		Thread.sleep(1000);
		thread2.start();
	}

}
