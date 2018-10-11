package bin.manager.common.winxin.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lenovo on 2018/10/11.
 */
public class WinXinUploadUtil {
    /**
     * 模拟form表单的形式 ，上传文件 以输出流的形式把文件写入到url中，然后用输入流来获取url的响应
     *
     * @param url      请求地址 微信接口地址比如http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=image
     * @param filePath 文件在服务器保存路径比如localhost:8080/WeChat/file/xxx.jpg
     * @return String url的响应信息返回值
     * @throws IOException
     */
    public static String uploadFile(String url, String access_token, String type, String filePath) {
        String result = null;
        url = url.replace("ACCESS_TOKEN", access_token).replace("TYPE", type);
        HttpURLConnection downloadCon = null;
        InputStream inputStream = null;
        BufferedReader reader = null;

        try {
            URL urlFile = new URL(filePath);
            downloadCon = (HttpURLConnection) urlFile.openConnection();
            inputStream = downloadCon.getInputStream();

            /**
             * 第一部分
             */
            URL urlObj = new URL(url);
            // 连接
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            /**
             * 设置关键值
             */
            con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false); // post方式不能使用缓存
            // 设置请求头信息
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");
            // 设置边界
            String BOUNDARY = "---------------------------" + System.currentTimeMillis();
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary="
                    + BOUNDARY);
            // 请求正文信息
            // 第一部分：
            StringBuilder sb = new StringBuilder();
            String regex = ".*/([^\\.]+)";
            sb.append("--"); // 必须多两道线
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"media\";filename=\""
                    + filePath.replaceAll(regex, "$1") + "\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            byte[] head = sb.toString().getBytes("utf-8");
            // 获得输出流
            OutputStream out = new DataOutputStream(con.getOutputStream());
            // 输出表头
            out.write(head);
            // 文件正文部分
            // 把文件已流文件的方式 推入到url中
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = inputStream.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            inputStream.close();
            // 结尾部分
            byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
            out.write(foot);

            out.flush();
            out.close();

            StringBuffer buffer = new StringBuffer();
            // 定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}
