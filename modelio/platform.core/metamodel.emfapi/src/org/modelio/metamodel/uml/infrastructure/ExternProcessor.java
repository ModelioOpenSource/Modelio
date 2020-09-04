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
    @objid ("44a30b41-68a3-4902-9003-bc692c053dda")
    public static final String MNAME = "ExternProcessor";

    /**
     * The metaclass qualified name.
     */
    @objid ("f7efe0a3-13a7-46c9-8656-15dfa9c1b0d8")
    public static final String MQNAME = "Infrastructure.ExternProcessor";

    /**
     * Getter for attribute 'ExternProcessor.ClassName'
     * 
     * Metamodel description:
     * <i>Class name of the external processor.
     * Should be a complete Java class name.</i>
     */
    @objid ("10df33e6-59ba-41c1-b0f2-fc7885c96026")
    String getClassName();

    /**
     * Setter for attribute 'ExternProcessor.ClassName'
     * 
     * Metamodel description:
     * <i>Class name of the external processor.
     * Should be a complete Java class name.</i>
     */
    @objid ("081d60fd-2c49-4118-8916-f6fd6d3ccbb9")
    void setClassName(String value);

    /**
     * Getter for relation 'ExternProcessor->OwnerQuery'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8210d371-f914-4c04-ad0c-fa165e24b39d")
    QueryDefinition getOwnerQuery();

    /**
     * Setter for relation 'ExternProcessor->OwnerQuery'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9d6259cd-9f0c-4443-91af-8476371a235a")
    void setOwnerQuery(QueryDefinition value);

    /**
     * Getter for relation 'ExternProcessor->OwnerValDef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c101a805-6a4a-4c86-9fe5-08f576594a41")
    MatrixValueDefinition getOwnerValDef();

    /**
     * Setter for relation 'ExternProcessor->OwnerValDef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9ad53082-baec-4255-9bfa-080cf538e2df")
    void setOwnerValDef(MatrixValueDefinition value);

}
