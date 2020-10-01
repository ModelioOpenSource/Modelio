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
import org.modelio.vbasic.log.Log;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX handler that parses the document.
 * <p>
 * Uses the state machine design pattern.
 * Delegates the work to XxxState classes beginning with .
 * The first State is the {@link DocumentState}.
 */
@objid ("b5c58dfd-3fbb-11e2-87cb-001ec947ccaf")
final class DocumentContentHandler extends DefaultHandler {
    @objid ("b5c58e00-3fbb-11e2-87cb-001ec947ccaf")
    private boolean errorEncountered;

    @objid ("b5c58dff-3fbb-11e2-87cb-001ec947ccaf")
    private AbstractState currentState;

    @objid ("b5c58e03-3fbb-11e2-87cb-001ec947ccaf")
    private final RelationState relationState;

    @objid ("b5c58e05-3fbb-11e2-87cb-001ec947ccaf")
    private final DocumentState documentState;

    @objid ("b5c58e06-3fbb-11e2-87cb-001ec947ccaf")
    private Locator locator;

    @objid ("b5c58e07-3fbb-11e2-87cb-001ec947ccaf")
    private final LocalDataModel dataModel;

    @objid ("d94f9427-404d-11e2-87cb-001ec947ccaf")
    private final OBJECTState objectState;

    @objid ("b5c58e09-3fbb-11e2-87cb-001ec947ccaf")
    public DocumentContentHandler(LocalDataModel dataModel) {
        this.objectState = new OBJECTState();
        this.relationState = new RelationState();
        this.documentState = new DocumentState();
        
        this.documentState.setStateHandler (this);
        this.relationState.setStateHandler (this);
        this.objectState.setStateHandler (this);
        
        this.currentState = (this.documentState);
        this.errorEncountered = false;
        this.dataModel = dataModel;
    }

    @objid ("b5c58e0f-3fbb-11e2-87cb-001ec947ccaf")
    @Override
    public void characters(final char[] chars, final int start, final int length) throws SAXException {
        this.currentState.characters (chars, start, length);
    }

    @objid ("b5c58e1a-3fbb-11e2-87cb-001ec947ccaf")
    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        //TODO trace
        //System.out.println(" /"+localName);
        this.currentState.endElement (uri, localName, qName);
    }

    @objid ("b5c58e2f-3fbb-11e2-87cb-001ec947ccaf")
    @Override
    public void error(final SAXParseException exc) throws SAXException {
        // === begin cxx code ===
        // 
        //     char  msg = XMLString.transcode(exc.getMessage());
        //     XMLSSize_t column = exc.getColumnNumber();
        //     XMLSSize_t line = exc.getLineNumber();
        // 
        //     CR_string crmsg = getRC().getFormattedString("saxerror", line, column, msg);
        // 
        //     cerr << crmsg << endl;
        //     FLog.error(crmsg);
        // 
        //     XMLString.release( msg);
        throw exc;
        // === end cxx code ===
    }

    @objid ("b5c58e34-3fbb-11e2-87cb-001ec947ccaf")
    @Override
    public void fatalError(final SAXParseException exc) throws SAXException {
        throw exc;
        
        // === begin cxx code ===
        // 
        //     char                  msg = XMLString.transcode (exc.getMessage ());
        //     XMLSSize_t column = exc.getColumnNumber ();
        //     XMLSSize_t line = exc.getLineNumber ();
        // 
        //     CR_string crmsg =
        //             getRC ().getFormattedString ("saxerror", line, column, msg);
        // 
        //     cerr << crmsg << endl;
        //     FLog.fatal (crmsg);
        // 
        //     XMLString.release ( msg);
        // 
        //     throw exc;
        // === end cxx code ===
    }

    @objid ("b5c58e39-3fbb-11e2-87cb-001ec947ccaf")
    public Locator getLocator() {
        return this.locator;
    }

    @objid ("b5c58e3f-3fbb-11e2-87cb-001ec947ccaf")
    @Override
    public void setDocumentLocator(final Locator aLocator) {
        this.locator = aLocator;
        this.dataModel.setDocumentLocator(aLocator);
    }

    @objid ("b5c58e46-3fbb-11e2-87cb-001ec947ccaf")
    @Override
    public void startElement(final String uri, final String localName, final String qName, final org.xml.sax.Attributes atts) throws SAXException {
        //TODO trace
        //System.out.println(" >"+localName);
        this.currentState.startElement (uri, localName, qName, atts);
    }

    @objid ("b5c58e5a-3fbb-11e2-87cb-001ec947ccaf")
    @Override
    public void warning(final SAXParseException exc) {
        // === begin cxx code ===
        // 
        //     char                  msg = XMLString.transcode (exc.getMessage ());
        //     XMLSSize_t column = exc.getColumnNumber ();
        //     XMLSSize_t line = exc.getLineNumber ();
        // 
        //     CR_string crmsg =
        //             getRC ().getFormattedString ("saxwarning", line, column, msg);
        // 
        //     FLog.warn (crmsg);
        // 
        //     XMLString.release ( msg);
        // === end cxx code ===
        Log.warning(exc); // TODO better reporting
    }

    @objid ("b5c58e2b-3fbb-11e2-87cb-001ec947ccaf")
    void enterOBJECTState() {
        this.currentState = this.objectState;
    }

    @objid ("b5c58e25-3fbb-11e2-87cb-001ec947ccaf")
    void enterRelationState() {
        this.currentState =  this.relationState;
    }

    @objid ("b5c58e0c-3fbb-11e2-87cb-001ec947ccaf")
    LocalDataModel getDataModel() {
        return this.dataModel;
    }

    @objid ("b5c58e3c-3fbb-11e2-87cb-001ec947ccaf")
    boolean hasEncounteredError() {
        return this.errorEncountered;
    }

    /**
     * Reset the model between 2 runs.
     */
    @objid ("b5c58e5f-3fbb-11e2-87cb-001ec947ccaf")
    void reset() {
        this.errorEncountered = false;
        enterDocumentState();
    }

    @objid ("b5c58e44-3fbb-11e2-87cb-001ec947ccaf")
    void setErrorEncountered() {
        this.errorEncountered = true;
    }

    @objid ("b5c58e52-3fbb-11e2-87cb-001ec947ccaf")
    void throwError(final String msg) throws SAXException {
        error (new SAXParseException (msg,  getLocator ()));
    }

    @objid ("b5c58e56-3fbb-11e2-87cb-001ec947ccaf")
    void throwFatalError(final String msg) throws SAXException {
        fatalError (new SAXParseException (msg,  getLocator ()));
    }

    @objid ("b5c58e23-3fbb-11e2-87cb-001ec947ccaf")
    void enterDocumentState() {
        this.currentState =  this.documentState;
    }

}
