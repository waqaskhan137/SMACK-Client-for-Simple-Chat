
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Type;

////This class is responsible to handle all Actions related to Chat Management. Connection, Login, Status, Create Entry and Message Listener.

public class XmppManager {

    private static final int packetReplyTimeout = 500; // millis

    private String server;
    private int port;

    private ConnectionConfiguration config;
    private XMPPConnection connection;

    private ChatManager chatManager;
    private MessageListener messageListener;


    public XmppManager(String server, int port) {
        this.server = server;
        this.port = port;
    }

    public void init() throws XMPPException {

        EFLogger.LogInfo("XmppManager", String.format("Initializing connection to server %1$s port %2$d", server, port));

        SmackConfiguration.setPacketReplyTimeout(packetReplyTimeout);
//        SmackConfiguration.DEBUG = true;
        config = new ConnectionConfiguration(server, port);
        config.isDebuggerEnabled();

        connection = new XMPPConnection(config);
        connection.connect();

        EFLogger.LogInfo("XmppManager", "Connected: " + connection.isConnected());

        chatManager = connection.getChatManager();
        //messageListener = new MyMessageListener();

    }

    public void performLogin(String username, String password) throws XMPPException {
        if (connection != null && connection.isConnected()) {
            EFLogger.LogInfo("XmppManager", "Before login userName [ " + username + " ] password [ " + password + " ]");
            connection.login(username, password);
            System.out.printf("Logged in ");

        }
    }

    public void setStatus(boolean available, String status) {

        Presence.Type type = available ? Type.available : Type.unavailable;
        Presence presence = new Presence(type);

        presence.setStatus(status);
        connection.sendPacket(presence);

    }

    public void destroy() {
        if (connection != null && connection.isConnected()) {
            connection.disconnect();
        }
    }

    public void sendMessage(String message, String buddyJID) throws XMPPException {
        EFLogger.LogInfo("XmppManager", String.format("Sending mesage '%1$s' to user %2$s", message, buddyJID));
        Chat chat = chatManager.createChat(buddyJID, messageListener);
        chat.sendMessage(message);
    }

    public void createEntry(String user, String name) throws Exception {
        EFLogger.LogInfo("XmppManager", String.format("Creating entry for buddy '%1$s' with name %2$s", user, name));
        Roster roster = connection.getRoster();
        roster.createEntry(user, name, null);
    }

    static class MyMessageListener implements MessageListener {

        public void processMessage(Chat chat, Message message) {
            String from = message.getFrom();
            String body = message.getBody();
            EFLogger.LogInfo("XmppManager", String.format("Received message '%1$s' from %2$s", message.getError(), from));
        }

    }

}
