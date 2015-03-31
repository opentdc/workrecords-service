/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Arbalo AG
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.opentdc.workrecords;

import java.util.List;
import java.util.logging.Logger;

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

import org.opentdc.service.GenericService;
import org.opentdc.service.exception.DuplicateException;
import org.opentdc.service.exception.NotFoundException;

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
public class WorkRecordsService extends GenericService<ServiceProvider> {
	
	private static ServiceProvider sp = null;

	// instance variables
	private Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * Invoked for each service invocation (Constructor)
	 * @throws ReflectiveOperationException 
	 */
	public WorkRecordsService(
		@Context ServletContext context
	) throws ReflectiveOperationException {
		logger.info("> WorkRecordsService()");
		if (sp == null) {
			sp = this.getServiceProvider(WorkRecordsService.class, context);
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
