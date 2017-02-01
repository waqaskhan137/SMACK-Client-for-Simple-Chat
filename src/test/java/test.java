import org.junit.Assert;
import org.junit.Test;

/**
 * Created by rmw on 25/01/2017.
 */
public class test {

    Main m = new Main();

    @Test
    public void testHello() {
        String test = "Hello World";
        Assert.assertEquals(test, m.hello());
    }
}
