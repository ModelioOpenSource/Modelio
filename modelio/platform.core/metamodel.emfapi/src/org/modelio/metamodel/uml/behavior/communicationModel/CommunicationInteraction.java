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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.uml.behavior.communicationModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;

/**
 * CommunicationInteraction v0.0.9054
 */
@objid ("005a13d8-c4bf-1fd8-97fe-001ec947cd2a")
public interface CommunicationInteraction extends Behavior {
    /**
     * The metaclass simple name.
     */
    @objid ("d3cb1dc4-64bd-42a4-98c1-477bbcea408d")
    public static final String MNAME = "CommunicationInteraction";

    /**
     * The metaclass qualified name.
     */
    @objid ("29e68cb2-460f-4975-b95c-a5cc49cf73b8")
    public static final String MQNAME = "Standard.CommunicationInteraction";

    /**
     * Getter for relation 'CommunicationInteraction->Owned'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("c44f243c-b645-458c-9a66-210d3702342d")
    EList<CommunicationNode> getOwned();

    /**
     * Filtered Getter for relation 'CommunicationInteraction->Owned'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("f562daf0-a384-4263-80ad-e7e2e0873598")
    <T extends CommunicationNode> List<T> getOwned(java.lang.Class<T> filterClass);

}
