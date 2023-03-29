package the_project;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import java.util.Vector;

public class pagePrincipal extends JFrame  implements ActionListener,KeyListener,MouseListener,FocusListener,WindowListener {
    String[] vec;
    JButton recherche_Icon,acheter;
    JTextField recherche,nbr;
    JButton addMedicament;
    JLabel delete;
    JMenuBar menuBar;
    JMenu btn;
    Add ajouter;
    JMenuItem stat,notification,supTout;
    Border border=BorderFactory.createLineBorder(Color.black,1);
    Border textFieldBorder=BorderFactory.createLineBorder(Color.black,1);
    //Border textFieldBorder=BorderFactory.createTitledBorder(border,"Recherche",2,2,new Font(null,Font.ITALIC,15));
    Border tableBorder=BorderFactory.createLineBorder(Color.black,2);
    JPanel panel1,panel2,panel3;
    JLayeredPane lP;
    JTable medicament=new JTable();
    JTableHeader theader;
    DefaultTableModel model;
    String[][] a;
    public void tableData(String [][] meds){
        a=meds;
        String [] header={"Nom","Prix","Quantity","Date de fin"};
//        for (String[] r:
//                FillTheTable.showData() ) {
//            for (Object g:
//                 r) {
//                System.out.println(g);
//            }
//
//        }
        model= (DefaultTableModel) medicament.getModel();
        model.setDataVector(a,header);
//        medicament=new JTable(meds,header);


    }
    pagePrincipal() {
        this.setSize(1000,550);
        this.setTitle("Accueil");
        this.setDefaultCloseOperation(pagePrincipal.EXIT_ON_CLOSE);
        this.setResizable(false);
        ImageIcon icon=new ImageIcon("download.png");
        this.setIconImage(icon.getImage());
        //this.getContentPane().setBackground(Color.CYAN);
        this.setLayout(null);
        this.addWindowListener(this);

        recherche=new JTextField();
        recherche.requestFocus(false);
        recherche.setText(" Rechercher");
        recherche.setBounds(300,0,350,50);
        recherche.setBackground(new Color(255, 255, 255));
        recherche.setMargin(new Insets(10,100,10,10));
        recherche.setFont(new Font(null,Font.PLAIN,16));
        recherche.setForeground(Color.lightGray);
        recherche.setBorder(textFieldBorder);
        recherche.addKeyListener(this);
        recherche.addFocusListener(this);

        ImageIcon rechercheIcon=new ImageIcon("magnifyingGlass.png");
        recherche_Icon=new JButton();
        recherche_Icon.setBounds(650,0,50,50);
        recherche_Icon.setIcon(rechercheIcon);
        recherche_Icon.setBackground(Color.white);
        recherche_Icon.setBorder(textFieldBorder);
        recherche_Icon.setFocusable(false);
        recherche_Icon.addActionListener(this);

        addMedicament=new JButton();
        addMedicament.setBounds(5,30,50,50);
        addMedicament.setFocusable(false);
        //addMedicament.setBorder(null);
        addMedicament.setText("+");
        addMedicament.setToolTipText("ajouter un medicament");
        addMedicament.setFont(new Font(null,Font.BOLD,26));
        addMedicament.setBackground(null);
        addMedicament.setFocusable(false);
        addMedicament.addActionListener(this);

        menuBar=new JMenuBar();
        menuBar.setBackground(Color.BLACK);


        btn = new JMenu("...");
        //btn.setBounds(800,0,10,50);
        btn.setMargin(new Insets(10,10,10,10));
        btn.setFocusable(false);
        btn.setBorder(null);
        btn.setFont(new Font(null,Font.BOLD,30));
        btn.setForeground(Color.WHITE);
        btn.setBackground(null);
        notification=new JMenuItem("Notification");
        stat =new JMenuItem("Statistique");
        supTout =new JMenuItem("Supprimer tout");
        supTout.addActionListener(this);
        btn.add(stat);
        btn.addSeparator();
        btn.add(notification);
        btn.addSeparator();
        btn.add(supTout);
        btn.setVerticalTextPosition(JMenu.CENTER);
        menuBar.add(btn);

            this.tableData(FillTheTable.showData());


        theader=medicament.getTableHeader();
        theader.setFont(new Font("Tahome",Font.BOLD,20));
        theader.setSize(40,40);
        medicament.setFont(new Font(null,Font.PLAIN,15));
        medicament.setSize(900,500);
        medicament.setRowHeight(40);
        medicament.setShowGrid(true);
        medicament.addMouseListener(this);
        JScrollPane s=new JScrollPane(medicament);
        s.setBorder(tableBorder);

        ImageIcon deleteIcon=new ImageIcon("delete.png");
        delete=new JLabel();
        delete.setIcon(deleteIcon);
        delete.addMouseListener(this);

        panel1=new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(50,60,900,90);
        //panel1.setBackground(Color.CYAN);
        //btn.setVerticalAlignment(JButton.RIGHT);
        panel1.add(addMedicament);
        panel1.add(recherche);
        panel1.add(recherche_Icon);
        //panel1.add(btn);


        panel2=new JPanel();
        panel2.setLayout(new BorderLayout(20,0));
        panel2.setBounds(50,155,900,200);
        //panel2.setBackground(Color.red);
        panel2.add(s);
        panel3=new JPanel();
        panel3.setLayout(null);
        panel3.setBounds(50,360,900,90);
        //panel3.setBackground(Color.red);

        acheter=new JButton();
        acheter.setText("Acheter");
        acheter.setBounds(400,25,100,30);
        acheter.setFocusable(false);
        acheter.addActionListener(this);
        nbr=new JTextField();
        nbr.setBounds(500,25,50,30);
        nbr.setToolTipText("la quantité de medicament");
        panel3.add(acheter);
        panel3.add(nbr);

        this.add(delete);

        this.setJMenuBar(menuBar);
        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.setVisible(true);
    }

