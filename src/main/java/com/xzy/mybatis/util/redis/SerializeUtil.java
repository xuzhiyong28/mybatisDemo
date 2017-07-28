package com.xzy.mybatis.util.redis;

import java.io.*;

/**
 * Created by XZY on 2017/5/2.
 * 序列化 反序列化 对象
 */
public class SerializeUtil {
    /***
     * 序列化
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    /**
     * 反序列化
     *
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /***
     * 根据字节数截取字符串，若发现字节数不符合一个汉字则减掉这个汉字 （UTF-8 汉字=3字节）
     * @param string
     * @param len
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String substrUTF8(String string, int len)
            throws UnsupportedEncodingException {
        byte[] bytes = string.getBytes("UTF-8");
        if (bytes == null || bytes.length == 0 || len <= 0) {
            return "";
        }
        if (len >= bytes.length) {
            return new String(bytes, "UTF-8");
        }
        int index = 0;
        while (index < len) {
            final byte b = bytes[index];
            if ((b & 0x80/*0b10000000*/) == 0) {// is ascii
                ++index;
            } else {
                int count = 1;
                byte t = 0x40/*0b01000000*/;
                for (int i = 1; i < 8; ++i) {
                    if ((b & t) != 0) {
                        ++count;
                        t >>>= 1;
                    } else {
                        break;
                    }
                }
                final int sum = index + count;
                if (sum <= len) {
                    index = sum;
                } else {
                    break;
                }
            }
        }
        return new String(bytes, 0, index, "UTF-8");
    }

}
