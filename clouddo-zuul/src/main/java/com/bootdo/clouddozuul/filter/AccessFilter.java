package com.bootdo.clouddozuul.filter;

import com.bootdo.clouddocommon.constants.CommonConstants;
import com.bootdo.clouddocommon.context.FilterContextHandler;
import com.bootdo.clouddocommon.dto.MenuDTO;
import com.bootdo.clouddocommon.dto.UserToken;
import com.bootdo.clouddocommon.utils.JSONUtils;
import com.bootdo.clouddocommon.utils.JwtUtils;
import com.bootdo.clouddocommon.utils.R;
import com.bootdo.clouddocommon.utils.StringUtils;
import com.bootdo.clouddozuul.prc.admin.MenuService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

/**
 * @version V1.0
 * @Author bootdo 1992lcg@163.com
 */
public class AccessFilter extends ZuulFilter {
    @Autowired
    MenuService menuService;


    private String ignorePath = "/api-admin/login,/api-admin/login222,/api-admin/test1,/api-cms/blog/blogall,/api-cms/blog/blogid,/api-cms/blogComments/listbyblogid,/api-cms/blogComments/Commentsblogid,/api-pay/pay/LoadPayQRCode" +
            ",/api-app/app,/api-exam/exam/,/api-admin/common/generator/code/,/api-activiti/test/list,/api-shop/user/checkLogin,/api-shop/goods/listgoods,/api-shop/user/loginshop,/api-shop/orderlist/ordershoplist,/api-shop/cartlist/addcart,/api-shop/cartlist/cartshoplist" +
            ",/api-shop/addresslist/addressshopList,/api-shop/orderlist/payOrder,/api-shop/goods/getDetails,/api-shop/user/loginoutshop,/api-shop/cartlist/delcart,/api-shop/addresslist/saveshopaddress,/api-shop/addresslist/deleteAdr" +
            ",/api-shop/orderlist/updateOrder,/api-shop/orderlist/delOrder,/api-pay/pay/LoadPayQRCode,/api-shop/cartlist/editSelectAll";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10000;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }


    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        final String requestUri = request.getRequestURI();
        if (isStartWith(requestUri)) {
            return null;
        }
        String accessToken = request.getHeader(CommonConstants.CONTEXT_TOKEN);
        if(null == accessToken || accessToken == ""){
            accessToken = request.getParameter(CommonConstants.TOKEN);
        }
        if (null == accessToken) {
            setFailedRequest(R.error401(), 200);
            return null;
        }
        try {
            UserToken userToken = JwtUtils.getInfoFromToken(accessToken);
        } catch (Exception e) {
            setFailedRequest(R.error401(), 200);
            return null;
        }
        FilterContextHandler.setToken(accessToken);
        if(!havePermission(request)){
            setFailedRequest(R.error403(), 200);
            return null;
        }
        Set<String> headers = (Set<String>) ctx.get("ignoredHeaders");
        //We need our JWT tokens relayed to resource servers
        //添加自己header
//        ctx.addZuulRequestHeader(CommonConstants.CONTEXT_TOKEN, accessToken);
        //移除忽略token
        headers.remove("authorization");
        return null;
//        RequestContext ctx = RequestContext.getCurrentContext();
//        Set<String> headers = (Set<String>) ctx.get("ignoredHeaders");
//        // We need our JWT tokens relayed to resource servers
//        headers.remove("authorization");
//        return null;
    }

    private void setFailedRequest(Object body, int code) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        HttpServletResponse response = ctx.getResponse();
        PrintWriter out = null;
        try{
            out = response.getWriter();
            out.write(JSONUtils.beanToJson(body));
            out.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
        ctx.setSendZuulResponse(false);
    }

    private boolean havePermission(HttpServletRequest request){
        String currentURL = request.getRequestURI();
        List<MenuDTO> menuDTOS = menuService.userMenus();
        for(MenuDTO menuDTO:menuDTOS){
            if(currentURL!=null&&null!=menuDTO.getUrl()&&currentURL.startsWith(menuDTO.getUrl())){
                return true;
            }
        }
        return false;
    }

    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        for (String s : ignorePath.split(",")) {

            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return flag;
    }
}
