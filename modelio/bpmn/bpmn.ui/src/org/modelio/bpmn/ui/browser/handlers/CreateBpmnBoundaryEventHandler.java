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

package org.modelio.bpmn.ui.browser.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.model.browser.view.handlers.create.CreateElementHandler;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("8cf455c5-c9d8-11e1-b479-001ec947c8cc")
public class CreateBpmnBoundaryEventHandler extends CreateElementHandler {
    @objid ("4e499464-ccde-11e1-97e5-001ec947c8cc")
    @Override
    protected MObject doCreate(MObject owner, MClass metaclass, MDependency dependency, Stereotype stereotype, IMModelServices mmServices) {
        MObject effectiveOwner = getEffectiveOwner(owner);
        BpmnBoundaryEvent createdElement = (BpmnBoundaryEvent) super.doCreate(effectiveOwner, metaclass, dependency, stereotype, mmServices);
        createdElement.setCancelActivity(true);
        createdElement.setAttachedToRef((BpmnActivity) owner);
        return createdElement;
    }

    @objid ("4e49946d-ccde-11e1-97e5-001ec947c8cc")
    private MObject getEffectiveOwner(final MObject element) {
        if (element instanceof BpmnProcess || element instanceof BpmnSubProcess) {
            return element;
        } else {
            return getEffectiveOwner(element.getCompositionOwner());
        }
    }

    @objid ("730daf39-ae04-4f55-bc92-ebef10ce5c3b")
    @Override
    protected MDependency getDependency(String dependencyName, MObject selectedOwner) {
        if (selectedOwner instanceof BpmnActivity) {
            if (selectedOwner instanceof BpmnProcess || selectedOwner instanceof BpmnSubProcess) {
                return super.getDependency(dependencyName, selectedOwner);
            } else {
                return super.getDependency(dependencyName, selectedOwner.getCompositionOwner());
            }
        }
        return super.getDependency(dependencyName, selectedOwner);
    }

}
