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
 * Document v2.1.00
 * 
 * 
 * <p>Represents a rich note stored outside the database.</p><p>The rich note has a specified MIME type that allows choosing an adequate editor to modify it. It may have or not a path telling where it is stored. It may also have an abstract containing a resume, statistics or other informations telling what the document contains without having to open it.</p>
 */
@objid ("00924014-c4be-1fd8-97fe-001ec947cd2a")
public interface Document extends AbstractResource {
    /**
     * The metaclass simple name.
     */
    @objid ("d30b8988-d0f5-4458-ad53-b455588a8bb9")
    public static final String MNAME = "Document";

    /**
     * The metaclass qualified name.
     */
    @objid ("74a46bc2-3d9c-42e6-92c5-7f70f330dee8")
    public static final String MQNAME = "Infrastructure.Document";

    /**
     * Getter for attribute 'Document.Abstract'
     * 
     * Metamodel description:
     * <i>An Abstract/Resume of the rich note.
     * Tells what the rich note contains without having to open it.</i>
     */
    @objid ("6bed717c-6cff-4a1a-a8d5-4a38a8f9bc84")
    String getAbstract();

    /**
     * Setter for attribute 'Document.Abstract'
     * 
     * Metamodel description:
     * <i>An Abstract/Resume of the rich note.
     * Tells what the rich note contains without having to open it.</i>
     */
    @objid ("7b810d4f-6dfa-441b-971f-e446b26bbfea")
    void setAbstract(String value);

}
