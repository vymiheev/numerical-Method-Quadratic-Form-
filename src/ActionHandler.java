/*
* n - count of nodes
*
* */

public class ActionHandler implements Runnable {
    private double[] exactSolution;
    private double[] approximateSolution;
    private int N;

    public ActionHandler() {
        RungeRule rungeRule = new RungeRule();
        this.approximateSolution = rungeRule.runRungeRule();
        this.N = approximateSolution.length;
        System.out.println("N: " + N + "\ntrue size " + (int) (1+(EntryPoint.UPPER_LIMIT - EntryPoint.LOWER_LIMIT) / EntryPoint.STEP) +
                "\nstep: " + EntryPoint.STEP + "\niteration count: " + Math.log(EntryPoint.STEP) / Math.log(1.0/2));
        this.exactSolution = new ExactSolution(N).getExactSolution();
    }

    @Override
    public void run() {

        //approximateSolution = new QuadratureMethod().getApproximateSolution();
        printSolution(approximateSolution);

        System.out.println("\nexact Solution: ");
        printSolution(exactSolution);

        printApproximation(N);
    }


    private void printSolution(double[] matrix) {
        System.out.println();
        for (double aSolution : matrix) {
            System.out.println(aSolution);
        }
    }

    private void printApproximation(int N) {
        System.out.println("\nApproximation:\n ");
        for (int i = 0; i < N; i++) {
            System.out.println(Math.abs(exactSolution[i] - approximateSolution[i]));

        }
    }
}
