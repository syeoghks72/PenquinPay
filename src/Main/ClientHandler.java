package Main;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;

//클라이언트의 동작을 담당
public class ClientHandler extends Thread {
	private Socket sock;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	private HashMap<InetAddress, ClientHandler> clientsHashMap;
	private RunCommandImpl runCommandImpl;
	private ParsingPacket parsingPacket;
	private String email;

	public ClientHandler(Socket sock, HashMap<InetAddress, ClientHandler> clientsHashMap) {
		super();
		this.sock = sock;
		this.clientsHashMap = clientsHashMap;
		AddClient(sock.getInetAddress(), this);
		try {
			objectInputStream = new ObjectInputStream(sock.getInputStream());
			objectOutputStream = new ObjectOutputStream(sock.getOutputStream());

		} catch (Exception e) {
			System.out.println("Stream연결 도중 예외 : " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		parsingPacket = new ParsingPacket(this);
		while (true) {
			try {
				System.out.println("실행중..");
				Packet packet = (Packet)objectInputStream.readObject();
				runCommandImpl = parsingPacket.returnRunCommand(packet);
				runCommandImpl.RunCommand();
			}
			catch(Exception e) {
				e.printStackTrace();
				clientsHashMap.remove(sock.getInetAddress());
				closeAll();
				break;
				//e.printStackTrace();
			}
		}

	}

	public void sendMessage(Packet packet) throws Exception{
		if (sock != null && objectInputStream != null && objectOutputStream != null) {
			objectOutputStream.writeObject(packet);
			objectOutputStream.flush();
		} else {
			System.out.println("메시지 보내는중 에러 : Client 초기화가 필요합니다");
		}
	}

	public void AddClient(InetAddress address, ClientHandler client) {
		if(clientsHashMap.containsKey(address)) {
			System.out.println(clientsHashMap);
			clientsHashMap.get(address).closeAll();
			clientsHashMap.remove(address);
		}

		clientsHashMap.put(address, client);

	}



	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	// 소켓과 사용된 스트림 닫기..
	public void closeAll() {
		try {
			if (sock != null) {
				sock.close();
				sock = null;
			}
			if (objectInputStream != null)
				objectInputStream.close();
			if (objectOutputStream != null)
				objectOutputStream.close();
		} catch (Exception e) {
			System.out.println("소켓 혹은 스트림 닫는중 예외 : " + e.getMessage());
		}
	}
}
