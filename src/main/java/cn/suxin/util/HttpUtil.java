package cn.suxin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.apache.commons.lang.StringUtils;

public class HttpUtil {

    public static final int DEFAULT_CONNECT_TIME_OUT = 30000;

    public static final int DEFAULT_READ_TIME_OUT = 30000;

    public static final String DEFAULT_CHARSET = "UTF-8";

    public static final String THREADLOCAL_SPANID = "spanid";     //spanId

    public static String sendRequest(String url, int connectTimeout, int readTimeout, String charset, boolean returnSingle) {
        BufferedReader in = null;
        HttpURLConnection conn = null;
        try {
            if (StringUtils.isBlank(charset)) {
                charset = DEFAULT_CHARSET;
            }
            conn = getURLConnection(url, connectTimeout, readTimeout);
            in = new BufferedReader(new InputStreamReader(connect(conn), charset));
            String result = getReturnResult(in, returnSingle);
            if (StringUtils.isBlank(result)) {
                //throw new RemoteInvocationFailureException("网络异常，" + url + "无法联通");
                //LoggerUtil.alarmInfo("[合作方通讯][普通][网络异常，"+url + "无法联通]");
                return null;
            }
            return result;
        } catch (Exception e) {
            //logger.error("", e);
            //throw new RemoteInvocationFailureException("网络IO异常[" + url + "]", e);
            if (e instanceof RemoteInvocationFailureException
                || e instanceof java.net.ConnectException
                || e instanceof java.net.SocketTimeoutException
                || e instanceof IOException) {//处理一下超时
//					LoggerUtil.alarmInfo("[合作方通讯][普通][网络IO异常，"+url + "无法联通或连接超时]");

            } else {
//					LoggerUtil.error("未知异常",e);
            }
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }

                if (conn != null) {
                    conn.disconnect();
                }
            } catch (IOException e) {
                //logger.error("", e);
            }
        }
    }

    public static String sendRequest(String url, boolean returnSingle) throws RemoteInvocationFailureException {
        return sendRequest(url, DEFAULT_CONNECT_TIME_OUT, DEFAULT_READ_TIME_OUT, DEFAULT_CHARSET, returnSingle);
    }

    public static byte[] sendRequest(String url, int connectTimeout, int readTimeout) throws RemoteInvocationFailureException {
        InputStream is = null;
        HttpURLConnection conn = null;
        try {
            conn = getURLConnection(url, connectTimeout, readTimeout);
            is = connect(conn);
            int size = is.available();
            byte[] result = new byte[size];
            //logger.debug("result length:" + size);
            is.read(result, 0, size);
            return result;
        } catch (Exception e) {
            //logger.error("", e);
            //throw new RemoteInvocationFailureException("网络IO异常[" + url + "]", e);
            if (e instanceof RemoteInvocationFailureException
                || e instanceof java.net.ConnectException
                || e instanceof java.net.SocketTimeoutException
                || e instanceof IOException) {//处理一下超时
//					LoggerUtil.alarmInfo("[合作方通讯][普通][网络IO异常，"+url + "无法联通或连接超时]");

            } else {
//					LoggerUtil.error("未知异常",e);
            }
            return null;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (IOException e) {
                //logger.error("", e);
            }
        }
    }

    public static byte[] sendRequest(String url) throws RemoteInvocationFailureException {
        return sendRequest(url, DEFAULT_CONNECT_TIME_OUT, DEFAULT_READ_TIME_OUT);
    }

    public static String sendPostRequest(String url, String content, String charset) {
        return sendPostRequest(url, content, charset, DEFAULT_CONNECT_TIME_OUT, DEFAULT_READ_TIME_OUT);
    }

    public static String sendPostRequest(String url, String content, String charset, int connectTimeout, int readTimeout) {
        return sendPostRequest(url, content, charset, DEFAULT_CONNECT_TIME_OUT, DEFAULT_READ_TIME_OUT, false);
    }

    public static String sendPostRequest(String url, String content, String charset, int connectTimeout, int readTimeout,
        boolean needCompress) {
        BufferedReader in = null;
        HttpURLConnection httpConn = null;
        try {
            httpConn = getURLConnection(url, connectTimeout, readTimeout);
            if (StringUtils.isBlank(charset)) {
                charset = DEFAULT_CHARSET;
            }
            //			logger.debug("请求发送地址:" + url);
            //logger.debug("参数:" + content);
            InputStream stream = postConnect(httpConn, content, charset, needCompress);

            in = new BufferedReader(new InputStreamReader(stream, charset));
            String result = getReturnResult(in, false);
            //logger.debug("请求返回结果:" + result);
            if (StringUtils.isBlank(result)) {
                //throw new RemoteInvocationFailureException("网络异常，" + url + "无法联通");
//				LoggerUtil.alarmInfo("[合作方通讯][普通][网络异常，"+url + "无法联通]");
                return null;
            }
            return result;
        } catch (Exception e) {
            if (e instanceof RemoteInvocationFailureException
                || e instanceof java.net.ConnectException
                || e instanceof java.net.SocketTimeoutException
                || e instanceof IOException) {//处理一下超时
//					LoggerUtil.alarmInfo("[合作方通讯][普通][网络IO异常，"+url + "无法联通或连接超时]");

            } else {
//					LoggerUtil.error("未知异常",e);
            }
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }

                if (httpConn != null) {
                    httpConn.disconnect();
                }
            } catch (IOException e) {
                //logger.error("", e);
            }
        }
    }

    public static String sendPostRequest(String url, String content, String inCharset, String outCharset, int connectTimeout,
        int readTimeout, boolean needCompress) {
        BufferedReader in = null;
        HttpURLConnection httpConn = null;
        try {
            httpConn = getURLConnection(url, connectTimeout, readTimeout);
            if (StringUtils.isBlank(inCharset)) {
                inCharset = DEFAULT_CHARSET;
            }
            //			logger.debug("请求发送地址:" + url);
            //logger.debug("参数:" + content);
            InputStream stream = postConnect(httpConn, content, inCharset, needCompress);

            in = new BufferedReader(new InputStreamReader(stream, outCharset));
            String result = getReturnResult(in, false);
            //logger.debug("请求返回结果:" + result);
            if (StringUtils.isBlank(result)) {
                //throw new RemoteInvocationFailureException("网络异常，" + url + "无法联通");
//				LoggerUtil.alarmInfo("[合作方通讯][普通][网络异常，"+url + "无法联通]");
            }
            return result;
        } catch (Exception e) {
            //logger.error("", e);
            //throw new RemoteInvocationFailureException("网络IO异常[" + url + "]", e);
            if (e instanceof RemoteInvocationFailureException
                || e instanceof java.net.ConnectException
                || e instanceof java.net.SocketTimeoutException
                || e instanceof IOException) {//处理一下超时
//					LoggerUtil.alarmInfo("[合作方通讯][普通][网络IO异常，"+url + "无法联通或连接超时]");

            } else {
//					LoggerUtil.error("未知异常",e);
            }
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }

                if (httpConn != null) {
                    httpConn.disconnect();
                }
            } catch (IOException e) {
                //logger.error("", e);
            }
        }
    }

    public static String sendPostRequest(String url, String content, String charset, boolean needCompress) {
        return sendPostRequest(url, content, charset, DEFAULT_CONNECT_TIME_OUT, DEFAULT_READ_TIME_OUT, needCompress);
    }

    private static InputStream postConnect(HttpURLConnection httpConn, String content, String charset, boolean needCompress) {
        String urlStr = httpConn.getURL().toString();
        try {
            if (StringUtils.isBlank(charset)) {
                charset = DEFAULT_CHARSET;
            }
            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true,
            // 默认情况下是false;
            httpConn.setDoOutput(true);
            // Post 请求不能使用缓存
            httpConn.setUseCaches(false);
            // 设定请求的方法为"POST"，默认是GET
            httpConn.setRequestMethod("POST");
            if (needCompress) {
                sendCompressRequest(content, charset, httpConn);
            } else {
                sendNoCompressRequest(content, charset, httpConn);
            }
            // 接收数据
            if (needCompress) {
                return new GZIPInputStream(httpConn.getInputStream());
            }
            return httpConn.getInputStream();
        } catch (MalformedURLException e) {
            //logger.error("", e);
            throw new RemoteInvocationFailureException("远程访问异常[" + urlStr + "]", e);
        } catch (IOException e) {
            //logger.error("", e);
            throw new RemoteInvocationFailureException("网络IO异常[" + urlStr + "]", e);
        }
    }

    private static void sendCompressRequest(String content, String charset, HttpURLConnection httpConn) {
        GZIPOutputStream out = null;
        try {
            httpConn.setRequestProperty("Content-Type", "application/x-gzip");
            httpConn.setRequestProperty("Accept", "application/x-gzip");
            out = new GZIPOutputStream(httpConn.getOutputStream());
            out.write(content.getBytes("GBK"));
            out.flush();
        } catch (IOException e) {
            //logger.error("", e);
            throw new RemoteInvocationFailureException("网络IO异常[" + httpConn.getURL().toString() + "]", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    //logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 发送原始消息
     */
    private static void sendNoCompressRequest(String content, String charset, HttpURLConnection httpConn) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new OutputStreamWriter(httpConn.getOutputStream(), charset));
            out.write(content);
            out.flush();
        } catch (IOException e) {
            //logger.error("", e);
            throw new RemoteInvocationFailureException("网络IO异常[" + httpConn.getURL().toString() + "]", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 建立远程连接
     */
    private static InputStream connect(HttpURLConnection httpConn) {
        String urlStr = httpConn.getURL().toString();
        try {
            if (httpConn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                //logger.error(urlStr + "|ResponseCode=" + httpConn.getResponseCode());
                throw new RemoteInvocationFailureException("远程访问" + urlStr + "出错，返回结果为：" + httpConn.getResponseCode());
            }
            return httpConn.getInputStream();
        } catch (IOException e) {
            //logger.error("", e);
            throw new RemoteInvocationFailureException("网络IO异常[" + urlStr + "]", e);
        }
    }

    /**
     * 构造URLConnnection
     */
    private static HttpURLConnection getURLConnection(String urlStr, int connectTimeout, int readTimeout)
        throws RemoteInvocationFailureException {
        //	logger.debug("请求URL:" + urlStr);
        try {
            URL remoteUrl = new URL(urlStr);
            HttpURLConnection httpConn = (HttpURLConnection) remoteUrl.openConnection();
            httpConn.setConnectTimeout(connectTimeout);
            httpConn.setReadTimeout(readTimeout);
            return httpConn;
        } catch (MalformedURLException e) {
            //logger.error("", e);
            throw new RemoteInvocationFailureException("远程访问异常[" + urlStr + "]", e);
        } catch (IOException e) {
            //logger.error("", e);
            throw new RemoteInvocationFailureException("网络IO异常[" + urlStr + "]", e);
        }
    }

    private static String getReturnResult(BufferedReader in, boolean returnSingleLine) throws IOException {
        if (returnSingleLine) {
            return in.readLine();
        } else {
            StringBuffer sb = new StringBuffer();
            String result = "";
            while ((result = in.readLine()) != null) {
                sb.append(StringUtils.trimToEmpty(result));
            }
            return sb.toString();
        }
    }


    public static String doPost(String reqUrl, Map<String, String> parameters, boolean isHttps) {
        HttpURLConnection urlConn = null;
        try {
            urlConn = sendPost(reqUrl, parameters, isHttps);
            return getContent(urlConn);
        } finally {
            try {
                if (urlConn != null) {
                    urlConn.disconnect();
                    urlConn = null;
                }
            } catch (Exception e) {

            }
        }
    }


    private static String getContent(HttpURLConnection urlConn) {
        try {
            String responseContent = null;
            InputStream in = urlConn.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String tempLine = rd.readLine();
            StringBuffer tempStr = new StringBuffer();
            String crlf = System.getProperty("line.separator");
            while (tempLine != null) {
                tempStr.append(tempLine);
                tempStr.append(crlf);
                tempLine = rd.readLine();
            }
            responseContent = tempStr.toString();
            rd.close();
            in.close();
            return responseContent;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static HttpURLConnection sendPost(String reqUrl, Map<String, String> parameters, boolean isHttps) {

        return sendHttp(reqUrl, "POST", parameters);
    }

    private static HttpURLConnection sendHttp(String reqUrl, String requestMethod, Map<String, String> parameters) {
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            send(urlConn, reqUrl, requestMethod, parameters);
            return urlConn;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }




    private static void send(HttpURLConnection urlConn, String reqUrl, String requestMethod,
        Map<String, String> parameters) throws Exception {
        urlConn.setRequestMethod(requestMethod);
        urlConn.setConnectTimeout(5000);
        urlConn.setReadTimeout(5000);
        urlConn.setDoOutput(true);
        urlConn.setUseCaches(false);
        urlConn.connect();

        String params = generatorParamString(parameters);
        if (params != null && !parameters.isEmpty()) {
            byte[] b = params.getBytes("UTF-8");
            OutputStream outputStream = urlConn.getOutputStream();
            outputStream.write(b, 0, b.length);
            outputStream.flush();
            outputStream.close();
        }
    }




    public static String generatorParamString(Map<String, String> parameters) {
        if (parameters == null) {
            return null;
        }

        StringBuilder params = new StringBuilder(512);
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue();
            params.append(name + "=");
            try {
                params.append(URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e.getMessage(), e);
            } catch (Exception e) {
                String message = String.format("'%s'='%s'", name, value);
                throw new RuntimeException(message, e);
            }
            params.append("&");
        }
        params.delete(params.length() - 1, params.length());
        return params.toString();
    }
}
