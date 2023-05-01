package bjoon_test_05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BJ_1152 {
	public static void main(String[] args) throws IOException {
		/* 문자열의 앞 뒤에도 공백이 있을 수 있기 때문에 그냥 공백의 개수만 구하면 오류 발생 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		System.out.print(st.countTokens());
		
		/* 또 다른 방법
		  
		  int count = 0;
		  int pre_str = 32;	// 공백을 의미함
		  int str ;
			
		while(true) {
		  str = System.in.read(); 
            if(str == 32) {		 		   // 입력받은 문자가 공백일 때,
				if(pre_str != 32) count++; // 이전의 문자가 공백이 아니면
			}
 
			
			else if(str == 10) {		   // 입력받은 문자가 개행일때 ('\n')
				if(pre_str != 32) count++; // 이전의 문자가 공백이 아니면
				break;
			}
			pre_str = str;			
		}		
		System.out.println(count); */
		
		}
	}