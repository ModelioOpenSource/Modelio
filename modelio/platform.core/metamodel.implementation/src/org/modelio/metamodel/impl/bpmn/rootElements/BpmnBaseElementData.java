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
package org.modelio.metamodel.impl.bpmn.rootElements;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00783e94-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class BpmnBaseElementData extends ModelElementData {
    @objid ("663b17b3-1e53-4620-a7bd-1e6776912889")
     List<SmObjectImpl> mOutgoingAssoc = null;

    @objid ("8ba84ea7-cd4c-4dc8-9e22-e375b3f3f16e")
     List<SmObjectImpl> mIncomingAssoc = null;

    @objid ("498f09f6-3cb1-493e-8491-9a230c6d46f0")
     List<SmObjectImpl> mIncomingFlow = null;

    @objid ("d2a80303-c0ed-4251-ac5a-9c7d1f0c8e68")
     List<SmObjectImpl> mOutgoingFlow = null;

    @objid ("d9c5c3c4-28a2-4bd2-bcbf-7599d663d50d")
     List<SmObjectImpl> mPartitionedLaneRefs = null;

    @objid ("1364e239-5011-4d3d-8b16-40412c6278e3")
    public BpmnBaseElementData(BpmnBaseElementSmClass smClass) {
        super(smClass);
    }

}
