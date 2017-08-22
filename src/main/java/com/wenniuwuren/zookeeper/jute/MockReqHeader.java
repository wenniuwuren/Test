package com.wenniuwuren.zookeeper.jute;

import org.apache.jute.*;
import org.apache.zookeeper.server.ByteBufferInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 请求头定义
 * Created by Yibin_Zhu on 2017/7/20.
 */
public class MockReqHeader implements Record{

    private long sessionId;
    private String type;

    public MockReqHeader() {}

    public MockReqHeader(long sessionId, String type) {
        this.sessionId = sessionId;
        this.type = type;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void serialize(OutputArchive archive, String tag) throws IOException {
        archive.startRecord(this, tag);
        archive.writeLong(sessionId, "sessionId");
        archive.writeString(type, "type");
        archive.endRecord(this, tag);
    }

    @Override
    public void deserialize(InputArchive archive, String tag) throws IOException {
        archive.startRecord(tag);
        sessionId = archive.readLong("sessionId");
        type = archive.readString("type");
        archive.endRecord(tag);
    }

    public static void main(String[] args) {
        // 1. 实体类实现 Record 接口
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 2. 构建一个序列化器 BinaryOutputArchive
            BinaryOutputArchive boa = BinaryOutputArchive.getArchive(baos);
            // 3. 序列化，序列化到指定的 tag
            new MockReqHeader(0x34211eccb92a34el, "ping").serialize(boa, "header");
            // 这里通常是 TCP 网络传输对象
            ByteBuffer bb = ByteBuffer.wrap(baos.toByteArray());
            // 4. 开始反序列化
            ByteBufferInputStream bbis = new ByteBufferInputStream(bb);
            BinaryInputArchive bbia = BinaryInputArchive.getArchive(bbis);
            MockReqHeader header2 = new MockReqHeader();
            header2.deserialize(bbia, "header");
            bbis.close();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
