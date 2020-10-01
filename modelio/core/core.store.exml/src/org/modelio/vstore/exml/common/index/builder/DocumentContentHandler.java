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

import java.io.IOError;
import java.util.ArrayDeque;
import java.util.Deque;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vstore.exml.common.index.ICmsNodeIndex;
import org.modelio.vstore.exml.common.index.IUserNodeIndex;
import org.modelio.vstore.exml.common.index.IndexException;
import org.modelio.vstore.exml.common.model.ObjId;
import org.modelio.vstore.exml.common.model.ObjIdName;
import org.modelio.vstore.exml.common.utils.ObjIdReader;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX handler that parses the document.
 * <p>
 * Uses the state machine design pattern. Delegates the work to XxxState classes beginning with . The first State is the
 * {@link DocumentState}.
 */
@objid ("fd26b9fe-5986-11e1-991a-001ec947ccaf")
class DocumentContentHandler extends DefaultHandler {
    @objid ("fd21f6e4-5986-11e1-991a-001ec947ccaf")
    private boolean errorEncountered;

    @objid ("fd21f6df-5986-11e1-991a-001ec947ccaf")
    private boolean parentSet;

    @objid ("fd21f6e8-5986-11e1-991a-001ec947ccaf")
    private AbstractState currentState;

    @objid ("fd1f92d0-5986-11e1-991a-001ec947ccaf")
    private final ICmsNodeIndex cmsNodeIndex;

    @objid ("fd21f54a-5986-11e1-991a-001ec947ccaf")
    private final IUserNodeIndex userNodeIndex;

    @objid ("fd21f6e3-5986-11e1-991a-001ec947ccaf")
    private final OBJECTState objectState;

    @objid ("fd21f6e2-5986-11e1-991a-001ec947ccaf")
    private final EXTIDState extIdState;

    @objid ("fd21f6e1-5986-11e1-991a-001ec947ccaf")
    private final REFOBJState refObjState;

    @objid ("fd21f6de-5986-11e1-991a-001ec947ccaf")
    private final DocumentState documentState;

    @objid ("fd21f5d5-5986-11e1-991a-001ec947ccaf")
    private ObjId cmsNodeId;

    @objid ("fd21f67b-5986-11e1-991a-001ec947ccaf")
    private Locator locator;

    @objid ("686ff554-9939-4b1f-a36a-86f23936e088")
    private final LINKState linkState;

    @objid ("020232d5-7353-4f31-a525-6144153e5969")
    private final ObjIdReader idReader;

    @objid ("e84b9549-377e-4645-9588-9bbc5a0954c3")
    private final Deque<Data> dataStack = new ArrayDeque<>();

    @objid ("fd21f716-5986-11e1-991a-001ec947ccaf")
    public DocumentContentHandler(SmMetamodel metamodel, ICmsNodeIndex parentIndex, IUserNodeIndex userIndex) {
        this.cmsNodeIndex = parentIndex;
        this.userNodeIndex = userIndex;
        
        this.extIdState = new EXTIDState();
        this.objectState = new OBJECTState();
        this.refObjState = new REFOBJState();
        this.linkState = new LINKState();
        
        this.documentState = new DocumentState();
        
        this.documentState.setStateHandler(this);
        this.extIdState.setStateHandler(this);
        this.objectState.setStateHandler(this);
        this.refObjState.setStateHandler(this);
        this.linkState.setStateHandler(this);
        
        this.currentState = (this.documentState);
        this.errorEncountered = false;
        this.idReader = new ObjIdReader(metamodel, 
                () -> "Index builder: ", 
                () -> ": line "+getLocator().getLineNumber() + " in " + getLocator().getPublicId());
    }

    @objid ("fd21f715-5986-11e1-991a-001ec947ccaf")
    public void addContainedObject(final String className, final String uid, final String name, boolean isCmsNode) throws IndexException {
        ObjIdName childIdName = this.idReader.readObjIdName(className, uid, name, isCmsNode);
        ObjId childId = childIdName.toObjId();
        if (this.cmsNodeId==null) {
            assert (isCmsNode);
            this.cmsNodeId = childId;
        }
        
        this.cmsNodeIndex.addObject(this.cmsNodeId, childIdName);
        pushObject(childId);
    }

    @objid ("fd21f714-5986-11e1-991a-001ec947ccaf")
    public void addDependencyTarget(final String className, final String uid, final String name) throws IndexException {
        ObjId usedId = this.idReader.readObjId(className, uid, false);
        
        // add to used refs unless usedId is contained inside cmsNodeId
        if (! this.cmsNodeId.equals(this.cmsNodeIndex.getCmsNodeOf(usedId))) {
            Data data = getDataStack().element();
            this.userNodeIndex.addUsed(data.id, data.curDepName, usedId);
        }
    }

    @objid ("fd21f711-5986-11e1-991a-001ec947ccaf")
    @Override
    public void characters(final char[] chars, final int start, final int length) throws SAXException {
        this.currentState.characters(chars, start, length);
    }

