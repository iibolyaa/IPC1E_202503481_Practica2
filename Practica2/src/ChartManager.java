import java.awt.*;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

public abstract class ChartManager {
    protected DefaultCategoryDataset datos; //Establecer los valores del gráfico
    protected JLabel lblEstado;
    protected JButton btnOrdenar;
    protected JPanel panel;
    protected JButton btnDetener;
    protected JButton btnReiniciar;


    protected int[] arreglo;
    protected int velocidad;
    protected boolean ascendente;

    private Thread hilo;
    private volatile boolean detenido = false;

    public ChartManager(JPanel panel, int[] arreglo, boolean ascendente, int velocidad) {
        this.panel      = panel;
        this.arreglo    = arreglo;
        this.ascendente = ascendente;
        this.velocidad  = velocidad;
        inicializarFormaData();
    }

    //Creación del gráfico
    private void inicializarFormaData() {
        datos = new DefaultCategoryDataset();

        actualizarDatos(arreglo.clone(), -1, -1);

        String titulo = getNombre() + " (" + (ascendente ? "ASC" : "DESC") + ") ";

        JFreeChart grafica = ChartFactory.createBarChart(
                titulo, "Indice", "Valor", datos
        );

        CategoryPlot plot = grafica.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(70, 130, 180));  // normal
        renderer.setSeriesPaint(1, new Color(255, 248, 0));   // comparando
        renderer.setSeriesPaint(2, new Color(220, 50, 50));   // ordenado
        renderer.setSeriesPaint(3, new Color(0x7E0DCA));   //pivote

        renderer.setShadowVisible(false);
        renderer.setBarPainter(new StandardBarPainter());

        ChartPanel chartPanel = new ChartPanel(grafica);

        lblEstado = new JLabel("Presiona para Iniciar", SwingConstants.CENTER);

        btnOrdenar = new JButton("Iniciar Ordenamiento");
        btnOrdenar.addActionListener(e -> {
            reiniciar();
            btnOrdenar.setEnabled(false);
            btnDetener.setEnabled(true);
            btnReiniciar.setEnabled(false);

            if (ascendente) {
                lblEstado.setText("Ordenando ascendente...");
            } else {
                lblEstado.setText("Ordenando descendente...");
            }

            iniciarOrdenamiento(arreglo.clone());
        });

        btnDetener = new JButton("Detener");
        btnDetener.setEnabled(false);
        btnDetener.addActionListener(e -> {
            detener();
            btnDetener.setEnabled(false);
            btnReiniciar.setEnabled(true);
            lblEstado.setText("Detenido");
        });

        btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.setEnabled(false);
        btnReiniciar.addActionListener(e -> {
            reiniciar();
            actualizarDatos(arreglo.clone(), -1, -1);
            btnOrdenar.setEnabled(true);
            btnDetener.setEnabled(false);
            btnReiniciar.setEnabled(false);
            lblEstado.setText("Presiona Iniciar");
        });

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panelBotones.add(btnOrdenar);
        panelBotones.add(btnDetener);
        panelBotones.add(btnReiniciar);


        JPanel panelSur = new JPanel(new BorderLayout(5, 5));
        panelSur.add(lblEstado,  BorderLayout.NORTH);
        panelSur.add(panelBotones, BorderLayout.SOUTH);

        panel.removeAll();
        panel.setLayout(new BorderLayout());
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.add(panelSur,   BorderLayout.SOUTH);
        panel.setPreferredSize(new Dimension(600, 400));
        panel.revalidate();
        panel.repaint();
    }

    private void iniciarOrdenamiento(int[] copia) {
        hilo = new Thread(() -> {
            ordenar(copia);

            if(!estaDetenido()) {
                SwingUtilities.invokeLater(() -> {
                    actualizarDatos(copia, -1, -1);
                    btnOrdenar.setEnabled(true);
                    btnOrdenar.setText("Ordenar de nuevo");
                    lblEstado.setText("Ordenado con " + getNombre());
                });
            }
        });


        hilo.setDaemon(true);
        hilo.start();
    }

    // Actualizar grafico
    protected void actualizarDatos(int[] arr, int id1, int id2) {
        datos.clear();

        for (int i = 0; i < arr.length; i++) {
            String etiqueta = "Indice " + i;
            if (i == id1 || i == id2) {
                datos.addValue(arr[i], "Comparando", etiqueta);
                datos.addValue(null,   "Valores",    etiqueta);
                datos.addValue(null,   "Ordenado",   etiqueta);
            } else {
                datos.addValue(null,   "Comparando", etiqueta);
                datos.addValue(arr[i], "Valores",    etiqueta);
                datos.addValue(null,   "Ordenado",   etiqueta);
            }
        }
    }

    protected void marcarOrdenado(int[] arr, int idOrdenado) {
        datos.clear();
        for (int i = 0; i < arr.length; i++) {
            String etiqueta = "Indice " + i;
            if (i == idOrdenado) {
                datos.addValue(null,   "Comparando", etiqueta);
                datos.addValue(null,   "Valores",    etiqueta);
                datos.addValue(arr[i], "Ordenado",   etiqueta);
            } else {
                datos.addValue(null,   "Comparando", etiqueta);
                datos.addValue(arr[i], "Valores",    etiqueta);
                datos.addValue(null,   "Ordenado",   etiqueta);
            }
        }
    }

    protected void marcarPivote(int[] arr, int idPivote, int id1, int id2) {
        datos.clear();
        for(int i = 0; i < arr.length; i++) {
            String etiqueta = "Indice " + i;
            if (i == idPivote) {
                datos.addValue(null, "Comparando", etiqueta);
                datos.addValue(null, "Valores", etiqueta);
                datos.addValue(null, "Ordenado", etiqueta);
                datos.addValue(arr[i], "Pivote", etiqueta);
            }else if (i == id1 || i == id2) {
                    datos.addValue(arr[i], "Comparando", etiqueta);
                    datos.addValue(null, "Valores", etiqueta);
                    datos.addValue(null, "Ordenado", etiqueta);
                    datos.addValue(null, "Pivote", etiqueta);
            } else{
                datos.addValue(null, "Comparando", etiqueta);
                datos.addValue(arr[i],   "Valores",    etiqueta);
                datos.addValue(null,   "Ordenado",   etiqueta);
                datos.addValue(null, "Pivote", etiqueta);
            }
        }
    }

    // Comparacion de intercambio
    protected boolean debeIntercambiar(int a, int b) {
        if (ascendente) {
            return a > b;
        } else {
            return a < b;
        }
    }


    protected void pausa() {
        try {
            if (detenido) {
                Thread.currentThread().interrupt();
                return;
            }
            Thread.sleep(velocidad);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    public void detener() {
        detenido = true;
    }

    public void reiniciar() {
        detenido = false;
    }

    public boolean estaDetenido() {
        return detenido;
    }
    protected abstract String getNombre();
    protected abstract void ordenar(int[] arr);
}


