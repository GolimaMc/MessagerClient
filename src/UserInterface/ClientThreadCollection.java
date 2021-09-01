package UserInterface;

import java.net.Socket;
import java.util.HashMap;

public class ClientThreadCollection {
    public static HashMap<String,ClientConnectServerThread> map = new HashMap<>();
    public static void add(String id,ClientConnectServerThread thread){
        map.put(id,thread);
    }
    public static ClientConnectServerThread get(String id){
        return map.get(id);
    }
}
