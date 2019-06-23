import javax.swing.*;
import java.util.concurrent.*;

public class Controls extends JPanel {

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> futureExecutor;
    private boolean running;

    public Controls(GameGrid gameGrid) {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setSize(700, 50);
        this.setBorder(BorderFactory.createTitledBorder("Controls"));

        //JButton start = new JButton("Start");
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        JButton oneStep = new JButton("Run One Step");
        JButton clear = new JButton("Clear");
        stop.setEnabled(false);

        JSlider speed = new JSlider();
        speed.setBorder(BorderFactory.createTitledBorder("Speed [ms]"));
        speed.setMinimum(100);
        speed.setMaximum(1100);
        speed.setMinorTickSpacing(100);
        speed.setMajorTickSpacing(200);
        speed.setPaintTicks(true);
        speed.setPaintTrack(true);
        speed.setSnapToTicks(true);
        speed.setPaintLabels(true);
        speed.setValue(300);

        speed.addChangeListener((l) -> {

            if (running) {
                futureExecutor.cancel(false);
                futureExecutor = executorService.scheduleAtFixedRate(GameGrid::playOneStep, 0, speed.getValue(), TimeUnit.MILLISECONDS);
            }
        });

        start.addActionListener((l) -> {
            this.running = true;

            futureExecutor = executorService.scheduleAtFixedRate(GameGrid::playOneStep, 0, speed.getValue(), TimeUnit.MILLISECONDS);


            stop.setEnabled(true);
            start.setEnabled(false);
        });

        stop.addActionListener((l) -> {

            futureExecutor.cancel(false);

            this.running = false;
            start.setEnabled(true);
            stop.setEnabled(false);
        });

        oneStep.addActionListener((l) -> {

            executorService.execute(GameGrid::playOneStep);
            //gameGrid.playOneStep();
        });

        clear.addActionListener((l) -> {

            executorService.execute(GameGrid::clearGrid);
        });

        this.add(start);
        this.add(stop);
        this.add(oneStep);
        this.add(clear);
        this.add(speed);

    }

}
