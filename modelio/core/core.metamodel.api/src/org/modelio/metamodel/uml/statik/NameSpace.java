/* 
 * Copyright 2013-2020 Modeliosoft
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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.informationFlow.DataFlow;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.ModelTree;

/**
 * NameSpace v0.0.9054
 * 
 * 
 * A NameSpace is a named element that can own other named elements. Each named element may be owned by at most one NameSpace. 
 * 
 * A NameSpace provides a means of identifying named elements by name. Named elements can be identified by name in a NameSpace either by being directly owned by the NameSpace or by being introduced into the NameSpace by other means, for example, import or inheritance. 
 * 
 * Namespace is an abstract metaclass. 
 * 
 * A NameSpace can own Constraints. The Constraint does not necessarily apply to the NameSpace itself, but may also apply to elements in the NameSpace.  In Modelio, NameSpaces are generalizable. They can contain Interactions and are the definition context for Instances.
 * 
 * In Modelio, a NameSpace belongs to another NameSpace, with the single exception of the Root NameSpace, which is associated to the Project.
 */
@objid ("00115274-c4bf-1fd8-97fe-001ec947cd2a")
public interface NameSpace extends ModelTree {
    /**
     * The metaclass simple name.
     */
    @objid ("664aeeb2-b5dd-43b5-86b5-145678f94d9a")
    public static final String MNAME = "NameSpace";

    /**
     * The metaclass qualified name.
     */
    @objid ("e1a9ef80-7a21-4d52-be03-8df6bbd8800b")
    public static final String MQNAME = "Standard.NameSpace";

    /**
     * Getter for attribute 'NameSpace.IsAbstract'
     * 
     * Metamodel description:
     * <i>An abstract NameSpace is defined on a very general level and does not have direct instances.</i>
     */
    @objid ("f3914388-cdd5-4fee-8afb-9ccffe2e1e13")
    boolean isIsAbstract();

    /**
     * Setter for attribute 'NameSpace.IsAbstract'
     * 
     * Metamodel description:
     * <i>An abstract NameSpace is defined on a very general level and does not have direct instances.</i>
     */
    @objid ("1deee478-2426-4e9d-b1e0-9fed6e9cb2dd")
    void setIsAbstract(boolean value);

    /**
     * Getter for attribute 'NameSpace.IsLeaf'
     * 
     * Metamodel description:
     * <i>Determines if the NameSpace is an inheritance tree leaf. This prohibits future inheritance.</i>
     */
    @objid ("06c44ac5-107f-4fbb-9f5c-114d6c34ef68")
    boolean isIsLeaf();

    /**
     * Setter for attribute 'NameSpace.IsLeaf'
     * 
     * Metamodel description:
     * <i>Determines if the NameSpace is an inheritance tree leaf. This prohibits future inheritance.</i>
     */
    @objid ("c9c68436-4bf9-488e-ab50-688e6dd7a762")
    void setIsLeaf(boolean value);

    /**
     * Getter for attribute 'NameSpace.IsRoot'
     * 
     * Metamodel description:
     * <i>Determines that the current NameSpace is the root of a Generalization tree.</i>
     */
    @objid ("afa6c82c-5d70-4e9d-987a-89e108daf8a4")
    boolean isIsRoot();

    /**
     * Setter for attribute 'NameSpace.IsRoot'
     * 
     * Metamodel description:
     * <i>Determines that the current NameSpace is the root of a Generalization tree.</i>
     */
    @objid ("d6629e2c-1c5f-43d8-8dc9-3fa12bf516fc")
    void setIsRoot(boolean value);

    /**
     * Getter for attribute 'NameSpace.Visibility'
     * 
     * Metamodel description:
     * <i>Defines the visibility of the NameSpace, inside its owning NameSpace (visibility of a Class in a Package, for example).</i>
     */
    @objid ("7eb78b50-a720-4e44-839b-d66d6d2af688")
    VisibilityMode getVisibility();

    /**
     * Setter for attribute 'NameSpace.Visibility'
     * 
     * Metamodel description:
     * <i>Defines the visibility of the NameSpace, inside its owning NameSpace (visibility of a Class in a Package, for example).</i>
     */
    @objid ("7140153b-e9cb-41c9-8c98-5077d47b5db0")
    void setVisibility(VisibilityMode value);

    /**
     * Getter for relation 'NameSpace->Parent'
     * 
     * Metamodel description:
     * <i>Association to the Parent NameSpace through the intermediate Generalization class.</i>
     */
    @objid ("5426d8ec-74bd-40d1-ba17-e4cd92651238")
    EList<Generalization> getParent();

