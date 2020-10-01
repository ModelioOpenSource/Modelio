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
 * Module: ModelerModule v9.1.00

 * This file was generated on 3/2/20 11:26 AM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * MDA expert to handle a {@link MethodologicalLink} with << State >> stereotype.
 * <p>
 * This link can be created between:
 * </p>
 * <table summary="Creation rules" border="1">
 * <tr>
 *   <th>Possible source</th>
 *   <th>Possible target</th>
 * </tr>
 * <tr><td>BpmnDataState</td><td>State</td></tr>
 * <tr><td>BpmnItemAwareElement</td><td>State</td></tr>
 * <tr><td>BpmnMessage</td><td>State</td></tr>
 * 
 * </table>
 */
@objid ("f911e5a9-adc1-4456-a9dc-3462e921277c")
public class StateExpert implements IMdaExpert {
    @objid ("f009ce16-ba72-406e-a337-9c726a804291")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnDataState"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage")));
    }

    @objid ("b1788bfe-20c0-45a3-b298-2ce5f0644257")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnDataState"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnMessage")));
    }

    @objid ("7e48fec1-14d1-4de8-b973-ff2632a14dfe")
    @Override
    public boolean canSource(ElementScope linkScope, MObject fromElement) {
        MMetamodel metamodel = linkScope.getMetaclass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnDataState"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnMessage")));
    }

    @objid ("f8358baf-db91-4b8b-915c-aedaf7de7b8a")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Standard.State")));
    }

    @objid ("7bace48e-76fd-4ab5-8f12-f82940504d58")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkElement, MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Standard.State")));
    }

    @objid ("7e3b63e9-a8bc-420e-924e-1fd7b214cd64")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnDataState"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.State")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.State")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.State"))));
    }

    @objid ("4ef4f77b-3f05-446e-a24a-7cd8cc0e03f3")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject fromElement, MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnDataState"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.State")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.State")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.State"))));
    }

    @objid ("a418cd53-eab4-4e49-b5a7-fae677919737")
    @Override
    public Collection<MClass> getPossibleSourceMetaclasses(Stereotype linkStereotype, MClass targetMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.State")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnDataState");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.State")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnItemAwareElement");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.State")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnMessage");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("8b1fbc57-41e6-4863-9074-01f20b20ef0f")
    @Override
    public Collection<MClass> getPossibleTargetMetaclasses(Stereotype linkStereotype, MClass sourceMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnDataState")))) {
        	MClass mc = metamodel.getMClass("Standard.State");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement")))) {
        	MClass mc = metamodel.getMClass("Standard.State");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage")))) {
        	MClass mc = metamodel.getMClass("Standard.State");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("b65b8603-d637-4baa-8410-2f496ac837d6")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

}
