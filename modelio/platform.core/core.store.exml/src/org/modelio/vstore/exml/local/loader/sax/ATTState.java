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
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

@objid ("2af53043-3faf-11e2-87cb-001ec947ccaf")
class ATTState extends AbstractState {
    @objid ("40f433bc-3faf-11e2-87cb-001ec947ccaf")
    private String attName;

    /**
     * Used to concat all CDATA nodes content.
     */
    @objid ("2af53044-3faf-11e2-87cb-001ec947ccaf")
    private StringBuilder attValueBuilder;

    @objid ("2af53052-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void characters(char[] chars, int start, int length) throws SAXException {
        if (this.attValueBuilder != null) {
            this.attValueBuilder.append(chars, start, length);
        }
    }

    @objid ("2af5305a-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        switch (localName) {
        case TAG_ATT:
            getDataModel().loadAtt(this.attName, this.attValueBuilder.toString());
            this.attValueBuilder = null;
            this.attName = null;
            break;
        case TAG_ATTRIBUTES:
            getDataModel().initObjectFlags();
            this.stateHandler.enterOBJECTState();
            break;
        default:
            throwInvalidTag(localName);
        }
    }

    @objid ("2af53047-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes attrs) throws SAXException {
        if (localName.equals( TAG_ATT))
        {
            this.attName = attrs.getValue(ATT_NAME);
            this.attValueBuilder = new StringBuilder();
        
            String basicValue = attrs.getValue(ATT_ATT_VALUE);
            if (basicValue != null) {
                this.attValueBuilder.append(basicValue);
            }
        } else {
            throwInvalidTag(localName);
        }
    }

}
