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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0088dbfa-c4be-1fd8-97fe-001ec947cd2a")
public abstract class ModelElementData extends ElementData {
    @objid ("c5715e2a-3489-479d-9494-d44f7f59ea68")
     Object mName = "";

    @objid ("5a20fa8f-2a9f-4120-bc44-2be81b57c5a8")
     SmObjectImpl mLocalProperties;

    @objid ("fedbf4c8-2c79-41ab-9e26-1bed54f60103")
     List<SmObjectImpl> mExtension = null;

    @objid ("bca70623-c49b-4356-b50b-1bfa0ecf96c8")
     List<SmObjectImpl> mDependsOnDependency = null;

    @objid ("efe3134d-39d4-45f8-9164-22ba34ec9e0e")
     List<SmObjectImpl> mTag = null;

    @objid ("70c060ba-9567-465c-a0c8-41d8f2ba0c89")
     List<SmObjectImpl> mImpactedDependency = null;

    @objid ("eb9c70dc-77f1-4120-8ab8-39a02c395010")
     List<SmObjectImpl> mProperties = null;

    @objid ("2a1495b0-23e7-4361-a092-59bf119d5ce6")
     List<SmObjectImpl> mProduct = null;

    @objid ("6d029ff0-e6c8-4343-9f4e-d7e50624f3d6")
     List<SmObjectImpl> mDescriptor = null;

    @objid ("7d39b41a-43c3-4894-9d2a-36be101349fd")
     List<SmObjectImpl> mMatrix = null;

    @objid ("9a684ceb-405a-4c9e-9446-5037580a1ad1")
     List<SmObjectImpl> mImpactImpacted = null;

    @objid ("329447db-237f-4978-ab44-5cb019901c95")
     List<SmObjectImpl> mImpactDependsOn = null;

    @objid ("d5aad24f-ba1e-4f30-bf99-efc8299604a9")
     List<SmObjectImpl> mAttached = null;

    @objid ("692cb3aa-d2b8-4aab-b5eb-be11476a772d")
    public ModelElementData(ModelElementSmClass smClass) {
        super(smClass);
    }

}
