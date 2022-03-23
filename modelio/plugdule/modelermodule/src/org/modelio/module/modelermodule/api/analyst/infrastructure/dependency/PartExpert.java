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
 * MDA expert to handle a {@link Dependency} with << part >> stereotype.
 * <p>
 * This link can be created between:
 * </p>
 * <table summary="Creation rules" border="1">
 * <tr>
 *   <th>Possible source</th>
 *   <th>Possible target</th>
 * </tr>
 * <tr><td>Requirement</td><td>Requirement</td></tr>
 * <tr><td>Goal</td><td>Goal</td></tr>
 * 
 * </table>
 */
@objid ("0730b77b-d191-4913-b495-e1c50163bd83")
public class PartExpert implements IMdaExpert {
    @objid ("58090239-d749-4be1-9070-643710ee4dd7")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")));
    }

    @objid ("b8f43caf-8931-409e-9b17-b0c5a2c374ea")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal")));
    }

    @objid ("addbfeb1-a0a7-4350-9fe8-a7582c0b9834")
    @Override
    public boolean canSource(ElementScope linkScope, MObject fromElement) {
        MMetamodel metamodel = linkScope.getMetaclass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal")));
    }

    @objid ("f965d946-95bf-4346-add9-d8d1946b31fa")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")));
    }

    @objid ("47d3ddda-203e-475c-a9af-75bdb378583e")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkElement, MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal")));
    }

    @objid ("3e95b64d-6f0a-40c5-ba5c-bcc908eb798d")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Analyst.Goal"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal"))));
    }

    @objid ("8ccd9f49-9442-449f-9b0e-392abc52cc08")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject fromElement, MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal"))));
    }

    @objid ("5beb202a-cb67-47b2-937f-79b3406c074e")
    @Override
    public Collection<MClass> getPossibleSourceMetaclasses(Stereotype linkStereotype, MClass targetMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((targetMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement")))) {
        	MClass mc = metamodel.getMClass("Analyst.Requirement");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) {
        	MClass mc = metamodel.getMClass("Analyst.Goal");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("13dd7f85-d3f8-4563-bd74-a559e7115c80")
    @Override
    public Collection<MClass> getPossibleTargetMetaclasses(Stereotype linkStereotype, MClass sourceMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement")))) {
        	MClass mc = metamodel.getMClass("Analyst.Requirement");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) {
        	MClass mc = metamodel.getMClass("Analyst.Goal");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("0e853eda-f974-4fb0-8036-a260c2ca30ad")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

}
