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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;

/**
 * AcceptSignalAction v0.0.9054
 * 
 * 
 * AcceptSignalAction is an accept event action representing the receipt of an asynchronous signal.
 * 
 * 
 */
@objid ("00250f30-c4bf-1fd8-97fe-001ec947cd2a")
public interface AcceptSignalAction extends ActivityAction {
    /**
     * The metaclass simple name.
     */
    @objid ("ab2f66ba-e831-45a2-bfa6-581c8bf10d97")
    public static final String MNAME = "AcceptSignalAction";

    /**
     * The metaclass qualified name.
     */
    @objid ("8f2e7aec-0742-4d4f-9d83-0568173bf505")
    public static final String MQNAME = "Standard.AcceptSignalAction";

    /**
     * Getter for relation 'AcceptSignalAction->Accepted'
     * 
     * Metamodel description:
     * <i>The type of signal accepted.</i>
     * 
     */
    @objid ("64fa0399-06cf-4730-b6c7-2148560224af")
    EList<Signal> getAccepted();

    /**
     * Filtered Getter for relation 'AcceptSignalAction->Accepted'
     * 
     * Metamodel description:
     * <i>The type of signal accepted.</i>
     * 
     */
    @objid ("773c04b9-75e6-4bc8-9454-61d5a2998631")
    <T extends Signal> List<T> getAccepted(java.lang.Class<T> filterClass);
}

