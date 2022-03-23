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
    @objid ("330a01c5-29d6-4868-97bc-e5d1d687cb8e")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnTask"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.GeneralClass")));
    }

    @objid ("d9efa5b3-d752-41ad-ba57-cbd5cecabe21")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnTask"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.GeneralClass")));
    }

    @objid ("dfa8a362-875e-4561-80fb-7ccbbb8939bf")
    @Override
    public boolean canSource(ElementScope linkScope, MObject fromElement) {
        MMetamodel metamodel = linkScope.getMetaclass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnTask"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.GeneralClass")));
    }

    @objid ("6b2d9060-26d7-41e6-ad5b-a5b0e9dc3f49")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.BpmnTask")));
    }

    @objid ("87a1a848-944e-4d4a-b51e-db8744712e9c")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkElement, MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.BusinessRule"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnTask")));
    }

    @objid ("cd48edde-542c-43e3-bbaf-b05a56d1594d")
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

    @objid ("3ff803e9-742e-42cc-aabb-625d62575f6f")
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

    @objid ("d1a13a39-4dc6-4cd5-a210-998defba77d4")
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

    @objid ("02f247d8-b4fa-45c3-bcd5-24e064fb6bfa")
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

    @objid ("93ea5aa9-3393-4e79-a0b0-683c52f5efd2")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

}
