package com.fit.core.base.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.fit.core.base.tips.SuccessTip;
import com.fit.core.base.warpper.BaseControllerWarpper;
import com.fit.core.page.PageInfoBT;
import com.fit.core.support.HttpKit;
import com.fit.core.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BaseController {

    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected static String SUCCESS = "SUCCESS";
    protected static String ERROR = "ERROR";

    protected static String REDIRECT = "redirect:";
    protected static String FORWARD = "forward:";

    protected static SuccessTip SUCCESS_TIP = new SuccessTip();

    protected HttpServletRequest getHttpServletRequest() {
        return HttpKit.getRequest();
    }

    protected HttpServletResponse getHttpServletResponse() {
        return HttpKit.getResponse();
    }

    protected HttpSession getSession() {
        return HttpKit.getRequest().getSession();
    }

    protected HttpSession getSession(Boolean flag) {
        return HttpKit.getRequest().getSession(flag);
    }

    protected String getPara(String name) {
        return HttpKit.getRequest().getParameter(name);
    }

    protected void setAttr(String name, Object value) {
        HttpKit.getRequest().setAttribute(name, value);
    }

    protected Integer getSystemInvokCount() {
        return (Integer) this.getHttpServletRequest().getServletContext().getAttribute("systemCount");
    }

    /**
     * 把service层的分页信息，封装为bootstrap table通用的分页封装
     */
    protected <T> PageInfoBT<T> packForBT(Page<T> page) {
        return new PageInfoBT<T>(page);
    }

    /**
     * 包装一个list，让list增加额外属性
     */
    protected Object warpObject(BaseControllerWarpper warpper) {
        return warpper.warp();
    }

    /**
     * 删除cookie
     */
    protected void deleteCookieByName(String cookieName) {
        Cookie[] cookies = this.getHttpServletRequest().getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                Cookie temp = new Cookie(cookie.getName(), "");
                temp.setMaxAge(0);
                this.getHttpServletResponse().addCookie(temp);
            }
        }
    }

    /**
     * 返回前台文件流
     *
     * @author Aim
     * @date 2017年2月28日 下午2:53:19
     */
    protected ResponseEntity<byte[]> renderFile(String fileName, String filePath) {
        byte[] bytes = FileUtil.toByteArray(filePath);
        return renderFile(fileName, bytes);
    }

    /**
     * 返回前台文件流
     *
     * @author Aim
     * @date 2017年2月28日 下午2:53:19
     */
    protected ResponseEntity<byte[]> renderFile(String fileName, byte[] fileBytes) {
        String dfileName = null;
        try {
            dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<byte[]>(fileBytes, headers, HttpStatus.CREATED);
    }

    /**
     * 获取请求的所有参数
     */
    protected Map<String, Object> getRequestParamsMap(HttpServletRequest request) {
        // 返回值Map
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
            // 参数Map
            Map<String, String[]> properties = request.getParameterMap();
            String value = "";
            for (Iterator<?> iter = properties.entrySet().iterator(); iter.hasNext(); ) {
                Map.Entry<?, ?> element = (Map.Entry<?, ?>) iter.next();
                Object strKey = element.getKey();
                Object strObj = element.getValue();
                if (null == strObj) {
                    value = "";
                } else if (strObj instanceof String[]) {
                    String[] values = (String[]) strObj;
                    for (int i = 0; i < values.length; i++) { // 用于请求参数中有多个相同名称
                        value = values[i] + ",";
                    }
                    value = value.substring(0, value.length() - 1);
                } else {
                    value = strObj.toString();// 用于请求参数中请求参数名唯一
                }
                if (StringUtils.hasLength(value)) {
                    returnMap.put(strKey.toString(), value);
                }
            }
        } catch (Exception e) {
            logger.error("getRequestParamsMap错误信息：{}", e);
        }
        return returnMap;
    }
}
