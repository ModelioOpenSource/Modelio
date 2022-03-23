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
package org.modelio.platform.search.engine.searchers.note;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.platform.search.engine.ISearchCriteria;
import org.modelio.platform.search.engine.ISearchEngine;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The SearchEngine is able to find all the model elements matching its criteria. The SearchEngine criteria are:
 * <ul>
 * <li>a regular expression used to match the model element name</li>
 * <li>a set of metaclasses defining the nature of the searched elements
 * <li>several boolean flags (eg: include/exclude RAMC)</li>
 * <li>a stereotype</li>
 * 
 * @author phv
 */
@objid ("bd9547e8-16cc-4f56-8eec-1be0b278296c")
public class NoteSearchEngine implements ISearchEngine {
    @objid ("ab99cdc6-5d67-4a0b-a74a-9d8582d1dc7d")
    public  NoteSearchEngine() {
        
    }

    @objid ("d6d902b9-55bb-4f40-8569-a01adc99707a")
    @Override
    public List<Element> search(ICoreSession session, ISearchCriteria params) {
        assert (params instanceof NoteSearchCriteria);
        
        final NoteSearchCriteria criteria = (NoteSearchCriteria) params;
        
        // Collect raw results
        final Collection<? extends MObject> rawResults = session.getModel().findByClass(session.getMetamodel().getMClass(Note.class), IModel.NODELETED);
        final List<Element> filteredResults = new ArrayList<>();
        
        try {
            final int flags = (criteria.isCaseSensitive()) ? 0 : Pattern.CASE_INSENSITIVE;
            final String noteType = (criteria.getNoteType().isEmpty() ? ".*" : criteria.getNoteType());
            final Pattern typePattern = Pattern.compile(noteType);
            final Pattern contentsPattern = Pattern.compile(criteria.getExpression(), flags);
        
            for (final MObject mObject : rawResults) {
                final Note note = (Note) mObject;
        
                // check note type
                if (!typePattern.matcher(note.getModel().getName()).matches()) {
                    continue;
                }
        
                // check text condition
                if (!contentsPattern.matcher(note.getContent()).find()) {
                    continue;
                }
        
                // if reached, mObject is matching all the criteria, add it to results
                filteredResults.add(note);
            }
        
        } catch (final PatternSyntaxException e) {
            // do nothing, will simply return an empty list
        }
        return filteredResults;
    }

}
