package UserInterface;

import Common.Message;
import Common.Message_type;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread {
    private Socket socket;

    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("waiting server response");
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) objectInputStream.readObject();
                if (message.getMessage_type().equals(Message_type.MESSAGE_RET_ONLINE_USER)) {
                    String[] onlineUsers = message.getContent().split(" ");
                    System.out.println("\nUsers list");
                    for (int i = 0; i < onlineUsers.length; i++) {
                        System.out.println("User: " + onlineUsers[i]);
                    }
                } else if (message.getMessage_type().equals(Message_type.MESSAGE_SEND_WORDS)) {
                System.out.println("\n" + message.getSender()
                        + " sent" + message.getReceiver() + " : " + message.getContent());
            }else if (message.getMessage_type().equals(Message_type.MESSAGE_SEND_ALL)) {
                    System.out.println("\n" + message.getSender() + " sent " + message.getContent());
                }else {
                    System.out.println("Other message");
                }

            } catch(Exception e){
            e.printStackTrace();
        }
    }
}
}
