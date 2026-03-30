import javax.swing.*;
import java.util.Random;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class MainFrame extends JFrame{
    private JPanel MainFrame;
    private JTextField ArregloCampo;
    private JPanel ControlPanel;
    private  JPanel VisualizationPanel;
    private JComboBox comboAlgoritmos;
    private JButton generarAleatorioButton;
    private JButton aceptarButton;
    private JComboBox comboVelocidad;
    private JComboBox comboOrden;
    private JTabbedPane tabbedPane1;
    private JPanel ComparisionPanel;
    private JPanel IterativoPanel;
    private JPanel RecursivoPanel;
    private JButton detenerButton;
    private JButton reiniciarButton;
    private JComboBox comboBubble;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainFrame.class.getName());
    private DefaultCategoryDataset dataset;
    private JFreeChart chart;
    private static boolean ascendente = true;
    private int velocidad  = 0;
    private static String  algoritmo  = "";

    public MainFrame() {

        inicializarForma();
        aceptarButton.addActionListener(e -> iniciarAlgoritmo());
    }

    private void inicializarForma() {
        setContentPane(MainFrame);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        comboBubble.addActionListener(e -> iniciarAlgoritmo());
        comboOrden.addActionListener(e -> iniciarAlgoritmo());
        comboVelocidad.addActionListener(e -> iniciarAlgoritmo());
        comboAlgoritmos.addActionListener(e -> iniciarAlgoritmo());
    }

    private void iniciarAlgoritmo() {

        int arreglo[] = leerArreglo();
        generarAleatorioButton.addActionListener(e -> generarAleatorio());
        seleccionarOrden();
        seleccionarVelocidad();
        this.algoritmo = this.comboAlgoritmos.getSelectedItem().toString();

        switch (this.algoritmo) {
            case "Bubble":
                seleccionarAlgoritmo();
                new BubbleSortIterativo(IterativoPanel, arreglo, this.ascendente, this.velocidad);
                new BubbleSortRecursivo(RecursivoPanel, arreglo, this.ascendente, this.velocidad);
                break;
            case "Shell":
                ControlPanel.remove(comboBubble);
                new ShellSort(VisualizationPanel, arreglo, this.ascendente, this.velocidad);
                break;
            case "Quick":
                ControlPanel.remove(comboBubble);
                new QuickSort(VisualizationPanel, arreglo, this.ascendente, this.velocidad);
                break;
        }
    }

    private int[] leerArreglo(){

        String text = this.ArregloCampo.getText();
        String[] numeros = text.split(",");

        int[] arreglo = new int[numeros.length];

        for (int i = 0; i < numeros.length; i++) {
            arreglo[i] = Integer.parseInt(numeros[i].trim());
        }

        return arreglo;
    }

    private void seleccionarVelocidad(){
        String op = "";
        op = this.comboVelocidad.getSelectedItem().toString();

        switch(op){
            case "Lento":
                this.velocidad = 500;
                break;
            case "Medio":
                this.velocidad = 100;
                break;
            case "Rapido":
                this.velocidad = 20;
                break;
            default:
                this.velocidad = 100; // valor por defecto si algo falla
                break;
        }
    }

    private void seleccionarOrden(){
        String op = "";
        op = this.comboOrden.getSelectedItem().toString();

        switch(op){
            case "Ascendente":
                this.ascendente = true;
                break;
            case "Descendente":
                this.ascendente = false;
                break;
            default:
                this.ascendente = true; // valor por defecto si algo falla
                break;
        }
    }

    private void seleccionarAlgoritmo(){
        int arreglo[] = leerArreglo();

        String op = "";
        op = this.comboBubble.getSelectedItem().toString();
        switch(op){
            case "Iterativo":
                new BubbleSortIterativo(VisualizationPanel, arreglo, this.ascendente, this.velocidad);
                break;
            case "Recursivo":
                new BubbleSortRecursivo(VisualizationPanel, arreglo, this.ascendente, this.velocidad);
                break;
        }
    }

    private int[] generarAleatorio(){
        int n = 0;
        int arreglo[] = new int[8];
        Random rand = new Random();

        for(int i = 0; i<8; i++){
            n = rand.nextInt(80);
            arreglo[i] = n;
        }

        return arreglo;
    }

    public static void main(String[] args){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }

}
