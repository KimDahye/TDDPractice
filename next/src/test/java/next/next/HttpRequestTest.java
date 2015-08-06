package next.next;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HttpRequestTest {
	String httpheader = "GET /create?"
			+ "userId=javajigi&password=password&"
			+ "name=%EB%B0%95%EC%9E%AC%EC%84%B1&"
			+ "email=javajigi%40slipp.net HTTP/1.1\n"
			+ "Host: localhost:8080\n"
			+ "Connection: keep-alive\n Accept: */*\n";
	
	HttpRequest request =  new HttpRequest(httpheader);;

	@Test
	public void testParameter() {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		assertEquals(userId, "javajigi");
		assertEquals(password, "password");
	}
	@Test
	public void testHeader() throws Exception {
		String host = request.getHeader("host");
		assertEquals(host, "localhost:8080");
	}
	@Test
	public void testURI() throws Exception {
		String requestURI = request.getRequestURI();
		assertEquals(requestURI, "/create?"
				+ "userId=javajigi&password=password&"
				+ "name=%EB%B0%95%EC%9E%AC%EC%84%B1&"
				+ "email=javajigi%40slipp.net");
	}

}
