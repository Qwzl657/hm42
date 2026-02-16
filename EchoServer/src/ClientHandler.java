import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final EchoServer server;
    private PrintWriter out;

    public ClientHandler(Socket socket, EchoServer server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))
        ) {
            out = new PrintWriter(socket.getOutputStream(), true);

            String line;
            while ((line = in.readLine()) != null) {
                server.broadcast(line, this);
            }

        } catch (IOException e) {
            System.out.println("Client disconnected");
        } finally {
            close();
        }
    }

    public void send(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    private void close() {
        try {
            socket.close();
        } catch (IOException ignored) {}

        server.removeClient(this);
    }
}