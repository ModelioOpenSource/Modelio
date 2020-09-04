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
 * MDA expert to handle a {@link Dependency} with << assigned >> stereotype.
 * <p>
 * This link can be created between:
 * </p>
 * <table summary="Creation rules" border="1">
 * <tr>
 * <th>Possible source</th>
 * <th>Possible target</th>
 * </tr>
 * <tr><td>Interface</td><td>Goal</td></tr>
 * <tr><td>Actor</td><td>Goal</td></tr>
 * <tr><td>Package</td><td>Goal</td></tr>
 * <tr><td>BpmnProcess</td><td>Goal</td></tr>
 * <tr><td>BpmnCollaboration</td><td>Goal</td></tr>
 * 
 * </table>
 */
@objid ("6311cc6c-f82a-4b45-b4c5-a59c4d12ab08")
public class AssignedExpert implements IMdaExpert {
    @objid ("b9ef3bf6-1bc6-4412-8f39-e27ce9dc7ff9")
    @Override
    public boolean canSource(final Stereotype linkStereotype, final MClass linkMetaclass, final MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Standard.Interface"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.Actor"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.Package"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCollaboration")));
    }

    @objid ("b1dcd6e0-1227-44da-a28d-44b06110e081")
    @Override
    public boolean canSource(final Stereotype linkStereotype, final MObject linkElement, final MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.Interface"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.Actor"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.Package"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnCollaboration")));
    }

    @objid ("23c99092-b4a1-4ac5-b142-13568063b55d")
    @Override
    public boolean canTarget(final Stereotype linkStereotype, final MClass linkMetaclass, final MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")));
    }

    @objid ("ddc3dc1a-40e4-4105-804c-04208fbd25f8")
    @Override
    public boolean canTarget(final Stereotype linkStereotype, final MObject linkElement, final MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal")));
    }

    @objid ("48cdb15d-1ce7-4736-9dcc-dc0660809f6f")
    @Override
    public boolean canLink(final Stereotype linkStereotype, final MClass linkMetaclass, final MClass fromMetaclass, final MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Standard.Interface"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.Actor"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.Package"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCollaboration"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Goal"))));
    }

    @objid ("9ae634f4-4637-460d-a20d-08898b4ea52f")
    @Override
    public boolean canLink(final Stereotype linkStereotype, final MClass linkMetaclass, final MObject fromElement, final MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.Interface"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.Actor"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.Package"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnCollaboration"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Goal"))));
    }

}
