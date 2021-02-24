package com.k7.handlers;

import com.k7.entities.Order;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class SaveOrderHandler extends OrderHandler {
    @Override
    public void handler(Order o) {
        try (SeekableByteChannel channel = Files.newByteChannel(
                Path.of("orders.txt"),
                StandardOpenOption.APPEND)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(new String(o.getId() + ":" + o.getFrom() + "["+o.getDescription() + "]\n")
                    .getBytes());
            buffer.flip();
            channel.write(buffer);
            buffer.clear();
            next(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
