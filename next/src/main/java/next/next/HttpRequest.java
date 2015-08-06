package next.next;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

	private String httpheader;
	private Map<String, String> parameterMap = new HashMap<String, String>();
	private Map<String, String> headerMap = new HashMap<String, String>();
	private String uri;
	
	public HttpRequest(String httpheader) {
		this.httpheader = httpheader;
		parse();
	}

	private void parse() {
		String [] result = this.httpheader.split("\n");
		parseParameter(result[0]);
		parseUri(result[0]);
		for(int i=1; i < result.length; i++ ) {
			parseHeader(result[i]);
		}
	}

	private void parseHeader(String string) {
		String[] res = string.split(":\\s");
		this.headerMap.put(res[0].toLowerCase(), res[1]);
	}

	private void parseUri(String string) {
		String [] result = string.split("\\s");
		this.uri = result[1];
	}

	private void parseParameter(String firstLine) {
		String temp =  firstLine.split("\\s")[1];
		String res = temp.split("\\?")[1];
		String[] params = res.split("&");
		
		for (String param : params) {
			String[] keyvalue = param.split("=");
			this.parameterMap.put(keyvalue[0], keyvalue[1]);
		}
	}

	public String getHeader(String key) {
		return headerMap.get(key.toLowerCase());
	}

	public String getParameter(String key) {
		return parameterMap.get(key);
	}

	public String getRequestURI() {
		return this.uri;
	}

}
