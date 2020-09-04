package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * MDA expert to handle a {@link Dependency} with << derive >> stereotype.
 * <p>
 * This link can be created between:
 * </p>
 * <table summary="Creation rules" border="1">
 * <tr>
 * <th>Possible source</th>
 * <th>Possible target</th>
 * </tr>
 * <tr><td>UseCase</td><td>Requirement</td></tr>
 * <tr><td>Requirement</td><td>Requirement</td></tr>
 * 
 * </table>
 */
@objid ("d09df54c-da2e-4104-b333-69fd57674fd9")
public class DeriveExpert implements IMdaExpert {
    @objid ("47b36163-3e7b-41de-96f5-edb28ebdde68")
    @Override
    public boolean canLink(final Stereotype linkStereotype, final MClass linkMetaclass, final MClass fromMetaclass, final MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Standard.UseCase"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement")))) || 
        ((fromMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement"))));
    }

    @objid ("48fda9cb-202c-4333-a51d-a68ee029edb6")
    @Override
    public boolean canLink(final Stereotype linkStereotype, final MClass linkMetaclass, final MObject fromElement, final MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Standard.UseCase"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement")))) || 
        ((fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement"))));
    }

    @objid ("26c1f9a0-16ef-4047-9b76-cc10a7d12a8a")
    @Override
    public boolean canSource(final Stereotype linkStereotype, final MClass linkMetaclass, final MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Standard.UseCase"))) || 
        (fromMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement")));
    }

    @objid ("d35ff17b-2c4f-44af-9466-528df1b6dc07")
    @Override
    public boolean canSource(final Stereotype linkStereotype, final MObject linkElement, final MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Standard.UseCase"))) || 
        (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement")));
    }

    @objid ("2a277a57-967e-4e75-9d03-8e75ca5015c6")
    @Override
    public boolean canTarget(final Stereotype linkStereotype, final MClass linkMetaclass, final MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Analyst.Requirement")));
    }

    @objid ("19380329-7d5c-4b04-8fc5-112fa1db93af")
    @Override
    public boolean canTarget(final Stereotype linkStereotype, final MObject linkElement, final MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Requirement")));
    }

}
