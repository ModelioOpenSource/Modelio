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
 * Module: ModelerModule v9.1.00

 * This file was generated on 3/2/20 11:26 AM by Modelio Studio.
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
 * MDA expert to handle a {@link MethodologicalLink} with << PartitionElement >> stereotype.
 * <p>
 * This link can be created between:
 * </p>
 * <table summary="Creation rules" border="1">
 * <tr>
 *   <th>Possible source</th>
 *   <th>Possible target</th>
 * </tr>
 * <tr><td>BpmnLane</td><td>BusinessActor</td></tr>
 * <tr><td>BpmnLane</td><td>BusinessRole</td></tr>
 * <tr><td>BpmnLane</td><td>Actor</td></tr>
 * <tr><td>BpmnLane</td><td>Package</td></tr>
 * <tr><td>BpmnLane</td><td>Component</td></tr>
 * <tr><td>BpmnLane</td><td>Class</td></tr>
 * <tr><td>BpmnLane</td><td>Node</td></tr>
 * <tr><td>BpmnLane</td><td>ApplicationComponent</td></tr>
 * 
 * </table>
 */
@objid ("4db80c7c-ad0f-452f-80b3-0d024ff66c60")
public class PartitionElementExpert implements IMdaExpert {
    @objid ("8bef9824-1edf-4be3-a77d-a2656678a70d")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane")));
    }

    @objid ("193f7e46-79ee-4c33-9644-40b84dc90007")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane")));
    }

    @objid ("44790491-afa6-4d0c-8093-41e27f386724")
    @Override
    public boolean canSource(ElementScope linkScope, MObject fromElement) {
        MMetamodel metamodel = linkScope.getMetaclass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane")));
    }

    @objid ("4af551ff-4855-42c9-b476-77efdfb39e92")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessActor"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessRole"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Actor"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Package"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Component"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Class"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Node"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent")));
    }

    @objid ("0fa820ae-f38a-49e1-88b4-17e2ad335511")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkElement, MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessActor"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessRole"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Actor"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Package"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Component"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Class"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Node"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationComponent")));
    }

    @objid ("3afe5a0b-1d35-4d24-ba76-493c816dd6c1")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessActor")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessRole")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Actor")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Package")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Component")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Class")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Node")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))));
    }

    @objid ("f9222815-c8c8-40d5-be03-25ab55dc0177")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject fromElement, MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessActor")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessRole")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Actor")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Package")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Component")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Class")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Node")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))));
    }

    @objid ("5a1d2f3a-6d07-41c2-b2fc-6f167d3a5758")
    @Override
    public Collection<MClass> getPossibleSourceMetaclasses(Stereotype linkStereotype, MClass targetMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessActor")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnLane");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessRole")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnLane");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Actor")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnLane");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Package")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnLane");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Component")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnLane");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Class")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnLane");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Node")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnLane");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnLane");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("023b1344-6c79-4a6f-8eac-9d33be065c37")
    @Override
    public Collection<MClass> getPossibleTargetMetaclasses(Stereotype linkStereotype, MClass sourceMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane")))) {
        	MClass mc = metamodel.getMClass("Archimate.BusinessActor");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane")))) {
        	MClass mc = metamodel.getMClass("Archimate.BusinessRole");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane")))) {
        	MClass mc = metamodel.getMClass("Standard.Actor");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane")))) {
        	MClass mc = metamodel.getMClass("Standard.Package");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane")))) {
        	MClass mc = metamodel.getMClass("Standard.Component");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane")))) {
        	MClass mc = metamodel.getMClass("Standard.Class");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane")))) {
        	MClass mc = metamodel.getMClass("Standard.Node");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane")))) {
        	MClass mc = metamodel.getMClass("Archimate.ApplicationComponent");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("1a95e757-4f72-443b-88b1-07738b785499")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

}
