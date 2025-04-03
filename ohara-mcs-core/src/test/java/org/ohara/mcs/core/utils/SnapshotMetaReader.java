//package org.ohara.mcs.core.utils;
//
//import com.alipay.sofa.jraft.entity.RaftOutter;
//import com.alipay.sofa.jraft.storage.snapshot.SnapshotReader;
//import com.alipay.sofa.jraft.storage.snapshot.local.LocalSnapshotReader;
//
//import com.alipay.sofa.jraft.storage.snapshot.SnapshotReader;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//public class SnapshotMetaReader {
//    public static void main(String[] args) throws Exception {
//        // 读取二进制文件
//        byte[] data = Files.readAllBytes(Paths.get("/Users/caoshipeng/IdeaProjects/ohara-mcs/raft/config_center_group/127.0.0.1_9001/snapshot/snapshot_13/__raft_snapshot_meta"));
//
//        // 解析 Protobuf
//        //RaftOutter.SnapshotMeta meta = RaftOutter.SnapshotMeta.parseFrom(data);
//        RaftOutter.SnapshotMeta meta = RaftOutter.SnapshotMeta.parseFrom(data);
//
//        // 打印元数据
//        System.out.println("Last Included Index: " + meta.getLastIncludedIndex());
//        System.out.println("Last Included Term: " + meta.getLastIncludedTerm());
//        System.out.println("Peers: " + meta.getPeersList());
//        System.out.println("Learners: " + meta.getLearnersList());
//
//        String snapshotPath = "/Users/caoshipeng/IdeaProjects/ohara-mcs/raft/config_center_group/127.0.0.1_9001/snapshot/snapshot_13/__raft_snapshot_meta";
//        SnapshotReader reader = new LocalSnapshotReader(null, null, snapshotPath, null);
////        if (reader.init(null)) {
////            RaftOutter.SnapshotMeta load = reader.load();
////            System.out.println("Last Included Index: " + reader.getSnapshotMeta().getLastIncludedIndex());
////            System.out.println("Last Included Term: " + reader.getSnapshotMeta().getLastIncludedTerm());
////            System.out.println("Peers: " + reader.getSnapshotMeta().getPeersList());
////        }
//    }
//}
