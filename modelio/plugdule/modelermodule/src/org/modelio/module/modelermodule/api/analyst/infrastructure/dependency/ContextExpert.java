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
 * MDA expert to handle a {@link Dependency} with << context >> stereotype.
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
 * <tr><td>BpmnProcess</td><td>Goal</td></tr>
 * <tr><td>BpmnCollaboration</td><td>Goal</td></tr>
 * 
 * </table>
 */
@objid ("df104a7b-4e95-4bf0-b700-05aec816039f")
public class ContextExpert implements IMdaExpert {
    @objid ("8a300ae1-dc3b-44dc-b28a-ec90f7b17927")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Standard.Interface"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.Actor"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCollaboration")));
    }

    @objid ("8bff513f-39c4-4006-a7e7-556b17fbab6e")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.Interface"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.Actor"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnCollaboration")));
    }

    @objid ("b1045fc6-a8fd-4132-a224-ba51f61aaab9")
    @Override
    public boolean canSource(ElementScope linkScope, MObject fromElement) {
        MMetamodel metamodel = linkScope.getMetaclass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.Interface"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.Actor"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnCollaboration")));
    }

    @objid ("0a7d2ed9-e727-428d-b36e-dae1cab03ce6")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")));
    }

    @objid ("bbbb42f7-5e1f-4b94-a679-625c1b88df43")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkElement, MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal")));
    }

    @objid ("0f93461d-a13f-4490-aa1c-71a0f8cda077")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Standard.Interface"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.Actor"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCollaboration"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal"))));
    }

    @objid ("95069ddb-561f-4d18-9225-398b619400ba")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject fromElement, MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.Interface"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.Actor"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnCollaboration"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal"))));
    }

    @objid ("dbfc2084-39f4-4aca-b664-41c5391a67d2")
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

    @objid ("a805f83b-bb71-45d0-97ec-291d08e0f304")
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

    @objid ("cb7279ec-741d-44c8-803a-89ef93c420ff")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

}
