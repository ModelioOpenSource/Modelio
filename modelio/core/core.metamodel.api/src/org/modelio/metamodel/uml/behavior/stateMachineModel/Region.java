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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

/**
 * Region v0.0.9054
 */
@objid ("0056e10e-c4bf-1fd8-97fe-001ec947cd2a")
public interface Region extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("81b4899f-8c54-44c3-9780-cce12ee69d22")
    public static final String MNAME = "Region";

    /**
     * The metaclass qualified name.
     */
    @objid ("330e8fe9-40c6-4861-a852-ac110f0c1b27")
    public static final String MQNAME = "Standard.Region";

    /**
     * Getter for relation 'Region->Parent'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("6700f38a-923c-42be-9dd5-a7ac3294e462")
    State getParent();

    /**
     * Setter for relation 'Region->Parent'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("d3cd079c-2d76-45a2-966b-2b7618114700")
    void setParent(State value);

    /**
     * Getter for relation 'Region->Represented'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("b3be9e6e-c5f7-406e-955c-f432ad64f3b7")
    StateMachine getRepresented();

    /**
     * Setter for relation 'Region->Represented'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("d33a4bc9-8243-4f6b-b6f9-3557a9cba337")
    void setRepresented(StateMachine value);

    /**
     * Getter for relation 'Region->Sub'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("e4c6b140-ea08-415e-9d54-2bcb91538ecc")
    EList<StateVertex> getSub();

    /**
     * Filtered Getter for relation 'Region->Sub'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("7731d6ab-0d89-4acd-9bea-8a5de357e918")
    <T extends StateVertex> List<T> getSub(java.lang.Class<T> filterClass);

}
