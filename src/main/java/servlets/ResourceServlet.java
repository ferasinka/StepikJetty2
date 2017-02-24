package servlets;

import accountServer.ResourceServerI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resources.TestResource;
import sax.ReadXMLFileSAX;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResourceServlet extends HttpServlet {
	public static final String PAGE_URL = "/resources";
	static final Logger logger = LogManager.getLogger(AdminServlet.class.getName());
	private ResourceServerI resourceServer;
	
	public ResourceServlet(ResourceServerI resourceServer) {
		this.resourceServer = resourceServer;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathToResource = req.getParameter("path");
		
		TestResource resource = (TestResource) ReadXMLFileSAX.readXML(pathToResource);
		resourceServer.setTestResource(resource);
	}
}
