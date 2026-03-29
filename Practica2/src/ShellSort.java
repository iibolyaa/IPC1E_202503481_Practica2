import javax.swing.*;

public class ShellSort extends ChartManager{

    public ShellSort(JPanel panel, int[] arreglo, boolean ascendente, int velocidad) {
        super(panel, arreglo, ascendente, velocidad);
    }

    @Override
    protected String getNombre() {
        return "Shell Sort";
    }

    @Override
    protected void ordenar(int[] arr) {
        ShellSortAscendente(arr, arr.length);
    }

    private void ShellSortAscendente(int arreglo[], int n){

        for(int intervalo = n/2; intervalo>0; intervalo = intervalo/2){
            for(int i = intervalo; i < n; i++) {
                int temp = arreglo[i];
                int j = 0;

                for (j = i; j >= intervalo && debeIntercambiar(arreglo[j - intervalo], temp); j -= intervalo) {

                    final int id1 = j;
                    final int id2 = j - intervalo;
                    final int[] snap = arreglo.clone();

                    SwingUtilities.invokeLater(() -> {
                        actualizarDatos(snap, id1, id2);
                        lblEstado.setText("Comparando Indice " + id1 + " con Indice " + id2);
                    });

                    pausa();

                    arreglo[j] = arreglo[j - intervalo];

                }

                arreglo[j] = temp;

                if(intervalo == 1) {
                    final int posOrdenada = j;
                    final int[] snap3 = arreglo.clone();

                    SwingUtilities.invokeLater(() -> {
                        marcarOrdenado(snap3, posOrdenada);
                        lblEstado.setText("Índice " + posOrdenada + " en posición final");
                    });

                    pausa();
                }
            }
        }

        final int[] snapFinal = arreglo.clone();

        SwingUtilities.invokeLater(() -> {
            marcarOrdenado(snapFinal, 0);
            lblEstado.setText("Ordenamiento completado");
        });
    }

    private void ShellSortDescendente(int arreglo[], int n){

        for(int intervalo = n/2; intervalo>0; intervalo = intervalo/2) {

            for (int i = intervalo; i < n; i++) {
                int temp = arreglo[i];
                int j = 0;

                for (j = i; j >= intervalo && arreglo[j - intervalo] > temp; j -= intervalo) {
                    arreglo[j] = arreglo[j - intervalo];
                }
                arreglo[j] = temp;
            }
        }
    }

}
