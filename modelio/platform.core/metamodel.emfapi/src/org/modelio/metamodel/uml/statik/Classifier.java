/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.infrastructure.Substitution;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.ComponentRealization;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.RaisedException;

/**
 * Classifier v0.0.9054
 * 
 * 
 * A Classifier is an abstract view of the most important metaclasses such as Class, UseCase, Actor, Component and Node. 
 * 
 * A Classifier notably factorizes the aggregation to Features. The Classifier can act as a structured Classifier (as defined in UML 2.0). In this case, its internal structure, which expresses its configuration in terms of Parts and Ports, is defined through a dedicated Collaboration. 
 * 
 * A Classifier is owned by a NameSpace.
 */
@objid ("0003645c-c4bf-1fd8-97fe-001ec947cd2a")
public interface Classifier extends NameSpace {
    /**
     * The metaclass simple name.
     */
    @objid ("8ca37bb9-bca9-4c8e-a204-256bcd97970c")
    public static final String MNAME = "Classifier";

    /**
     * The metaclass qualified name.
     */
    @objid ("b0704174-76ef-4f4c-a3fc-cb3d1802051f")
    public static final String MQNAME = "Standard.Classifier";

    /**
     * Getter for relation 'Classifier->OwnedOperation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8a2df45e-3b02-46a2-a775-863157dda511")
    EList<Operation> getOwnedOperation();

    /**
     * Filtered Getter for relation 'Classifier->OwnedOperation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("af437304-215e-4826-be00-f516480ea9a1")
    <T extends Operation> List<T> getOwnedOperation(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Classifier->Representation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2ff502d8-738b-43ee-9a98-9d76175870b6")
    EList<InformationItem> getRepresentation();

    /**
     * Filtered Getter for relation 'Classifier->Representation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("dece95bd-4a0b-4939-80ec-ee1fb2c9cf17")
    <T extends InformationItem> List<T> getRepresentation(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Classifier->Substitued'
     * 
     * Metamodel description:
     * <i>Specifies the Classifier to which it can be substituted.</i>
     */
    @objid ("ad7e7baa-beea-4475-abaa-41539b3fc06d")
    EList<Substitution> getSubstitued();

    /**
     * Filtered Getter for relation 'Classifier->Substitued'
     * 
     * Metamodel description:
     * <i>Specifies the Classifier to which it can be substituted.</i>
     */
    @objid ("ce4d6020-6c29-4280-a5af-dc40ab51a028")
    <T extends Substitution> List<T> getSubstitued(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Classifier->OwnedAttribute'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7673c835-23e3-4c53-97c6-537bba926a73")
    EList<Attribute> getOwnedAttribute();

    /**
     * Filtered Getter for relation 'Classifier->OwnedAttribute'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("09ee2d21-ae60-4d8b-b187-ef72c48956f1")
    <T extends Attribute> List<T> getOwnedAttribute(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Classifier->OwnedNaryEnd'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("29a565b8-ad19-4e9b-92b8-8d49c790c7f1")
    EList<NaryAssociationEnd> getOwnedNaryEnd();

    /**
     * Filtered Getter for relation 'Classifier->OwnedNaryEnd'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("84009142-9cdf-4aaa-83f2-637c80e1270b")
    <T extends NaryAssociationEnd> List<T> getOwnedNaryEnd(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Classifier->Conveyer'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("83576a24-2c68-4576-9170-f8625118b20b")
    EList<InformationFlow> getConveyer();

    /**
     * Filtered Getter for relation 'Classifier->Conveyer'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("22161d8d-334f-4ef3-a45c-246f0c768a1b")
    <T extends InformationFlow> List<T> getConveyer(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Classifier->SubstitutingSubstitution'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("aa568b24-ea93-41df-922f-002b2ea821d8")
    EList<Substitution> getSubstitutingSubstitution();

    /**
     * Filtered Getter for relation 'Classifier->SubstitutingSubstitution'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5ed3ae7b-4a27-4159-8a9b-980292afd593")
    <T extends Substitution> List<T> getSubstitutingSubstitution(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Classifier->TargetingEnd'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7ee8f13a-46e8-4779-b4aa-92045e3d9ee2")
    EList<AssociationEnd> getTargetingEnd();

    /**
     * Filtered Getter for relation 'Classifier->TargetingEnd'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("fd9bd921-7222-4136-9c87-9e81e9c71841")
    <T extends AssociationEnd> List<T> getTargetingEnd(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Classifier->OwnedEnd'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a1dad718-eb2d-458a-8b19-707ba043ac9e")
    EList<AssociationEnd> getOwnedEnd();

    /**
     * Filtered Getter for relation 'Classifier->OwnedEnd'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6b471a16-2a91-452b-b22f-7b04805c1911")
    <T extends AssociationEnd> List<T> getOwnedEnd(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Classifier->Throwing'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7aafb705-ccb6-4fe0-b749-6e1a309baea8")
    EList<RaisedException> getThrowing();

    /**
     * Filtered Getter for relation 'Classifier->Throwing'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("fb6e76c8-cc1e-4066-8fda-86d6b267eb0d")
    <T extends RaisedException> List<T> getThrowing(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Classifier->InternalStructure'
     * 
     * Metamodel description:
     * <i>Parts owned by the internal structure of the Class. These Parts represent the structure of instances of the Class, once they are instanciated.</i>
     */
    @objid ("58ee51b7-dca3-4a47-8d95-9524c7f7d01d")
    EList<BindableInstance> getInternalStructure();

    /**
     * Filtered Getter for relation 'Classifier->InternalStructure'
     * 
     * Metamodel description:
     * <i>Parts owned by the internal structure of the Class. These Parts represent the structure of instances of the Class, once they are instanciated.</i>
     */
    @objid ("4f8f408d-ea63-4b31-9eb3-d2c3cb1f85cc")
    <T extends BindableInstance> List<T> getInternalStructure(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Classifier->RealizedComponent'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a4b685a4-6602-4b51-806c-217657839167")
    EList<ComponentRealization> getRealizedComponent();

    /**
     * Filtered Getter for relation 'Classifier->RealizedComponent'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f28a25fb-b402-4367-a400-7d8ab8cbbfe4")
    <T extends ComponentRealization> List<T> getRealizedComponent(java.lang.Class<T> filterClass);

}
