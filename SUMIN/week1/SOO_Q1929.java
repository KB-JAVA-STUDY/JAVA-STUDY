package SUMIN.week1;

import java.util.Scanner;

public class SOO_Q1929 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();

        boolean[] isPrime = new boolean[n + 1];  // 기본값 false (소수 여부 저장)
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;  // 0과 1은 제외하고, 2 이상부터 소수라고 가정
        }

        for (int i = 2; i * i <= n; i++) {  // √n 까지만 확인
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {  // i의 배수 제거
                    isPrime[j] = false;
                }
            }
        }

        for (int i = m; i <= n; i++) {
            if (isPrime[i]) {
                System.out.println(i);
            }
        }

        sc.close();
    }
}
