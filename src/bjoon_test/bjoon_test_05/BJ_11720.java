package bjoon_test_05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BJ_11720 {
	public static void main(String[] args) throws IOException {
		// BufferedReader 사용(데이터 양이 많을 때 효율적)
		/* getBytes() = 유니코드 문자열을 바이트코드로 인코딩 해주는 메소드
		   getBytes( )의 인자로 캐릭터셋을 넘기지 않으면 디폴트 charset으로 코딩
		   문자열을 byte[] 배열로 바꾸는 메소드		
		*/
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		bf.readLine();
		
		int sum = 0;
		
		for(byte value : bf.readLine().getBytes()) {
			sum += (value - '0');
		}
		System.out.println(sum);
	} 
}