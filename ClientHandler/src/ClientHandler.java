import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final EchoServer server;

    public ClientHandler(Socket socket, EchoServer server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {

        System.out.println("Client connected: " + socket);

        try (socket;
             Scanner reader = new Scanner(socket.getInputStream(), "UTF-8");
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            while (reader.hasNextLine()) {

                String message = reader.nextLine();

                if (message == null || message.isBlank()
                        || "bye".equalsIgnoreCase(message)) {
                    break;
                }

                writer.println(message);
            }

        } catch (NoSuchElementException e) {
            System.out.println("Client closed connection.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Client disconnected: " + socket);
    }
}