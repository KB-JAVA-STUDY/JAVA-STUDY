import java.io.*;
import java.util.*;

public class HJ9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        String bomb = br.readLine();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));
            if(sb.length() >= bomb.length()) {
                boolean isMatch = true;
                for(int j = 0; j < bomb.length(); j++) {
                    if(sb.charAt(sb.length() - bomb.length() + j) != bomb.charAt(j)) {
                        isMatch = false;
                        break;
                    }
                }
                if(isMatch) sb.replace(sb.length() - bomb.length(), sb.length(), "");
            }
        }
        bw.write(sb.length() == 0 ? "FRULA" : sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}