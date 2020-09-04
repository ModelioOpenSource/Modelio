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

package org.modelio.vstore.exml.local;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.session.impl.storage.IModelLoaderProvider;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vstore.exml.common.AbstractExmlRepository;
import org.modelio.vstore.exml.common.ExmlStorageHandler;
import org.modelio.vstore.exml.common.ILoadHelper;
import org.modelio.vstore.exml.common.LoadHelper;
import org.modelio.vstore.exml.common.index.IndexException;
import org.modelio.vstore.exml.common.model.ObjId;
import org.modelio.vstore.exml.local.loader.IExmlLoader;
import org.modelio.vstore.exml.local.loader.sax.SaxExmlLoader;
import org.modelio.vstore.exml.local.save.ExmlSaver;
import org.modelio.vstore.exml.resource.IExmlResourceProvider.ExmlResource;
import org.modelio.vstore.exml.resource.IExmlResourceProvider;
import org.xml.sax.InputSource;

/**
 * EXML local repository implementation.
 */
@objid ("fd26ba14-5986-11e1-991a-001ec947ccaf")
public class ExmlBase extends AbstractExmlRepository {
    @objid ("679e4b83-2e7b-11e2-8aaa-001ec947ccaf")
    private ILoadHelper loadHelper;

    @objid ("fd1f92aa-5986-11e1-991a-001ec947ccaf")
    private IExmlLoader loader;

    /**
     * Initialize a EXML repository from an EXML resource provider.
     * 
     * @param resProvider an EXML resource provider.
     */
    @objid ("3e1a4116-1ea1-11e2-90db-001ec947ccaf")
    public ExmlBase(IExmlResourceProvider resProvider) {
        super(resProvider);
    }

    /**
     * Initialize a EXML repository from a directory path.
     * <p>
     * The repository needs to be {@link #open(IModelLoaderProvider, IModelioProgress) opened} before being used.
     * 
     * @param path a directory path.
     * @throws java.io.IOException in case of failure.
     * @deprecated use {@link #ExmlBase(Path, String)}
     */
    @objid ("3e1a4119-1ea1-11e2-90db-001ec947ccaf")
    @Deprecated
    public ExmlBase(Path path) throws IOException {
        super(path, path, path.toString());
    }

    /**
     * Initialize a EXML repository from a directory path.
     * <p>
     * The repository needs to be {@link #open(IModelLoaderProvider, IModelioProgress) opened} before being used.
     * 
     * @param path a directory path.
     * @param name a repository identifier to use in messages.
     * @throws java.io.IOException in case of failure.
     */
    @objid ("e1cdc0fc-80ea-4f03-965a-548bd951c657")
    public ExmlBase(Path path, String name) throws IOException {
        super(path, path, name);
    }

    @objid ("a013bb70-1d0e-11e2-8eb9-001ec947ccaf")
    @Override
    public synchronized void doReloadCmsNode(SmObjectImpl obj, IModelLoader modelLoader) throws DuplicateObjectException, IOException, IndexException {
        final ObjId cmsNodeId = new ObjId(obj);
        
        ExmlResource resource = getResourceProvider().getResource(cmsNodeId);
        if (resource == null) {
            // No EXML for this node, set the object as shell.
            throw new FileNotFoundException(cmsNodeId.toString());
        } else {
            try(InputStream is= resource.bufferedRead()) {
                if (is == null) {
                    // Exml not found, set the object as shell.
                    throw new FileNotFoundException(resource.getPublicLocation());
                } else {
                    InputSource isrc = new InputSource(is);
                    isrc.setPublicId(resource.getPublicLocation());
        
                    this.loader.load(isrc, modelLoader);
                }
            }
        }
    }

    @objid ("679e4b84-2e7b-11e2-8aaa-001ec947ccaf")
    @Override
    protected ILoadHelper getloadHelper() {
        return this.loadHelper;
    }

    @objid ("3e1a411c-1ea1-11e2-90db-001ec947ccaf")
    @Override
    protected void initializeLoader() {
        this.loadHelper = new LoadHelper(this, isWriteable());
        //this.loader = new ExmlLoader(this.loadHelper, this.getLoadCache());
        this.loader = new SaxExmlLoader(this.loadHelper);
    }

    @objid ("fd2458e1-5986-11e1-991a-001ec947ccaf")
    @Override
    protected void save(ExmlStorageHandler handler, IModelioProgress progress) throws IOException {
        SmObjectImpl cmsNode = handler.getCmsNode();
        ObjId cmsNodeId = handler.getCmsNodeId();
        
        try (OutputStream os = getResourceProvider().getResource(cmsNodeId).bufferedWrite()){
            ExmlSaver saver = new ExmlSaver();
            saver.externalize(cmsNode, os);
        }
    }

}
