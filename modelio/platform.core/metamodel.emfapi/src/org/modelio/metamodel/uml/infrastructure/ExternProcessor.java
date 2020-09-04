/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;

/**
 * ExternProcessor v0.0.9054
 * 
 * 
 * Represents an external processor.
 * 
 * An external processor references a java class that can be executed.
 */
@objid ("e5bf50a8-5a3e-4c5c-9bf8-83310c530ee3")
public interface ExternProcessor extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("fcca26f6-ed7a-4c39-8ebe-b385d6d7aa49")
    public static final String MNAME = "ExternProcessor";

    /**
     * The metaclass qualified name.
     */
    @objid ("79b659a1-be35-4da9-a544-6dcb59f8fed6")
    public static final String MQNAME = "Infrastructure.ExternProcessor";

    /**
     * Getter for attribute 'ExternProcessor.ClassName'
     * 
     * Metamodel description:
     * <i>Class name of the external processor.
     * Should be a complete Java class name.</i>
     */
    @objid ("21f5bedb-396b-433a-a8b6-a1ed8ab351a2")
    String getClassName();

    /**
     * Setter for attribute 'ExternProcessor.ClassName'
     * 
     * Metamodel description:
     * <i>Class name of the external processor.
     * Should be a complete Java class name.</i>
     */
    @objid ("2592b1db-7c96-43a1-b6bd-9c0400d72fff")
    void setClassName(String value);

    /**
     * Getter for relation 'ExternProcessor->OwnerQuery'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2aa6c5f4-b956-4383-856c-65a5c4c363e9")
    QueryDefinition getOwnerQuery();

    /**
     * Setter for relation 'ExternProcessor->OwnerQuery'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c1d44022-8b6b-4ace-933e-7005c727af5e")
    void setOwnerQuery(QueryDefinition value);

    /**
     * Getter for relation 'ExternProcessor->OwnerValDef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a200c188-40ef-4cae-9b41-65a4ac4859db")
    MatrixValueDefinition getOwnerValDef();

    /**
     * Setter for relation 'ExternProcessor->OwnerValDef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("185ba874-8710-4d91-85a7-2b78f7f66c3b")
    void setOwnerValDef(MatrixValueDefinition value);

}
