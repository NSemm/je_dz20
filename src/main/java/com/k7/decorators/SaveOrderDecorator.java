package com.k7.decorators;

import com.k7.entities.Order;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@AllArgsConstructor
public class SaveOrderDecorator implements OrderDecorator {
    private OrderDecorator wrapObj;

    @Override
    public void Proc(Order o) {
        try (SeekableByteChannel channel = Files.newByteChannel(
                Path.of("orders.txt"),
                StandardOpenOption.APPEND)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(new String(o.getId() + ":" + o.getFrom() + "[" + o.getDescription() + "]\n")
                    .getBytes());
            buffer.flip();
            channel.write(buffer);
            buffer.clear();
            wrapObj.Proc(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
