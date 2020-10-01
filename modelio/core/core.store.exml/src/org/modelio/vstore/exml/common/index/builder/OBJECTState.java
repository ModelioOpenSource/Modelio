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
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Parses the first found ID tag to record a contained object
 * Parses the first PID tag, to record the parent CMS node.
 * 
 * All ID tags are added to CMS node content.
 * Parses all object references and foreign references.
 */
@objid ("fd26b9fa-5986-11e1-991a-001ec947ccaf")
class OBJECTState extends AbstractState {
    @objid ("fd21f6b9-5986-11e1-991a-001ec947ccaf")
    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes attrs) throws SAXException {
        if (localName.equals(ExmlTags.TAG_ID))
        {
            String cuid = attrs.getValue (ExmlTags.ATT_ID_UID);
            String cclassof = attrs.getValue (ExmlTags.ATT_ID_MC);
            String cname = attrs.getValue (ExmlTags.ATT_ID_NAME);
        
            try {
                // If first ID it is the CMS node ID.
                if (this.stateHandler.getCmsNode() == null) {
                    this.stateHandler.addContainedObject(cclassof, cuid, cname, true);
                } else {
                    // it is a contained object
                    this.stateHandler.addContainedObject(cclassof, cuid, cname, false);
                }
            } catch (IndexException e) {
                this.stateHandler.throwError(e);
            }
        }
        else if (localName.equals(ExmlTags.TAG_CMSNODE_PID))
        {
            String cuid = attrs.getValue (ExmlTags.ATT_ID_UID);
            String cclassof = attrs.getValue (ExmlTags.ATT_ID_MC);
            String cname = attrs.getValue (ExmlTags.ATT_ID_NAME);
        
            try {
                // Called for all PID found but the handler will remember
                // only the first call.
                // Since 3.4 there should be only one PID tag in the whole file.
                this.stateHandler.setParent (cclassof, cuid, cname);
            } catch (IndexException e) {
                this.stateHandler.throwError(e);
            }
        } else if (localName.equals(ExmlTags.TAG_LINK) ||
                localName.equals(ExmlTags.TAG_COMP)) {
            this.stateHandler.enterLINKState(attrs.getValue (ExmlTags.ATT_RELATION));
        }
    }

    @objid ("fd21f6b7-5986-11e1-991a-001ec947ccaf")
    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        if (localName.equals(ExmlTags.TAG_OBJECT)) {
            this.stateHandler.popData();
            // This may also be end of file but don't mind it
            this.stateHandler.enterLINKState(null);
        }
    }

    @objid ("fd21f6b4-5986-11e1-991a-001ec947ccaf")
    public OBJECTState() {
    }

}
