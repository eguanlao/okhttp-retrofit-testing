package okhttp;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class OkHttpTest {

    MockWebServer server;

    @Before
    public void setUp() {
        // Create a MockWebServer. These are lean enough that you can create a new
        // instance for every unit test.
        server = new MockWebServer();
    }

    @After
    public void tearDown() throws IOException {
        // Shut down the server. Instances cannot be reused.
        server.shutdown();
    }

    @Test
    public void test() throws Exception {
        // Schedule some responses.
        server.enqueue(new MockResponse().setBody("hello, world!"));
        server.enqueue(new MockResponse().setBody("sup, bra?"));
        server.enqueue(new MockResponse().setBody("yo dog"));

        // Start the server.
        server.start();

        // Ask the server for its URL. You'll need this to make HTTP requests.
        HttpUrl baseUrl = server.url("/v1/chat");

        // Exercise your application code, which should make those HTTP requests.
        // Responses are returned in the same order that they are enqueued.
        Chat chat = new Chat(baseUrl);

        chat.loadMore();
        assertEquals("hello, world!", chat.messages());

        chat.loadMore();
        chat.loadMore();
        assertEquals(""
                + "hello, world!\n"
                + "sup, bra?\n"
                + "yo dog", chat.messages());
    }

}
