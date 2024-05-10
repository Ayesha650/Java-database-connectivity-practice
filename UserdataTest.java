package in.jdbc.project;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.Scanner;

public class UserdataTest {
    public static void main(String[] args) throws Exception {
try{
        //user taking data
        Scanner sc =  new Scanner(System.in);
        boolean continueInserting  = true;    // boolean variable set a true

    //Load and register the class
    Class.forName("com.mysql.cj.jdbc.Driver");

    //Establish a connection
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdata","root","Ayesha#123");


    while(continueInserting){

        System.out.println("Enter the name : ");
        String name = sc.nextLine();

        System.out.println("Enter the email: ");
        String email = sc.nextLine();

        System.out.println("Enter the gender: ");
        String gender = sc.nextLine();


        //prepare sql statement
        PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO user_tab VALUES (?, ?, ?)");
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,email);
        preparedStatement.setString(3,gender);

        //Execute sql statements
        int effectedrows = preparedStatement.executeUpdate();
        if(effectedrows >0){
            System.out.println("Successfully inserted record: ");
        }
        else {
            System.out.println("Faild to insert the record: ");
        }
         System.out.println("Do you want to insert another record? (Yes/No)");
         String choice =sc.nextLine().trim().toLowerCase();

         if(!choice.equals("Yes")){
             continueInserting = false;

         }
         //option to view the record
        System.out.println("Do you want to view the inserted record? (Yes/No)");
         String viewrecord = sc.nextLine().trim().toLowerCase();

         if(viewrecord.equals("Yes")){
             PreparedStatement viewstatement = con.prepareStatement("SELECT * from user_tab");
           ResultSet resultSet = viewstatement.executeQuery();
           System.out.println("Inserted records: ");
           while (resultSet.next()){
               System.out.println("Name"+ resultSet.getString("name")+ " " +"Email" + resultSet.getString("email" + " " + "Gender" + resultSet.getString("gender")));


           }
           viewstatement.close();
         }
    }

    con.close();
    sc.close();
    }
   catch (SQLException e){
    e.printStackTrace();
   }

    }

}
