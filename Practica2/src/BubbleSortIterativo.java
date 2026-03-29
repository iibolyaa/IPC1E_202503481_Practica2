import javax.swing.*;

public class BubbleSortIterativo extends ChartManager {

    public BubbleSortIterativo(JPanel panel, int[] arreglo, boolean ascendente, int velocidad) {
        super(panel, arreglo, ascendente, velocidad);
    }

    @Override
    protected String getNombre() {
        return "Bubble Sort";
    }

    @Override
    protected void ordenar(int[] arr) {
        BubbleSortIterativo(arr);
    }

    public void BubbleSortIterativo(int arreglo[]) {
        int temp = 0;

        for (int i = 0; i < arreglo.length - 1; i++) {
            for (int j = 0; j < arreglo.length - i - 1; j++) {

                final int id1 = j;
                final int id2 = j + 1;
                final int[] snap = arreglo.clone();

                SwingUtilities.invokeLater(() -> {
                    actualizarDatos(snap, id1, id2);
                    lblEstado.setText("Comparando Indice " + id1 + " con Indice " + id2);
                });

                pausa();

                if (debeIntercambiar(arreglo[j],arreglo[j+1])){

                    temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;

                    final int[] snap2 = arreglo.clone();

                    SwingUtilities.invokeLater(() -> {
                        actualizarDatos(snap2, id1, id2);
                        lblEstado.setText("Intercambio en Indice " + id1 + " con Indice " + id2);
                    });

                    pausa();
                }
            }

            final int idOrdenado = arreglo.length - i - 1;
            final int[] snap3 = arreglo.clone();

            SwingUtilities.invokeLater(() -> {
                marcarOrdenado(snap3, idOrdenado);
                lblEstado.setText("Posición con Indice " + idOrdenado + " ya ordenada");
            });

            pausa();
        }

        final int[] snapFinal = arreglo.clone();

        SwingUtilities.invokeLater(() -> {
            marcarOrdenado(snapFinal, 0);
            lblEstado.setText("Ordenamiento completado");
        });
    }

}
