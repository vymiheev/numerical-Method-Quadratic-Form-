/*
* it's a main class of quadrature method that used to solve System of Linear Algebraic Equations (SLAE)\
*
* Result:
* give approximate solution of integral equation : u(x)=1+ J (t+xt^2)u(t) dt
* nodes are used inside the interval, it means that iteration begins in a+0.5*h point - not in a,
* where interval is [a,b]
* */

public class QuadratureMethod {
    private double[][] coreKij;
    public static double[] fx;
    public static double coefficientMatrixU[][];
    private final double LAMBDA; // coefficient before the integral
    private final int N;

    public QuadratureMethod() {
        this.N = (int) ((EntryPoint.UPPER_LIMIT - EntryPoint.LOWER_LIMIT) / EntryPoint.STEP);
        this.coreKij = new double[N][N];
        fx = new double[N];
        coefficientMatrixU = new double[N][N];
        LAMBDA = 1.0;
    }

    public double[] getApproximateSolution() {
        countCoreKAndFx();
        fillMatrix();
        GaussMethod method = new GaussMethod();
        return method.solveSystemLinearAlgebraicEquations();
    }


    private double A(int i) {
        return EntryPoint.STEP;

/*        if (i + 1 != N) {
            return EntryPoint.STEP;
        } else
            return 0;*/

        /*if ((i + 1) % 2 == 0) {
            return 4 * EntryPoint.STEP / 3; //if even
        } else {
            return EntryPoint.STEP / 3;    //if odd
        }*/
    }

    private void countCoreKAndFx() {
        for (double x = EntryPoint.LOWER_LIMIT + 0.5 * EntryPoint.STEP, row = 0; x <= EntryPoint.UPPER_LIMIT; x += EntryPoint.STEP, row++) {
            fx[((int) row)] = 1;
            for (double t = EntryPoint.LOWER_LIMIT + 0.5 * EntryPoint.STEP, col = 0; t <= EntryPoint.UPPER_LIMIT; t += EntryPoint.STEP, col++) {
                coreKij[((int) row)][((int) col)] = t + x * Math.pow(t, 2);
                //System.out.print(coreKij[((int) row)][((int) col)] + " ");
            }
            //System.out.println();
        }
        //System.out.println();

    }

    private void fillMatrix() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                coefficientMatrixU[row][col] = -1.0 * LAMBDA * A(col) * coreKij[row][col];
                //System.out.print(coefficientMatrixU[row][col] + " ");
            }
            coefficientMatrixU[row][row] += 1.0;
            //System.out.println();
        }

/*        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++)
                System.out.print(coefficientMatrixU[row][col] + " ");
            System.out.println();
        }*/
    }


}
