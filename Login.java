package the_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Login extends JFrame implements ActionListener{
    JTextField txt1 ;
    JTextField TXT2;
    public Login() {
        // TopPanel.setBackground(new Color(236, 180, 180,150));
        // jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Back_50px.png")));
        this.setSize(500, 500);
        JLabel nomuser = new JLabel("nom_utilisateur :");
        txt1 = new JTextField(15);
        JLabel password = new JLabel("mot de passe   :");
        TXT2 = new JTextField(15);
        Container contentPane = getContentPane();
        SpringLayout layout = new SpringLayout();
        JLabel login = new JLabel("LOGIN ");
        JButton connex = new JButton("Se connecter");
        contentPane.setLayout(layout);
        contentPane.add(nomuser);

        layout.putConstraint(SpringLayout.NORTH, nomuser, 150, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, nomuser, 50, SpringLayout.WEST, contentPane);
        contentPane.add(txt1);
        layout.putConstraint(SpringLayout.NORTH, txt1, 150, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, txt1, 45, SpringLayout.EAST, nomuser);
        contentPane.add(password);
        layout.putConstraint(SpringLayout.WEST, password, 0, SpringLayout.WEST, nomuser);
        layout.putConstraint(SpringLayout.NORTH, password, 45, SpringLayout.SOUTH, txt1);
        contentPane.add(TXT2);
        layout.putConstraint(SpringLayout.NORTH, TXT2, 53, SpringLayout.SOUTH, txt1);
        layout.putConstraint(SpringLayout.WEST, TXT2, 0, SpringLayout.WEST, txt1);
        contentPane.add(login);
        layout.putConstraint(SpringLayout.NORTH, login, 15, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, login, 170, SpringLayout.WEST, contentPane);
        login.setFont(new java.awt.Font("Serif", 1, 30));
        login.setForeground(new java.awt.Color(162, 26, 26));
        nomuser.setFont(new java.awt.Font("Serif", 1, 18));
        password.setFont(new java.awt.Font("Serif", 1, 18));
        contentPane.add(connex);
        layout.putConstraint(SpringLayout.NORTH, connex, 350, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, connex, 160, SpringLayout.WEST, contentPane);
        connex.setFont(new java.awt.Font("Time new roman", 1, 18));
        connex.setForeground(new java.awt.Color(107, 99, 99));
        contentPane.setBackground(Color.CYAN);
        txt1.getBaseline(200, 20);
        setResizable(false);
        this.setVisible(true);

        //ACtion button se connecter
        connex.addActionListener(this);
    }



    public String[][] ConnectionLogin()

    {
        // la connection avec la base de donnéés
        ResultSet resultSet = null;
        String[][] res=new String[1][2];
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/gestionpharmacie", "root", ""
            );
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from admin");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));


            }
            res[0][0]=resultSet.getString(1);
            res[0][1]=resultSet.getString(2);

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return res;

    }
    public static void main(String[]args){
        Login login=new Login();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username=txt1.getText();
        String passwordd=TXT2.getText();
        String[][] log=new String[1][2];
        log=ConnectionLogin();
        if (log[0][0]==passwordd && log[0][1]==username){
            this.dispose();
            pagePrincipal page=new pagePrincipal();
        }
    }
}
