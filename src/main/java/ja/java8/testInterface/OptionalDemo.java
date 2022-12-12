package ja.java8.testInterface;

import ja.java8.Person;

import java.util.Optional;

/**
 * Created by yangst on 2018/7/26.
 */

public class OptionalDemo {


    public static String getCarInsuranceName(Person person){
        Optional<Person> optPerson = Optional.ofNullable(person);
//        Optional<String> name = optPerson.map(Person::getCar).map(Car::getInsurance).map(Insurance::getName);
        return "";
    }

    public static void main(String[] args) {
        getCarInsuranceName(null);
    }
}
