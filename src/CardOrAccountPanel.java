import javax.imageio.ImageIO;
import javax.print.URIException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class CardOrAccountPanel extends JPanel {
    private Image backgroundImage;
    private JLabel money;
    private JLabel account;
    private GridBagConstraints gbc;

    public CardOrAccountPanel(String backgroundImagePath, String money){
        this.setPreferredSize(new Dimension(640, 120));
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        this.money = new JLabel(money);
        this.money.setFont(new Font("SansSerif", Font.BOLD, 25));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.gridheight = 2;
        gbc.weightx = 0.8;
        gbc.weighty = 0.8;
        this.add(new JLabel(""), gbc);

        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.2;
        this.add(this.money, gbc);

        try {
            backgroundImage = ImageIO.read(new File(getClass().getResource(backgroundImagePath).toURI()));
        } catch (IOException e){
            System.out.println("IO Error");
        } catch (URISyntaxException e){
            System.out.println("URISyntax Error");
        }

    }

    public CardOrAccountPanel(String backgroundImagePath, String account, String money){
        this.setPreferredSize(new Dimension(640, 120));
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        this.account = new JLabel(account);
        this.account.setFont(new Font("SansSerif", Font.PLAIN, 20));

        this.money = new JLabel(money);
        this.money.setFont(new Font("SansSerif", Font.PLAIN, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.gridheight = 2;
        gbc.weightx = 0.6;
        gbc.weighty = 0.5;
        this.add(new JLabel(""), gbc);

        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.PAGE_END;
        this.add(this.account, gbc);

        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.15;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(this.money, gbc);

        try {
            backgroundImage = ImageIO.read(new File(getClass().getResource(backgroundImagePath).toURI()));
        } catch (IOException e){
            System.out.println("IO Error");
        } catch (URISyntaxException e){
            System.out.println("URISyntax Error");
        }

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }

    public void setMoney(String money){
        this.money.setText(money);
    }

    public String getMoney(){
        return this.money.getText();
    }
}
