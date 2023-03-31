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

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * OpaqueAction v0.0.9054
 * 
 * 
 * The semantics of the action are determined by the implementation. OpaqueAction is introduced for implementation-specific actions or for use as a temporary placeholder before some other action is chosen.
 * 
 * Modelio specific:
 * The action may be filled in one language only.
 * Code generation MDA components may allow specific note types in order to allow the user to fill code in their generated language.
 * 
 * 
 * 
 */
@objid ("0039f63e-c4bf-1fd8-97fe-001ec947cd2a")
public interface OpaqueAction extends ActivityAction {
    /**
     * The metaclass simple name.
     */
    @objid ("798b3d1f-1dad-4025-81b4-f91a720135f3")
    public static final String MNAME = "OpaqueAction";

    /**
     * The metaclass qualified name.
     */
    @objid ("160bc0d6-db76-41ec-b771-799368061c18")
    public static final String MQNAME = "Standard.OpaqueAction";

    /**
     * Getter for attribute 'OpaqueAction.Body'
     * 
     * Metamodel description:
     * <i>Specifies the action in one language.</i>
     * 
     */
    @objid ("a8e34dac-3f36-49e5-bd01-63a6e9194e74")
    String getBody();

    /**
     * Setter for attribute 'OpaqueAction.Body'
     * 
     * Metamodel description:
     * <i>Specifies the action in one language.</i>
     * 
     */
    @objid ("6fe0187f-8139-49ad-8fb7-089ff73856f8")
    void setBody(String value);
}

