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
package org.modelio.vcore.session.api.blob;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Support to relate blobs to model objects.
 * <p>
 * Allows to register blobs providers that tell which blobs are related
 * to a model object.
 * <p>
 * A blob related to a model object will be deleted with the model object on save.
 */
@objid ("f18539a7-a05a-4ba5-be4e-dee31bb40141")
public interface IBlobSupport {
    /**
     * Add a blob change listener.
     * <p>
     * Blob change listeners are triggered when {@link #fireBlobsChanged(IBlobChangeEvent)} is called.
     * @param listener a blob change listener.
     */
    @objid ("bd38cadb-81c7-4b06-a161-38df0ee5430c")
    void addBlobChangeListener(IBlobChangeListener listener);

    /**
     * Add a Blob provider.
     * <p>
     * Blob providers are asked for blobs related to CMS nodes to make the same CMS operation
     * as the related element.
     * @param provider a blob provider.
     */
    @objid ("8b45b40b-1dca-48af-bc39-21236a2dfe11")
    void addBlobProvider(IBlobProvider provider);

    /**
     * Get all blobs related to a given model object.
     * <p>
     * Asks all registered blob providers.
     * @param obj a model object
     * @return all related blobs
     */
    @objid ("08f99c7b-920f-43ac-85c0-2ba15143ab1a")
    Collection<String> getRelatedBlobs(MObject obj);

    /**
     * Fires all blob providers a blob change event.
     * @param event a blob change event to fire.
     */
    @objid ("a924598b-13d7-4b59-8fd2-1d596fdaa0c9")
    void fireBlobsChanged(IBlobChangeEvent event);

    /**
     * Called when a model object has been copied or imported.
     * <p>
     * Both objects may belong to the same project or to different ones.
     * The model objects may have the same identifier in case of import or
     * different identifiers in case of copy.
     * <p>
     * The implementation should decide what to do with the blobs it handles.
     * An implementation usually duplicate the blobs of the original object.
     * @param from the original model object
     * @param to the model object copy.
     */
    @objid ("11abba18-401b-4051-ac73-abe1dc913636")
    void fireObjectCopied(MObject from, MObject to);

    /**
     * Remove a blob change listener.
     * @param listener a blob change listener.
     */
    @objid ("423d200e-3480-462b-9ee5-777d8f089fb0")
    void removeBlobChangeListener(IBlobChangeListener listener);

    /**
     * Remove a blob provider.
     * @param provider a blob provider.
     */
    @objid ("df621722-4dee-43d9-9096-1ec11eac77cf")
    void removeBlobProvider(IBlobProvider provider);

    /**
     * Called when model objects have been moved to another repository.
     * <p>
     * The implementation should decide what to do with the blobs it handles.
     * An implementation usually moves the blobs of the moved object to the destination repository.
     * @param objs the moved model objects. the model object is already in the new repository.
     * @param fromRepo its previous repository
     * @param destRepo its new repository.
     */
    @objid ("ef800a0f-f06d-4117-8e82-9fdca2daab7e")
    void fireObjectsMoved(Collection<? extends MObject> objs, IRepository fromRepo, IRepository destRepo);
}

