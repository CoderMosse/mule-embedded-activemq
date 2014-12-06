package org.eclipse.jetty.server.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.io.EndPoint;
import org.eclipse.jetty.server.AbstractHttpConnection;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.IPAccessHandler;

public class RealIPAccessHandler extends IPAccessHandler {

	public RealIPAccessHandler() {
		super();
	}

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AbstractHttpConnection connection = baseRequest.getConnection();
        if (connection != null) {
            EndPoint endp = connection.getEndPoint();
            if (endp != null) {
            	// Get the remote IP if behind a load balancer
            	String header = baseRequest.getHeader("X-Real-IP");
            	// If not behind a load balancer, get the real remote IP (not the one set by the forwarded headers (which may be forged))
            	String addr = (header == null || header.equals("")) ? endp.getRemoteAddr() : header;
                if (addr != null && !isAddrUriAllowed(addr,baseRequest.getPathInfo())) {
                    response.sendError(HttpStatus.FORBIDDEN_403);
                    baseRequest.setHandled(true);
                    return;
                }
            }
        }
        
        getHandler().handle(target,baseRequest, request, response);
	}
	
}
