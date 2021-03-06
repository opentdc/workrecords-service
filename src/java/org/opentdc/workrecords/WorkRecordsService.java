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
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.opentdc.service.GenericService;
import org.opentdc.service.TagRefModel;
import org.opentdc.service.exception.*;

@Path("/api/workrecord")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WorkRecordsService extends GenericService<ServiceProvider> {
	
	private ServiceProvider sp = null;

	// instance variables
	private static final Logger logger = Logger.getLogger(WorkRecordsService.class.getName());

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
	public List<WorkRecordModel> listWorkRecords(
		@DefaultValue(DEFAULT_QUERY) @QueryParam("query") String query,
		@DefaultValue(DEFAULT_QUERY_TYPE) @QueryParam("queryType") String queryType,
		@DefaultValue(DEFAULT_POSITION) @QueryParam("position") int position,
		@DefaultValue(DEFAULT_SIZE) @QueryParam("size") int size			
	) {
		return sp.listWorkRecords(query, queryType, position, size);
	}

	@POST
	@Path("/")
	public WorkRecordModel createWorkRecord(
			@Context HttpServletRequest request,
			WorkRecordModel workrecord) 
		throws DuplicateException, ValidationException {
		return sp.createWorkRecord(request, workrecord);
	}

	@GET
	@Path("/{id}")
	public WorkRecordModel readWorkRecord(
		@PathParam("id") String id
	) throws NotFoundException {
		return sp.readWorkRecord(id);
	}

	@PUT
	@Path("/{id}")
	public WorkRecordModel updateWorkRecord(
		@Context HttpServletRequest request,
		@PathParam("id") String id,
		WorkRecordModel workrecord
	) throws NotFoundException, ValidationException {
		return sp.updateWorkRecord(request, id, workrecord);
	}

	@DELETE
	@Path("/{id}")
	public void deleteWorkRecord(
			@PathParam("id") String id) 
		throws NotFoundException, InternalServerErrorException {
		sp.deleteWorkRecord(id);
	}
	
	/********************************** tagRef ***************************************/
	@GET
	@Path("/{id}/tagref")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TagRefModel> listTagRefs(
		@PathParam("id") String id,
		@DefaultValue(DEFAULT_QUERY) @QueryParam("query") String query,
		@DefaultValue(DEFAULT_QUERY_TYPE) @QueryParam("queryType") String queryType,
		@DefaultValue(DEFAULT_POSITION) @QueryParam("position") int position,
		@DefaultValue(DEFAULT_SIZE) @QueryParam("size") int size
	) {
		return sp.listTagRefs(id, query, queryType, position, size);
	}

	@POST
	@Path("/{id}/addTags")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<TagRefModel> addTags(
		@Context HttpServletRequest request,
		@PathParam("id") String id,
		String tags)
		throws ValidationException
	{
		return sp.addTags(request, id, tags);
	}
	
	@POST
	@Path("/{id}/tagref")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TagRefModel createTagRef(
		@Context HttpServletRequest request,
		@PathParam("id") String id, 
		TagRefModel model
	) throws DuplicateException, ValidationException {
		return sp.createTagRef(request, id, model);
	}
	
	@GET
	@Path("/{id}/tagref/{tid}")
	@Produces(MediaType.APPLICATION_JSON)
	public TagRefModel readTagRef(
		@PathParam("id") String id,
		@PathParam("tid") String tid
	) throws NotFoundException {
		return sp.readTagRef(id, tid);
	}

	@DELETE
	@Path("/{id}/tagref/{tid}")
	public void deleteTagRef(
		@PathParam("id") String id,
		@PathParam("tid") String tid
	) throws NotFoundException, InternalServerErrorException {
		sp.deleteTagRef(id, tid);
	}
}
