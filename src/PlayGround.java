import java.util.Arrays;

public class PlayGround {

    private static int n;
    private static boolean[][] gameArray;

    public static void setN(int fieldLen) {
        n = fieldLen;
        //TODO PM: resize the array
    }

    public static int getN() {
        return n;
    }

    public static boolean[][] getGameArray() {
        if (gameArray == null) {
            gameArray = new boolean[n][n];
            Arrays.fill(gameArray, Boolean.FALSE);
        }
        return gameArray;
    }

    public static void changeIndexValue(int i, int j) {
        gameArray[i][j] = !gameArray[i][j];
    }

    public static void init(int fieldLen) {
        n = fieldLen;
        gameArray = new boolean[n][n];
    }
}
