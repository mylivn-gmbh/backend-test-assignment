package com.mylivn.ta;

import java.util.List;

/**
 * Simple message queue interface.
 * Queue supports sending and receiving messages.
 */
public interface MessageBroker {
    /**
     * Sends message to the queue with specified name (id).
     * This method can block.
     *
     * @param queue queue name (id)
     * @param message message body
     * @return unique id assigned to a message
     */
    String send(String queue, String message);

    /**
     * Receive available messages in queue.
     * If there are no messages available, method immediately returns an empty list.
     * If there are too many messages, method can return only first part of them.
     * Other messages can be requested with further calls of this method.
     * This method can block.
     *
     * @param queue queue name (id)
     * @return list of messages (ordered from earliest to the latest)
     */
    List<Message> receive(String queue);
}
