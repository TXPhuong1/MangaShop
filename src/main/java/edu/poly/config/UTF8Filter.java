package edu.poly.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@WebFilter(urlPatterns = "/", dispatcherTypes = DispatcherType.REQUEST)
public class UTF8Filter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		request.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());

		System.err.println("Filter run....");

		chain.doFilter(request, response);

	}

}
