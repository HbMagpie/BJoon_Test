package bjoon_test_04;

import java.util.Scanner;

public class BJ_1546 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();			// 과목 개수(N) 입력
		int arr[] = new int[N];			// 배열 초기화
		int M = 0;						// 최댓값(M) 초기화
		float sum = 0, a = 0;			// a = 수정 후 점수(실수형)
						
		for(int i = 0; i < N; i++) {	// 과목수만큼 점수 입력
			arr[i] = sc.nextInt();					
				if(M < arr[i]) {		// 최댓값 구하기
					M = arr[i];				
				}		
			}
			for(int i = 0; i < N; i++) {	// 모든 점수 수정(점수/최댓값*100)
			 a = ((float)arr[i]/M*100);
			 sum += a;
			}
			System.out.print(sum/N);	// 총 평균 값 출력
			sc.close();
		}
	}