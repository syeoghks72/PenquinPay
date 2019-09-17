import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Main.Client;
import Main.Packet;
import ScreenObject.AccountObject;
import ScreenObject.CardObject;
import ScreenObject.JoinObject;
import ScreenObject.MypageObject;
import ScreenObject.SendMoneyObject;
import ScreenObject.TimeLineObject;

public class Controller implements ActionListener, MouseListener, ListSelectionListener, ItemListener {
    private Client client;
    private Stack<String> pageOrder = new Stack<>();
    private String currentPage;
    private MainFrame mainFrame;
    private MainPanel mainPanel;
    private TopPanel topPanel;
    private DockPanel dockPanel;
    private SignInPanel signIn;
    private SignUpPanel signUp;
    private SendMoneyPanel sendMoneyPanel;
    private CardConnectPanel cardConnectPanel;
    private String data;
    private String name;
    private String phoneNumeber;
    private String[] testNameData = {"신한"};
    private String[] testNumData = {"110-357-928087"};


    public Controller(){
        client = new Client();
        client.InitClient("192.168.0.89",7700);
        client.startClient();

        System.out.println("Server Connected");

        signIn = new SignInPanel(this);
        signUp = new SignUpPanel(this);
        mainPanel = new MainPanel(this, testNameData, testNumData, name = "이해창", phoneNumeber = "01051353493");

        topPanel = mainPanel.getTop();
        dockPanel = mainPanel.getDock();
        sendMoneyPanel = mainPanel.getSendMoney();
        cardConnectPanel = mainPanel.getCardConnectPanel();

        mainFrame = new MainFrame(this);
        mainFrame.getContentPane().add("signIn", signIn);
        mainFrame.getContentPane().add("signUp", signUp);
        mainFrame.getContentPane().add("main", mainPanel);

        currentPage = "signIn";
        pageOrder.push(currentPage);
        mainFrame.onVisible();
    }

    // moveMainPage
    public void moveMainPage(int flag){
        if(!(pageOrder.isEmpty())){
            switch (flag) {
                case 0: {
                    dockPanel.getDockButtons()[0].setImageIcon(dockPanel.getDockButtonImgaeRelativePath()[4]);
                    dockPanel.getDockButtons()[1].setImageIcon(dockPanel.getDockButtonImgaeRelativePath()[1]);
                    dockPanel.getDockButtons()[2].setImageIcon(dockPanel.getDockButtonImgaeRelativePath()[2]);
                    dockPanel.getDockButtons()[3].setImageIcon(dockPanel.getDockButtonImgaeRelativePath()[3]);
                    dockPanel.setDockFlagOnOff(0);
                    topPanel.setImageIcon(0);
                    mainPanel.changeCenterPanel(1);
                    break;
                }
                case 1: {
                    dockPanel.getDockButtons()[1].setImageIcon(dockPanel.getDockButtonImgaeRelativePath()[5]);
                    dockPanel.getDockButtons()[0].setImageIcon(dockPanel.getDockButtonImgaeRelativePath()[0]);
                    dockPanel.getDockButtons()[2].setImageIcon(dockPanel.getDockButtonImgaeRelativePath()[2]);
                    dockPanel.getDockButtons()[3].setImageIcon(dockPanel.getDockButtonImgaeRelativePath()[3]);
                    dockPanel.setDockFlagOnOff(1);
                    topPanel.setImageIcon(1);
                    mainPanel.changeCenterPanel(2);
                    break;
                }
                case 2: {
                    dockPanel.getDockButtons()[0].setImageIcon(dockPanel.getDockButtonImgaeRelativePath()[0]);
                    dockPanel.getDockButtons()[2].setImageIcon(dockPanel.getDockButtonImgaeRelativePath()[6]);
                    dockPanel.getDockButtons()[1].setImageIcon(dockPanel.getDockButtonImgaeRelativePath()[1]);
                    dockPanel.setDockFlagOnOff(2);
                    dockPanel.getDockButtons()[3].setImageIcon(dockPanel.getDockButtonImgaeRelativePath()[3]);
                    topPanel.setImageIcon(2);
                    mainPanel.changeCenterPanel(3);
                    break;
                }
                case 3: {
                    dockPanel.getDockButtons()[3].setImageIcon(dockPanel.getDockButtonImgaeRelativePath()[7]);
                    dockPanel.getDockButtons()[0].setImageIcon(dockPanel.getDockButtonImgaeRelativePath()[0]);
                    dockPanel.getDockButtons()[1].setImageIcon(dockPanel.getDockButtonImgaeRelativePath()[1]);
                    dockPanel.getDockButtons()[2].setImageIcon(dockPanel.getDockButtonImgaeRelativePath()[2]);
                    dockPanel.setDockFlagOnOff(3);
                    topPanel.setImageIcon(3);
                    mainPanel.changeCenterPanel(4);
                    break;
                }
                case 4: {
                    dockPanel.getDockButtons()[3].setImageIcon(dockPanel.getDockButtonImgaeRelativePath()[7]);
                    dockPanel.getDockButtons()[0].setImageIcon(dockPanel.getDockButtonImgaeRelativePath()[0]);
                    dockPanel.getDockButtons()[1].setImageIcon(dockPanel.getDockButtonImgaeRelativePath()[1]);
                    dockPanel.getDockButtons()[2].setImageIcon(dockPanel.getDockButtonImgaeRelativePath()[2]);
                    dockPanel.setDockFlagOnOff(3);
                    topPanel.setImageIcon(3);
                    mainPanel.changeCenterPanel(5);
                    break;
                }
            }
            this.refresh();
        }
    }

