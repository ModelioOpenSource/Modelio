package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * MDA expert to handle a {@link Dependency} with << implement >> stereotype.
 * <p>
 * This link can be created between:
 * </p>
 * <table summary="Creation rules" border="1">
 * <tr>
 * <th>Possible source</th>
 * <th>Possible target</th>
 * </tr>
 * <tr><td>ApplicationComponent</td><td>BusinessRule</td></tr>
 * <tr><td>ApplicationComponent</td><td>BpmnProcess</td></tr>
 * <tr><td>ApplicationComponent</td><td>BpmnTask</td></tr>
 * <tr><td>BpmnProcess</td><td>BusinessRule</td></tr>
 * <tr><td>BpmnTask</td><td>BusinessRule</td></tr>
 * <tr><td>GeneralClass</td><td>BusinessRule</td></tr>
 * <tr><td>GeneralClass</td><td>BpmnProcess</td></tr>
 * <tr><td>GeneralClass</td><td>BpmnTask</td></tr>
 * 
 * </table>
 */
@objid ("fa7c2c26-a27f-424c-aa5a-7eee2ffcc109")
public class ImplementExpert implements IMdaExpert {
    @objid ("725a17cb-85f3-4e11-922b-c0822a23af97")
    @Override
    public boolean canSource(final Stereotype linkStereotype, final MClass linkMetaclass, final MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnTask"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Standard.GeneralClass")));
    }

    @objid ("9350c5df-46dc-40cf-b301-1c62552abbe8")
    @Override
    public boolean canSource(final Stereotype linkStereotype, final MObject linkElement, final MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnTask"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.GeneralClass")));
    }

    @objid ("86bec811-1089-4f78-9f74-451ab64dabed")
    @Override
    public boolean canTarget(final Stereotype linkStereotype, final MClass linkMetaclass, final MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (toMetaclass.hasBase(metamodel.getMClass("Standard.BpmnTask")));
    }

    @objid ("d841fd3b-d6ba-4fe0-a2c1-3a94b9f5dd89")
    @Override
    public boolean canTarget(final Stereotype linkStereotype, final MObject linkElement, final MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.BusinessRule"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess"))) || 
        (toElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnTask")));
    }

    @objid ("bd7cde75-5cca-4483-b12c-6173d5fb3d6c")
    @Override
    public boolean canLink(final Stereotype linkStereotype, final MClass linkMetaclass, final MClass fromMetaclass, final MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.BpmnTask")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.BpmnTask"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.GeneralClass"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.GeneralClass"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.BpmnProcess")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Standard.GeneralClass"))) && (toMetaclass.hasBase(metamodel.getMClass("Standard.BpmnTask"))));
    }

    @objid ("cbe01bd0-2144-4233-ae57-a9958df8deb2")
    @Override
    public boolean canLink(final Stereotype linkStereotype, final MClass linkMetaclass, final MObject fromElement, final MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.BusinessRule")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Archimate.ApplicationComponent"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnTask")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.BusinessRule")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnTask"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.BusinessRule")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.GeneralClass"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.BusinessRule")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.GeneralClass"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnProcess")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.GeneralClass"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Standard.BpmnTask"))));
    }

}
