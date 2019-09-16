import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;


public class SendMoneyPanel extends JPanel {
    private Controller controller;
    private JLabel money;
    private String moneyStr;
    private JLabel won;
    private JButton[] numPad;
    private JList myAccounts;
    private String seletecdAccount;
    private String[] banksList;
    private String[] nameBanks, numBanks;
    private HintTextField receiverAccount;
    private JPanel myAccountAndReceiverAccount;
    private JButton send;
    private GridBagConstraints gbc;
    private JScrollPane bankScrollpane;

    public SendMoneyPanel(Controller c, String[] nameBanks, String[] numBanks) {
        this.controller = c;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.white);
        this.nameBanks = nameBanks;
        this.numBanks = numBanks;
        this.banksList = new String[nameBanks.length];

        for(int i = 0; i < numBanks.length; ++i){
            banksList[i] = nameBanks[i]+"|"+numBanks[i];
        }

        gbc = new GridBagConstraints();

        moneyStr = "";
        money = new JLabel(moneyStr);
        money.setHorizontalAlignment(JTextField.RIGHT);
        money.setBackground(Color.white);
        money.setBorder(BorderFactory.createEmptyBorder());
        money.setFont(new Font("SansSerif",Font.BOLD,40));

        won = new JLabel("원");
        won.setHorizontalAlignment(JTextField.LEFT);
        won.setFont(new Font("SansSerif",Font.BOLD,40));

        send = new JButton(new ImageIcon(getClass().getResource("images/button/sendmoney_btn.jpg")));
        send.addActionListener(controller);
        send.setBorderPainted(false);
        send.setContentAreaFilled(false);
        send.setFocusPainted(false);


        // set numPad Image
        numPad = new JButton[12];

        for(int i = 0; i < 9; ++i){
            numPad[i] = new NumPadButton((i+1)+"", controller);
        }

        numPad[9] = new NumPadButton("cancel", controller);
        numPad[10] = new NumPadButton("0", controller);
        numPad[11] = new NumPadButton("delete", controller);

        Controller.locateXY(gbc, 0, 0, 2, 1);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(money,gbc);

        myAccounts = new JList(banksList);
        myAccounts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        myAccounts.setFont(new Font("SansSerif", Font.PLAIN, 20));
//        myAccounts.getInsets(new Insets(0,10,0,0));
        myAccounts.addListSelectionListener(controller);
//        myAccounts.setPreferredSize(new Dimension(120, 100));

        bankScrollpane = new JScrollPane(myAccounts);
        bankScrollpane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        bankScrollpane.setPreferredSize(new Dimension(600, 80));

        receiverAccount = new HintTextField("상대방 계좌번호를 입력해주세요");
        receiverAccount.setFont(new Font("SansSerif", Font.BOLD, 35));
        receiverAccount.setColumns(20);
        receiverAccount.setHorizontalAlignment(SwingConstants.CENTER);
        receiverAccount.setBorder(new EmptyBorder(0,0,0,0));
        receiverAccount.setPreferredSize(new Dimension(300, 100));

//        myAccountAndReceiverAccount = new JPanel();
//        myAccountAndReceiverAccount.setLayout(new GridLayout(1,2));
//        myAccountAndReceiverAccount.add(bankScrollpane);
//        myAccountAndReceiverAccount.add(receiverAccount);
//
//        Controller.locateXY(gbc, 0, 5, 3, 1);
//        this.add(myAccountAndReceiverAccount, gbc);
        gbc.fill = GridBagConstraints.NONE;
        Controller.locateXY(gbc, 0, 5, 3, 1);
        this.add(bankScrollpane, gbc);

        gbc.insets = new Insets(30, 0, 30, 0);
        gbc.fill = GridBagConstraints.NONE;
        Controller.locateXY(gbc, 0, 6, 3, 1);
        this.add(receiverAccount,gbc);


        // gbc.fill setDefault
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0,0,0,0);

        Controller.locateXY(gbc, 2, 0, 1, 1);
        this.add(won, gbc);

        for(int y = 1; y < 5; ++y){
            for(int x = 0; x < 3; ++x){
                Controller.locateXY(gbc, x, y, 1, 1);

                switch (y){
                    case 1 : {
                        this.add(numPad[x], gbc);
                        break;
                    }
                    case 2 : {
                        this.add(numPad[x+3], gbc);
                        break;
                    }
                    case 3 : {
                        this.add(numPad[x+6], gbc);
                        break;
                    }
                    case 4 : {
                        this.add(numPad[x+9], gbc);
                        break;
                    }
                }
            }
        }

        Controller.locateXY(gbc, 0, 7, 3, 1);
        this.add(send, gbc);

    }

    public JScrollPane getBankScrollpane() {
		return bankScrollpane;
	}

	public void setBankScrollpane(JScrollPane bankScrollpane) {
		this.bankScrollpane = bankScrollpane;
	}

	public JButton getSend() { return send; }

    public String getReceiverAccount() {
        return receiverAccount.getText();
    }

    public String getMoney(){
        return this.money.getText();
    }

    public int getMoneyStrLeng(){
        return moneyStr.length();
    }

    public JButton[] getNumPad(){
        return this.numPad;
    }

    public String getSeletecdAccount() {
        return seletecdAccount;
    }

    public void setSeletecdAccount(String seletecdAccount) {
        this.seletecdAccount = seletecdAccount;
    }

    public String[] getBanksList() {
        return banksList;
    }

    public JList getMyAccounts() {
        return myAccounts;
    }

    public void setMoney(String value){
        this.moneyStr += value;
        this.money.setText(this.money.getText() + value);
    }

    public void subMoney() {
        this.moneyStr = moneyStr.substring(0, moneyStr.length()-1);
        this.money.setText(moneyStr);

    }
    
    public void clearMoney(){
        this.moneyStr = "";
        this.money.setText(moneyStr);
    }


}//SendMoneyPanel