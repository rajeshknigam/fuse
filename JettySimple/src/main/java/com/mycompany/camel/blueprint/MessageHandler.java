package com.mycompany.camel.blueprint;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MessageHandler implements Processor {

	public void process(Exchange exchange) throws Exception {
		System.out.println("--------------------------------------------------------------");
		String message = new Date()+") Hello ";
		
		HttpServletRequest request = exchange.getIn().getBody(HttpServletRequest.class);
		String name = request.getParameter("name");
		
		if (name.equalsIgnoreCase("Rajesh")) {
			message = message+ name+", How is JBoss COE going.";
		}else if (name.equalsIgnoreCase("Bipin")) {
			message = message+name+", How is APIGE training going.";
		}else if (name.equalsIgnoreCase("Sandeep")) {
			message = message+name+", How are you doing.. Demo for DevOps is on.";
		}else{
			message = message+name+", I am sorry, not sure what to say.";
		}
		
		exchange.getOut().setBody(message); 
	}
}
