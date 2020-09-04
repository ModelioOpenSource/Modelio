/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vstore.exml.common.index.IndexException;
import org.modelio.vstore.exml.common.model.IllegalReferenceException;
import org.modelio.vstore.exml.common.model.ObjIdName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Read 'COMP' and 'LINK' tags.
 */
@objid ("b5c58eb3-3fbb-11e2-87cb-001ec947ccaf")
class RelationState extends AbstractState {
    @objid ("b5c58ebc-3fbb-11e2-87cb-001ec947ccaf")
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (localName) {
        case TAG_COMP:
            this.stateHandler.enterOBJECTState();
            break;
        default:
            break;
        }
    }

    @objid ("b5c58eb5-3fbb-11e2-87cb-001ec947ccaf")
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        try {
            switch (localName) {
            case TAG_COMPID:
                ObjIdName objid = readID(atts);
                getDataModel().addDepId(objid);
        
                break;
            default:
                throwInvalidTag(localName);
        
            }
        } catch (IllegalReferenceException e) {
            // TODO: Don't know whether to throw a warning or a fatal error.
            //this.stateHandler.warning(new SAXParseException(e.getLocalizedMessage(), this.stateHandler.getLocator(), e));
            rethrowException(e);
        } catch (DuplicateObjectException e) {
            rethrowException(e);
        } catch (IndexException e) {
            rethrowException(e);
        }
    }

}
