package BDD;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class addTagToTableTag extends JFrame implements ActionListener {

    JTextField j1 = new JTextField();
    JButton b1 = new JButton("valider");
    JButton b2 = new JButton("ajouter");

    public addTagToTableTag() {


        this.setSize(500, 100);

        this.setLayout(new FlowLayout());

        this.add(new JLabel("tag1"));
        j1.setPreferredSize(new Dimension(100, 20));
        this.add(j1);
        this.add(b2);
        this.add(b1);
        b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                addBDD(j1.getText());
                j1.setText("");

            }
        });
        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                addBDD(j1.getText());
                dispose();
            }
        });
        this.setDefaultCloseOperation(1);
        this.setLocationRelativeTo(null);


    }

    protected void addBDD(String tag1) {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");


            c = DriverManager.getConnection("jdbc:sqlite:model.db");
            c.setAutoCommit(false);
            Statement stmt = c.createStatement();
            String sql;


            if (!tag1.equals("")) {
                sql = "INSERT INTO Tags  "
                        + "VALUES ('" + tag1 + "');";
                try {
                    stmt.executeUpdate(sql);
                } catch (Exception e) {
                }

            }
            stmt.close();
            c.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                c.close();
            } catch (Exception e2) {
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.setVisible(true);

    }
}
