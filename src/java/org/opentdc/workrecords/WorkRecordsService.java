package org.opentdc.workrecords;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.opentdc.exception.DuplicateException;
import org.opentdc.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CXFNonSpringJaxrsServlet (defined in web.xml) uses Singleton as a default
 * scope for service classes specified by a jaxrs.serviceClasses servlet
 * parameter.
 * 
 * @author Bruno Kaiser
 *
 */
@Path("/api/workrecord")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WorkRecordsService {
	private static StorageProvider sp = null;

	// instance variables
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Invoked for each service invocation (Constructor)
	 */
	public WorkRecordsService(@Context ServletContext context) {
		logger.info("> WorkRecordsService()");
		if (sp == null) {
			sp = StorageFactory.getStorageProvider(context);
		}
		logger.info("WorkRecordsService() initialized");
	}

	/******************************** workrecord *****************************************/
	@GET
	@Path("/")
	public List<WorkRecordModel> listWorkRecords() {
		return sp.listWorkRecords();
	}

	@POST
	@Path("/")
	public WorkRecordModel createWorkRecord(WorkRecordModel workrecord) throws DuplicateException {
		return sp.createWorkRecord(workrecord);
	}

	@GET
	@Path("/{id}")
	public WorkRecordModel readWorkRecord(@PathParam("id") String id) throws NotFoundException {
		return sp.readWorkRecord(id);
	}

	@PUT
	@Path("/")
	public WorkRecordModel updateWorkRecord(WorkRecordModel workrecord) throws NotFoundException {
		return sp.updateWorkRecord(workrecord);
	}

	@DELETE
	@Path("/{id}")
	public void deleteWorkRecord(@PathParam("id") String id) throws NotFoundException {
		sp.deleteWorkRecord(id);
	}

	@GET
	@Path("/count")
	public int countWorkRecords() {
		return sp.countWorkRecords();
	}

}
