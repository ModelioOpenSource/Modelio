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
    @objid ("490fe0eb-7008-4b88-88e2-eff8fe8226b4")
    public static final String MNAME = "Document";

    /**
     * The metaclass qualified name.
     */
    @objid ("f6040f43-3258-46c1-999c-0ff0d6c54084")
    public static final String MQNAME = "Infrastructure.Document";

    /**
     * Getter for attribute 'Document.Abstract'
     * 
     * Metamodel description:
     * <i>An Abstract/Resume of the rich note.
     * Tells what the rich note contains without having to open it.</i>
     */
    @objid ("5bb00370-143a-406f-8eef-cc1eb82576c8")
    String getAbstract();

    /**
     * Setter for attribute 'Document.Abstract'
     * 
     * Metamodel description:
     * <i>An Abstract/Resume of the rich note.
     * Tells what the rich note contains without having to open it.</i>
     */
    @objid ("3bd075a4-195e-4b32-b37c-4efe4ea081ba")
    void setAbstract(String value);

}
