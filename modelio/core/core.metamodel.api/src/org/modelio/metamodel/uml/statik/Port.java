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

/**
 * Port v0.0.9054
 * 
 * 
 * (UML 2.0) Ports are connected to other Parts through Connectors through which requests can be made to invoke the behavioral Features of a Classifier. 
 * 
 * A Port may specify the services a Classifier offers to its environment, as well as the services that a Classifier expects of its environment.  
 * 
 * In Modelio, Ports are declared on Classes (UML 2.0 structured Classifiers) by creating them in the Class's internal structure. These Ports can then be shown on Instances of the Class, by creating Port instances in the Class instances, and by binding these Port instances to the Ports defined at Class level.  
 * 
 * In Modelio, a Port always belongs to a Class, a Component or a Signal.
 * 
 * 
 * 
 */
@objid ("0018a768-c4bf-1fd8-97fe-001ec947cd2a")
public interface Port extends BindableInstance {
    /**
     * The metaclass simple name.
     */
    @objid ("14a31cf4-a516-476a-94d0-d6147c5723a3")
    public static final String MNAME = "Port";

    /**
     * The metaclass qualified name.
     */
    @objid ("d046309b-e1b8-4013-9953-877560c28503")
    public static final String MQNAME = "Standard.Port";

    /**
     * Getter for attribute 'Port.IsBehavior'
     * 
     * Metamodel description:
     * <i>Specifies whether requests arriving at this Port are sent to the Classifier behavior of this Classifier. Such Ports are referred to as behavior Port. 
     * 
     * Any invocation of a behavioral feature targeted at a behavior Port will be handled by the instance of the owning Classifier itself, rather than by any instances that this Classifier may contain. 
     * 
     * The default value is false.</i>
     * 
     */
    @objid ("a754c41c-50d5-4586-8141-1e7a5f3931e9")
    boolean isIsBehavior();

    /**
     * Setter for attribute 'Port.IsBehavior'
     * 
     * Metamodel description:
     * <i>Specifies whether requests arriving at this Port are sent to the Classifier behavior of this Classifier. Such Ports are referred to as behavior Port. 
     * 
     * Any invocation of a behavioral feature targeted at a behavior Port will be handled by the instance of the owning Classifier itself, rather than by any instances that this Classifier may contain. 
     * 
     * The default value is false.</i>
     * 
     */
    @objid ("342dc5ab-1d99-42fd-b924-5825bd460768")
    void setIsBehavior(boolean value);

    /**
     * Getter for attribute 'Port.IsService'
     * 
     * Metamodel description:
     * <i>If true, indicates that this Port is used to provide the published functionality of a Classifier. If false, this Port is used to implement the Classifier but is not part of the essential externally-visible functionality of the Classifier and can, therefore, be altered or deleted along with the internal implementation of the Classifier and other properties that are considered part of its implementation. 
     * 
     * The default value for this attribute is true.</i>
     * 
     */
    @objid ("27af4e22-e239-4b75-b3f2-f8aae6535fec")
    boolean isIsService();

    /**
     * Setter for attribute 'Port.IsService'
     * 
     * Metamodel description:
     * <i>If true, indicates that this Port is used to provide the published functionality of a Classifier. If false, this Port is used to implement the Classifier but is not part of the essential externally-visible functionality of the Classifier and can, therefore, be altered or deleted along with the internal implementation of the Classifier and other properties that are considered part of its implementation. 
     * 
     * The default value for this attribute is true.</i>
     * 
     */
    @objid ("d14d6511-6338-46d5-9964-8478e1057d8a")
    void setIsService(boolean value);

    /**
     * Getter for attribute 'Port.IsConjugated'
     * 
     * Metamodel description:
     * <i>Specifies the way that the provided and required interfaces are derived from the Port's Type.  The default value is false.</i>
     * 
     */
    @objid ("8e64c927-68d6-43fa-a751-5c6544a42f45")
    boolean isIsConjugated();

    /**
     * Setter for attribute 'Port.IsConjugated'
     * 
     * Metamodel description:
     * <i>Specifies the way that the provided and required interfaces are derived from the Port's Type.  The default value is false.</i>
     * 
     */
    @objid ("0dc58d56-b27d-451a-8e21-5f1506e1a643")
    void setIsConjugated(boolean value);

    /**
     * Getter for attribute 'Port.Direction'
     * 
     * Metamodel description:
     * <i><p>Specifies the way that the data&nbsp;flows through&nbsp;the Port.</p>
     * </i>
     * 
     */
    @objid ("cfa83652-c56d-42c4-a863-aee54939006e")
    PortOrientation getDirection();

    /**
     * Setter for attribute 'Port.Direction'
     * 
     * Metamodel description:
     * <i><p>Specifies the way that the data&nbsp;flows through&nbsp;the Port.</p>
     * </i>
     * 
     */
    @objid ("51ca9dda-d02c-460e-bd10-3ab86bacc54f")
    void setDirection(PortOrientation value);

    /**
     * Getter for relation 'Port->Provided'
     * 
     * Metamodel description:
     * <i>References the Interfaces specifying the set of operations and receptions which the Classifier provides to its environment, and which it will handle either directly or by forwarding it to a part of its internal structure.</i>
     * 
     */
    @objid ("ca049f8d-2638-4670-8aaa-32edafdaa25f")
    EList<ProvidedInterface> getProvided();

    /**
     * Filtered Getter for relation 'Port->Provided'
     * 
     * Metamodel description:
     * <i>References the Interfaces specifying the set of operations and receptions which the Classifier provides to its environment, and which it will handle either directly or by forwarding it to a part of its internal structure.</i>
     * 
     */
    @objid ("d8d99489-447f-4e5c-8d2e-ae5a1cec8e73")
    <T extends ProvidedInterface> List<T> getProvided(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Port->Required'
     * 
     * Metamodel description:
     * <i>References the Interfaces specifying the set of operations and receptions which the Classifier expects its environment to handle.</i>
     * 
     */
    @objid ("0e3daeeb-1108-4be6-90f8-7ffdfbbe98e7")
    EList<RequiredInterface> getRequired();

    /**
     * Filtered Getter for relation 'Port->Required'
     * 
     * Metamodel description:
     * <i>References the Interfaces specifying the set of operations and receptions which the Classifier expects its environment to handle.</i>
     * 
     */
    @objid ("85c4f483-0acd-4909-8f97-d9b56fce6b99")
    <T extends RequiredInterface> List<T> getRequired(java.lang.Class<T> filterClass);
}

