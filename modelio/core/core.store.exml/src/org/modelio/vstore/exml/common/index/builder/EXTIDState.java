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
package org.modelio.vstore.exml.common.index.builder;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vstore.exml.common.index.IndexException;
import org.modelio.vstore.exml.common.model.ExmlTags;
import org.xml.sax.SAXException;

@objid ("fd26b9f9-5986-11e1-991a-001ec947ccaf")
@Deprecated
class EXTIDState extends AbstractState {
    @objid ("fd21f6af-5986-11e1-991a-001ec947ccaf")
    public void enterState(final org.xml.sax.Attributes attributes) throws SAXException {
        // Ignored since 3.4
        String cuid = attributes.getValue (ExmlTags.ATT_ID_UID);
        String cclassof = attributes.getValue (ExmlTags.ATT_ID_MC);
        String cname = attributes.getValue (ExmlTags.ATT_ID_NAME);
        
        try {
            this.stateHandler.addDependencyTarget (cclassof, cuid, cname);
        } catch (IndexException e) {
            this.stateHandler.throwError(e);
        }
        
    }

    @objid ("fd21f675-5986-11e1-991a-001ec947ccaf")
    @SuppressWarnings("deprecation")
    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        if (localName.equals(ExmlTags.TAG_DEPS_EXTID))
        {
            this.stateHandler.enterOBJECTState();
        }
        
    }

}
