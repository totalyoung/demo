package ja.Concurrency.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class PersonInvocationHandler implements InvocationHandler {

	private Person person;
	private Animal animal;
	private Map<String,Object> map = new HashMap<>();


	public PersonInvocationHandler(Person person,Animal animal) {
		super();
		this.person = person;
		this.animal = animal;
		map.put(person.getClass().getInterfaces()[0].getTypeName(),person);
		map.put(animal.getClass().getInterfaces()[0].getTypeName(),animal);
	}

	@Override
	/**
	 *
	 */
	public Object invoke(Object arg0, Method method, Object[] arg2) throws Throwable {
		System.out.println("hahah");
        String typeName = method.getDeclaringClass().getTypeName();
        Object o = map.get(typeName);
		System.out.println(typeName);
//        for (int i = 0; i < 20; i++) {
//            method.invoke(o, arg2);
//        }
		return method.invoke(o, arg2);

	}

}
