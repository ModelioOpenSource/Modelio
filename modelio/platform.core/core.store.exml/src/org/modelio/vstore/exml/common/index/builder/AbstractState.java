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

package org.modelio.vstore.exml.common.index.builder;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

@objid ("fd26b9fc-5986-11e1-991a-001ec947ccaf")
@SuppressWarnings("unused")
class AbstractState {
    @objid ("fd21f6d5-5986-11e1-991a-001ec947ccaf")
    protected DocumentContentHandler stateHandler;

    @objid ("fd21f6cf-5986-11e1-991a-001ec947ccaf")
    public void setStateHandler(final DocumentContentHandler value) {
        this.stateHandler =  value;
    }

    @objid ("fd21f6cd-5986-11e1-991a-001ec947ccaf")
    public AbstractState() {
    }

    @objid ("fd21f6cc-5986-11e1-991a-001ec947ccaf")
    public void startElement(final String uri, final String localName, final String qName, final Attributes atts) throws SAXException {
        // do nothing by default
    }

    @objid ("fd21f6cb-5986-11e1-991a-001ec947ccaf")
    public void characters(final char[] chars, final int start, final int length) throws SAXException {
        // do nothing by default
    }

    @objid ("fd21f696-5986-11e1-991a-001ec947ccaf")
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        // do nothing by default
    }

}
