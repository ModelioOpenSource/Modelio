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

package org.modelio.metamodel.uml.behavior.stateMachineModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * InitialPseudoState v0.0.9054
 * 
 * 
 * An initial pseudostate represents a default vertex that is the source for a single transition to the default state of a composite state. There can be at most one initial vertex in a region. The outgoing transition from the initial vertex may have a behavior, but not a trigger or guard.
 */
@objid ("0050cd82-c4bf-1fd8-97fe-001ec947cd2a")
public interface InitialPseudoState extends AbstractPseudoState {
    /**
     * The metaclass simple name.
     */
    @objid ("302d0557-eb35-4d28-a44a-0d693f621175")
    public static final String MNAME = "InitialPseudoState";

    /**
     * The metaclass qualified name.
     */
    @objid ("ce5a5ab1-8f20-4be3-96dc-bfb4c3eede14")
    public static final String MQNAME = "Standard.InitialPseudoState";

}
