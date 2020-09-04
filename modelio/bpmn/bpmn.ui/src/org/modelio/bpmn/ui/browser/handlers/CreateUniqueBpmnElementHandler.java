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
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.model.browser.view.handlers.create.CreateElementHandler;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("8dd93785-c9d8-11e1-b479-001ec947c8cc")
public class CreateUniqueBpmnElementHandler extends CreateElementHandler {
    @objid ("0015db64-d02c-1006-9c1d-001ec947cd2a")
    @Override
    protected boolean doCanExecute(MObject owner, MClass metaclass, MDependency dependency, Stereotype stereotype) {
        if (!super.doCanExecute(owner, metaclass, dependency, stereotype)) {
            return false;
        }
        
        // Now lets have a look a some specific things:
        if (owner instanceof BpmnActivity) {
            BpmnActivity element = (BpmnActivity) owner;
            return element.getLoopCharacteristics() == null;
        } else if (owner instanceof BpmnMultiInstanceLoopCharacteristics) {
            BpmnMultiInstanceLoopCharacteristics element = (BpmnMultiInstanceLoopCharacteristics) owner;
            return element.getLoopDataInput() == null && element.getLoopDataOutputRef() == null;
        } else if (owner instanceof BpmnItemAwareElement) {
            BpmnItemAwareElement element = (BpmnItemAwareElement) owner;
            return element.getDataState() == null;
        }
        return true;
    }

}
