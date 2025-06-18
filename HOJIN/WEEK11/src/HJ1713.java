import java.io.*;
import java.util.*;

public class HJ1713 {
    static class Student{
        int no, like, time;

        public Student(int no, int like, int time) {
            this.no = no;
            this.like = like;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int likeCnt = Integer.parseInt(br.readLine());

        int[] likes = new int[likeCnt];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < likeCnt; i++) {
            likes[i] = Integer.parseInt(st.nextToken());
        }

        List<Student> frame = new ArrayList<>();

        for(int i = 0; i < likeCnt; i++) {
            int now = likes[i];
            boolean found = false;

            for(Student s : frame) {
                if (s.no == now) {
                    s.like++;
                    found = true;
                    break;
                }
            }

            //이미 액자에 걸려있고 추천했으면 다음으로
            if(found) continue;

            //가득차면, 추천수 가장 적은 학생 제거 -> 동률일경우 오래된 학생 제거
            if(frame.size() >= N) {
                frame.sort(new Comparator<Student>() {
                   @Override
                   public int compare(Student s1, Student s2) {
                       if(s1.like == s2.like) return s1.time - s2.time;
                       return s1.like - s2.like;
                   }
                });
                frame.remove(0);
            }

            frame.add(new Student(now, 1, i));
        }

//        frame.sort(new Comparator<Student>() {
//            @Override
//            public int compare(Student s1, Student s2) {
//                return s1.no - s2.no;
//            }
//        });

        frame.sort((a, b) -> a.no - b.no);

        StringBuilder sb = new StringBuilder();

        for(Student std : frame) {
            sb.append(std.no + " ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }
}
