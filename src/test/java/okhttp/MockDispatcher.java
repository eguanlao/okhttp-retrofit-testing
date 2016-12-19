package okhttp;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.QueueDispatcher;
import okhttp3.mockwebserver.RecordedRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MockDispatcher extends QueueDispatcher {

    final Map<String, MockResponse> mockResponseMap;

    MockDispatcher() {
        mockResponseMap = new HashMap<>();
        mockResponseMap.put("/v1/chat/messages/", new MockResponse().setBody("hello, world!"));
        mockResponseMap.put("/v1/chat/messages/2", new MockResponse().setBody("sup, bra?"));
        mockResponseMap.put("/v1/chat/messages/3", new MockResponse().setBody("yo dog"));
    }

    @Override
    public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
        return Optional.ofNullable(mockResponseMap.get(request.getPath())).orElse(new MockResponse().setResponseCode(404));
    }

}
