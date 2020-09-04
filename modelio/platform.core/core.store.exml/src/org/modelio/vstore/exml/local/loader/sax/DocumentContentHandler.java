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
@objid ("2af9f4e0-3faf-11e2-87cb-001ec947ccaf")
class DocumentContentHandler extends DefaultHandler {
    @objid ("2af9f4e3-3faf-11e2-87cb-001ec947ccaf")
    private boolean errorEncountered;

    @objid ("2af9f4e2-3faf-11e2-87cb-001ec947ccaf")
    private AbstractState currentState;

    @objid ("2af9f4e4-3faf-11e2-87cb-001ec947ccaf")
    private final OBJECTState objectState;

    @objid ("2af9f4e5-3faf-11e2-87cb-001ec947ccaf")
    private final REFOBJState refObjState;

    @objid ("2af9f4e6-3faf-11e2-87cb-001ec947ccaf")
    private final RelationState relationState;

    @objid ("2af9f4e7-3faf-11e2-87cb-001ec947ccaf")
    private final EXTDEPState extDepState;

    @objid ("2af9f4e8-3faf-11e2-87cb-001ec947ccaf")
    private final DocumentState documentState;

    @objid ("2af9f4e9-3faf-11e2-87cb-001ec947ccaf")
    private Locator locator;

    @objid ("2af9f4ea-3faf-11e2-87cb-001ec947ccaf")
    private final DataModel dataModel;

    @objid ("2af9f4eb-3faf-11e2-87cb-001ec947ccaf")
    private final AbstractState attState;

    @objid ("2af9f4ec-3faf-11e2-87cb-001ec947ccaf")
    public DocumentContentHandler(DataModel dataModel) {
        this.objectState = new OBJECTState();
        this.relationState = new RelationState();
        this.extDepState = new EXTDEPState();
        this.refObjState = new REFOBJState();
        this.attState = new ATTState();
        this.documentState = new DocumentState();
        
        this.documentState.setStateHandler (this);
        this.relationState.setStateHandler (this);
        this.extDepState.setStateHandler (this);
        this.objectState.setStateHandler (this);
        this.refObjState.setStateHandler (this);
        this.attState.setStateHandler (this);
        
        this.currentState = (this.documentState);
        this.errorEncountered = false;
        this.dataModel = dataModel;
    }

    @objid ("2af9f4f3-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void characters(final char[] chars, final int start, final int length) throws SAXException {
        try {
            this.currentState.characters (chars, start, length);
        } catch (RuntimeException e) {
            throw parseRuntimeException(e);
        }
    }

    @objid ("2afc56f7-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        try {
            this.currentState.endElement (uri, localName, qName);
        } catch (RuntimeException e) {
            throw parseRuntimeException(e);
        }
    }

    @objid ("2afc570c-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void error(final SAXParseException exc) throws SAXException {
        throw exc;
    }

    @objid ("2afc5711-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void fatalError(final SAXParseException exc) throws SAXException {
        throw exc;
    }

    @objid ("2afc5716-3faf-11e2-87cb-001ec947ccaf")
    public Locator getLocator() {
        return this.locator;
    }

    @objid ("2afc571e-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void setDocumentLocator(final Locator aLocator) {
        this.locator = aLocator;
        this.dataModel.setDocumentLocator(aLocator);
    }

    @objid ("2afc5725-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void startElement(final String uri, final String localName, final String qName, final org.xml.sax.Attributes atts) throws SAXException {
        try {
            this.currentState.startElement (uri, localName, qName, atts);
        } catch (RuntimeException e) {
            throw parseRuntimeException(e);
        }
    }

    @objid ("2afc5739-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void warning(final SAXParseException exc) {
        Log.warning(exc); // TODO better reporting
    }

    @objid ("2afc5706-3faf-11e2-87cb-001ec947ccaf")
    void enterAttState() {
        this.currentState = this.attState;
    }

    @objid ("2afc5704-3faf-11e2-87cb-001ec947ccaf")
    void enterEXTDEPState() {
        this.currentState =  this.extDepState;
        this.dataModel.getCurrent().setCurrentState(this.currentState);
    }

    @objid ("2afc5708-3faf-11e2-87cb-001ec947ccaf")
    void enterOBJECTState() {
        this.currentState = this.objectState;
    }

    @objid ("2afc570a-3faf-11e2-87cb-001ec947ccaf")
    void enterREFOBJState() {
        this.currentState = this.refObjState;
    }

    @objid ("2afc5702-3faf-11e2-87cb-001ec947ccaf")
    void enterRelationState() {
        this.currentState =  this.relationState;
        this.dataModel.getCurrent().setCurrentState(this.currentState);
    }

    @objid ("2af9f4ef-3faf-11e2-87cb-001ec947ccaf")
    DataModel getDataModel() {
        return this.dataModel;
    }

    @objid ("2afc571a-3faf-11e2-87cb-001ec947ccaf")
    boolean hasEncounteredError() {
        return this.errorEncountered;
    }

    @objid ("2afc5741-3faf-11e2-87cb-001ec947ccaf")
    void popOBJECTState() {
        final IObjectDataModel oldModel = this.dataModel.pop();
        final IObjectDataModel currentModel = this.dataModel.getCurrent();
        
        oldModel.finishDependenciesLoading();
        
        if (currentModel == null) {
            enterDocumentState();
        } else {
            this.currentState = currentModel.getCurrentState();
            currentModel.addToDep(oldModel.getObject());
        }
    }

    /**
     * Reset the model between 2 runs.
     */
    @objid ("2afc573e-3faf-11e2-87cb-001ec947ccaf")
    public void reset() {
        this.errorEncountered = false;
        enterDocumentState();
    }

    @objid ("2afc5723-3faf-11e2-87cb-001ec947ccaf")
    void setErrorEncountered() {
        this.errorEncountered = true;
    }

    @objid ("2afc5731-3faf-11e2-87cb-001ec947ccaf")
    void throwError(final String msg) throws SAXException {
        error (new SAXParseException (msg,  getLocator ()));
    }

    @objid ("2afc5735-3faf-11e2-87cb-001ec947ccaf")
    void throwFatalError(final String msg) throws SAXException {
        fatalError (new SAXParseException (msg,  getLocator ()));
    }

    @objid ("2afc5700-3faf-11e2-87cb-001ec947ccaf")
    private void enterDocumentState() {
        this.currentState =  this.documentState;
    }

    @objid ("10def486-a7e4-472c-a2c7-112810102e0e")
    private SAXParseException parseRuntimeException(RuntimeException e) {
        return new SAXParseException(e.toString(), getLocator(), e);
    }

}
