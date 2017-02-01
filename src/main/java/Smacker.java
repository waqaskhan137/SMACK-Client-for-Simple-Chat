///**
// * Created by rmw on 30/01/2017.
// */
//
//import org.jivesoftware.smack.*;
//import org.jivesoftware.smack.packet.Message;
//import org.jivesoftware.smack.packet.Packet;
//import org.jivesoftware.smackx.muc.MultiUserChat;
//
//
//public class Smacker {
//
//    public static void main(String[] args) {
//
//        XMPPConnection connection;
//
////        SmackConfiguration.setLocalSocks5ProxyEnabled(false);
//
//
//        System.out.println("Starting session...");
//        try {
//            // Create a connection to the igniterealtime.org XMPP server.
//            String server = "192.168.1.38";
//            int port = 5222;
//            ConnectionConfiguration config = new ConnectionConfiguration(server, port);
//            config.setSASLAuthenticationEnabled(false);
//            config.setReconnectionAllowed(true);
//            config.setCompressionEnabled(false);
//            config.setDebuggerEnabled(true);
//            config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
//
//            connection = new XMPPConnection(config);
//            System.out.println("Connecting to : " + connection.getHost() + ":" + connection.getPort());
//            // Connect to the server
//            connection.connect();
//            // Most servers require you to login before performing other tasks.
//            String username = "test@email.com";
//            String password = "123456";
//            // will receive the message sent.
//            String receiver = "test2@email.com";
//            connection.login(username, password);
//
//            MultiUserChat muc;
//            muc = new MultiUserChat(connection, "TestRoom");
//            muc.create("TestRoom");
//
//            muc.join("Waqas");
//
//            muc.addMessageListener(new PacketListener() {
//
//                public void processPacket(Packet packet) {
//                    System.out.println("Got Message { \n " + packet.toXML());
//                }
//            });
//
//            muc.sendMessage("Hello World ! ");
//            muc.sendMessage("Hello World ! ");
//
//
//            /*
//            ChatManager cm = connection.getChatManager();
//
//            Chat chat = cm.createChat(receiver, new MessageListener() {
//                public void processMessage(Chat chat, Message message) {
//                    System.out.println("Received message: Subject "
//                            + message.getSubject() +
//                            "\n : " + message.getBody() +
//                            "\n : " + message.getFrom() + " "
//                            + "\n" + message.toXML() +
//                            "\n" + message.getError());
//                }
//            });
//
//            chat.sendMessage("Smack> Message sent via API.");
//
//            //Thread.currentThread();
//            Thread.sleep(10000);
//            // Disconnect from the server
//            connection.disconnect();
//            */
//        } catch (XMPPException e) {
//            e.printStackTrace();
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//        }
//        System.out.println("Ended session...");
//    }
//
//}
