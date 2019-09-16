import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class TimeLinePanel extends JPanel{
    private ArrayList<JPanel> data;
    private JLabel currentMonth;
    private BoxLayout dataPanel;
    private GridBagConstraints gbc;


    public TimeLinePanel(){
        this.setLayout(new GridBagLayout());
        this.gbc = new GridBagConstraints();
        this.data = new ArrayList<>();

        //data.add(makeDataPanel("-15000원", "신한체크", "써니사이드업", "2019/06/19"));
        //data.add(makeDataPanel("+8000원", "신한할인캐쉬", "내 신한 계좌", "2019/06/17"));
        
        currentMonth = Controller.makeTextLabel("조회결과");

        Controller.locateXY(gbc, 0, 0, 1,1);
        this.add(currentMonth, gbc);

        //this.addDataPanel(0, 1);
        //this.addDataPanel(1, 3);

    }

    public void addDataPanel(int dataIndex, int y){
        Controller.locateXY(gbc, 0, y, 1, 1);
        this.add(data.get(dataIndex),gbc);
        Controller.locateXY(gbc, 0, y+1, 1, 1);
        this.add(new JLabel(" "), gbc);
    }
    
    public void addDataPanel2(ArrayList<JPanel> arrayList,int dataIndex, int y){
        Controller.locateXY(gbc, 0, y, 1, 1);
        this.add(arrayList.get(dataIndex),gbc);
        Controller.locateXY(gbc, 0, y+1, 1, 1);
        this.add(new JLabel(" "), gbc);
    }

    public JPanel makeDataPanel(String money, String card, String destination, String date){
        JPanel data = new JPanel();
        data.setPreferredSize(new Dimension(640,150));
        data.setLayout(new GridBagLayout());
        data.setBackground(Color.white);

        JLabel moneyData = new JLabel(money);
        moneyData.setFont(new Font("SansSerif",Font.BOLD,20));
        if(money.contains("-")){
            moneyData.setForeground(Color.BLACK);
        }else if(money.contains("+")){
            moneyData.setForeground(new Color(000,126,255));
        }
        else{
            moneyData.setForeground(Color.red);
        }
        moneyData.setPreferredSize(new Dimension(400,50));
        moneyData.setBorder(new EmptyBorder(0,25,0,0));
        Controller.locateXY(gbc, 1, 0, 2, 1);
        data.add(moneyData, gbc);

        JLabel cardAndStore = new JLabel(card+"->"+destination);
        cardAndStore.setForeground(Color.gray);
        cardAndStore.setFont(new Font("SansSerif",Font.BOLD,20));
        cardAndStore.setPreferredSize(new Dimension(400,40));
        cardAndStore.setBorder(new EmptyBorder(0,25,0,0));
        Controller.locateXY(gbc, 1, 1, 2, 1);
        data.add(cardAndStore, gbc);

        JLabel tradeData = new JLabel(date);
        tradeData.setForeground(Color.gray);
        tradeData.setFont(new Font("SansSerif",Font.BOLD,20));
        tradeData.setPreferredSize(new Dimension(200,100));
        Controller.locateXY(gbc, 3, 0, 2, 2);
        data.add(tradeData,gbc);

        return data;
    }
}//Timeline