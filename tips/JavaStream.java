package tips;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaStream {
  class Person {
    String name;
    int age;

    Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

    @Override
    public String toString() {
      return name;
    }
  }

  List<Person> persons =
      Arrays.asList(
          new Person("Max", 18),
          new Person("Peter", 23),
          new Person("Pamela", 23),
          new Person("David", 12));

  class Bar {
    String name;

    Bar(String name) {
      this.name = name;
    }
  }

  class Foo {
    String name;
    List<Bar> bars = new ArrayList<>();

    Foo(String name) {
      this.name = name;
    }
  }

  /**
   * First example
   */
  private void example1() {
    System.out.println("===Example 1===");

    List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
    myList.stream()
        .filter(s -> s.startsWith("c"))
        .map(s -> s.toUpperCase())
        .sorted()
        .forEach(System.out::println);
  }

  /**
   *  Different kind of streams
   */
  private void example2() {
    System.out.println("===Example 2===");

    Arrays.asList("a1", "a2", "a3")
        .stream()
        .findFirst()
        .ifPresent(System.out::println);

    Stream.of("a1", "a2", "a3")
        .findFirst()
        .ifPresent(System.out::println);

    IntStream.range(1, 4)
        .forEach(System.out::println);

    Arrays.stream(new int[] {1, 2, 3})
        .map(i -> 2 * i)
        .average()
        .ifPresent(System.out::println);

    Stream.of("a1", "a2", "a3")
        .mapToInt(i -> Integer.valueOf(i.substring(1,2)))
        .max()
        .ifPresent(System.out::println);

    IntStream.range(1, 4)
        .mapToObj(i -> "\"" + String.valueOf(i) + "\"")
        .forEach(System.out::println);

    Stream.of(1.0, 2.0, 3.0)
        .mapToInt(Double::intValue)
        .mapToObj(i -> "a" + i)
        .forEach(System.out::println);
  }

  /**
   *  Processing Order
   */
  private void example3() {
    System.out.println("===Example 3===");

    Stream.of("d2", "a2", "b1", "b3", "c")
        .filter(s -> {
          System.out.println("filter: " + s);
          return true;
        });

    Stream.of("d2", "a2", "b1", "b3", "c")
        .filter(s -> {
          System.out.println("filter: " + s);
          return true;
        })
        .forEach(s -> System.out.println("forEach: " + s));

    Stream.of("d2", "a2", "b1", "b3", "c")
        .map(s -> {
          System.out.println("map: " + s);
          return s.toUpperCase();
        })
        .anyMatch(s -> {
          System.out.println("anyMatch: " + s);
          return s.startsWith("A");
        });

    Stream.of("d2", "a2", "b1", "b3", "c")
        .sorted((s1, s2) -> {
          System.out.printf("sort: %s; %s\n", s1, s2);
          return s1.compareTo(s2);
        })
        .filter(s -> {
          System.out.println("filter: " + s);
          return s.startsWith("a");
        })
        .map(s -> {
          System.out.println("map: " + s);
          return s.toUpperCase();
        })
        .forEach(s -> System.out.println("forEach: " + s));

    Stream.of("d2", "a2", "b1", "b3", "c")
        .filter(s -> {
          System.out.println("filter: " + s);
          return s.startsWith("a");
        })
        .sorted((s1, s2) -> {
          System.out.printf("sort: %s; %s\n", s1, s2);
          return s1.compareTo(s2);
        })
        .map(s -> {
          System.out.println("map: " + s);
          return s.toUpperCase();
        })
        .forEach(s -> System.out.println("forEach: " + s));
  }

  /**
   * Reusing Streams
   */
  private void example4() {
    System.out.println("===Example 4===");

    Stream<String> stream =
        Stream.of("d2", "a2", "b1", "b3", "c")
            .filter(s -> s.startsWith("a"));
    try {
      stream.anyMatch(s -> true);    // ok
      stream.noneMatch(s -> true);   // exception
    } catch (Exception e) {
      e.printStackTrace();
    }

    Supplier<Stream<String>> streamSupplier =
        () -> Stream.of("d2", "a2", "b1", "b3", "c")
            .filter(s -> s.startsWith("a"));
    streamSupplier.get().anyMatch(s -> true);   // ok
    streamSupplier.get().noneMatch(s -> true);  // ok
  }

  /**
   * Collect
   */
  private void example5() {
    System.out.println("===Example 5===");

    List<Person> filtered =
        persons
            .stream()
            .filter(p -> p.name.startsWith("P"))
            .collect(Collectors.toList());
    System.out.println(filtered);

    Map<Integer, List<Person>> personsByAge = persons
        .stream()
        .collect(Collectors.groupingBy(p -> p.age));
    personsByAge.forEach((age, list) -> System.out.format("age %s: %s\n", age, list));

    Double averageAge = persons
        .stream()
        .collect(Collectors.averagingInt(p -> p.age));
    System.out.println(averageAge);

    persons
        .stream()
        .mapToInt(p -> p.age)
        .average()
        .ifPresent(System.out::println);

    IntSummaryStatistics ageSummary = persons
        .stream()
        .collect(Collectors.summarizingInt(p -> p.age));
    System.out.println(ageSummary);

    String phrase = persons
        .stream()
        .filter(p -> p.age >= 18)
        .map(p -> p.name)
        .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));
    System.out.println(phrase);

    Map<Integer, String> map = persons
        .stream()
        .collect(Collectors.toMap(
            p -> p.age,
            p -> p.name,
            (name1, name2) -> name1 + ";" + name2));
    System.out.println(map);

    Map<Integer, ArrayList<String>> map2 = persons
        .stream()
        .collect(Collectors.toMap(
            p -> p.age,
            p -> {ArrayList<String> l = new ArrayList<String>(); l.add(p.name); return l;},
            (l1, l2) -> {l1.addAll(l2); return l1;}));
    System.out.println(map2);

    Collector<Person, StringJoiner, String> personNameCollector =
        Collector.of(
            () -> new StringJoiner(" | "),
            (stringJoiner, person) -> stringJoiner.add(person.name.toUpperCase()),
            ((stringJoiner1, stringJoiner2) -> stringJoiner1.merge(stringJoiner2)),
            StringJoiner::toString
        );
    String names = persons
        .stream()
        .collect(personNameCollector);
    System.out.println(names);

    Collector<Person, StringBuilder, String> personNameCollector2 =
        Collector.of(
            () -> new StringBuilder(),
            (stringBuilder, person) -> stringBuilder.append(person.name.toUpperCase() + " "),
            ((stringBuilder1, stringBuilder2) -> stringBuilder1.append(stringBuilder2)),
            StringBuilder::toString
        );
    String names2 = persons
        .stream()
        .collect(personNameCollector2);
    System.out.println(names2);
  }

  /**
   * FlatMap
   */
  private void example6() {
    System.out.println("===Example 6===");

    List<Foo> foos = new ArrayList<>();
    IntStream
        .range(1, 4)
        .forEach(i -> foos.add(new Foo("Foo" + i)));
    foos.forEach(f ->
        IntStream
            .range(1, 4)
            .forEach(i -> f.bars.add(new Bar("Bar" + i + " of " + f.name))));
    foos
        .stream()
        .flatMap(f -> f.bars.stream())
        .forEach(b -> System.out.println(b.name));

    foos
        .stream()
        .map(f -> f.bars.stream())
        .forEach(s ->
            s.forEach(b -> System.out.println(b.name)));

    IntStream.range(1, 4)
        .mapToObj(i -> new Foo("Foo" + i))
        .peek(f -> IntStream.range(1, 4)
            .mapToObj(i -> new Bar("Bar" + i + " <- " + f.name))
            .forEach(f.bars::add))
        .flatMap(f -> f.bars.stream())
        .forEach(b -> System.out.println(b.name));

    class Inner {
      String foo;
    }

    class Nested {
      Inner inner;
    }

    class Outer {
      Nested nested;
    }

    Optional.of(new Outer())
        .flatMap(o -> Optional.ofNullable(o.nested))
        .flatMap(n -> Optional.ofNullable(n.inner))
        .flatMap(i -> Optional.ofNullable(i.foo))
        .ifPresent(System.out::println);
  }

  /**
   * Reduce
   */
  private void example7() {
    System.out.println("===Example 7===");

    persons
        .stream()
        .reduce((p1, p2) -> p1.age > p2.age ? p1 : p2)
        .ifPresent(System.out::println);    // Pamela

    Person result =
        persons
            .stream()
            .reduce(new Person("", 0), (p1, p2) -> {
              p1.age += p2.age;
              p1.name += p2.name;
              return p1;
            });

    System.out.format("name=%s; age=%s", result.name, result.age);

    Integer ageSum = persons
        .stream()
        .reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);
    System.out.println(ageSum);  // 76

    persons
        .stream()
        .reduce(0,
            (sum, p) -> {
              System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
              return sum += p.age;
            },
            (sum1, sum2) -> {
              System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
              return sum1 + sum2;
            });

    persons
        .parallelStream()
        .reduce(0,
            (sum, p) -> {
              System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
              return sum += p.age;
            },
            (sum1, sum2) -> {
              System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
              return sum1 + sum2;
            });
  }

  /**
   * Parallel Streams
   */
  private void example8() {
    System.out.println("===Example 8===");

    Arrays.asList("a1", "a2", "b1", "c2", "c1")
        .parallelStream()
        .filter(s -> {
          System.out.format("filter: %s [%s]\n",
              s, Thread.currentThread().getName());
          return true;
        })
        .map(s -> {
          System.out.format("map: %s [%s]\n",
              s, Thread.currentThread().getName());
          return s.toUpperCase();
        })
        .forEach(s -> System.out.format("forEach: %s [%s]\n",
            s, Thread.currentThread().getName()));

    Arrays.asList("a1", "a2", "b1", "c2", "c1")
        .parallelStream()
        .filter(s -> {
          System.out.format("filter: %s [%s]\n",
              s, Thread.currentThread().getName());
          return true;
        })
        .map(s -> {
          System.out.format("map: %s [%s]\n",
              s, Thread.currentThread().getName());
          return s.toUpperCase();
        })
        .sorted((s1, s2) -> {
          System.out.format("sort: %s <> %s [%s]\n",
              s1, s2, Thread.currentThread().getName());
          return s1.compareTo(s2);
        })
        .forEach(s -> System.out.format("forEach: %s [%s]\n",
            s, Thread.currentThread().getName()));

    List<Person> persons = Arrays.asList(
        new Person("Max", 18),
        new Person("Peter", 23),
        new Person("Pamela", 23),
        new Person("David", 12));

    persons
        .parallelStream()
        .reduce(0,
            (sum, p) -> {
              System.out.format("accumulator: sum=%s; person=%s [%s]\n",
                  sum, p, Thread.currentThread().getName());
              return sum += p.age;
            },
            (sum1, sum2) -> {
              System.out.format("combiner: sum1=%s; sum2=%s [%s]\n",
                  sum1, sum2, Thread.currentThread().getName());
              return sum1 + sum2;
            });
  }

  public static void main(String[] args) {
    JavaStream stream = new JavaStream();
    stream.example1();
    stream.example2();
    stream.example3();
    stream.example4();
    stream.example5();
    stream.example6();
    stream.example7();
    stream.example8();
  }
}
