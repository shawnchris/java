package other;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class A {


    public static void main (String[] args) {
        System.out.println();
        System.out.println(Integer.MAX_VALUE);

        A a = new A();

        a.tryStream();
    }

    private void tryStream() {
        List<POLineItem> poModel = new ArrayList<>();
        poModel.add(new POLineItem(1, "1"));
        poModel.add(new POLineItem(2, "2"));
        poModel.add(new POLineItem(3, "3"));

        Map<Long, POLineItem> purchaseOrderLineItems = poModel
                .stream()
                .collect(Collectors.toMap(POLineItem::getLineNumber, Function.identity()));

        Set<Long> set = new HashSet<Long>();

        Arrays.asList(poModel.stream().mapToLong(a -> a.getLineNumber()).toArray());



        for (long l : poModel.stream().mapToLong(a -> a.getLineNumber()).toArray()) set.add(l);

        for (Map.Entry<Long, POLineItem> entry : purchaseOrderLineItems.entrySet()) {
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        }

        for (long l : set) {
            System.out.println("key: " + l);
        }
    }

    class POLineItem {
        private long lineNumber;
        private String name;
        public POLineItem() {
            this.lineNumber = 0;
            this.name = "";
        };
        public POLineItem(long lineNumber, String name) {
            this.lineNumber = lineNumber;
            this.name = name;
        };

        public long getLineNumber() {
            return lineNumber;
        }
        public String getName() {
            return name;
        }
        @Override
        public String toString() {
            return "lineNumber: " + lineNumber + ", name: " + name;
        }
    }
}
