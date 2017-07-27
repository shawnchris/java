package leetcode;

public class A605_Can_Place_Flowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int l = flowerbed.length;
        if (l == 0) return false;
        if (l == 1) return flowerbed[0] == 0 && n <= 1 || n <= 0 ? true : false;

        for (int i = 0; i < l && n > 0; i++) {
            if (flowerbed[i] == 0 && (i == 0 && flowerbed[1] == 0
                    || i == l - 1 && flowerbed[l - 2] == 0
                    || i > 0 && i < l - 1 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                n--;
                System.out.println(i);
            }
        }

        return n <= 0;
    }
}
