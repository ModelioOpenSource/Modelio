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
    @objid ("1ab4f884-be7f-4425-90ef-74c142593c29")
    public static final String MNAME = "QueryDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("6d23e0dc-c43d-495c-9719-9c10faf71077")
    public static final String MQNAME = "Infrastructure.QueryDefinition";

    /**
     * Getter for attribute 'QueryDefinition.UsingAdditions'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("99740acd-4974-4cb6-8593-485755fcd7ad")
    boolean isUsingAdditions();

    /**
     * Setter for attribute 'QueryDefinition.UsingAdditions'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("70ed184b-a1ff-4c34-8802-acc68ba3774f")
    void setUsingAdditions(boolean value);

    /**
     * Getter for relation 'QueryDefinition->Added'
     * 
     * Metamodel description:
     * <i>Elements manually added to the lines or columns.</i>
     * 
     */
    @objid ("0415437e-9680-474c-a5ea-392a7567a012")
    EList<Element> getAdded();

    /**
     * Filtered Getter for relation 'QueryDefinition->Added'
     * 
     * Metamodel description:
     * <i>Elements manually added to the lines or columns.</i>
     * 
     */
    @objid ("e2c25527-e2d6-4775-8279-e1e132bbc060")
    <T extends Element> List<T> getAdded(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'QueryDefinition->Processor'
     * 
     * Metamodel description:
     * <i>External processor used to execute the query.</i>
     * 
     */
    @objid ("4a9140b2-48b4-4c85-aee3-8f5f852d3868")
    ExternProcessor getProcessor();

    /**
     * Setter for relation 'QueryDefinition->Processor'
     * 
     * Metamodel description:
     * <i>External processor used to execute the query.</i>
     * 
     */
    @objid ("f2915778-35e0-46e6-8d32-a9f46da90165")
    void setProcessor(ExternProcessor value);

    /**
     * Getter for relation 'QueryDefinition->Parameters'
     * 
     * Metamodel description:
     * <i>Parameters to pass to the external processor.</i>
     * 
     */
    @objid ("007bd1ed-e749-486c-a1c7-a6c1d6ee2e25")
    PropertyTable getParameters();

    /**
     * Setter for relation 'QueryDefinition->Parameters'
     * 
     * Metamodel description:
     * <i>Parameters to pass to the external processor.</i>
     * 
     */
    @objid ("b06746fd-ca90-4983-b8ad-c81b202c766e")
    void setParameters(PropertyTable value);

    /**
     * Getter for relation 'QueryDefinition->OwnerAsLine'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("cdc91219-f66c-41a3-a845-9b505b779cf4")
    MatrixDefinition getOwnerAsLine();

    /**
     * Setter for relation 'QueryDefinition->OwnerAsLine'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("1a430ba1-004a-4884-8d16-b667a882cab3")
    void setOwnerAsLine(MatrixDefinition value);

    /**
     * Getter for relation 'QueryDefinition->OwnerAsCol'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("acc0cfba-ad91-4831-939b-05b7ad6280ad")
    MatrixDefinition getOwnerAsCol();

    /**
     * Setter for relation 'QueryDefinition->OwnerAsCol'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("7968cc7a-01a6-4745-aee1-ea08d971811c")
    void setOwnerAsCol(MatrixDefinition value);

    /**
     * Getter for relation 'QueryDefinition->OwnerAsDepth'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("e71d7ef2-bfd4-444e-91a6-db372dc8649e")
    MatrixDefinition getOwnerAsDepth();

    /**
     * Setter for relation 'QueryDefinition->OwnerAsDepth'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("ccb7a3e8-7c52-42d4-8a57-79156dad6074")
    void setOwnerAsDepth(MatrixDefinition value);
}

