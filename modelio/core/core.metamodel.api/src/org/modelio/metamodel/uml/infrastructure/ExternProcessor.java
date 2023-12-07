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
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;

/**
 * ExternProcessor v0.0.9054
 * 
 * 
 * Represents an external processor.
 * 
 * An external processor references a java class that can be executed.
 * 
 * 
 */
@objid ("e5bf50a8-5a3e-4c5c-9bf8-83310c530ee3")
public interface ExternProcessor extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("e3fb65ad-71ed-4135-992a-3a141d660344")
    public static final String MNAME = "ExternProcessor";

    /**
     * The metaclass qualified name.
     */
    @objid ("26c97cc4-64a3-4959-bd1c-fc13fa35d1b6")
    public static final String MQNAME = "Infrastructure.ExternProcessor";

    /**
     * Getter for attribute 'ExternProcessor.ClassName'
     * 
     * Metamodel description:
     * <i>Class name of the external processor.
     * Should be a complete Java class name.</i>
     * 
     */
    @objid ("1cd9ced8-6e9a-486d-a688-2f2df4202072")
    String getClassName();

    /**
     * Setter for attribute 'ExternProcessor.ClassName'
     * 
     * Metamodel description:
     * <i>Class name of the external processor.
     * Should be a complete Java class name.</i>
     * 
     */
    @objid ("aebbce84-aed8-4838-92b8-5b30e8658adc")
    void setClassName(String value);

    /**
     * Getter for relation 'ExternProcessor->OwnerQuery'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("a2ce6685-f1bc-4928-8df6-e2aafa749523")
    QueryDefinition getOwnerQuery();

    /**
     * Setter for relation 'ExternProcessor->OwnerQuery'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("c20cecba-3611-4848-a12a-9caf169d5ca2")
    void setOwnerQuery(QueryDefinition value);

    /**
     * Getter for relation 'ExternProcessor->OwnerValDef'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("51f19079-e7ec-45e9-81d7-563588c6c682")
    MatrixValueDefinition getOwnerValDef();

    /**
     * Setter for relation 'ExternProcessor->OwnerValDef'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("6fb28cdf-6451-4a68-bbce-e9af64c27798")
    void setOwnerValDef(MatrixValueDefinition value);
}

