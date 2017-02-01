import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;

/**
 * Created by rmw on 25/01/2017.
 */
public class Receiver {

    public static void main(String a[]) throws XMPPException, InterruptedException {
        XMPPConnection connection = new XMPPConnection("192.168.1.38");
        System.out.println(connection);
        connection.connect();


        connection.login("test2", "123456");
        EFLogger.LogInfo("Receiver", "Connected [ " + connection.isConnected() + " ]");

        connection.getChatManager().addChatListener(new ChatManagerListener() {
            public void chatCreated(Chat chat, boolean b) {
                System.out.println("In Message Listener ! ");
                chat.addMessageListener(new MessageListener() {
                    public void processMessage(Chat chat, Message message) {
                        System.out.println("Message [ " + message.toXML());
                    }
                });
                try {
                    chat.sendMessage("Hello");
                } catch (XMPPException e) {
                    e.printStackTrace();
                }
            }
        });


        while (true) {

        }
    }
}