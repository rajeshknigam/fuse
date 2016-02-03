package my.rajesh.fuse.blueprint.test;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

public class TestCase1 extends CamelBlueprintTestSupport {

	@Override
	protected String getBlueprintDescriptor() {
		return "OSGI-INF/blueprint/blueprint.xml";
	}
	
	@EndpointInject(uri = "mock:result")
	protected MockEndpoint resultEndpoint;

	@Produce(uri = "direct:start")
	protected ProducerTemplate template;

	@Test
    public void Test1_Rajesh() throws Exception {
        String expectedBody = "Hello Rajesh, How is JBoss COE going.";
        resultEndpoint.expectedBodiesReceived(expectedBody);
        template.sendBody("Rajesh");
        resultEndpoint.assertIsSatisfied();
    }
 
    @Test
    public void Test2_Bipin() throws Exception {
        String expectedBody = "Hello Bipin, How is APIGE training going.";
        resultEndpoint.expectedBodiesReceived(expectedBody);
        template.sendBody("Bipin");
        resultEndpoint.assertIsSatisfied();
    }
    
    @Test
    public void Test3_Sandeep() throws Exception {
        String expectedBody = "Hello Sandeep, How are you doing.. Demo for DevOps is on.";
        resultEndpoint.expectedBodiesReceived(expectedBody);
        template.sendBody("Sandeep");
        resultEndpoint.assertIsSatisfied();
    }    
    
    @Test
    public void Test4_Failed() throws Exception {
        String expectedBody = "Hello Unknown, I am sorry, not sure what to say.";
        resultEndpoint.expectedBodiesReceived(expectedBody);
        template.sendBody("");
        resultEndpoint.assertIsSatisfied();
    }

	
	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {
			public void configure() {
				from("direct:start").to("jetty:http://127.0.0.1:7082/jettysample?exchangePattern=InOut&amp;synchronous=true").to("mock:result");
				//from("direct:start").to("start").to("mock:result");
			}
		};
	}

}
