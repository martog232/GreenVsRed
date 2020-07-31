public class GridCell {
    private int row;

    private int column;

    private ColorCell color;


    public GridCell(long[][] grid, int row, int column) {
        setRow(row);
        setColumn(column);
        setColor(grid, row, column);
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public ColorCell getColor() {
        return this.color;
    }

    public void setColor(long[][] grid, int width, int height) {
        if (grid[width][height] == 1) {
            this.color = ColorCell.GREEN;
        } else if (grid[width][height] == 0){
            this.color = ColorCell.RED;
        }
    }
    public enum ColorCell {
        RED, GREEN}

}
