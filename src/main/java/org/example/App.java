package org.example;

import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;

public class App {
    public static void main(String[] args) {
        ExchangeStrategies strategies = ExchangeStrategies.builder().codecs(clientCodecConfigurer -> {
            clientCodecConfigurer.registerDefaults(false);
            clientCodecConfigurer.customCodecs().register(new AvroEncoder());
            clientCodecConfigurer.customCodecs().register(new AvroDecoder());
        }).build();
        final var client = WebClient.builder().exchangeStrategies(strategies).baseUrl("http://localhost:8080").build();
        final var user = UserFactory.getAvroUser();
        final var resultOfPost = client.post().uri("/avro")
                .body(Mono.just(user), User.class)
                .retrieve().bodyToMono(User.class)
                .block();
        System.out.println(resultOfPost);
    }
}
