import java.io.*;

public class HJ9935_MemoryBomB {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        String bomb = br.readLine();

        while(str.contains(bomb)) {
            str = str.replaceAll(bomb, "");
        }

        bw.write(str.equals("") ? "FRULA" : str);
        bw.flush();
        bw.close();
        br.close();
    }
}
