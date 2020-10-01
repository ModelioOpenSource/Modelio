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
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Instance;

/**
 * BindableInstance v0.0.9054
 * 
 * 
 * A BindableInstance represents the UML 2.0 "Part" concept.  It is an Instance that can be bound to an AssociationEnd, a Parameter or an Instance.
 * 
 * The RepresentedFeature is used for every case where Elements need to be represented under a different and more specific configuration. For example, a Port on a Part is bound to the same Port on the Classifier of that Part.  When Attributes are represented as Parts inside a Class and its internal structure, the Part is bound to its representing Attribute.  
 * 
 * In Modelio, a BindableInstance belongs to an instance, a Classifier internal structure or a Collaboration declared instance.
 */
@objid ("0000aa6e-c4bf-1fd8-97fe-001ec947cd2a")
public interface BindableInstance extends Instance {
    /**
     * The metaclass simple name.
     */
    @objid ("f4c145c6-bc2d-4567-964e-2a95feec38db")
    public static final String MNAME = "BindableInstance";

    /**
     * The metaclass qualified name.
     */
    @objid ("0bcf074a-6c7e-4f2a-beda-31927727c626")
    public static final String MQNAME = "Standard.BindableInstance";

    /**
     * Getter for relation 'BindableInstance->Cluster'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7f2168f5-e9a1-492e-a2b0-f24d960db5e7")
    Instance getCluster();

    /**
     * Setter for relation 'BindableInstance->Cluster'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f8d8c797-0368-400a-b192-3ccc33075a2c")
    void setCluster(Instance value);

    /**
     * Getter for relation 'BindableInstance->InternalOwner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("950bc150-13af-455b-8b3d-e13d5fbff507")
    Classifier getInternalOwner();

    /**
     * Setter for relation 'BindableInstance->InternalOwner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4c23dfd0-dd57-4dd5-ad10-a484ce3cb46b")
    void setInternalOwner(Classifier value);

    /**
     * Getter for relation 'BindableInstance->Representation'
     * 
     * Metamodel description:
     * <i>Binding between Parts, from a CollaborationUse.</i>
     */
    @objid ("bd2986f9-55ca-4ea1-8257-d09fe8df8c90")
    EList<Binding> getRepresentation();

    /**
     * Filtered Getter for relation 'BindableInstance->Representation'
     * 
     * Metamodel description:
     * <i>Binding between Parts, from a CollaborationUse.</i>
     */
    @objid ("2c0a17be-ad3b-43ff-92f7-7b711027f980")
    <T extends Binding> List<T> getRepresentation(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BindableInstance->RepresentedFeature'
     * 
     * Metamodel description:
     * <i>Expresses that the Part or Port represents an element from within a more accurate context (such as within an instance or a class internal structure). </i>
     */
    @objid ("bd97e264-d4fa-4b9b-853e-37086f7962fc")
    UmlModelElement getRepresentedFeature();

    /**
     * Setter for relation 'BindableInstance->RepresentedFeature'
     * 
     * Metamodel description:
     * <i>Expresses that the Part or Port represents an element from within a more accurate context (such as within an instance or a class internal structure). </i>
     */
    @objid ("8373f772-eae1-4363-99e2-b839b2dd9cfa")
    void setRepresentedFeature(UmlModelElement value);

}