    //public static void main(String[] args) {
        //pagePrincipal p=new pagePrincipal();
    //}

    @Override
    public void actionPerformed(ActionEvent e) {
        delete.setVisible(false);
       if(e.getSource()==recherche_Icon){
           String text= recherche.getText();
           if (!Objects.equals(text, "") && !Objects.equals(text, " Rechercher")) {
               String s="Nom";
               String [][] med= FillTheTable.recherche(text,s);
               this.tableData(med);
               System.out.println("tableChange");
           }

       }
        if(e.getSource()==supTout){
          int supORNo=JOptionPane.showConfirmDialog(null,"Êtes-vous sûr de vouloir effacer définitivement tous les médicaments ? ","Suppremer tout les medecaments",2);

            if ( supORNo==0) {
                FillTheTable.delete();
                this.tableData(null);
            }
        }
        if(e.getSource()==addMedicament){
            vec=new String[5];
            if (medicament.getSelectedRow()!=-1){

                for (int i = 0; i <5 ; i++) {
                    vec[i]=a[medicament.getSelectedRow()][i] ;
                    System.out.println(vec);
                }
            }
           ajouter=new Add(addMedicament,vec);
           ajouter.addWindowListener(this);
        }
        if (e.getSource()==acheter){
            if (medicament.getSelectedRow()!=-1 && !Objects.equals(nbr.getText(), "") && Integer.parseInt(nbr.getText())<=Integer.parseInt(a[medicament.getSelectedRow()][2]) ){
                int supORNo=JOptionPane.showConfirmDialog(null,"Voulez-vous acheter ce médicament ? ","Confirmed",2);
                if ( supORNo==0)
                {
                    FillTheTable.acheter(Integer.parseInt(nbr.getText()),a[medicament.getSelectedRow()][4], Integer.parseInt(a[medicament.getSelectedRow()][2]));
                    this.tableData(FillTheTable.showData());

                }
                nbr.setText("");
                System.out.println("yes");
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() ==8) {
            if (Objects.equals(recherche.getText(), "")) {
                this.tableData(FillTheTable.showData());
                System.out.println("theMainTableBack");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       if (e.getSource()==delete){
           int supORNo=JOptionPane.showConfirmDialog(null,"Êtes-vous sûr de vouloir effacer définitivement cette médicament ? ","Suppremer le medecament",2);
           System.out.println(medicament.getSelectedRow());
           if ( supORNo==0) {
               String id= a[medicament.getSelectedRow()][4];

               System.out.println(id);
               FillTheTable.deleteRow(id);
               this.tableData(FillTheTable.showData());
           }
               delete.setVisible(false);
       }
    }

    @Override
    public void mousePressed(MouseEvent e) {
     if (e.getSource()==medicament){
         if (SwingUtilities.isRightMouseButton(e)){
             if (medicament.getSelectedRow()!=-1) {
                 if (e.getY()+150<=650)
                 { delete.setBounds(10,e.getYOnScreen()-90,32,32);
                     delete.setVisible(true);
                 }


             }

         }
         else{
             delete.setBounds(10,510,32,32);
             delete.setVisible(false);
         }
     }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {
        if (Objects.equals(recherche.getText(), " Rechercher")){
                recherche.setText("");
                recherche.requestFocus();
                recherche.setForeground(Color.BLACK);

            }

    }

    @Override
    public void focusLost(FocusEvent e) {
        if (Objects.equals(recherche.getText(), "")){
            recherche.setText(" Rechercher");
            recherche.setForeground(Color.lightGray);

        }

    }

    int count=0;
    @Override
    public void windowOpened(WindowEvent e) {
        if (e.getSource()==this){
            nbr.requestFocus();

            String [][] medec= FillTheTable.recherche("0","Quantity");
            for (String[] g:medec) {

                String[][] b=FillTheTable.recherche(g[0],"Nom");

                if(b.length>1){
                    for (String[] n: b) {


                        System.out.println(g[4].substring(0,7));
                        if (n[4].contains(g[4].substring(0,7))){
                            count++;

                        }
                    }

                    if (count>1){

                        FillTheTable.deleteRow(g[4]);
                        this.tableData(FillTheTable.showData());
                        count=0;
                    }


                }


            }


        }
    }
    @Override
    public void windowClosing(WindowEvent e) {
        if (e.getSource()==ajouter){
            addMedicament.setEnabled(true);
            this.tableData(FillTheTable.showData());
        }


    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
