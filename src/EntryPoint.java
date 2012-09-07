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
    public static double LEFT_BORDER;
    public static double RIGHT_BORDER;
    public static int nodesCount;
    public static double STEP;

    public EntryPoint() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input"));
        LEFT_BORDER = scanner.nextDouble();
        RIGHT_BORDER = scanner.nextDouble();
        nodesCount = scanner.nextInt();
        STEP = (RIGHT_BORDER - LEFT_BORDER) / (nodesCount - 1);
        //System.out.println(LEFT_BORDER + " " + RIGHT_BORDER + " " + nodesCount + " " + STEP);

    }

    public static void main(String[] args) throws FileNotFoundException {
        EntryPoint initialData = new EntryPoint();
        FunctionHandler handler = new FunctionHandler();
        handler.run();

    }
}
