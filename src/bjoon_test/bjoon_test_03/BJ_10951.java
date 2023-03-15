package bjoon_test_03;

import java.util.Scanner;

public class BJ_10951 {
	public static void main(String[] args) {
		// EOF(End Of File) = 입력에서 더이상 읽을 수 있는 데이터가 없을 때 종료됨
		
		Scanner sc = new Scanner(System.in);
				
		 while(sc.hasNextInt()) {
			 /* 입력값이 정수일경우 true를 반환하며 정수가 아닐경우 바로 예외를 던지며
			    더이상의 입력을 받지 않고 hasNextInt()에서 false를 반환하면서 반복문이 종료됨
			  */
					 
			 int a = sc.nextInt();
			 int b = sc.nextInt();
			 System.out.println(a+b);
		 }
		 sc.close();
	}
}
