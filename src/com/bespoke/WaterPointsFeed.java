package com.bespoke;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

/**
 * This class imitates the JSON source. It depicts a web service that mimics a
 * crud functionality that would return the water point details, however, we
 * simply read the provided JSON file and expose the JSON String via a web
 * service for any web service client that would care to read from the path
 * 
 * @author Stanslaus
 *
 */
@Path("/waterfeed")
public class WaterPointsFeed {

	/**
	 * This method works to simulate the mining and distribution of the water
	 * points feed It would naturally do more than just reading the provided
	 * file and serving us with the JSON data
	 * 
	 * @return - Returns a response object with the provided JSON data read from
	 *         the file thus simulating a hosted web service that would be a
	 *         preferable way of serving the required data instead
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnJsonData() {
		String data = null;
		try {
			data = getJson();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Response.ok(data).build();
	}

	/**
	 * utility method to read the file that contains the JSON string from the
	 * classpath as a resource stream
	 * 
	 * @return returns the content of the read JSON file if found in the class
	 *         path
	 * @throws IOException
	 *             Error if the file can not be read
	 */
	private String getJson() throws IOException {

		InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream("water-points.json");
		String jsonData = IOUtils.toString(is, "UTF-8");
		return jsonData;

	}

}
