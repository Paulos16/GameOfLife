import javax.swing.*;
import java.awt.*;

public class Block extends JButton {

    private boolean active;
    private int i;
    private int j;


    public Block(int i, int j) {
        super();
        this.active = false;
        this.i = i;
        this.j = j;
        this.setBackground(Color.BLACK);
        this.setSize(10, 10);
        this.setOpaque(true);
        this.setBorderPainted(false);

        this.addActionListener((l) -> {
            this.changeState();
        });
    }


    public void activate() {
        this.setBackground(Color.WHITE);
        this.active = true;
    }

    public void deactivate() {
        this.setBackground(Color.BLACK);
        this.active = false;
    }

    public void changeState() {
        if (this.active) {
            this.setBackground(Color.BLACK);
            this.active = false;
        } else {
            this.setBackground(Color.WHITE);
            this.active = true;
        }
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "Block{" +
                "active=" + active +
                ", i=" + i +
                ", j=" + j +
                '}';
    }
}
