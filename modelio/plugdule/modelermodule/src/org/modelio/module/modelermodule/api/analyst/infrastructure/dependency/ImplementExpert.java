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
 * MDA expert to handle a {@link Dependency} with << implement >> stereotype.
 * <p>
 * This link can be created between:
 * </p>
 * <table summary="Creation rules" border="1">
 * <tr>
 *   <th>Possible source</th>
 *   <th>Possible target</th>
 * </tr>
 * <tr><td>ApplicationComponent</td><td>BusinessRule</td></tr>
 * <tr><td>ApplicationComponent</td><td>BpmnProcess</td></tr>
 * <tr><td>ApplicationComponent</td><td>BpmnTask</td></tr>
 * <tr><td>BpmnProcess</td><td>BusinessRule</td></tr>
 * <tr><td>BpmnTask</td><td>BusinessRule</td></tr>
 * <tr><td>GeneralClass</td><td>BusinessRule</td></tr>
 * <tr><td>GeneralClass</td><td>BpmnProcess</td></tr>
 * <tr><td>GeneralClass</td><td>BpmnTask</td></tr>
 * 
 * </table>
 */
@objid ("14970d28-4a12-4e62-b130-28a988ccc7a7")
public class ImplementExpert implements IMdaExpert {
    @objid ("c707edd2-c1b8-4781-9661-43144443ba95")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnTask"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.GeneralClass")));
    }

    @objid ("0be6bd4e-eaff-4cf6-b41e-9ea83f895cff")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnTask"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.GeneralClass")));
    }

    @objid ("978bf75f-0e96-4c38-8a44-1e3acb2ba4ac")
    @Override
    public boolean canSource(ElementScope linkScope, MObject fromElement) {
        MMetamodel metamodel = linkScope.getMetaclass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnTask"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.GeneralClass")));
    }

    @objid ("e2f1be99-b36d-44a9-84db-f650e0b75727")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.BpmnTask")));
    }

    @objid ("42379050-6b5d-4254-b9ce-606ed7a37613")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkElement, MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.BusinessRule"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnTask")));
    }

    @objid ("d0adb2bb-3edd-4afe-b054-638e2576b86c")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.BpmnTask")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnTask"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.GeneralClass"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.GeneralClass"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.GeneralClass"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.BpmnTask"))));
    }

    @objid ("b7f7eacd-6a84-4f2c-9bf2-0fd61e9af92e")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject fromElement, MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.BusinessRule")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnTask")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.BusinessRule")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnTask"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.BusinessRule")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.GeneralClass"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.BusinessRule")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.GeneralClass"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.GeneralClass"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnTask"))));
    }

    @objid ("c90cb482-612d-47df-bb67-65c67d56e543")
    @Override
    public Collection<MClass> getPossibleSourceMetaclasses(Stereotype linkStereotype, MClass targetMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((targetMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule")))) {
        	MClass mc = metamodel.getMClass("Archimate.ApplicationComponent");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess")))) {
        	MClass mc = metamodel.getMClass("Archimate.ApplicationComponent");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.BpmnTask")))) {
        	MClass mc = metamodel.getMClass("Archimate.ApplicationComponent");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnProcess");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnTask");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule")))) {
        	MClass mc = metamodel.getMClass("Standard.GeneralClass");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess")))) {
        	MClass mc = metamodel.getMClass("Standard.GeneralClass");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.BpmnTask")))) {
        	MClass mc = metamodel.getMClass("Standard.GeneralClass");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("a1aa19e4-6b08-4166-a16a-27b2ecb1a68a")
    @Override
    public Collection<MClass> getPossibleTargetMetaclasses(Stereotype linkStereotype, MClass sourceMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent")))) {
        	MClass mc = metamodel.getMClass("Analyst.BusinessRule");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnProcess");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnTask");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess")))) {
        	MClass mc = metamodel.getMClass("Analyst.BusinessRule");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnTask")))) {
        	MClass mc = metamodel.getMClass("Analyst.BusinessRule");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.GeneralClass")))) {
        	MClass mc = metamodel.getMClass("Analyst.BusinessRule");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.GeneralClass")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnProcess");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.GeneralClass")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnTask");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("1454d4fe-e9ea-4a1c-a8da-db970f38f177")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

}
