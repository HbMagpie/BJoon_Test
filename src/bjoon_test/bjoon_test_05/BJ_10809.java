package bjoon_test_05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BJ_10809 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
								
		int[] arr = new int[26];				// 배열 초기화
				
		for(int i = 0; i < arr.length; i++) {	// 모든 배열값에 -1 기본값을 줌
			arr[i] = -1;
		}
				
		String S = br.readLine();				// 단어(S) 입력
				
		for(int i = 0; i < S.length(); i++) {
			char ch = S.charAt(i);				// charAt() : String으로 저장된 문자열 중에서 한 글자만 선택해서 char타입으로 변환
					
		if(arr[ch-'a'] == -1) {					/* 동일 문자가 포함되어있는 경우 처음 나타난 위치를 나타내야 함
														 (-1 인 경우에는 배열의 원소 값을 변경하고 -1 이 아닌 경우 배열의 원소 값을 변경하지 않음)*/
			arr[ch-'a'] = i;					// ch 의 문자의 위치를 앞서 만든 arr 배열의 값으로 변환('a'=97)
			}
		}	
				
		for(int val : arr) {					// 최종 배열 출력
			System.out.print(val + " ");
		}
	} 
}