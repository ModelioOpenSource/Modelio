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
    @objid ("fb34788f-46c1-41b3-9930-ba2eda52af40")
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

    @objid ("b399db48-4bc1-49a2-b78c-5fefe2e9416c")
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

    @objid ("dea25d1e-1a9f-4d34-822c-e1bc7b3f6180")
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

    @objid ("46e763a4-980e-423b-b3d5-2272e7674ea4")
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

    @objid ("f8bbeb9b-1043-45e3-8c8b-bd061296c06a")
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

    @objid ("de821a2d-e382-4b67-8bd6-3abe809c0790")
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

    @objid ("85e12589-9882-47cf-8011-a8204d82c6d9")
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

    @objid ("2c30c01e-5b1e-44f2-9f77-4b09e3693ef6")
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

    @objid ("98e2dd98-05dc-4aaa-b14c-1997eded9a98")
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

    @objid ("4cad5361-2399-40c1-a4a9-4f91d201dc62")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

}
