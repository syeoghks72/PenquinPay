import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
	private Controller controller;
	private TopPanel top;
	private JPanel center = new JPanel();
	private DockPanel dock;
	private CardLayout centerCards;

	private SendMoneyPanel sendMoneyPanel;
	private LookUpPanel lookUpPanel;
	private TimeLinePanel timeLinePanel;
	private MyPagePanel myPagePanel;
	private CardConnectPanel cardConnectPanel;

	private JScrollPane sendMoney;
	private JScrollPane lookUp;
	private JScrollPane timeLine;
	private JScrollPane myPage;
	private JScrollPane cardConnect;

	public MainPanel(Controller c, String[] nameBanks, String[] numBanks, String name, String phoneNumber) {
		this.controller = c;
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);

		top = new TopPanel(controller);
		dock = new DockPanel(controller);

		sendMoney = makePanelForCenter(sendMoneyPanel = new SendMoneyPanel(controller, nameBanks, numBanks));
		// test lookUp code
		String[] test = { "20000", "100000" };
		lookUp = makePanelForCenter(lookUpPanel = new LookUpPanel(nameBanks, numBanks, test));
		timeLine = makePanelForCenter(timeLinePanel = new TimeLinePanel());
		myPage = makePanelForCenter(myPagePanel = new MyPagePanel(this.controller, name, phoneNumber));
		cardConnect = makePanelForCenter(cardConnectPanel = new CardConnectPanel(controller));

		
		centerCards = new CardLayout();
		center.setLayout(centerCards);
		center.setBackground(Color.WHITE);
		center.add("sendMoney", sendMoney);
		center.add("lookUp", lookUp);
		center.add("timeLine", timeLine);
		center.add("myPage", myPage);
		center.add("cardConnect", cardConnect);

		this.add(this.top, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		this.add(this.dock, BorderLayout.SOUTH);

	}

	public JScrollPane makePanelForCenter(JPanel panel) {
		JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(700, 700));
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setBackground(Color.WHITE);
		return scrollPane;
	}

	public void changeCenterPanel(int pageflag) {
		switch (pageflag) {
		case 1: {
			centerCards.show(center, "sendMoney");
			break;
		}
		case 2: {
			centerCards.show(center, "lookUp");
			break;
		}
		case 3: {
			centerCards.show(center, "timeLine");
			break;
		}
		case 4: {
			centerCards.show(center, "myPage");
			break;
		}
		case 5: {
			centerCards.show(center, "cardConnect");
			break;
		}
		}
	}

	public TopPanel getTop() {
		return top;
	}

	public DockPanel getDock() {
		return dock;
	}

	public SendMoneyPanel getSendMoney() {
		return sendMoneyPanel;
	}

	public MyPagePanel getMyPage() {
		return myPagePanel;
	}

	public CardConnectPanel getCardConnectPanel() {
		return cardConnectPanel;
	}

	public JScrollPane getLookUp() {
		return lookUp;
	}

	public void setLookUp(JScrollPane lookUp) {
		this.lookUp = lookUp;
	}

	public JPanel getCenter() {
		return center;
	}

	public void setCenter(JPanel center) {
		this.center = center;
	}

	public TimeLinePanel getTimeLinePanel() {
		return timeLinePanel;
	}

	public void setTimeLinePanel(TimeLinePanel timeLinePanel) {
		this.timeLinePanel = timeLinePanel;
	}
	

}
