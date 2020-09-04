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
package org.modelio.metamodel.uml.infrastructure.matrix;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;

/**
 * MatrixDefinition v0.0.9054
 * 
 * 
 * Definition of a matrix.
 * 
 * A matrix is defined by a lines columns and the value for a line and a column.
 * Line and column content are each defined by a query definition.
 * The matrix values are defined by a MatrixValueDefniition.
 */
@objid ("8a02f9a1-ae38-48d8-8f92-04ce8b6edc5c")
public interface MatrixDefinition extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("6eca811e-58f7-4373-a815-6aee1bb67a1b")
    public static final String MNAME = "MatrixDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("181b5ea4-d694-4bb9-add6-f9eef21bc8b2")
    public static final String MQNAME = "Infrastructure.MatrixDefinition";

    /**
     * Getter for relation 'MatrixDefinition->LinesDefinition'
     * 
     * Metamodel description:
     * <i>Lines definition.</i>
     */
    @objid ("3b653581-d361-42d4-be84-4f8aa19209b1")
    QueryDefinition getLinesDefinition();

    /**
     * Setter for relation 'MatrixDefinition->LinesDefinition'
     * 
     * Metamodel description:
     * <i>Lines definition.</i>
     */
    @objid ("87c51eb8-6234-4764-a993-4351e7435ddd")
    void setLinesDefinition(QueryDefinition value);

    /**
     * Getter for relation 'MatrixDefinition->ColumnsDefinition'
     * 
     * Metamodel description:
     * <i>Columns definition. If not specified, the line definition is used.</i>
     */
    @objid ("eb5febca-d6cb-4d46-970b-131524205935")
    QueryDefinition getColumnsDefinition();

    /**
     * Setter for relation 'MatrixDefinition->ColumnsDefinition'
     * 
     * Metamodel description:
     * <i>Columns definition. If not specified, the line definition is used.</i>
     */
    @objid ("7946dfcf-7269-4433-82da-992a5c4eb9b2")
    void setColumnsDefinition(QueryDefinition value);

    /**
     * Getter for relation 'MatrixDefinition->ValuesDefinition'
     * 
     * Metamodel description:
     * <i>Matrix values definition.</i>
     */
    @objid ("445ff945-6092-4ac5-85ed-4cf392152791")
    MatrixValueDefinition getValuesDefinition();

    /**
     * Setter for relation 'MatrixDefinition->ValuesDefinition'
     * 
     * Metamodel description:
     * <i>Matrix values definition.</i>
     */
    @objid ("e83d0483-b7b6-4a3c-aa1e-209440fa8642")
    void setValuesDefinition(MatrixValueDefinition value);

    /**
     * Getter for relation 'MatrixDefinition->DepthDefinition'
     * 
     * Metamodel description:
     * <i>Depth definition. May be left undefined.</i>
     */
    @objid ("a31ef6e6-6539-44a7-aa44-3a3d6b1f5603")
    QueryDefinition getDepthDefinition();

    /**
     * Setter for relation 'MatrixDefinition->DepthDefinition'
     * 
     * Metamodel description:
     * <i>Depth definition. May be left undefined.</i>
     */
    @objid ("2c204a5a-97e3-47df-bc9e-9df333172be6")
    void setDepthDefinition(QueryDefinition value);

    /**
     * Getter for relation 'MatrixDefinition->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b367827f-e35f-43a1-87d6-744a4c7ffb42")
    ModelElement getOwner();

    /**
     * Setter for relation 'MatrixDefinition->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b5b3d212-22d3-45f4-b5a9-b4f7bbdd76c6")
    void setOwner(ModelElement value);

}
