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
    @objid ("1d522886-e7e6-4b32-99fb-58373aa71b8a")
    public static final String MNAME = "MatrixDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("e08615c7-bd1c-4860-b45c-0b51aa617b6d")
    public static final String MQNAME = "Infrastructure.MatrixDefinition";

    /**
     * Getter for relation 'MatrixDefinition->LinesDefinition'
     * 
     * Metamodel description:
     * <i>Lines definition.</i>
     */
    @objid ("501b871a-6265-4b8d-89ee-a40305e21a64")
    QueryDefinition getLinesDefinition();

    /**
     * Setter for relation 'MatrixDefinition->LinesDefinition'
     * 
     * Metamodel description:
     * <i>Lines definition.</i>
     */
    @objid ("e5262ee3-a68e-4e8e-9331-032683cb3d85")
    void setLinesDefinition(QueryDefinition value);

    /**
     * Getter for relation 'MatrixDefinition->ColumnsDefinition'
     * 
     * Metamodel description:
     * <i>Columns definition. If not specified, the line definition is used.</i>
     */
    @objid ("ef584a85-601d-4de6-9b1c-cd1af1cbcb2d")
    QueryDefinition getColumnsDefinition();

    /**
     * Setter for relation 'MatrixDefinition->ColumnsDefinition'
     * 
     * Metamodel description:
     * <i>Columns definition. If not specified, the line definition is used.</i>
     */
    @objid ("d4dd84bb-dfb6-4cc3-865d-068f08ca0d17")
    void setColumnsDefinition(QueryDefinition value);

    /**
     * Getter for relation 'MatrixDefinition->ValuesDefinition'
     * 
     * Metamodel description:
     * <i>Matrix values definition.</i>
     */
    @objid ("4247ed89-8511-41e5-b1cb-02577c177cbb")
    MatrixValueDefinition getValuesDefinition();

    /**
     * Setter for relation 'MatrixDefinition->ValuesDefinition'
     * 
     * Metamodel description:
     * <i>Matrix values definition.</i>
     */
    @objid ("7afe65d1-b305-49f6-92aa-5c3cb6c71453")
    void setValuesDefinition(MatrixValueDefinition value);

    /**
     * Getter for relation 'MatrixDefinition->DepthDefinition'
     * 
     * Metamodel description:
     * <i>Depth definition. May be left undefined.</i>
     */
    @objid ("d163693b-21cb-483e-b82d-5abae1771d2e")
    QueryDefinition getDepthDefinition();

    /**
     * Setter for relation 'MatrixDefinition->DepthDefinition'
     * 
     * Metamodel description:
     * <i>Depth definition. May be left undefined.</i>
     */
    @objid ("8598babc-858a-4171-8932-e18ae0c71fd8")
    void setDepthDefinition(QueryDefinition value);

    /**
     * Getter for relation 'MatrixDefinition->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7be08d85-0a86-448f-92cf-32188664d71a")
    ModelElement getOwner();

    /**
     * Setter for relation 'MatrixDefinition->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8d838504-8850-4f23-beb0-3549d65ecc24")
    void setOwner(ModelElement value);

}
