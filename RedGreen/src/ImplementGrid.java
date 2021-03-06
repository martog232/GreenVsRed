import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ImplementGrid implements Grid{
    private long[][] matrix;

    private final List<Integer> greenNumbersOfChange = new ArrayList<>(List.of(0, 1, 4, 5, 7, 8));

    private final List<Integer> redNumbersOfChange = new ArrayList<>(List.of(0, 1, 2, 4, 5, 7, 8));

    public ImplementGrid(int rows, int columns) {
        setMatrixSize(rows, columns);
    }

    @Override
    public long getGenerationsCount(GridCell cell, int generationTurns) {
        long generationCount = 0;

        if (correctSize(matrix, cell.getRow(), cell.getColumn())) {
            for (int i = 0; i < generationTurns; i++) {
                this.matrix = getNextGen(matrix);

                if (matrix[cell.getRow()][cell.getColumn()] == 1) {
                    generationCount += 1;
                }
            }

            return generationCount;
        }

        System.out.printf("%s%n", ErrorMessages.INCORRECT_COORDINATES);
        return generationCount;
    }

    @Override
    public boolean correctSize(long[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[row].length;
    }

    @Override
    public long[][] getNextGen(long[][] matrix) {
        long[][] resultGrid = Arrays.stream(matrix).map(long[]::clone).toArray(long[][]::new);
        GridCell currentCell;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                currentCell = new GridCell(matrix, row, col);
                switch (currentCell.getColor()) {
                    case RED:
                        if (ChangeCellColor(currentCell)) {
                            resultGrid[row][col] = 1;
                        }
                        break;
                    case GREEN:
                        if (ChangeCellColor(currentCell)) {
                            resultGrid[row][col] = 0;
                        }
                        break;
                }
            }
        }

        return resultGrid;
    }

    @Override
    public boolean ChangeCellColor(GridCell currentCell) {
        List<Integer> neighborsList = getNeighbors(this.matrix, currentCell);
        int greenNeighboursCount = (int) neighborsList.stream().filter(e -> e == 1).count();

        switch (currentCell.getColor()) {
            case GREEN:
                return greenNumbersOfChange.contains(greenNeighboursCount);
            case RED:
                return !redNumbersOfChange.contains(greenNeighboursCount);
        }

        return false;
    }

    @Override
    public List<Integer> getNeighbors(long[][] grid, GridCell cell) {
        List<Integer> surroundings = new ArrayList<>();

        for (int rowIndexLimit = -1; rowIndexLimit <= 1; rowIndexLimit++) {
            for (int columnIndexLimit = -1; columnIndexLimit <= 1; columnIndexLimit++) {
                int row = cell.getRow() + rowIndexLimit;
                int column = cell.getColumn() + columnIndexLimit;

                if (correctSize(grid, row, column)) {
                    if ((rowIndexLimit != 0) || (columnIndexLimit != 0)) {
                        surroundings.add((int) grid[row][column]);
                    }
                }
            }
        }

        return surroundings;
    }

    private void checkGridSize(long width, long height) {
        if (width > height) {
            System.out.printf("%s%n", ErrorMessages.WIDTH_GREATER_HEIGHT);
        } else if (width > 999 || height > 999) {
            System.out.printf("Width %s%n", ErrorMessages.SIDE_GREATER_999);
        }
    }


    public long[][] getMatrix() {
        return this.matrix;
    }

    public void setMatrixSize(int width, int height) {
        checkGridSize(width, height);

        this.matrix = new long[width][height];
    }
}

