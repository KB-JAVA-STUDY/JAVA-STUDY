import java.io.*;
import java.util.*;
/*
    (100~1~|01)~
 */
public class HJ2671 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String sound = br.readLine();
        boolean isSub = sound.matches("(100+1+|01)+");

        bw.write(isSub ? "SUBMARINE" : "NOISE");
        bw.flush();
        bw.close();
        br.close();
    }
}
