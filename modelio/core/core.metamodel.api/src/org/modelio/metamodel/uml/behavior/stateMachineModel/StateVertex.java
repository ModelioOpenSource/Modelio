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
 * StateVertex v0.0.9054
 * 
 * 
 * A StateVertex can be either a State, or a PseudoState that is only a graphical convention. A StateVertex belongs to a State, or to a StateMachine if it is a root State.
 */
@objid ("0054944e-c4bf-1fd8-97fe-001ec947cd2a")
public interface StateVertex extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("3db2cda0-62bf-4298-96c3-313f04555491")
    public static final String MNAME = "StateVertex";

    /**
     * The metaclass qualified name.
     */
    @objid ("bd2aa0ad-1ca8-4943-99b6-bdc5c2a9a0f1")
    public static final String MQNAME = "Standard.StateVertex";

    /**
     * Getter for relation 'StateVertex->OutGoing'
     * 
     * Metamodel description:
     * <i>Specifies the Transitions departing from the StateVertex.</i>
     */
    @objid ("5146c59c-4407-4962-90a2-2e9f4a42c62b")
    EList<Transition> getOutGoing();

    /**
     * Filtered Getter for relation 'StateVertex->OutGoing'
     * 
     * Metamodel description:
     * <i>Specifies the Transitions departing from the StateVertex.</i>
     */
    @objid ("53b8bca8-ff35-4de3-893b-ac070dfd7173")
    <T extends Transition> List<T> getOutGoing(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'StateVertex->Incoming'
     * 
     * Metamodel description:
     * <i>Specifies the Transitions entering the StateVertex.</i>
     */
    @objid ("8574f5d1-6cdb-40d0-8b34-befbf2fb31dd")
    EList<Transition> getIncoming();

    /**
     * Filtered Getter for relation 'StateVertex->Incoming'
     * 
     * Metamodel description:
     * <i>Specifies the Transitions entering the StateVertex.</i>
     */
    @objid ("c5ee922f-b50e-4242-8ca5-1ba7e48f0b09")
    <T extends Transition> List<T> getIncoming(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'StateVertex->Parent'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("38729d42-249f-4746-b4c2-600d84de2be5")
    Region getParent();

    /**
     * Setter for relation 'StateVertex->Parent'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("10d06ea1-52fd-467a-aa7f-95f215a12131")
    void setParent(Region value);

}
