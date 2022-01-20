import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[] list = new int[1000000];
        for(int i = 0; i < 1000000; i++) {
            list[i] = ThreadLocalRandom.current().nextInt(10000, 10000000);
        }
        Thread.sleep(1000);
        long len = System.currentTimeMillis();
        sort(list);
        System.out.println(System.currentTimeMillis() - len);
        //printList(list);
    }

    public static void printList(int[] array) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < array.length; i++) {
            sb.append(array[i] + ",");
        }
        System.out.println(sb.toString());
    }

    /*
       We're going to assume that the Integers are whole numbers ==> this means no negatives.
     */
    public static void sort(int[] numbers) {
        int max = Integer.MIN_VALUE;
        for(int i : numbers) {
            if(i > max) {
                max = i;
            }
        }
        int mask = 1 << (int) (Math.log(max) / Math.log(2));
        partition(numbers, 0, numbers.length - 1, mask);
    }

    public static void partition(int[] numbers, int from, int to, int mask) {
        if(from >= to || mask == 0) {
            return;
        }
        int pos = from;
        for(int i = from; i <= to; i++) {
            if((numbers[i] & mask) == 0) {
                int temp = numbers[i];
                numbers[i] = numbers[pos];
                numbers[pos] = temp;
                pos++;
            }
        }
        mask = mask >> 1;
        partition(numbers, from, pos - 1, mask);
        partition(numbers, pos, to, mask);
    }
}
