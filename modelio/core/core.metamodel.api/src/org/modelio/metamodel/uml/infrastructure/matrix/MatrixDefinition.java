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

package org.modelio.metamodel.uml.infrastructure.matrix;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * MatrixDefinition v0.0.9054
 * 
 * 
 * Definition of a matrix.
 * 
 * A matrix is defined by a lines columns and the value for a line and a column.
 * Line and column content are each defined by a query definition.
 * The matrix values are defined by a MatrixValueDefniition.
 * 
 * 
 * 
 */
@objid ("8a02f9a1-ae38-48d8-8f92-04ce8b6edc5c")
public interface MatrixDefinition extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("fe9d8312-441a-4390-a7f4-174ff8e60aea")
    public static final String MNAME = "MatrixDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("ab57e175-d966-4cab-a4c1-f4660952a14a")
    public static final String MQNAME = "Infrastructure.MatrixDefinition";

    /**
     * Getter for relation 'MatrixDefinition->LinesDefinition'
     * 
     * Metamodel description:
     * <i>Lines definition.</i>
     * 
     */
    @objid ("ca23aede-aeb0-44fa-8511-4f980cf3ba0e")
    QueryDefinition getLinesDefinition();

    /**
     * Setter for relation 'MatrixDefinition->LinesDefinition'
     * 
     * Metamodel description:
     * <i>Lines definition.</i>
     * 
     */
    @objid ("ccfa8c09-df2a-4f65-85c7-b766ebe92903")
    void setLinesDefinition(QueryDefinition value);

    /**
     * Getter for relation 'MatrixDefinition->ColumnsDefinition'
     * 
     * Metamodel description:
     * <i>Columns definition. If not specified, the line definition is used.</i>
     * 
     */
    @objid ("d7f6610c-76d6-4a1d-9fcb-245f99d62469")
    QueryDefinition getColumnsDefinition();

    /**
     * Setter for relation 'MatrixDefinition->ColumnsDefinition'
     * 
     * Metamodel description:
     * <i>Columns definition. If not specified, the line definition is used.</i>
     * 
     */
    @objid ("a59876c6-7bc4-482e-bf60-526aae3243bf")
    void setColumnsDefinition(QueryDefinition value);

    /**
     * Getter for relation 'MatrixDefinition->ValuesDefinition'
     * 
     * Metamodel description:
     * <i>Matrix values definition.</i>
     * 
     */
    @objid ("ecf75f94-f9c0-400f-b929-c65bf7557eb7")
    MatrixValueDefinition getValuesDefinition();

    /**
     * Setter for relation 'MatrixDefinition->ValuesDefinition'
     * 
     * Metamodel description:
     * <i>Matrix values definition.</i>
     * 
     */
    @objid ("a1a3633f-57db-4273-aded-42ed741b0a02")
    void setValuesDefinition(MatrixValueDefinition value);

    /**
     * Getter for relation 'MatrixDefinition->DepthDefinition'
     * 
     * Metamodel description:
     * <i>Depth definition. May be left undefined.</i>
     * 
     */
    @objid ("174d543c-a9a2-405c-9914-b39db9ebfd88")
    QueryDefinition getDepthDefinition();

    /**
     * Setter for relation 'MatrixDefinition->DepthDefinition'
     * 
     * Metamodel description:
     * <i>Depth definition. May be left undefined.</i>
     * 
     */
    @objid ("7ad13ce8-431c-4813-8182-fbfafed8754b")
    void setDepthDefinition(QueryDefinition value);

    /**
     * Getter for relation 'MatrixDefinition->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("87c47c90-0140-4828-9344-c825933c535b")
    ModelElement getOwner();

    /**
     * Setter for relation 'MatrixDefinition->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("e33a667f-06d7-424c-ae0d-48c75d8d0c64")
    void setOwner(ModelElement value);
}

