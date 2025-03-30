package org.ohara.msc.listener;


public interface ConfigData {

    default String key() {
        return this.getClass().getSimpleName();
    }

}
