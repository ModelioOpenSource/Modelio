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
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.model.browser.view.handlers.create.CreateElementHandler;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("8cd2f4d9-c9d8-11e1-b479-001ec947c8cc")
public class CreateBpmnFlowElement extends CreateElementHandler {
    @objid ("4e57e2c3-ccde-11e1-97e5-001ec947c8cc")
    @Override
    protected MObject doCreate(MObject owner, MClass metaclass, MDependency dependency, Stereotype stereotype, IMModelServices mmServices) {
        MObject effectiveOwner = getEffectiveOwner(owner);
        BpmnFlowElement createdElement = (BpmnFlowElement) super.doCreate(effectiveOwner, metaclass, dependency, stereotype, mmServices);
        if (owner instanceof BpmnLane) {
            createdElement.getLane().add((BpmnLane) owner);
        }
        return createdElement;
    }

    @objid ("4e5a44f2-ccde-11e1-97e5-001ec947c8cc")
    private MObject getEffectiveOwner(final MObject element) {
        if (element instanceof BpmnProcess || element instanceof BpmnActivity) {
            return element;
        }
        return element != null ? getEffectiveOwner(element.getCompositionOwner()) : null;
    }

    @objid ("0058910c-cbbf-1006-9c1d-001ec947cd2a")
    @Override
    protected boolean doCanExecute(MObject owner, MClass metaclass, MDependency dependency, Stereotype stereotype) {
        if ((owner instanceof BpmnLane) && ((BpmnLane) owner).getChildLaneSet() != null) {
            return false;
        } else {
            return super.doCanExecute(owner, metaclass, dependency, stereotype);
        }
    }

    @objid ("c0b2c7eb-7f6f-4770-ad77-666dbfbc917c")
    @Override
    protected MDependency getDependency(final String dependencyName, MObject selectedOwner) {
        MObject owner = getEffectiveOwner(selectedOwner);
        if (owner == null) {
            return null;
        }
        MDependency dependency = owner.getMClass().getDependency(dependencyName);
        return dependency;
    }

}
