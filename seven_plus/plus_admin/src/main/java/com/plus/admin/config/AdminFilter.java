package com.plus.admin.config;

import com.common.consts.Global;
import com.common.result.JsonResult;
import com.common.result.ResultCode;
import com.common.util.ObjectHelper;
import com.domain.admin.entity.AdminModule;
import com.domain.admin.entity.AdminUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * 后台管理系统session监听
 * @author JIAO_LIU_BABA
 */
@WebFilter(urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {
	private static final Logger log = LoggerFactory.getLogger(AdminFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURI();
        log.info(" doFilter uri : " + url);

        boolean boo = boo(url); //包含的数据直接过滤
		if(boo){
			chain.doFilter(request, response);
			return;
		}

        AdminUser adminUser = (AdminUser)req.getSession().getAttribute("adminUser");
    	if(ObjectHelper.isEmpty(adminUser)) {
    		if(ObjectHelper.x_requested_with(req)){
    			//异步请求
    			res.getWriter().write(new JsonResult(ResultCode.SESSION_OUT).toString());
    		}else{
    			//同步请求
    			res.getWriter().write("<html><script>window.open('/back','_top')</script></html>");
    		}
    		return;
        }
    	//验证权限 - 角色为1的是超级管理员  - 直接滤过权限
        if(adminUser.getRoleId().equals(Global.IS_SUPER_ADMIN)){
        	chain.doFilter(request, response);
         	return;
        }
    	List<AdminModule> listModule = (List<AdminModule>)req.getSession().getAttribute("listModule");
		if(ObjectHelper.isEmpty(listModule)){
			res.setContentType("application/json; charset=utf-8");
			if(ObjectHelper.x_requested_with(req)){
    			//异步请求
    			res.getWriter().write(new JsonResult(ResultCode.SESSION_OUT).toString());
    		}else{
    			//同步请求
    			res.getWriter().write("<html><script>window.open('/back','_top')</script></html>");
    		}
			return;
		}else{
			boolean b = booSession(url); //强制过滤信息
			if(b){
				chain.doFilter(request, response);
				return;
			}
			Iterator<AdminModule> it = listModule.iterator();
			while (it.hasNext()) {
				AdminModule adminModule = it.next();
				if(url.equals(("/"+adminModule.getUrl()))){
					b = true;
					break;
				}
			}
			if(b){
				chain.doFilter(request, response);
				return;
			}
			if(ObjectHelper.x_requested_with(req)){
				String all = "No operation authority";
    			//异步请求 - 无操作权限
	            response.getWriter().write(new JsonResult(ResultCode.ERROR.code,all).toString());
    		}else{
    			//同步请求
    			res.sendRedirect("/admin/login/powerExpire.html");//无权限页面
    		}
		}
	}

	@Override
	public void destroy() {

	}

	//过滤的请求地址 - session值的数据
	private String[] filter = {"/admin/user/index","/admin/user/left","/admin/user/main"};
	//过滤的带后缀的地址
	private String[] filter_to = {".html",".js",".png",".jsp",".htm",".jpg",".gif",".jpeg",".text"};
	//请求过滤信息
	public boolean booSession(String url){
		for(int i = 0;i<filter.length;i++){
			if(url.equals(filter[i])){
				return true;
			}
		}
		return false;
	}
	//请求过滤信息
	public boolean boo(String url){
		for(int i = 0;i < filter_to.length;i++){
			if((url.toLowerCase()).contains(filter_to[i])){
				return true;
			}
		}
		return false;
	}
}
