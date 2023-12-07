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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyBaseType;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("006f8eac-ec87-1098-b22e-001ec947cd2a")
public class PropertyTypeData extends ModelElementData {
    @objid ("fa5075ad-4dd3-4f41-9ded-096018d12fe7")
    Object mBaseType = PropertyBaseType.STRING;

    @objid ("c6ebe45d-255e-46ee-b9d3-b093d597d170")
    SmObjectImpl mModuleOwner;

    @objid ("ffc70799-a47e-40d7-bf41-7d8d568e0ae2")
    List<SmObjectImpl> mTyped = null;

    @objid ("3f96aaf0-333c-4d4c-888d-4ca04747017f")
    SmObjectImpl mAnalystOwner;

    @objid ("56362f4f-7cb6-456e-8c78-ac8eb8a41267")
    public  PropertyTypeData(PropertyTypeSmClass smClass) {
        super(smClass);
    }

}
