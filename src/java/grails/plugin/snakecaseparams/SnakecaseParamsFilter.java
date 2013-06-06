package grails.plugin.snakecaseparams;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequestWrapper;

public class SnakecaseParamsFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException { }    

    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
                throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;

        System.out.println("SnakecaseParamsFilter Starting...");

        FilteredRequest filteredRequest = new FilteredRequest(request);
        filteredRequest.convertParams(request.getParameterMap());

        System.out.println("...SnakecaseParamsFilter Ending");

        chain.doFilter(filteredRequest, response);
    }

    public void destroy() { }
}
