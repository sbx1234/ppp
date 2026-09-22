/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba;

import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Tati
 */
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TablaBDMio extends JTable{
      public TablaBDMio() {
        super();
    }

    public void mostrarColumna(String base, String tabla, String columna) {

        try {

            DefaultTableModel modelo = new DefaultTableModel();

            modelo.addColumn(columna);

            conexion cn = new conexion();

            Connection cc = cn.conectar(base);
            if (cc == null) {
               return;
            }

            String sql = "SELECT " + columna + " FROM " + tabla;
            Statement st = cc.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Object[] fila = new Object[1];

                fila[0] = rs.getObject(columna);

                modelo.addRow(fila);
            }

            this.setModel(modelo);

            this.setRowHeight(25);

            this.getTableHeader().setReorderingAllowed(false);

            pintarCeldas();

            rs.close();
            st.close();
            cc.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al mostrar datos: " + e.getMessage()
            );
        }
    }

    public void pintarCeldas() {

        this.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {

                Component c = super.getTableCellRendererComponent(
                        table,
                        value,
                        isSelected,
                        hasFocus,
                        row,
                        column
                );

                if (isSelected) {

                    c.setBackground(table.getSelectionBackground());
                    c.setForeground(table.getSelectionForeground());

                } else {

                    if (value instanceof Number) {

                        double numero = ((Number) value).doubleValue();

                        if (numero >= 7) {

                            // Mayor a 7 = VERDE
                            c.setBackground(Color.GREEN);
                            c.setForeground(Color.BLACK);

                        } else if (numero < 7) {

                            // Menor a 7 = ROJO
                            c.setBackground(Color.RED);
                            c.setForeground(Color.WHITE);

                        } else {

                            // Igual a 7 = color normal
                            c.setBackground(Color.WHITE);
                            c.setForeground(Color.BLACK);
                        }

                    } else {

                        c.setBackground(Color.WHITE);
                        c.setForeground(Color.BLACK);
                    }
                }

                return c;
            }
        });
    }
}
/*
CLASE JFRAME
 public void cargarTabla() {

        tablaBDMio1.mostrarColumna(
                "nota",
                "estudiantes",
                "nota"
        );
    }
CONEXION
package prueba;

import javax.swing.JOptionPane;
import java.sql.*;

public class conexion {

    public Connection conectar(String base) {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + base,"root","");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error de conexión: " + ex.getMessage()
            );
        }

        return conn;
    }
}

SCRITP BD
-- Crear la nueva base de datos
CREATE DATABASE notas;

-- Seleccionar la base de datos
USE notas;

-- Crear la tabla estudiantes
CREATE TABLE estudiantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    nota DECIMAL(4,2) NOT NULL
);

-- Insertar datos
INSERT INTO estudiantes (nombre, apellido, nota) VALUES
('Juan', 'Perez', 9.00),
('Ana', 'Lopez', 6.00),
('Pedro', 'Gomez', 8.00),
('Maria', 'Torres', 5.00),
('Luis', 'Villacis', 7.00),
('Carlos', 'Mendoza', 10.00),
('Sofia', 'Castillo', 4.00),
('Daniel', 'Rodriguez', 8.50);

-- Verificar los datos
SELECT * FROM estudiantes;

RECUPERAR NOMBRE APELLIDO
public void mostrarColumnas(String base, String tabla) {
    try {
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");

        conexion cn = new conexion();
        Connection cc = cn.conectar(base);

        if (cc == null) {
            return;
        }

        String sql = "SELECT nombre, apellido FROM " + tabla;

        Statement st = cc.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            Object[] fila = new Object[2];

            fila[0] = rs.getString("nombre");
            fila[1] = rs.getString("apellido");

            modelo.addRow(fila);
        }

        this.setModel(modelo);
        this.setRowHeight(25);
        this.getTableHeader().setReorderingAllowed(false);

        rs.close();
        st.close();
        cc.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                null,
                "Error al mostrar datos: " + e.getMessage()
        );
    }
}

JFRAME
public void cargarTabla() {
    tablaBDMio1.mostrarColumnas(
            "notas",
            "estudiantes"
    );
}


*/