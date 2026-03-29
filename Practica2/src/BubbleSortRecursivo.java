import javax.swing.*;

public class BubbleSortRecursivo extends ChartManager{

    public BubbleSortRecursivo(JPanel panel, int[] arreglo, boolean ascendente, int velocidad) {
        super(panel, arreglo, ascendente, velocidad);
    }

    @Override
    protected String getNombre() {
        return "Bubble Sort";
    }

    @Override
    protected void ordenar(int[] arr) {
        BubbleSortRecursivo(arr, arreglo.length);
    }


    private void BubbleSortRecursivo(int arreglo[], int n){
        int temp = 0;

        if(n == 1){
            return;
        }

        for(int i = 1; i < n-1; i++){
            if(debeIntercambiar(arreglo[i],arreglo[i+1])){
                temp = arreglo[i];
                arreglo[i] = arreglo[i+1];
                arreglo[i+1] = temp;
            }
        }

        BubbleSortRecursivo(arreglo, n-1);
    }

}
