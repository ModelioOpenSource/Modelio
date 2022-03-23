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
package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * MDA expert to handle a {@link Dependency} with << assigned >> stereotype.
 * <p>
 * This link can be created between:
 * </p>
 * <table summary="Creation rules" border="1">
 * <tr>
 *   <th>Possible source</th>
 *   <th>Possible target</th>
 * </tr>
 * <tr><td>Interface</td><td>Goal</td></tr>
 * <tr><td>Actor</td><td>Goal</td></tr>
 * <tr><td>Package</td><td>Goal</td></tr>
 * <tr><td>BpmnProcess</td><td>Goal</td></tr>
 * <tr><td>BpmnCollaboration</td><td>Goal</td></tr>
 * 
 * </table>
 */
@objid ("6311cc6c-f82a-4b45-b4c5-a59c4d12ab08")
public class AssignedExpert implements IMdaExpert {
    @objid ("89f8c53d-84b0-49d3-b7d3-f341210e0323")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Standard.Interface"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.Actor"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.Package"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCollaboration")));
    }

    @objid ("b47e69dc-386c-4ffd-902b-a60020fd93ec")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.Interface"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.Actor"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.Package"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnCollaboration")));
    }

    @objid ("275b9995-e35a-4068-be24-7d3438088f59")
    @Override
    public boolean canSource(ElementScope linkScope, MObject fromElement) {
        MMetamodel metamodel = linkScope.getMetaclass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.Interface"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.Actor"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.Package"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnCollaboration")));
    }

    @objid ("e3b7d8fb-7d31-4fe2-8424-804118526e00")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")));
    }

    @objid ("f19d4861-791e-4905-a9eb-2e9ced5db500")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkElement, MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal")));
    }

    @objid ("417b7258-170a-4f4c-8db7-2a09986acb72")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Standard.Interface"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.Actor"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.Package"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCollaboration"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal"))));
    }

    @objid ("d582789e-0f67-4c00-a5e6-6aa60d0e210a")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject fromElement, MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.Interface"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.Actor"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.Package"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnCollaboration"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal"))));
    }

    @objid ("01809721-aba9-40f6-92c7-eee8eb1e03cf")
    @Override
    public Collection<MClass> getPossibleSourceMetaclasses(Stereotype linkStereotype, MClass targetMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((targetMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) {
        	MClass mc = metamodel.getMClass("Standard.Interface");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) {
        	MClass mc = metamodel.getMClass("Standard.Actor");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) {
        	MClass mc = metamodel.getMClass("Standard.Package");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnProcess");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnCollaboration");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("3a9f15f3-3525-49eb-b9ac-f2bc17d971c7")
    @Override
    public Collection<MClass> getPossibleTargetMetaclasses(Stereotype linkStereotype, MClass sourceMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.Interface")))) {
        	MClass mc = metamodel.getMClass("Analyst.Goal");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.Actor")))) {
        	MClass mc = metamodel.getMClass("Analyst.Goal");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.Package")))) {
        	MClass mc = metamodel.getMClass("Analyst.Goal");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess")))) {
        	MClass mc = metamodel.getMClass("Analyst.Goal");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCollaboration")))) {
        	MClass mc = metamodel.getMClass("Analyst.Goal");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("d5846e0f-e3cb-477e-b9df-bb66ff5841b7")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

}
