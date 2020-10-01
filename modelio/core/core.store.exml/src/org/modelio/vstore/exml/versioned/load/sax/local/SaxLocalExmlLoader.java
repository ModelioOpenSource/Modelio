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

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vstore.exml.common.ILoadHelper;
import org.modelio.vstore.exml.common.utils.ExmlUtils;
import org.modelio.vstore.exml.local.loader.sax.IDependencyContentHook;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

/**
 * SAX model loader.
 * <p>
 * Usage: instantiate and the call {@link #load(InputSource, IModelLoader)} for each EXML file in the repository.
 */
@objid ("b5c58ec2-3fbb-11e2-87cb-001ec947ccaf")
public class SaxLocalExmlLoader implements IDependencyContentHook {
    @objid ("b5c58ec6-3fbb-11e2-87cb-001ec947ccaf")
    private boolean loadInProgress;

    @objid ("b5c58ec4-3fbb-11e2-87cb-001ec947ccaf")
    private final DocumentContentHandler defaultHandler;

    @objid ("b5c58ec5-3fbb-11e2-87cb-001ec947ccaf")
    private XMLReader xmlReader;

    @objid ("b5c58ec7-3fbb-11e2-87cb-001ec947ccaf")
    private final LocalDataModel dataModel;

    /**
     * Initialize the SAX loader.
     * 
     * @param loadHelper a load helper
     */
    @objid ("b5c58ec8-3fbb-11e2-87cb-001ec947ccaf")
    public SaxLocalExmlLoader(ILoadHelper loadHelper) {
        this.dataModel = new LocalDataModel(loadHelper);
        this.defaultHandler = new DocumentContentHandler(this.dataModel);
        
        try {
            SAXParserFactory saxFactory = SAXParserFactory.newInstance();
            SAXParser parser = saxFactory.newSAXParser();
            this.xmlReader = parser.getXMLReader();
        
            this.xmlReader.setFeature("http://xml.org/sax/features/namespaces", true);
        
            this.xmlReader.setContentHandler(this.defaultHandler);
            this.xmlReader.setErrorHandler(this.defaultHandler);
        } catch (ParserConfigurationException e) {
            // should never happen
            throw new Error(e);
        } catch (SAXException e) {
            // should never happen
            throw new Error(e);
        }
    }

    /**
     * Load an EXML resource from an XML {@link InputSource}.
     * 
     * @param is the EXML source.
     * @param loader the API to use to load the content.
     * @return the loaded CMS node.
     * @throws java.io.IOException in case of failure
     * @throws org.modelio.vcore.model.DuplicateObjectException if another object with the same identifier as a loaded object already exists in another repository.
     */
    @objid ("b5c58ecd-3fbb-11e2-87cb-001ec947ccaf")
    public LocalDataModel load(final InputSource is, IModelLoader loader) throws IOException, DuplicateObjectException {
        if (this.loadInProgress) {
            throw new IllegalStateException("Reentrant call not allowed.");
        }
        
        this.loadInProgress = true;
        this.dataModel.reset();
        this.defaultHandler.reset();
        this.dataModel.setModelLoader (loader);
        
        
        try {
            this.xmlReader.parse(is);
            return this.dataModel;
        } catch(SAXParseException toCatch) {
            String msg = toCatch.getPublicId()
                    +":"+toCatch.getLineNumber()
                    +":"+toCatch.getColumnNumber()
                    +": "+toCatch.getLocalizedMessage();
            throw new IOException(msg, toCatch );
        } catch (SAXException e) {
            String msg = is.getPublicId()+": "+e.getLocalizedMessage();
            throw new IOException (msg, e);
        } catch (IOException e) {
            String msg = is.getPublicId()+": "+e.getLocalizedMessage();
            throw new IOException (msg, e);
        } catch (Error | RuntimeException e) { // NOPMD by cma on 2/6/17 1:52 PM
            e.addSuppressed(new Throwable("Error occurred while reading '"+is.getPublicId()+"'"));
            throw e;
        } finally {
            this.loadInProgress = false;
            this.dataModel.setModelLoader (null);
        }
    }

    /**
     * Reset the data model.
     */
    @objid ("ddde5795-407a-11e2-87cb-001ec947ccaf")
    public void clear() {
        this.dataModel.reset();
        this.defaultHandler.reset();
    }

    /**
     * Add the local data to the dependency.
     */
    @objid ("ddde5799-407a-11e2-87cb-001ec947ccaf")
    @Override
    public List<SmObjectImpl> getContent(SmObjectImpl obj, SmDependency dep) {
        if (ExmlUtils.isDepComponent(dep)) {
            return this.dataModel.getDependencyContent(obj, dep);
        }
        return null;
    }

    @objid ("6e1c2ccc-b9ea-42a3-b6a1-871d97f6a20b")
    @Override
    public Collection<? extends Content> getContent(SmObjectImpl obj) {
        return this.dataModel.getDependencyContent(obj);
    }

}
