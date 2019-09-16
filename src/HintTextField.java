import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class HintTextField extends JTextField implements FocusListener {

    private final String hint;
    private boolean showingHint;
    public HintTextField(final String hint) {
        super(hint);
        this.hint = hint;
        this.showingHint = true;
        super.addFocusListener(this);
        this.setForeground(Color.gray);
        this.setFont(new Font("SansSerif", Font.BOLD, 25));

    }

    @Override
    public void focusGained(FocusEvent e) {
        setForeground(Color.BLACK);
        if(this.getText().isEmpty()) {
            super.setText("");
            showingHint = false;
        }
    }
    @Override
    public void focusLost(FocusEvent e) {
        if(this.getText().isEmpty()) {
            setForeground(Color.gray);
            super.setText(hint);

            showingHint = true;
        }
    }
    @Override
    public String getText() {
        return showingHint ? "" : super.getText();
    }

}

