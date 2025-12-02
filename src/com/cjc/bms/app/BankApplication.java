package com.cjc.bms.app;

import java.util.Scanner;
import com.cjc.bms.service.RBI;
import com.cjc.bms.serviceimpl.SBI;

public class BankApplication {
   
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		RBI rbi = new SBI();
		 
		int ch = 0;
		
		while (ch != 6) {
		 
		System.out.println("\n***WELCOME TO CJC BANK APPLICATION***");
		System.out.println("Enter 1 For Create Account");
		System.out.println("Enter 2 For View Account Details");
		System.out.println("Enter 3 For Withdrawl Money");
		System.out.println("Enter 4 For Deposite Money");
		System.out.println("Enter 5 For Check Balance");
		System.out.println("Enter 6 For Exit");
		System.out.println("************************************");
		
		System.out.println("Enter Your Choice :");
		ch = sc.nextInt();
		
		switch(ch) {
		case 1:
			rbi.createAccount();
			break;
        case 2:
			rbi.showDetails();
			break;
        case 3:
	        rbi.withDrawlMoney();
	        break;
        case 4:
	        rbi.depositeMoney();
	        break;
        case 5:
	        rbi.showBalance();
	        break;
        case 6:
        	System.out.println("Thank You For Using CJC Bank Application.Goodbye!");
        	break;	
        default:
        	System.out.println("Invalid Choice! Plz Try Again.");
		}
	
		}	
		
	}
}
