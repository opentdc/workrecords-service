package org.opentdc.workrecords;

import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.opentdc.exception.DuplicateException;
import org.opentdc.exception.NotFoundException;
import org.opentdc.exception.NotImplementedException;

public class MongoImpl extends StorageProvider {
	private static String url = null;
	private static String userName = null;
	private static String password = null;
	private static String providerName = null;

	// instance variables

	public MongoImpl(ServletContext context) {
		logger.info("> MongoImpl()");

		super.initStorageProvider();

		if (url == null) {
			url = context.getInitParameter("backend.url");
		}
		if (userName == null) {
			userName = context.getInitParameter("backend.userName");
		}
		if (password == null) {
			password = context.getInitParameter("backend.password");
		}
		if (providerName == null) {
			providerName = context.getInitParameter("backend.providerName");  // in MongoDB terms: the DB-name
		}

		logger.info("MongoImpl() initialized");
	}

	/******************************** workrecord *****************************************/
	/**
	 * List all workrecords.
	 * 
	 * @return a list of all workrecords.
	 */
	@Override
	public ArrayList<WorkRecordModel> listWorkRecords() {
		// TODO: implement listWorkRecords
		logger.info("listWorkRecords() -> " + countWorkRecords() + " workrecords");
		throw new NotImplementedException("listWorkRecords is not yet implemented for storage in MongoDB");
	}

	/**
	 * Create a new WorkRecord.
	 * 
	 * @param workrecord
	 * @return the newly created workrecord (can be different than workrecord param)
	 * @throws DuplicateException
	 *             if a workrecord with the same ID already exists.
	 */
	@Override
	public WorkRecordModel createWorkRecord(WorkRecordModel workrecord) throws DuplicateException {
		if (readWorkRecord(workrecord.getId()) != null) {
			// object with same ID exists already
			throw new DuplicateException();
		}
		// TODO: implement createWorkRecord
		logger.info("createWorkRecord() -> " + countWorkRecords() + " workrecords");
		throw new NotImplementedException(
			"method createWorkRecord is not yet implemented for storage in MongoDB");
		// logger.info("createWorkRecord() -> " + workrecord);
	}

	/**
	 * Find a WorkRecord by ID.
	 * 
	 * @param id
	 *            the WorkRecord ID
	 * @return the WorkRecord
	 * @throws NotFoundException
	 *             if there exists no WorkRecord with this ID
	 */
	@Override
	public WorkRecordModel readWorkRecord(String xri) throws NotFoundException {
		WorkRecordModel _workrecord = null;
		// TODO: implement readWorkRecord()
		throw new NotImplementedException(
			"method readWorkRecord() is not yet implemented for storage in MongoDB");
		// logger.info("readWorkRecord(" + xri + ") -> " + _workrecord);
	}

	@Override
	public WorkRecordModel updateWorkRecord(WorkRecordModel workrecord) throws NotFoundException {
		WorkRecordModel _workrecord = null;
		// TODO implement updateWorkRecord()
		throw new NotImplementedException(
				"method updateWorkRecord() is not yet implemented for storage in MongoDB.");
	}

	@Override
	public void deleteWorkRecord(String id) throws NotFoundException {
		// TODO implement deleteWorkRecord()
		throw new NotImplementedException(
				"method deleteWorkRecord() is not yet implemented for storage in MongoDB.");
	}

	@Override
	public int countWorkRecords() {
		int _count = -1;
		// TODO: implement countWorkRecords()
		throw new NotImplementedException(
				"method countWorkRecords() is not yet implemented for storage in MongoDB.");
		// logger.info("countWorkRecords() = " + _count);
	}


	/******************************** utility methods *****************************************/

}
