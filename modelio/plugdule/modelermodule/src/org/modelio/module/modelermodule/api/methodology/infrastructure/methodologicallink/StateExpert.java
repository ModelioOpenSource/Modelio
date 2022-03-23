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
    @objid ("8bc62ee2-1120-4ff1-9719-1682e72704c1")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnDataState"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage")));
    }

    @objid ("4f590a36-6a7b-420d-ab2a-6154bcd9b86f")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnDataState"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnMessage")));
    }

    @objid ("58935bdd-2a96-4719-a64e-495c940fee97")
    @Override
    public boolean canSource(ElementScope linkScope, MObject fromElement) {
        MMetamodel metamodel = linkScope.getMetaclass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnDataState"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnMessage")));
    }

    @objid ("5c7ad3ed-1e31-45ae-aa36-da32472217d3")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Standard.State")));
    }

    @objid ("214d3349-33f9-450d-a7d3-99bacb354c6c")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkElement, MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Standard.State")));
    }

    @objid ("3d93742d-3fb9-493b-b852-6279dc572ad0")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnDataState"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.State")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.State")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.State"))));
    }

    @objid ("4120b4c6-8be4-4ab9-9d0e-3500249b54c8")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject fromElement, MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnDataState"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.State")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.State")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.State"))));
    }

    @objid ("7dc1551d-854d-474d-a306-a3adf7808af6")
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

    @objid ("8ed758b7-cf7e-4d77-a3e3-124e751317e8")
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

    @objid ("b17abbef-7710-4243-b5ad-939adb2ab2e8")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

}
