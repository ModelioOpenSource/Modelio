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
 * ExternElement vnull
 * 
 * 
 * null
 * 
 * 
 */
@objid ("5baa6b80-135d-4087-b40c-42085acc4818")
public interface ExternElement extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("a7e5a833-e587-4fbc-bcbe-69d312b8dd9d")
    public static final String MNAME = "ExternElement";

    /**
     * The metaclass qualified name.
     */
    @objid ("bb0bbd15-3e6a-4013-878f-a348416f38b3")
    public static final String MQNAME = "Infrastructure.ExternElement";

    /**
     * Getter for attribute 'ExternElement.Provider'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("89852537-828e-4532-aa65-b84023755844")
    String getProvider();

    /**
     * Setter for attribute 'ExternElement.Provider'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("15e0e8b5-ce8a-459a-b386-797c278edb66")
    void setProvider(String value);

    /**
     * Getter for attribute 'ExternElement.ExternId'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("1a2673fd-f4f5-4c0b-bc10-1b0c3e5dbcd6")
    String getExternId();

    /**
     * Setter for attribute 'ExternElement.ExternId'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("5d7ce128-cfbc-4fa2-b570-239ad87f5b97")
    void setExternId(String value);

    /**
     * Getter for attribute 'ExternElement.Location'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("85c7702b-97d1-4c93-9037-6b330dd208ba")
    String getLocation();

    /**
     * Setter for attribute 'ExternElement.Location'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("d6cb67ae-770b-4063-bade-e5a07c1fabb2")
    void setLocation(String value);

    /**
     * Getter for relation 'ExternElement->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("e5de208a-ca42-4f6e-a79a-4dae59f87f7d")
    MethodologicalLink getOwner();

    /**
     * Setter for relation 'ExternElement->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("c926b021-653a-4f02-9395-9b8a21413fb6")
    void setOwner(MethodologicalLink value);
}

