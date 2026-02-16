import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {

    private final int port;
    private final ExecutorService pool = Executors.newCachedThreadPool();

    private EchoServer(int port) {
        this.port = port;
    }

    public static EchoServer bindToPort(int port) {
        return new EchoServer(port);
    }

    public void run() {

        try (ServerSocket server = new ServerSocket(port)) {

            System.out.println("Server started on port " + port);

            while (!server.isClosed()) {

                Socket socket = server.accept();

                ClientHandler handler =
                        new ClientHandler(socket, this);

                pool.submit(handler);
            }

        } catch (IOException e) {
            System.out.println("Port busy.");
        }
    }

    public static void main(String[] args) {
        EchoServer.bindToPort(8089).run();
    }
}