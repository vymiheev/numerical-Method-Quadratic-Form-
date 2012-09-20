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
        System.out.println("N: " + N + "\nstep: " + EntryPoint.STEP + "\niteration count: " + (Math.log(EntryPoint.STEP) / Math.log(1.0 / 2)-1));
        this.exactSolution = new ExactSolution(N).getExactSolution();
    }

    @Override
    public void run() {

        //approximateSolution = new QuadratureMethod().getApproximateSolution();
        printSolution(approximateSolution);

        System.out.println("\nexact Solution: ");
        printSolution(exactSolution);

        printApproximation();
        printMaxDiffer();
    }

    private void printSolution(double[] matrix) {
        System.out.println();
        for (double aSolution : matrix) {
            System.out.println(aSolution);
        }
    }

    private void printApproximation() {
        System.out.println("\nApproximation:\n ");
        for (int i = 0; i < N; i++) {
            System.out.println(Math.abs(exactSolution[i] - approximateSolution[i]));

        }
    }

    private void printMaxDiffer() {
        System.out.println("\nmaxDiffer:\n");
        double maxDiffer = Math.abs(exactSolution[0] - approximateSolution[0]);
        for (int i = 1; i < N; i++) {
            double tempDiffer = Math.abs(exactSolution[i] - approximateSolution[i]);
            if (maxDiffer < tempDiffer) {
                maxDiffer = tempDiffer;
            }
        }
        System.out.println(maxDiffer);
    }
}