    /**
     * Filtered Getter for relation 'NameSpace->Parent'
     * 
     * Metamodel description:
     * <i>Association to the Parent NameSpace through the intermediate Generalization class.</i>
     */
    @objid ("fe28d2d2-0a6f-46c8-8c57-f3716ee60ad4")
    <T extends Generalization> List<T> getParent(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NameSpace->TemplateInstanciation'
     * 
     * Metamodel description:
     * <i>Relates to the template that is instanciated by the current NameSpace.</i>
     */
    @objid ("9ece59ea-2d72-47c6-9560-1de072b32133")
    EList<TemplateBinding> getTemplateInstanciation();

    /**
     * Filtered Getter for relation 'NameSpace->TemplateInstanciation'
     * 
     * Metamodel description:
     * <i>Relates to the template that is instanciated by the current NameSpace.</i>
     */
    @objid ("a9cd9f8b-03ce-4bd0-9b92-aeb42b342ac4")
    <T extends TemplateBinding> List<T> getTemplateInstanciation(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NameSpace->Representing'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d55a59ef-0efc-4d7d-a953-3aa7cd002ec0")
    EList<Instance> getRepresenting();

    /**
     * Filtered Getter for relation 'NameSpace->Representing'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("388e5889-9ce2-4351-9bdf-2960b6712656")
    <T extends Instance> List<T> getRepresenting(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NameSpace->OwnedBehavior'
     * 
     * Metamodel description:
     * <i>Behavior specification that specifies the behavior of the namespace itself.</i>
     */
    @objid ("a7b3ff61-0bc5-408d-810f-a53bde2db089")
    EList<Behavior> getOwnedBehavior();

    /**
     * Filtered Getter for relation 'NameSpace->OwnedBehavior'
     * 
     * Metamodel description:
     * <i>Behavior specification that specifies the behavior of the namespace itself.</i>
     */
    @objid ("2cdb6f22-3ffd-479a-aabb-55a3e2a24aa7")
    <T extends Behavior> List<T> getOwnedBehavior(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NameSpace->Received'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("dd2cdc17-4df9-4a9b-a862-6f35066f6f0b")
    EList<DataFlow> getReceived();

    /**
     * Filtered Getter for relation 'NameSpace->Received'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f805b9fa-2722-4c3b-a7d1-a994c7dfc982")
    <T extends DataFlow> List<T> getReceived(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NameSpace->OwnedInformationFlow'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("61d811c4-5d7e-4031-a937-8b4d35e48a73")
    EList<InformationFlow> getOwnedInformationFlow();

    /**
     * Filtered Getter for relation 'NameSpace->OwnedInformationFlow'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("349b0951-b494-4e26-8ef9-cb6bbdb1b62b")
    <T extends InformationFlow> List<T> getOwnedInformationFlow(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NameSpace->Importing'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9148107c-1ee2-4e1b-accc-1f3a9da4cd79")
    EList<ElementImport> getImporting();

    /**
     * Filtered Getter for relation 'NameSpace->Importing'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3fc52766-9a66-4205-9ffb-50f8d848ed2a")
    <T extends ElementImport> List<T> getImporting(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NameSpace->Sent'
     * 
     * Metamodel description:
     * <i>DataFlows sent by the NameSpace.</i>
     */
    @objid ("fd6b2daf-7d4b-4cdd-b88b-e98d686faa1e")
    EList<DataFlow> getSent();

    /**
     * Filtered Getter for relation 'NameSpace->Sent'
     * 
     * Metamodel description:
     * <i>DataFlows sent by the NameSpace.</i>
     */
    @objid ("e42e05a6-f632-45bf-83d4-c425464a7b33")
    <T extends DataFlow> List<T> getSent(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NameSpace->OwnedDataFlow'
     * 
     * Metamodel description:
     * <i>DataFlows belong to a NameSpace. If they have an origin NameSpace, then this is the owner. Otherwise, the NameSpace constituting the definition context of the DataFlow (this typically designates the Package in which the diagram has been defined) will be the owner.</i>
     */
    @objid ("af44f142-6b39-417f-ba74-7aa60f453e4b")
    EList<DataFlow> getOwnedDataFlow();

    /**
     * Filtered Getter for relation 'NameSpace->OwnedDataFlow'
     * 
     * Metamodel description:
     * <i>DataFlows belong to a NameSpace. If they have an origin NameSpace, then this is the owner. Otherwise, the NameSpace constituting the definition context of the DataFlow (this typically designates the Package in which the diagram has been defined) will be the owner.</i>
     */
    @objid ("8002e50b-1cec-4e1a-8d12-4b4ce303699b")
    <T extends DataFlow> List<T> getOwnedDataFlow(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NameSpace->OwnedCollaborationUse'
     * 
     * Metamodel description:
     * <i>Collaboration occurences can be owned by NameSpaces such as Packages, Classes and Collaborations.</i>
     */
    @objid ("780e9c6e-3425-44cf-bda9-73fe3a9b4111")
    EList<CollaborationUse> getOwnedCollaborationUse();

    /**
     * Filtered Getter for relation 'NameSpace->OwnedCollaborationUse'
     * 
     * Metamodel description:
     * <i>Collaboration occurences can be owned by NameSpaces such as Packages, Classes and Collaborations.</i>
     */
    @objid ("c73927f7-3f24-4a37-985b-c2d1987e885f")
    <T extends CollaborationUse> List<T> getOwnedCollaborationUse(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NameSpace->OwnedPackageImport'
     * 
     * Metamodel description:
     * <i>Packages imported by the NameSpace.</i>
     */
    @objid ("d668a351-8181-456e-9f70-f9a261ef2fda")
    EList<PackageImport> getOwnedPackageImport();

    /**
     * Filtered Getter for relation 'NameSpace->OwnedPackageImport'
     * 
     * Metamodel description:
     * <i>Packages imported by the NameSpace.</i>
     */
    @objid ("d66cf8ce-b3e2-44e4-9bb7-2485cb2949e9")
    <T extends PackageImport> List<T> getOwnedPackageImport(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NameSpace->Template'
     * 
     * Metamodel description:
     * <i>In the case of template classes, this association defines its template parameters.</i>
     */
    @objid ("66d0c4b8-c1f4-4efa-add9-0986eb3519b4")
    EList<TemplateParameter> getTemplate();

    /**
     * Filtered Getter for relation 'NameSpace->Template'
     * 
     * Metamodel description:
     * <i>In the case of template classes, this association defines its template parameters.</i>
     */
    @objid ("01e1b48a-d60c-4e9a-af92-cdb3703c27fe")
    <T extends TemplateParameter> List<T> getTemplate(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NameSpace->Specialization'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2bebb521-8991-4f96-9f67-75356dda26a2")
    EList<Generalization> getSpecialization();

    /**
     * Filtered Getter for relation 'NameSpace->Specialization'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("418d5f22-3b42-4881-aff6-d3c9f72086d9")
    <T extends Generalization> List<T> getSpecialization(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NameSpace->Realized'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("61b27371-9492-4eda-a60a-7d5d4fd20baf")
    EList<InterfaceRealization> getRealized();

    /**
     * Filtered Getter for relation 'NameSpace->Realized'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("42c252c7-b397-456e-8a7e-77f09e20afc1")
    <T extends InterfaceRealization> List<T> getRealized(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NameSpace->Declared'
     * 
     * Metamodel description:
     * <i>Instances declared in the context of the current NameSpace.  </i>
     */
    @objid ("c225df9b-dc3d-477a-b137-bb7a4f329cc5")
    EList<Instance> getDeclared();

    /**
     * Filtered Getter for relation 'NameSpace->Declared'
     * 
     * Metamodel description:
     * <i>Instances declared in the context of the current NameSpace.  </i>
     */
    @objid ("87bb8a9c-e820-40c8-bca0-c0d0e16f2ae9")
    <T extends Instance> List<T> getDeclared(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NameSpace->InstanciatingBinding'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6034fb0f-3118-45a3-8cb4-f3aae4678025")
    EList<TemplateBinding> getInstanciatingBinding();

    /**
     * Filtered Getter for relation 'NameSpace->InstanciatingBinding'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("68c47e21-8d28-4b34-b327-faa7189fee28")
    <T extends TemplateBinding> List<T> getInstanciatingBinding(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NameSpace->OwnedImport'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6eacc742-1384-4327-b0c9-17f38dc60a99")
    EList<ElementImport> getOwnedImport();

    /**
     * Filtered Getter for relation 'NameSpace->OwnedImport'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b670ff5d-6064-4b82-8d58-9045f51ca999")
    <T extends ElementImport> List<T> getOwnedImport(java.lang.Class<T> filterClass);

}
