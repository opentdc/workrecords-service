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

import org.opentdc.service.exception.*;

public interface ServiceProvider {

	public abstract List<WorkRecordModel> listWorkRecords(
		String queryType,
		String query,
		long position,
		long size
	);

	public abstract WorkRecordModel createWorkRecord(
			WorkRecordModel workrecord) 
		throws DuplicateException, ValidationException;

	public abstract WorkRecordModel readWorkRecord(
			String id) 
		throws NotFoundException;

	public abstract WorkRecordModel updateWorkRecord(
			String id, 
			WorkRecordModel workrecord) 
		throws NotFoundException, NotAllowedException;

	public abstract void deleteWorkRecord(
			String id) 
		throws NotFoundException, InternalServerErrorException;
}
