/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.3.00

 * This file was generated on 10/8/20 2:50 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * MDA expert to handle a {@link MethodologicalLink} with << Called >> stereotype.
 * <p>
 * This link can be created between:
 * </p>
 * <table summary="Creation rules" border="1">
 * <tr>
 *   <th>Possible source</th>
 *   <th>Possible target</th>
 * </tr>
 * <tr><td>BpmnCallActivity</td><td>ApplicationProcess</td></tr>
 * <tr><td>BpmnCallActivity</td><td>BusinessProcess</td></tr>
 * <tr><td>BpmnCallActivity</td><td>Operation</td></tr>
 * <tr><td>BpmnCallActivity</td><td>Behavior</td></tr>
 * <tr><td>BpmnReceiveTask</td><td>Operation</td></tr>
 * <tr><td>BpmnSendTask</td><td>Operation</td></tr>
 * <tr><td>BpmnServiceTask</td><td>Operation</td></tr>
 * <tr><td>BpmnServiceTask</td><td>BusinessService</td></tr>
 * 
 * </table>
 */
@objid ("c3a412dc-e6d9-4373-9d46-a48ce82c078d")
public class CalledExpert implements IMdaExpert {
    @objid ("4ec2ce38-580d-4661-9961-3a15f623e4b8")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCallActivity"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnReceiveTask"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnSendTask"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnServiceTask")));
    }

    @objid ("0c3d161a-0d66-4955-b32a-1b207edf8a2a")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnCallActivity"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnReceiveTask"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnSendTask"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnServiceTask")));
    }

    @objid ("023347ac-95d3-4975-a1d3-6dbbd4538e87")
    @Override
    public boolean canSource(ElementScope linkScope, MObject fromElement) {
        MMetamodel metamodel = linkScope.getMetaclass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnCallActivity"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnReceiveTask"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnSendTask"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnServiceTask")));
    }

    @objid ("64cb6f56-9c92-433a-b715-26840d227282")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationProcess"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessProcess"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Operation"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Behavior"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessService")));
    }

    @objid ("eb766d22-f722-426d-8ac6-8b3fb290fb93")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkElement, MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationProcess"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessProcess"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Operation"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Behavior"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessService")));
    }

    @objid ("c47b431d-b870-4324-965b-29e4a8b528ef")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCallActivity"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationProcess")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCallActivity"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessProcess")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCallActivity"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Operation")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCallActivity"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Behavior")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnReceiveTask"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Operation")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnSendTask"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Operation")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnServiceTask"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Operation")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnServiceTask"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessService"))));
    }

    @objid ("1b33f85e-3c4d-4b33-a07b-82b5f1ae2dea")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject fromElement, MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnCallActivity"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationProcess")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnCallActivity"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessProcess")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnCallActivity"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Operation")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnCallActivity"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Behavior")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnReceiveTask"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Operation")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnSendTask"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Operation")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnServiceTask"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Operation")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnServiceTask"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessService"))));
    }

    @objid ("46692ee0-d488-4ea7-8039-f0b639d95999")
    @Override
    public Collection<MClass> getPossibleSourceMetaclasses(Stereotype linkStereotype, MClass targetMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationProcess")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnCallActivity");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessProcess")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnCallActivity");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Operation")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnCallActivity");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Behavior")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnCallActivity");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Operation")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnReceiveTask");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Operation")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnSendTask");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Operation")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnServiceTask");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessService")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnServiceTask");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("0c70e155-2504-44d7-8982-68bc3a5babf6")
    @Override
    public Collection<MClass> getPossibleTargetMetaclasses(Stereotype linkStereotype, MClass sourceMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCallActivity")))) {
        	MClass mc = metamodel.getMClass("Archimate.ApplicationProcess");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCallActivity")))) {
        	MClass mc = metamodel.getMClass("Archimate.BusinessProcess");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCallActivity")))) {
        	MClass mc = metamodel.getMClass("Standard.Operation");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCallActivity")))) {
        	MClass mc = metamodel.getMClass("Standard.Behavior");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnReceiveTask")))) {
        	MClass mc = metamodel.getMClass("Standard.Operation");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnSendTask")))) {
        	MClass mc = metamodel.getMClass("Standard.Operation");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnServiceTask")))) {
        	MClass mc = metamodel.getMClass("Standard.Operation");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnServiceTask")))) {
        	MClass mc = metamodel.getMClass("Archimate.BusinessService");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("961c2b61-ceb2-4e40-a52f-97c79b870449")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

}
