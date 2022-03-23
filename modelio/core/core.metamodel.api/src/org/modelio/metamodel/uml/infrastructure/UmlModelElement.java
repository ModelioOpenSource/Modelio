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

package org.modelio.metamodel.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.NaryConnector;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;

/**
 * UmlModelElement v0.0.9054
 * 
 * 
 * A ModelElement describes every element that can exist in a model. Only low-level Elements are not ModelElements. 
 * 
 * ModelElements can be extended by Stereotypes and TaggedValues, can have Notes, can be the origin or target of Dependencies, and can have Constraints.
 */
@objid ("7bd99241-ebad-4f0a-aa75-6ce5e877b315")
public interface UmlModelElement extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("246facf9-b5d5-4b62-91be-204f8defca10")
    public static final String MNAME = "UmlModelElement";

    /**
     * The metaclass qualified name.
     */
    @objid ("d6742ba3-0e55-4ec7-bc8c-9b71531a4614")
    public static final String MQNAME = "Standard.UmlModelElement";

    /**
     * Getter for relation 'UmlModelElement->TemplateSubstitution'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f0b384b7-631b-4f74-b548-aa3aa7c38200")
    EList<TemplateParameterSubstitution> getTemplateSubstitution();

    /**
     * Filtered Getter for relation 'UmlModelElement->TemplateSubstitution'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("527c432a-af46-44d4-a114-c6fa92dc0c29")
    <T extends TemplateParameterSubstitution> List<T> getTemplateSubstitution(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'UmlModelElement->DefaultParametering'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("26c29d3e-d11e-46d5-90fd-abbed45c90be")
    EList<TemplateParameter> getDefaultParametering();

    /**
     * Filtered Getter for relation 'UmlModelElement->DefaultParametering'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3fada466-f06f-4574-8f47-b5ddcb80e93c")
    <T extends TemplateParameter> List<T> getDefaultParametering(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'UmlModelElement->Represents'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ced077f8-a2c8-4d22-9211-1ffa02404d84")
    EList<Binding> getRepresents();

    /**
     * Filtered Getter for relation 'UmlModelElement->Represents'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("99994b66-7602-4cc3-9aa0-6f8418be6b9c")
    <T extends Binding> List<T> getRepresents(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'UmlModelElement->OwnerTemplateParameter'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("11dc9672-9adb-40c8-9d8c-7d6dd8eacee9")
    TemplateParameter getOwnerTemplateParameter();

    /**
     * Setter for relation 'UmlModelElement->OwnerTemplateParameter'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("09159e14-81c7-47e5-9c1e-404d38aaea38")
    void setOwnerTemplateParameter(TemplateParameter value);

    /**
     * Getter for relation 'UmlModelElement->RepresentingEnd'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("45c20d5d-85f2-4c0d-ad7d-9153602bab4f")
    EList<ConnectorEnd> getRepresentingEnd();

    /**
     * Filtered Getter for relation 'UmlModelElement->RepresentingEnd'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f63086e4-5b3f-4b74-bbde-f19cc03d374d")
    <T extends ConnectorEnd> List<T> getRepresentingEnd(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'UmlModelElement->RepresentingPartition'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a2d380ba-642a-4427-9829-42d07466b80a")
    EList<ActivityPartition> getRepresentingPartition();

    /**
     * Filtered Getter for relation 'UmlModelElement->RepresentingPartition'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0cf79417-7972-4c40-a148-bdc7a8a593c9")
    <T extends ActivityPartition> List<T> getRepresentingPartition(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'UmlModelElement->ConstraintDefinition'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7faaf641-b223-4a2e-85a5-dcf1fceb3ed8")
    EList<Constraint> getConstraintDefinition();

    /**
     * Filtered Getter for relation 'UmlModelElement->ConstraintDefinition'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4934fceb-8090-4524-a25e-60601aea930d")
    <T extends Constraint> List<T> getConstraintDefinition(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'UmlModelElement->TypingParameter'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b53ea271-dcf8-45a6-8ea3-de2d1e623721")
    EList<TemplateParameter> getTypingParameter();

    /**
     * Filtered Getter for relation 'UmlModelElement->TypingParameter'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("89a1b9ff-50d7-44ee-9679-7d650a118b13")
    <T extends TemplateParameter> List<T> getTypingParameter(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'UmlModelElement->Manifesting'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("64c30ee8-86a2-4516-999c-d4fa0a622b3f")
    EList<Manifestation> getManifesting();

    /**
     * Filtered Getter for relation 'UmlModelElement->Manifesting'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5ce0def9-6a9e-4b4d-8461-91e613aa57ac")
    <T extends Manifestation> List<T> getManifesting(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'UmlModelElement->RepresentingInstance'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("89dfcfa1-03c1-4c8c-90af-8a6e2db0f1c1")
    EList<BindableInstance> getRepresentingInstance();

    /**
     * Filtered Getter for relation 'UmlModelElement->RepresentingInstance'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4f8a2dad-2b27-4298-99ea-f8c7c427a916")
    <T extends BindableInstance> List<T> getRepresentingInstance(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'UmlModelElement->ReceivedInfo'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("af62f8bd-a27c-4496-a01f-05e4a1421f0f")
    EList<InformationFlow> getReceivedInfo();

    /**
     * Filtered Getter for relation 'UmlModelElement->ReceivedInfo'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3e8f0ca0-0fdd-4741-8220-3a8a2606514b")
    <T extends InformationFlow> List<T> getReceivedInfo(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'UmlModelElement->SentInfo'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d4ad082c-cd95-4460-95d1-e1957d5dee57")
    EList<InformationFlow> getSentInfo();

    /**
     * Filtered Getter for relation 'UmlModelElement->SentInfo'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c2a0ae1c-ea21-431a-8708-d892be793746")
    <T extends InformationFlow> List<T> getSentInfo(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'UmlModelElement->RepresentingConnector'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4c72296c-f76d-4239-be37-4c017e6cc553")
    EList<NaryConnector> getRepresentingConnector();

    /**
     * Filtered Getter for relation 'UmlModelElement->RepresentingConnector'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("700c3cda-63c8-48bd-95b3-b77ca393f4c0")
    <T extends NaryConnector> List<T> getRepresentingConnector(java.lang.Class<T> filterClass);

}
