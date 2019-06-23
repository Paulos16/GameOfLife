import javax.swing.*;
import java.awt.*;

public class GameGrid extends JPanel {

    private static Block[][] grid;
    private static int underpopulation;
    private static int overpopulation;
    private static int reproduction;

    public GameGrid(int dimension, int underpopulation, int overpopulation, int reproduction) {
        super();
        this.underpopulation = underpopulation;
        this.overpopulation = overpopulation;
        this.reproduction = reproduction;

        this.setLayout(new GridLayout(dimension, dimension, 1, 1));
        this.setSize(700, 700);

        grid = new Block[dimension][dimension];

        for (int i = 0; i < dimension; i++) {

            for (int j = 0; j < dimension; j++) {


                grid[i][j] = new Block(i, j);
                this.add(grid[i][j]);
            }
        }
    }


    public static void playOneStep() {

        Block[][] nextGrid = new Block[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                nextGrid[i][j] = new Block(i, j);
            }
        }


        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {

                int activeNeighbors = countActiveNeighbors(i, j);

                if (activeNeighbors < underpopulation || activeNeighbors > overpopulation) {
                    nextGrid[i][j].deactivate();

                } else if (activeNeighbors == reproduction) {
                    nextGrid[i][j].activate();

                } else {
                    nextGrid[i][j] = grid[i][j];
                }
            }
        }


        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {

                if (grid[i][j].isActive() != nextGrid[i][j].isActive()) {
                    grid[i][j].changeState();
                }
            }
        }
    }


    public static void clearGrid() {
        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {

                grid[i][j].deactivate();
            }
        }
    }

    private static int countActiveNeighbors(int i, int j) {

        int count = 0;
        int colSize = grid.length-1;
        int rowSize = grid[0].length-1;


            // CORNERS:
        if (i == 0 && j == 0) { // top left corner

            if (grid[0][1].isActive()) count++;
            if (grid[1][0].isActive()) count++;
            if (grid[1][1].isActive()) count++;

        } else if (i == 0 && j == rowSize) { // top right corner

            if (grid[0][rowSize-1].isActive()) count++;
            if (grid[1][rowSize-1].isActive()) count++;
            if (grid[1][rowSize].isActive()) count++;

        } else if (i == colSize && j == 0) { // bottom left corner

            if (grid[colSize-1][0].isActive()) count++;
            if (grid[colSize-1][1].isActive()) count++;
            if (grid[colSize][1].isActive()) count++;

        } else if (i == colSize && j == rowSize) { // bottom right corner

            if (grid[colSize-1][rowSize-1].isActive()) count++;
            if (grid[colSize-1][rowSize].isActive()) count++;
            if (grid[colSize][rowSize-1].isActive()) count++;

            // BORDERS:
        } else if (i == 0 && j > 0 && j < rowSize) { // top border

            if (grid[0][j-1].isActive()) count++;
            if (grid[1][j-1].isActive()) count++;
            if (grid[1][j].isActive()) count++;
            if (grid[1][j+1].isActive()) count++;
            if (grid[0][j+1].isActive()) count++;

        } else if (j == 0 && i > 0 && i < colSize) { // left border

            if (grid[i-1][0].isActive()) count++;
            if (grid[i-1][1].isActive()) count++;
            if (grid[i][1].isActive()) count++;
            if (grid[i+1][1].isActive()) count++;
            if (grid[i+1][0].isActive()) count++;

        } else if (i == colSize && j > 0 && j < rowSize) { // bottom border

            if (grid[colSize][j-1].isActive()) count++;
            if (grid[colSize-1][j-1].isActive()) count++;
            if (grid[colSize-1][j].isActive()) count++;
            if (grid[colSize-1][j+1].isActive()) count++;
            if (grid[colSize][j+1].isActive()) count++;

        } else if (j == rowSize && i > 0 && i < rowSize) { // right border

            if (grid[i-1][rowSize].isActive()) count++;
            if (grid[i-1][rowSize-1].isActive()) count++;
            if (grid[i][rowSize-1].isActive()) count++;
            if (grid[i+1][rowSize-1].isActive()) count++;
            if (grid[i+1][rowSize].isActive()) count++;

            // THE REST:
        } else {

            if (grid[i-1][j-1].isActive()) count++;
            if (grid[i-1][j].isActive()) count++;
            if (grid[i-1][j+1].isActive()) count++;
            if (grid[i][j-1].isActive()) count++;
            if (grid[i][j+1].isActive()) count++;
            if (grid[i+1][j-1].isActive()) count++;
            if (grid[i+1][j].isActive()) count++;
            if (grid[i+1][j+1].isActive()) count++;

        }

        return count;
    }
}
