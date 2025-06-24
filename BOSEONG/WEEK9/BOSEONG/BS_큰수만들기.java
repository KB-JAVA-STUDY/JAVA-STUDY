import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        int len = number.length();
        
        for(int i = 0 ; i < len; i++){
            char c = number.charAt(i);
            
            while(!stack.isEmpty() && k > 0 && stack.peek() < c){
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        
        while (k > 0) {
            stack.pop();
            k--;
        }
        
        while(!stack.isEmpty()){
            sb.append(stack.pollLast());
        }
        
        return sb.toString();
    }
}