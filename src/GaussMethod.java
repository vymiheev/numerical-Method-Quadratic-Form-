public class GaussMethod implements Runnable {
    private int n = FunctionHandler.matrixB.length;
    public double[] matrixU = new double[n];

    @Override
    public void run() {
        for (int i = 0; i < n - 1; i++) {
            if (FunctionHandler.matrixB[i][i] == 0) {
                chooseColMainEl(i);
            }
            for (int k = i + 1; k < n; k++) {
                double p = FunctionHandler.matrixB[k][i] / FunctionHandler.matrixB[i][i];
                for (int j = i + 1; j < n; j++) {
                    FunctionHandler.matrixB[k][j] -= p * FunctionHandler.matrixB[i][j];
                }
                FunctionHandler.fx[k] -= p * FunctionHandler.fx[i];
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            double s = 0;
            for (int j = i + 1; j < n; j++) {
                s += FunctionHandler.matrixB[i][j] * matrixU[j];
            }
            matrixU[i] = (FunctionHandler.fx[i] - s) / FunctionHandler.matrixB[i][i];
        }
        printMatrixU();
    }

    private void printMatrixU() {
        for (int i = 0; i < matrixU.length; i++) {
            System.out.println(matrixU[i]);
        }
    }


    private void chooseColMainEl(int i) {
        int k = i;
        for (int m = i + 1; m < n; m++) {
            if (Math.abs(FunctionHandler.matrixB[m][i]) > Math.abs(FunctionHandler.matrixB[k][i])) {
                k = m;
            }
        }
        if (k != i) {
            for (int j = i; j < n; j++) {
                double box = FunctionHandler.matrixB[i][j];
                FunctionHandler.matrixB[i][j] = FunctionHandler.matrixB[k][j];
                FunctionHandler.matrixB[k][j] = box;
            }
            double box = FunctionHandler.fx[i];
            FunctionHandler.fx[i] = FunctionHandler.fx[k];
            FunctionHandler.fx[k] = box;
        }
    }
}
