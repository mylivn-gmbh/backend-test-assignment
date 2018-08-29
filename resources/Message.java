package com.mylivn.ta;

/**
 * This interface represents a message received from a MessageBroker.
 * Each message have a unique id.
 * Each message should be committed after successful processing.
 * If message is not committed, it means that processing failed and any other consumer can read it after configured timeout.
 * Optionally message can be manually marked as failed to process. In this case it will be available to other consumers immediately.
 */
public interface Message {

    /**
     * Get message id.
     *
     * @return unique message id.
     */
    String getId();

    /**
     * Get message body.
     *
     * @return message body.
     */
    String getBody();

    /**
     * Confirm that message received and successfully processed.
     */
    void commit();

    /**
     * Marks message as failed to be processed immediately.
     */
    void fail();
}
