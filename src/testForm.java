import javax.swing.*;
import java.awt.*;

public class testForm {
    private Controller controller;
    public testForm(){
//        this.controller = c;
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720,1280);

        Container container = frame.getContentPane();

        /***---------------------------***/

        container.add(new tempCardConnectPanel());


        /***---------------------------***/

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new testForm();
    }
}