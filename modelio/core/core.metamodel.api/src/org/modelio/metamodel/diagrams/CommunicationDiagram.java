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

package org.modelio.metamodel.diagrams;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * CommunicationDiagram v0.0.9054
 * 
 * 
 * <p>Communication diagrams are used to illustrate architecture and its functioning principles, by presenting, for example, which associations are used to transmit certain messages between different elements.</p><p>They partially cover the capacities provided by sequence diagrams, but are less relevant when detailing event sequences and messages occurring between several participants.</p><p>They are most often created in collaborations, operations or internal class structures to describe the principles of the dynamic.</p>
 */
@objid ("006cae9e-c4bf-1fd8-97fe-001ec947cd2a")
public interface CommunicationDiagram extends BehaviorDiagram {
    /**
     * The metaclass simple name.
     */
    @objid ("e2b10f5e-cb7a-4ff0-bd1e-9aa16caf2cd8")
    public static final String MNAME = "CommunicationDiagram";

    /**
     * The metaclass qualified name.
     */
    @objid ("831ee965-ae32-4165-a752-0edcf97338e9")
    public static final String MQNAME = "Standard.CommunicationDiagram";

}
