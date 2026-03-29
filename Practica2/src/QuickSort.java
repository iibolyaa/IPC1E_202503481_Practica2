import javax.swing.*;

public class QuickSort extends ChartManager{

    public QuickSort(JPanel panel, int[] arreglo, boolean ascendente, int velocidad) {
        super(panel, arreglo, ascendente, velocidad);
    }


    @Override
    protected String getNombre() {
        return "QuickSort";
    }

    @Override
    protected void ordenar(int[] arr) {
        QuickSortAscendente(arr, 0, arr.length - 1);
    }

    private void QuickSortAscendente(int arreglo[], int low, int high) {
        if (low < high) {
            int pi = partition(arreglo, low, high);

            final int idPivote = pi;
            final int[] snap = arreglo.clone();
            final int id1= -1;
            final int id2 = -1;

            SwingUtilities.invokeLater(() -> {
                marcarPivote(snap, idPivote, id1, id2);
                lblEstado.setText("Pivote seleccionado: Índice " + idPivote);
            });

            pausa();


            QuickSortAscendente(arreglo, low, pi - 1);
            QuickSortAscendente(arreglo, pi + 1, high);
        }
    }

    private int partition(int[] arreglo, int low, int high) {
        // High y low son índices del arreglo

        int pivote = arreglo[high];
        int i = low - 1;

        final int idPivote = high;
        final int[] snap1 = arreglo.clone();

        SwingUtilities.invokeLater(() -> {
            marcarPivote(snap1, idPivote, -1, -1);
            lblEstado.setText("Pivote seleccionado en " + idPivote);
        });

        pausa();

        for (int j = low; j < high; j++) {
            if (!debeIntercambiar(arreglo[j], pivote)) {
                i++;

                int temp = arreglo[i];
                arreglo[i] = arreglo[j];
                arreglo[j] = temp;

                final int id1 = i;
                final int id2 = j;
                final int[] snap2 = arreglo.clone();

                SwingUtilities.invokeLater(() -> {
                    actualizarDatos(snap2, id1, id2);
                    lblEstado.setText("Comparando índice " + id1 + " con índice " + id2);
                });

                    pausa();
            }
        }


            final int posFinal = i + 1;

            final int[] snap3 = arreglo.clone();
            SwingUtilities.invokeLater(() -> {
                marcarPivote(snap3, high, posFinal, high);
                lblEstado.setText("Intercambiando pivote con índice " + posFinal);
            });

            pausa();


        //Intercambiar el pivote
        int temp = arreglo[i + 1];
        arreglo[i + 1] = arreglo[high];
        arreglo[high] = temp;

        final int id1 = i+1;
        final int[] snap4 = arreglo.clone();

        SwingUtilities.invokeLater(() -> {
            marcarPivote(snap4, id1, -1, -1);
            lblEstado.setText("Pivote reubicado en " + id1);
        });

        pausa();


        final int[] snapOrdenado = arreglo.clone();
        final int idOrdenado = posFinal;

        SwingUtilities.invokeLater(() -> {
            marcarOrdenado(snapOrdenado, idOrdenado);
            lblEstado.setText("Índice " + idOrdenado + " ya está ordenado");
        });

        pausa();

        // Regresa la posición final del pivote
        return posFinal;
    }

//    private void QuickSortDescendente(int arreglo[], int low, int high) {
//        if (low < high) {
//            int pi = partitionD(arreglo, low, high);
//
//            QuickSortDescendente(arreglo, low, pi - 1);
//            QuickSortDescendente(arreglo, pi + 1, high);
//        }
//    }
//
//    private int partitionD(int[] arreglo, int low, int high) {
//        int pivote = arreglo[high];
//        int i = low - 1;
//
//        for (int j = low; j < high; j++) {
//            if (arreglo[j] > pivote) {
//                i++;
//
//                int temp = arreglo[i];
//                arreglo[i] = arreglo[j];
//                arreglo[j] = temp;
//            }
//        }
//
//        //Intercambiar el pivote
//        int temp = arreglo[i + 1];
//        arreglo[i + 1] = arreglo[high];
//        arreglo[high] = temp;
//
//        // Regresa la posición final del pivote
//        return i + 1;
//    }


}
