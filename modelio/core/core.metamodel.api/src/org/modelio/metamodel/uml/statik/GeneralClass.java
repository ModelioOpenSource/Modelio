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
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;

/**
 * GeneralClass v0.0.9054
 * 
 * 
 * A GeneralClass is an elaborated Classifier.  It belongs to its NameSpace.
 */
@objid ("000bbdb4-c4bf-1fd8-97fe-001ec947cd2a")
public interface GeneralClass extends Classifier {
    /**
     * The metaclass simple name.
     */
    @objid ("c93a55a6-150f-4c70-8446-68bf30373311")
    public static final String MNAME = "GeneralClass";

    /**
     * The metaclass qualified name.
     */
    @objid ("e10cc188-14be-4901-ac06-f74b2fc47f70")
    public static final String MQNAME = "Standard.GeneralClass";

    /**
     * Getter for attribute 'GeneralClass.IsElementary'
     * 
     * Metamodel description:
     * <i>Determines whether a Class is elementary/primitive or not. A Class is primitive if its value cannot be broken down and its instances are not handled by the application. For example, integer and boolean are elementary Classes, whereas Human or Device are generally not.</i>
     */
    @objid ("5381523e-36f3-4e47-aeb0-31695bb5aec1")
    boolean isIsElementary();

    /**
     * Setter for attribute 'GeneralClass.IsElementary'
     * 
     * Metamodel description:
     * <i>Determines whether a Class is elementary/primitive or not. A Class is primitive if its value cannot be broken down and its instances are not handled by the application. For example, integer and boolean are elementary Classes, whereas Human or Device are generally not.</i>
     */
    @objid ("c70f56ec-f3fa-4f72-999a-3bb6ffba21a4")
    void setIsElementary(boolean value);

    /**
     * Getter for relation 'GeneralClass->Occurence'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("cc9626c2-6c4b-4748-911a-7ad2434600d8")
    EList<Parameter> getOccurence();

    /**
     * Filtered Getter for relation 'GeneralClass->Occurence'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7ff2660b-1161-478c-abe5-8e65066ec2eb")
    <T extends Parameter> List<T> getOccurence(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'GeneralClass->ExceptionInput'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0cf076f7-42d5-4dc7-b317-cb1183fd3559")
    ExceptionHandler getExceptionInput();

    /**
     * Setter for relation 'GeneralClass->ExceptionInput'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("68b97bc9-9419-4cf7-9885-9a1845eae9d7")
    void setExceptionInput(ExceptionHandler value);

    /**
     * Getter for relation 'GeneralClass->Object'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0746d0a0-2a00-4e09-ba3b-dee86f0e5c55")
    EList<Attribute> getObject();

    /**
     * Filtered Getter for relation 'GeneralClass->Object'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a22decbf-7a78-4330-a76a-21cf013fbcab")
    <T extends Attribute> List<T> getObject(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'GeneralClass->SRepresentation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("91619915-6453-4df2-9018-a6d3e67e8f54")
    EList<Signal> getSRepresentation();

    /**
     * Filtered Getter for relation 'GeneralClass->SRepresentation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("25290dbc-4e3a-4309-9e29-395c86e40f9f")
    <T extends Signal> List<T> getSRepresentation(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'GeneralClass->OccurenceObjectNode'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("cab4b85e-bf51-4ad0-bf7e-4419b346ac0c")
    EList<ObjectNode> getOccurenceObjectNode();

    /**
     * Filtered Getter for relation 'GeneralClass->OccurenceObjectNode'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("90f524cd-ec12-444d-91bb-ddb84691125c")
    <T extends ObjectNode> List<T> getOccurenceObjectNode(java.lang.Class<T> filterClass);

}
