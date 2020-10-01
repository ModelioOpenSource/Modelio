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
package org.modelio.metamodel.uml.infrastructure.matrix;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ExternProcessor;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;

/**
 * MatrixValueDefinition v0.0.9054
 * 
 * 
 * Definition of a matrix value.
 * 
 * References an external java processor able to compute a matrix value and to modify it.
 */
@objid ("6ae11939-6513-44a4-b535-1daf76e27f63")
public interface MatrixValueDefinition extends Element {
    /**
     * The metaclass simple name.
     */
    @objid ("30e25730-86ed-4370-958c-e3ff09baf420")
    public static final String MNAME = "MatrixValueDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("02ec0b2f-2294-4140-9782-161d44e2ee1d")
    public static final String MQNAME = "Infrastructure.MatrixValueDefinition";

    /**
     * Getter for relation 'MatrixValueDefinition->Processor'
     * 
     * Metamodel description:
     * <i>External java processor that displays and edit matrix values.</i>
     */
    @objid ("26925aff-a2ca-463c-bcfb-f9273b871dc6")
    ExternProcessor getProcessor();

    /**
     * Setter for relation 'MatrixValueDefinition->Processor'
     * 
     * Metamodel description:
     * <i>External java processor that displays and edit matrix values.</i>
     */
    @objid ("08e9645f-d484-4fef-bd58-c7f1b9ad1f2d")
    void setProcessor(ExternProcessor value);

    /**
     * Getter for relation 'MatrixValueDefinition->Parameters'
     * 
     * Metamodel description:
     * <i>Parameters to pass to the external processor.</i>
     */
    @objid ("1872153e-861c-4278-b938-7e6ee5d3674a")
    PropertyTable getParameters();

    /**
     * Setter for relation 'MatrixValueDefinition->Parameters'
     * 
     * Metamodel description:
     * <i>Parameters to pass to the external processor.</i>
     */
    @objid ("2763c304-dc08-42cf-bee4-c2e96023a479")
    void setParameters(PropertyTable value);

    /**
     * Getter for relation 'MatrixValueDefinition->Matrix'
     * 
     * Metamodel description:
     * <i>Matrix owner</i>
     */
    @objid ("6b848df0-2dab-40a3-8635-2fd92767ff5d")
    MatrixDefinition getMatrix();

    /**
     * Setter for relation 'MatrixValueDefinition->Matrix'
     * 
     * Metamodel description:
     * <i>Matrix owner</i>
     */
    @objid ("d2fd3a7b-8138-466a-8103-0b9e3624c598")
    void setMatrix(MatrixDefinition value);

}
