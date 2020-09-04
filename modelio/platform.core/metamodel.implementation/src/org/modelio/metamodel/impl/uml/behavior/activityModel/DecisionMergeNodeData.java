/* 
 * Copyright 2013-2019 Modeliosoft
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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.DecisionNodeKind;

@objid ("0030401c-c4bf-1fd8-97fe-001ec947cd2a")
public class DecisionMergeNodeData extends ControlNodeData {
    @objid ("3684c061-654f-4eda-99ca-2afd1ddbb8f7")
     Object mDecisionKind = DecisionNodeKind.EXCLUSIVEDECISION;

    @objid ("942cfb4d-9b69-42c4-b33d-bc2ce2556dcb")
     Object mDecisionInputBehavior = "";

    @objid ("b154ab90-fd3f-4ded-8acb-ebabea804f36")
    public DecisionMergeNodeData(DecisionMergeNodeSmClass smClass) {
        super(smClass);
    }

}
