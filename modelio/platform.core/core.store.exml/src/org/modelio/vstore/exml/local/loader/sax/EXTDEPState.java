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

package org.modelio.vstore.exml.local.loader.sax;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vstore.exml.common.index.IndexException;
import org.modelio.vstore.exml.common.model.IllegalReferenceException;
import org.modelio.vstore.exml.common.model.ObjIdName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Read 'EXTDEP' obsolete tags.
 */
@objid ("2afeb94f-3faf-11e2-87cb-001ec947ccaf")
class EXTDEPState extends AbstractState {
    @objid ("2afeb958-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals(TAG_EXTDEP)) {
            getDataModel().getCurrent().updateCurrentDependency();
            this.stateHandler.enterOBJECTState();
        }
    }

    @objid ("2afeb951-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        try {
            ObjIdName objid;
            if (localName.equals(TAG_ID)) {
                objid = readID(atts);
                if (objid != null) {
                    getDataModel().addDepRef(objid);
                }
            } else {
                throwInvalidTag(localName);
            }
        } catch (DuplicateObjectException e) {
            rethrowException(e);
        } catch (IllegalReferenceException e) {
            rethrowException(e);
        } catch (IndexException e) {
            rethrowException(e);
        }
    }

}
