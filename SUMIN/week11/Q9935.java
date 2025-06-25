package SUMIN.week11;

import java.util.*;
import java.io.*;
public class Q9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String bomb = br.readLine();

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < str.length(); i++) {
            stack.add(str.charAt(i));
        }

        String checkWord = stack.poll().toString();
        for (int i = 0; i < bomb.length()-1; i++) {
            checkWord = checkWord + String.valueOf(stack.poll());
        }

        if (checkWord.matches(bomb)) {
            for (int i = 0; i < bomb.length(); i++) {
                checkWord = checkWord + String.valueOf(stack.poll());
            }
            checkWord = checkWord.substring(bomb.length());
        }
        if (!checkWord.matches(bomb)) {
            stack.add(checkWord.charAt(0));
            checkWord = checkWord.substring(1,bomb.length());
            checkWord = checkWord + String.valueOf(stack.poll());
        }

        System.out.println(checkWord);

        while(!stack.isEmpty()) {
            System.out.print(stack.poll() + " ");
        }
    }
}
