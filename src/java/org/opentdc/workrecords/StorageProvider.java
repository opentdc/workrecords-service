package org.opentdc.workrecords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.opentdc.exception.DuplicateException;
import org.opentdc.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class StorageProvider {
	protected static Map<String, WorkRecordModel> index = null;

	// instance variables
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public void initStorageProvider() {
		logger.info("> initStorageProvider()");

		if (index == null) {
			index = new HashMap<String, WorkRecordModel>();
		}

		logger.info("initStorageProvider() initialized");
	}

	/************************* workrecords *****************************/
	public abstract ArrayList<WorkRecordModel> listWorkRecords();

	public abstract WorkRecordModel createWorkRecord(WorkRecordModel workrecord) throws DuplicateException;

	public abstract WorkRecordModel readWorkRecord(String id) throws NotFoundException;

	public abstract WorkRecordModel updateWorkRecord(WorkRecordModel workrecord) throws NotFoundException;

	public abstract void deleteWorkRecord(String id) throws NotFoundException;

	public abstract int countWorkRecords();

}
