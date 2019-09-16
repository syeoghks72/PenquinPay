import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SignInPanel extends JPanel {
    private Controller controller;
    private JLabel logo;
    private HintTextField idInput;
    private HintPassField pwInput;
    private JButton confirmBtn;
    private JButton signUp;
    private GridBagConstraints gbc;

    public SignInPanel(Controller c){
        this.controller = c;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,20,0);

        logo = new JLabel(new ImageIcon(getClass().getResource("images/logo.png")));

        idInput = new HintTextField("  아이디 ");
        idInput.setPreferredSize(new Dimension(585, 80));

        pwInput = new HintPassField("  비밀번호 ");
        pwInput.setPreferredSize(new Dimension(585, 80));

        confirmBtn = new JButton(new ImageIcon(getClass().getResource("images/button/login_btn.jpg")));
        confirmBtn.setPreferredSize(new Dimension(580, 80));
        confirmBtn.requestFocusInWindow();
        confirmBtn.addActionListener(controller);

        signUp = new JButton(new ImageIcon(getClass().getResource("images/button/signup_btn.png")));
        signUp.setPreferredSize(new Dimension(580, 80));
        signUp.addActionListener(controller);

        gbc.anchor = GridBagConstraints.CENTER;
        Controller.locateXY(gbc, 0, 0, 1, 1);
        this.add(logo, gbc);

        gbc.insets = new Insets(0,0,0,0);
        Controller.locateXY(gbc,0,1,1, 1);
        this.add(idInput, gbc);
        Controller.locateXY(gbc,0,2,1, 1);
        this.add(pwInput, gbc);

        gbc.insets = new Insets(30,0,10,0);
        Controller.locateXY(gbc,0,3,1, 1);
        this.add(confirmBtn, gbc);

        gbc.insets = new Insets(0,0,0,0);
        Controller.locateXY(gbc,0,4,1, 1);
        this.add(signUp, gbc);


//        this.locateXY(0,4, 1, 1, 1, 0.2);
//        JLabel temp = new JLabel("아직도 펭귄페이 회원이 아니신가요?");
//        this.add(temp, gbc);
//        JLabel temp2 = new JLabel("회원 가입하기>>", SwingConstants.RIGHT);
//        this.locateXY(1, 4, 1, 1, 1, 0.2);
//        this.add(temp2, gbc);

    }

    public void locateXY(int x, int y, int w, int h, double wx, double wy){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbc.weightx = wx;
        gbc.weighty = wy;
    }

    public JButton getConfirmBtn(){
        return confirmBtn;
    }

    public JButton getSignUp() {
        return signUp;
    }

    public String getID(){
        return this.idInput.getText();
    }

    public String getPW(){
        return this.pwInput.getText();
    }
}
