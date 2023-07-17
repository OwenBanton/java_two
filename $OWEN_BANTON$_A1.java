package javaProjects;//Owen Banton


import java.util.ArrayList;

public class $OWEN_BANTON$_A1 {

    public static void main(String[] args) {

        System.out.println(crazySeries(1));
        System.out.println(benNumber(1, 1));

    }

    static ArrayList<Double> crazyList = new ArrayList<>();// Static list that needs to be accessed by multiple methods.

    // crazySeries method adds initial input to the list, then calls recursive functions to add
    // multiplied then divided values to the list.
    public static ArrayList<Double> crazySeries(int start) {
        double input = start;                                   // Input cast to double and added to list.
        crazyList.add(input);
        crazyMulti(input);                                      // Multiplication method call.
        crazyDiv(crazyList.get(crazyList.size() - 1));          // Division method call, starting from element
        return crazyList;                                       // where multiplication ended.
    }

    // Recursive function to multiply input until > 100.

    public static void crazyMulti(double x) {
        if (x < 100) {
            x *= 2;                                // Input checked then multiplied to include one element over 100.
            crazyList.add(x);
            crazyMulti(x);                         // Element added to list then function called again for next element.
        }
    }

    // Recursive function to divide input until < 5. Functions similarly to multiplication method.

    public static void crazyDiv(double x) {
        if (x > 5) {
            x /= 3;
            crazyList.add(x);
            crazyDiv(x);
        }
    }

    // Recursive function to sum up ben number series.

    static int benNumber(int a, int b) {
        int nextA;                       // Declaring a variable to use as a buffer for the following element.
        if (b == 0) {
            return 0;
        }
        if (b == 1) {
            return a;                    // Excluding cases with 1 or 0 elements which wouldn't need to be manipulated.
        } else {
            if ((a % 2) == 0) {          // Checking if the element is even, and multiplying the next by 2 if so.
                nextA = a * 2;
            } else {                     // Subtracting one from all other elements which will be odd as they didn't
                nextA = a - 1;           // enter the preceding if block.
            }
            return (a + benNumber(nextA, b - 1)); // Returning sum of initial value and each subsequent element, with
        }                                          // the b value decreasing each time to obtain the appropriate length.

    }
}