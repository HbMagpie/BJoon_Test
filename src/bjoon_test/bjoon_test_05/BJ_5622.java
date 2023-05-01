package bjoon_test_05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BJ_5622 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		
		int count=0;
		int k = s.length();
        
		for(int i = 0 ; i < k ; i++) {
			
			// 숫자 1이 count=2이고 그 다음 순서(왼쪽)로 갈수록 +1이 된다고 했음
			switch(s.charAt(i)) {
			case 'A' : case 'B': case 'C' : 	
				count += 3; 	
				break;
                
			case 'D' : case 'E': case 'F' : 
				count += 4; 
				break;
                
			case 'G' : case 'H': case 'I' : 
				count += 5; 
				break;
                
			case 'J' : case 'K': case 'L' : 
				count += 6; 
				break;
                
			case 'M' : case 'N': case 'O' : 
				count += 7; 
				break;
                
			case 'P' : case 'Q': case 'R' : case 'S' :
				count += 8; 
				break;
                
			case 'T' : case 'U': case 'V' : 
				count += 9; 
				break;
                
			case 'W' : case 'X': case 'Y' : case 'Z' : 
				count += 10; 
				break;
			}	// break 하면 switch문만 빠져나감
		}		
		System.out.print(count);
			
		}
	}