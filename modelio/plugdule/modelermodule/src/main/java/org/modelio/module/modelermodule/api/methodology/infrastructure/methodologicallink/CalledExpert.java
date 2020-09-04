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
 * MDA expert to handle a {@link MethodologicalLink} with << Called >> stereotype.
 * <p>
 * This link can be created between:
 * </p>
 * <table summary="Creation rules" border="1">
 * <tr>
 *   <th>Possible source</th>
 *   <th>Possible target</th>
 * </tr>
 * <tr><td>BpmnCallActivity</td><td>ApplicationProcess</td></tr>
 * <tr><td>BpmnCallActivity</td><td>BusinessProcess</td></tr>
 * <tr><td>BpmnCallActivity</td><td>Operation</td></tr>
 * <tr><td>BpmnCallActivity</td><td>Behavior</td></tr>
 * <tr><td>BpmnReceiveTask</td><td>Operation</td></tr>
 * <tr><td>BpmnSendTask</td><td>Operation</td></tr>
 * <tr><td>BpmnServiceTask</td><td>Operation</td></tr>
 * <tr><td>BpmnServiceTask</td><td>BusinessService</td></tr>
 * 
 * </table>
 */
@objid ("c3a412dc-e6d9-4373-9d46-a48ce82c078d")
public class CalledExpert implements IMdaExpert {
    @objid ("baeb67fa-45a7-4083-91d1-3f580dbdb3c6")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

    @objid ("98d61efa-189e-45ae-bcb2-1ca6330125e4")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCallActivity"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnReceiveTask"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnSendTask"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnServiceTask")));
    }

    @objid ("75b5876b-8c12-454a-a839-5cbbc54a7018")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnCallActivity"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnReceiveTask"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnSendTask"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnServiceTask")));
    }

    @objid ("7e7a2cdd-28c1-48ca-b044-77db7c4ef47d")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationProcess"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessProcess"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Operation"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Behavior"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessService")));
    }

    @objid ("1b468589-7859-43b9-96b0-3de86c414c93")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkElement, MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationProcess"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessProcess"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Operation"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Behavior"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessService")));
    }

    @objid ("f9e1b22e-e59a-4bf1-9916-119c5d524195")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCallActivity"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationProcess")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCallActivity"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessProcess")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCallActivity"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Operation")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCallActivity"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Behavior")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnReceiveTask"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Operation")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnSendTask"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Operation")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnServiceTask"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Operation")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnServiceTask"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessService"))));
    }

    @objid ("a455b967-ec5a-41bc-ab91-12b7bde4b5ed")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject fromElement, MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnCallActivity"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationProcess")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnCallActivity"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessProcess")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnCallActivity"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Operation")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnCallActivity"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Behavior")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnReceiveTask"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Operation")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnSendTask"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Operation")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnServiceTask"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Operation")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnServiceTask"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessService"))));
    }

    @objid ("c61a4758-f733-4348-a3c1-858d8814ef6c")
    @Override
    public Collection<MClass> getPossibleSourceMetaclasses(Stereotype linkStereotype, MClass targetMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationProcess")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnCallActivity");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessProcess")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnCallActivity");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Operation")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnCallActivity");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Behavior")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnCallActivity");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Operation")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnReceiveTask");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Operation")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnSendTask");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Operation")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnServiceTask");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessService")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnServiceTask");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("f600449f-fc42-4460-859b-42d141c6d26f")
    @Override
    public Collection<MClass> getPossibleTargetMetaclasses(Stereotype linkStereotype, MClass sourceMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCallActivity")))) {
        	MClass mc = metamodel.getMClass("Archimate.ApplicationProcess");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCallActivity")))) {
        	MClass mc = metamodel.getMClass("Archimate.BusinessProcess");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCallActivity")))) {
        	MClass mc = metamodel.getMClass("Standard.Operation");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnCallActivity")))) {
        	MClass mc = metamodel.getMClass("Standard.Behavior");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnReceiveTask")))) {
        	MClass mc = metamodel.getMClass("Standard.Operation");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnSendTask")))) {
        	MClass mc = metamodel.getMClass("Standard.Operation");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnServiceTask")))) {
        	MClass mc = metamodel.getMClass("Standard.Operation");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnServiceTask")))) {
        	MClass mc = metamodel.getMClass("Archimate.BusinessService");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

}
