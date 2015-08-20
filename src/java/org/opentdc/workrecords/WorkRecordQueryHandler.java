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
import java.util.List;
import java.util.StringTokenizer;

import org.opentdc.query.AbstractQueryHandler;
import org.opentdc.query.QueryOperator;
import org.opentdc.query.QueryQuantor;
import org.opentdc.query.SortPredicate;
import org.opentdc.service.exception.NotImplementedException;
import org.opentdc.service.exception.ValidationException;

/**
 * Parses the query string and filters workrecord entries accordingly
 * @author Bruno Kaiser
 *
 */
public class WorkRecordQueryHandler extends AbstractQueryHandler {
	protected List<WorkRecordQueryPredicate> queryPredicates = null;

	/**
	 * Constructor.
	 */
	public WorkRecordQueryHandler(
			String query) 
	{
		queryPredicates = new ArrayList<WorkRecordQueryPredicate>();
		sortPredicates = new ArrayList<SortPredicate>();
		parsePredicates(query);
	}
	
	/**
	 * Parse the query string into Query- and Sort-Predicates.
	 * @param query the query string, a comma-separated list of predicates.
	 */
	protected void parsePredicates(
			String query) 
			throws ValidationException 
	{
		StringTokenizer _st = new StringTokenizer(query, ";");
		while (_st.hasMoreTokens()) {
			String _token = _st.nextToken();
			if (_token.startsWith("orderBy")) {
				sortPredicates.add(SortPredicate.parsePredicate(_token));
			}
			else {
				queryPredicates.add(WorkRecordQueryPredicate.parsePredicate(_token));
			}
		}
		logger.info("parsePredicates(" + query + ") -> OK");
	}

	/**
	 * @param model
	 * @return
	 */
	public boolean evaluate(
			TaggedWorkRecord wr)
			throws NotImplementedException, ValidationException 
	{
		boolean _retVal = true;
		for (WorkRecordQueryPredicate _queryPredicate : queryPredicates) {
			logger.info("evaluate(" + wr.getModel().getId() + "): featureType=" + _queryPredicate.getFeatureType() + ", operator=" + _queryPredicate.getOperator() + ", value=" + _queryPredicate.getValues()[0]);
			if (_queryPredicate.getQuantor() != QueryQuantor.NONE) {
				throw new NotImplementedException("support for QueryQuantor is not yet implemented");
			}
			if (_queryPredicate.getValues().length == 0) {
				throw new ValidationException("expected value is missing");
			}
			if (_queryPredicate.getValues().length > 1) {
				throw new NotImplementedException("Multi-valued queries are not yet supported");		
			}
			switch(_queryPredicate.getFeatureType()) {
			case STARTAT:		_retVal = evaluateDateOperation(wr.getModel().getStartAt(), _queryPredicate.getOperator(), _queryPredicate.getValues()); break;
			case PROJECTID:		_retVal = evaluateStringOperation(wr.getModel().getProjectId(), _queryPredicate.getOperator(), _queryPredicate.getValues()); break;
			case COMPANYID:		_retVal = evaluateStringOperation(wr.getModel().getCompanyId(), _queryPredicate.getOperator(), _queryPredicate.getValues()); break;
			case RESOURCEID:	_retVal = evaluateStringOperation(wr.getModel().getResourceId(), _queryPredicate.getOperator(), _queryPredicate.getValues()); break;
			case DURATIONHOURS: _retVal = evaluateIntOperation(wr.getModel().getDurationHours(), _queryPredicate.getOperator(), _queryPredicate.getValues()); break;
			case DURATIONMINUTES: _retVal = evaluateIntOperation(wr.getModel().getDurationMinutes(), _queryPredicate.getOperator(), _queryPredicate.getValues()); break;
			case ISBILLABLE:	_retVal = evaluateBooleanOperation(wr.getModel().isBillable(), _queryPredicate.getOperator(), _queryPredicate.getValues()); break;
			case ISRUNNING:		_retVal = evaluateBooleanOperation(wr.getModel().isRunning(), _queryPredicate.getOperator(), _queryPredicate.getValues()); break;
			case ISPAUSED:		_retVal = evaluateBooleanOperation(wr.getModel().isPaused(), _queryPredicate.getOperator(), _queryPredicate.getValues()); break;
			case TAGID:			_retVal = evaluateTagId(wr, _queryPredicate.getOperator(), _queryPredicate.getValues()); break;
			case CREATEDBY:		_retVal = evaluateStringOperation(wr.getModel().getCreatedBy(), _queryPredicate.getOperator(), _queryPredicate.getValues()); break;
			case MODIFIEDBY:	_retVal = evaluateStringOperation(wr.getModel().getModifiedBy(), _queryPredicate.getOperator(), _queryPredicate.getValues()); break;
			default: 			throw new ValidationException("FeatureType <" + _queryPredicate.getFeatureType() + "> is invalid.");
			}
			if (_retVal == false) break;
		}
		logger.info("evaluate() -> " + _retVal);
		return _retVal;
	}
	
	/**
	 * 
	 * @param wr
	 * @param operator
	 * @param expectedTagIds
	 * @return
	 * @throws NotImplementedException
	 */
	private boolean evaluateTagId(
			TaggedWorkRecord wr,
			QueryOperator operator,
			String[] expectedTagIds) 
			throws NotImplementedException {
		logger.info("evaluateTagId(" + wr.getModel().getId() + ", " + operator + ", " + expectedTagIds[0]);
		switch(operator) {
		case EQUALTO:					return wr.containsTag(expectedTagIds[0]);
		case NOTEQUALTO:				return !wr.containsTag(expectedTagIds[0]);
		case GREATERTHAN:				
		case GREATERTHANOREQUALTO:	
		case LESSTHAN:					
		case LESSTHANOREQUALTO:		
		case ISLIKE:					
		case NONE:	
		default:						throw new NotImplementedException("operator " + operator + " is not implemented for tagId.");
		}

	}
}
