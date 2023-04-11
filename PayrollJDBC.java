package payrolljdbc;

import java.util.*; //User Input
import javax.swing.*;   //GUI
import java.sql.*;  //Database

public class PayrollJDBC {
  
public static void main(String[] args){
    	
    ArrayList<String> Payroll = new ArrayList<>();
    double Gross_Pay = 0;
    double Days_Worked;
    double Rate;
    double Tax;
    double Net_Pay = 0;
    double Fixed_Deductions = 1500;
        
      try{
        String Employee_ID = "";
        String ID = JOptionPane.showInputDialog (null, "ENTER THE FOLLOWING\n\n EMPLOYEE NUMBER:"+Employee_ID+" ","PAYROLL SYSTEM", JOptionPane.PLAIN_MESSAGE);
        Payroll.add(Employee_ID);
        
        String DRW = JOptionPane.showInputDialog (null, "DAYS OF RENDERED WORK: ", "PAYROLL SYSTEM", JOptionPane.PLAIN_MESSAGE);
    	  Days_Worked = Double.parseDouble(DRW);
        
    	  String RPD = JOptionPane.showInputDialog (null, "RATE PER DAY: ", "PAYROLL SYSTEM", JOptionPane.PLAIN_MESSAGE);
        Rate = Double.parseDouble(RPD);
        
        String FD = JOptionPane.showInputDialog (null, "FIXED DEDUCTION: ","PAYROLL SYSTEM", JOptionPane.PLAIN_MESSAGE);
    	  Fixed_Deductions = Double.parseDouble(FD);
    		
        if(Gross_Pay <= 30000){
          Tax=0.5*(Net_Pay-1.50);
        }
    	
        else if (Gross_Pay >= 30000){
          Tax=1.5*(Net_Pay-1.90);
        }
    	
        else if (Gross_Pay >= 50000){
          Tax=1.50*(Net_Pay-10.00);
    	  }
    		
    	Gross_Pay = Rate * Days_Worked;
    	Tax = Rate * 1.00;
    	Net_Pay = Gross_Pay - Tax - Fixed_Deductions;
    		
    	JOptionPane.showMessageDialog(null, "GROSS SALARY: "+Gross_Pay+ "\nTAX: "+ Tax+ "\nFIXED DEDUCTIONS: " +Fixed_Deductions+ "\nNET PAY: "+ Net_Pay,"PAYROLL SYSTEM", JOptionPane.PLAIN_MESSAGE);
    		     
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PayrollSystem","root","1010");
    PreparedStatement Statement = conn.prepareStatement("insert into BPSData (Employee_ID, Days_Worked, Rate, Fixed_Deductions, Gross_Pay, Tax, Net_Pay) values (?,?,?,?,?,?,?)");
 
    Statement.setString(1, ID);
    Statement.setString(2, DRW);
    Statement.setString(3, RPD);
    Statement.setString(4, FD);
    Statement.setDouble(5, Gross_Pay);
    Statement.setDouble(6, Tax);
    Statement.setDouble(7, Net_Pay);
           
    int i = Statement.executeUpdate();
    
    if(i!=0){
      System.out.println("PROCESS SUCCESSFULLY EXECUUTED");
    }
      
    else{
      JOptionPane.showMessageDialog(null,"INVALID INPUT","ERROR", JOptionPane.ERROR_MESSAGE);   //Error Message;
    }
    
    }
    
    catch(Exception payroll){
      System.out.println(payroll);
    }
  
  }
  
}
