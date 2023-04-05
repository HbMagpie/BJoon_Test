package bjoon_test_04;

import java.util.Scanner;

public class BJ_10807 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int arr[] = new int[a];
		int count = 0;
		
		for(int i = 0; i < a; i++) {
			arr[i] = sc.nextInt();
		}
			int b = sc.nextInt();		
			for(int i = 0; i < a; i++) {
				if(arr[i] == b) {
					count++;
				}	
			}
			System.out.print(count);
			sc.close();
		}

	}
