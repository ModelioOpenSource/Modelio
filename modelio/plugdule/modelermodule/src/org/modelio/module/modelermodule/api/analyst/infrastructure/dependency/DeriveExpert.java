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
 * MDA expert to handle a {@link Dependency} with << derive >> stereotype.
 * <p>
 * This link can be created between:
 * </p>
 * <table summary="Creation rules" border="1">
 * <tr>
 *   <th>Possible source</th>
 *   <th>Possible target</th>
 * </tr>
 * <tr><td>UseCase</td><td>Requirement</td></tr>
 * <tr><td>Requirement</td><td>Requirement</td></tr>
 * 
 * </table>
 */
@objid ("ebb6dec7-2a13-4b56-851d-c0557f76bcfe")
public class DeriveExpert implements IMdaExpert {
    @objid ("3fb25f10-4214-452a-ae29-7fc83856539a")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Standard.UseCase"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement")));
    }

    @objid ("f9ebe768-86b9-4dd4-851b-aab5e81cf7b4")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.UseCase"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement")));
    }

    @objid ("40e34119-8b5a-4976-bc01-2b460182af1d")
    @Override
    public boolean canSource(ElementScope linkScope, MObject fromElement) {
        MMetamodel metamodel = linkScope.getMetaclass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.UseCase"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement")));
    }

    @objid ("c9bab062-ad74-4267-9c8e-5acc06df4665")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement")));
    }

    @objid ("78e22e7e-460e-456e-a94a-2c1ebb09c94d")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkElement, MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement")));
    }

    @objid ("e82db8b5-eaef-4a38-9ff7-274b36759334")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Standard.UseCase"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement"))));
    }

    @objid ("71a3fcc8-a215-4cbe-8e4a-eaab98f926b6")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject fromElement, MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.UseCase"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement"))));
    }

    @objid ("89a8f7a3-9792-4948-a5e5-de7931324b07")
    @Override
    public Collection<MClass> getPossibleSourceMetaclasses(Stereotype linkStereotype, MClass targetMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((targetMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement")))) {
        	MClass mc = metamodel.getMClass("Standard.UseCase");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement")))) {
        	MClass mc = metamodel.getMClass("Analyst.Requirement");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("6552add5-aa5d-4cb8-823d-d29858c5b16b")
    @Override
    public Collection<MClass> getPossibleTargetMetaclasses(Stereotype linkStereotype, MClass sourceMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.UseCase")))) {
        	MClass mc = metamodel.getMClass("Analyst.Requirement");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement")))) {
        	MClass mc = metamodel.getMClass("Analyst.Requirement");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("b13aa8b2-f020-4940-a641-1cb243f7fac6")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

}
