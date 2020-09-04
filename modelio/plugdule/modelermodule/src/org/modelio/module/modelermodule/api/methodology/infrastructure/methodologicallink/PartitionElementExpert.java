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
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
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
    @objid ("82de0faf-5c14-4fe9-acb2-849fb4377094")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

    @objid ("2a2c2142-f061-43d4-9011-94c07f179eb7")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane")));
    }

    @objid ("26859ee9-284e-44b6-8c52-f9effb892c46")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane")));
    }

    @objid ("fca72807-1b40-424c-b61e-64b90328bbd6")
    @Override
    public boolean canSource(ElementScope linkScope, MObject fromElement) {
        MMetamodel metamodel = linkScope.getMetaclass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane")));
    }

    @objid ("6179c8e9-18f1-43d9-b559-6a5ae7306b29")
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

    @objid ("20b7ef5c-28a2-4f95-9ec0-47f665c16cdf")
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

    @objid ("a15158d1-c584-4aef-8d9e-e6da8949a1cd")
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

    @objid ("feb315d2-f239-4989-b9c7-7ebddf789ed3")
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

    @objid ("208f07ae-adcc-4027-ace2-b77417f4a2c6")
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

    @objid ("3686f22e-952a-4785-ace1-41d99e8e2ca0")
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

}
