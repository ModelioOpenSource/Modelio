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
 * MDA expert to handle a {@link MethodologicalLink} with << Reference >> stereotype.
 * <p>
 * This link can be created between:
 * </p>
 * <table summary="Creation rules" border="1">
 * <tr>
 *   <th>Possible source</th>
 *   <th>Possible target</th>
 * </tr>
 * <tr><td>BpmnParticipant</td><td>Package</td></tr>
 * <tr><td>BpmnOperation</td><td>Operation</td></tr>
 * <tr><td>BpmnItemDefinition</td><td>GeneralClass</td></tr>
 * <tr><td>BpmnInterface</td><td>GeneralClass</td></tr>
 * <tr><td>BpmnParticipant</td><td>BusinessActor</td></tr>
 * <tr><td>BpmnParticipant</td><td>BusinessRole</td></tr>
 * <tr><td>BpmnParticipant</td><td>ApplicationComponent</td></tr>
 * <tr><td>BpmnParticipant</td><td>ApplicationProcess</td></tr>
 * <tr><td>BpmnParticipant</td><td>BusinessProcess</td></tr>
 * <tr><td>BpmnParticipant</td><td>TechnologyProcess</td></tr>
 * 
 * </table>
 */
@objid ("05db4267-ceb7-4b43-afbf-975089d23614")
public class ReferenceExpert implements IMdaExpert {
    @objid ("04427092-0913-4788-98f2-a96aafd746a5")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnParticipant"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnOperation"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnItemDefinition"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnInterface")));
    }

    @objid ("a02e1abc-a9ee-444e-984f-c4bd11baaf92")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnParticipant"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnOperation"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnItemDefinition"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnInterface")));
    }

    @objid ("1534e817-7ed1-4897-a699-076df1b2c681")
    @Override
    public boolean canSource(ElementScope linkScope, MObject fromElement) {
        MMetamodel metamodel = linkScope.getMetaclass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnParticipant"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnOperation"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnItemDefinition"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnInterface")));
    }

    @objid ("06674f12-ba49-464f-92d2-66b074fc8fc4")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Standard.Package"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Operation"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.GeneralClass"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessActor"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessRole"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationProcess"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessProcess"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.TechnologyProcess")));
    }

    @objid ("96b28b19-9d39-4cf4-99a7-7ad81d396df5")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkElement, MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Package"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Operation"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.GeneralClass"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessActor"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessRole"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationProcess"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessProcess"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.TechnologyProcess")));
    }

    @objid ("337d9778-c89f-4460-8c32-cc5a69fc32a3")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnParticipant"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Package")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnOperation"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Operation")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnItemDefinition"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.GeneralClass")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnInterface"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.GeneralClass")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnParticipant"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessActor")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnParticipant"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessRole")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnParticipant"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnParticipant"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationProcess")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnParticipant"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessProcess")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnParticipant"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.TechnologyProcess"))));
    }

    @objid ("178f59d5-c8de-4208-b1d3-2c5552d89f1f")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject fromElement, MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnParticipant"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Package")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnOperation"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Operation")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnItemDefinition"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.GeneralClass")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnInterface"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.GeneralClass")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnParticipant"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessActor")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnParticipant"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessRole")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnParticipant"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationComponent")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnParticipant"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationProcess")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnParticipant"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessProcess")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnParticipant"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.TechnologyProcess"))));
    }

    @objid ("c7f51693-3b1c-44d7-9553-8c0e5a18dad7")
    @Override
    public Collection<MClass> getPossibleSourceMetaclasses(Stereotype linkStereotype, MClass targetMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Package")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnParticipant");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Operation")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnOperation");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.GeneralClass")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnItemDefinition");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.GeneralClass")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnInterface");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessActor")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnParticipant");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessRole")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnParticipant");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnParticipant");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationProcess")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnParticipant");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessProcess")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnParticipant");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.TechnologyProcess")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnParticipant");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("3c3c6483-d01d-46c9-8f1c-d3463f6b66f7")
    @Override
    public Collection<MClass> getPossibleTargetMetaclasses(Stereotype linkStereotype, MClass sourceMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnParticipant")))) {
        	MClass mc = metamodel.getMClass("Standard.Package");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnOperation")))) {
        	MClass mc = metamodel.getMClass("Standard.Operation");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnItemDefinition")))) {
        	MClass mc = metamodel.getMClass("Standard.GeneralClass");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnInterface")))) {
        	MClass mc = metamodel.getMClass("Standard.GeneralClass");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnParticipant")))) {
        	MClass mc = metamodel.getMClass("Archimate.BusinessActor");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnParticipant")))) {
        	MClass mc = metamodel.getMClass("Archimate.BusinessRole");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnParticipant")))) {
        	MClass mc = metamodel.getMClass("Archimate.ApplicationComponent");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnParticipant")))) {
        	MClass mc = metamodel.getMClass("Archimate.ApplicationProcess");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnParticipant")))) {
        	MClass mc = metamodel.getMClass("Archimate.BusinessProcess");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnParticipant")))) {
        	MClass mc = metamodel.getMClass("Archimate.TechnologyProcess");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("b3eb7c92-febf-46d6-bf37-9c75f2981020")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

}
