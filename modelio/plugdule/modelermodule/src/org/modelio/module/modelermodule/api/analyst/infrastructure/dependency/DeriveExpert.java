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

package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.metamodel.uml.infrastructure.Dependency;
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
 * <th>Possible source</th>
 * <th>Possible target</th>
 * </tr>
 * <tr><td>UseCase</td><td>Requirement</td></tr>
 * <tr><td>Requirement</td><td>Requirement</td></tr>
 * 
 * </table>
 */
@objid ("ebb6dec7-2a13-4b56-851d-c0557f76bcfe")
public class DeriveExpert implements IMdaExpert {
    @objid ("55e155f2-0c1e-4414-8e82-8fad09fdceff")
    @Override
    public boolean canSource(final Stereotype linkStereotype, final MClass linkMetaclass, final MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Standard.UseCase"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement")));
    }

    @objid ("40a073bf-d513-4c50-8d18-19d2af9e0ae7")
    @Override
    public boolean canSource(final Stereotype linkStereotype, final MObject linkElement, final MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.UseCase"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement")));
    }

    @objid ("922b0095-6a96-4144-91c1-17ccd0e82d07")
    @Override
    public boolean canTarget(final Stereotype linkStereotype, final MClass linkMetaclass, final MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement")));
    }

    @objid ("f810ef14-cbfd-42a2-b396-7be792ca5cd9")
    @Override
    public boolean canTarget(final Stereotype linkStereotype, final MObject linkElement, final MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement")));
    }

    @objid ("cce8afc5-8cad-4655-8b3b-634746eb12a4")
    @Override
    public boolean canLink(final Stereotype linkStereotype, final MClass linkMetaclass, final MClass fromMetaclass, final MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Standard.UseCase"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement"))));
    }

    @objid ("91fa5003-8574-469f-8cd3-fe8f530cccc9")
    @Override
    public boolean canLink(final Stereotype linkStereotype, final MClass linkMetaclass, final MObject fromElement, final MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.UseCase"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement"))));
    }

}
