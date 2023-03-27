package the_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class Add extends JFrame implements ActionListener {
    JTextField txt1,txt2,txt3,txt4,txt5;
    public Add(Component com){
        JLabel nomLabel = new JLabel("Nom :");
        txt1 = new JTextField(15);
        JLabel prixlabel = new JLabel("Prix:");
        txt2 = new JTextField(15);
        JLabel quantlabel = new JLabel("Quantité:");
        txt3 = new JTextField(15);
        JLabel datlabel = new JLabel("Date fin :");
        txt4 = new JTextField(15);
        JLabel ajoutProduit = new JLabel("Ajouter un produit ");
        JButton Produit = new JButton("Ajouter  ");
        JLabel idlabel = new JLabel("ID_Produit :");
        txt5 = new JTextField(15);
        this.setSize(600,600);
        Container contentPane = getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        contentPane.add(idlabel);

        layout.putConstraint(SpringLayout.NORTH,idlabel,100,SpringLayout.NORTH,contentPane);
        layout.putConstraint(SpringLayout.WEST,idlabel,0,SpringLayout.WEST,nomLabel);

        contentPane.add(txt5);
        layout.putConstraint(SpringLayout.NORTH,txt5,100,SpringLayout.NORTH,contentPane);
        layout.putConstraint(SpringLayout.WEST,txt5,0,SpringLayout.WEST,txt1);

        contentPane.add(nomLabel);
        layout.putConstraint(SpringLayout.NORTH,nomLabel,40,SpringLayout.SOUTH,idlabel);
        layout.putConstraint(SpringLayout.WEST,nomLabel,100,SpringLayout.WEST,contentPane);

        contentPane.add(txt1);
        layout.putConstraint(SpringLayout.NORTH,txt1,40,SpringLayout.SOUTH,idlabel);
        layout.putConstraint(SpringLayout.WEST,txt1,95,SpringLayout.EAST,nomLabel);

        contentPane.add(prixlabel);
        layout.putConstraint(SpringLayout.NORTH,prixlabel,40,SpringLayout.SOUTH,nomLabel);
        layout.putConstraint(SpringLayout.WEST,prixlabel,0,SpringLayout.WEST,nomLabel);

        contentPane.add(txt2);
        layout.putConstraint(SpringLayout.NORTH,txt2,40,SpringLayout.SOUTH,txt1);
        layout.putConstraint(SpringLayout.WEST,txt2,0,SpringLayout.WEST,txt1);

        contentPane.add(quantlabel);
        layout.putConstraint(SpringLayout.NORTH,quantlabel,40,SpringLayout.SOUTH,prixlabel);
        layout.putConstraint(SpringLayout.WEST,quantlabel,0,SpringLayout.WEST,nomLabel);

        contentPane.add(txt3);
        layout.putConstraint(SpringLayout.NORTH,txt3,40,SpringLayout.SOUTH,txt2);
        layout.putConstraint(SpringLayout.WEST,txt3,0,SpringLayout.WEST,txt2);

        contentPane.add(datlabel);
        layout.putConstraint(SpringLayout.NORTH,datlabel,40,SpringLayout.SOUTH,quantlabel);
        layout.putConstraint(SpringLayout.WEST,datlabel,0,SpringLayout.WEST,quantlabel);

        contentPane.add(txt4);
        layout.putConstraint(SpringLayout.NORTH,txt4,40,SpringLayout.SOUTH,txt3);
        layout.putConstraint(SpringLayout.WEST,txt4,0,SpringLayout.WEST,txt3);



        contentPane.add(ajoutProduit);
        layout.putConstraint(SpringLayout.WEST,ajoutProduit,200,SpringLayout.WEST,contentPane);
        layout.putConstraint(SpringLayout.NORTH,ajoutProduit,30,SpringLayout.NORTH,contentPane);

        ajoutProduit.setFont(new java.awt.Font("Serif", 1, 22)); // NOI18N
        ajoutProduit.setForeground(new java.awt.Color(162, 26, 26));
        contentPane.add(Produit);
        layout.putConstraint(SpringLayout.WEST,Produit,230,SpringLayout.WEST,contentPane);
        layout.putConstraint(SpringLayout.NORTH,Produit,100,SpringLayout.SOUTH,txt4);

        Produit.setFont(new java.awt.Font("Serif", 1, 22));
        Produit.setForeground(new java.awt.Color(162, 26, 26, 199));
        Produit.setFocusable(false);
        Produit.addActionListener(this);
        contentPane.setBackground(Color.CYAN);
        nomLabel.setFont(new java.awt.Font("Serif", 1, 15));
        prixlabel.setFont(new java.awt.Font("Serif", 1, 15));
        quantlabel.setFont(new java.awt.Font("Serif", 1, 15));
        datlabel.setFont(new java.awt.Font("Serif", 1, 15));
        idlabel.setFont(new java.awt.Font("Serif", 1, 15));
        setResizable(false);
        com.setEnabled(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);



    }
  public int[] Insert() {
      // la connection avec la base de donnéés
      int[] s = new int[0];
      try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection connection = DriverManager.getConnection(
                  "jdbc:mysql://localhost:3306/gestionpharmacie", "root", ""
          );
          Statement statement = connection.createStatement();
          statement.addBatch("INSERT INTO `themain`(`Id`, `Nom`, `Prix`, `Quantity`, `Date_fin`) VALUES ('" + txt5.getText() + "','" + txt1.getText() + "','" + txt2.getText() + "','" + txt3.getText() + "','" + txt4.getText() + "')");
          s = statement.executeBatch();

          connection.close();
      } catch (Exception e) {
          System.out.println(e);
      }
      return s;
  }

    @Override
    public void actionPerformed(ActionEvent e) {

       if (Insert()!=null){
           JOptionPane.showMessageDialog(null,"Un medicament ajouter","L'opération a réussi",JOptionPane.PLAIN_MESSAGE);
           txt1.setText("");
           txt2.setText("");
           txt3.setText("");
           txt4.setText("");
           txt5.setText("");
       }


    }
}
