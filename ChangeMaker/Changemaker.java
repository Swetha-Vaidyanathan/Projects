import java.util.Arrays;
public class Changemaker {
    /**
     * @param denominations  value of each type of coin
     * @param coinsRemaining quantity of each type of coin
     * @param value          total amount of change to be dispensed
     * @return
     */
    public static int count(int[] denominations, int[] coinsRemaining, int value) {
        int numWays = 0;
        int maxChangeDispensed = 0;
        // checks for largest coin denomination lesser or equal to value
        // and assigns ctr the index of it in the denominations array.
        int ctr=0;
        for (int i = 0; i < denominations.length ; i++) {
            if (denominations[i]>value) {
                ctr = i;
                break;
            }
            else {
                ctr = denominations.length;
            }
        }
        for (int i = 0; i < ctr ; i++) {
            maxChangeDispensed += denominations[i]*coinsRemaining[i];
        }


        // value = 0 (1 way to dispense 0 coins)
        if (value == 0) {
            numWays++;
            return numWays;
        }
        // no way to make change
        // value is negative ||
        // value is less than smallest denomination ||
        // value is greater than the maximum change that can be dispensed.
        else if (value < 0 || value<denominations[0] || value > maxChangeDispensed) {
            return numWays;
        }

        // recursive algorithm to compute the number of ways in which coins can be dispensed
        else {
            for (int i = 0; i < ctr;i++) {
                int [] newCoinsRemaining = Arrays.copyOf(coinsRemaining,denominations.length);
                if(newCoinsRemaining[i]>0) {
                    newCoinsRemaining[i]--;
                    numWays += count(denominations, newCoinsRemaining, value-denominations[i]);
                }
            }
            return numWays;
        }
    }

    /**
     * @param denominations  value of each type of coin
     * @param coinsRemaining quantity of each type of coin
     * @param value          total amount of change to be dispensed
     * @return
     */
//    public static int minCoins(int[] denominations, int[] coinsRemaining, int value) {
//        if(value == 0){
//            return 0;
//        }
//        if(value<0){
//            return -1;
//        }
//        int count = 0;
//        int retval=-1;
//        for (int i = 0;i< denominations.length;i++) {
//            // try to dispense denominations[i]
//            if (coinsRemaining[i] != 0 && denominations[i] <= value) {
//                count++;
//                coinsRemaining[i]--;
//                count = minCoins(denominations, coinsRemaining, value - denominations[i]);
//                coinsRemaining[i]++;
//            }
//        }
//        return count;
        public static int minCoins(int [] denominations, int[] coinsRemaining, int value) {
            if (value == 0) {
                return 0;
            }
            if (value < 0) {
                return -1;
            }
            int minCoins = Integer.MAX_VALUE;
            for (int i = 0; i < denominations.length; i++) {
                if (coinsRemaining[i] > 0) {
                    coinsRemaining[i]--;
                    int subMinCoins = minCoins(denominations, coinsRemaining, value - denominations[i]);
                    if (subMinCoins != -1) {
                        minCoins = Math.min(minCoins, subMinCoins + 1);
                    }
                    coinsRemaining[i]++;
                }
            }
            return (minCoins == Integer.MAX_VALUE) ? -1 : minCoins;
        }

    /**
     * @param denominations  value of each type of coin
     * @param coinsRemaining quantity of each type of coin
     * @param value          total amount of change to be dispensed
     * @return
     */
    public static int[] makeChange(int[] denominations, int[] coinsRemaining, int value) {
        // base cases
        if (value == 0) {
            int[] arr = new int[denominations.length];
            return arr;
        }

        if (value < 0) { // no possible solution
            return null;
        }

        // recursive cases
        int[] optimalSolution = null; // no solution yet
        int min = -1; // number of coins in optimalSolution

        // explore possible solution and find minimum
        for (int i = denominations.length - 1; i >= 0; i--) {
            // try to dispense denominations[i]
            if (denominations[i] <= value && coinsRemaining[i] != 0) {
                // dispense coin
                coinsRemaining[i]--;
                // solve smaller problem to make change for value -denominations[i]
                int[] arr = makeChange(denominations, coinsRemaining, value - denominations[i]);
                coinsRemaining[i]++;
                if (arr != null) {
                    // if smaller problem solved successfully, combine the solution to make value
                    arr[i]++; // possible solution to make value
                    int sum=sum(arr);
                    // check if it is optimal solution
                    if(min==-1||sum<min){
                        optimalSolution=arr;
                        min=sum;
                    }
                }
            }
        }

        return optimalSolution;
    }

    private static int sum(int[] coins) {
        int total = 0;
        for (int i = 0; i < coins.length; i++) {
            total += coins[i];
        }
        return total;
    }

    private static int[] useCoin(int[] coinsRemaining, int coinToUse){
        coinsRemaining[coinToUse]--;
        int[] newArray = Arrays.copyOf(coinsRemaining,coinsRemaining.length);
        return newArray;
    }



}

