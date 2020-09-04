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
 * MDA expert to handle a {@link MethodologicalLink} with << Event >> stereotype.
 * <p>
 * This link can be created between:
 * </p>
 * <table summary="Creation rules" border="1">
 * <tr>
 *   <th>Possible source</th>
 *   <th>Possible target</th>
 * </tr>
 * <tr><td>BpmnEvent</td><td>ApplicationEvent</td></tr>
 * <tr><td>BpmnEvent</td><td>BusinessEvent</td></tr>
 * <tr><td>BpmnEvent</td><td>TechnologyEvent</td></tr>
 * <tr><td>BpmnEvent</td><td>Signal</td></tr>
 * 
 * </table>
 */
@objid ("d380f89f-9140-46b8-bf10-3bd06e004afa")
public class EventExpert implements IMdaExpert {
    @objid ("d81265d0-780a-4437-8e1d-67c4a3c811f2")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

    @objid ("cd2de9d2-5bef-4d12-9eef-30e56c5ac881")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnEvent")));
    }

    @objid ("c7e52e4a-e220-4b0c-87aa-7b5f9527d1e5")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnEvent")));
    }

    @objid ("580b0bdc-6016-4f07-8494-92b5b77abc15")
    @Override
    public boolean canSource(ElementScope linkScope, MObject fromElement) {
        MMetamodel metamodel = linkScope.getMetaclass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnEvent")));
    }

    @objid ("3e3f0f37-c909-4f36-9db5-dd7aff6c58a7")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationEvent"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessEvent"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.TechnologyEvent"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Signal")));
    }

    @objid ("c75c1aae-b142-4fdd-a049-d017e80d1dbb")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkElement, MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationEvent"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessEvent"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.TechnologyEvent"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Signal")));
    }

    @objid ("99eb458b-969f-4a4d-a4d7-bcd3d5f81235")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationEvent")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessEvent")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.TechnologyEvent")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Signal"))));
    }

    @objid ("db0f57ae-b677-402a-8dd8-646e6be655c5")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject fromElement, MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationEvent")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessEvent")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.TechnologyEvent")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Signal"))));
    }

    @objid ("885582f1-fd56-43f6-81b9-b4c2074ced7b")
    @Override
    public Collection<MClass> getPossibleSourceMetaclasses(Stereotype linkStereotype, MClass targetMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationEvent")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnEvent");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessEvent")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnEvent");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.TechnologyEvent")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnEvent");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Signal")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnEvent");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("6248a388-126f-4839-83b5-99efcef85523")
    @Override
    public Collection<MClass> getPossibleTargetMetaclasses(Stereotype linkStereotype, MClass sourceMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnEvent")))) {
        	MClass mc = metamodel.getMClass("Archimate.ApplicationEvent");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnEvent")))) {
        	MClass mc = metamodel.getMClass("Archimate.BusinessEvent");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnEvent")))) {
        	MClass mc = metamodel.getMClass("Archimate.TechnologyEvent");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnEvent")))) {
        	MClass mc = metamodel.getMClass("Standard.Signal");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

}
