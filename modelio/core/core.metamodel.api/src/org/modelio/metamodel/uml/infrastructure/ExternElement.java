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
 * ExternElement vnull
 * 
 * 
 * null
 */
@objid ("5baa6b80-135d-4087-b40c-42085acc4818")
public interface ExternElement extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("c231d2d9-49f0-4706-a2bc-a0f23481807e")
    public static final String MNAME = "ExternElement";

    /**
     * The metaclass qualified name.
     */
    @objid ("1259aa41-c68f-430a-b0cd-30dfd619a2a9")
    public static final String MQNAME = "Infrastructure.ExternElement";

    /**
     * Getter for attribute 'ExternElement.Provider'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("13076b59-23b8-4bbe-bf57-a2515b84ec3b")
    String getProvider();

    /**
     * Setter for attribute 'ExternElement.Provider'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("27cc61d2-9a21-4e9b-a564-efa0af45050d")
    void setProvider(String value);

    /**
     * Getter for attribute 'ExternElement.ExternId'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("57c5fe6f-1435-4407-b574-4dec593a1e36")
    String getExternId();

    /**
     * Setter for attribute 'ExternElement.ExternId'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("72bdadda-713c-431c-9173-17c88b047f98")
    void setExternId(String value);

    /**
     * Getter for attribute 'ExternElement.Location'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d526c094-e6d6-4cb5-97e5-a30f69108ab6")
    String getLocation();

    /**
     * Setter for attribute 'ExternElement.Location'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("04d04758-f23e-4e14-9eb4-6e9045e5f9f0")
    void setLocation(String value);

    /**
     * Getter for relation 'ExternElement->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("229e3817-6440-4030-99c2-91c84cc1ddb9")
    MethodologicalLink getOwner();

    /**
     * Setter for relation 'ExternElement->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2c13bf5a-d1df-43f4-ac00-017192e8c89f")
    void setOwner(MethodologicalLink value);

}
