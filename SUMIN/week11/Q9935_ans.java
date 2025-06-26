package SUMIN.week11;

import java.util.*;
import java.io.*;

public class Q9935_ans {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String bomb = br.readLine();
        int bombLen = bomb.length();

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < str.length(); i++) {
            stack.addLast(str.charAt(i));

            // 스택의 끝이 bomb의 마지막 문자와 같으면 검사 시작
            if (stack.size() >= bombLen) {
                boolean isBomb = true;
                Iterator<Character> iter = stack.descendingIterator();
                for (int j = bombLen - 1; j >= 0; j--) {
                    if (!iter.hasNext() || iter.next() != bomb.charAt(j)) {
                        isBomb = false;
                        break;
                    }
                }

                if (isBomb) {
                    for (int j = 0; j < bombLen; j++) {
                        stack.removeLast();
                    }
                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            StringBuilder sb = new StringBuilder();
            for (char c : stack) {
                sb.append(c);
            }
            System.out.println(sb);
        }
    }
}
