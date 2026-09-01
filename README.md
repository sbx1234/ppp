package clase01;

import java.util.ArrayList;

public class Numero {

    private int numero;

    private static ArrayList<Numero> numeros = new ArrayList<>();

    // Constructor vacío
    public Numero() {
    }

    // Constructor con parámetro
    public Numero(int numero) {
        this.numero = numero;
    }

    // Get y Set
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    // Método para saber si es par
    public boolean esPar() {
        return numero % 2 == 0;
    }

    // Método para saber si es impar
    public boolean esImpar() {
        return numero % 2 != 0;
    }

    // Método para guardar
    public void guardar() {
        numeros.add(this);
    }

    // Método para eliminar
    public void eliminar() {
        numeros.remove(this);
    }

    // Método para comprobar si el número ya existe
    public static boolean numeroYaExiste(int numero) {

        for (Numero n : numeros) {

            if (n.getNumero() == numero) {
                return true;
            }
        }

        return false;
    }

    // Obtener todos los objetos
    public static ArrayList<Numero> getNumeros() {
        return numeros;
    }
}
package clase01;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class prueba extends javax.swing.JFrame {

    DefaultTableModel modeloTabla;
    DefaultListModel<String> modeloLista;

    // Objeto Numero
    private Numero numero;

    // Vector original
    int vector[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public prueba() {

        initComponents();

        inicializarTabla();
        inicializarLista();
        cargarCombo();
    }

    // =========================
    // INICIALIZAR TABLA
    // =========================

    public void inicializarTabla() {

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("Pares");
        modeloTabla.addColumn("Impares");

        jtblClasificacion.setModel(modeloTabla);
    }

    // =========================
    // INICIALIZAR LISTA
    // =========================

    public void inicializarLista() {

        modeloLista = new DefaultListModel<>();

        jlstResultado.setModel(modeloLista);
    }

    // =========================
    // CARGAR COMBO
    // =========================

    private void cargarCombo() {

        jcbxSeleccionarNumero.removeAllItems();

        jcbxSeleccionarNumero.addItem("Seleccione");

        for (int i = 0; i < vector.length; i++) {

            jcbxSeleccionarNumero.addItem(
                    String.valueOf(vector[i])
            );
        }

        jcbxSeleccionarNumero.setSelectedIndex(0);
    }

    // =========================
    // PROCESAR SELECCIÓN
    // =========================

    private void procesarSeleccion() {

        if (jcbxSeleccionarNumero.getSelectedItem() == null) {
            return;
        }

        if (jcbxSeleccionarNumero.getSelectedIndex() <= 0) {
            return;
        }

        int valor = Integer.parseInt(
                jcbxSeleccionarNumero
                        .getSelectedItem()
                        .toString()
        );

        // CREAR OBJETO
        numero = new Numero(valor);

        // COMPROBAR SI YA EXISTE
        if (Numero.numeroYaExiste(valor)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Número repetido"
            );

            return;
        }

        // UTILIZAR MÉTODO DEL OBJETO
        numero.guardar();

        // MOSTRAR EN LA INTERFAZ
        agregarATabla(numero);
        agregarALista(numero);
    }

    // =========================
    // AGREGAR A TABLA
    // =========================

    private void agregarATabla(Numero numero) {

        Object[] fila;

        if (numero.esPar()) {

            fila = new Object[]{
                numero.getNumero(),
                ""
            };

        } else {

            fila = new Object[]{
                "",
                numero.getNumero()
            };
        }

        modeloTabla.addRow(fila);
    }

    // =========================
    // AGREGAR A LISTA
    // =========================

    private void agregarALista(Numero numero) {

        String texto;

        if (numero.esPar()) {

            texto = numero.getNumero() + " Par";

        } else {

            texto = numero.getNumero() + " Impar";
        }

        modeloLista.addElement(texto);
    }

    // =========================
    // LIMPIAR
    // =========================

    private void limpiarTodo() {

        modeloTabla.setRowCount(0);

        modeloLista.clear();

        jcbxSeleccionarNumero.setSelectedIndex(0);
    }

    // =========================
    // EVENTO DEL COMBO
    // =========================

    private void jcbxSeleccionarNumeroActionPerformed(
            java.awt.event.ActionEvent evt) {

        procesarSeleccion();
    }

    // =========================
    // AQUÍ VA initComponents()
    // =========================

    /*
       NetBeans genera automáticamente
       initComponents() y las variables
       de los componentes.

       NO debes modificar esa parte.
    */
}


