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
 * MDA expert to handle a {@link MethodologicalLink} with << Represents >> stereotype.
 * <p>
 * This link can be created between:
 * </p>
 * <table summary="Creation rules" border="1">
 * <tr>
 *   <th>Possible source</th>
 *   <th>Possible target</th>
 * </tr>
 * <tr><td>BpmnDataObject</td><td>Artifact</td></tr>
 * <tr><td>BpmnDataObject</td><td>BusinessObject</td></tr>
 * <tr><td>BpmnDataObject</td><td>DataObject</td></tr>
 * <tr><td>BpmnDataObject</td><td>Product</td></tr>
 * <tr><td>BpmnDataInput</td><td>Parameter</td></tr>
 * <tr><td>BpmnDataOutput</td><td>Parameter</td></tr>
 * <tr><td>BpmnItemAwareElement</td><td>AssociationEnd</td></tr>
 * <tr><td>BpmnItemAwareElement</td><td>Attribute</td></tr>
 * <tr><td>BpmnItemAwareElement</td><td>Instance</td></tr>
 * <tr><td>BpmnItemAwareElement</td><td>Classifier</td></tr>
 * <tr><td>BpmnMessage</td><td>Classifier</td></tr>
 * <tr><td>BpmnParticipant</td><td>Classifier</td></tr>
 * <tr><td>BpmnMessage</td><td>BusinessObject</td></tr>
 * <tr><td>BpmnMessage</td><td>DataObject</td></tr>
 * <tr><td>BpmnMessage</td><td>Artifact</td></tr>
 * <tr><td>BpmnMessage</td><td>Product</td></tr>
 * <tr><td>BpmnMessage</td><td>BusinessEvent</td></tr>
 * <tr><td>BpmnMessage</td><td>ApplicationEvent</td></tr>
 * <tr><td>BpmnMessage</td><td>TechnologyEvent</td></tr>
 * 
 * </table>
 */
@objid ("7f80e47f-47f2-4c75-a3e3-ee16add59e9a")
public class RepresentsExpert implements IMdaExpert {
    @objid ("b1a490eb-6f9e-456c-8448-766e3557bff7")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnDataObject"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnDataInput"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnDataOutput"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnParticipant")));
    }

    @objid ("5d53f59c-73af-488c-b877-6fed9dde344f")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnDataObject"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnDataInput"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnDataOutput"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnMessage"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnParticipant")));
    }

    @objid ("b5caa608-eae4-4ab3-9bfc-188bff747f5c")
    @Override
    public boolean canSource(ElementScope linkScope, MObject fromElement) {
        MMetamodel metamodel = linkScope.getMetaclass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnDataObject"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnDataInput"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnDataOutput"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnMessage"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnParticipant")));
    }

    @objid ("fa6b99d0-ccae-4d75-8271-60550a8b2b45")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Archimate.Artifact"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessObject"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.DataObject"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.Product"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Parameter"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.AssociationEnd"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Attribute"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Instance"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Classifier"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessEvent"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationEvent"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.TechnologyEvent")));
    }

    @objid ("191430a7-615a-4eca-bfd3-271d9a0420cd")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkElement, MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.Artifact"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessObject"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.DataObject"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.Product"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Parameter"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.AssociationEnd"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Attribute"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Instance"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Classifier"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessEvent"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationEvent"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.TechnologyEvent")));
    }

    @objid ("a5ba0eb8-f1b2-4715-a61e-cda3819763a1")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnDataObject"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.Artifact")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnDataObject"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessObject")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnDataObject"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.DataObject")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnDataObject"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.Product")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnDataInput"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Parameter")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnDataOutput"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Parameter")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.AssociationEnd")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Attribute")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Instance")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Classifier")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Classifier")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnParticipant"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Classifier")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessObject")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.DataObject")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.Artifact")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.Product")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessEvent")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationEvent")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.TechnologyEvent"))));
    }

    @objid ("1b34e4c2-1ef1-44b6-af0c-1c048770a612")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject fromElement, MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnDataObject"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.Artifact")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnDataObject"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessObject")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnDataObject"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.DataObject")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnDataObject"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.Product")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnDataInput"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Parameter")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnDataOutput"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Parameter")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.AssociationEnd")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Attribute")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Instance")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Classifier")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Classifier")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnParticipant"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Classifier")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessObject")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.DataObject")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.Artifact")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.Product")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessEvent")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationEvent")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.TechnologyEvent"))));
    }

    @objid ("622c7a37-3efc-4052-a1ce-12020c201b20")
    @Override
    public Collection<MClass> getPossibleSourceMetaclasses(Stereotype linkStereotype, MClass targetMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.Artifact")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnDataObject");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessObject")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnDataObject");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.DataObject")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnDataObject");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.Product")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnDataObject");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Parameter")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnDataInput");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Parameter")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnDataOutput");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.AssociationEnd")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnItemAwareElement");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Attribute")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnItemAwareElement");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Instance")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnItemAwareElement");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Classifier")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnItemAwareElement");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Classifier")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnMessage");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Classifier")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnParticipant");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessObject")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnMessage");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.DataObject")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnMessage");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.Artifact")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnMessage");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.Product")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnMessage");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessEvent")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnMessage");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationEvent")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnMessage");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.TechnologyEvent")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnMessage");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("8e324826-aa21-4c86-ba8d-e304567da13a")
    @Override
    public Collection<MClass> getPossibleTargetMetaclasses(Stereotype linkStereotype, MClass sourceMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnDataObject")))) {
        	MClass mc = metamodel.getMClass("Archimate.Artifact");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnDataObject")))) {
        	MClass mc = metamodel.getMClass("Archimate.BusinessObject");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnDataObject")))) {
        	MClass mc = metamodel.getMClass("Archimate.DataObject");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnDataObject")))) {
        	MClass mc = metamodel.getMClass("Archimate.Product");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnDataInput")))) {
        	MClass mc = metamodel.getMClass("Standard.Parameter");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnDataOutput")))) {
        	MClass mc = metamodel.getMClass("Standard.Parameter");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement")))) {
        	MClass mc = metamodel.getMClass("Standard.AssociationEnd");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement")))) {
        	MClass mc = metamodel.getMClass("Standard.Attribute");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement")))) {
        	MClass mc = metamodel.getMClass("Standard.Instance");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnItemAwareElement")))) {
        	MClass mc = metamodel.getMClass("Standard.Classifier");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage")))) {
        	MClass mc = metamodel.getMClass("Standard.Classifier");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnParticipant")))) {
        	MClass mc = metamodel.getMClass("Standard.Classifier");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage")))) {
        	MClass mc = metamodel.getMClass("Archimate.BusinessObject");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage")))) {
        	MClass mc = metamodel.getMClass("Archimate.DataObject");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage")))) {
        	MClass mc = metamodel.getMClass("Archimate.Artifact");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage")))) {
        	MClass mc = metamodel.getMClass("Archimate.Product");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage")))) {
        	MClass mc = metamodel.getMClass("Archimate.BusinessEvent");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage")))) {
        	MClass mc = metamodel.getMClass("Archimate.ApplicationEvent");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage")))) {
        	MClass mc = metamodel.getMClass("Archimate.TechnologyEvent");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("4fe17dad-933d-4e6f-8e4f-ac00dbe5d2db")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

}