    public void refresh(){
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    /** Static Method **/
    public static void locateXY(GridBagConstraints gbc, int x, int y, int w, int h){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
    }

    public static JLabel makeTextLabel(String text){
        JLabel jLabel= new JLabel(text);
        jLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        jLabel.setPreferredSize(new Dimension(640, 100));

        return jLabel;
    }

    /** Event Method **/
    // ActionEvent Method
    @Override
    public void actionPerformed(ActionEvent e) {
        // SignInBtn (Login Button)
        if(e.getSource() == signIn.getConfirmBtn()){
            Packet data;
            String id = signIn.getID();
            String pw = signIn.getPW();

            JoinObject joinObject = new JoinObject(null, id, pw, null);
            ArrayList<Object> joinList = new ArrayList<>();
            joinList.add(joinObject);
            if(id.trim().isEmpty() || id == null || pw.trim().isEmpty() || pw == null) {
            	JOptionPane.showMessageDialog(mainFrame, "모두 기입해주세요!", "로그인 오류!!", JOptionPane.ERROR_MESSAGE);
            	return;
            }
            try {
                client.sendMessage(new Packet("client","server","login", joinList));
                Thread.sleep(1500);
                data = client.getCurrentPacket();

                if(((String)(data.getDataObject().get(0))).equals("OK")){
                    System.out.println("SignIn Success");
                    currentPage = "sendMoney";
                    pageOrder.push(currentPage);                
                    mainFrame.getMainCards().show(mainFrame.getContentPane(), "main");  
                    
            		Packet pk = new Packet();
            		pk.setFrom("client");
            		pk.setTo("server");
            		pk.setCommand("account_list");
            		pk.setDataObject(null);
            		client.sendMessage(pk);
            		
            		Thread.sleep(2000);
            		data = client.getCurrentPacket();
    				ArrayList<Object> ar = data.getDataObject();
    				
    				String[] arr = new String[ar.size()];
    				
    				for(int i = 0 ; i < ar.size() ; i++) {
    					SendMoneyObject sendMoneyObject = ((SendMoneyObject)ar.get(i));
    					String bankName = sendMoneyObject.getMycardname();
    					String cardNum = sendMoneyObject.getMycardnum();
    					arr[i] = bankName + "(" + cardNum + ")";
    				}
                    sendMoneyPanel.getMyAccounts().setListData(arr);
                    sendMoneyPanel.getBankScrollpane().setViewportView(sendMoneyPanel.getMyAccounts());
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        // signIn confirmBtn (move SignUpPage)
        else if(e.getSource() == signIn.getSignUp()){
            currentPage = "signUp";
            pageOrder.push(currentPage);
            System.out.println(pageOrder.size()+"|"+pageOrder.peek());

            mainFrame.getMainCards().show(mainFrame.getContentPane(), "signUp");
        }

        // signUp confirmBtn (move SignInPage)
        else if(e.getSource() == signUp.getConfirmBtn()){
            currentPage = "signIn";
            pageOrder.push(currentPage);
            if(signUp.getPw().equals(signUp.getPwCheck())) {
            	Packet data;
        		Packet pk = new Packet();
        		pk.setFrom("client");
        		pk.setTo("server");
        		pk.setCommand("join");
        		ArrayList<Object> ao = new ArrayList<Object>();
        		JoinObject jo = new JoinObject();
        		jo.setEmail(signUp.getEmail());
        		jo.setName(signUp.getName());
        		jo.setPassword(signUp.getPw());
        		jo.setPhone(signUp.getPhoneNumber());
        		ao.add(jo);
        		pk.setDataObject(ao);
        		try {
        			client.sendMessage(pk);
        			Thread.sleep(2000);        			
                    data = client.getCurrentPacket();
                    if(((String)(data.getDataObject().get(0))).equals("joinOK")){
	        			JOptionPane.showMessageDialog(mainFrame, "회원가입이 완료되었습니다! 로그인해주세요", "회원가입 완료", JOptionPane.PLAIN_MESSAGE);
	        			mainFrame.getMainCards().show(mainFrame.getContentPane(), "signIn");
                    }
                    else {
                    	JOptionPane.showMessageDialog(mainFrame, "회원 가입 실패", "서버오류!!", JOptionPane.ERROR_MESSAGE);
                    }
        		}
        		catch(Exception ex) {
        			JOptionPane.showMessageDialog(mainFrame, "회원 가입 실패", "서버오류!!", JOptionPane.ERROR_MESSAGE);
        			ex.printStackTrace();
        		}
            }
            else{
                JOptionPane.showMessageDialog(mainFrame, "비밀번호가 일치하지 않습니다", "비밀번호 불일치", JOptionPane.ERROR_MESSAGE);
            }
        }

        // move SendMoneyPage
        else if(e.getSource() == dockPanel.getDockButtons()[0]){
            if(dockPanel.getDockFlag()[0] == 0){
                this.moveMainPage(0);
                currentPage = "sendMoney";
                pageOrder.push(currentPage);
            }
        }

        // move LookUpPage
        else if(e.getSource() == dockPanel.getDockButtons()[1]){
            if(dockPanel.getDockFlag()[1] == 0){
            	try {
            		Packet pk = new Packet();
            		pk.setFrom("client");
            		pk.setTo("server");
            		pk.setCommand("my_account");
            		pk.setDataObject(null);
	        		client.sendMessage(pk);
	        		
	        		Thread.sleep(500);
	        		Packet data = client.getCurrentPacket();
					
					ArrayList<Object> ar = data.getDataObject();
					String[] bankNameArr = new String[ar.size()];
					String[] cardnumArr = new String[ar.size()];
					String[] moneyArr = new String[ar.size()];
					for(int i = 0 ; i < ar.size() ; i++) {
						bankNameArr[i] = ((AccountObject)ar.get(i)).getBankname();
						cardnumArr[i] = ((AccountObject)ar.get(i)).getCardnum();
						moneyArr[i] = ((AccountObject)ar.get(i)).getMoney()+"원";
					}
					LookUpPanel lookUpPanel = new LookUpPanel(bankNameArr, cardnumArr, moneyArr);
					JScrollPane lookUpScroll = mainPanel.getLookUp();
					lookUpScroll = mainPanel.makePanelForCenter(lookUpPanel);
					mainPanel.getCenter().add("lookUp", lookUpScroll);
            	}
            	catch(Exception ex) {
            		ex.printStackTrace();
            	}            	
            	
                this.moveMainPage(1);
                currentPage = "lookUp";
                pageOrder.push(currentPage);
            }
        }

        // move TimeLinePage
        else if(e.getSource() == dockPanel.getDockButtons()[2]){
            if(dockPanel.getDockFlag()[2] == 0){
        		Packet pk = new Packet();
        		pk.setFrom("client");
        		pk.setTo("server");
        		pk.setCommand("time_line");
        		pk.setDataObject(null);
        		try {
        			client.sendMessage(pk);
        			Thread.sleep(1500);
	        		Packet data = client.getCurrentPacket();
    				ArrayList<Object> ar = data.getDataObject();
    				TimeLinePanel timeLinePanel = new TimeLinePanel();
    				mainPanel.setTimeLinePanel(timeLinePanel);
    				JScrollPane timeline = mainPanel.getTimeLine();
    				mainPanel.getCenter().remove(timeline);
    				timeline = mainPanel.makePanelForCenter(timeLinePanel);    				
    				mainPanel.getCenter().add("timeLine", timeline);
    				
    				TimeLinePanel timelinePanel = mainPanel.getTimeLinePanel();
    				for(int i = 0 ; i < ar.size() ; i++) {
    					TimeLineObject timelineObject = (TimeLineObject)ar.get(i);
    					String money = "";
    					String myName = "";
    					String yourName = "";
    					if(timelineObject.getSentmoney()!=null) {
    						money = "-" + ((TimeLineObject)ar.get(i)).getSentmoney() + "원";
    						myName = "내 입출력 계좌";
    						yourName = ((TimeLineObject)ar.get(i)).getTrader();
    					}
    					else {
    						money =  "+" + ((TimeLineObject)ar.get(i)).getReceivemoney() + "원";
    						myName = ((TimeLineObject)ar.get(i)).getTrader();
    						yourName = "내 입출력 계좌";
    					}
    					String whendate = ((TimeLineObject)ar.get(i)).getWhentime();
    					timelinePanel.addDataPanel3(timelinePanel.makeDataPanel(money, myName, yourName, whendate), i*2+1);
    					 
    				}
        		}
        
        		catch(Exception ex) {
        			ex.printStackTrace();
        		}
                this.moveMainPage(2);
                currentPage = "timeLine";
                pageOrder.push(currentPage);
            }
        }

        // move MyPage
        else if(e.getSource() == dockPanel.getDockButtons()[3]) {
            if(dockPanel.getDockFlag()[3] == 0){
        		
        		Packet pk = new Packet();
        		pk.setFrom("client");
        		pk.setTo("server");
        		pk.setCommand("mypage");
        		pk.setDataObject(null);
        		try {
        			client.sendMessage(pk);
        			Thread.sleep(500);
	        		Packet data2 = client.getCurrentPacket();
    				ArrayList<Object> ar = data2.getDataObject();
    				MyPagePanel myPagePanel = mainPanel.getMyPage();
    				for(int i = 0 ; i< ar.size() ; i++) {
    					myPagePanel.getMyName().setText(((MypageObject)ar.get(i)).getName());
    					myPagePanel.getPhoneNumber().setText(((MypageObject)ar.get(i)).getPhone());
    					myPagePanel.getSpentMoney().setMoney(((MypageObject)ar.get(i)).getMoney());
    				}
        		}
        		catch(Exception ex) {
        			ex.printStackTrace();
        		}
                this.moveMainPage(3);
                currentPage = "myPage";
                pageOrder.push(currentPage);
            }
        }

        // numPad integer
        else if(e.getSource() == sendMoneyPanel.getNumPad()[0]){
            sendMoneyPanel.setMoney(sendMoneyPanel.getNumPad()[0].getName());
        }
        else if(e.getSource() == sendMoneyPanel.getNumPad()[1]){
            sendMoneyPanel.setMoney(sendMoneyPanel.getNumPad()[1].getName());
        }
        else if(e.getSource() == sendMoneyPanel.getNumPad()[2]){
            sendMoneyPanel.setMoney(sendMoneyPanel.getNumPad()[2].getName());
        }
        else if(e.getSource() == sendMoneyPanel.getNumPad()[3]){
            sendMoneyPanel.setMoney(sendMoneyPanel.getNumPad()[3].getName());
        }
        else if(e.getSource() == sendMoneyPanel.getNumPad()[4]){
            sendMoneyPanel.setMoney(sendMoneyPanel.getNumPad()[4].getName());
        }
        else if(e.getSource() == sendMoneyPanel.getNumPad()[5]){
            sendMoneyPanel.setMoney(sendMoneyPanel.getNumPad()[5].getName());
        }
        else if(e.getSource() == sendMoneyPanel.getNumPad()[6]){
            sendMoneyPanel.setMoney(sendMoneyPanel.getNumPad()[6].getName());
        }
        else if(e.getSource() == sendMoneyPanel.getNumPad()[7]){
            sendMoneyPanel.setMoney(sendMoneyPanel.getNumPad()[7].getName());
        }
        else if(e.getSource() == sendMoneyPanel.getNumPad()[8]){
            sendMoneyPanel.setMoney(sendMoneyPanel.getNumPad()[8].getName());
        }
        else if(e.getSource() == sendMoneyPanel.getNumPad()[9]) {
            sendMoneyPanel.clearMoney();
        }
        else if(e.getSource() == sendMoneyPanel.getNumPad()[10]){
            if(!(sendMoneyPanel.getMoney().isEmpty())) {
                sendMoneyPanel.setMoney(sendMoneyPanel.getNumPad()[10].getName());
            }
        }
        else if(e.getSource() == sendMoneyPanel.getNumPad()[11]){
            if (!(sendMoneyPanel.getMoneyStrLeng() == 1 || sendMoneyPanel.getMoneyStrLeng() == 0)){
                sendMoneyPanel.subMoney();
            }
            else {
                sendMoneyPanel.clearMoney();
            }
        }

        // numPad sendMoneyBtn
        else if(e.getSource() == mainPanel.getSendMoney().getSend()){        	
            String[] bankAndAccount = mainPanel.getSendMoney().getSeletecdAccount().split("\\(|\\)");
            String bank = bankAndAccount[0];
            String myCardNum = bankAndAccount[1];
            String money = mainPanel.getSendMoney().getMoney();
            String yourCardNum = mainPanel.getSendMoney().getReceiverAccount();
    		Packet pk = new Packet();
    		pk.setFrom("client");
    		pk.setTo("server");
    		pk.setCommand("send");
    		ArrayList<Object> ao = new ArrayList<Object>();
    		SendMoneyObject sm = new SendMoneyObject(money, myCardNum, null, yourCardNum);
    		ao.add(sm);
    		pk.setDataObject(ao);
    
    		try {
    			client.sendMessage(pk);
    			Thread.sleep(2000);        			
                Packet data = client.getCurrentPacket();    
                if(((String)(data.getDataObject().get(0))).equals("sendOK")){
        			JOptionPane.showMessageDialog(mainFrame, "송금에 성공하셨습니다.", "송금 성공", JOptionPane.PLAIN_MESSAGE);
        			//mainFrame.getMainCards().show(mainFrame.getContentPane(), "signIn");
                }
                else if(((String)(data.getDataObject().get(0))).equals("sendNO")) {
                	JOptionPane.showMessageDialog(mainFrame, "송금에 실패하셨습니다. 출금할 계좌의 잔액이나 송금할 계좌번호를 확인해 주세요!!", "송금 실패", JOptionPane.ERROR_MESSAGE);
                }
                
    		}
    		catch(Exception ex) {
    			ex.printStackTrace();
    		}
        }

        // movePage addCardPage
        else if(e.getSource() == mainPanel.getMyPage().getAddCardButton()){
            mainPanel.changeCenterPanel(5);
        }

        // addCardBtn
        else if(e.getSource() == cardConnectPanel.getConfirm()){
    		Packet pk = new Packet();
    		pk.setFrom("client");
    		pk.setTo("server");
    		pk.setCommand("add_card");
    		ArrayList<Object> ao = new ArrayList<Object>();
    		CardObject co = new CardObject();
    		co.setBankname(cardConnectPanel.getSelectedBank());
    		co.setCardnum(cardConnectPanel.getCardNumber());
    		co.setCvc(cardConnectPanel.getCvc());
    		co.setPassword(cardConnectPanel.getPw());
    		co.setPeriod(cardConnectPanel.getExpiration());
    		ao.add(co);
    		pk.setDataObject(ao);
    
    		try {
    			 client.sendMessage(pk);
    			 Thread.sleep(2000);        			
                 Packet data = client.getCurrentPacket();
    			 if(((String)(data.getDataObject().get(0))).equals("add_card_yes")) {
    				 JOptionPane.showMessageDialog(mainFrame, "카드가 추가되었습니다!", "카드추가 완료", JOptionPane.PLAIN_MESSAGE);
    			 }else {
    				 JOptionPane.showMessageDialog(mainFrame, "카드 추가에 실패하셨습니다. 잘못된 정보를 입력하셨습니다.", "카드 추가실패", JOptionPane.ERROR_MESSAGE);
    			 }
    		}
    
    		catch(Exception ex) {
    			ex.printStackTrace();
    		}


           

            mainPanel.changeCenterPanel(4);
        }

        else{
            System.out.println("*^^*");
        }

    }

    // MouseEvent Method
    @Override
    public void mouseClicked(MouseEvent e){
        if(e.getSource() == (Object)mainFrame.getContentPane()){
            if(e.getY() > 0 && e.getY() < 100 && e.getX() > 0 && e.getX() < 100 && pageOrder.size() > 1){
                pageOrder.pop();
                currentPage = pageOrder.peek();

                if(currentPage.contains("signIn")){
                    mainFrame.getMainCards().show(mainFrame.getContentPane(), "signIn");
                    System.out.println(pageOrder.size()+"|"+pageOrder.peek());
                }

                else {
                    switch (currentPage){
                        case "sendMoney":{
                            mainPanel.changeCenterPanel(1);
                            this.moveMainPage(0);
                            System.out.println(pageOrder.size()+"|"+pageOrder.peek());
                            break;
                        }
                        case "lookUp":{
                            mainPanel.changeCenterPanel(2);
                            this.moveMainPage(1);
                            System.out.println(pageOrder.size()+"|"+pageOrder.peek());
                            break;
                        }
                        case "timeLine":{
                            mainPanel.changeCenterPanel(3);
                            this.moveMainPage(2);
                            System.out.println(pageOrder.size()+"|"+pageOrder.peek());
                            break;
                        }
                        case "myPage":{
                            mainPanel.changeCenterPanel(4);
                            this.moveMainPage(3);
                            System.out.println(pageOrder.size()+"|"+pageOrder.peek());
                            break;
                        }
                        case "cardConnect":{
                            mainPanel.changeCenterPanel(5);
                            this.moveMainPage(4);
                            System.out.println(pageOrder.size()+"|"+pageOrder.peek());
                            break;
                        }

                    }
                }
            }
        }
    }

    // MouseListener Abstract Method Overriding
    @Override public void mousePressed(MouseEvent e){ }
    @Override public void mouseReleased(MouseEvent e){ }
    @Override public void mouseEntered(MouseEvent e){ }
    @Override public void mouseExited(MouseEvent e){ }

    // ListSelectionListener Abstract Method Overriding
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (mainPanel.getSendMoney().getMyAccounts().getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "계좌를 선택해주세요");
        } else if(mainPanel.getSendMoney().getMyAccounts().getValueIsAdjusting()){
            mainPanel.getSendMoney().setSeletecdAccount((String)mainPanel.getSendMoney().getMyAccounts().getSelectedValue());
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED){
            JRadioButton temp = (JRadioButton)e.getItem();
            switch (temp.getName()){
                case "신한":{
                    cardConnectPanel.getBanks()[0].setIcon(new ImageIcon(getClass().getResource("images/BankIcon/sh_active.jpg")));
                    cardConnectPanel.getBanks()[1].setIcon(new ImageIcon(getClass().getResource("images/BankIcon/kb.jpg")));
                    cardConnectPanel.getBanks()[2].setIcon(new ImageIcon(getClass().getResource("images/BankIcon/nh.jpg")));
                    cardConnectPanel.getBanks()[3].setIcon(new ImageIcon(getClass().getResource("images/BankIcon/hn.jpg")));
                    break;
                }
                case "국민": {
                    cardConnectPanel.getBanks()[0].setIcon(new ImageIcon(getClass().getResource("images/BankIcon/sh.jpg")));
                    cardConnectPanel.getBanks()[1].setIcon(new ImageIcon(getClass().getResource("images/BankIcon/kb_active.jpg")));
                    cardConnectPanel.getBanks()[2].setIcon(new ImageIcon(getClass().getResource("images/BankIcon/nh.jpg")));
                    cardConnectPanel.getBanks()[3].setIcon(new ImageIcon(getClass().getResource("images/BankIcon/hn.jpg")));
                    break;
                }
                case "농협":{
                    cardConnectPanel.getBanks()[0].setIcon(new ImageIcon(getClass().getResource("images/BankIcon/sh.jpg")));
                    cardConnectPanel.getBanks()[1].setIcon(new ImageIcon(getClass().getResource("images/BankIcon/kb.jpg")));
                    cardConnectPanel.getBanks()[2].setIcon(new ImageIcon(getClass().getResource("images/BankIcon/nh_active.jpg")));
                    cardConnectPanel.getBanks()[3].setIcon(new ImageIcon(getClass().getResource("images/BankIcon/hn.jpg")));
                    break;
                }
                case "하나":{
                    cardConnectPanel.getBanks()[0].setIcon(new ImageIcon(getClass().getResource("images/BankIcon/sh.jpg")));
                    cardConnectPanel.getBanks()[1].setIcon(new ImageIcon(getClass().getResource("images/BankIcon/kb.jpg")));
                    cardConnectPanel.getBanks()[2].setIcon(new ImageIcon(getClass().getResource("images/BankIcon/nh.jpg")));
                    cardConnectPanel.getBanks()[3].setIcon(new ImageIcon(getClass().getResource("images/BankIcon/hn_active.jpg")));
                    break;
                }
            }
            cardConnectPanel.setSelectedBank(temp.getName());
        }
      
    }

    public static void main(String[] args) {
        Controller c = new Controller();
    }
}
