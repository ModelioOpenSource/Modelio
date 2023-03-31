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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Information about a blob.
 * <p>
 * Contains the unique identifier of the blob and a user friendly label.
 */
@objid ("9d1ae9a5-a7cd-4eb5-81fb-9cfdc8b6263b")
public interface IBlobInfo {
    /**
     * Get the blob key.
     * <p>
     * The key is the unique identifier for a blob in a repository.
     * @return the blob key.
     */
    @objid ("b98b44c8-cc81-40db-927b-edea8497687c")
    String getKey();

    /**
     * Get the blob label.
     * <p>
     * This label is meant to be displayed to the user.
     * Must not return <code>null</code>.
     * @return the blob label.
     * @deprecated since 3.7, not used anymore
     */
    @objid ("94149fc6-ffc6-44ce-81ff-a234f5642da9")
    @Deprecated
    String getLabel();

    /**
     * Get the model object this blob is related to.
     * <p>
     * The blob is expected to be deleted with its model object on save.
     * @return the related model object.
     * @since 3.7
     */
    @objid ("af5cf825-2f0c-42f0-a704-8e0e8c423da5")
    MRef getRelatedElement();

    /**
     * Get the name of the blob, identify it among the blobs related to a same model object.
     * <p>
     * Returns the key if the blob is not related to a model object.
     * @return the local name.
     * @since 3.7
     */
    @objid ("f2f4e6a7-a6ae-4bee-b584-457a181c5c15")
    String getLocalName();
}

