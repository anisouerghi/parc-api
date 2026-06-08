//package com.transtu.pacbus.security;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class ApiKeyFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
//        // ✅ HEADERS CORS (OBLIGATOIRE)
//        res.setHeader("Access-Control-Allow-Origin", "*");
//        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        res.setHeader("Access-Control-Allow-Headers", "*");
//
//        String path = req.getRequestURI();
//
//        // ✅ Autoriser Swagger
//        if (path.startsWith("/swagger-ui") ||
//            path.startsWith("/v3/api-docs") ||
//            path.startsWith("/swagger-resources") ||
//            path.startsWith("/webjars") ||
//            path.startsWith("/favicon.ico")) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        // ✅ IMPORTANT : Autoriser PRE-FLIGHT (CORS)
//        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
//            res.setStatus(HttpServletResponse.SC_OK);
//            return;
//        }
//
//        // 🔑 Vérification API KEY
//        String key = req.getHeader("X-API-KEY");
//
//        if ("ma-cle-secrete-12345".equals(key)) {
//            chain.doFilter(request, response);
//        } else {
//            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            res.getWriter().write("Clé API invalide !");
//        }
//    }
//}










package com.transtu.pacbus.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApiKeyFilter implements Filter {

    private static final String API_KEY = "ma-cle-secrete-12345";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();

        // =========================
        // CORS
        // =========================
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers", "*");

        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            res.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        // =========================
        // 🔥 SUPER SAFE SWAGGER EXCLUSION
        // =========================
        if (path.contains("swagger-ui") ||
            path.contains("v3/api-docs") ||
            path.contains("swagger-resources") ||
            path.contains("webjars") ||
            path.contains("favicon.ico")) {

            chain.doFilter(request, response);
            return;
        }

        // =========================
        // API KEY CHECK
        // =========================
        String key = req.getHeader("X-API-KEY");

        if (API_KEY.equals(key)) {
            chain.doFilter(request, response);
        } else {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Clé API invalide !");
        }
    }
}








//package com.transtu.pacbus.security;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import java.io.IOException;
//
//@Component
//public class ApiKeyFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
//        String path = req.getRequestURI();
//
//        // ✅ Autoriser Swagger et tous ses sous-chemins
//        if (path.startsWith("/swagger-ui") ||
//            path.startsWith("/v3/api-docs") ||  // inclut swagger-config
//            path.startsWith("/swagger-resources") ||
//            path.startsWith("/webjars") ||
//            path.startsWith("/favicon.ico")) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        // 🔑 Vérifier la clé API pour tous les autres endpoints
//        String key = req.getHeader("X-API-KEY");
//        if ("ma-cle-secrete-12345".equals(key)) {
//            chain.doFilter(request, response);
//        } else {
//            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            res.getWriter().write("Clé API invalide !");
//        }
//    }
//}