/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.vstore.exml.local.loader.sax;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vstore.exml.common.index.IndexException;
import org.modelio.vstore.exml.common.model.IllegalReferenceException;
import org.modelio.vstore.exml.common.model.ObjIdName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

@objid ("2afeb977-3faf-11e2-87cb-001ec947ccaf")
class REFOBJState extends AbstractState {
    @objid ("2afeb978-3faf-11e2-87cb-001ec947ccaf")
    private ObjIdName ref;

    @objid ("2afeb986-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        if (localName.equals( "REFOBJ"))
        {
            this.stateHandler.enterRelationState();
        }
    }

    @objid ("2afeb979-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes attrs) throws SAXException {
        switch (localName) {
        case "ID":
            this.ref  = readID(attrs);
            resolve();
            break;
        case "PID":
            // Ignored since 3.4
            //this.ref.pid = readID(attrs);
        
            break;
        default:
            throwInvalidTag(localName);
        }
    }

    @objid ("2afeb984-3faf-11e2-87cb-001ec947ccaf")
    private void resolve() throws SAXParseException {
        if (this.ref != null) {
            try {
                getDataModel().addDepRef(this.ref);
            } catch (DuplicateObjectException | IllegalReferenceException | IndexException | RuntimeException e) {
                rethrowException(e);
            }
        }
    }

}
