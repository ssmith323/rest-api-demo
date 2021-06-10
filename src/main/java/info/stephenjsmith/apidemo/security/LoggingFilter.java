package info.stephenjsmith.apidemo.security;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("***** Starting method");
        chain.doFilter(request,response);
        log.info("***** End method");
    }
}
