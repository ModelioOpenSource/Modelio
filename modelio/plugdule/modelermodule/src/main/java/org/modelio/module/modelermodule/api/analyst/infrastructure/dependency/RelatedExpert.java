package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * MDA expert to handle a {@link Dependency} with << related >> stereotype.
 * <p>
 * This link can be created between:
 * </p>
 * <table summary="Creation rules" border="1">
 * <tr>
 * <th>Possible source</th>
 * <th>Possible target</th>
 * </tr>
 * <tr>
 * <td>BusinessRule</td>
 * <td>BusinessRule</td>
 * </tr>
 * <tr>
 * <td>Term</td>
 * <td>Term</td>
 * </tr>
 * 
 * </table>
 */
@objid ("75d0b639-3ce7-464f-87b3-9e341cfc9a5c")
public class RelatedExpert implements IMdaExpert {
    @objid ("6c4a003c-73dc-498d-bfc6-df90e43947a7")
    @Override
    public boolean canLink(final Stereotype linkStereotype, final MClass linkMetaclass, final MClass fromMetaclass, final MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule"))) ||
                (fromMetaclass.hasBase(metamodel.getMClass("Analyst.Term"))) && (toMetaclass.hasBase(metamodel.getMClass("Analyst.Term"))));
    }

    @objid ("ba81e688-4c7c-4358-a62e-48063f15ed0e")
    @Override
    public boolean canLink(final Stereotype linkStereotype, final MClass linkMetaclass, final MObject fromElement, final MObject toElement) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return ((fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.BusinessRule"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.BusinessRule"))) ||
                (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Term"))) && (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Term"))));
    }

    @objid ("22d4f929-97f6-4c38-b974-b4083d168752")
    @Override
    public boolean canSource(final Stereotype linkStereotype, final MClass linkMetaclass, final MClass fromMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (fromMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule")) ||
                fromMetaclass.hasBase(metamodel.getMClass("Analyst.Term")));
    }

    @objid ("f9a078d8-2b74-4091-8ad2-173882129a82")
    @Override
    public boolean canSource(final Stereotype linkStereotype, final MObject linkElement, final MObject fromElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.BusinessRule")) ||
                fromElement.getMClass().hasBase(metamodel.getMClass("Analyst.Term")));
    }

    @objid ("39a4996b-cb78-4e94-aac8-47099320d0df")
    @Override
    public boolean canTarget(final Stereotype linkStereotype, final MClass linkMetaclass, final MClass toMetaclass) {
        MMetamodel metamodel = linkMetaclass.getMetamodel();
        return (toMetaclass.hasBase(metamodel.getMClass("Analyst.BusinessRule")) ||
                toMetaclass.hasBase(metamodel.getMClass("Analyst.Term")));
    }

    @objid ("0a7a3cf5-d128-4ff5-bfe7-3a0dc043c5c0")
    @Override
    public boolean canTarget(final Stereotype linkStereotype, final MObject linkElement, final MObject toElement) {
        MMetamodel metamodel = linkElement.getMClass().getMetamodel();
        return (toElement.getMClass().hasBase(metamodel.getMClass("Analyst.BusinessRule")) ||
                toElement.getMClass().hasBase(metamodel.getMClass("Analyst.Term")));
    }

}
