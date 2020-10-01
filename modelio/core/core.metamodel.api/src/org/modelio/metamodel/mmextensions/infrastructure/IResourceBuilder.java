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

package org.modelio.metamodel.mmextensions.infrastructure;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Resource;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vbasic.auth.IAuthData;

/**
 * Attached resource element builder.
 * <p>
 * Builds {@link Document} and {@link Resource} elements.
 * 
 * @author cma
 * @since 3.7
 */
@objid ("7fe5ebe1-8a3c-45c2-b0c5-43a4f9a1acf2")
public interface IResourceBuilder {
    /**
     * @return the created document.
     */
    @objid ("ef0a41eb-e205-43eb-bccc-d15acc87a92e")
    Document createDocumentReference();

    /**
     * Creates the embedded document.
     * 
     * @return the created document.
     * @throws java.io.IOException if embedding fails with an I/O error.
     */
    @objid ("53558423-3c18-4c6a-926b-449c537d92af")
    Document createEmbeddedDocument() throws IOException;

    /**
     * Creates the embedded resource.
     * 
     * @return the created resource.
     * @throws java.io.IOException if embedding fails with an I/O error.
     */
    @objid ("e8e74b45-a33d-4377-92d1-19628d1f22ec")
    Resource createEmbeddedResource() throws IOException;

    /**
     * @return the created resource.
     */
    @objid ("480f6e70-b166-476e-a341-393409a779c7")
    Resource createResourceReference();

    /**
     * @param file the represented file path
     * @return this builder.
     */
    @objid ("e1415d10-22d8-430b-b524-380cd372be7e")
    IResourceBuilder withFile(Path file);

    /**
     * @param mimeType the resource MIME type.
     * @return
     */
    @objid ("384f65ed-4851-4c12-a22d-46fb549b0371")
    IResourceBuilder withMimeType(String mimeType);

    /**
     * @param name The resource title.
     * @return this instance.
     */
    @objid ("581eeef6-a508-443f-a773-78a116c55ef5")
    IResourceBuilder withName(String name);

    @objid ("37f90bc5-c8b2-4988-bdfd-6e9486591696")
    IResourceBuilder withOwner(ModelElement owner);

    /**
     * Set the resource role.
     * 
     * @param moduleName the module name or regex pattern
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param typeName the role name
     * @return this builder
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException if the role was not found.
     * @throws java.lang.IllegalStateException if {@link #withOwner(ModelElement)} was not called before.
     */
    @objid ("799a7c4d-274a-4477-83fe-c33c337d1266")
    IResourceBuilder withRole(String moduleName, String ownerName, String typeName) throws ExtensionNotFoundException, IllegalStateException;

    /**
     * @param type the resource role.
     * @return this instance.
     */
    @objid ("63a986ce-7ca1-4772-9b75-9405ae62a0de")
    IResourceBuilder withRole(ResourceType type);

    /**
     * @param uri the represented resource URI.
     * @return this instance.
     */
    @objid ("14b6cc43-6e98-4d59-8c48-f2366940d1e1")
    IResourceBuilder withURI(URI uri);

    /**
     * use the given authentication data to at least probe the content type.
     * <p>
     * The password will never be stored in any case. The current implementation does not store the authentication data.
     * 
     * @param auth authentication data.
     * @return this builder.
     * @since 3.7.1
     */
    @objid ("51e9618c-14ea-44ec-bf7b-71d7a52a3445")
    IResourceBuilder withAuthData(IAuthData auth);

}
