package org.alexv.vet_manager_api.commons.log;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Enumeration;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class LoggingFilter extends OncePerRequestFilter {

    Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            logRequestDetails(wrappedRequest);
            logResponseDetails(wrappedResponse);
            wrappedResponse.copyBodyToResponse();
        }
    }

    private void logRequestDetails(ContentCachingRequestWrapper request) throws UnsupportedEncodingException {

        String requestBody = new String(request.getContentAsByteArray(), request.getCharacterEncoding());
        logger.info("Incoming request: method={}, url={}", request.getMethod(), request.getRequestURL());
        logger.info("Request body: {}", requestBody);

        logger.info("Request headers:");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            logger.info("Header: {} = {}", headerName, request.getHeader(headerName));
        }
    }

    private void logResponseDetails(ContentCachingResponseWrapper response) throws IOException {
        String responseBody = new String(response.getContentAsByteArray(), response.getCharacterEncoding());
        logger.info("Outgoing response status: {}", response.getStatus());
        logger.info("Response body: {}", responseBody);

        logger.info("Response headers:");
        Collection<String> headerNames = response.getHeaderNames();
        for (String headerName : headerNames) {
            logger.info("Header: {} = {}", headerName, response.getHeader(headerName));
        }
    }

}
