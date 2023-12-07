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
 * Document v2.1.00
 * 
 * 
 * <p>Represents a rich note stored outside the database.</p><p>The rich note has a specified MIME type that allows choosing an adequate editor to modify it. It may have or not a path telling where it is stored. It may also have an abstract containing a resume, statistics or other informations telling what the document contains without having to open it.</p>
 * 
 * 
 * 
 */
@objid ("00924014-c4be-1fd8-97fe-001ec947cd2a")
public interface Document extends AbstractResource {
    /**
     * The metaclass simple name.
     */
    @objid ("52b5a292-cbd3-49fb-b5ab-fdde37af2e1f")
    public static final String MNAME = "Document";

    /**
     * The metaclass qualified name.
     */
    @objid ("0d9e8a5b-f6d0-4fd8-99ed-83ed669ae98a")
    public static final String MQNAME = "Infrastructure.Document";

    /**
     * Getter for attribute 'Document.Abstract'
     * 
     * Metamodel description:
     * <i>An Abstract/Resume of the rich note.
     * Tells what the rich note contains without having to open it.</i>
     * 
     */
    @objid ("76425fd9-cf9a-4713-bfed-0ff9e80720d7")
    String getAbstract();

    /**
     * Setter for attribute 'Document.Abstract'
     * 
     * Metamodel description:
     * <i>An Abstract/Resume of the rich note.
     * Tells what the rich note contains without having to open it.</i>
     * 
     */
    @objid ("e2b1e9d1-d571-4c8a-abde-1a3dd70b089a")
    void setAbstract(String value);
}

