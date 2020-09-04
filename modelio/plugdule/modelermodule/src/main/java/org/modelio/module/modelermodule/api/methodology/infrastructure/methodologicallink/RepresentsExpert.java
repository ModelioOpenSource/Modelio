/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
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
 * 
 * </table>
 */
@objid ("7f80e47f-47f2-4c75-a3e3-ee16add59e9a")
public class RepresentsExpert implements IMdaExpert {
    @objid ("b33df862-9c5b-4fec-9fa2-5c5aa181de28")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

    @objid ("fdadd4f0-7b76-417c-8c02-7d1218628956")
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

    @objid ("8e809407-4178-4d1b-9641-376785b76493")
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

    @objid ("47a9d38f-6167-4146-92f6-2db14ae0e837")
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
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Classifier")));
    }

    @objid ("52a3c81b-7ef1-4bc1-bdae-b3c5370dec0c")
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
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Classifier")));
    }

    @objid ("f12ac5dc-6bff-4fc2-b6e8-cf1a33182b0a")
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
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.Product"))));
    }

    @objid ("431cdb37-d321-400b-826a-c461676767c5")
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
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnMessage"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.Product"))));
    }

    @objid ("f2d3daba-c37e-4908-9027-f8539af897a9")
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
        return metaclasses;
    }

    @objid ("b690ad2f-4891-460d-9396-b83872a3fcbd")
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
        return metaclasses;
    }

}
