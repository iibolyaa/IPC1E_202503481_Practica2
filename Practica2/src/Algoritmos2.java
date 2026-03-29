import javax.swing.*;

public class Algoritmos2 extends ChartManager{

        public Algoritmos2(JPanel panel, int[] arreglo, boolean ascendente, int velocidad) {
            super(panel, arreglo, ascendente, velocidad);
        }


        @Override
        protected String getNombre() {
            return "";
        }

        @Override
        protected void ordenar(int[] arr) {
        }


        private void BubbleSortRecursivoAscendente(int arreglo[], int n){
            int temp = 0;

            if(n == 1){
                return;
            }

            for(int i = 1; i < n-1; i++){
                if(arreglo[i]>arreglo[i+1]){
                    temp = arreglo[i];
                    arreglo[i] = arreglo[i+1];
                    arreglo[i+1] = temp;
                }
            }

            BubbleSortRecursivoAscendente(arreglo, n-1);
        }

        private void BubbleSortRecursivoDescendente(int arreglo[], int n){
            int temp = 0;

            if(n == 1){
                return;
            }

            for(int i = 0; i < n-1; i++){
                if(arreglo[i]<arreglo[i+1]){
                    temp = arreglo[i];
                    arreglo[i] = arreglo[i+1];
                    arreglo[i+1] = temp;
                }
            }

            BubbleSortRecursivoDescendente(arreglo, n-1);
        }

        private void ShellSortAscendente(int arreglo[], int n){
            n = arreglo.length;

            for(int intervalo = n/2; intervalo>0; intervalo = intervalo/2){

                for(int i = intervalo; i < n; i++) {
                    int temp = arreglo[i];
                    int j = 0;

                    for (j = i; j >= intervalo && arreglo[j - intervalo] < temp; j -= intervalo) {
                        arreglo[j] = arreglo[j - intervalo];
                    }
                    arreglo[j] = temp;
                }
            }
        }

        private void ShellSortDescendente(int arreglo[], int n){
            n = arreglo.length;

            for(int intervalo = n/2; intervalo>0; intervalo = intervalo/2){

                for(int i = intervalo; i < n; i++) {
                    int temp = arreglo[i];
                    int j = 0;

                    for (j = i; j >= intervalo && arreglo[j - intervalo] > temp; j -= intervalo) {
                        arreglo[j] = arreglo[j - intervalo];
                    }
                    arreglo[j] = temp;
                }
            }
        }

        private void QuickSortAscendente(int arreglo[], int low, int high) {
            if (low < high) {
                int pi = partition(arreglo, low, high);

                QuickSortAscendente(arreglo, low, pi - 1);
                QuickSortAscendente(arreglo, pi + 1, high);
            }
        }

        private int partition(int[] arreglo, int low, int high) {
            // High y low son índices del arreglo

            int pivote = arreglo[high];
            int i = low - 1;

            for (int j = low; j < high; j++) {
                if (arreglo[j] < pivote) {
                    i++;

                    int temp = arreglo[i];
                    arreglo[i] = arreglo[j];
                    arreglo[j] = temp;
                }
            }

            //Intercambiar el pivote
            int temp = arreglo[i + 1];
            arreglo[i + 1] = arreglo[high];
            arreglo[high] = temp;

            // Regresa la posición final del pivote
            return i + 1;
        }

        private void QuickSortDescendente(int arreglo[], int low, int high) {
            if (low < high) {
                int pi = partitionD(arreglo, low, high);

                QuickSortDescendente(arreglo, low, pi - 1);
                QuickSortDescendente(arreglo, pi + 1, high);
            }
        }

        private int partitionD(int[] arreglo, int low, int high) {
            int pivote = arreglo[high];
            int i = low - 1;

            for (int j = low; j < high; j++) {
                if (arreglo[j] > pivote) {
                    i++;

                    int temp = arreglo[i];
                    arreglo[i] = arreglo[j];
                    arreglo[j] = temp;
                }
            }

            //Intercambiar el pivote
            int temp = arreglo[i + 1];
            arreglo[i + 1] = arreglo[high];
            arreglo[high] = temp;

            // Regresa la posición final del pivote
            return i + 1;
        }
    }

