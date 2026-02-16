import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))
        ) {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Message: " + line);
            }

        } catch (IOException e) {
            System.out.println("Client disconnected");
        }
    }
}