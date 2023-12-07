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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * AbstractResource v2.1.00
 * 
 * 
 * null
 * 
 * 
 */
@objid ("99a0b4ef-1e10-4e11-82bd-308aa634b06b")
public interface AbstractResource extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("0686789b-811d-4cb7-8116-6f95aec1e9ef")
    public static final String MNAME = "AbstractResource";

    /**
     * The metaclass qualified name.
     */
    @objid ("58f0444f-2ef5-4979-97ee-130b4b0477d0")
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
     * 
     */
    @objid ("2611729b-9048-4183-b479-0a35b0a277eb")
    String getMimeType();

    /**
     * Setter for attribute 'AbstractResource.MimeType'
     * 
     * Metamodel description:
     * <i>MIME type of the rich note.</i>
     * 
     */
    @objid ("54133575-5bad-48ef-ba13-199c5e7c1942")
    void setMimeType(String value);

    /**
     * Getter for attribute 'AbstractResource.storageInfo'
     * 
     * Metamodel description:
     * <i><p>Internal informations about where the resource is located and/or where it can be extracted.</p>
     * </i>
     * 
     */
    @objid ("240f687f-8baa-44d1-885b-65ad74386695")
    String getStorageInfo();

    /**
     * Setter for attribute 'AbstractResource.storageInfo'
     * 
     * Metamodel description:
     * <i><p>Internal informations about where the resource is located and/or where it can be extracted.</p>
     * </i>
     * 
     */
    @objid ("71ae6bed-0858-4f93-89c5-c9825d23b83c")
    void setStorageInfo(String value);

    /**
     * Getter for relation 'AbstractResource->Type'
     * 
     * Metamodel description:
     * <i>Semantic function of the rich note.</i>
     * 
     */
    @objid ("78fbc29e-f3fc-4793-922f-ac4f5b460175")
    ResourceType getType();

    /**
     * Setter for relation 'AbstractResource->Type'
     * 
     * Metamodel description:
     * <i>Semantic function of the rich note.</i>
     * 
     */
    @objid ("c82465b8-ef51-4b0e-a215-7eb65adfba75")
    void setType(ResourceType value);

    /**
     * Getter for relation 'AbstractResource->Subject'
     * 
     * Metamodel description:
     * <i>Model element owning the rich note.</i>
     * 
     */
    @objid ("85ed967a-2bb1-4195-8f69-61c07be7bc3a")
    ModelElement getSubject();

    /**
     * Setter for relation 'AbstractResource->Subject'
     * 
     * Metamodel description:
     * <i>Model element owning the rich note.</i>
     * 
     */
    @objid ("aa465bd6-5086-4f7a-bd64-73e5df09bbd3")
    void setSubject(ModelElement value);
}

