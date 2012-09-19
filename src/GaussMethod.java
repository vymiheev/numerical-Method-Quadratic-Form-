/*
* solving system of algebraic equations by Gauss method:
* Ax=b
* a ~ coefficientMatrixU
* x ~ matrixU
* b ~ fx
*/
public class GaussMethod {
    final int N;
    public double[] matrixU;

    public GaussMethod() {
        this.N= 1 + (int) ((EntryPoint.UPPER_LIMIT - EntryPoint.LOWER_LIMIT) / EntryPoint.STEP);
        matrixU=new double[N];
    }

    public double[] solveSystemLinearAlgebraicEquations() {
        for (int i = 0; i < N - 1; i++) {
            if (QuadratureMethod.coefficientMatrixU[i][i] == 0) {
                chooseColMainEl(i);
            }
            for (int k = i + 1; k < N; k++) {
                double p = QuadratureMethod.coefficientMatrixU[k][i] / QuadratureMethod.coefficientMatrixU[i][i];
                for (int j = i; j < N; j++) {
                    QuadratureMethod.coefficientMatrixU[k][j] -= p * QuadratureMethod.coefficientMatrixU[i][j];
                }
                QuadratureMethod.fx[k] -= p * QuadratureMethod.fx[i];
            }
        }
        for (int i = N - 1; i >= 0; i--) {
            double s = 0;
            for (int j = i + 1; j < N; j++) {
                s += QuadratureMethod.coefficientMatrixU[i][j] * matrixU[j];
            }
            matrixU[i] = (QuadratureMethod.fx[i] - s) / QuadratureMethod.coefficientMatrixU[i][i];
        }
        return matrixU;
    }


    private void chooseColMainEl(int i) {
        int k = i;
        for (int m = i + 1; m < N; m++) {
            if (Math.abs(QuadratureMethod.coefficientMatrixU[m][i]) > Math.abs(QuadratureMethod.coefficientMatrixU[k][i])) {
                k = m;
            }
        }
        if (k != i) {
            for (int j = i; j < N; j++) {
                double box = QuadratureMethod.coefficientMatrixU[i][j];
                QuadratureMethod.coefficientMatrixU[i][j] = QuadratureMethod.coefficientMatrixU[k][j];
                QuadratureMethod.coefficientMatrixU[k][j] = box;
            }
            double box = QuadratureMethod.fx[i];
            QuadratureMethod.fx[i] = QuadratureMethod.fx[k];
            QuadratureMethod.fx[k] = box;
        }
    }
}
