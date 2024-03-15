package org.example;

import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.Decoder;
import org.springframework.core.codec.DecodingException;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.MimeType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AvroDecoder implements Decoder<User> {
    private int maxInMemorySize = 262144;
    final AvroSerializer<User> serializer = new AvroSerializer<>(User.getClassSchema());

    @Override
    public boolean canDecode(ResolvableType elementType, MimeType mimeType) {
        return true;
    }

    @Override
    public Flux<User> decode(Publisher<DataBuffer> inputStream, ResolvableType elementType, MimeType mimeType, Map<String, Object> hints) {
        return null;
    }

    @Override
    public Mono<User> decodeToMono(Publisher<DataBuffer> inputStream, ResolvableType elementType, MimeType mimeType, Map<String, Object> hints) {
        return DataBufferUtils.join(inputStream, this.maxInMemorySize).flatMap((dataBuffer) ->
                Mono.justOrEmpty(this.decode(dataBuffer, elementType, mimeType, hints))
        );
    }

    public User decode(DataBuffer dataBuffer, ResolvableType targetType, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) throws DecodingException {
        byte[] bytes = new byte[dataBuffer.readableByteCount()];
        dataBuffer.read(bytes);
        DataBufferUtils.release(dataBuffer);
        try {
            return serializer.deserialize(bytes);
        } catch (final Exception ignored) {

        }
        return null;
    }

    @Override
    public List<MimeType> getDecodableMimeTypes() {
        return Collections.emptyList();
    }
}
