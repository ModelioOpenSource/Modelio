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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ExternProcessor;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;

/**
 * MatrixValueDefinition v0.0.9054
 * 
 * 
 * Definition of a matrix value.
 * 
 * References an external java processor able to compute a matrix value and to modify it.
 * 
 * 
 */
@objid ("6ae11939-6513-44a4-b535-1daf76e27f63")
public interface MatrixValueDefinition extends Element {
    /**
     * The metaclass simple name.
     */
    @objid ("55cab020-e34a-4c3f-a9c9-00c60cedd106")
    public static final String MNAME = "MatrixValueDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("e218d0a3-bd17-4fee-9e50-139d5570827f")
    public static final String MQNAME = "Infrastructure.MatrixValueDefinition";

    /**
     * Getter for relation 'MatrixValueDefinition->Processor'
     * 
     * Metamodel description:
     * <i>External java processor that displays and edit matrix values.</i>
     * 
     */
    @objid ("42fb65bc-8e7f-48ab-a9a2-a2bf3125870e")
    ExternProcessor getProcessor();

    /**
     * Setter for relation 'MatrixValueDefinition->Processor'
     * 
     * Metamodel description:
     * <i>External java processor that displays and edit matrix values.</i>
     * 
     */
    @objid ("29ed0b20-12a2-4b91-a289-ecd9cd2e59bd")
    void setProcessor(ExternProcessor value);

    /**
     * Getter for relation 'MatrixValueDefinition->Parameters'
     * 
     * Metamodel description:
     * <i>Parameters to pass to the external processor.</i>
     * 
     */
    @objid ("1d87143a-dba8-4fcb-8538-120d0a70f835")
    PropertyTable getParameters();

    /**
     * Setter for relation 'MatrixValueDefinition->Parameters'
     * 
     * Metamodel description:
     * <i>Parameters to pass to the external processor.</i>
     * 
     */
    @objid ("a9ea12e5-e5d9-4a8f-b280-72a9b314bb56")
    void setParameters(PropertyTable value);

    /**
     * Getter for relation 'MatrixValueDefinition->Matrix'
     * 
     * Metamodel description:
     * <i>Matrix owner</i>
     * 
     */
    @objid ("7d3e24ee-32c3-49c9-9101-1a056c65d221")
    MatrixDefinition getMatrix();

    /**
     * Setter for relation 'MatrixValueDefinition->Matrix'
     * 
     * Metamodel description:
     * <i>Matrix owner</i>
     * 
     */
    @objid ("3d5bf32a-3602-4bb2-bb57-8dac25976b68")
    void setMatrix(MatrixDefinition value);
}

