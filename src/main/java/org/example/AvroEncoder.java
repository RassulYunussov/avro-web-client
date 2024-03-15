package org.example;

import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.Encoder;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.MimeType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AvroEncoder implements Encoder<User> {
    final AvroSerializer<User> serializer = new AvroSerializer<>(User.getClassSchema());

    @Override
    public boolean canEncode(ResolvableType elementType, MimeType mimeType) {
        return true;
    }

    @Override
    public Flux<DataBuffer> encode(Publisher<? extends User> inputStream, DataBufferFactory bufferFactory, ResolvableType elementType, MimeType mimeType, Map<String, Object> hints) {
        return Mono.from(inputStream).map(value -> this.encodeValue(value, bufferFactory, elementType, mimeType, hints)).flux();
    }

    public DataBuffer encodeValue(User value, DataBufferFactory bufferFactory, ResolvableType valueType, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {
        try {
            final var bytes = serializer.serialize(value);
            DataBuffer buffer = bufferFactory.allocateBuffer(bytes.length);
            buffer.write(bytes);
            return buffer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MimeType> getEncodableMimeTypes() {
        return Collections.emptyList();
    }
}
