package okhttp;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Chat {

    private final OkHttpClient client = new OkHttpClient();
    private final HttpUrl baseUrl;
    private final List<String> messages;

    Chat(HttpUrl baseUrl) {
        this.baseUrl = baseUrl;
        messages = new ArrayList<>();
    }

    void loadMore() throws IOException {
        int index = messages.size();
        String url = baseUrl + "/messages/" + (index > 0 ? index + 1 : "");

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "foo")
                .build();

        Response response = client.newCall(request).execute();
        messages.add(response.body().string());
    }

    String messages() throws IOException {
        return messages.stream().collect(Collectors.joining("\n"));
    }

}
