package bjoon_test_05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BJ_1152 {
	public static void main(String[] args) throws IOException {
		/* ���ڿ��� �� �ڿ��� ������ ���� �� �ֱ� ������ �׳� ������ ������ ���ϸ� ���� �߻� */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		System.out.print(st.countTokens());
		
		/* �� �ٸ� ���
		  
		  int count = 0;
		  int pre_str = 32;	// ������ �ǹ���
		  int str ;
			
		while(true) {
		  str = System.in.read(); 
            if(str == 32) {		 		   // �Է¹��� ���ڰ� ������ ��,
				if(pre_str != 32) count++; // ������ ���ڰ� ������ �ƴϸ�
			}
 
			
			else if(str == 10) {		   // �Է¹��� ���ڰ� �����϶� ('\n')
				if(pre_str != 32) count++; // ������ ���ڰ� ������ �ƴϸ�
				break;
			}
			pre_str = str;			
		}		
		System.out.println(count); */
		
		}
	}