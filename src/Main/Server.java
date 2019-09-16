package Main;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server extends Thread{
    private static ServerSocket serverSocket = null;
    private static HashMap<InetAddress, ClientHandler> clientsHashMap;
    private static Server server;

    public static void InitServer(int port) {
        closeAll();
        if(serverSocket==null && clientsHashMap==null) {
            try {
                serverSocket = new ServerSocket(port);
                clientsHashMap = new HashMap<>();
                server = new Server();
            }
            catch(Exception e) {
                System.out.println("서버 초기화 예외: " + e.getMessage());
            }
        }
    }

    public static void startServer() {
        server.start();
    }

    @Override
    public void run() {
        System.out.println("서버 가동!!");
        while(true) {
            try {
                Socket sock = serverSocket.accept();
                if(!clientsHashMap.containsKey(sock.getInetAddress())) {
                    ClientHandler clientHander = new ClientHandler(sock, clientsHashMap);
                    clientsHashMap.put(sock.getInetAddress(), clientHander);
                    clientHander.start();
                }
            }
            catch(Exception e) {
                e.printStackTrace();
                System.out.println("연결도중 에러 : " + e.getMessage());
                //클라이언트 서버 연결 시도중 에러 발생 했을때 처리부분....
                //처리는 일단 생략
            }
        }
    }



    public static void closeAll() {
        try {
            if(serverSocket!=null) {
                serverSocket.close();
                serverSocket = null;
            }
            if(serverSocket!=null) serverSocket=null;
            if(server!=null) server=null;

        }catch(Exception e) {
            System.out.println("소켓 또는 벡터 닫는 중 예외 : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Server.InitServer(7700);
        Server.startServer();
    }
}