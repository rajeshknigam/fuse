package com.mycompany.camel.blueprint;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.Service;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class Test1 extends CamelTestSupport {

	@EndpointInject(uri = "mock:result")
	protected MockEndpoint resultEndpoint;

	@Produce(uri = "direct:start")
	protected ProducerTemplate template;

	@EndpointInject(uri = "jetty:http://0.0.0.0:7080/jettysample?name=test")
	protected MockEndpoint flowEndpoint;

	@Test
	public void testSendMatchingMessage() throws Exception {
		String expectedBody = "<matched/>";

		resultEndpoint.expectedBodiesReceived(expectedBody);

		template.sendBodyAndHeader(expectedBody, "foo", "bar");

		resultEndpoint.assertIsSatisfied();
	}

	@Override
	protected RouteBuilder createRouteBuilder() {
		return new RouteBuilder() {
			public void configure() {
				from("direct:start").to(flowEndpoint).to("mock:result");
			}
		};
	}
	
	@Override
	public Service getCamelContextService() {
		// TODO Auto-generated method stub
		return super.getCamelContextService();
	}
}