package accountServer;

public class ResourceServerController implements ResourceServerControllerMBean {
	private final ResourceServerI resourceServer;
	
	public ResourceServerController(ResourceServerI resourceServer) {
		this.resourceServer = resourceServer;
	}
	
	@Override
	public String getName() {
		return resourceServer.getTestResource().getName();
	}
	
	@Override
	public int getAge() {
		return resourceServer.getTestResource().getAge();
	}
}