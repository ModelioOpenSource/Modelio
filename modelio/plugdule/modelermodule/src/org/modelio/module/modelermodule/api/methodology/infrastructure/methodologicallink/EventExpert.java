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
    @objid ("18d5c27f-5b44-4a3c-bc26-7c1adff469a3")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnEvent")));
    }

    @objid ("5cabe99b-64fc-4634-8a00-04541f070199")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnEvent")));
    }

    @objid ("b31c0be9-e4bb-4eea-aedd-518c35c42fa5")
    @Override
    public boolean canSource(ElementScope linkScope, MObject fromElement) {
        MMetamodel metamodel = linkScope.getMetaclass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnEvent")));
    }

    @objid ("f377f619-78a2-4a69-b3d6-19c1b4941d5c")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationEvent"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessEvent"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.TechnologyEvent"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Signal")));
    }

    @objid ("d81ee8cd-a2e7-4066-b544-76d139d2b08c")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkElement, MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationEvent"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessEvent"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.TechnologyEvent"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Signal")));
    }

    @objid ("8d4c5a47-972b-41e8-a3b2-a78ab45038d9")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationEvent")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessEvent")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.TechnologyEvent")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Signal"))));
    }

    @objid ("8c8da5e9-739a-4c54-8ef9-9d02436cc245")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject fromElement, MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationEvent")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessEvent")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.TechnologyEvent")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Signal"))));
    }

    @objid ("18a490db-7596-49b4-aca0-f381d54af8da")
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

    @objid ("7b901335-3a4c-431d-9786-8e2e85a58b65")
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

    @objid ("5a55eed6-b3a0-4a87-b210-3fc7b71955ea")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

}
