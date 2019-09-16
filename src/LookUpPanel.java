import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LookUpPanel extends JPanel {
    private ArrayList<CardOrAccountPanel> accounts;
    private CardOrAccountPanel sh;
    private CardOrAccountPanel kb;
    private CardOrAccountPanel nh;
    private CardOrAccountPanel hana;
    private String[] cardImages = new String[4];
    private JLabel myAccount_text;
    private GridBagConstraints gbc;
    private String[] nameBanks;
    private String[] moneyBanks;
    private String[] numBanks;


    public LookUpPanel(String[] nameBanks, String[] numBanks,String[] moneyBanks){
        this.nameBanks = nameBanks;
        this.moneyBanks = moneyBanks;
        this.numBanks = numBanks;
        this.accounts = new ArrayList<>();

        cardImages[0] = "images/LookUp/lookup_list01.jpg";
        cardImages[1] = "images/LookUp/lookup_list02.jpg";
        cardImages[2] = "images/LookUp/lookup_list03.jpg";
        cardImages[3] = "images/LookUp/lookup_list04.jpg";

        this.setLayout(new GridBagLayout());
        this.gbc = new GridBagConstraints();
        this.gbc.insets = new Insets(0,0,10,0);

        myAccount_text = Controller.makeTextLabel("나의 계좌");

        for(int i = 0; i < nameBanks.length; i++){
            if(nameBanks[i].equals("신한")){
                accounts.add(new CardOrAccountPanel(cardImages[0], numBanks[i], moneyBanks[i]));
            } else if(nameBanks[i].equals("국민")){
                accounts.add(new CardOrAccountPanel(cardImages[1], numBanks[i], moneyBanks[i]));
            } else if(nameBanks[i].equals("농협")){
                accounts.add(new CardOrAccountPanel(cardImages[2], numBanks[i], moneyBanks[i]));
            } else if(nameBanks[i].equals("하나")){
                accounts.add(new CardOrAccountPanel(cardImages[3], numBanks[i], moneyBanks[i]));
            } else{
                System.out.println("Error!");
            }
        }

        Controller.locateXY(gbc, 0, 0, 1, 2);
        this.add(myAccount_text, gbc);

        int temp = 2;

        for(CardOrAccountPanel panel : accounts) {
            Controller.locateXY(gbc,0, temp, 1, 1);
            this.add(panel, gbc);
            temp++;
        }

    }

    public void addAccountPanel(){

    }

    public void setShMoney(String money) {
        this.sh.setMoney(money);
    }

    public void setKbMoney(String money) {
        this.kb.setMoney(money);
    }

    public void setNhMoney(String money) {
        this.nh.setMoney(money);
    }

    public void setHanaMoney(String money) {
        this.hana.setMoney(money);
    }
}
