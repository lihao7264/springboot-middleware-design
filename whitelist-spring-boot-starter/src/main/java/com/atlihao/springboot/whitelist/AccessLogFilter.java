package com.atlihao.springboot.whitelist;

import com.alibaba.fastjson.JSONObject;
import com.atlihao.springboot.whitelist.wrapper.BodyRequestWrapper;
import com.google.common.collect.Lists;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @Description:
 * @Author: lihao726726
 * @CreateDate: 2023/7/18 8:21 下午
 * @UpdateUser: lihao726726
 * @UpdateDate: 2023/7/18 8:21 下午
 * @Version: 1.0.0
 */
@WebFilter(filterName = "accessLogFilter", urlPatterns = "/*")
public class AccessLogFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(AccessLogFilter.class);

    private Set<String> excludeUris = new HashSet<>();
    private static final PathMatcher URI_PATH_MATCHER = new AntPathMatcher();

    private static final List<String> DEFAULT_DOWNLOAD_CONTENT_TYPE = new ArrayList<>();


    private void doSaveAccessLog1(final BodyRequestWrapper request,
                                  final long useTime,
                                  final Throwable t) {
        try {
            final String requestUri = request.getRequestURI();
            final String requestHeaders = getRequestHeaders(request);
            final String requestIp = getRequestIp(request);
            final String requestParams = getRequestParams(request);
            final String requestString = isUpload(request) ? StringUtils.EMPTY : request.getBody();
            JSONObject jsonObject = JSONObject.parseObject(requestString);
            final List<String> logs = new ArrayList();
            logs.add("time=" + useTime + "ms");
            logs.add("ip=" + requestIp);
            logs.add("uri=" + requestUri);
            logs.add("headers=" + requestHeaders);
            logs.add("requestContentType=" + request.getContentType());
            logs.add("params=" + requestParams);
            logs.add("request=" + requestString);
            logger.info(String.join(",", logs));
        } catch (Throwable e) {
            logger.error("got an exception when saving access log", e);
        } finally {
        }
    }

    private void doSaveAccessLog(final HttpServletRequest request,
                                 final long useTime,
                                 final Throwable t) {
        try {
            final String requestUri = request.getRequestURI();
            final String requestHeaders = getRequestHeaders(request);
            final String requestIp = getRequestIp(request);
            final String requestParams = getRequestParams(request);
            final List<String> logs = new ArrayList();
            logs.add("time=" + useTime + "ms");
            logs.add("ip=" + requestIp);
            logs.add("uri=" + requestUri);
            logs.add("headers=" + requestHeaders);
            logs.add("requestContentType=" + request.getContentType());
            logs.add("params=" + requestParams);
            logger.info(String.join(",", logs));
        } catch (Throwable e) {
            logger.error("got an exception when saving access log", e);
        } finally {
        }
    }


    private String getRequestIp(final HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");// 这是一个可以伪造的头
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        }
        // 最后一个为RemoteAddr
        int pos = ip.lastIndexOf(',');
        if (pos >= 0) {
            ip = ip.substring(pos);
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    private String getRequestHeaders(final HttpServletRequest request) {
        final Enumeration<String> headerNames = request.getHeaderNames();
        final List<String> headers = new ArrayList<>();
        while (headerNames.hasMoreElements()) {
            final String key = headerNames.nextElement();
            headers.add(key + ':' + request.getHeader(key));
        }
        return '[' + String.join(",", headers) + ']';
    }

    private String getRequestParams(final HttpServletRequest request) {
        final Map<String, String[]> requestParams = new HashMap<>(request.getParameterMap());
        final List<String> pairs = new ArrayList();
        if (MapUtils.isNotEmpty(requestParams)) {
            for (final Map.Entry<String, String[]> entry : requestParams.entrySet()) {
                final String name = entry.getKey();
                final String[] value = entry.getValue();
                if (value == null) {
                    pairs.add(name + "=");
                } else {
                    for (final String v : value) {
                        pairs.add(name + "=" + StringUtils.trimToEmpty(v));
                    }
                }
            }
        }
        String requestParamsStr = CollectionUtils.isEmpty(pairs) ? StringUtils.EMPTY : String.join("&", pairs);
        if (StringUtils.equalsIgnoreCase(request.getContentType(), MediaType.APPLICATION_FORM_URLENCODED_VALUE)) {
            try {
                requestParamsStr = URLDecoder.decode(requestParamsStr, StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException ignored) {
            }
        }
        return requestParamsStr;
    }

    private boolean isUpload(final HttpServletRequest request) {
        final String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
        if (StringUtils.isBlank(contentType)) {
            return false;
        }
        return StringUtils.containsIgnoreCase(contentType, MediaType.MULTIPART_FORM_DATA_VALUE);
    }

    private boolean isDownload(final HttpServletResponse response) {
        final String contentType = response.getContentType();
        if (StringUtils.isBlank(contentType)) {
            return false;
        }
        return DEFAULT_DOWNLOAD_CONTENT_TYPE.stream().anyMatch(it -> StringUtils.equalsIgnoreCase(it, contentType));
    }

    private String getRequestString(final HttpServletRequest request) {
        final ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            try {
                final byte[] buf = wrapper.getContentAsByteArray();
                return new String(buf, wrapper.getCharacterEncoding()).replaceAll("\n|\r", "");
            } catch (UnsupportedEncodingException e) {
                return "[UNKNOWN]";
            }
        }
        return StringUtils.EMPTY;
    }

    private boolean matchExclude(final String uri) {
        if (CollectionUtils.isEmpty(excludeUris)) {
            return false;
        }
        for (final String excludeUri : excludeUris) {
            if (URI_PATH_MATCHER.match(excludeUri, uri)) {
                return true;
            }
        }
        return false;
    }

    public void setExcludeUris(final Set<String> excludeUris) {
        this.excludeUris = excludeUris == null ? new HashSet<>() : excludeUris;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 如果是被排除的uri，不记录access_log
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (matchExclude(httpServletRequest.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }
        final String requestMethod = httpServletRequest.getMethod();
        final boolean shouldWrapMethod = StringUtils.equalsIgnoreCase(requestMethod, HttpMethod.PUT.name())
                || StringUtils.equalsIgnoreCase(requestMethod, HttpMethod.POST.name());
        final boolean shouldWrapRequest = !(request instanceof BodyRequestWrapper) && shouldWrapMethod;
        final HttpServletRequest requestToUse = shouldWrapRequest && !isUpload(httpServletRequest) ? new BodyRequestWrapper(httpServletRequest) : httpServletRequest;
        final long startTime = System.currentTimeMillis();
        Throwable t = null;
        if (requestToUse instanceof BodyRequestWrapper) {
            doSaveAccessLog1((BodyRequestWrapper) requestToUse, System.currentTimeMillis() - startTime, t);
        } else {
            doSaveAccessLog(requestToUse, System.currentTimeMillis() - startTime, t);
        }
        try {
            chain.doFilter(requestToUse, response);
        } catch (Exception e) {
            t = e;
            throw e;
        }
    }

    public static void main(String[] args) {
        String str = "{\n" +
                "\n" +
                "\t\"projectId\": 1111\n" +
                "\n" +
                "}";
        System.out.println(deepQueryAppIdInJSONObject(JSONObject.parseObject(str)));
    }

    public static String deepQueryAppIdInJSONObject(Object bodyObject) {
        if (Objects.isNull(bodyObject)) {
            return null;
        }
        if (!(bodyObject instanceof JSONObject)) {
            return null;
        }
        JSONObject jsonObject = (JSONObject) bodyObject;
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            if (Lists.newArrayList("appId", "businessId").contains(key)) {
                return String.valueOf(entry.getValue());
            }
            String result = deepQueryAppIdInJSONObject(entry.getValue());
            if (StringUtils.isNotBlank(result)) {
                return result;
            }
        }
        return null;
    }
}
