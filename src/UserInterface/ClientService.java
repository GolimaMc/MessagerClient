package UserInterface;

import Common.Message;
import Common.Message_type;
import Common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientService {
    private User u = new User();
    private Socket socket;

    public boolean checkUser(String userId, String pwd) {
        boolean b = false;
        u.setUserid(userId);
        u.setPassword(pwd);


        try {
            socket = new Socket(InetAddress.getLocalHost(), 9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message ms = (Message) ois.readObject();

            if (ms.getMessage_type().equals(Message_type.MESSAGE_LOGIN_SUCCEED)) {//登录OK

                ClientConnectServerThread clientConnectServerThread =
                        new ClientConnectServerThread(socket);
                clientConnectServerThread.start();
                ClientThreadCollection.add(userId, clientConnectServerThread);
                b = true;
            } else {
                socket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return b;

    }

    public void onlineFriendList() {

        Message message = new Message();
        message.setMessage_type(Message_type.MESSAGE_SHOW_ONLINE_USER);
        message.setSender(u.getUserid());


        try {
            ClientConnectServerThread clientConnectServerThread =
                    ClientThreadCollection.get(u.getUserid());
            Socket socket = clientConnectServerThread.getSocket();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void logout() {
        Message message = new Message();
        message.setMessage_type(Message_type.MESSAGE_CLIENT_EXIT);
        message.setSender(u.getUserid());

        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(ClientThreadCollection.get(u.getUserid()).getSocket().getOutputStream());
            oos.writeObject(message);
            System.out.println(u.getUserid() + " logout ");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
