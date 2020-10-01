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

package org.modelio.vstore.exml.versioned;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vstore.exml.common.AbstractExmlRepository;
import org.modelio.vstore.exml.common.ExmlStorageHandler;
import org.modelio.vstore.exml.common.ILoadHelper;
import org.modelio.vstore.exml.common.model.ObjId;
import org.modelio.vstore.exml.resource.IExmlResourceProvider.ExmlResource;
import org.modelio.vstore.exml.resource.IExmlResourceProvider;
import org.modelio.vstore.exml.versioned.load.IVersionedExmlLoader;
import org.modelio.vstore.exml.versioned.load.sax.SaxVersionedExmlLoader;
import org.modelio.vstore.exml.versioned.save.VersionedExmlSaver;
import org.xml.sax.InputSource;

/**
 * EXML repository with references to non versioned elements saved in a separate file.
 */
@objid ("f51527b6-12b9-11e2-816a-001ec947ccaf")
public abstract class VersionedExmlBase extends AbstractExmlRepository {
    @objid ("979e713f-12de-11e2-816a-001ec947ccaf")
    private IVersionedExmlLoader versionedLoader;

    @objid ("679724a2-2e7b-11e2-8aaa-001ec947ccaf")
    private VersionedLoadHelper loadHelper;

    /**
     * Initialize the repository.
     * 
     * @param resProvider an EXML resource provider.
     */
    @objid ("979e7140-12de-11e2-816a-001ec947ccaf")
    public VersionedExmlBase(IExmlResourceProvider resProvider) {
        super(resProvider);
    }

    @objid ("979e7148-12de-11e2-816a-001ec947ccaf")
    @Override
    public synchronized void doReloadCmsNode(SmObjectImpl obj, IModelLoader modelLoader) throws DuplicateObjectException, IOException {
        ObjId cmsNodeId = new ObjId(obj);
        
        IExmlResourceProvider resProvider = getResourceProvider();
        ExmlResource resource = resProvider.getResource(cmsNodeId);
        ExmlResource localRes = resProvider.getLocalResource(cmsNodeId);
        
        try (InputStream is = resource.bufferedRead();
                InputStream lis = localRes.bufferedRead();) {
            if (is == null) {
                // Exml not found, set the object as shell.
                throw new FileNotFoundException(cmsNodeId+": "+resource.getPublicLocation());
            } else {
                // setup XML input sources
                InputSource src = new InputSource(is);
                InputSource lsrc = null;
                if (lis != null ) {
                    lsrc = new InputSource(lis);
                    lsrc.setPublicId(localRes.getPublicLocation());
                }
        
                src.setPublicId(resource.getPublicLocation());
        
                // Load XML files
                this.versionedLoader.load(src, lsrc, modelLoader);
            }
        }
    }

    @objid ("979e714e-12de-11e2-816a-001ec947ccaf")
    @Override
    protected void save(ExmlStorageHandler handler, IModelioProgress progress) throws IOException {
        final SmObjectImpl cmsNode = handler.getCmsNode();
        final ObjId cmsNodeId = new ObjId(cmsNode);
        
        try (OutputStream os = getResourceProvider().getResource(cmsNodeId).bufferedWrite();){
            VersionedExmlSaver saver = new VersionedExmlSaver(getErrorSupport());
            
            saver.externalize(cmsNode, os, getResourceProvider().getLocalResource(cmsNodeId));
        }
    }

    @objid ("3e36dd14-1ea1-11e2-90db-001ec947ccaf")
    @Override
    protected final void initializeLoader() {
        this.loadHelper = new VersionedLoadHelper(this, getStatusInitializer(), isWriteable());
        this.versionedLoader = new SaxVersionedExmlLoader(this.loadHelper);
    }

    /**
     * Get the version status flags initializer.
     * 
     * @return the status flags initializer.
     */
    @objid ("3e36dd17-1ea1-11e2-90db-001ec947ccaf")
    protected abstract IVersionStatusInitializer getStatusInitializer();

    @objid ("679724a3-2e7b-11e2-8aaa-001ec947ccaf")
    @Override
    protected final ILoadHelper getloadHelper() {
        return this.loadHelper;
    }

}
