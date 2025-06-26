package BOSEONG.WEEK11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BS_2671 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        // 이렇게 하면 문자열 안에서 패턴 파악 가능
        // 신기함..
        if (input.matches("(100+1+|01)+")) {
            System.out.println("SUBMARINE");
        } else {
            System.out.println("NOISE");
        }
    }
}
