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

import org.opentdc.service.TagRefModel;
import org.opentdc.service.exception.*;

/**
 * Abstract implementation of WorkRecordService.
 * @author Bruno Kaiser
 *
 */
public interface ServiceProvider {

	//**************************** WorkRecord *************************************
	/**
	 * Retrieve a list of WorkRecords.
	 * @param query	the query string
	 * @param queryType the query type
	 * @param position the position to start the result set
	 * @param size the size of returning batches
	 * @return
	 */
	public abstract List<WorkRecordModel> listWorkRecords(
		String query,
		String queryType,
		int position,
		int size
	);

	/**
	 * Create a new WorkRecord.
	 * @param workrecord the new data
	 * @return the newly created WorkRecord
	 * @throws DuplicateException if a WorkRecord with the same ID already exists
	 * @throws ValidationException if any data is incorrect
	 */
	public abstract WorkRecordModel createWorkRecord(
			WorkRecordModel workrecord) 
		throws DuplicateException, ValidationException;

	/**
	 * Retrieve a WorkRecord by its ID.
	 * @param id the ID of the WorkRecord to retrieve
	 * @return the WorkRecord found
	 * @throws NotFoundException if no WorkRecord with this ID was found
	 */
	public abstract WorkRecordModel readWorkRecord(
			String id) 
		throws NotFoundException;

	/**
	 * Update a WorkRecord.
	 * @param id the ID of the WorkRecord to update
	 * @param workrecord the new data
	 * @return the updated WorkRecord data
	 * @throws NotFoundException if no WorkRecord with this ID was found
	 * @throws ValidationException if the new data is incorrect
	 */
	public abstract WorkRecordModel updateWorkRecord(
			String id, 
			WorkRecordModel workrecord) 
		throws NotFoundException, ValidationException;

	/**
	 * Delete a WorkRecord by its ID.
	 * @param id the ID of the WorkRecord to delete
	 * @throws NotFoundException if no WorkRecord with this ID was found
	 * @throws InternalServerErrorException if any data inconsistencies were encountered
	 */
	public abstract void deleteWorkRecord(
			String id) 
		throws NotFoundException, InternalServerErrorException;
	
	//**************************** TagRef *************************************
	/**
	 * List all references to Tags of a specific WorkRecord
	 * @param workRecordId the WorkRecord to list its Tags for
	 * @param query the query string
	 * @param queryType the type of query
	 * @param position the position to start the result list with
	 * @param size the size of returned batches
	 * @return
	 */
	public abstract List<TagRefModel> listTagRefs(
			String workRecordId,
			String query,
			String queryType,
			int position,
			int size
		);
	
	/**
	 * @param workRecordId
	 * @param tags
	 * @return
	 * @throws ValidationException
	 */
	public abstract List<TagRefModel> addTags(
			String workRecordId,
			String tags)
	throws ValidationException;

	/**
	 * @param workRecordId
	 * @param model
	 * @return
	 * @throws DuplicateException
	 * @throws ValidationException
	 */
	public abstract TagRefModel createTagRef(
			String workRecordId,
			TagRefModel model) 
	throws DuplicateException, ValidationException;

	/**
	 * @param workRecordId
	 * @param tagRefId
	 * @return
	 * @throws NotFoundException
	 */
	public abstract TagRefModel readTagRef(
			String workRecordId,
			String tagRefId) 
	throws NotFoundException;

	/**
	 * @param workRecordId
	 * @param tagRefId
	 * @throws NotFoundException
	 * @throws InternalServerErrorException
	 */
	public abstract void deleteTagRef(
			String workRecordId,
			String tagRefId) 
	throws NotFoundException, InternalServerErrorException;
}
