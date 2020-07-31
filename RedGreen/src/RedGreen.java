
import java.util.Arrays;
import java.util.Scanner;

public class RedGreen {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int[] size = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();

    ImplementGrid grid = new ImplementGrid(size[0], size[1]);
    for (int i = 0; i < grid.getMatrix().length; i++) {
        long[] line = Arrays.stream(scanner.nextLine().split("")).mapToLong(Long::parseLong).toArray();

        grid.getMatrix()[i] = line;
    }

    String[] lastArguments = scanner.nextLine().split(", ");

    int x1 = Integer.parseInt(lastArguments[0]);
    int y1 = Integer.parseInt(lastArguments[1]);
    GridCell targetCell = new GridCell(grid.getMatrix(), x1, y1);

    int generationTimes = Integer.parseInt(lastArguments[2]);
    long greenCount = grid.getGenerationsCount(targetCell, generationTimes);

    System.out.printf("# expected result: %d ", greenCount);
}
}

