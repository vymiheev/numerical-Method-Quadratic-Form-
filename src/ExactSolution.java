/*
 used to study the error
 exaxt solution of integral equation : u(x)=1+ J (t+xt^2)u(t) dt
 with lower limit=0 and upper limit=1 is next equation:
 u(x)=1+35/19 + 24*x/19;

 */
public class ExactSolution {
    private double[] exactSolution;
    private final double coefficientA;
    private final double coefficientB;
    private final int N;

    public ExactSolution(int N) {
        this.N = N;
        this.exactSolution = new double[N];
        coefficientA = 1 + 35.0 / 19;
        coefficientB = 24.0 / 19;
    }

    public double[] getExactSolution() {
        for (double x = EntryPoint.LOWER_LIMIT + 0.5*EntryPoint.STEP, i = 0; x <= EntryPoint.UPPER_LIMIT; x += EntryPoint.STEP, i++) {
            exactSolution[((int) i)] = coefficientA + coefficientB * x;

        }
        return exactSolution;
    }


}
