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

package org.modelio.vstore.exml.common;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vstore.exml.common.index.IndexException;
import org.modelio.vstore.exml.common.model.IllegalReferenceException;
import org.modelio.vstore.exml.common.model.ObjId;
import org.modelio.vstore.exml.common.model.ObjIdName;

/**
 * Services needed by EXML loader implementations..
 * <p>
 * Not to be used outside the EXML repository implementation.
 */
@objid ("fd26ba15-5986-11e1-991a-001ec947ccaf")
public interface ILoadHelper {
    /**
     * Instantiates a new storage handler for the given CMS node.
     * @param cmsNode the CMS node object.
     * @param isNodeLoaded <code>true</code> if the node is already or being loaded,
     * <code>false</code> if the object needs to be loaded on access.
     * @return the new storage handler.
     */
    @objid ("fd21f52f-5986-11e1-991a-001ec947ccaf")
    ExmlStorageHandler createStorageHandler(SmObjectImpl cmsNode, final boolean isNodeLoaded);

    /**
     * Tells whether the given object is stored in this repository.
     * @param id the object id
     * @return <code>true</code> if the object is in the repository else <code>false</code>.
     */
    @objid ("cf9f38e1-d73c-11e1-adbb-001ec947ccaf")
    boolean isStored(ObjId id);

    /**
     * Get a reference to an object that may be loaded later.
     * <p>
     * The returned object may come from another repository.
     * @param modelLoader the model loading API to use.
     * @param id the object ID
     * @return the found or created object
     * @throws org.modelio.vcore.model.DuplicateObjectException if another object with the same identifier already exists in another repository
     * @throws org.modelio.vstore.exml.common.model.IllegalReferenceException if 'id' or 'pid' is not a legal reference
     * @throws org.modelio.vstore.exml.common.index.IndexException if indexes are broken
     */
    @objid ("679e4b68-2e7b-11e2-8aaa-001ec947ccaf")
    SmObjectImpl getRefObject(IModelLoader modelLoader, ObjIdName id) throws DuplicateObjectException, IllegalReferenceException, IndexException;

    /**
     * Creates an object that will be loaded by the caller.
     * @param modelLoader the model loading API to use.
     * @param id the object ID
     * @param handler the storage handler to put. If <i>null</i> the object will have no defined storage handler.
     * @return the created object
     * @throws org.modelio.vcore.model.DuplicateObjectException if another object with the same identifier already exists in another repository
     */
    @objid ("679e4b6e-2e7b-11e2-8aaa-001ec947ccaf")
    SmObjectImpl createObject(IModelLoader modelLoader, ObjId id, ExmlStorageHandler handler) throws DuplicateObjectException;

    /**
     * Find the loaded object with the given identifier.
     * @param id an identifier
     * @return the found object or <code>null</code> if no such object is loaded.
     */
    @objid ("679e4b73-2e7b-11e2-8aaa-001ec947ccaf")
    SmObjectImpl getLoadedObject(final ObjId id);

    /**
     * Set an attribute value.
     * @param modelLoader the API to use to set the attribute
     * @param obj an object
     * @param attName an attribute name
     * @param attValue the attribute value
     */
    @objid ("679e4b78-2e7b-11e2-8aaa-001ec947ccaf")
    void doLoadAtt(IModelLoader modelLoader, SmObjectImpl obj, String attName, String attValue);

    /**
     * Find an object in other repositories.
     * @param modelLoader the API to use to load the object
     * @param id the object identifier
     * @return the found or created shell object
     */
    @objid ("679e4b7e-2e7b-11e2-8aaa-001ec947ccaf")
    SmObjectImpl getForeignObject(IModelLoader modelLoader, ObjIdName id);

    /**
     * Initialize the model object status flags.
     * <p>
     * <u><b>Note:</b></u> To be called <b>after</b> having loaded all attributes.
     * @param modelLoader the model loader to use to initialize flags.
     * @param obj the model object to initialize.
     */
    @objid ("e9685a58-3974-11e2-920a-001ec947ccaf")
    void initObjectFlags(IModelLoader modelLoader, SmObjectImpl obj);

    /**
     * Creates a stub object that will be loaded later.
     * <p>
     * Initialize its storage handler.
     * No other object with the given identifier must exist in memory.
     * @param pid the owner CMS node ID
     * @param modelLoader the model loading API to use.
     * @param id the object ID with its name.
     * @param loadNameFromIndex whether to look for the name of the object in the indexes.
     * If the name is not found then one in 'id' will be used.
     * @return the created object
     * @throws org.modelio.vcore.model.DuplicateObjectException if another object with the same identifier already exists in another repository
     * @throws org.modelio.vstore.exml.common.model.IllegalReferenceException if 'id' or 'pid' is not a legal reference
     * @throws org.modelio.vstore.exml.common.index.IndexException if the index is broken
     */
    @objid ("689117f9-ff79-42c7-9654-36fc82b02f4b")
    SmObjectImpl createStubObject(IModelLoader modelLoader, ObjIdName id, boolean loadNameFromIndex) throws DuplicateObjectException, IllegalReferenceException, IndexException;

    /**
     * Get a  metaclass with its name.
     * @param xclassof a metaclass name
     * @return the found SmClass or null.
     */
    @objid ("c3552de9-01f3-4e9b-be11-938598c28054")
    SmClass getSmClass(String xclassof);

    /**
     * Creates a stub CMS node object that will be loaded later.
     * <p>
     * Initialize its storage handler.
     * No other object with the given identifier must exist in memory.
     * @param modelLoader the model loading API to use.
     * @param id the object ID
     * @param defaultName the fallback name of the object if its name can't be found in indexes.
     * @return the created object
     * @throws org.modelio.vcore.model.DuplicateObjectException if another object with the same identifier already exists in another repository
     */
    @objid ("b6b0eca8-2700-4dc1-9b72-d5220f5ad055")
    SmObjectImpl createStubCmsNode(IModelLoader modelLoader, ObjId id, String defaultName) throws DuplicateObjectException;

    /**
     * @param obj a model object, loaded or not.
     * @return the CMS node storing the given object or null if the object is not stored in the repository.
     * @throws org.modelio.vstore.exml.common.index.IndexException if the indexes are broken
     */
    @objid ("884db01a-4f54-464e-9197-8601f2ee6a01")
    ObjId getCmsNodeId(ObjId obj) throws IndexException;

    /**
     * Method to be called when loading fails with an exception.
     * <p>
     * Fires a warning to repository monitors and set the object as shell.
     * @param obj the CMS node unable to be loaded.
     * @param modelLoader the model loader
     * @param e the exception
     */
    @objid ("82157688-1438-4320-9ad1-8f3fe1226557")
    void loadFailed(SmObjectImpl obj, IModelLoader modelLoader, Exception e);

    /**
     * Load the name of the given object from indexes and return it in a {@link ObjIdName}.
     * <p>
     * In case of loading error, the name is set to the empty string.
     * @param id an object identifier
     * @return an object identifier with its name loaded.
     */
    @objid ("b154b872-c4e7-462d-b861-2137d1d16877")
    ObjIdName withNameFromIndex(ObjId id);

}
