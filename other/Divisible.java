package other;

public class Divisible {
    static long[] dividens = {25235, 435768, 14892, 332168, 957016, 4175799, 825458785, 44358970, 7175, 8266624466628L};
    public static void main(String[] args) {
        for (long dividen : dividens) {
            System.out.print(dividen + " : ");
            for (int i = 2; i < 11; i++) {
                if (dividen % i == 0) {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
        }

    }
}
