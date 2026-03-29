import javax.swing.*;

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

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainFrame.class.getName());
    private DefaultCategoryDataset dataset;
    private JFreeChart chart;
    private static boolean ascendente = true;
    private int velocidad  = 0;
    private static String  algoritmo  = "";

    public MainFrame() {
        inicializarForma();
        iniciarAlgoritmo();
    }

    private void inicializarForma() {
        setContentPane(MainFrame);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        comboOrden.addActionListener(e -> iniciarAlgoritmo());
        comboVelocidad.addActionListener(e -> iniciarAlgoritmo());
        comboAlgoritmos.addActionListener(e -> iniciarAlgoritmo());
    }

    private void iniciarAlgoritmo() {
        seleccionarOrden();
        seleccionarVelocidad();
        int[] arreglo = {64, 25, 12, 22, 11, 90, 45, 33};
        this.algoritmo = this.comboAlgoritmos.getSelectedItem().toString();

        switch (this.algoritmo) {
            case "Bubble":
                new BubbleSortIterativo(VisualizationPanel, arreglo, this.ascendente, this.velocidad);
                break;
            case "Quick":
                new QuickSort(VisualizationPanel, arreglo, this.ascendente, this.velocidad);
                break;
            case "Shell":
                new ShellSort(VisualizationPanel, arreglo, this.ascendente, this.velocidad);
                break;
        }
    }

    private void leerArreglo(){

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
