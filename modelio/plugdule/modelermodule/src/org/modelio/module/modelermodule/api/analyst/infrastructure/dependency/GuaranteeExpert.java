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
 * MDA expert to handle a {@link Dependency} with << guarantee >> stereotype.
 * <p>
 * This link can be created between:
 * </p>
 * <table summary="Creation rules" border="1">
 * <tr>
 *   <th>Possible source</th>
 *   <th>Possible target</th>
 * </tr>
 * <tr><td>Requirement</td><td>Goal</td></tr>
 * <tr><td>KPI</td><td>Goal</td></tr>
 * 
 * </table>
 */
@objid ("f6bc15fc-7c46-4ff4-b615-18b6d6d86023")
public class GuaranteeExpert implements IMdaExpert {
    @objid ("4cd9935b-b464-49f1-bf1a-ee597e8e9d4d")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Analyst.KPI")));
    }

    @objid ("7f72118e-4623-4bf3-861f-6e5292ca55e3")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.KPI")));
    }

    @objid ("d369b817-0b0a-4489-bed2-0ee54823e619")
    @Override
    public boolean canSource(ElementScope linkScope, MObject fromElement) {
        MMetamodel metamodel = linkScope.getMetaclass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.KPI")));
    }

    @objid ("55e60b5f-b5b0-4ea9-adc1-31a488b0ad12")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")));
    }

    @objid ("c9968752-60ef-4edd-9aaa-1e13b2505f10")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkElement, MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal")));
    }

    @objid ("a78e6a8e-8df7-42eb-b329-a12c80bbe297")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Analyst.KPI"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal"))));
    }

    @objid ("f3b4cb96-1e65-4fc5-bfc3-eb87e5717dd9")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject fromElement, MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.KPI"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal"))));
    }

    @objid ("545a59c0-9cc0-427b-9cac-dc18c35f842c")
    @Override
    public Collection<MClass> getPossibleSourceMetaclasses(Stereotype linkStereotype, MClass targetMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((targetMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) {
        	MClass mc = metamodel.getMClass("Analyst.Requirement");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) {
        	MClass mc = metamodel.getMClass("Analyst.KPI");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("b1ad0a72-9fc2-4b93-b617-d476bb6fd903")
    @Override
    public Collection<MClass> getPossibleTargetMetaclasses(Stereotype linkStereotype, MClass sourceMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement")))) {
        	MClass mc = metamodel.getMClass("Analyst.Goal");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Analyst.KPI")))) {
        	MClass mc = metamodel.getMClass("Analyst.Goal");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("72f7c3ef-98e8-4188-96a4-daf28d8b6b3c")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

}
