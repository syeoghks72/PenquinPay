import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CardConnectInputPanel extends JPanel {
    private HintTextField inputField;
    private JLabel explain;

    public CardConnectInputPanel(String title, String explainStr){
        this.setLayout(new BorderLayout());

        inputField = new HintTextField(title);
        inputField.setBackground(null);
        inputField.setHorizontalAlignment(JTextField.LEFT);
        inputField.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(000,126,255)));
        inputField.setFont(new Font("SansSerif",Font.TRUETYPE_FONT,40));
        inputField.setPreferredSize(new Dimension(620,100));

        explain = new JLabel(explainStr);
        explain.setForeground(Color.gray);
        explain.setFont(new Font("Sanserif",Font.BOLD,15));
        explain.setBorder(new EmptyBorder(0,0,0,0));
        explain.setPreferredSize(new Dimension(620,20));

        this.add(inputField, BorderLayout.CENTER);
        this.add(explain, BorderLayout.SOUTH);
    }

    public String getInputFieldValue(){
        return inputField.getText();
    }
}
