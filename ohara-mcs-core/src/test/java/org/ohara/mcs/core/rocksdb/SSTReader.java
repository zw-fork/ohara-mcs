package org.ohara.mcs.core.rocksdb;

import com.alipay.sofa.jraft.entity.LogEntry;
import com.alipay.sofa.jraft.entity.codec.DefaultLogEntryCodecFactory;
import com.alipay.sofa.jraft.entity.codec.LogEntryCodecFactory;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksIterator;

public class SSTReader {
    static {
        RocksDB.loadLibrary();
    }

    public static void main(String[] args) throws Exception {
        Options options = new Options().setCreateIfMissing(false);
        // 打开 RocksDB 实例（需包含所有 .sst 文件的目录）
        String logPath = "/Users/caoshipeng/IdeaProjects/ohara-mcs/raft/config_center_group/127.0.0.1_9001/logs";
//        try (RocksDB db = RocksDB.openReadOnly(options, "/path/to/raft/logs")) {
        try (RocksDB db = RocksDB.openReadOnly(options, logPath)) {
            // 遍历所有键值对
            try (RocksIterator iterator = db.newIterator()) {
                for (iterator.seekToFirst(); iterator.isValid(); iterator.next()) {
                    long logIndex = bytesToLong(iterator.key());
                    byte[] logEntryBytes = iterator.value();
                    // 反序列化 LogEntry（需实现反序列化逻辑）
//                    LogEntry logEntry = LogEntry.parseFrom(logEntryBytes);
                    DefaultLogEntryCodecFactory factory = DefaultLogEntryCodecFactory.getInstance();
                    LogEntry decode = factory.decoder().decode(logEntryBytes);
                    System.out.printf("Index: %d, Entry: %s%n", logIndex, factory);
                }
            }
        }
    }

    // 将字节数组转换为 long（Key的编码方式需与JRaft实现一致）
    private static long bytesToLong(byte[] bytes) {
        return ((bytes[7] & 0xFFL) << 56) |
               ((bytes[6] & 0xFFL) << 48) |
               ((bytes[5] & 0xFFL) << 40) |
               ((bytes[4] & 0xFFL) << 32) |
               ((bytes[3] & 0xFFL) << 24) |
               ((bytes[2] & 0xFFL) << 16) |
               ((bytes[1] & 0xFFL) << 8) |
               (bytes[0] & 0xFFL);
    }
}