    @objid ("fd21f710-5986-11e1-991a-001ec947ccaf")
    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        this.currentState.endElement(uri, localName, qName);
    }

    @objid ("fd21f70d-5986-11e1-991a-001ec947ccaf")
    public void enterDocumentState() {
        this.currentState = (this.documentState);
    }

    @objid ("fd21f70c-5986-11e1-991a-001ec947ccaf")
    @Deprecated
    public void enterEXTIDState(final Attributes attributes) throws SAXException {
        this.currentState = this.extIdState;
        this.extIdState.enterState(attributes);
    }

    @objid ("fd21f70a-5986-11e1-991a-001ec947ccaf")
    public void enterOBJECTState() {
        this.currentState = this.objectState;
    }

    @objid ("fd21f709-5986-11e1-991a-001ec947ccaf")
    public void enterREFOBJState() {
        this.currentState = this.refObjState;
    }

    @objid ("fd21f707-5986-11e1-991a-001ec947ccaf")
    @Override
    public void error(final SAXParseException exc) throws SAXException {
        throw exc;
    }

    @objid ("fd21f703-5986-11e1-991a-001ec947ccaf")
    public Locator getLocator() {
        return this.locator;
    }

    @objid ("fd21f702-5986-11e1-991a-001ec947ccaf")
    public boolean hasEncounteredError() {
        return this.errorEncountered;
    }

    @objid ("fd21f6f6-5986-11e1-991a-001ec947ccaf")
    public void setCmsNode(final String className, final String uid, final String name) throws IndexException {
        ObjIdName objIdName = this.idReader.readObjIdName(className, uid, name, true);
        this.cmsNodeId = objIdName.toObjId();
        this.cmsNodeIndex.addCmsNode(objIdName);
    }

    @objid ("fd21f6ea-5986-11e1-991a-001ec947ccaf")
    @Override
    public void setDocumentLocator(final Locator aLocator) {
        this.locator = aLocator;
    }

    @objid ("fd21f6e9-5986-11e1-991a-001ec947ccaf")
    public void setErrorEncountered() {
        this.errorEncountered = true;
    }

    /**
     * Set the current CMS node parent
     * 
     * @param className the metaclass name
     * @param uid the UUID
     * @param name the name (currently unused)
     * @throws org.modelio.vstore.exml.common.index.IndexException on index broken
     */
    @objid ("fd21f6e7-5986-11e1-991a-001ec947ccaf")
    public void setParent(final String className, final String uid, final String name) throws IndexException {
        if (!this.parentSet) {
            this.parentSet = true;
        
            ObjId parentId = this.idReader.readObjId(className, uid, true);
            this.cmsNodeIndex.setParent(this.cmsNodeId, parentId);
        }
    }

    @objid ("fd21f6e6-5986-11e1-991a-001ec947ccaf")
    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes atts) throws SAXException {
        try {
            this.currentState.startElement(uri, localName, qName, atts);
        } catch (IOError err) {
            throwError((Exception) err.getCause());
        }
    }

    @objid ("fd21f6e5-5986-11e1-991a-001ec947ccaf")
    void throwError(final Exception e) throws SAXParseException {
        String msg = e.getLocalizedMessage();
        if (msg==null || msg.isEmpty()) {
            msg = e.toString();
        }
        
        throw new SAXParseException(msg, getLocator(), e);
    }

    @objid ("fd21f6d2-5986-11e1-991a-001ec947ccaf")
    @Override
    public void warning(final SAXParseException exc) {
        Log.warning(exc); // TODO better reporting
    }

    /**
     * Reset the model between 2 runs.
     */
    @objid ("4086635d-1878-11e2-9dfc-001ec947ccaf")
    public void resetModel() {
        this.cmsNodeId = null;
        this.errorEncountered = false;
        this.parentSet = false;
    }

    @objid ("56e05097-6d1e-4c2c-b20d-844e7cbd08f9")
    public void enterLINKState(String depName) {
        this.currentState = this.linkState;
        if (depName != null) {
            getDataStack().element().curDepName = depName;
        }
    }

    @objid ("b5c0b734-c5dc-4025-a9a2-7a4ee1b33a32")
    public ObjId getCmsNode() {
        return this.cmsNodeId;
    }

    @objid ("5c1dcc64-b9d1-4624-92d8-2ed8d36ef720")
    private Deque<Data> getDataStack() {
        return this.dataStack;
    }

    @objid ("a7f494ea-162d-453e-9333-472e4e67ebb5")
    public void pushObject(ObjId id) {
        this.dataStack.push(new Data(id));
    }

    @objid ("719bd94b-07ea-4288-b303-dec7ca161cd7")
    public void popData() {
        this.dataStack.pop();
    }

    @objid ("e77ff2f2-5c55-47ed-a021-ff577f7bbc3f")
    public ObjId readId(final String className, final String uid, final String name, boolean isCmsNode) throws IndexException {
        return this.idReader.readObjId(className, uid, isCmsNode);
    }

    @objid ("1a295e82-8df7-4c57-96e8-92f7e292934f")
    private static class Data {
        @objid ("e0d1e4bc-07d9-437f-931a-fd34d98e3318")
         String curDepName;

        @objid ("4bac275c-7a48-4267-85c5-ccee87e71db4")
         final ObjId id;

        @objid ("0f74cb1e-9161-4dcf-a719-248b8a787b74")
        public Data(ObjId id) {
            this.id = id;
        }

    }

}
