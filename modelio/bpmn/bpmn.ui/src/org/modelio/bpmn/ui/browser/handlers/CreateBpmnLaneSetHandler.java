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

package org.modelio.bpmn.ui.browser.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.model.browser.view.handlers.create.CreateElementHandler;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("8cb8bafb-c9d8-11e1-b479-001ec947c8cc")
public class CreateBpmnLaneSetHandler extends CreateElementHandler {
    @objid ("4e57e298-ccde-11e1-97e5-001ec947c8cc")
    @Override
    protected MObject doCreate(MObject owner, MClass metaclass, MDependency dependency, Stereotype stereotype, IMModelServices mmServices) {
        IStandardModelFactory mmFactory = mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        BpmnLaneSet newLaneSet = mmFactory.createBpmnLaneSet();
        
        if (owner instanceof BpmnProcess) {
            ((BpmnProcess) owner).setLaneSet(newLaneSet);
        
        } else if (owner instanceof BpmnLane) {
            ((BpmnLane) owner).setLaneSet(newLaneSet);
        }
        
        newLaneSet.setName(mmServices.getElementNamer().getUniqueName(newLaneSet));
        
        BpmnLane newLane = mmFactory.createBpmnLane();
        newLaneSet.getLane().add(newLane);
        
        newLane.setName(mmServices.getElementNamer().getUniqueName(newLane));
        return newLane;
    }

}
