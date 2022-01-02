/*
Coffee shop -> Poll
Do you drink coffee? Yes (1) or No (-1)

Matrix
Maximum Sum Submatrix
Kadane's Algorithm (Dynamic) -> Bentley's Algorithm
Left Bound, Right Bound -> RowSum
Apply Kadane's Algo to RowSum Array

Kadane's Algo:
 max(MaxSum[i-1] + a[i], a[i])
         0  1 2  3  4 5 6  7
 arr = {-2 -3 4 -1 -2 1 5 -3}
 OPT(0) = -2
 OPT(1) = max(-3, -3 + -2)  = -3
 OPT(2) = max(4, 4 + -3) = 4
 OPT(3) = max(-1, -1 + 4) = 3
 OPT(4) = max(-2, 3 + -2) = 1
 OPT(5) = max(1, 1 + 1) = 2
 OPT(6) = max(5, 2 + 5) = 7
 OPT(7) = max(-3, 7 + -3) = 4

maxSumEndingHere = [
 */



import static java.lang.Math.max;


public class Main {

    public static void main(String[] args) {
        int[][] testArray = {{-2, 5, 0, -5, -2, 2, -3},
                {4, -3, -1, 3, 2, 1, -1},
                {-5, 6, 3, -5, -1, -4, -2},
                {-1, -1, 3, -1, 4, 1, 1},
                {3, -3, 2, 0, 3, -3, -2},
                {-2, 1, -2, 1, 1, 3, -1},
                {2, -4, 0, 1, 0, -3, -1}};
        System.out.println("The bentleyMax: " + bentleyMax(testArray) + "\n");
    }

    public static int bentleyMax(int[][] matrix) {
        int colCount = matrix[0].length;
        int[] rowS = new int[matrix.length];
        int currMax = 0;
        int leftMax, rightMax;

        int i, j;
        for(i = 0; i < colCount; i++) {
            for(j = i; j < colCount; j++){
                rowS = rowSum(matrix, i, j);
                currMax = max(currMax, kadaneMax(rowS));
            }
        }

        return currMax;
    }

    public static int[] rowSum(int[][] matrix, int leftBound, int rightBound) {
        int rowCount = matrix.length;
        int colCount = rightBound - leftBound;
        int[] sumOfRow = new int[rowCount];

        int i, j;
        for(i = 0; i < rowCount; i++) {
            sumOfRow[i] = 0;
            for(j = 0; j < colCount; j++) {
                sumOfRow[i] += matrix[i][j];
            }
        }

        return sumOfRow;
    }

    public static int kadaneMax(int[] arr) {
        int size = arr.length;
        int[] OPT = new int[size]; //OPT
        int maxSum;
        int start = 0;
        int end = 0;
        OPT[0] = arr[0];
        maxSum = OPT[0];

        int i;
        for (i = 1; i < size; i++) {

            if(arr[i] > (arr[i] + OPT[i - 1])){
                OPT[i] = arr[i];
                start = i;
            } else {
                OPT[i] = arr[i] + OPT[i - 1];
            }

            if(OPT[i] > maxSum) {
                maxSum = OPT[i];
                end = i;
            }
        }

        return maxSum;
    }
}
