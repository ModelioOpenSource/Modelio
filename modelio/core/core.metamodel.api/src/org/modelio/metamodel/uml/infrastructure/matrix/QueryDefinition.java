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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ExternProcessor;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;

/**
 * QueryDefinition v0.0.9054
 * 
 * 
 * Line or column query definition.
 * 
 * Uses an external processor to execute the query.
 * 
 * 
 */
@objid ("1b70521e-83b3-42a8-a6ca-a0b86b8dc3ea")
public interface QueryDefinition extends Element {
    /**
     * The metaclass simple name.
     */
    @objid ("17218617-d30e-4aa8-a60c-594874f7973f")
    public static final String MNAME = "QueryDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("926a87f8-780a-45ea-ad9b-070f39ca8435")
    public static final String MQNAME = "Infrastructure.QueryDefinition";

    /**
     * Getter for attribute 'QueryDefinition.UsingAdditions'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("5affaafb-76f1-499c-a1d2-615bde499d20")
    boolean isUsingAdditions();

    /**
     * Setter for attribute 'QueryDefinition.UsingAdditions'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("7a2f33e4-e3cc-4f2b-a90d-fae03f2d8dd2")
    void setUsingAdditions(boolean value);

    /**
     * Getter for relation 'QueryDefinition->Added'
     * 
     * Metamodel description:
     * <i>Elements manually added to the lines or columns.</i>
     * 
     */
    @objid ("8e519d87-9316-4a14-a9fb-1f2afa4b9d3a")
    EList<Element> getAdded();

    /**
     * Filtered Getter for relation 'QueryDefinition->Added'
     * 
     * Metamodel description:
     * <i>Elements manually added to the lines or columns.</i>
     * 
     */
    @objid ("94925c15-c30a-4d60-9b8a-ccc03732f575")
    <T extends Element> List<T> getAdded(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'QueryDefinition->Processor'
     * 
     * Metamodel description:
     * <i>External processor used to execute the query.</i>
     * 
     */
    @objid ("299f0126-a60f-41f3-bf7c-feb8f3dfb0fd")
    ExternProcessor getProcessor();

    /**
     * Setter for relation 'QueryDefinition->Processor'
     * 
     * Metamodel description:
     * <i>External processor used to execute the query.</i>
     * 
     */
    @objid ("18e4988f-af75-4e6e-9453-ada6b9967e6c")
    void setProcessor(ExternProcessor value);

    /**
     * Getter for relation 'QueryDefinition->Parameters'
     * 
     * Metamodel description:
     * <i>Parameters to pass to the external processor.</i>
     * 
     */
    @objid ("0af13d8e-69f9-4bd1-83b1-60d4cc99c859")
    PropertyTable getParameters();

    /**
     * Setter for relation 'QueryDefinition->Parameters'
     * 
     * Metamodel description:
     * <i>Parameters to pass to the external processor.</i>
     * 
     */
    @objid ("607ac3cf-f65b-4ffd-a5b4-470319a05a20")
    void setParameters(PropertyTable value);

    /**
     * Getter for relation 'QueryDefinition->OwnerAsLine'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("dacad759-a5d6-4015-897e-f61f24cc6197")
    MatrixDefinition getOwnerAsLine();

    /**
     * Setter for relation 'QueryDefinition->OwnerAsLine'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("2128b5a2-7370-468d-aa19-a2c5a8cf7246")
    void setOwnerAsLine(MatrixDefinition value);

    /**
     * Getter for relation 'QueryDefinition->OwnerAsCol'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("c9391643-bbbc-462b-80fb-8489c2780df9")
    MatrixDefinition getOwnerAsCol();

    /**
     * Setter for relation 'QueryDefinition->OwnerAsCol'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("1d6d80d0-36b0-4606-9449-cdc2924b250e")
    void setOwnerAsCol(MatrixDefinition value);

    /**
     * Getter for relation 'QueryDefinition->OwnerAsDepth'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("4c97504a-6d8d-40c0-b5d4-777bc98869a0")
    MatrixDefinition getOwnerAsDepth();

    /**
     * Setter for relation 'QueryDefinition->OwnerAsDepth'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("ac52db70-4ff2-48ec-843a-f663c15fe9f2")
    void setOwnerAsDepth(MatrixDefinition value);
}

