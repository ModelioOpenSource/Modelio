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

package org.modelio.metamodel.impl.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0086407a-c4be-1fd8-97fe-001ec947cd2a")
public class DependencyData extends ModelElementData {
    @objid ("c2735ef2-46c8-490c-bc17-7c9e3d54e9ad")
    SmObjectImpl mImpacted;

    @objid ("e8d6cc3f-9301-4989-8f8d-be20f26c7bb8")
    SmObjectImpl mDependsOn;

    @objid ("b9994553-d7aa-4849-8c5c-5feafde94af5")
    public  DependencyData(DependencySmClass smClass) {
        super(smClass);
    }

}
