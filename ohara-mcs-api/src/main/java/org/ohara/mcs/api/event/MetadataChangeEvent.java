package org.ohara.mcs.api.event;

import org.ohara.mcs.api.grpc.auto.Metadata;

/**
 * @author Spring Cat
 */
public record MetadataChangeEvent(Metadata metadata, EventType type) implements Event {
}
