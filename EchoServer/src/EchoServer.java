import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new EchoServer(12345).run();
    }
}