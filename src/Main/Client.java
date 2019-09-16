package Main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import ScreenObject.AccountObject;
import ScreenObject.CardObject;
import ScreenObject.JoinObject;
import ScreenObject.MypageObject;
import ScreenObject.SendMoneyObject;
import ScreenObject.TimeLineObject;

public class Client extends Thread{
	private static Socket sock;
	private static ObjectInputStream objectInputStream;
	private static ObjectOutputStream objectOutputStream;
	private static Packet current_packet;
	public void InitClient(String ip, int port) {
		closeAll();
		try {
			sock = new Socket(ip, port);
			objectOutputStream = new ObjectOutputStream(sock.getOutputStream());
			objectInputStream = new ObjectInputStream(sock.getInputStream());

		} catch (Exception e) {
			/*!!!!!!!!!!!!!!!!!!!!!!!!!!여기 주목!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
			//이부분에 서버와 연결이 실패 했다는 모달창을 띄웠으면 좋겠음....
			//"서버와의 연결을 실패했습니다!!" 요런식으로~
			System.out.println("클라이언트 초기화 예외 : " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void startClient() {
		if(sock!=null && objectOutputStream!=null && objectInputStream!=null) {
			this.start();
		}else {
			System.out.println("Client 시작중 에러!!.. Client 초기화를 확인해 보세요!");
		}
	}

	@Override
	public void run() {
		//로그인 체크할때
//		Packet pk = new Packet();
//		pk.setFrom("client");
//		pk.setTo("server");
//		pk.setCommand("login");
//		ArrayList<Object> s = new ArrayList<Object>();
//		JoinObject jo = new JoinObject(null,"kww888@naver.com","a123123",null);
//		s.add(jo);
//		pk.setDataObject(s);
//		try {
//			sendMessage(pk);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}

		//마이페이지//로그인이 되어있는상태이어야함
//		Packet pk = new Packet();
//		pk.setFrom("client");
//		pk.setTo("server");
//		pk.setCommand("mypage");
//		pk.setDataObject(null);
//		try {
//			sendMessage(pk);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}

		//송금화면에서 나의 계좌 목록 가져오기
//		Packet pk = new Packet();
//		pk.setFrom("client");
//		pk.setTo("server");
//		pk.setCommand("account_list");
//		pk.setDataObject(null);
//		try {
//			sendMessage(pk);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}

		//송금할때
//		Packet pk = new Packet();
//		pk.setFrom("client");
//		pk.setTo("server");
//		pk.setCommand("send");
//		ArrayList<Object> ao = new ArrayList<Object>();
//		SendMoneyObject sm = new SendMoneyObject("100", "1554381255469401", null,"1110554199419401");
//		ao.add(sm);
//		pk.setDataObject(ao);
//
//		try {
//			sendMessage(pk);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}


		//조회
//		Packet pk = new Packet();
//		pk.setFrom("client");
//		pk.setTo("server");
//		pk.setCommand("my_account");
//		pk.setDataObject(null);
//		try {
//			sendMessage(pk);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}


		//카드연결하기
//		Packet pk = new Packet();
//		pk.setFrom("client");
//		pk.setTo("server");
//		pk.setCommand("add_card");
//		ArrayList<Object> ao = new ArrayList<Object>();
//		CardObject co = new CardObject();
//		co.setBankname("A");
//		co.setCardnum("B");
//		co.setCvc("C");
//		co.setPassword("D");
//		co.setPeriod("E");
//		ao.add(co);
//		pk.setDataObject(ao);
//
//		try {
//			sendMessage(pk);
//		}
//
//		catch(Exception e) {
//			e.printStackTrace();
//		}

//타임라인 내역 요청
//		Packet pk = new Packet();
//		pk.setFrom("client");
//		pk.setTo("server");
//		pk.setCommand("time_line");
//		pk.setDataObject(null);
//		try {
//			sendMessage(pk);
//		}
//
//		catch(Exception e) {
//			e.printStackTrace();
//		}

//		Packet pk = new Packet();
//		pk.setFrom("client");
//		pk.setTo("server");
//		pk.setCommand("join");
//		ArrayList<Object> ao = new ArrayList<Object>();
//		JoinObject jo = new JoinObject();
//		jo.setEmail("testEmail");
//		jo.setName("testName");
//		jo.setPassword("testPassword");
//		jo.setPhone("setPhone");
//		ao.add(jo);
//		pk.setDataObject(ao);
//		try {
//			sendMessage(pk);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
		while(true) {
			try {
				current_packet = (Packet)objectInputStream.readObject();

				System.out.println("여기서 던짐? :" + current_packet.toString());
//				System.out.println("current_packet : " + current_packet);
//마이페이지..
//				ArrayList<Object> ar = current_packet.getDataObject();
//				for(int i = 0 ; i< ar.size() ; i++) {
//					System.out.println(((MypageObject)ar.get(i)).getName());
//				}

//송금에서 계좌 리스트
//				ArrayList<Object> ar = current_packet.getDataObject();
//				for(int i = 0 ; i < ar.size() ; i++) {
//					System.out.println(((SendMoneyObject)ar.get(i)).getMycardname());
//				}

//송금 성공 여부..
//				ArrayList<Object> ar = current_packet.getDataObject();
//				for(int i = 0 ; i < ar.size() ; i++) {
//					System.out.println((String)ar.get(i));
//				}

//조회 내역
//				ArrayList<Object> ar = current_packet.getDataObject();
//				for(int i = 0 ; i < ar.size() ; i++) {
//					System.out.println(((AccountObject)ar.get(i)).getBankname());
//					System.out.println(((AccountObject)ar.get(i)).getCardnum());
//					System.out.println(((AccountObject)ar.get(i)).getMoney());
//				}


				//타임라인 내역
//				ArrayList<Object> ar = current_packet.getDataObject();
//				for(int i = 0 ; i < ar.size() ; i++) {
//					System.out.println(((TimeLineObject)ar.get(i)).getReceivemoney());
//					System.out.println(((TimeLineObject)ar.get(i)).getSentmoney());
//					System.out.println(((TimeLineObject)ar.get(i)).getTrader());
//					System.out.println(((TimeLineObject)ar.get(i)).getWhentime());
//					System.out.println(((TimeLineObject)ar.get(i)).getWhentime());
//				}
			}
			catch(Exception e) {
				//System.out.println("메시지 읽는 도중 에러 : " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public Packet getCurrentPacket() {
		Packet packet = this.current_packet;
		this.current_packet = null;
		return packet;
	}

	public static void closeAll() {
		try {
			if (sock != null) {
				sock.close();
				sock = null;
			}
			if (objectOutputStream != null) objectOutputStream.close();
			if (objectInputStream != null) objectInputStream.close();

		} catch (Exception e) {
			System.out.println("소켓 혹은 스트림 닫는중 예외 : " + e.getMessage());
		}
	}

	public void sendMessage(Packet packet) throws Exception{
		if (sock != null && objectOutputStream != null && objectInputStream != null) {
			objectOutputStream.writeObject(packet);
			objectOutputStream.flush();
		}else {
			System.out.println("메시지 보내는중 에러 : Client 초기화가 필요합니다");
		}
	}

//	public static void main(String[] args) {
//		Client c = new Client();
//		c.InitClient("192.168.17.42",7700);
//		c.startClient();
//	}
}