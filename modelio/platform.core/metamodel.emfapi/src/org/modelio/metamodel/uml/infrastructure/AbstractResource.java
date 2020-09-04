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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ResourceType;

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
    @objid ("85b0318d-c64c-4b59-aa95-5ca0d1881bb0")
    public static final String MNAME = "AbstractResource";

    /**
     * The metaclass qualified name.
     */
    @objid ("2d587f33-2d85-4453-b908-744ddab338f8")
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
    @objid ("8fb7d078-1249-406c-9d5e-8dce1a7b3b8e")
    String getMimeType();

    /**
     * Setter for attribute 'AbstractResource.MimeType'
     * 
     * Metamodel description:
     * <i>MIME type of the rich note.</i>
     */
    @objid ("6c103e00-2479-4bd9-bd53-e7be89109746")
    void setMimeType(String value);

    /**
     * Getter for attribute 'AbstractResource.storageInfo'
     * 
     * Metamodel description:
     * <i><p>Internal informations about where the resource is located and/or where it can be extracted.</p>
     * </i>
     */
    @objid ("e23e2258-7028-4aa0-a20f-0f1c379cf0f1")
    String getStorageInfo();

    /**
     * Setter for attribute 'AbstractResource.storageInfo'
     * 
     * Metamodel description:
     * <i><p>Internal informations about where the resource is located and/or where it can be extracted.</p>
     * </i>
     */
    @objid ("22acc68e-302a-43ee-b7e2-74048fa9fd30")
    void setStorageInfo(String value);

    /**
     * Getter for relation 'AbstractResource->Type'
     * 
     * Metamodel description:
     * <i>Semantic function of the rich note.</i>
     */
    @objid ("8b95b59a-6032-4d3d-8050-b9146eb41b38")
    ResourceType getType();

    /**
     * Setter for relation 'AbstractResource->Type'
     * 
     * Metamodel description:
     * <i>Semantic function of the rich note.</i>
     */
    @objid ("73e97e2a-d718-44e4-8ada-477b605a2622")
    void setType(ResourceType value);

    /**
     * Getter for relation 'AbstractResource->Subject'
     * 
     * Metamodel description:
     * <i>Model element owning the rich note.</i>
     */
    @objid ("9dfd6cfe-c83c-40a4-b9a5-f8754de65293")
    ModelElement getSubject();

    /**
     * Setter for relation 'AbstractResource->Subject'
     * 
     * Metamodel description:
     * <i>Model element owning the rich note.</i>
     */
    @objid ("fd6c7788-c16a-4b6a-ab42-dfb76d4b0902")
    void setSubject(ModelElement value);

}
