import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tempCardConnectPanel extends JPanel {
    private Controller controller;
    private CardConnectInputPanel cardNumber;
    private CardConnectInputPanel expiration;
    private CardConnectInputPanel cvc;
    private CardConnectInputPanel pw;
    private ButtonGroup bankGroup;
    private JRadioButton[] banks;
    private JButton confirm;

    private GridBagConstraints gbc;

    public tempCardConnectPanel(){
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        bankGroup = new ButtonGroup();
        banks = new JRadioButton[4];

        banks[0] = new JRadioButton(new ImageIcon(getClass().getResource("images/BankIcon/sh.jpg")));
        banks[1] = new JRadioButton(new ImageIcon(getClass().getResource("images/BankIcon/kb.jpg")));
        banks[2] = new JRadioButton(new ImageIcon(getClass().getResource("images/BankIcon/nh.jpg")));
        banks[3] = new JRadioButton(new ImageIcon(getClass().getResource("images/BankIcon/hn.jpg")));

        bankGroup.add(banks[0]);
        bankGroup.add(banks[1]);
        bankGroup.add(banks[2]);
        bankGroup.add(banks[3]);

        cardNumber = new CardConnectInputPanel("카드 번호", "-없이 숫자만 입력해주세요.");
        expiration = new CardConnectInputPanel("유효 기간", "월/년 순서로 4자리 숫자를 입력해주세요");
        cvc = new CardConnectInputPanel("CVC 번호", "카드 뒷면의 CVC번호 3자리를 입력해주세요");
        pw = new CardConnectInputPanel("비밀번호", "4자리 숫자를 입력해주세요");

        confirm = new JButton(new ImageIcon(getClass().getResource("images/button/mypage_makecard_btn.jpg")));
        confirm.setBorderPainted(false);
        confirm.setContentAreaFilled(false);
        confirm.setFocusPainted(false);

        for (int i = 0; i < banks.length; ++i){
            Controller.locateXY(gbc, i, 0, 1, 1);
            this.add(banks[i], gbc);
        }
        Controller.locateXY(gbc, 0, 1, 1, 1);
        this.add(cardNumber, gbc);
        Controller.locateXY(gbc, 0, 2, 1, 1);
        this.add(expiration, gbc);
        Controller.locateXY(gbc, 0, 3, 1, 1);
        this.add(cvc, gbc);
        Controller.locateXY(gbc, 0, 4, 1, 1);
        this.add(pw, gbc);
        Controller.locateXY(gbc, 0, 5, 1, 3);
        this.add(new JLabel(" "));
        Controller.locateXY(gbc, 0, 8, 1, 1);
        this.add(confirm, gbc);

    }//

    public String getCardNumber() {
        return cardNumber.getInputFieldValue();
    }

    public String getExpiration() {
        return expiration.getInputFieldValue();
    }

    public String getCvc() {
        return cvc.getInputFieldValue();
    }

    public String getPw() {
        return pw.getInputFieldValue();
    }

    public JButton getConfirm() {
        return confirm;
    }
}//