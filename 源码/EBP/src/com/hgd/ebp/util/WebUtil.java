package com.hgd.ebp.util;

import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hgd.ebp.service.TicketService;

public class WebUtil {
	public static final int MAX_PAGE_LINES = 5;
	
	public static String getMapData(Map<String, String> map, String key) {
    	if (map == null) return "";
    	
        String msg = map.get(key);
        return (msg == null) ? "" : msg;
    }
	public static TicketService getTicketServiceBean(ServletContext sc) {
    	ApplicationContext context = 
    			WebApplicationContextUtils.getWebApplicationContext(sc);
        return (TicketService)context.getBean("ticketService");
    }
}
