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

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Bruno Kaiser
 *
 */
public class TaggedWorkRecord {
	private WorkRecordModel model;
	ArrayList<TagRefModel> tagRefs;

	/******************************* Constructors *****************************/
	/**
	 * Constructor.
	 */
	public TaggedWorkRecord() {
		tagRefs = new ArrayList<TagRefModel>();
	}

	/******************************* Setters / Getters *****************************/
	/**
	 * Retrieve the WorkRecordModel.
	 * @return the WorkRecordModel
	 */
	public WorkRecordModel getModel() {
		return model;
	}

	/**
	 * Set the WorkRecordModel
	 * @param model the WorkRecordModel
	 */
	public void setModel(WorkRecordModel model) {
		this.model = model;
	}

	/**
	 * Retrieve a list of all references to tags (TagRefModel)
	 * @return a list of TagRefModels
	 */
	public ArrayList<TagRefModel> getTagRefs() {
		return tagRefs;
	}

	/**
	 * Set the list of all references to tags (TagRefModel)
	 * @param tagRefs a list of TagRefModels
	 */
	public void setTagRefs(ArrayList<TagRefModel> tagRefs) {
		this.tagRefs = tagRefs;
	}
	
	/**
	 * Add a reference to a tag to a workrecord.
	 * @param tagRef the reference to a tag
	 */
	public void addTagRef(TagRefModel tagRef) {
		this.tagRefs.add(tagRef);
	}
	
	/**
	 * Remove a reference to a tag from a workrecord.
	 * @param tagRef the tag reference that needs to be removed
	 * @return true if the removal was successful
	 */
	public boolean removeTagRef(TagRefModel tagRef) {
		return this.tagRefs.remove(tagRef);
	}
	
	/**
	 * Tests whether this TaggedWorkRecord contains a TagRefModel with the same tag (id).
	 * @param tagId the tag id to test for
	 * @return true if the TagRefModel is already contained, false if otherwise
	 */
	public boolean containsTag(String tagId) {
		for (TagRefModel _model : tagRefs) {
			if (_model.getTagId() == tagId) {
				return true;
			}
		}
		return false;
	}
	/******************************* Comparator *****************************/
	public static Comparator<TaggedWorkRecord> TaggedWorkRecordComparator = new Comparator<TaggedWorkRecord>() {

		public int compare(TaggedWorkRecord obj1, TaggedWorkRecord obj2) {
			if (obj1.getModel().getId() == null) {
				return -1;
			}
			if (obj2.getModel().getId() == null) {
				return 1;
			}

			String _attr1 = obj1.getModel().getId();
			String _attr2 = obj2.getModel().getId();

			// ascending order
			return _attr1.compareTo(_attr2);

			// descending order
			// return _attr2.compareTo(_attr1);
		}
	};

	
}
