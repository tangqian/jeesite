package com.thinkgem.jeesite.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by tony on 2018/02/21.
 */
public class AccessFilter implements Filter {

    private final static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest request1 = (HttpServletRequest) request;
            java.util.Enumeration requestParamNames = request.getParameterNames();

            StringBuffer sb = new StringBuffer();
            while (requestParamNames.hasMoreElements()) {
                String key = (String) requestParamNames.nextElement();
                if (sb.length() == 0)
                    sb.append("?");
                else
                    sb.append("&");
                sb.append(key).append("=");// + request.getParameter(key);
                String[] arr = request.getParameterValues(key);
                StringBuffer value = new StringBuffer();
                for (int i = 0; i < arr.length; i++) {
                    if (i > 0)
                        value.append(",");
                    value.append(arr[i]);
                }
                sb.append(value);
            }
            logger.info(request1.getRequestURL() + sb.toString());

        } catch (Exception e) {
            logger.error("accessFilter Exception:", e);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
