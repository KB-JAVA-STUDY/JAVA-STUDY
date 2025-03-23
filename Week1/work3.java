import java.util.*;

public class work3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for (int i = 0; i * i <= N; i++) {
            while (N % i == 0) {
                System.out.println(i);
                N /= i;
            }
        }
        if (N != 1) {
            System.out.println(N);
        }

        sc.close();
    }
}
