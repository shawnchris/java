package other;

public class DifferentExceptions {
    private void funcThrowsRunTimeException(boolean flag) {
        if (flag) {
            throw new RuntimeException("Intended");
        }
        else {
            System.out.println("Won't throw.");
        }
    }

    private void runTimeExceptions() {
        for (int i = 0 ; i < 5; i++) {
            try {
                if (i < 3)
                    funcThrowsRunTimeException(true);
                else
                    funcThrowsRunTimeException(false);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DifferentExceptions de = new DifferentExceptions();
        de.runTimeExceptions();
    }
}
