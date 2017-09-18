package com.liulu.sell.util;

import net.sf.json.JSONObject;
import java.io.*;
import org.apache.commons.io.IOUtils;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 2017/9/5
 * Time: 15:54
 * To change this template use File | Settings | File Templates.
 */
public class RestUtils {

    /**
     * 缓冲流获取传入数据
     *
     * @return
     * @throws Exception
     */
    public static JSONObject getRequestParam() throws Exception {
        InputStream is = SpringBootUtil.getHttpServletRequest().getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return JSONObject.fromObject(sb.toString());
    }


    /**
     * 使用IoUtils工具获取传入数据
     *
     * @return
     * @throws Exception
     */
    public static JSONObject getRequestParamByIoUtils() throws Exception {
        InputStream is = SpringBootUtil.getHttpServletRequest().getInputStream();
        String result = IOUtils.toString(is, "Utf-8");
        return JSONObject.fromObject(result);
    }

    /**
     * 字节流获取传入数据
     * @return
     */
    public static JSONObject getRequestParamByByteArray() {
        byte[] buf = null;
        String strParam = null;
        ByteArrayOutputStream baos = null;
        HttpServletRequest request = SpringBootUtil.getHttpServletRequest();
        int tmp = 0;
        try {
            baos = new ByteArrayOutputStream();
            while ((tmp = request.getInputStream().read()) != -1) {
                baos.write(tmp);
            }
            buf = baos.toByteArray();
            System.out.println("buf:" + buf);
            strParam = new String(buf, "UTF-8");
            System.out.println("strParam:" + strParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String a = "<?xml version=\"1.0\" encoding=\"GBK\"?><AIPG>";
        return JSONObject.fromObject(strParam);
    }
}
