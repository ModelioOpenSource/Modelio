/* 
 * Copyright 2013-2019 Modeliosoft
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
package org.modelio.metamodel.uml.behavior.activityModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.GeneralClass;

/**
 * ExceptionHandler v0.0.9054
 * 
 * 
 * null
 */
@objid ("00307956-c4bf-1fd8-97fe-001ec947cd2a")
public interface ExceptionHandler extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("474b3ca5-8bf7-4041-a204-0d82dd32953e")
    public static final String MNAME = "ExceptionHandler";

    /**
     * The metaclass qualified name.
     */
    @objid ("a7e520ca-2c3b-4a1d-a40b-786b844e0f12")
    public static final String MQNAME = "Standard.ExceptionHandler";

    /**
     * Getter for attribute 'ExceptionHandler.Guard'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("bfadd88c-4a66-4834-a1c7-ef7460e247a6")
    String getGuard();

    /**
     * Setter for attribute 'ExceptionHandler.Guard'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("eb11c270-6c03-41ed-ae99-f13b3b6dd7e0")
    void setGuard(String value);

    /**
     * Getter for attribute 'ExceptionHandler.Weight'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("99348e15-1557-4298-8597-a91f17db0d00")
    String getWeight();

    /**
     * Setter for attribute 'ExceptionHandler.Weight'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8565fde7-ca51-43d4-b3a9-ee88678b0101")
    void setWeight(String value);

    /**
     * Getter for relation 'ExceptionHandler->ProtectedNode'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("bd7e1bd0-fc5a-4c5f-a521-d7799b9f487f")
    ActivityAction getProtectedNode();

    /**
     * Setter for relation 'ExceptionHandler->ProtectedNode'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("adf993e1-e157-4fda-bbbe-e4131e55c1f6")
    void setProtectedNode(ActivityAction value);

    /**
     * Getter for relation 'ExceptionHandler->ExceptionInput'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("502863e6-47f4-41b3-8d5d-ba9d41ab40db")
    InputPin getExceptionInput();

    /**
     * Setter for relation 'ExceptionHandler->ExceptionInput'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0e368cae-b68f-41cb-be47-6aed62a0ea88")
    void setExceptionInput(InputPin value);

    /**
     * Getter for relation 'ExceptionHandler->ExceptionType'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("09bd6b9a-4f95-48a6-8fb3-4ced4e0fcb18")
    EList<GeneralClass> getExceptionType();

    /**
     * Filtered Getter for relation 'ExceptionHandler->ExceptionType'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6a21c859-e35e-4841-b814-2300b85f596c")
    <T extends GeneralClass> List<T> getExceptionType(java.lang.Class<T> filterClass);

}
