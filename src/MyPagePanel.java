import javax.swing.*;
import java.awt.*;

public class MyPagePanel extends JPanel{
    private Controller controller;
    private GridBagConstraints gbc;
    private JLabel myPicture;
    private JLabel profile_text;
    private JLabel monthCardMoney_text;
    private JLabel name;
    private JLabel phoneNumber;
    private JPanel profile;
    private CardOrAccountPanel spentMoney;
    private JButton addCardButton;


    public MyPagePanel(Controller c, String nameParam, String phoneNumberParam) {
        this.controller = c;
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,0,15,0);

        profile_text = this.makeTextLabel("프로필");

        profile = new JPanel();
        profile.setLayout(new GridBagLayout());
        profile.setBackground(Color.WHITE);
        profile.setPreferredSize(new Dimension(640, 300));

        myPicture = new JLabel(new ImageIcon(getClass().getResource("images/mypage/mypage_profile_image.png")));

        name = new JLabel(nameParam);
        name.setFont(new Font("SansSerif", Font.TRUETYPE_FONT, 17));
        name.setForeground(Color.GRAY);

        phoneNumber = new JLabel(phoneNumberParam);
        phoneNumber.setFont(new Font("SansSerif", Font.TRUETYPE_FONT, 17));
        phoneNumber.setForeground(Color.GRAY);

        this.locateXY(1, 0, 1, 3);
        profile.add(myPicture, gbc);
        this.locateXY(1, 3, 1, 2);
        profile.add(name, gbc);
        this.locateXY( 1, 5, 1, 1);
        profile.add(phoneNumber, gbc);

        monthCardMoney_text = this.makeTextLabel("이번달 카드값");
        spentMoney = new CardOrAccountPanel("images/mypage/mypage_spentMoney.jpeg", "120000");
        spentMoney.setMoney("120000원");

        addCardButton = new JButton(new ImageIcon(getClass().getResource("images/button/mypage_card_btn.jpg")));
        addCardButton.setBorderPainted(false);
        addCardButton.setContentAreaFilled(false);
        addCardButton.setFocusPainted(false);
        addCardButton.addActionListener(controller);

        this.locateXY(0,0, 1, 2);
        this.add(profile_text, gbc);
        this.locateXY(0,2, 1, 3);
        this.add(profile, gbc);
        this.locateXY(0,5, 1, 2);
        this.add(monthCardMoney_text, gbc);
        this.locateXY(0,7, 1, 2);
        this.add(spentMoney, gbc);
        this.locateXY(0, 9, 1, 1);
        this.add(addCardButton, gbc);

    }

    public void locateXY(int x, int y, int w, int h){
        this.gbc.gridx = x;
        this.gbc.gridy = y;
        this.gbc.gridwidth = w;
        this.gbc.gridheight = h;

    }

    public JLabel makeTextLabel(String text){
        JLabel jLabel= new JLabel(text);
        jLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        jLabel.setPreferredSize(new Dimension(640, 100));

        return jLabel;
    }

    public JButton getAddCardButton(){
        return this.addCardButton;
    }
    public JLabel getMyName(){
        return name;
    }

    public JLabel getPhoneNumber(){
        return phoneNumber;
    }

	public CardOrAccountPanel getSpentMoney() {
		return spentMoney;
	}

	public void setSpentMoney(CardOrAccountPanel spentMoney) {
		this.spentMoney = spentMoney;
	}
    
}
