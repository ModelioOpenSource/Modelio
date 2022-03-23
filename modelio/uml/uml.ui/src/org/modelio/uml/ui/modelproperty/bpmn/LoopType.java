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
package org.modelio.uml.ui.modelproperty.bpmn;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.BpmnStandardLoopCharacteristics;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.vcore.smkernel.SmObjectImpl;

/**
 * Enum for Activity LoopType property
 */
@objid ("d382b938-47a9-479e-a55d-85e21c3f1348")
enum LoopType {
    @objid ("1154e8e0-72ea-4a6c-a54e-9ea971c97cb9")
    MultiInstanceParallel,
    @objid ("5d938e38-4642-41e2-908e-bae3c5bfb75b")
    MultiInstanceSequential,
    @objid ("f7ed7e8c-24f2-42de-8fcb-b80973375ff0")
    None,
    @objid ("8d5e3d20-998d-4b9f-a07b-ab99f0695f30")
    Standard;

    /**
     * Get Loop Characteristics of an activity.
     * @param activity the edited activity.
     * @return a LoopType.
     */
    @objid ("b5d034a9-efed-4907-b56c-472ddaed3cd9")
    public static LoopType getType(BpmnActivity activity) {
        BpmnLoopCharacteristics definition = activity.getLoopCharacteristics();
        if (definition instanceof BpmnStandardLoopCharacteristics) {
            return Standard;
        } else if (definition instanceof BpmnMultiInstanceLoopCharacteristics) {
            BpmnMultiInstanceLoopCharacteristics multi = (BpmnMultiInstanceLoopCharacteristics) definition;
            if (multi.isIsSequencial()) {
                return MultiInstanceSequential;
            } else {
                return MultiInstanceParallel;
            }
        }
        return None;
    }

    /**
     * Update Loop Characteristics
     * @param modelService model services, to create elements.
     * @param type type
     * @param activity activity
     */
    @objid ("d662db81-1bb5-4948-b099-5755e757fe55")
    public static void setType(IMModelServices mmService, LoopType type, BpmnActivity activity) {
        // Delete the old loop type
        BpmnLoopCharacteristics ch = activity.getLoopCharacteristics();
        if (ch != null) {
            ((SmObjectImpl) ch).delete();
        }
        
        IStandardModelFactory modelFactory = mmService.getModelFactory().getFactory(IStandardModelFactory.class);
        switch (type) {
        case Standard:
            BpmnStandardLoopCharacteristics newStandardEvent = modelFactory.createBpmnStandardLoopCharacteristics();
            newStandardEvent.setOwnerActivity(activity);
            break;
        case MultiInstanceParallel:
            BpmnMultiInstanceLoopCharacteristics newMultiInstanceParallelEvent = modelFactory.createBpmnMultiInstanceLoopCharacteristics();
            newMultiInstanceParallelEvent.setOwnerActivity(activity);
            newMultiInstanceParallelEvent.setIsSequencial(false);
            break;
        case MultiInstanceSequential:
            BpmnMultiInstanceLoopCharacteristics newMultiInstanceSequential = modelFactory.createBpmnMultiInstanceLoopCharacteristics();
            newMultiInstanceSequential.setOwnerActivity(activity);
            newMultiInstanceSequential.setIsSequencial(true);
            break;
        case None:
            // Nothing to do, the old type is already deleted
            break;
        default:
            break;
        }
        
    }

}
