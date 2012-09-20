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

    public double[] runRungeRule() {
        double[] IH = new QuadratureMethod().getApproximateSolution();
        while (true) {
            EntryPoint.STEP /= STEP_DIVIDER;
            double[] Ih = new QuadratureMethod().getApproximateSolution();
            /*findMaxDifference(Ih, IH);
            if (findDifference(Ih, IH) < EntryPoint.EPS) {
                return IH;
            }*/

            if (findMaxDifference(Ih, IH) / D < EntryPoint.EPS) {
                return Ih;
            }
            /* if (it==2){
                return Ih;
            }
            it++;*/
            IH = Ih.clone();

        }

    }

    private double findDifference(double[] Ih, double[] IH) {
        double IhSum = 0;
        double IHSum = 0;
        for (int i = 0; i < Ih.length; i += STEP_DIVIDER) {
            IhSum += Ih[i];
        }
        for (double aIH : IH) {
            IHSum += aIH;
        }
        double integralDifference = Math.abs(IHSum - IhSum) / D;
        System.out.println("\nintegralDifference:\n" + integralDifference);
        return integralDifference;
    }

    private double findMaxDifference(double[] Ih, double[] IH) {
        double max = Math.abs(IH[0] - Ih[0]);
        for (int i = 1; i < IH.length; i++) {
            double tempDifference = Math.abs(IH[i] - Ih[STEP_DIVIDER * i]);
            if (tempDifference > max) {
                max = tempDifference;
            }
        }
        return max;

    }
}