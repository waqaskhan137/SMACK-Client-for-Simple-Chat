
public class Main {

    public static void main(String[] args) throws Exception {

        String username = "test";
        String password = "123456";

        XmppManager xmppManager = new XmppManager("192.168.1.38", 5222);

        xmppManager.init();
        xmppManager.performLogin(username, password);
        xmppManager.setStatus(true, "Hello everyone");

        String buddyJID = "test2@ef-app2/Smack";
        String buddyName = "test";
        xmppManager.createEntry(buddyJID, buddyName);


        for (int i = 0; i < 10; i++) {
            xmppManager.sendMessage("Hello mate from test " + i, "test2");
        }

        boolean isRunning = true;

        while (isRunning) {
            Thread.sleep(50);
        }

        xmppManager.destroy();

    }


    //Just to test Junit Lib
    public static String hello() {
        return "Hello World";
    }
}
