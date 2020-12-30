import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, String> cellToValue = new TreeMap<>();
        int numOfCells = sc.nextInt();
        sc.skip("\n");
        for (int i = 0; i < numOfCells; i++) {
            String cell = sc.nextLine();
            String cellVal = sc.nextLine();
            cellToValue.put(cell, cellVal);
        }
        sc.close();

        Solution solution = new Solution(cellToValue);
        solution.solve();
    }

}
