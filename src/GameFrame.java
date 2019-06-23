import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame(int dimension, int underpopulation, int overpopulation, int reproduction) {
        super("Game of Life");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700, 750);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setSize(500, 550);

        GameGrid gameGrid = new GameGrid(dimension, underpopulation, overpopulation, reproduction);
        Controls controls = new Controls(gameGrid);

        mainPanel.add(gameGrid);
        mainPanel.add(controls);

        this.add(mainPanel);
    }

}
