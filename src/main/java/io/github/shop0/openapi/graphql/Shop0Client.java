package io.github.shop0.openapi.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequest;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;

import javax.net.ssl.*;
import java.io.IOException;
import java.util.TimeZone;

import static com.fasterxml.jackson.core.JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.PropertyNamingStrategy.LOWER_CAMEL_CASE;
import static com.fasterxml.jackson.databind.SerializationFeature.*;

public class Shop0Client {
    private Shop0Configuration configuration;

    public static ObjectMapper mapper() {
        return new ObjectMapper()
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                .setPropertyNamingStrategy(LOWER_CAMEL_CASE)
                .setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"))
                .disable(WRITE_DATES_AS_TIMESTAMPS)
                .disable(WRITE_DURATIONS_AS_TIMESTAMPS)
                .disable(FAIL_ON_EMPTY_BEANS)
                .configure(WRITE_BIGDECIMAL_AS_PLAIN, true)
                .enable(FAIL_ON_READING_DUP_TREE_KEY)
                .disable(FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static final ObjectMapper MAPPER = mapper();

    private Shop0Client(Shop0Configuration configuration) {
        this.configuration = configuration;
        httpClient = buildHttpClient();
    }

    public static class Builder {
        private Shop0Configuration configuration = new Shop0Configuration();

        public Builder host(String host) {
            configuration.host = host;
            return this;
        }

        public Builder debug(boolean v) {
            configuration.debug = v;
            return this;
        }

        public Builder accessToken(String accessToken) {
            configuration.accessToken = accessToken;
            return this;
        }

        public Shop0Client build() {
            if (configuration.host == null) {
                configuration.host = "http://localhost:8080/graphql";
            }

            Shop0Client shop0Client = new Shop0Client(this.configuration);
            return shop0Client;
        }
    }

    private OkHttpClient httpClient;
    private OkHttpClient buildHttpClient() {
        if (httpClient == null) {
            synchronized (Shop0Client.class) {
                if (httpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    if (configuration.debug) {
                        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                        builder.addInterceptor(loggingInterceptor);
                    }

                    try {
                        final TrustManager[] trustAllCerts = new TrustManager[]{
                                new X509TrustManager() {
                                    @Override
                                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                                    }

                                    @Override
                                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                                    }

                                    @Override
                                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                        return new java.security.cert.X509Certificate[]{};
                                    }
                                }
                        };

                        final SSLContext sslContext = SSLContext.getInstance("SSL");
                        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                        final javax.net.ssl.SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
                        builder.sslSocketFactory(sslSocketFactory);

                        builder.hostnameVerifier(new HostnameVerifier() {
                            @Override
                            public boolean verify(String hostname, SSLSession session) {
                                return true;
                            }
                        });
                        httpClient = builder.build();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return httpClient;
    }


    public <T> T invoke(GraphQLRequest req, Class<T> rspCls) throws IOException {
        MediaType json = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(json, req.toHttpJsonBody());
        Request request = new Request.Builder().header("Authorization", configuration.accessToken).url(configuration.host).post(body).build();
        Call call = httpClient.newCall(request);
        String data = call.execute().body().string();

        return MAPPER.readerFor(rspCls).readValue(data);
    }
}
