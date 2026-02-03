 package com.cjc.bms.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.cjc.bms.config.DbConnection;
import com.cjc.bms.model.Account;
import com.cjc.bms.service.RBI;

public class SBI implements RBI {
   
	Scanner sc = new Scanner(System.in);
	
	Connection con = DbConnection.getConnection();
	
	Account ac = new Account();
	
	@Override
	public void createAccount() {
		
		System.out.println("Enter Account Number Here :");
		ac.setAcno(sc.nextInt());
		
		System.out.println("Enter Account Holder Name Here :");
		ac.setName(sc.next()+sc.nextLine());
		
		System.out.println("Enter Account Holders Address Here :");
		ac.setAddress(sc.next()+sc.nextLine());
		
		System.out.println("Enter Adhar Number Here :");
		ac.setAdharno(sc.nextLong());
		
		System.out.println("Enter Mobile Number Here :");
		ac.setMobileno(sc.nextLong());
		
		System.out.println("Enter Pancard Number Here :");
		ac.setPancard(sc.next());
		
		System.out.println("Enter Account Holder Gender Here :");
		ac.setGender(sc.next());
		
		System.out.println("Enter Account Balance Here :");
		ac.setBalance(sc.nextDouble());
		
		//Step 3: Create SQL Query
		String insert = "insert into account value(?,?,?,?,?,?,?,?)";
		
		//Step 4: Create PreparedStatement(I) Object
		
		try {
		  PreparedStatement ps = con.prepareStatement(insert);
		  
		  ps.setInt(1,ac.getAcno());
		  ps.setString(2, ac.getName());
		  ps.setString(3, ac.getAddress());
		  ps.setLong(4,ac.getAdharno());
		  ps.setLong(5,ac.getMobileno());
		  ps.setString(6, ac.getPancard());
		  ps.setString(7, ac.getGender());
		  ps.setDouble(8, ac.getBalance());
		  
		  //Step 5: Execute The SQL Query
		  ps.execute();
		  
		System.out.println("Account Created Successfully......");  
		
		}
		catch(SQLException e) {
		   System.out.println(e.getMessage());
		}
		catch(InputMismatchException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void showDetails() {
		
		System.out.println("Enter Account Holder No");
		int acno = sc.nextInt();
		
		if(acno != ac.getAcno()) {
			System.out.println("Invalid Account Number");
		}
		
		//Step 3: Create SQL Query
		String select = "select * from account where acno = ?";
		
		//Step 4: Create PreparedStatement(I) Object	 
		try {
			 PreparedStatement ps = con.prepareStatement(select);
			 ps.setInt(1, acno);
			 
			 //Step 5: Execute SQL Query
			 ResultSet rs = ps.executeQuery();
			 
			 if(rs.next()) {
				 System.out.println("Account Number :- "+rs.getInt("acno"));
				 System.out.println("Account Holder Name :- "+rs.getString("name"));
				 System.out.println("Holder's Address :- "+rs.getString("addr"));
				 System.out.println("Adhar Number :- "+rs.getLong("adharno"));
				 System.out.println("Mobile Number :- "+rs.getLong("mobileno"));
				 System.out.println("Pancard :- "+rs.getString("pancard"));
				 System.out.println("Gender Of Holder :- "+rs.getString("gender"));
				 System.out.println("Balance :- "+rs.getDouble("balance"));
			 }
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		catch(InputMismatchException e) {
			System.out.println(e.getMessage());
		}
		catch(NullPointerException e)
		{
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void withDrawlMoney() {
		
		 try {
		        System.out.println("Enter Withdrawal Amount: ");
		        double amount = sc.nextDouble();

		        System.out.println("Enter Account Number For Withdrawal: ");
		        int acno = sc.nextInt();

		        //Step Create SQL Query
		        String select = "SELECT balance FROM account WHERE acno = ?";
		        
		        //Create PreparedStatement(I) Object
		        PreparedStatement pst = con.prepareStatement(select);
		        pst.setInt(1, acno);
		        
		        //Execute SQl Query
		        ResultSet rs = pst.executeQuery();

		        if (rs.next()) {
		            double balance = rs.getDouble("balance");

		            if (amount > 0) {
		                if (balance >= amount) {
		                    
		                    double newBalance = balance - amount;

		                    String update = "UPDATE account SET balance = ? WHERE acno = ?";
		                    
		                    PreparedStatement pstUpdate = con.prepareStatement(update);
		                    pstUpdate.setDouble(1, newBalance);
		                    pstUpdate.setInt(2, acno);

		                    int rows = pstUpdate.executeUpdate();
		                    if (rows > 0) {
		                        System.out.println("Withdrawal Successful! New Balance: " + newBalance);
		                    }
		                } else {
		                    System.out.println("Insufficient Balance! Current Balance: " + balance);
		                }
		            } else {
		                System.out.println("Invalid Amount! Withdrawal must be greater than 0.");
		            }
		        } else {
		            System.out.println("Account not found!");
		        }
		    } catch (SQLException e) {
		        System.out.println(e.getMessage());
		    }
		    catch(InputMismatchException e) {
		    	    System.out.println(e.getMessage());
		    }
    }
	
	@Override
	public void depositeMoney() {
		
		 try {
		        System.out.println("Enter Deposit Amount: ");
		        double amount = sc.nextDouble();

		        System.out.println("Enter Account Number For Deposit: ");
		        int acno = sc.nextInt();

		        if (amount > 0) {
		            
		            String select = "SELECT balance FROM account WHERE acno = ?";
		            PreparedStatement pst = con.prepareStatement(select);
		            pst.setInt(1, acno);
		            ResultSet rs = pst.executeQuery();

		            if (rs.next()) {
		                double balance = rs.getDouble("balance");
		                double newBalance = balance + amount;

		                
		                String update = "UPDATE account SET balance = ? WHERE acno = ?";
		                PreparedStatement pstUpdate = con.prepareStatement(update);
		                pstUpdate.setDouble(1, newBalance);
		                pstUpdate.setInt(2, acno);

		                int rows = pstUpdate.executeUpdate();
		                if (rows > 0) {
		                    System.out.println("Deposit Successful! New Balance: " + newBalance);
		                }		                
		            }
		            else{
		                System.out.println("Account not found!");
		            }
		        }
		        else 
		        {
		            System.out.println("Invalid Amount! Deposit must be greater than 0.");
		        }
		    }
		    catch (SQLException e) {
		        System.out.println(e.getMessage());
		    }
		    catch(InputMismatchException e) {
			 System.out.println(e.getMessage());
		    }
	}

	@Override
	public void showBalance() {
		
		try {
		System.out.println("Enter Account Number For View Balance :");
		int acno = sc.nextInt();
		
		String check = "select balance from account where acno=?";
		
		  PreparedStatement ps = con.prepareStatement(check);
		  ps.setInt(1, acno);
		  
		  ResultSet rs = ps.executeQuery();
		  if(rs.next()) {
			  double balance = rs.getDouble("balance");
			  System.out.println("Account Balance For Ac No " + acno + " is : " + balance);
		  }
		  else
		  {
			  System.out.println("Account Not Found!");
		  }
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		catch(InputMismatchException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}
	}
}
