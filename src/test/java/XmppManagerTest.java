import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;

public class XmppManagerTest {

    @Mock
    private XMPPConnection connection;

    @Mock
    private ChatManager chatManager;

    @Mock
    private MessageListener messageListener;

    private XmppManager xmppManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        xmppManager = new XmppManager("server", 1234);
        xmppManager.connection = connection;
        xmppManager.chatManager = chatManager;
        xmppManager.messageListener = messageListener;
    }

    @Test
    public void testPerformLogin() throws XMPPException {
        when(connection.isConnected()).thenReturn(true);
        xmppManager.performLogin("username", "password");
        verify(connection, times(1)).login("username", "password");
    }

    @Test
    public void testSetStatus() {
        xmppManager.setStatus(true, "status");
        verify(connection, times(1)).sendPacket(any(Presence.class));
    }

    @Test
    public void testDestroy() {
        when(connection.isConnected()).thenReturn(true);
        xmppManager.destroy();
        verify(connection, times(1)).disconnect();
    }

    @Test
    public void testSendMessage() throws XMPPException {
        when(chatManager.createChat(anyString(), eq(messageListener))).thenReturn(mock(Chat.class));
        xmppManager.sendMessage("message", "buddyJID");
        verify(chatManager, times(1)).createChat("buddyJID", messageListener);
    }

    @Test
    public void testCreateEntry() throws Exception {
        Roster roster = mock(Roster.class);
        when(connection.getRoster()).thenReturn(roster);
        xmppManager.createEntry("user", "name");
        verify(roster, times(1)).createEntry("user", "name", null);
    }
}