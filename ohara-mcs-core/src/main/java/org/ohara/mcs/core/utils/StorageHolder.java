package org.ohara.mcs.core.utils;

import org.ohara.mcs.core.storage.MetadaStorage;
import org.ohara.mcs.core.storage.Storage;
import org.ohara.mcs.spi.SpiExtensionFactory;

public class StorageHolder {

    @SuppressWarnings("unchecked")
    public static <T extends Storage<?>> T getInstance(String key) {
        Storage<?> extension = SpiExtensionFactory.getExtension(key, Storage.class);
        return (T) extension;
    }

}
