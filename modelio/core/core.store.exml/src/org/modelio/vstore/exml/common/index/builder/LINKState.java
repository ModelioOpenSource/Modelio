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

@objid ("79b169c0-37da-4d89-bd9a-fa29b37900f5")
class LINKState extends AbstractState {
    @objid ("049d32fe-3232-435d-92b1-53564295d920")
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
        try {
            if (localName.equals(ExmlTags.TAG_ID) ||
                    localName.equals(ExmlTags.TAG_COMPID) ||
                    localName.equals( ExmlTags.TAG_FOREIGNID))
            {
                String cuid = attrs.getValue (ExmlTags.ATT_ID_UID);
                String cclassof = attrs.getValue (ExmlTags.ATT_ID_MC);
                String cname = attrs.getValue (ExmlTags.ATT_ID_NAME);
        
                this.stateHandler.addDependencyTarget(cclassof, cuid, cname);
            }
            else if (localName.equals( ExmlTags.TAG_REFOBJ))
            {
                this.stateHandler.enterREFOBJState();
            }
            else if (localName.equals(ExmlTags.TAG_OBJECT))
            {
                this.stateHandler.enterOBJECTState();
            }
        } catch (IndexException | RuntimeException e) {
            this.stateHandler.throwError(e);
        }
        
    }

    @objid ("cf582774-2601-44e0-bd88-f2a240530ec2")
    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        if (localName.equals(ExmlTags.TAG_LINK) ||
                localName.equals(ExmlTags.TAG_COMP))
        {
            this.stateHandler.enterOBJECTState();
        }
        
    }

}
