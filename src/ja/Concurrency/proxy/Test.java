package ja.Concurrency.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException {
		//打开生成代理类的开关
		System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		Person p = new YellowPerson();
		Animal a = new Dog();
//		Method getName = p.getClass().getDeclaredMethod("getName");
//		Method getMethodAccessor = getName.getClass().getDeclaredMethod("getMethodAccessor");
//		System.out.println(getMethodAccessor);
		PersonInvocationHandler pi = new PersonInvocationHandler(p,a);
		List<Class<?>> list = new ArrayList<>();
		list.addAll(Arrays.asList( a.getClass().getInterfaces()));
		list.addAll(Arrays.asList( p.getClass().getInterfaces()));

		Class<?>[] classes = list.toArray(new Class<?>[list.size()]);
		Person newProxyInstance = (Person) Proxy.newProxyInstance(p.getClass().getClassLoader(),
				classes, pi);

//		Object newProxyInstance = Proxy.newProxyInstance(p.getClass().getClassLoader(),
//				classes, pi);
//		Object o = Class.forName(newProxyInstance.getClass().getTypeName()).newInstance();
//		String componentType = newProxyInstance.getClass().getTypeName();
//		System.out.println(a.getClass().getTypeName());
//		System.out.println(a.getClass().getName());
//		System.out.println(a.getClass().getSimpleName());
//		System.out.println(a.getClass().getCanonicalName());
//		System.out.println(a.getClass().getSimpleName());
		newProxyInstance.getAge();


//		Animal animalProxy = (Animal) Proxy.newProxyInstance(p.getClass().getClassLoader(),
//				p.getClass().getInterfaces(), pi);
//		animalProxy.eat();
//		System.out.println("$Proxy0.class全名: "+Proxy.getProxyClass(Person.class.getClassLoader(), Person.class));
		// pi = new PersonInvocationHandler(new BlackPerson());
		// newProxyInstance.getName();
		// newProxyInstance.test();

	}
}
