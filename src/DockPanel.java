import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class DockPanel extends JPanel {
    private Controller controller;
    private String[] dockButtonImgaeRelativePath;
    private DockButton[] dockButtons;
    private int[] dockFlag = {1,0,0,0};

    public DockPanel(Controller c){
        this.controller = c;
        dockButtonImgaeRelativePath = new String[8];
        dockButtons = new DockButton[4];

        this.setPreferredSize(new Dimension(720,110));
        this.setLayout(new GridLayout(1,4));

        dockButtonImgaeRelativePath[0] = "images/dock/dock_sendmoney.jpg";
        dockButtonImgaeRelativePath[1] = "images/dock/dock_lookup.jpg";
        dockButtonImgaeRelativePath[2] = "images/dock/dock_timeline.jpg";
        dockButtonImgaeRelativePath[3] = "images/dock/dock_mypage.jpg";
        dockButtonImgaeRelativePath[4] = "images/dock/dock_sendmoney_active.jpg";
        dockButtonImgaeRelativePath[5] = "images/dock/dock_lookup_active.jpg";
        dockButtonImgaeRelativePath[6] = "images/dock/dock_timeline_active.jpg";
        dockButtonImgaeRelativePath[7] = "images/dock/dock_mypage_active.jpg";

        System.out.println(dockButtonImgaeRelativePath[4]);
        dockButtons[0] = new DockButton(controller, dockButtonImgaeRelativePath[4]);
        dockButtons[0].addActionListener(controller);
        this.add(dockButtons[0]);

        for(int i = 1; i < dockButtons.length; ++i){
            dockButtons[i] = new DockButton(controller, dockButtonImgaeRelativePath[i]);
            dockButtons[i].addActionListener(controller);
            this.add(dockButtons[i]);
        }
    }

    public String[] getDockButtonImgaeRelativePath(){
        return dockButtonImgaeRelativePath;
    }

    public DockButton[] getDockButtons(){
        return dockButtons;
    }

    public int[] getDockFlag(){
        return dockFlag;
    }

    public void setOnOff(int position){
        this.getDockButtons()[position].setImageIcon(this.getDockButtonImgaeRelativePath()[position+4]);

    }

    public void setDockFlagOnOff(int position){
        for(int i = 0; i < dockFlag.length; ++i){
            if(i == position){
                dockFlag[position] = 1;
            }
            else{
                dockFlag[i] = 0;
            }
        }
    }

    public void changeImage(){

    }
}
