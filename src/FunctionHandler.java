/*
* n - count of nodes
*
* */

import java.util.Scanner;

public class FunctionHandler implements Runnable {
    private double[][] coreKij;
    public static double[] fx;
    public static double matrixB[][];
    private final double lambda;

    public FunctionHandler() {
        this.coreKij = new double[EntryPoint.nodesCount][EntryPoint.nodesCount];
        fx = new double[EntryPoint.nodesCount];
        matrixB = new double[EntryPoint.nodesCount][EntryPoint.nodesCount];
        lambda = 1.0 / 2; // coefficient before the integral
    }

    private double A(int i) {
        if ((i + 1) % 2 == 0) {
            return 4 * EntryPoint.STEP / 3; //if even
        } else {
            return EntryPoint.STEP / 3;    //if odd
        }
    }

    private void countCoreKAndFx() {
        for (double x = EntryPoint.LEFT_BORDER, row = 0; x <= EntryPoint.RIGHT_BORDER; x += EntryPoint.STEP, row++) {
            fx[((int) row)] = (5 * x) / 6;                        // it is f(x)
            //
            //System.out.println(fx[((int) row)]);
            //
            for (double t = EntryPoint.LEFT_BORDER, col = 0; t <= EntryPoint.RIGHT_BORDER; t += EntryPoint.STEP, col++) {
                coreKij[((int) row)][((int) col)] = x * t; // it is core K(xi,xj)
            }
        }
        System.out.println();
        //
/*        for (int i = 0; i < coreKij.length; i++) {
            for (int j = 0; j < coreKij.length; j++) {
                System.out.print(coreKij[i][j] + " ");
            }
            System.out.println();
        }*/
        //
    }

    private void fillMatrix() {
        for (int row = 0; row < EntryPoint.nodesCount; row++) {
            for (int col = 0; col < EntryPoint.nodesCount; col++) {
                matrixB[row][col] = -1 * lambda * A(col) * coreKij[row][col];
            }
            matrixB[row][row] += 1.0;
        }
        /*
        Scanner scanner=new Scanner(System.in);
        for (int i = 0; i < matrixB.length; i++) {
            for (int j = 0; j < matrixB.length; j++) {
                 matrixB[i][j]=scanner.nextDouble();
            }

        }
        System.out.println("okey!");
        for (int i = 0; i < fx.length; i++) {
            fx[i]=scanner.nextDouble();
        }*/

        //

/*        for (int row = 0; row < EntryPoint.nodesCount; row++) {
            System.out.println();

            for (int col = 0; col < EntryPoint.nodesCount; col++) {
                System.out.print(matrixB[row][col] + " ");
            }
        }*/
    }


    @Override
    public void run() {
        countCoreKAndFx();
        fillMatrix();
        GaussMethod method=new GaussMethod();
        method.run();
    }
}
