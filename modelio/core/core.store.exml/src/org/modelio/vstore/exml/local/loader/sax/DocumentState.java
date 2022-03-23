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
package org.modelio.vstore.exml.local.loader.sax;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vstore.exml.common.model.ExmlTags;
import org.modelio.vstore.exml.plugin.VStoreExml;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * First state of the document.
 * <p>
 * Parses the content of the DEPS tag.
 * Switches to {@link OBJECTState} when finding the first OBJECT tag.
 */
@objid ("2afc5743-3faf-11e2-87cb-001ec947ccaf")
class DocumentState extends AbstractState {
    @objid ("2afeb943-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void startElement(final String uri, final String localName, final String qName, final org.xml.sax.Attributes attrs) throws SAXException {
        switch (localName) {
        case TAG_EXT:
            final String sversion = attrs.getValue(ATT_EXT_VERSION);
            if (sversion==null || sversion.isEmpty()) {
                throw new SAXParseException(VStoreExml.I18N.getMessage("ExmlFormat.unspecified"), this.stateHandler.getLocator());
            }
        
            int v = Integer.parseInt(sversion);
            if (v > ExmlTags.FORMAT_VERSION) {
                throw new SAXParseException(
                        VStoreExml.I18N.getMessage("ExmlFormat.newer", v, ExmlTags.FORMAT_VERSION),
                        this.stateHandler.getLocator());
            }
        
            getDataModel().setVersion(v);
            break;
        case TAG_ID:
            /*try {
                getDataModel().readRootOBJECT(readID(attrs));
            } catch (DuplicateObjectException e) {
                rethrowDuplicateObjException(e);
            }*/
            break;
        case TAG_OBJECT:
            this.stateHandler.enterOBJECTState();
            break;
        default:
            break;
        }
        
    }

}
