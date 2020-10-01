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

package org.modelio.vstore.exml.versioned.load.sax.local;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vstore.exml.common.model.ObjId;
import org.modelio.vstore.exml.common.model.ObjIdName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Parses the first found ID tag to record a contained object
 * Parses the first PID tag, to record the parent CMS node.
 * 
 * then switches to the {@link OBJECT2State}.
 */
@objid ("b5c58e82-3fbb-11e2-87cb-001ec947ccaf")
class OBJECTState extends AbstractState {
    @objid ("b5c58e84-3fbb-11e2-87cb-001ec947ccaf")
    private ObjIdName id;

    @objid ("b5c58e99-3fbb-11e2-87cb-001ec947ccaf")
    OBJECTState() {
    }

    @objid ("b5c58e90-3fbb-11e2-87cb-001ec947ccaf")
    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        if (localName.equals(TAG_OBJECT))
        {
            this.stateHandler.enterDocumentState();
        }
    }

    @objid ("b5c58e85-3fbb-11e2-87cb-001ec947ccaf")
    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes attrs) throws SAXException {
        switch (localName) {
        case TAG_ID:
            this.id = readID(attrs);
            getDataModel().beginObject(this.id);
            break;
        case TAG_COMP:
            getDataModel().beginDependency(attrs.getValue(ATT_RELATION));
            this.stateHandler.enterRelationState();
            break;
        default:
            throwInvalidTag(localName);
        }
    }

}
