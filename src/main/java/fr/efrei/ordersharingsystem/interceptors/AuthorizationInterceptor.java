package fr.efrei.ordersharingsystem.interceptors;

import fr.efrei.ordersharingsystem.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

@NoArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        if (request.getMethod().equals("GET")) {
            return true;
        }

        String agentId = request.getHeader("user-id");
        if (agentId == null) {
            System.out.println("No user-id header");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        var user = userRepository.findById(Long.parseLong(agentId)).orElse(null);
        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        var parkId = Long.parseLong((String) pathVariables.get("parkId"));
        var userNotFound = user == null;
        if (userNotFound) {
            System.out.println("User not found");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        var userNotAuthorized = !user.getAssignedBowlingPark().getId().equals(parkId);
        if (userNotAuthorized) {
            System.out.println("User not authorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }
}
