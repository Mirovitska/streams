import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Task1 {

    static class First {

        private static final Comparator<Person> byName = (Person o1, Person o2) -> {
            return o1.getName().compareTo(o2.getName());
        };


        private static final Comparator<Person> byAge = (Person o1, Person o2) -> {
            return Integer.compare(o1.getAge(), o2.getAge());
        };

        public static void main(String[] args) {

            System.out.printf("%nTask1.1%n");

            Person[] p = new Person[]{
                    new Person("Bill", 30),
                    new Person("John", 43),
                    new Person("Fred", 23),

            };

            System.out.println("Original:");
            Arrays.asList(p).forEach(System.out::println);

            Arrays.sort(p, byName);
            System.out.printf("%nSorted by name: %s", Arrays.toString(p));
            Arrays.sort(p, byAge);
            System.out.printf("%nSorted by age: %s", Arrays.toString(p));

        }

    }


    static class Second {

        public static void main(String[] args) {

            System.out.printf("%nTask1.2%n");

            // Supplier
            Random random = new Random();
            Supplier<Double> randomValue = random::nextDouble;

            double valueSquared = powValue(randomValue);

            System.out.println("valueSquared = " + valueSquared);

            // Consumer
            List<String> names = Arrays.asList("Mykola", "Petro", "Vasyl", "Nikola", "David");
            names.forEach(name -> System.out.println("Hello, " + name));


            // Predicate
            List<String> namesWithI = names.stream()
                    .filter(name -> name.contains("i"))
                    .collect(Collectors.toList());

            System.out.printf("%nNames with i: %s", namesWithI);


            // Operator
            names.replaceAll(String::toUpperCase);
            System.out.printf("%nUppercase names: %s%n", names);

            BinaryOperator<String> binaryOperator =
                    (value1, value2) -> {
                        return value1.concat(value2);
                    };

            System.out.println("binaryOperator.apply(\"Hello\", \" teacher!\") = " + binaryOperator.apply("Hello", " teacher!"));

        }

        private static double powValue(Supplier<Double> randomValue) {
            return Math.pow(randomValue.get(), 2);
        }

    }

    static class ThirdFourthFifth {

        @FunctionalInterface
        public interface MyFunctionalInterface {
            void execute();

            default void print(String text) {
                System.out.println("MyFunctionalInterface " + text);
            }

            static void print(String text, PrintWriter writer) {
                writer.write("MyFunctionalInterface " + text);
            }
        }

        private static final MyFunctionalInterface myLambdaHello = () -> {
            System.out.println("MyFunctionalInterface Lambda Hello!");
        };

        private static final MyFunctionalInterface myAnonymousHello = new MyFunctionalInterface() {
            @Override
            public void execute() {
                System.out.println("MyFunctionalInterface Anonymous Hello!");
            }
        };

        public static void main(String[] args) {

            System.out.printf("%nTask1.345%n");

            myLambdaHello.execute();
            myAnonymousHello.execute();

            myLambdaHello.print("Using default method.");

            PrintWriter writer = new PrintWriter(System.out);
            MyFunctionalInterface.print("Using static method.", writer);
            writer.flush();

        }
    }

}
