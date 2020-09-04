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

import java.io.IOException;
import java.util.ServiceConfigurationError;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vstore.exml.common.ILoadHelper;
import org.modelio.vstore.exml.local.loader.IExmlLoader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

/**
 * SAX model loader.
 * <p>
 * Usage: instantiate and the call {@link #load(InputSource, IModelLoader)} for each EXML file in the repository.
 * <p>
 * Note : this class is not thread safe nor reentrant.
 */
@objid ("2b011b99-3faf-11e2-87cb-001ec947ccaf")
public class SaxExmlLoader implements IExmlLoader {
    @objid ("2b011b9b-3faf-11e2-87cb-001ec947ccaf")
    private final DocumentContentHandler defaultHandler;

    @objid ("2b011b9c-3faf-11e2-87cb-001ec947ccaf")
    private final XMLReader xmlReader;

    @objid ("2b011b9e-3faf-11e2-87cb-001ec947ccaf")
    private final DataModel dataModel;

    @objid ("879bda47-31e8-4b86-a46c-a9df0e412cf9")
    private final AtomicBoolean loadInProgress = new AtomicBoolean(false);

    /**
     * Initialize the SAX loader.
     * @param loadHelper a load helper
     */
    @objid ("2b011b9f-3faf-11e2-87cb-001ec947ccaf")
    public SaxExmlLoader(ILoadHelper loadHelper) {
        this.dataModel = new DataModel(loadHelper);
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
            throw new ServiceConfigurationError(e.getLocalizedMessage(), e);
        } catch (SAXException e) {
            // should never happen
            throw new ServiceConfigurationError(e.getLocalizedMessage(), e);
        }
    }

    /**
     * Load an EXML resource from an XML {@link InputSource}.
     * @param is the EXML source.
     * @param loader the API to use to load the content.
     * @return the loaded CMS node.
     * @throws java.io.IOException in case of failure
     * @throws org.modelio.vcore.model.DuplicateObjectException if another object with the same identifier as a loaded object already exists in another repository.
     */
    @objid ("2b011ba4-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public SmObjectImpl load(final InputSource is, IModelLoader loader) throws DuplicateObjectException, IOException {
        if (! this.loadInProgress.compareAndSet(false, true)) {
            throw new IllegalStateException(getClass().getSimpleName()+" Reentrant call not allowed.");
        }
        
        try {
            this.dataModel.reset();
            this.defaultHandler.reset();
            this.dataModel.setModelLoader (loader);
        
            this.xmlReader.parse(is);
            return this.dataModel.getRootObject();
        } catch(SAXParseException e) {
            String msg = e.getPublicId()
                    +":"+e.getLineNumber()
                    +":"+e.getColumnNumber()
                    +": "+e.getLocalizedMessage();
            
            throw new IOException(msg, e );
        } catch (SAXException e) {
            String msg = is.getPublicId()+": "+e.getLocalizedMessage();
            throw new IOException (msg, e);
        } catch (IOException e) {
            String msg = is.getPublicId()+": "+FileUtils.getLocalizedMessage(e);
            throw new IOException (msg, e);
        } catch (Error | RuntimeException e) {
            // just add a debugging exception and rethrow
            e.addSuppressed(new Throwable("Error occurred while reading '"+is.getPublicId()+"'"));
            throw e;
        } finally {
            this.loadInProgress.set(false);
            this.dataModel.setModelLoader (null);
        }
    }

    @objid ("ddef07b7-407a-11e2-87cb-001ec947ccaf")
    protected final void setDependencyContentHook(IDependencyContentHook hook) {
        this.dataModel.setDependencyContentHook(hook);
    }

}
