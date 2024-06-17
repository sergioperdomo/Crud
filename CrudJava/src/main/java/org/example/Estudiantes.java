package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


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
    Statement st;
    ResultSet r;
    DefaultListModel mod = new DefaultListModel();


    public Estudiantes() {
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    insertar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void listar() throws SQLException {
        conectar();
        lista.setModel(mod);
        st = connection.createStatement(); // Vamos a tener internamente toda la información de la consulta
        r = st.executeQuery("SELECT id_estudiante, nombre,apellido FROMA estudiante"); // Se mostrara la información que estamos pasando por la Query
        mod.removeAllElements();
        // Mostrando información en la parte visual de los tres elementos que colocamos en la Query
        while (r.next()){
            mod.addElement(r.getString(1) + "" + r.getString(2) + "" + r.getString(3));
        }
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

            idText.setText("");
            nombreText.setText("");
            apellidoText.setText("");
            edadText.setText("");
            telText.setText("");
            direccionText.setText("");
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
