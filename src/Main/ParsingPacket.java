package Main;

import java.util.ArrayList;

import ScreenObject.CardObject;
import ScreenObject.JoinObject;
import ScreenObject.SendMoneyObject;

public class ParsingPacket {
	private RunCommandImpl runcommandImpl;
	private LoginCheck loginCheck = new LoginCheck(); 
	private MyPage myPage = new MyPage();
	private SendMoney sendMoney = new SendMoney();
	private AccountList accountList = new AccountList();
	private MyAccount myAccount = new MyAccount();
	private AddCard addCard = new AddCard();
	private TimeLine timeLine = new TimeLine();
	private Join join = new Join();
	private ClientHandler clientHandler;
	private Client client;
	private Packet packet;
	private Dao dao = new Dao();
	
	public ParsingPacket(ClientHandler clientHandler) {
		this.clientHandler = clientHandler;
	}
	
	public ParsingPacket(Client client) {
		this.client = client;
	}
		
	public RunCommandImpl returnRunCommand(Packet packet) {
		this.packet = packet;
		
		String command = this.packet.getCommand();
		
		if(command.equals("login")) runcommandImpl = loginCheck;
		else if(command.equals("mypage")) runcommandImpl = myPage;
		else if(command.equals("send")) runcommandImpl = sendMoney;
		else if(command.equals("account_list")) runcommandImpl = accountList;
		else if(command.equals("my_account")) runcommandImpl = myAccount;
		else if(command.equals("add_card")) runcommandImpl = addCard;
		else if(command.equals("time_line")) runcommandImpl = timeLine;
		else if(command.equals("join")) runcommandImpl = join;
		return runcommandImpl; 
	}
	
	public class LoginCheck implements RunCommandImpl{	
		@Override
		public void RunCommand() {
			ArrayList<Object> joinArray = packet.getDataObject();
			JoinObject joinObject = (JoinObject)(joinArray.get(0));
			String email = joinObject.getEmail();
			String password = joinObject.getPassword();
			try {
				boolean login_check = dao.loginCheck(email, password);
				Packet send_packet = new Packet();
				send_packet.setFrom("server");
				send_packet.setTo("client");
				send_packet.setCommand("login");
				ArrayList<Object> msg = new ArrayList<Object>();
				
				if(login_check) {
					msg.add("OK");
					clientHandler.setEmail(email);
				}
				else {
					msg.add("NO");
				}
				send_packet.setDataObject(msg);
				clientHandler.sendMessage(send_packet);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void RollBackCommand() {
			
		}	
	}
	
	public class MyPage implements RunCommandImpl{
		@Override
		public void RunCommand() {
			Packet pk = new Packet();
			pk.setFrom("server");
			pk.setTo("client");
			pk.setCommand("mypage");
			try {
				pk.setDataObject(dao.getMypageObject(clientHandler.getEmail()));
				clientHandler.sendMessage(pk);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void RollBackCommand() {
			
		}
		
	}
	
	public class SendMoney implements RunCommandImpl{
		@Override
		public void RunCommand() {
			ArrayList<Object> sendMoneyArry = packet.getDataObject();
			SendMoneyObject sendMoneyObject = (SendMoneyObject)(sendMoneyArry.get(0));
			String money = sendMoneyObject.getMoney();
			String mycardnum = sendMoneyObject.getMycardnum();
			String yourcardnum = sendMoneyObject.getYourcardnum();
			Packet pk = new Packet();
			pk.setFrom("server");
			pk.setTo("client");
			pk.setCommand("send");
			ArrayList<Object> msg = new ArrayList<Object>();
			try {
				Boolean sendCheck = dao.sendMoney(money, mycardnum, yourcardnum, clientHandler.getEmail());
				if(sendCheck) {
					msg.add("sendOK");
				}
				else {
					msg.add("sendNO");
				}				
				pk.setDataObject(msg);
				clientHandler.sendMessage(pk);
			}
			catch(Exception e) {
				e.printStackTrace();
				msg.add("sendNO");
				pk.setDataObject(msg);
				try {
					clientHandler.sendMessage(pk);
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		}

		@Override
		public void RollBackCommand() {
			
		}
	}
	
	public class AccountList implements RunCommandImpl{
		@Override
		public void RunCommand() {
			Packet pk = new Packet();
			pk.setFrom("server");
			pk.setTo("client");
			pk.setCommand("account_list");
			try {
				pk.setDataObject(dao.getMyAccountList(clientHandler.getEmail()));
				clientHandler.sendMessage(pk);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void RollBackCommand() {
			
		}
	}
	
	public class MyAccount implements RunCommandImpl{
		@Override
		public void RunCommand() {
			Packet pk = new Packet();
			pk.setFrom("server");
			pk.setTo("client");
			pk.setCommand("my_account");
			try {
				pk.setDataObject(dao.getAccountObjects(clientHandler.getEmail()));
				clientHandler.sendMessage(pk);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		@Override
		public void RollBackCommand() {
			
		}
	}
	
	public class AddCard implements RunCommandImpl{
		@Override
		public void RunCommand() {
			ArrayList<Object> cardObjectArray = packet.getDataObject();
			CardObject sendMoneyObject = (CardObject)(cardObjectArray.get(0));
			String cardnum = sendMoneyObject.getCardnum();
			String period = sendMoneyObject.getPeriod();
			String cvc = sendMoneyObject.getCvc();
			String password = sendMoneyObject.getPassword();
			String bankname = sendMoneyObject.getBankname();
			
			Packet pk = new Packet();
			pk.setFrom("server");
			pk.setTo("client");
			pk.setCommand("add_card");
			ArrayList<Object> msg = new ArrayList<Object>();
			try {
				dao.addCard(cardnum, period, cvc, password, clientHandler.getEmail(), bankname);
				msg.add("add_card_yes");
				pk.setDataObject(msg);
				clientHandler.sendMessage(pk);
				
			} catch (Exception e) {
				e.printStackTrace();
				msg.add("add_card_no");
				pk.setDataObject(msg);
				try {
					clientHandler.sendMessage(pk);
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		}

		@Override
		public void RollBackCommand() {
			
		}
	}
	
	public class TimeLine implements RunCommandImpl{
		@Override
		public void RunCommand() {
			Packet pk = new Packet();
			pk.setFrom("server");
			pk.setTo("client");
			pk.setCommand("time_line");
			try {
				pk.setDataObject(dao.getTimeLineObjects(clientHandler.getEmail()));
				clientHandler.sendMessage(pk);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void RollBackCommand() {
			
		}
	}
	
	public class Join implements RunCommandImpl{

		@Override
		public void RunCommand() {
			ArrayList<Object> joinObjectArray = packet.getDataObject();
			JoinObject joinObject = (JoinObject)(joinObjectArray.get(0));
			String name = joinObject.getName();
			String email = joinObject.getEmail();
			String password = joinObject.getPassword();
			String phone = joinObject.getPhone();
			
			Packet pk = new Packet();
			pk.setFrom("server");
			pk.setTo("client");
			pk.setCommand("join");
			ArrayList<Object> msg = new ArrayList<Object>();
			try {
				dao.addMember(name, email, password, phone);
				msg.add("joinOK");
				pk.setDataObject(msg);
				clientHandler.sendMessage(pk);
			}
			catch(Exception e) {
				e.printStackTrace();
				msg.add("joinNO");
				pk.setDataObject(msg);
				try {
					clientHandler.sendMessage(pk);
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}		
		}

		@Override
		public void RollBackCommand() {
			
		}
		
	}
	

}
