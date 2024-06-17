package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class Estudiantes extends  JFrame{
    private JPanel panel;
    private JTextField telText;
    private JTextField apellidoText;
    private JTextField edadText;
    private JTextField direccionText;
    private JButton ingresarButton;
    private JButton consultarButton;
    private JList lista;
    private JTextField idText;
    private JTextField nombreText;

    Connection connection;
    PreparedStatement ps;
    DefaultListModel mod = new DefaultListModel();

    public Estudiantes() {
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conectar();
            }
        });
    }

    public void listar(){
        conectar();
    }

    public void insertar() throws SQLException {
        conectar();
        ps = connection.prepareStatement("INSERT INTO estudiante VALUES (?,?,?,?,?,?)");
        ps.setInt(1, Integer.parseInt(idText.getText()));
        ps.setString(2, nombreText.getText());
        ps.setString(3, apellidoText.getText());
        ps.setInt(4, Integer.parseInt(edadText.getText()));
        ps.setString(5, telText.getText());
        ps.setString(6, direccionText.getText());

        if (ps.executeUpdate() > 0){
            lista.setModel(mod);
            mod.removeAllElements();
            mod.addElement("¡Inserción Exitosa!");
        }

    }



    public static void main (String[] args){
        Estudiantes f =  new Estudiantes();
        f.setContentPane(new Estudiantes().panel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.pack();
    }


    public void conectar (){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/learning", "root", "");
            System.out.println("Conectado!!!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
