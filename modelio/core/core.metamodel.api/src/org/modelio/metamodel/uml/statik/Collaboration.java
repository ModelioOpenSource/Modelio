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

/**
 * Collaboration v0.0.9054
 * 
 * 
 * (UML 2.0) A Collaboration is represented as a kind of Classifier, and defines a set of cooperating entities to be played by instances (its roles), as well as a set of Connectors that define Links between the participating instances. The cooperating entities are the properties of the Collaboration. 
 * 
 * A Collaboration is not instanciable. A Collaboration specifies a view (or projection) of a set of cooperating Classifiers. It describes the required Links between instances that play the roles of the Collaboration, as well as the Features required of the Classifiers that specify the participating instances. Several Collaborations may describe different projections of the same set of Classifiers. 
 * 
 * In Modelio, Collaborations have the same meaning. Modelio extends them, by allowing them to directly bind Parts and Connectors to other ModelElements. This means that a bound Collaboration can be directly entered.   
 * 
 * In Modelio, a Collaboration belongs to a UseCase, a Class, a Package, a Collaboration or an Operation.
 * 
 * 
 */
@objid ("0003f692-c4bf-1fd8-97fe-001ec947cd2a")
public interface Collaboration extends NameSpace {
    /**
     * The metaclass simple name.
     */
    @objid ("1b4bec90-e329-40f6-86ac-a111bf7da4d5")
    public static final String MNAME = "Collaboration";

    /**
     * The metaclass qualified name.
     */
    @objid ("6a0c102b-7273-4dea-8967-069c02b2f97b")
    public static final String MQNAME = "Standard.Collaboration";

    /**
     * Getter for attribute 'Collaboration.IsConcurrent'
     * 
     * Metamodel description:
     * <i>Expresses if the Collaboration between objects is concurrent or sequential.</i>
     * 
     */
    @objid ("59962e3b-fd12-4a48-ab97-97788649c37e")
    boolean isIsConcurrent();

    /**
     * Setter for attribute 'Collaboration.IsConcurrent'
     * 
     * Metamodel description:
     * <i>Expresses if the Collaboration between objects is concurrent or sequential.</i>
     * 
     */
    @objid ("3d000d9f-37fb-4bda-ad56-b3318339ae12")
    void setIsConcurrent(boolean value);

    /**
     * Getter for relation 'Collaboration->ORepresented'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("b9c1fd19-b9f4-4a92-9c21-78a355731814")
    Operation getORepresented();

    /**
     * Setter for relation 'Collaboration->ORepresented'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("734ff3a3-098c-4b50-a247-50604daf91d2")
    void setORepresented(Operation value);

    /**
     * Getter for relation 'Collaboration->BRepresented'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("68e625f8-08bd-4674-8f38-2455a53f34b0")
    Behavior getBRepresented();

    /**
     * Setter for relation 'Collaboration->BRepresented'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("e5b14bf1-9d5e-4e28-bfdf-3d77b2dd338f")
    void setBRepresented(Behavior value);

    /**
     * Getter for relation 'Collaboration->Occurrence'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("8fa4d315-a94b-432b-95e1-5c314e908f44")
    EList<CollaborationUse> getOccurrence();

    /**
     * Filtered Getter for relation 'Collaboration->Occurrence'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("e6d47857-2427-4f9e-b4d9-054b234d1859")
    <T extends CollaborationUse> List<T> getOccurrence(java.lang.Class<T> filterClass);
}

