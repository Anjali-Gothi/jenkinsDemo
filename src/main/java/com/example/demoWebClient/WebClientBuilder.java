package com.example.demoWebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.net.URI;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class WebClientBuilder {
    public void getClient(String url) {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(conn ->
//                        5 secs
                        conn.addHandlerLast(new ReadTimeoutHandler(50000, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(50000, TimeUnit.MILLISECONDS)));
        WebClient client= WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(url)
                .defaultCookie("value", "value1")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", url))
                .build();
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec=client.post();
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(URI.create("/resource"));
    }

}
