import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SetupFrame extends JFrame {

    public SetupFrame() {
        super("Game Configuration");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(200, 250);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

        JSpinner dimension = new JSpinner();
        JSpinner underpopulation = new JSpinner();
        JSpinner overpopulation = new JSpinner();
        JSpinner reproduction = new JSpinner();
        JButton startGame = new JButton("Start game");

        dimension.setBorder(BorderFactory.createTitledBorder("Dimension"));
        underpopulation.setBorder(BorderFactory.createTitledBorder("Underpopulation (less than)"));
        overpopulation.setBorder(BorderFactory.createTitledBorder("Overpopulation (more than)"));
        reproduction.setBorder(BorderFactory.createTitledBorder("Reproduction (exactly)"));

        SpinnerListModel dimensionSpinnerModel = new SpinnerListModel();
        SpinnerListModel underpopulationSpinnerModel = new SpinnerListModel();
        SpinnerListModel overpopulationSpinnerModel = new SpinnerListModel();
        SpinnerListModel reproductionSpinnerModel = new SpinnerListModel();

        dimensionSpinnerModel.setList(IntStream.rangeClosed(2, 21).boxed().collect(Collectors.toList()));
        underpopulationSpinnerModel.setList(Arrays.asList(2));
        overpopulationSpinnerModel.setList(Arrays.asList(3));
        reproductionSpinnerModel.setList(Arrays.asList(3));

        dimension.setModel(dimensionSpinnerModel);
        underpopulation.setModel(underpopulationSpinnerModel);
        overpopulation.setModel(overpopulationSpinnerModel);
        reproduction.setModel(reproductionSpinnerModel);

        ((JSpinner.DefaultEditor) dimension.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) underpopulation.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) overpopulation.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) reproduction.getEditor()).getTextField().setEditable(false);

        startGame.addActionListener((l) -> {
            int gameDimension = (int) dimension.getValue();
            int gameUnderpopulation = (int) underpopulation.getValue();
            int gameOverpopulation = (int) overpopulation.getValue();
            int gameReproduction = (int) reproduction.getValue();

            GameFrame gameFrame = new GameFrame(gameDimension, gameUnderpopulation, gameOverpopulation, gameReproduction);
            gameFrame.setVisible(true);
            this.dispose();
        });

        JPanel btmPanel = new JPanel();
        btmPanel.setLayout(new BorderLayout());
        btmPanel.add(startGame, BorderLayout.CENTER);

        jp.add(dimension);
        jp.add(underpopulation);
        jp.add(overpopulation);
        jp.add(reproduction);
        jp.add(btmPanel);


        this.add(jp);
    }

}
