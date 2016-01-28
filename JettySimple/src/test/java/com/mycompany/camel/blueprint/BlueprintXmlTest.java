package com.mycompany.camel.blueprint;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import org.apache.camel.ExchangePattern;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

public class BlueprintXmlTest extends CamelBlueprintTestSupport {

	// TODO Create test message bodies that work for the route(s) being tested
	// Expected message bodies
	protected Object[] expectedBodies = { "How is JBoss COE going",
			"<something id='2'>expectedBody2</something>" };
	// Templates to send to input endpoints

	protected String[] inputs = { "Rajesh", "Bipin" };

	@Produce(uri = "jetty:http://127.0.0.1:7080/jettysample?name=")
	protected ProducerTemplate inputEndpoint;

	@Test
	public void testCamelRoute() throws Exception {

		// Define some expectations

		// For now, let's just wait for some messages// TODO Add some
		// expectations here
		// Send some messages to input endpoints
		


		for (String input : inputs) {

			String url = "jetty:http://127.0.0.1:7080/jettysample?name=" + input;
			System.out.println("Setting endpoints...");

			inputEndpoint.setDefaultEndpointUri(url);
			//inputEndpoint.sendBody(input);
			Object message = inputEndpoint.sendBody(inputEndpoint.getDefaultEndpoint(), ExchangePattern.InOut, input);
			
			System.out.println("---------NULL-----"+message.getClass().getCanonicalName());
			
			ByteArrayOutputStream b = new ByteArrayOutputStream();
	        ObjectOutputStream o = new ObjectOutputStream(b);
	        o.writeObject(message);
	        byte[] array =  b.toByteArray();
	        
	        System.out.println(new String(array));
			
			
			
		}

		// Validate our expectations
		assertMockEndpointsSatisfied();

	}


	@Override
	protected String getBlueprintDescriptor() {
		return "OSGI-INF/blueprint/blueprint.xml";
	}

}
