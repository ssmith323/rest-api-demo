package info.stephenjsmith.apidemo.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
public class ApiKeyFilter implements Filter {

    private final ApiKeyRepository apiKeyRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        String key = req.getHeader("x-api-key");

        if(key == null) {
            log.info("!!!!! No API Key used");
            HttpServletResponse resp = (HttpServletResponse) response;

            resp.reset();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            log.info("***** API Key passed is %s", key);

            Optional<Boolean> apiKeyOptional = this.apiKeyRepository.findOneByKey(key);
            if(apiKeyOptional.isPresent()){
                chain.doFilter(request, response);
            }else{
                HttpServletResponse resp = (HttpServletResponse) response;

                resp.reset();
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }
}
