package wooteco.subway.auth.infrastructure;

import org.springframework.web.servlet.HandlerInterceptor;
import wooteco.subway.auth.application.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    private final AuthService authService;

    public LoginInterceptor(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = AuthorizationExtractor.extract(request);
        authService.validateToken(token);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}