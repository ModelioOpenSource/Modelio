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

    @objid ("8de1dac3-656b-4123-8646-e60f92186eb4")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnEvent")));
    }

    @objid ("e46436e4-87f3-4c0a-9c3e-23d720757acd")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkElement, MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnEvent")));
    }

    @objid ("46d42613-712f-4bc7-9bfc-2bf046d176a3")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationEvent"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessEvent"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Archimate.TechnologyEvent"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.Signal")));
    }

    @objid ("8321ac66-2052-43e1-8dff-bc284a83ecc2")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkElement, MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationEvent"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessEvent"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.TechnologyEvent"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Signal")));
    }

    @objid ("016c506f-46a8-44b5-8b95-1e606f16f0a3")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationEvent")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.BusinessEvent")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toMetaclass.hasBase(metamodel.getMClass("Archimate.TechnologyEvent")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.Signal"))));
    }

    @objid ("2a0cff0e-f381-4fff-b1ca-503b37fc9f6f")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject fromElement, MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationEvent")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.BusinessEvent")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Archimate.TechnologyEvent")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnEvent"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.Signal"))));
    }

    @objid ("02dff190-5ca3-4f5d-8706-3d6b35b6ad2c")
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

    @objid ("cb96cae8-0d9a-401b-835e-94eeb4c283d1")
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
