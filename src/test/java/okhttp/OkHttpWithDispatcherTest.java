package okhttp;

public class OkHttpWithDispatcherTest extends OkHttpTest {

    @Override
    public void setUp() {
        super.setUp();
        server.setDispatcher(new MockDispatcher());
    }

}
