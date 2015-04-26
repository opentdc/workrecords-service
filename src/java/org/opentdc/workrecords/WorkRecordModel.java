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

import java.util.Date;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class WorkRecordModel {
	public final static String DEF_XRI = "XRI_UNDEFINED";
	public final static String DEF_ID = "ID_UNDEFINED";
	public final static String DEF_COMMENT = "COMMENT_UNDEFINED";

	private String id;
	private String xri;
	private String projectId;
	private String resourceId;
	private Date startAt;
	private int durationHours;
	private int durationMinutes;
	private String rateId;
	private boolean isBillable;
	private String comment;

	public WorkRecordModel() {
		this.id = UUID.randomUUID().toString();
		this.xri = DEF_XRI;
		this.projectId = DEF_ID;
		this.resourceId = DEF_ID;
		this.startAt = new Date();
		this.durationHours = 1;
		this.durationMinutes = 30;
		this.rateId = DEF_ID;
		this.isBillable = true;
		this.setComment(DEF_COMMENT);
	}

	public WorkRecordModel(
			String xri,
			String projectId, 
			String resourceId,
			Date startAt,
			int durationHours,
			int durationMinutes,
			String rateId,
			boolean isBillable, 
			String comment) {
		this.id = UUID.randomUUID().toString();
		this.xri = xri;
		this.projectId = projectId;
		this.resourceId = resourceId;
		this.startAt = startAt;
		this.durationHours = durationHours;
		this.durationMinutes = durationMinutes;
		this.rateId = rateId;
		this.isBillable = isBillable;
		this.setComment(comment);
	}
	
	public WorkRecordModel(WorkRecordModel w, boolean generateId) {
		if (generateId == true) {
			this.id = UUID.randomUUID().toString();
		}
		else {
			this.id = w.getId();
		}
		this.xri = w.getXri();
		this.projectId = w.getProjectId();
		this.resourceId = w.getResourceId();
		this.startAt = w.getStartAt();
		this.durationHours = w.getDurationHours();
		this.durationMinutes = w.getDurationMinutes();
		this.rateId = w.getRateId();
		this.isBillable = w.isBillable();
		this.setComment(w.getComment());
	}

	/**
	 * 
	 * @return the ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set the ID to a unique random number
	 */
	public void setId() {
		this.id = UUID.randomUUID().toString();
	}

	/**
	 * Return the object ID in the backend.
	 * @return the xri 
	 */
	public String getXri() {
		return xri;
	}

	/**
	 * Set the object ID for the backend.
	 * @param xri
	 *            the xri to set
	 */
	public void setXri(String xri) {
		this.xri = xri;
	}

	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the resourceId
	 */
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * @return the startAt
	 */
	public Date getStartAt() {
		return startAt;
	}

	/**
	 * @param startAt the startAt to set
	 */
	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	/**
	 * @return the durationHours
	 */
	public int getDurationHours() {
		return durationHours;
	}

	/**
	 * @param durationHours the durationHours to set
	 */
	public void setDurationHours(int durationHours) {
		this.durationHours = durationHours;
	}

	/**
	 * @return the durationMinutes
	 */
	public int getDurationMinutes() {
		return durationMinutes;
	}

	/**
	 * @param durationMinutes the durationMinutes to set
	 */
	public void setDurationMinutes(int durationMinutes) {
		this.durationMinutes = durationMinutes;
	}

	/**
	 * @return the rateId
	 */
	public String getRateId() {
		return rateId;
	}

	/**
	 * @param rateId the rateId to set
	 */
	public void setRateId(String rateId) {
		this.rateId = rateId;
	}

	/**
	 * @return the isBillable
	 */
	public boolean isBillable() {
		return isBillable;
	}

	/**
	 * @param isBillable the isBillable to set
	 */
	public void setBillable(boolean isBillable) {
		this.isBillable = isBillable;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
}
