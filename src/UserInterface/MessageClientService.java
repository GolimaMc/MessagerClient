package UserInterface;

import Common.Message;
import Common.Message_type;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

public class MessageClientService {
    public void sendMessageToAll(String content, String senderId) {
        //构建message
        Message message = new Message();
        message.setMessage_type(Message_type.MESSAGE_SEND_ALL);
        message.setSender(senderId);
        message.setContent(content);
        message.setTime(new Date().toString());
        System.out.println(senderId + " said " + content);

        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(ClientThreadCollection.get(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendMessageToOne(String content, String senderId, String receiverId) {
        Message message = new Message();
        message.setMessage_type(Message_type.MESSAGE_SEND_WORDS);
        message.setSender(senderId);
        message.setReceiver(receiverId);
        message.setContent(content);
        message.setTime(new Date().toString());
        System.out.println(senderId + " told " + receiverId + " : " + content);

        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(ClientThreadCollection.get(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
