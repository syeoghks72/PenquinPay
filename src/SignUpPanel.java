import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SignUpPanel extends JPanel {
    private SignUpInputField name;
    private SignUpInputField pw;
    private SignUpInputField pwCheck;
    private SignUpInputField email;
    private SignUpInputField phoneNumber;
    private JLabel top;
    private JLabel requiredTitle;

    private JLabel acceptedTitle;
    private JButton confirmBtn;
    private Controller controller;
    private GridBagConstraints gbc;


    public SignUpPanel(Controller c){
        this.controller = c;
        this.setLayout(new BorderLayout());
        this.requestFocus();

        JPanel temp = new JPanel();
        temp.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        top = new JLabel(new ImageIcon(getClass().getResource("images/top/top_signup.jpg")));

        requiredTitle = Controller.makeTextLabel("필수항목");
        acceptedTitle = Controller.makeTextLabel("약관동의");
        confirmBtn = new JButton(new ImageIcon(getClass().getResource("images/button/signup_btn.png")));
        confirmBtn.setPreferredSize(new Dimension(600, 100));
        confirmBtn.addActionListener(controller);

        name = new SignUpInputField(1, "이름을 입력하세요", "images/signup/signup_icon_name.png");
        pw = new SignUpInputField(2, "비밀번호 (영문, 숫자, 특수문 8~15자)", "images/signup/signup_icon_password.png");
        pwCheck = new SignUpInputField(2, "비밀번호 재입력", "images/signup/signup_icon_password.png");
        email = new SignUpInputField(1, "이메일을 입력하세요", "images/signup/signup_icon_id.png");
        phoneNumber = new SignUpInputField(1, "휴대폰 번호를 입력하세요", "images/signup/signup_icon_phone.png");



        Controller.locateXY(gbc, 0, 0, 1,1);
        temp.add(requiredTitle, gbc);

        gbc.insets = new Insets(0,0, 10, 0);

        Controller.locateXY(gbc, 0,1, 1, 1);
        temp.add(name, gbc);
        Controller.locateXY(gbc, 0,2, 1, 1);
        temp.add(pw, gbc);
        Controller.locateXY(gbc, 0,3, 1, 1);
        temp.add(pwCheck, gbc);
        Controller.locateXY(gbc, 0,4, 1, 1);
        temp.add(email, gbc);
        Controller.locateXY(gbc, 0,5, 1, 1);
        temp.add(phoneNumber, gbc);

        gbc.insets = new Insets(30,0, 10, 0);
        Controller.locateXY(gbc, 0, 6, 1, 1);
        temp.add(confirmBtn, gbc);


        JScrollPane scrollPane = new JScrollPane(temp, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(720,800));
        scrollPane.setBorder(new EmptyBorder(1,1,1,1));
        scrollPane.setBackground(Color.WHITE);

        this.add(top, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

    }

//    public JPanel makeInputTextField(int flag, String str, String imagePath){
//        JPanel panel = new JPanel();
//        panel.setPreferredSize(new Dimension(640, 100));
////        panel.setBorder(new EmptyBorder(5,5,5,5));
//        panel.setLayout(new GridBagLayout());
//        panel.setBackground(Color.WHITE);
//        panel.getInsets(new Insets(30, 0, 30, 0));
//
//        JLabel icon = new JLabel(new ImageIcon(getClass().getResource(imagePath)));
//        icon.setPreferredSize(new Dimension(80,100));
//
//        JTextField field  = null;
//        switch (flag){
//            case 1:{
//                field = new HintTextField(str);
//                break;
//            }
//            case 2: {
//                field = new HintPassField(str);
//                break;
//            }
//        }
//
//        field.setPreferredSize(new Dimension(540, 100));
//        field.setBorder(new EmptyBorder(0,0,0,0));
//
//
//        Controller.locateXY(gbc, 0, 0, 1, 1);
//        panel.add(icon, gbc);
//        Controller.locateXY(gbc, 1, 0, 1, 1);
//        panel.add(field, gbc);
//
//        return panel;
//    }

    @Override
    public String getName() {
        return name.getInputText();
    }

    public String getPw() {
        return pw.getInputText();
    }

    public String getPwCheck() {
        return pwCheck.getInputText();
    }

    public String getEmail() {
        return email.getInputText();
    }

    public String getPhoneNumber() {
        return phoneNumber.getInputText();
    }

    public JButton getConfirmBtn() {
        return confirmBtn;
    }
}
