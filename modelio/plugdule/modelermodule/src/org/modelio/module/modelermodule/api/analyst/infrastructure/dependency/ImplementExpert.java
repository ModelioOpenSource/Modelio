/* 
 * Copyright 2013-2019 Modeliosoft
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
    @objid ("f3388fc5-4327-4dc1-ba10-510b1f599150")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnTask"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.GeneralClass")));
    }

    @objid ("9c85152f-44ea-4477-ae10-d861e11f95eb")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnTask"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.GeneralClass")));
    }

    @objid ("c4a9b8a7-f537-4ba2-aa37-f5c624ca75ec")
    @Override
    public boolean canSource(ElementScope linkScope, MObject fromElement) {
        MMetamodel metamodel = linkScope.getMetaclass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnTask"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.GeneralClass")));
    }

    @objid ("467b906c-639d-45db-a269-5abc252b0165")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.BpmnTask")));
    }

    @objid ("8257863e-e968-4081-af76-e7f5c25b627f")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkElement, MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.BusinessRule"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnTask")));
    }

    @objid ("04604f22-7dab-4003-974a-1ca9796006f0")
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

    @objid ("7bb043d5-3f07-4792-bfdf-c7c5cefbe3c6")
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

    @objid ("caedd2db-5d3f-41ba-852a-5d61727fa89c")
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

    @objid ("d798f4e0-53e0-48ce-8d9f-2519f51c88f5")
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

    @objid ("03450b43-76c8-4467-8167-14c4fb62e745")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

}
