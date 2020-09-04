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
 * MDA expert to handle a {@link Dependency} with << homonym >> stereotype.
 * <p>
 * This link can be created between:
 * </p>
 * <table summary="Creation rules" border="1">
 * <tr>
 *   <th>Possible source</th>
 *   <th>Possible target</th>
 * </tr>
 * <tr><td>Term</td><td>Term</td></tr>
 * 
 * </table>
 */
@objid ("1bf51965-5dac-4916-b9d3-02840b74e53a")
public class HomonymExpert implements IMdaExpert {
    @objid ("fb67bca4-5919-482f-bb7e-a1604b7ab4a7")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Analyst.Term")));
    }

    @objid ("0163fa75-2b9e-4aa3-957a-e9965c18d2dd")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Term")));
    }

    @objid ("a80f7cb2-db74-48c8-8ab1-ee5c48d2552a")
    @Override
    public boolean canSource(ElementScope linkScope, MObject fromElement) {
        MMetamodel metamodel = linkScope.getMetaclass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Term")));
    }

    @objid ("cd7603f6-bf48-4a02-a3f7-ebbf694cd62d")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Analyst.Term")));
    }

    @objid ("cc76f062-e2bb-4c77-bd8b-84a401319784")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkElement, MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Term")));
    }

    @objid ("df95ea40-95e2-4b7e-963c-807954cb46b1")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Analyst.Term"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Term"))));
    }

    @objid ("70c5e3cd-a8ad-4c53-8d54-702c9b48e742")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject fromElement, MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Term"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Term"))));
    }

    @objid ("7056082a-75ed-456c-a993-0faa637e4a29")
    @Override
    public Collection<MClass> getPossibleSourceMetaclasses(Stereotype linkStereotype, MClass targetMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((targetMetaclass.hasBase(metamodel.getMClass("Analyst.Term")))) {
        	MClass mc = metamodel.getMClass("Analyst.Term");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("85527b82-b3c6-40e6-b268-08f2359a4808")
    @Override
    public Collection<MClass> getPossibleTargetMetaclasses(Stereotype linkStereotype, MClass sourceMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Analyst.Term")))) {
        	MClass mc = metamodel.getMClass("Analyst.Term");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("91230f87-a5a5-4e31-af76-536aa704b349")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

}
