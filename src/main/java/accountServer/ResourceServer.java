package accountServer;

import resources.TestResource;

public class ResourceServer implements ResourceServerI {
	private TestResource testResource;
	
	public ResourceServer(TestResource testResource) {
		this.testResource = testResource;
	}
	
	public ResourceServer() {
	}
	
	@Override
	public TestResource getTestResource() {
		return testResource;
	}
	
	@Override
	public void setTestResource(TestResource resource) {
		testResource = resource;
	}
}
