package ja;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;

/**
 * Created by yangst on 2018/6/13.
 */

public class reflectDemo {

//    private static Object instance;
//
//    public reflectDemo(String name) throws Exception{
//        Class<?> clazz = createClass(name);
//        synchronized (this){
//            if(instance == null){
//                instance = clazz.newInstance();
//            }
//        }
//    }

//    public static Class<?> createClass() throws ClassNotFoundException {
//        StreamDemo.Dis dis = new StreamDemo().new Dis("beef", false, 700);
//
//        Class<?> dis1 = dis.getClass();
//        System.out.println(dis1.getName() + "  "+ dis1.getCanonicalName() +"  "+dis1.getSimpleName()+ "  " +dis1.getTypeName());
//
//        Class<?> dis2 =  StreamDemo.Dis.class;
//
//        Class<?> dis3 = Class.forName("StreamDemo$Dis");
//
//        System.out.println(dis1==dis2&&dis2==dis3&&dis1==dis3);
//        return dis2;

//    }

    public static Class<?> createClass(String name) throws Exception{
        return Class.forName(name);
    }

    public static Object createInstance(String name)throws Exception{
        return createClass(name).newInstance();
    }

    public static void printMethods(Method[] declaredMethods) throws Exception {

//        Method[] declaredMethods = createClass("java.lang.String").getDeclaredMethods();
        for(Method method : declaredMethods){
//            System.out.println(method.getDeclaringClass().getSimpleName());
            StringBuilder sb = new StringBuilder();
            if(method.getDeclaredAnnotations().length>0){
                printAnnotation(method);
            }
            int modifiers = method.getModifiers();
            Class<?> returnType = method.getReturnType();
            sb.append(Modifier.toString(modifiers)).append(" ").append(returnType.getSimpleName()).append(" ")
                    .append(method.getName()).append(" ").append("( ");
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class parameterType: parameterTypes){
                sb.append(parameterType.getSimpleName()).append(" ");
            }
            sb.append(")");
            Class<?>[] exceptionTypes = method.getExceptionTypes();
            if(exceptionTypes.length>0){
                sb.append(" throws ");
            }
            for(Class exceptionType: exceptionTypes){
                sb.append(exceptionType.getSimpleName()).append(" ");
            }
            System.out.println(sb);

        }
    }

    public static void printConstructor() throws Exception{
        Constructor<?>[] declaredConstructors = createClass("java.lang.String").getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            StringBuilder sb = new StringBuilder();
            sb.append(Modifier.toString(declaredConstructor.getModifiers())).append(" ").append(declaredConstructor.getName()).append(" ").append("( ");
            Class<?>[] parameterTypes = declaredConstructor.getParameterTypes();
            for (Class parameterType: parameterTypes){
                sb.append(parameterType.getSimpleName()).append(" ");
            }
            sb.append(")");
            Class<?>[] exceptionTypes = declaredConstructor.getExceptionTypes();
            if(exceptionTypes.length>0){
                sb.append(" throws ");
            }
            for(Class exceptionType: exceptionTypes){
                sb.append(exceptionType.getSimpleName()).append(" ");
            }
            System.out.println(sb);
        }
    }

    public static void printField() throws Exception {
        Object instance = createInstance("java.lang.String");
        Field[] declaredFields = createClass("java.lang.String").getDeclaredFields();
        for(Field field : declaredFields){
            if(field.getDeclaredAnnotations().length>0){
                printAnnotation(field);
            }
            field.setAccessible(true);
            int modifiers = field.getModifiers();
            String name = field.getName();
            Class<?> type = field.getType();
            Object o = field.get(instance);
            System.out.println(Modifier.toString(modifiers)+" "+type.getSimpleName()+" "+name + " = " +o.toString());
        }

    }

    public static void printAnnotation(AccessibleObject ao){
        Annotation[] annotations = ao.getDeclaredAnnotations();
        for (Annotation annotation:annotations) {
            System.out.println("@"+annotation.annotationType().getSimpleName());
        }

    }

    //利用反射写泛型数组
    public static Object goodCopyOf(Object a,int newLength){
        Class cl = a.getClass();
        if(!cl.isArray()) return null;
        Class componentType = cl.getComponentType();
        int length = Array.getLength(a);
        Object newArray = Array.newInstance(componentType,newLength);
        System.arraycopy(a,0,newArray,0,Math.min(length,newLength));
        return newArray;
    }

    public static Object[] badCopyOf(Object[] a, int newLength){
        Object[] newArray = new Object[newLength];
        System.arraycopy(a,0,newArray,0,Math.min(a.length,newLength));
        return newArray;
    }

    public static void testArrayCopy(){
        int[] a = {1,2,3};
        a=(int[])goodCopyOf(a,10);
        System.out.println(Arrays.toString(a));

        String[] b = {"aa","bb","cc"};
        b =(String[])goodCopyOf(b,10);
        System.out.println(Arrays.toString(b));

        b=(String[])badCopyOf(b,10);

    }
    public static void main(String[] args) throws Exception {
//        printField();
//        printMethods();
//        printConstructor();
//        testArrayCopy();
//        createClass("java.lang.String").getEnumConstants();
        Integer[] is = new Integer[2];
//       ;
        Method[] declaredMethods = is.getClass().getMethods();
        Method declaredMethod = is.getClass().getDeclaredMethod("", is.getClass());
        Type genericReturnType = declaredMethod.getGenericReturnType();
        printMethods(is.getClass().getDeclaredMethods());
    }
}
