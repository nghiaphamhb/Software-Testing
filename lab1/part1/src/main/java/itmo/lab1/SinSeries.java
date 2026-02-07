package itmo.lab1;

public class SinSeries 
{
    public static double calculate(double x, double eps){
        if (eps <= 0) throw new IllegalArgumentException("eps must be > 0");

        double term = x; 
        double sum = x;
        int k = 0;

        // term_{k+1} = -term_k * x^2 / ((2k+2)(2k+3))
        while (Math.abs(term) >= eps) {
            double denom = (2.0 * k + 2.0) * (2.0 * k + 3.0);
            term = -term * x * x / denom;
            sum += term;
            k ++;

            if (k > 10000) {
                throw new IllegalStateException("Too many iterations (eps too small or numeric issue).");
            }
        }

        return sum;
    }
}
