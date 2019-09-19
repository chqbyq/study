package org.chq.study.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description: 学习NIO
 * @Author: chenhq
 * @Email: 542504275@qq.com
 * @CreateDate: 2019/6/11 22:58
 */
public class NioUtil {

    public static void test() throws IOException {

        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("/data/nio.txt", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();

            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();

    }

    public static void main(String[] args) {
        try {
            test();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
