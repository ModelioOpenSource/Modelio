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
    @objid ("87ac3024-64a6-432b-8c09-634500546360")
    public static final String MNAME = "MatrixValueDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("2e79ccb2-db2e-46f8-9ad1-68cf4271b2ac")
    public static final String MQNAME = "Infrastructure.MatrixValueDefinition";

    /**
     * Getter for relation 'MatrixValueDefinition->Processor'
     * 
     * Metamodel description:
     * <i>External java processor that displays and edit matrix values.</i>
     */
    @objid ("43cbfd93-4872-48c4-998d-6ae7fc12d954")
    ExternProcessor getProcessor();

    /**
     * Setter for relation 'MatrixValueDefinition->Processor'
     * 
     * Metamodel description:
     * <i>External java processor that displays and edit matrix values.</i>
     */
    @objid ("526266b4-9818-46cf-b36e-6a234eada5e0")
    void setProcessor(ExternProcessor value);

    /**
     * Getter for relation 'MatrixValueDefinition->Parameters'
     * 
     * Metamodel description:
     * <i>Parameters to pass to the external processor.</i>
     */
    @objid ("bb42b443-0255-493b-83d9-cf638b2b28e4")
    PropertyTable getParameters();

    /**
     * Setter for relation 'MatrixValueDefinition->Parameters'
     * 
     * Metamodel description:
     * <i>Parameters to pass to the external processor.</i>
     */
    @objid ("40a15df0-c55f-4613-bbdd-70ca92545b52")
    void setParameters(PropertyTable value);

    /**
     * Getter for relation 'MatrixValueDefinition->Matrix'
     * 
     * Metamodel description:
     * <i>Matrix owner</i>
     */
    @objid ("59b0bbd1-bd50-4018-bcfe-2123996d74a0")
    MatrixDefinition getMatrix();

    /**
     * Setter for relation 'MatrixValueDefinition->Matrix'
     * 
     * Metamodel description:
     * <i>Matrix owner</i>
     */
    @objid ("dfe45074-cd9b-4a8e-ae31-c37a6b037d26")
    void setMatrix(MatrixDefinition value);

}
