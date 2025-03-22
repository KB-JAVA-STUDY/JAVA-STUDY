package week1;

import java.util.Scanner;

public class Q15649 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        //1. 배열에 1부터 n까지의 값을 넣어주기
        int[] numArr = new int[n];
        for (int i = 0; i < n; i++) {
            numArr[i] = i+1;
        }

        //?어케짬

        //for문을 돌려서 자신을 제외한 다른 칸에 담긴 값들을 한번씩 전부 돌아야함(한번씩 돌았는지 여부는 isPrinted boolean으로 확인)
        sc.close();
    }
}
