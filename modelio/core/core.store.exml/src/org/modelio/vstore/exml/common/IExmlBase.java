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
package org.modelio.vstore.exml.common;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.resource.Resource;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.model.MObjectCache;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.session.impl.storage.IModelLoaderProvider;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vstore.exml.common.index.ICmsNodeIndex;
import org.modelio.vstore.exml.common.index.IndexException;
import org.modelio.vstore.exml.common.model.IllegalReferenceException;
import org.modelio.vstore.exml.common.model.ObjId;

/**
 * Extension of {@link IRepository} with a private EXML repository API
 * used by {@link ExmlStorageHandler}.
 * <p>
 * Not to be used outside the EXML repository implementation.
 */
@objid ("fd26ba16-5986-11e1-991a-001ec947ccaf")
public interface IExmlBase extends IRepository {
    /**
     * @return the CMS nodes composition indexes.
     * @throws IndexException in case of index access error.
     */
    @objid ("fd21f505-5986-11e1-991a-001ec947ccaf")
    ICmsNodeIndex getCmsNodeIndex() throws IndexException;

    /**
     * @return the loaded objects cache
     */
    @objid ("fd1f92c1-5986-11e1-991a-001ec947ccaf")
    MObjectCache getLoadCache();

    /**
     * Load the given CMS node if not already loaded.
     * @param obj a CMS node ID .
     * @param modelLoader the model loader to use.
     * @param force true to always reload the node, false to load it only if not already done.
     * @return the loaded CMS node.
     * @throws DuplicateObjectException if a duplicate object is detected in many repositories.
     */
    @objid ("fd1f92d4-5986-11e1-991a-001ec947ccaf")
    SmObjectImpl loadCmsNode(ObjId obj, IModelLoader modelLoader, boolean force) throws DuplicateObjectException;

    /**
     * Force reload the given CMS node.
     * <p>
     * In case of failure the CMS node will be set as <i>shell</i>.
     * @param obj a CMS node model object.
     * @param modelLoader the model loader to use.
     * @throws DuplicateObjectException if a duplicate object is detected in many repositories.
     */
    @objid ("fd21f4c8-5986-11e1-991a-001ec947ccaf")
    void reloadCmsNode(final SmObjectImpl obj, IModelLoader modelLoader) throws DuplicateObjectException;

    /**
     * Remove the model object from the repository.
     * @param object the model object to remove from the base.
     * @throws IOException in case of I/O error.
     */
    @objid ("fd21f527-5986-11e1-991a-001ec947ccaf")
    void removeObject(final SmObjectImpl object) throws IOException;

    /**
     * Get the already loaded object.
     * @param id the object ID.
     * @return the loaded object or <code>null</code>.
     */
    @objid ("fd21f52e-5986-11e1-991a-001ec947ccaf")
    SmObjectImpl getLoadedObject(final ObjId id);

    /**
     * Get the EMF Resource adapter.
     * @return the EMF Resource.
     */
    @objid ("4b16d903-c065-11e1-b511-001ec947ccaf")
    Resource getEmfResource();

    /**
     * Create and register a new storage handler for the given CMS node
     * @param cmsNode a CMs node
     * @param isNodeLoaded <code>true</code> if the node is already loaded, else <code>false</code>
     * @return the new storage handler.
     */
    @objid ("67aefb44-2e7b-11e2-8aaa-001ec947ccaf")
    ExmlStorageHandler createStorageHandler(SmObjectImpl cmsNode, boolean isNodeLoaded);

    /**
     * Tells whether the given object is stored in this repository.
     * @param id the object id
     * @return <code>true</code> if the object is in the repository else <code>false</code>.
     */
    @objid ("67aefb49-2e7b-11e2-8aaa-001ec947ccaf")
    boolean isStored(ObjId id);

    /**
     * Get the model loader provider.
     * @return a loading session.
     */
    @objid ("73a67de2-43a3-11e2-91c9-001ec947ccaf")
    IModelLoaderProvider getModelLoaderProvider();

    /**
     * Declare the indexes as damaged.
     * @param e the index error.
     */
    @objid ("e7e4ee21-55ba-11e2-81b0-001ec947ccaf")
    void setIndexesDamaged(Exception e);

    /**
     * Tells whether the object has been detached from this repository since last save.
     * @param id the object identifier
     * @return the object if it has been detached from this repository .
     */
    @objid ("be62e324-e7c9-427e-878a-60a327ad90b8")
    SmObjectImpl getDetachedObject(ObjId id);

    /**
     * Find an object from its ID in the repository.
     * @param id an object ID.
     * @param modelLoader the model loader to use to load the model object
     * @return the found model object or <code>null</code>.
     * @throws DuplicateObjectException if another object with the same identifier already exists
     * in another repository.
     * @throws IndexException if the index is broken
     * @throws IllegalReferenceException in case of problem with the related object.
     */
    @objid ("9922f96b-a6ca-46bf-843e-fa3cfb58f12b")
    SmObjectImpl findByObjId(final ObjId id, IModelLoader modelLoader) throws DuplicateObjectException, IndexException, IllegalReferenceException;

    /**
     * Unload and forget the given object.
     * @param obj the object to unload
     */
    @objid ("ef33536b-3ce1-43eb-afde-6c1dc1fa4f57")
    void unloadObject(SmObjectImpl obj);

}
