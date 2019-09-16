import javax.swing.*;
import java.awt.*;

public class NumPadButton extends JButton {
    private Controller controller;

    public NumPadButton(String value, Controller c){
        this.controller = c;

        this.setIcon(new ImageIcon(getClass().getResource("images/numPad/"+value+".png")));
        this.setName(value);

        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);

        this.addActionListener(controller);
    }
}
