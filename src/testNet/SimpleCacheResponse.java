package testNet;

import java.io.IOException;
import java.io.InputStream;
import java.net.CacheResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SimpleCacheResponse extends CacheResponse {
    private final Map<String, List<String>> headers;
    private final SimpleCacheRequset requset;
    private final Date expires;

    public SimpleCacheResponse(Map<String, List<String>> headers,
                               SimpleCacheRequset requset, Date expires) {
        this.headers = headers;
        this.requset = requset;
        this.expires = expires;
    }

    @Override
    public Map<String, List<String>> getHeaders() throws IOException {
        return null;
    }

    @Override
    public InputStream getBody() throws IOException {
        return null;
    }
}
