import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*
calculate integral Fredgolma 2nd form:
u(x)-lJK(x,t)u(t)dt=f(x);
use quadrature methods that expand out integral to next view:
u(xi)=[SUM]AjK(xi,tj)u(xj)=f(xi)
where Aj - coefficient suits its quadrature form.

In rectangle quadratic form:
STEP=(b-a)/n;
Aj=STEP;
*/

public class EntryPoint {
    public static double LOWER_LIMIT;
    public static double UPPER_LIMIT;
    public static int N;
    public static double STEP;
    public static double EPS;

    public EntryPoint() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input"));
        LOWER_LIMIT = scanner.nextDouble();
        UPPER_LIMIT = scanner.nextDouble();
        N = scanner.nextInt();
        EPS = scanner.nextDouble();
        STEP = (UPPER_LIMIT - LOWER_LIMIT) / (N - 1);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new EntryPoint();
        new ActionHandler().run();

    }
}
