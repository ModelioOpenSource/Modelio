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
package org.modelio.metamodel.uml.infrastructure.matrix;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ExternProcessor;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;

/**
 * QueryDefinition v0.0.9054
 * 
 * 
 * Line or column query definition.
 * 
 * Uses an external processor to execute the query.
 */
@objid ("1b70521e-83b3-42a8-a6ca-a0b86b8dc3ea")
public interface QueryDefinition extends Element {
    /**
     * The metaclass simple name.
     */
    @objid ("9b8daa5c-0f15-4e2a-b7fe-2c965d17e893")
    public static final String MNAME = "QueryDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("476954fa-a610-45a0-83fd-3f2abe554d20")
    public static final String MQNAME = "Infrastructure.QueryDefinition";

    /**
     * Getter for attribute 'QueryDefinition.UsingAdditions'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("fd90cfc4-a0be-4b6a-93e9-60d5695707a6")
    boolean isUsingAdditions();

    /**
     * Setter for attribute 'QueryDefinition.UsingAdditions'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8ab64a96-ed3d-48c3-81c3-83a8498101ae")
    void setUsingAdditions(boolean value);

    /**
     * Getter for relation 'QueryDefinition->Added'
     * 
     * Metamodel description:
     * <i>Elements manually added to the lines or columns.</i>
     */
    @objid ("bd6fa3a6-2bfe-4bea-a66c-37a1c8b5eb62")
    EList<Element> getAdded();

    /**
     * Filtered Getter for relation 'QueryDefinition->Added'
     * 
     * Metamodel description:
     * <i>Elements manually added to the lines or columns.</i>
     */
    @objid ("74ae49a1-a568-4f5c-bf9e-7959124010b2")
    <T extends Element> List<T> getAdded(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'QueryDefinition->Processor'
     * 
     * Metamodel description:
     * <i>External processor used to execute the query.</i>
     */
    @objid ("9cfb53ec-d1c3-437e-90a6-84179f2b5c80")
    ExternProcessor getProcessor();

    /**
     * Setter for relation 'QueryDefinition->Processor'
     * 
     * Metamodel description:
     * <i>External processor used to execute the query.</i>
     */
    @objid ("35ed532d-d39a-4951-b878-bc69ab0fb91b")
    void setProcessor(ExternProcessor value);

    /**
     * Getter for relation 'QueryDefinition->Parameters'
     * 
     * Metamodel description:
     * <i>Parameters to pass to the external processor.</i>
     */
    @objid ("dcb9bede-f520-40f3-8c60-deb7b40d42aa")
    PropertyTable getParameters();

    /**
     * Setter for relation 'QueryDefinition->Parameters'
     * 
     * Metamodel description:
     * <i>Parameters to pass to the external processor.</i>
     */
    @objid ("cbb7ba11-f584-43ee-b54a-007b23ed05e6")
    void setParameters(PropertyTable value);

    /**
     * Getter for relation 'QueryDefinition->OwnerAsLine'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("04b3fd07-168f-4f22-ae6a-ac77dd3982be")
    MatrixDefinition getOwnerAsLine();

    /**
     * Setter for relation 'QueryDefinition->OwnerAsLine'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("cd322c10-39b0-4c34-9752-6bdaada969b6")
    void setOwnerAsLine(MatrixDefinition value);

    /**
     * Getter for relation 'QueryDefinition->OwnerAsCol'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d2592e13-21f4-4d03-ac5e-d3f8ea201c21")
    MatrixDefinition getOwnerAsCol();

    /**
     * Setter for relation 'QueryDefinition->OwnerAsCol'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0ebb8ab0-2f21-4fa7-915b-b027fce6a367")
    void setOwnerAsCol(MatrixDefinition value);

    /**
     * Getter for relation 'QueryDefinition->OwnerAsDepth'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2df118f5-cd4d-4608-afe9-21cb147766c6")
    MatrixDefinition getOwnerAsDepth();

    /**
     * Setter for relation 'QueryDefinition->OwnerAsDepth'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("05197d57-55a0-4022-b227-e923a1b84346")
    void setOwnerAsDepth(MatrixDefinition value);

}
