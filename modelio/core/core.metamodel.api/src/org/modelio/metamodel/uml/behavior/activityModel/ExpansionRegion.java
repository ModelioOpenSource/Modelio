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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.uml.behavior.activityModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionKind;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionNode;

/**
 * ExpansionRegion v0.0.9054
 * 
 * 
 * null
 */
@objid ("0031bab4-c4bf-1fd8-97fe-001ec947cd2a")
public interface ExpansionRegion extends StructuredActivityNode {
    /**
     * The metaclass simple name.
     */
    @objid ("6d4a21d6-cf2f-4757-a1f5-92c2e5559353")
    public static final String MNAME = "ExpansionRegion";

    /**
     * The metaclass qualified name.
     */
    @objid ("d8030067-3ea1-46fc-a025-e47d62023af6")
    public static final String MQNAME = "Standard.ExpansionRegion";

    /**
     * Getter for attribute 'ExpansionRegion.Mode'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("bb8b9e9c-4c85-469b-bdcf-6c927b87c468")
    ExpansionKind getMode();

    /**
     * Setter for attribute 'ExpansionRegion.Mode'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("222ca9fe-d242-4888-9654-baf02d991507")
    void setMode(ExpansionKind value);

    /**
     * Getter for relation 'ExpansionRegion->OutputElement'
     * 
     * Metamodel description:
     * <i>An object node that accepts a separate element of the output collection during each of the multiple executions of the 
     * region. The values are formed into a collection that is available when the execution of the region is complete.</i>
     */
    @objid ("c543530f-3be7-4aaa-8d07-08a9d4646379")
    EList<ExpansionNode> getOutputElement();

    /**
     * Filtered Getter for relation 'ExpansionRegion->OutputElement'
     * 
     * Metamodel description:
     * <i>An object node that accepts a separate element of the output collection during each of the multiple executions of the 
     * region. The values are formed into a collection that is available when the execution of the region is complete.</i>
     */
    @objid ("052f2b1a-2bf2-4aac-a815-48921e89a3ed")
    <T extends ExpansionNode> List<T> getOutputElement(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ExpansionRegion->InputElement'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("123ae37b-acca-4f9a-ab47-3db4a5da67b7")
    EList<ExpansionNode> getInputElement();

    /**
     * Filtered Getter for relation 'ExpansionRegion->InputElement'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9e29a494-90e9-482f-9b88-919eae7306cc")
    <T extends ExpansionNode> List<T> getInputElement(java.lang.Class<T> filterClass);

}
