package org.ohara.mcs.core.event;

import com.alipay.sofa.jraft.entity.PeerId;
import org.ohara.mcs.api.event.Event;


public record LeaderRefreshEvent(PeerId peerId) implements Event {
}
