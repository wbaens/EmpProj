/**
 * @Course: SDEV 450 ~ Java Programming III
 * Author Name: Jeff
 * Assignment Name: jfinkeldey_javaii_week13
 * Date: Dec 10, 2017
 * Description: Company class is to manage inputs for Company tab
*/
package jfinkeldey_javaiii_project;

//Begin Class Company

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class Company extends GridPane {
    
        //Company fields
//    ValidationTF tfEmpID = new ValidationTF();
    static ValidationTF tfDept = new ValidationTF();
    static ValidationTF tfRole = new ValidationTF();
    static ValidationTF tfLevel = new ValidationTF();
    static ValidationTF tfSuper = new ValidationTF();
    static ValidationTF tfRate = new ValidationTF();
    static ValidationTF tfIns = new ValidationTF();    
    static ValidationTF tfInsID = new ValidationTF();        
    static ValidationTF tfDep = new ValidationTF();
    static ValidationTF tfInsPrem = new ValidationTF();
        
    public Company() {
        this.setHgap(10); 
        this.setVgap(25); 
        this.getColumnConstraints().add(new ColumnConstraints(100));
        this.getColumnConstraints().add(new ColumnConstraints(100));
        this.getColumnConstraints().add(new ColumnConstraints(100));
        this.getColumnConstraints().add(new ColumnConstraints(100));
        this.getColumnConstraints().add(new ColumnConstraints(100));    
        this.setPadding(new Insets(5, 5, 5, 5));
        this.add(new Label("Emp ID:"), 0, 1);
        this.add(new Label("Department:"), 0, 2);
        this.add(new Label("Role:"), 0, 3);
        this.add(new Label("Level:"), 0, 4);
        this.add(new Label("Supervisor:"), 2, 4);
        this.add(new Label("Rate:"), 4, 4);
        this.add(new Label("Insurance:"), 0, 5);
        this.add(new Label("Insurance ID:"), 2, 5);
        this.add(new Label("Dependents:"), 4, 5);    
        this.add(new Label("Premium:"), 0, 6);

//        this.add(tfEmpID, 1, 1);    
        this.add(tfDept, 1, 2, 4, 1);    
        this.add(tfRole, 1, 3, 4, 1);    
        this.add(tfLevel, 1, 4);    
        this.add(tfSuper, 3, 4);    
        this.add(tfRate, 5, 4);    
        this.add(tfIns, 1, 5);    
        this.add(tfInsID, 3, 5);    
        this.add(tfDep, 5, 5);    
        this.add(tfInsPrem, 1, 6);
    }
    
    public static void clear() {
            tfDept.setText("");
            tfRole.setText("");
            tfLevel.setText("");
            tfSuper.setText("");
            tfRate.setText("");
            tfIns.setText("");
            tfInsID.setText("");
            tfDep.setText("");
            tfInsPrem.setText("");
    }
    
public static void search(Integer IDin) {
   try{           
//       Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
       Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection con=DriverManager.getConnection(  
            "jdbc:derby://localhost:1527/employeedatabase","whiteflour","123456");  
       PreparedStatement pst = con.prepareStatement("Select * from Employees where EmpID=?");
       
       pst.setInt(1, IDin);
               
       ResultSet rs = pst.executeQuery();     
       
       if(rs.next()) {
           tfDept.setText(rs.getString("Department"));
           tfRole.setText(rs.getString("Role"));
           tfLevel.setText(String.valueOf(rs.getInt("Level")));
           tfSuper.setText(rs.getString("Supervisor"));
           tfRate.setText(String.valueOf(rs.getString("Rate")));
           tfIns.setText(rs.getString("Ins"));
           tfInsID.setText(rs.getString("InsID"));
           tfDep.setText(String.valueOf(rs.getString("Dependents")));
           tfInsPrem.setText(String.valueOf(rs.getString("InsPrem")));
           
//           return true;    
       }           
//       else
//           return false;            
   }
   catch(Exception e){
       e.printStackTrace();
//       return false;
   }       
}

    public static void update(Integer ID) {
            try{
                Connection con=DriverManager.getConnection(  
                        "jdbc:derby://localhost:1527/employeedatabase","whiteflour","123456");  
                PreparedStatement stmt=con.prepareStatement("update Employees Set "
                        + "Department = ?, Role = ?, Level = ?, Supervisor = ?, Rate = ?, Ins = ?, InsID = ?, "
                        + "Dependents = ?, InsPrem = ? "
                        + " where Empid = ?");

                stmt.setString(1,tfDept.getText());
                stmt.setString(2,tfRole.getText());
                stmt.setInt(3,Integer.parseInt(tfLevel.getText()));
                stmt.setInt(4,Integer.parseInt(tfSuper.getText()));
                stmt.setFloat(5,Float.parseFloat(tfRate.getText()));
                stmt.setString(6,tfIns.getText());
                stmt.setString(7,tfInsID.getText());
                stmt.setInt(8,Integer.parseInt(tfDep.getText()));
                stmt.setFloat(9,Float.parseFloat(tfInsPrem.getText()));
                stmt.setInt(10, ID);
                
                int i=stmt.executeUpdate();  
                
                con.close();  
            }catch(Exception e){ System.out.println(e); } 
    }
        
} //End Class Timesheet
