package iot.cloud.backend.webapi.config;

import com.alibaba.fastjson.JSON;
import iot.cloud.backend.common.utils.StringUtils;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author weichuang
 */
@Slf4j
public class RequestCustomWrapper extends HttpServletRequestWrapper {

    private byte[] body;

    public byte[] getBody() {
        return body;
    }

    public RequestCustomWrapper(HttpServletRequest request) {
        super(request);
        try {
            body = readBytes(request.getReader());
        } catch (IOException e) {
            log.error("读取request input stream失败..");
        }
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        try (final ByteArrayInputStream bais = new ByteArrayInputStream(body)) {
            return new ServletInputStream() {
                @Override
                public boolean isFinished() {
                    return false;
                }

                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setReadListener(ReadListener readListener) {

                }

                @Override
                public int read() throws IOException {
                    return bais.read();
                }
            };
        }

    }

    public byte[] readBytes(BufferedReader br) throws IOException {
        byte[] emptyBytes = new byte[0];
        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }

        if (StringUtils.isNotBlank(sb.toString())) {
            return sb.toString().getBytes(StandardCharsets.UTF_8);
        }

        return emptyBytes;
    }

    public String getToken() {
        Map<String, Object> jsonMap = (Map<String, Object>) JSON.parse(new String(body));
        if (jsonMap == null || jsonMap.isEmpty()) {
            return null;
        }
        return jsonMap.get("token").toString();
    }
}

