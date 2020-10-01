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
package org.modelio.metamodel.uml.behavior.activityModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionRegion;

/**
 * ExpansionNode v0.0.9054
 * 
 * 
 * null
 */
@objid ("00311a8c-c4bf-1fd8-97fe-001ec947cd2a")
public interface ExpansionNode extends ObjectNode {
    /**
     * The metaclass simple name.
     */
    @objid ("dbf3b0e3-ec32-4320-a275-a924f27b5ea0")
    public static final String MNAME = "ExpansionNode";

    /**
     * The metaclass qualified name.
     */
    @objid ("455a83c4-085b-4dd8-aeff-6f5287c2a674")
    public static final String MQNAME = "Standard.ExpansionNode";

    /**
     * Getter for relation 'ExpansionNode->RegionAsOutput'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8684609e-179a-4866-bee1-529e707ac63a")
    ExpansionRegion getRegionAsOutput();

    /**
     * Setter for relation 'ExpansionNode->RegionAsOutput'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6ee10e54-5454-4335-9310-2d182bca921a")
    void setRegionAsOutput(ExpansionRegion value);

    /**
     * Getter for relation 'ExpansionNode->RegionAsInput'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("43fd5f1d-e2ae-4de2-a6ae-d2c555fea49e")
    ExpansionRegion getRegionAsInput();

    /**
     * Setter for relation 'ExpansionNode->RegionAsInput'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f4eda086-88d7-4e3f-96f2-a4beb1513e97")
    void setRegionAsInput(ExpansionRegion value);

}
