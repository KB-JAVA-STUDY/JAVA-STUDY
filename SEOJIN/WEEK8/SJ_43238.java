import java.rmi.dgc.VMID;
import java.util.Arrays;

public class SJ_43238 {

    public static void main(String[] args) {
        int n = 6;
        int[] times = {7, 10};
        System.out.println(solution(n, times));


    }

    public static long solution(int n, int[] times) {
        Arrays.sort(times);


        long min = 0;
        // max에 대해서 다시 생각해보기
        long max = (long)times[times.length-1] * n;


        while (min < max){
            long mid = (max + min) / 2;
            long answer = 0;

            for (int i = 0; i < times.length; i++) {
                answer += mid / times[i];
            }
            if (answer < n){
                min = mid + 1;
            }else {
                max = mid;
            }
        }


        return min;
    }


}
