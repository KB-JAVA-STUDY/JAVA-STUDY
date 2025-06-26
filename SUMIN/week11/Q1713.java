package SUMIN.week11;

import java.util.*;
import java.io.*;


public class Q1713 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()); //사진 갯수
        int R = Integer.parseInt(br.readLine()); //추천 횟수

        Map<Integer, Integer> h = new HashMap<>(); //학생 번호, 추천 수
        List<Integer> frame = new ArrayList<>(); //학생 번호(순서대로 저장됨)

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < R; i++) {
            int student = Integer.parseInt(st.nextToken());

            //이미 존재하는 경우
            if (h.containsKey(student)) {
                h.put(student, h.get(student)+1); //map은 존재하는 키에 새로운 값을 put하면 덮어써버림 + hashmap은 값을 직접 수정하는 기능이 없음!
                continue;
            }


            //사진이 없고, 공간이 여유있는 경우
            if (frame.size() < N) {
                frame.add(student);
                h.put(student, 1);
                continue;
            }

            //사진이 꽉 찬 경우 -> 추천수가 가장 적은 > 가장 오래된 학생 사진 삭제
            int remove = frame.get(0); //일단 가장 앞에 학생 사진을 삭제 대상으로 지정
            for (int id : frame) {
                if (h.get(id) < h.get(remove) || //지우려는 (초기값은 맨 앞) 학생 추천수가 반복문 도는 학생 추천수보다 크다면 -> 삭제하려는 대상 수정
                        (h.get(id) == h.get(remove)) && (frame.indexOf(id) < frame.indexOf(remove)) //두 학생 추천 수가 같을때 -> 먼저 들어온게 삭제대상이 되어야 함
                ) {
                    remove = id; // 삭제될 대상의 인덱스 값을 id로 바꾸어 줌
                }
            }

            //삭제하고 새로 넣어주기
            h.remove(remove);
            h.put(student, 1);
            frame.remove((Integer) remove);
            frame.add(student);
        }
        Collections.sort(frame); // 후보로 남아있는 학생들 오름차순으로 정렬
        for (int i = 0; i < frame.size(); i++) {
            sb.append(frame.get(i)).append(" ");
        }
        System.out.println(sb);

    }
}
