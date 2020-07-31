import java.util.List;

public interface Grid {
    long getGenerationsCount(GridCell cell, int generationTurns);

    boolean correctSize(long[][] grid, int row, int col);

    long[][] getNextGen(long[][] matrix);

    boolean ChangeCellColor(GridCell currentCell);

    List<Integer> getNeighbors(long[][] grid, GridCell cell);
}
