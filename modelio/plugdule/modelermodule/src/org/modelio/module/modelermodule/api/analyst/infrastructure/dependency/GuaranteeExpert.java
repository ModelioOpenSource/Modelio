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

package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.metamodel.uml.infrastructure.Dependency;
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
 * <th>Possible source</th>
 * <th>Possible target</th>
 * </tr>
 * <tr><td>Requirement</td><td>Goal</td></tr>
 * <tr><td>KPI</td><td>Goal</td></tr>
 * 
 * </table>
 */
@objid ("f6bc15fc-7c46-4ff4-b615-18b6d6d86023")
public class GuaranteeExpert implements IMdaExpert {
    @objid ("bf790ea5-cbca-4607-a554-9f6b342741b3")
    @Override
    public boolean canSource(final Stereotype linkStereotype, final MClass linkMetaclass, final MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Analyst.KPI")));
    }

    @objid ("9f647b86-ea15-4973-a8cb-3b144f70cdd6")
    @Override
    public boolean canSource(final Stereotype linkStereotype, final MObject linkElement, final MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.KPI")));
    }

    @objid ("a3409087-c887-416a-b038-2910f5cd9445")
    @Override
    public boolean canTarget(final Stereotype linkStereotype, final MClass linkMetaclass, final MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")));
    }

    @objid ("85fdda60-921d-49f1-973a-954a3f951b50")
    @Override
    public boolean canTarget(final Stereotype linkStereotype, final MObject linkElement, final MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal")));
    }

    @objid ("25f8151c-1e8b-4d31-8ba5-b5d675e80813")
    @Override
    public boolean canLink(final Stereotype linkStereotype, final MClass linkMetaclass, final MClass fromMetaclass, final MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Analyst.KPI"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal"))));
    }

    @objid ("d79b2f3c-7635-409a-a8a5-137fb2c4292b")
    @Override
    public boolean canLink(final Stereotype linkStereotype, final MClass linkMetaclass, final MObject fromElement, final MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.KPI"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal"))));
    }

}
