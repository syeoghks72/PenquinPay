import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private Controller controller;
    private Container mainPane;
    private CardLayout mainCards;

    public MainFrame(Controller c){
        this.controller = c;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Penguin Pay");
        this.setSize(720,1280);
        this.setLocation(500, 50);

        mainPane = this.getContentPane();
        mainPane.setBackground(Color.WHITE);
        mainPane.setLayout(mainCards = new CardLayout());
        mainPane.addMouseListener(controller);

    }

    public CardLayout getMainCards(){
        return mainCards;
    }

    public void onVisible(){
        this.setVisible(true);
    }
}
