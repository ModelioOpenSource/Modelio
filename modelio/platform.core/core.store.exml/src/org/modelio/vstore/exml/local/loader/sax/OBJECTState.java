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
import org.modelio.vstore.exml.common.model.DependencyNotFoundException;
import org.modelio.vstore.exml.common.model.ObjIdName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Parses the first found ID tag to record a contained object
 * Parses the first PID tag, to record the parent CMS node.
 * 
 * then switches to the {@link OBJECT2State}.
 */
@objid ("2afeb95e-3faf-11e2-87cb-001ec947ccaf")
class OBJECTState extends AbstractState {
    @objid ("2afeb960-3faf-11e2-87cb-001ec947ccaf")
    private ObjIdName id;

    @objid ("2afeb96c-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        if (localName.equals(TAG_OBJECT))
        {
            this.stateHandler.popOBJECTState();
        }
    }

    @objid ("2afeb961-3faf-11e2-87cb-001ec947ccaf")
    @SuppressWarnings("deprecation")
    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes attrs) throws SAXException {
        switch (localName) {
        case TAG_ID:
            this.id = readID(attrs);
            break;
        case TAG_PID:
            // Ignore
            break;
        case TAG_ATTRIBUTES:
            getDataModel().pushObject(this.id);
            this.stateHandler.enterAttState();
            break;
        case TAG_DEPENDENCIES:
            break;
        case TAG_COMP:
        case TAG_LINK:
        {
            String depName = attrs.getValue(ATT_RELATION);
            try {
                getDataModel().getCurrent().beginDependency(depName);
            } catch (DependencyNotFoundException e) {
                addWarning(e);
            }
        
            this.stateHandler.enterRelationState();
        }
        break;
        case TAG_EXTDEP:
        {
            String depName = attrs.getValue(ATT_RELATION);
            // old tag not used anymore in Modelio 3
            try {
                getDataModel().getCurrent().beginDependency(depName);
            } catch (DependencyNotFoundException e) {
                addWarning(e);
            }
            this.stateHandler.enterEXTDEPState();
        }
        break;
        default:
            break;
        }
    }

    @objid ("0427073f-eae2-4e74-8504-24918689d7ff")
    private void addWarning(Exception e) {
        this.stateHandler.warning(
                new SAXParseException(e.getLocalizedMessage(),
                this.stateHandler.getLocator()));
    }

}
