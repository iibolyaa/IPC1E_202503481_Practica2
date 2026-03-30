import javax.swing.*;

public class BubbleSortRecursivo extends ChartManager{

    public BubbleSortRecursivo(JPanel panel, int[] arreglo, boolean ascendente, int velocidad) {
        super(panel, arreglo, ascendente, velocidad);
    }

    @Override
    protected String getNombre() {
        return "Bubble Sort Recursivo";
    }

    @Override
    protected void ordenar(int[] arr) {
        BubbleSortRecursivo(arr, arreglo.length);
    }


    private void BubbleSortRecursivo(int arreglo[], int n){
        int temp = 0;

        if(n == 1){

            final int[] snapFinal = arreglo.clone();

            SwingUtilities.invokeLater(() -> {
                marcarOrdenado(snapFinal, 0);
                lblEstado.setText("Ordenamiento completado");
            });

            return;
        }

        for(int i = 0; i < n-1; i++){

            final int id1 = i;
            final int id2 = i + 1;
            final int[] snap = arreglo.clone();

            SwingUtilities.invokeLater(() -> {
                actualizarDatos(snap, id1, id2);
                lblEstado.setText("Comparando Indice " + id1 + " con Indice " + id2);
            });

            pausa();

            if(debeIntercambiar(arreglo[i],arreglo[i+1])){
                temp = arreglo[i];
                arreglo[i] = arreglo[i+1];
                arreglo[i+1] = temp;

                final int[] snap2 = arreglo.clone();

                SwingUtilities.invokeLater(() -> {
                    actualizarDatos(snap2, id1, id2);
                    lblEstado.setText("Intercambio en Indice " + id1 + " con Indice " + id2);
                });

                pausa();
            }

        }

        final int idOrdenado = n - 1;
        final int[] snap3 = arreglo.clone();

        SwingUtilities.invokeLater(() -> {
            marcarOrdenado(snap3, idOrdenado);
            lblEstado.setText("Posición con Indice " + idOrdenado + " ya ordenada");
        });

        pausa();

        BubbleSortRecursivo(arreglo, n-1);
    }

}
