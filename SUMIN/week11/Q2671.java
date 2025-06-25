package SUMIN.week11;

import java.util.*;
import java.io.*;
//우리가 식별하고자 하는 잠수함의 엔진소리의 패턴은 다음과 같다.
//(100~1~|01)~


public class Q2671 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        if (str.matches("(100+1+|01)+")) {
            System.out.println("SUBMARINE");
        } else {
            System.out.println("NOISE");
        }
    }
}
