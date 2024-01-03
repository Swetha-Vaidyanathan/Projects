import java.util.Arrays;

public class ChangemakerTester {
    public static void main(String args[]) {
        System.out.println(testCountBase() ? "pass" : "x");
        System.out.println(testCountRecursive() ? "pass" : "x");
        System.out.println(testMinCoinsBase() ? "pass" : "x");
        System.out.println(testMinCoinsRecursive() ? "pass" : "x");
        System.out.println(testMakeChangeBase() ? "pass" : "x");
        System.out.println(testMakeChangeRecursive() ? "pass" : "x");

    }

    public static boolean testCountBase() {
        {
            int[] denominations = {1, 5, 10, 25};
            int[] coins = {2, 2, 5, 3};
            int value = -2;
            int expected = 0;
            int actual = Changemaker.count(denominations, coins, value);
            if (expected == actual) {
                return true;
            }
        }
        {
            int[] denominations = {1, 5, 10, 25};
            int[] coins = {2, 2, 0, 0};
            int value = 25;
            int expected = 0;
            int actual = Changemaker.count(denominations, coins, value);
            if (expected == actual) {
                return true;
            }

        }
        {
            int[] denominations = {1, 5, 10, 25};
            int[] coins = {2, 2, 5, 3};
            int value = 0;
            int expected = 1;
            int actual = Changemaker.count(denominations, coins, value);
            if (expected == actual) {
                return true;
            }
        }

        return false;
    }

    public static boolean testCountRecursive() {
        return false;
    }

    /**
     * test method to check the minCoins() method for correctness
     *
     * @return true of the return value(integer) of the method
     * is equal to the expected integer value else returns false
     */
    public static boolean testMinCoinsBase() {
        {
            int[] denominations = {1, 5, 10, 25};
            int[] coins = {2, 2, 5, 3};
            int value = -2;
            int expected = -1;
            int actual = Changemaker.minCoins(denominations, coins, value);
            if (expected == actual) {
                return true;
            }
        }
        {
            int[] denominations = {1, 5, 10, 25};
            int[] coins = {0, 4, 7, 2};
            int value = 6;
            int expected = -1;
            int actual = Changemaker.minCoins(denominations, coins, value);
            if (expected == actual) {
                return true;
            }
        }
        {
            int[] denominations = {1, 5, 10, 25};
            int[] coins = {2, 5, 3, 0};
            int value = 0;
            int expected = 0;
            int actual = Changemaker.minCoins(denominations, coins, value);
            if (expected == actual) {
                return true;
            }
        }
        return false;
    }

    /**
     * test method to check the minCoins() method for correctness
     *
     * @return true of the return value(integer) of the method
     * is equal to the expected integer value else returns false
     */

    public static boolean testMinCoinsRecursive() {
        {
            int[] denominations = {1, 5, 10, 25};
            int[] coins = {2, 1, 1, 1};
            int value = 31;
            int expected = 3;
            int actual = Changemaker.minCoins(denominations, coins, value);
            if (expected == actual) {
                return true;
            }
        }
        {
            int[] denominations = {6, 7, 8, 9};
            int[] coins = {2, 1, 1, 2};
            int value = 15;
            int expected = 2;
            int actual = Changemaker.minCoins(denominations, coins, value);
            if (expected == actual) {
                return true;
            }
        }
        {
            int[] denominations = {1, 3, 4, 5};
            int[] coins = {3, 1, 1, 2};
            int value = 7;
            int expected = 2;
            int actual = Changemaker.minCoins(denominations, coins, value);
            if (expected == actual) {
                return true;
            }
        }
        return false;
    }

    /**
     * test method to check the makeChange() method for correctness
     *
     * @return true of the return value(array) of the method
     * is equal to the expected array else returns false
     */
    public static boolean testMakeChangeBase() {
        {
            int[] denominations = {1, 5, 10, 25};
            int[] coins = {2, 2, 5, 3};
            int value = -2;
            int[] expected = null;
            int[] actual = Changemaker.makeChange(denominations, coins, value);
            if (expected==actual) {
                return true;
            }
        }
        {
            int[] denominations = {1, 5, 10, 25};
            int[] coins = {0, 4, 7, 2};
            int value = 6;
            int[] expected = null;
            int[] actual = Changemaker.makeChange(denominations, coins, value);
            if (expected.equals(actual)) {
                return true;
            }
        }
        {
            int[] denominations = {1, 5, 10, 25};
            int[] coins = {2, 5, 3, 0};
            int value = 0;
            int[] expected = {0, 0, 0, 0};
            int[] actual = Changemaker.makeChange(denominations, coins, value);
            if (expected.equals(actual)) {
                return true;
            }
        }
        return false;
    }

    /**
     * test method to check the makeChange() method for correctness
     *
     * @return true of the return value(array) of the method
     * is equal to the expected array else returns false
     */
    public static boolean testMakeChangeRecursive() {
       /* {
            int[] denominations = {1, 5, 10, 25};
            int[] coins = {2, 3, 1, 2};
            int value = 31;
            int[] expected = {1, 1, 0, 1};
            int[] actual = Changemaker.makeChange(denominations, coins, value);
            if (expected.equals(actual)) {
                return true;
            }
        }

        */
        {
            int[] denominations = {1, 5, 10, 25};
            int[] coins = {5, 2, 0, 1};
            int value = 30;
            int[] expected = {0, 1, 0, 1};
            int[] actual = Changemaker.makeChange(denominations, coins, value);
            if (expected.equals(actual)) {
                return true;
            }
        }
        /*
        {
            int[] denominations = {1, 5, 10, 25};
            int[] coins = {3, 2, 0, 2};
            int value = 7;
            int[] expected = {2, 1, 0, 0};
            int[] actual = Changemaker.makeChange(denominations, coins, value);
            if (expected.equals(actual)) {
                return true;
            }
        }

        */
        return false;
    }
}
