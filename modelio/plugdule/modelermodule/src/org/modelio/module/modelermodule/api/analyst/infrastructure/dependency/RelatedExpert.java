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
 * MDA expert to handle a {@link Dependency} with << related >> stereotype.
 * <p>
 * This link can be created between:
 * </p>
 * <table summary="Creation rules" border="1">
 * <tr>
 *   <th>Possible source</th>
 *   <th>Possible target</th>
 * </tr>
 * <tr><td>BusinessRule</td><td>BusinessRule</td></tr>
 * <tr><td>Term</td><td>Term</td></tr>
 * 
 * </table>
 */
@objid ("16d50e8e-6b3b-4fb1-9ec0-b0e3941c9f35")
public class RelatedExpert implements IMdaExpert {
    @objid ("e5f9de0c-853a-4833-b65a-3c493b415e79")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Analyst.Term")));
    }

    @objid ("62ad8d7d-6e7f-4b84-b22a-5528cb541d97")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.BusinessRule"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Term")));
    }

    @objid ("ce9f1932-7f64-4070-8e89-53ea49901b38")
    @Override
    public boolean canSource(ElementScope linkScope, MObject fromElement) {
        MMetamodel metamodel = linkScope.getMetaclass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.BusinessRule"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Term")));
    }

    @objid ("3e1f4164-fa0e-48ab-bdb4-5920562dff6f")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Analyst.Term")));
    }

    @objid ("62d413f1-bf80-4f9d-827d-386972aad95d")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkElement, MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.BusinessRule"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Term")));
    }

    @objid ("590f0857-10bc-4b65-88fb-f86d14b40551")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Analyst.Term"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Term"))));
    }

    @objid ("32868189-2e7d-4db1-8392-7c67b0d174af")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject fromElement, MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.BusinessRule"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.BusinessRule")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Term"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Term"))));
    }

    @objid ("61a05a7a-2d43-4305-bcd6-30080cc8d5ee")
    @Override
    public Collection<MClass> getPossibleSourceMetaclasses(Stereotype linkStereotype, MClass targetMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((targetMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule")))) {
        	MClass mc = metamodel.getMClass("Analyst.BusinessRule");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Analyst.Term")))) {
        	MClass mc = metamodel.getMClass("Analyst.Term");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("9752019d-4b71-47e4-a829-c74f811725ed")
    @Override
    public Collection<MClass> getPossibleTargetMetaclasses(Stereotype linkStereotype, MClass sourceMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule")))) {
        	MClass mc = metamodel.getMClass("Analyst.BusinessRule");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Analyst.Term")))) {
        	MClass mc = metamodel.getMClass("Analyst.Term");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("72bb0fad-e4e7-4bac-8b91-178209a02a1e")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

}
