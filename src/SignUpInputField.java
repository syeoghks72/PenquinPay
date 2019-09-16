import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SignUpInputField extends JPanel {
    JTextField inputfield;

    public SignUpInputField(int flag, String str, String imagePath){
        this.setPreferredSize(new Dimension(640, 100));
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);
        this.getInsets(new Insets(30, 0, 30, 0));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel icon = new JLabel(new ImageIcon(getClass().getResource(imagePath)));
        icon.setPreferredSize(new Dimension(80,100));

        inputfield  = null;
        switch (flag){
            case 1:{
                inputfield = new HintTextField(str);
                break;
            }
            case 2: {
                inputfield = new HintPassField(str);
                break;
            }
        }

        inputfield.setPreferredSize(new Dimension(540, 100));
        inputfield.setBorder(new EmptyBorder(0,0,0,0));


        Controller.locateXY(gbc, 0, 0, 1, 1);
        this.add(icon, gbc);
        Controller.locateXY(gbc, 1, 0, 1, 1);
        this.add(inputfield, gbc);
    }

    public String getInputText(){
        return this.inputfield.getText();
    }

}
