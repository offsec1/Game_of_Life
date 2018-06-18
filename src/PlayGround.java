import java.util.Arrays;

public class PlayGround {

    private static final int OFFSET = 1; //this is done because the edges are complicated to handle (that's the nicest way I recon)

    private static int n;
    private static boolean[][] gameArray;
    private static boolean[][] evolvedArray;

    public static void setN(int fieldLen) {
        n = fieldLen + OFFSET;
        gameArray = new boolean[n][n];
    }

    public static int getN() {
        return n - OFFSET;
    }

    public static boolean[][] getGameArray() {
        if (gameArray == null) {
            gameArray = new boolean[n][n];
        }
        return gameArray;
    }

    public static void changeIndexValue(int i, int j) {
        gameArray[i][j] = !gameArray[i][j];
    }

    public static void init(int fieldLen) {
        n = fieldLen + OFFSET;
        gameArray = new boolean[n][n];
    }

    public static void clearGameArray() {
        for (int i = 0; i < n; i++)
            Arrays.fill(gameArray[i], false);
    }

    /**
     * This is it! The evolving process
     */
    public static void evolve() {

        evolvedArray = copyArray(gameArray);

        for(int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {

                int countNeighbours = 0;

                //I'm at the edge so something has to work different...
                if (i == 0 || j == 0 || i == n - 1 || j == n - 1) {
                    //This means I'm ignoring the edges there are many other ways to handle this
                    //I like it more the way this works now than having a bigger array or something else
                }
                //check the neighbours
                else {
                    if (gameArray[i + 1][j])
                        countNeighbours++;
                    if (gameArray[i][j + 1])
                        countNeighbours++;
                    if (gameArray[i - 1][j])
                        countNeighbours++;
                    if (gameArray[i][j - 1])
                        countNeighbours++;
                    if (gameArray[i - 1][j - 1])
                        countNeighbours++;
                    if (gameArray[i + 1][j - 1])
                        countNeighbours++;
                    if (gameArray[i - 1][j + 1])
                        countNeighbours++;
                    if (gameArray[i + 1][j + 1])
                        countNeighbours++;
                }

                //rule 1: any live cell with fewer than two lie neighbours dies (underpopulation)
                if (gameArray[i][j] && countNeighbours < 2)
                    evolvedArray[i][j] = !evolvedArray[i][j];
                //rule 2: any live cell with more than three live neighbours dies (overpopulation)
                if (gameArray[i][j] && countNeighbours > 3)
                    evolvedArray[i][j] = !evolvedArray[i][j];
                //rule 3: any live cell with two or three live neighbours lives unchanged
                //nothing to do
                //rule 4: any dead cell with exactly three live neighbours will come to life
                if (!gameArray[i][j] && countNeighbours == 3)
                    evolvedArray[i][j] = !evolvedArray[i][j];
            }

        gameArray = copyArray(evolvedArray);
    }

    /**
     * Sync two 2D arrays
     * @param src source Array
     * @return cloned Array
     */
    private static boolean[][] copyArray(boolean[][] src) {

        boolean[][] dest = new boolean[src.length][];

        for (int i = 0; i < src.length; i++)
            dest[i] = src[i].clone();

        return dest;
    }

    public static void spawnGlider() {
        int middle = n / 2 - 1;

        changeIndexValue(middle, middle);
        middle++;
        changeIndexValue(middle, middle);
        changeIndexValue(middle + 1, middle);
        changeIndexValue(middle + 1, middle - 1);
        changeIndexValue(middle + 1, middle - 2);
    }

    public static void spawnSmallExploder() {
        int middle = n / 2 - 1;

        changeIndexValue(middle, middle);
        changeIndexValue(middle, middle + 1);
        changeIndexValue(middle, middle - 1);
        changeIndexValue(middle - 1, middle);
        changeIndexValue(middle + 1, middle - 1);
        changeIndexValue(middle + 1, middle + 1 );
        changeIndexValue(middle + 2, middle);
    }

    public static void spawnExploder() {
        int middle = n / 2 - 1;

        changeIndexValue(middle + 2, middle);
        changeIndexValue(middle - 2, middle);
        changeIndexValue(middle, middle + 2);
        changeIndexValue(middle - 1, middle + 2);
        changeIndexValue(middle - 2, middle + 2);
        changeIndexValue(middle + 1, middle + 2);
        changeIndexValue(middle + 2, middle + 2);
        changeIndexValue(middle, middle - 2);
        changeIndexValue(middle - 1, middle - 2);
        changeIndexValue(middle - 2, middle - 2);
        changeIndexValue(middle + 1, middle - 2);
        changeIndexValue(middle + 2, middle - 2);
    }

    public static void spawnSpaceShip() {
        int middle = n / 2 - 1;

        changeIndexValue(middle + 1, middle + 1);
        changeIndexValue(middle, middle + 2);
        changeIndexValue(middle - 1, middle + 2);
        changeIndexValue(middle - 2, middle + 2);
        changeIndexValue(middle - 2, middle + 1);
        changeIndexValue(middle - 2, middle);
        changeIndexValue(middle - 2, middle - 1);
        changeIndexValue(middle - 1, middle - 2);
        changeIndexValue(middle + 1, middle - 2);
    }

    public static void spawnTumbler() {
        int middle = n / 2 - 1;

        changeIndexValue(middle, middle + 1);
        changeIndexValue(middle, middle - 1);
        changeIndexValue(middle + 1, middle + 1);
        changeIndexValue(middle + 1, middle - 1);
        changeIndexValue(middle - 1, middle + 1);
        changeIndexValue(middle - 1, middle - 1);
        changeIndexValue(middle - 2, middle + 1);
        changeIndexValue(middle - 2, middle - 1);
        changeIndexValue(middle + 2, middle + 1);
        changeIndexValue(middle + 2, middle - 1);
        changeIndexValue(middle + 3, middle - 2);
        changeIndexValue(middle + 3, middle - 3);
        changeIndexValue(middle + 3, middle + 2);
        changeIndexValue(middle + 3, middle + 3);
        changeIndexValue(middle + 2, middle + 3);
        changeIndexValue(middle + 1, middle + 3);
        changeIndexValue(middle + 2, middle - 3);
        changeIndexValue(middle + 1, middle - 3);
        changeIndexValue(middle - 1, middle - 2);
        changeIndexValue(middle - 2, middle - 2);
        changeIndexValue(middle - 2, middle + 2);
        changeIndexValue(middle - 1, middle + 2);
    }

}
