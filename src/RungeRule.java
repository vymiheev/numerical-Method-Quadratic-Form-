/*The approximation e=|I(h)-I(h/2)| / (2^m-1)
@params:
* I(h) - integral with step value = h <-> IH
* I(h/2) - integral with step value = h/2   <-> Ih
* m - the order approximation (for rectangular method m = 2)
*
* */

public class RungeRule {
    public RungeRule() {
        M = 2;
        D = Math.pow(2, M) - 1;
    }

    private final int STEP_DIVIDER = 2;
    private final int M;
    private final double D;
    private int it = 0;
    private int iteration = 0;

    public double[] runRungeRule() {
        double[] IH = new QuadratureMethod().getApproximateSolution();
        while (true) {
            EntryPoint.STEP /= STEP_DIVIDER;
            double[] Ih = new QuadratureMethod().getApproximateSolution();
            double relativeDiff = findRelativeApproximationDifference(Ih, IH);
            System.out.println("ITERATION: " + iteration);
            System.out.println("        Step: " + EntryPoint.STEP);
            System.out.println("        Relative approximation difference: " + relativeDiff);
            System.out.println("        Factual approximation difference: " + findFaсtualApproximationDifference(Ih) + "\n");

            if (relativeDiff < EntryPoint.EPS) {
                return Ih;
            }
            /* if (it==2){
                return Ih;
            }
            it++;*/
            iteration++;
            IH = Ih.clone();

        }

    }


    private double findRelativeApproximationDifference(double[] Ih, double[] IH) {
        double[] newIh=reCountApproximation(Ih);

        double max = Math.abs(IH[0] - newIh[0]);
        for (int i = 1; i < IH.length; i++) {
            double tempDifference = Math.abs(IH[i] - newIh[i]);
            if (tempDifference > max) {
                max = tempDifference;
            }
        }
        return max / D;
        /*double max = Math.abs(IH[0] - Ih[0]);
        for (int i = 1; i < IH.length; i++) {
            double tempDifference = Math.abs(IH[i] - Ih[STEP_DIVIDER * i]);
            if (tempDifference > max) {
                max = tempDifference;
            }
        }
        return max / D;*/

    }

    private double[] reCountApproximation(double[] Ih) {
        double[] newIh = new double[Ih.length / 2];
        double previousStep = EntryPoint.STEP * 2;
        for (double x = EntryPoint.LOWER_LIMIT + 0.5 * previousStep, i = 0; x < EntryPoint.UPPER_LIMIT; i++, x += previousStep) {
            for (double t = EntryPoint.LOWER_LIMIT + 0.5 * EntryPoint.STEP, j = 0; t < EntryPoint.UPPER_LIMIT; j++, t += EntryPoint.STEP) {
                newIh[(int) i] += (t + Math.pow(t, 2) * x) * Ih[(int) j];
            }
            newIh[(int) i] = newIh[(int) i] * EntryPoint.STEP + 1;
        }
        return newIh;
    }

    private double findFaсtualApproximationDifference(double[] approximateSolution) {
        double[] exactSolution = new ExactSolution(approximateSolution.length).getExactSolution();
        double maxDiffer = Math.abs(exactSolution[0] - approximateSolution[0]);
        for (int i = 1; i < approximateSolution.length; i++) {
            double tempDiffer = Math.abs(exactSolution[i] - approximateSolution[i]);
            if (maxDiffer < tempDiffer) {
                maxDiffer = tempDiffer;
            }
        }
        return maxDiffer;
    }
}