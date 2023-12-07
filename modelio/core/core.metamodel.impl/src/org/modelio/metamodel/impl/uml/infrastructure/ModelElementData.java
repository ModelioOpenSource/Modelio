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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0088dbfa-c4be-1fd8-97fe-001ec947cd2a")
public abstract class ModelElementData extends ElementData {
    @objid ("5675d5d9-6cc2-4d62-89e4-28bc82625d5a")
    Object mName = "";

    @objid ("12dbc3e5-9746-456d-9668-3366d2fcfa2d")
    SmObjectImpl mLocalProperties;

    @objid ("db8ab938-f69b-493c-9e16-cf1b6edceeb6")
    List<SmObjectImpl> mExtension = null;

    @objid ("2f50024d-9d67-484f-a6e2-2fbdd36a6b27")
    List<SmObjectImpl> mDependsOnDependency = null;

    @objid ("7e0e04b5-88ad-45af-8a8a-793141c12bfb")
    List<SmObjectImpl> mTag = null;

    @objid ("8513a21d-a49d-4fa7-858a-81fdf18422d5")
    List<SmObjectImpl> mImpactedDependency = null;

    @objid ("82b35992-2178-4200-a4eb-62af9f26d809")
    List<SmObjectImpl> mProperties = null;

    @objid ("3817fcf9-e2cf-4ed0-88aa-ffec96640d7e")
    List<SmObjectImpl> mProduct = null;

    @objid ("58b1315b-91ab-4cca-97f7-3af8f9964e9c")
    List<SmObjectImpl> mDescriptor = null;

    @objid ("1a33ac00-8f98-42ec-b383-56781e1a8567")
    List<SmObjectImpl> mMatrix = null;

    @objid ("816fb8c9-e2d0-48dd-9a0a-ce6c68b2ad16")
    List<SmObjectImpl> mImpactImpacted = null;

    @objid ("e68168fd-cfea-4db3-90fe-ea3b200f16d7")
    List<SmObjectImpl> mImpactDependsOn = null;

    @objid ("ba3a7d19-130c-49dc-ad19-d79aa8b8e28e")
    List<SmObjectImpl> mAttached = null;

    @objid ("49dd4cb2-169d-4ccb-977c-40040bffa343")
    public  ModelElementData(ModelElementSmClass smClass) {
        super(smClass);
    }

}
