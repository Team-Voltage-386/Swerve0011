package frc.robot;

/** The Utils class contains general purpose methods, and the flags class */
public class Utils {

    /**Lerp, Standard Linear Interpolation
     * @param a first number
     * @param b second number
     * @param factor lerp factor
     */
    public static double lerp(double a, double b, double factor) {
        double c = b-a;
        c = factor * c;
        return a + c;
    }



    public static interface doubAlg {
        double get(double a);
    }

    public static interface doubAlgB {
        double get(double a, double b);
    }
}
