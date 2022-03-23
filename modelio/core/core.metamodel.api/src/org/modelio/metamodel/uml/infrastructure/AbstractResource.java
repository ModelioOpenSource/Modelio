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
/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/

package org.modelio.metamodel.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * AbstractResource v2.1.00
 * 
 * 
 * null
 */
@objid ("99a0b4ef-1e10-4e11-82bd-308aa634b06b")
public interface AbstractResource extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("cd6e89f4-a49d-45c4-83f0-9d43b1a47b43")
    public static final String MNAME = "AbstractResource";

    /**
     * The metaclass qualified name.
     */
    @objid ("2923908a-f104-42f8-9f77-c91629cb8561")
    public static final String MQNAME = "Infrastructure.AbstractResource";

    /**
     * Get access to the represented resource.
     * <p>
     * Returns null if the element is not yet initialized.
     * @return an access to the represented resource.
     */
    @objid ("d7140571-f25e-4d08-a546-559779cffcc1")
    IResourceHandle getHandle();

    /**
     * Initialize the element to embed a resource.
     * <p>
     * Use the returned handle to store the resource content.
     * @param fileName a file name that will be used in case of file extraction.
     * @return a handle to write the resource content.
     */
    @objid ("35c9e67b-83fe-43b7-9af1-f290060c466b")
    IResourceHandle createEmbeddedResource(String fileName);

    /**
     * Initialize the element to reference an external resource.
     * <p>
     * Use the returned handle to access the external resource content.
     * @param resourceLocation The resource location. Must be either an absolute file path or an {@link java.net.URI}.
     * @return a handle to access the resource content.
     */
    @objid ("b569805f-2cfe-44f3-9b14-553f2b96e08a")
    IResourceHandle createExternalResource(String resourceLocation);

    /**
     * Tells whether the resource is embedded with the model or is an external reference.
     * <p>
     * Embedded resources are stored with the model and are managed in version if the model is
     * in a SVN repository.
     * @return whether the resource is embedded.
     */
    @objid ("81b69c09-0325-435f-a967-967c7ba7128f")
    boolean isEmbedded();

    /**
     * Getter for attribute 'AbstractResource.MimeType'
     * 
     * Metamodel description:
     * <i>MIME type of the rich note.</i>
     */
    @objid ("422e5f5a-5c36-4726-ba4a-407cbd7f0076")
    String getMimeType();

    /**
     * Setter for attribute 'AbstractResource.MimeType'
     * 
     * Metamodel description:
     * <i>MIME type of the rich note.</i>
     */
    @objid ("3c2c06f3-04ec-41b6-bd42-7eef7ad4f91f")
    void setMimeType(String value);

    /**
     * Getter for attribute 'AbstractResource.storageInfo'
     * 
     * Metamodel description:
     * <i><p>Internal informations about where the resource is located and/or where it can be extracted.</p>
     * </i>
     */
    @objid ("199c6d8c-c760-42f9-a0d1-78c3281a51e2")
    String getStorageInfo();

    /**
     * Setter for attribute 'AbstractResource.storageInfo'
     * 
     * Metamodel description:
     * <i><p>Internal informations about where the resource is located and/or where it can be extracted.</p>
     * </i>
     */
    @objid ("446aa24f-f97b-4247-8950-ac0d781216aa")
    void setStorageInfo(String value);

    /**
     * Getter for relation 'AbstractResource->Type'
     * 
     * Metamodel description:
     * <i>Semantic function of the rich note.</i>
     */
    @objid ("85bfb9c6-0060-4710-b3b6-267df8454fbb")
    ResourceType getType();

    /**
     * Setter for relation 'AbstractResource->Type'
     * 
     * Metamodel description:
     * <i>Semantic function of the rich note.</i>
     */
    @objid ("f8bcb203-c521-41ba-a8b7-9094b802b650")
    void setType(ResourceType value);

    /**
     * Getter for relation 'AbstractResource->Subject'
     * 
     * Metamodel description:
     * <i>Model element owning the rich note.</i>
     */
    @objid ("55667236-6342-4a75-ba1e-f94ffa951865")
    ModelElement getSubject();

    /**
     * Setter for relation 'AbstractResource->Subject'
     * 
     * Metamodel description:
     * <i>Model element owning the rich note.</i>
     */
    @objid ("893518a4-2501-4d0a-b07d-4e2cfef4c1f1")
    void setSubject(ModelElement value);

}
