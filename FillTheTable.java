package the_project;

import java.sql.*;
import java.util.ArrayList;

public class FillTheTable {

    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
    }
        Connection con=null;

        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost/gestionpharmacie","root","");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(con);
        }

        return con;


        }

      public static String[][] fillTable(String sql){
          Connection con=getConnection();
          Statement ps;
          ResultSet rs;
          ArrayList<String[]> addrows=null;
          String[][] Rows;
          String[] row;
          int i=0;
          try {
              ps=con.createStatement();
              rs= ps.executeQuery(sql);
              addrows= new ArrayList<>();
              while (rs.next()){
                  row=new String[5];
                  row[0]=rs.getString("Nom");
                  row[1]=rs.getString("Prix");
                  row[2]=rs.getString("Quantity");
                  row[3]=rs.getString("Date_fin");
                  row[4]=rs.getString("Id");
                  addrows.add(row);
                  i++;

              }
          } catch (SQLException e) {
              System.out.println(e.getMessage());
          }
//          Rows=new String[i][];
//          for (String[] r:
//                  addrows) {
//              for (int j = 0; j < i; j++) {
//                  Rows[j]=r;
//              }
//          }

          return addrows.toArray(new String[i][]);
      }
      public static String[][] showData(){
       return fillTable("SELECT * FROM themain");
    }
      public static String[][] recherche(String a){
          String sql="SELECT * FROM themain WHERE Nom='"+a+"'";
          return fillTable(sql);
    }
    public static void delete(){
        Connection con=getConnection();
        Statement ps;
        try {
            ps = con.createStatement();
            ps.addBatch("DELETE FROM themain");
            ps.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteRow(String a){
        Connection con=getConnection();
        Statement ps;
        try {
            ps = con.createStatement();
            ps.addBatch("DELETE FROM themain WHERE Id='"+a+"'");
            ps.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void acheter(int nbr,String id,int pQ){
        Connection con=getConnection();
        Statement ps;
        try {
            ps = con.createStatement();
            ps.addBatch("UPDATE `themain` SET `Quantity`='"+(pQ-nbr)+"' WHERE Id='"+id+"'");
            ps.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
