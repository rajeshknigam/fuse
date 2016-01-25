package com.mycompany.camel.blueprint;

import javax.servlet.http.HttpServletRequest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MessageHandler implements Processor {

	public void process(Exchange exchange) throws Exception {
		HttpServletRequest request = exchange.getIn().getBody(HttpServletRequest.class);
		String message = request.getParameter("name");
		System.out.println(message);
		exchange.getOut().setBody("Hello " + message);
	}
}
