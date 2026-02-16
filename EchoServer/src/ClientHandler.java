import java.io.*;
import java.net.Socket;
import java.util.Random;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final EchoServer server;
    private PrintWriter out;
    private final String name = generateName();

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

            out.println("Your name: " + name);

            String line;
            while ((line = in.readLine()) != null) {
                server.broadcast(name + ": " + line, this);
            }

        } catch (IOException e) {
            System.out.println("Client disconnected: " + name);
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

    private String generateName() {
        Random random = new Random();
        return "User" + (1000 + random.nextInt(9000));
    }
}