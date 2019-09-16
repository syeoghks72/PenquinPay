import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DockButton extends JButton {
    private Controller controller;

    public DockButton(Controller c, String imageRelativePath){
        this.controller = c;

        this.setBorder(new EmptyBorder(10,10,0,10));
        this.setImageIcon(imageRelativePath);
        this.addActionListener(controller);
    }

    public void setImageIcon(String imageRelativePath){
        this.setIcon(new ImageIcon(getClass().getResource(imageRelativePath)));
    }

}
