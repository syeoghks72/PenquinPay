import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    private Controller controller;
    private JLabel topImage;
    private String[] topImageRelativePath;
    private int[] topImageOnOffFlag = {1,0,0,0};

    public TopPanel(Controller c){
        this.controller = c;
        this.setSize(720, 100);
        this.setLayout(new GridLayout(1,1));

        topImageRelativePath = new String[4];
        topImageRelativePath[0] = "images/top/top_sendmoney.jpg";
        topImageRelativePath[1] = "images/top/top_lookup.jpg";
        topImageRelativePath[2] = "images/top/top_timeline.jpg";
        topImageRelativePath[3] = "images/top/top_mypage.jpg";

        topImage = new JLabel(new ImageIcon(getClass().getResource(topImageRelativePath[0])));
        this.add(topImage);
    }

    public void setImageIcon(int flag){
        this.topImage.setIcon(new ImageIcon(getClass().getResource(topImageRelativePath[flag])));
        setDockFlagOnOff(flag);
    }

    public void setDockFlagOnOff(int position){
        for(int i = 0; i < topImageOnOffFlag.length; ++i){
            if(i == position){
                topImageOnOffFlag[position] = 1;
            }
            else{
                topImageOnOffFlag[i] = 0;
            }
        }
    }
}
