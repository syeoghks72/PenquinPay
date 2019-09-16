import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardConnectPanel extends JPanel {
    private Controller controller;
    private JPanel topBankIcons;
    private String selectedBank = "신한";
    private CardConnectInputPanel cardNumber;
    private CardConnectInputPanel expiration;
    private CardConnectInputPanel cvc;
    private CardConnectInputPanel pw;
    private ButtonGroup bankGroup;
    private JRadioButton[] banks;
    private JButton confirm;

    private GridBagConstraints gbc;

    public CardConnectPanel(Controller c){
        this.controller = c;
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        bankGroup = new ButtonGroup();
        banks = new JRadioButton[4];

        banks[0] = new JRadioButton(new ImageIcon(getClass().getResource("images/BankIcon/sh_active.jpg")));
        banks[0].setSelected(true);
        banks[0].setBorder(BorderFactory.createLineBorder(Color.BLUE, 20));
        banks[0].setName("신한");
        banks[0].addItemListener(controller);
        banks[1] = new JRadioButton(new ImageIcon(getClass().getResource("images/BankIcon/kb.jpg")));
        banks[1].setName("국민");
        banks[1].addItemListener(controller);
        banks[2] = new JRadioButton(new ImageIcon(getClass().getResource("images/BankIcon/nh.jpg")));
        banks[2].setName("농협");
        banks[2].addItemListener(controller);
        banks[3] = new JRadioButton(new ImageIcon(getClass().getResource("images/BankIcon/hn.jpg")));
        banks[3].setName("하나");
        banks[3].addItemListener(controller);

        bankGroup.add(banks[0]);
        bankGroup.add(banks[1]);
        bankGroup.add(banks[2]);
        bankGroup.add(banks[3]);

        topBankIcons = new JPanel();
        topBankIcons.setPreferredSize(new Dimension(600, 100));
        topBankIcons.setLayout(new GridLayout(1,4));
        for(JRadioButton temp : banks){
            topBankIcons.add(temp);
        }

        cardNumber = new CardConnectInputPanel("카드 번호", "-없이 숫자만 입력해주세요.");
        expiration = new CardConnectInputPanel("유효 기간", "월/년 순서로 4자리 숫자를 입력해주세요");
        cvc = new CardConnectInputPanel("CVC 번호", "카드 뒷면의 CVC번호 3자리를 입력해주세요");
        pw = new CardConnectInputPanel("비밀번호", "4자리 숫자를 입력해주세요");

        confirm = new JButton(new ImageIcon(getClass().getResource("images/button/mypage_makecard_btn.jpg")));
        confirm.setBorderPainted(false);
        confirm.setContentAreaFilled(false);
        confirm.setFocusPainted(false);
        confirm.addActionListener(controller);

        Controller.locateXY(gbc, 0, 0, 1, 1);
        this.add(topBankIcons, gbc);
        Controller.locateXY(gbc, 0, 1, 1, 1);
        this.add(cardNumber, gbc);
        Controller.locateXY(gbc, 0, 2, 1, 1);
        this.add(expiration, gbc);
        Controller.locateXY(gbc, 0, 3, 1, 1);
        this.add(cvc, gbc);
        Controller.locateXY(gbc, 0, 4, 1, 1);
        this.add(pw, gbc);
        Controller.locateXY(gbc, 0, 5, 1, 1);
        this.add(new JLabel(" "));
        gbc.insets = new Insets(50, 0, 0, 0);
        Controller.locateXY(gbc, 0, 6, 1, 1);
        this.add(confirm, gbc);

    }//

    public void refreshButtonImage(){
        banks[0].revalidate();
        banks[0].repaint();
        banks[1].revalidate();
        banks[1].repaint();
        banks[2].revalidate();
        banks[2].repaint();
        banks[3].revalidate();
        banks[3].repaint();
    }

    public JRadioButton[] getBanks() {
        return banks;
    }

    public String getSelectedBank() {
        return selectedBank;
    }

    public void setSelectedBank(String selectedBank) {
        this.selectedBank = selectedBank;
    }

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