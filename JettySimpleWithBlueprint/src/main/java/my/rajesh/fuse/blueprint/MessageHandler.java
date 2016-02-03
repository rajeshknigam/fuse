package my.rajesh.fuse.blueprint;

import javax.servlet.http.HttpServletRequest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MessageHandler implements Processor {

	public void process(Exchange exchange) throws Exception {
		System.out.println("--------------------------------------------------------------");
		String message = "Hello ";
		
		HttpServletRequest request = exchange.getIn().getBody(HttpServletRequest.class);
		
		String body = exchange.getIn().getBody(String.class);
		String name = null != request.getParameter("name")?request.getParameter("name"):"Failed";
		
		String token = name != "Failed"? name: null != body && body.length() > 0 ? body:"Unknown";
				
		System.out.println("name="+name);
		
		if (token.equalsIgnoreCase("Rajesh")) {
			message = message+ token+", How is JBoss COE going.";
		}else if (token.equalsIgnoreCase("Bipin")) {
			message = message+token+", How is APIGE training going.";
		}else if (token.equalsIgnoreCase("Sandeep")) {
			message = message+token+", How are you doing.. Demo for DevOps is on.";
		}else{
			message = message+token+", I am sorry, not sure what to say.";
		}
		
		exchange.getOut().setBody(message); 
	}
}
