package bjoon_test_04;

import java.util.Scanner;

public class BJ_10811 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int arr[] = new int[N];					// N만큼 출력해야 하므로 그 숫자에 맞추기 위해 int[N]
		
		for(int a = 0; a < N; a++) {			// 기본 1~5값 입력
			arr[a] = a+1;			
		}		
		int count = 0;
		while(count < M) {						// count가 M과 같아질 때까지 반복(줄 수 맞추기)
			int i = sc.nextInt()-1;				// 인덱스 값을 맞춰주기 위해 -1을 해줌
			int j = sc.nextInt()-1;	
			while(j > i) {						// j값이 i값보다 크면 교환 반복
				int temp = arr[i];				
				arr[i] = arr[j];
				arr[j] = temp;
				j--;							// j는 -1, i는 +1을 해주면서 두 값이 같아지면 반복 종료
				i++;
				} 
			count++;							// 한 줄 끝나면 count +1
			}	
		for(int b = 0; b < N; b++) {			// 순서대로 결괏값 출력
			System.out.print(arr[b] + " ");
		}
		sc.close();	
		}
	}