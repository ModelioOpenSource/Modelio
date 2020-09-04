/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.model.search.engine.searchers.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.model.search.engine.ISearchCriteria;
import org.modelio.model.search.engine.ISearchEngine;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The SearchEngine is able to find all the model elements matching its
 * criteria. The SearchEngine criteria are:
 * <ul>
 * <li>a regular expression used to match the model element name</li>
 * <li>a set of metaclasses defining the nature of the searched elements
 * <li>several boolean flags (eg: include/exclude RAMC)</li>
 * <li>a stereotype</li>
 */
@objid ("000693d4-c59e-10ab-8258-001ec947cd2a")
public class ModelSearchEngine implements ISearchEngine {
    @objid ("00078a00-c59e-10ab-8258-001ec947cd2a")
    @Override
    public List<Element> search(ICoreSession session, ISearchCriteria params) {
        assert (params instanceof ModelSearchCriteria);
        
        final ModelSearchCriteria criteria = (ModelSearchCriteria) params;
        
        final Set<MObject> rawResults = new HashSet<>();
        
        // Collect raw results
        for (final Class<? extends MObject> metaclass : criteria.getMetaclasses()) {
            rawResults.addAll(session.getModel().findByClass(metaclass, IModel.NODELETED));
        }
        
        
        
        final List<Element> filteredResults = new ArrayList<>();
        try {
            final int flags = (criteria.isCaseSensitive()) ? 0 : Pattern.CASE_INSENSITIVE;
            final Pattern p = Pattern.compile(criteria.getExpression(), flags);
        
            for (final MObject mObject : rawResults) {
        
                if (mObject instanceof Element) {
                    // check name condition
                    if (!p.matcher(mObject.getName()).matches()) {
                        continue;
                    }
                    // check is ramc condition
                    if (!criteria.isIncludeRamc() && !mObject.getStatus().isUserWrite()) {
                        continue;
                    }
                    // check stereotype condition
                    if (criteria.getStereotype() != null && !criteria.getStereotype().isEmpty()) {
                        if (!(mObject instanceof ModelElement
                                && isStereotyped(((ModelElement) mObject), criteria.getStereotype()))) {
                            continue;
                        }
                    }
        
                    // apply filter
                    if (criteria.getFilter() != null && !criteria.getFilter().accept(mObject)) {
                        continue;
                    }
        
                    // if reached, mObject is matching all the criteria, add it
                    // to results
                    filteredResults.add((Element) mObject);
                }
            }
        } catch (PatternSyntaxException e) {
            // do nothing, will simply return an empty list
        }
        return filteredResults;
    }

    @objid ("629f8a5c-756d-4442-9776-a7e4fe5028c5")
    private boolean isStereotyped(ModelElement modelElement, String stereotypeName) {
        for (Stereotype stereotype : modelElement.getExtension()) {
            if (stereotype.getName().equals(stereotypeName)) {
                return true;
            }
        }
        return false;
    }

}
