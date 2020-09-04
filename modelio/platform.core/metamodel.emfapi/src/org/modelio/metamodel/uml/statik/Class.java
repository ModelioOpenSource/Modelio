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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.ClassAssociation;

/**
 * Class v0.0.9054
 * 
 * 
 * The Class is the main concept used in object-oriented modeling. It specifies which Instances can exist in an application. 
 * 
 * In Modelio, a Class is owned by a NameSpace (ModelTree) that can be a Package or a Class.
 */
@objid ("0002155c-c4bf-1fd8-97fe-001ec947cd2a")
public interface Class extends GeneralClass {
    /**
     * The metaclass simple name.
     */
    @objid ("21c9f0be-02cd-4a0f-b808-81f3f3a9f029")
    public static final String MNAME = "Class";

    /**
     * The metaclass qualified name.
     */
    @objid ("61d3196b-40fb-440f-89c0-25ff42e8aa83")
    public static final String MQNAME = "Standard.Class";

    /**
     * Getter for attribute 'Class.IsActive'
     * 
     * Metamodel description:
     * <i>Specifies whether an Object of the Class maintains its own thread of control. If true, then an Object has its own thread of control and runs concurrently with other active Objects. If false, then Operations run in the address space and under the control of the active Object that controls the caller.</i>
     */
    @objid ("4bed515e-da1a-49e6-afa2-bec8dc824311")
    boolean isIsActive();

    /**
     * Setter for attribute 'Class.IsActive'
     * 
     * Metamodel description:
     * <i>Specifies whether an Object of the Class maintains its own thread of control. If true, then an Object has its own thread of control and runs concurrently with other active Objects. If false, then Operations run in the address space and under the control of the active Object that controls the caller.</i>
     */
    @objid ("e77f32dc-ed3f-487d-93f5-a6f25bb01b87")
    void setIsActive(boolean value);

    /**
     * Getter for attribute 'Class.IsMain'
     * 
     * Metamodel description:
     * <i>A main Class is a Class whose unique instance represents the application.</i>
     */
    @objid ("8084b1a2-8907-4ab5-9560-8fdbd0921d23")
    boolean isIsMain();

    /**
     * Setter for attribute 'Class.IsMain'
     * 
     * Metamodel description:
     * <i>A main Class is a Class whose unique instance represents the application.</i>
     */
    @objid ("16227634-ddf4-4497-bd97-39dc61c7740c")
    void setIsMain(boolean value);

    /**
     * Getter for relation 'Class->LinkToAssociation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c153e16a-2141-487c-927b-48f1d8f76975")
    ClassAssociation getLinkToAssociation();

    /**
     * Setter for relation 'Class->LinkToAssociation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ebb095d4-031f-4742-9ef8-7ee32a320aea")
    void setLinkToAssociation(ClassAssociation value);

}
