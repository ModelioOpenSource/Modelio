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
 * MDA expert to handle a {@link MethodologicalLink} with << PartitionElement >> stereotype.
 * <p>
 * This link can be created between:
 * </p>
 * <table summary="Creation rules" border="1">
 * <tr>
 *   <th>Possible source</th>
 *   <th>Possible target</th>
 * </tr>
 * <tr><td>BpmnLane</td><td>BusinessActor</td></tr>
 * <tr><td>BpmnLane</td><td>BusinessRole</td></tr>
 * <tr><td>BpmnLane</td><td>Actor</td></tr>
 * <tr><td>BpmnLane</td><td>Package</td></tr>
 * <tr><td>BpmnLane</td><td>Component</td></tr>
 * <tr><td>BpmnLane</td><td>Class</td></tr>
 * <tr><td>BpmnLane</td><td>Node</td></tr>
 * <tr><td>BpmnLane</td><td>ApplicationComponent</td></tr>
 * 
 * </table>
 */
@objid ("4db80c7c-ad0f-452f-80b3-0d024ff66c60")
public class PartitionElementExpert implements IMdaExpert {
    @objid ("82de0faf-5c14-4fe9-acb2-849fb4377094")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        return false;
    }

    @objid ("7cff9361-b629-4768-90af-4bcb9334e34a")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane")));
    }

    @objid ("248d5bbc-addf-4974-a0a7-41061a387eea")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane")));
    }

    @objid ("c752a382-c791-4c7f-aa8f-d03bf4ec0c7f")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessActor"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessRole"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Actor"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Package"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Component"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Class"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Node"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent")));
    }

    @objid ("13aa180a-771a-4fff-a685-8f443f47d9f5")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkElement, MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessActor"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessRole"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Actor"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Package"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Component"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Class"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Node"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationComponent")));
    }

    @objid ("3a17ad8a-6cf7-43f1-829f-86d75b26ca6f")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessActor")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessRole")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Actor")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Package")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Component")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Class")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Node")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))));
    }

    @objid ("613fdd50-52cf-43ae-8309-c99a9e899c02")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject fromElement, MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessActor")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessRole")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Actor")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Package")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Component")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Class")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Node")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnLane"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))));
    }

    @objid ("03a616c1-ba1e-42b1-a2e3-582b37aec6a3")
    @Override
    public Collection<MClass> getPossibleSourceMetaclasses(Stereotype linkStereotype, MClass targetMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessActor")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnLane");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessRole")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnLane");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Actor")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnLane");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Package")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnLane");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Component")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnLane");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Class")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnLane");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Standard.Node")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnLane");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((targetMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent")))) {
        	MClass mc = metamodel.getMClass("Standard.BpmnLane");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

    @objid ("f6ec93ce-9b59-4d76-8837-95ec9e2103ef")
    @Override
    public Collection<MClass> getPossibleTargetMetaclasses(Stereotype linkStereotype, MClass sourceMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        Set<MClass> metaclasses = new HashSet<>();
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane")))) {
        	MClass mc = metamodel.getMClass("Archimate.BusinessActor");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane")))) {
        	MClass mc = metamodel.getMClass("Archimate.BusinessRole");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane")))) {
        	MClass mc = metamodel.getMClass("Standard.Actor");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane")))) {
        	MClass mc = metamodel.getMClass("Standard.Package");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane")))) {
        	MClass mc = metamodel.getMClass("Standard.Component");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane")))) {
        	MClass mc = metamodel.getMClass("Standard.Class");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane")))) {
        	MClass mc = metamodel.getMClass("Standard.Node");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        if ((sourceMetaclass.hasBase(metamodel.getMClass("Standard.BpmnLane")))) {
        	MClass mc = metamodel.getMClass("Archimate.ApplicationComponent");
        	metaclasses.add(mc);
        	metaclasses.addAll(mc.getSub(true));
        }
        return metaclasses;
    }

}
