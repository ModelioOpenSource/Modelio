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
    @objid ("d8662c20-3fe6-4fb8-8462-d23dc9251598")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane")));
    }

    @objid ("995344ce-cc07-447d-b661-6dd5f7175851")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane")));
    }

    @objid ("1fd6c6ff-1bcd-4dd9-af25-e1398344ec5c")
    @Override
    public boolean canSource(ElementScope linkScope, MObject fromElement) {
        MMetamodel metamodel = linkScope.getMetaclass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane")));
    }

    @objid ("58d9d454-afce-43f5-abed-572765b22b9b")
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

    @objid ("511f4526-103c-4132-80e4-541aa43061c9")
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

    @objid ("6fa4d784-8929-4a9b-b848-95f2143bc391")
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

    @objid ("800c2e9e-e9d5-4f09-aa18-68216e65fe52")
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

    @objid ("064d571f-5ad6-4100-8401-e191c29e5543")
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

    @objid ("a5c85fa1-0be7-459a-b89e-3af231370a6b")
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

    @objid ("f07fa1ef-9f28-4393-ac27-1fd693676ba6")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

}
