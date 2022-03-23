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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0088dbfa-c4be-1fd8-97fe-001ec947cd2a")
public abstract class ModelElementData extends ElementData {
    @objid ("9b5035dc-8921-46e2-97f4-abbe3513746f")
    Object mName = "";

    @objid ("0fa82135-5feb-4e7b-8361-50d11a5be121")
    SmObjectImpl mLocalProperties;

    @objid ("03bf815b-4f24-48db-9975-d62fae943ced")
    List<SmObjectImpl> mExtension = null;

    @objid ("edc5484a-b495-4b0e-b37e-8dcd86712118")
    List<SmObjectImpl> mDependsOnDependency = null;

    @objid ("de83e280-ff19-415d-9ad7-ac14be09e35f")
    List<SmObjectImpl> mTag = null;

    @objid ("96dfc3a3-28fd-4dd1-9784-4e7b577a6d97")
    List<SmObjectImpl> mImpactedDependency = null;

    @objid ("be466afc-d2db-440d-a335-8b4158c8d7ec")
    List<SmObjectImpl> mProperties = null;

    @objid ("a13c7148-5c0d-4fd9-92a8-24d18d41818b")
    List<SmObjectImpl> mProduct = null;

    @objid ("3a0e6231-133d-45ad-b7e0-6c51153503a3")
    List<SmObjectImpl> mDescriptor = null;

    @objid ("decd710f-29c2-4d8d-a68a-9be40ec92fbf")
    List<SmObjectImpl> mMatrix = null;

    @objid ("8699eda3-a13b-4cba-b618-7fdfcbd4c2ff")
    List<SmObjectImpl> mImpactImpacted = null;

    @objid ("0b173e6e-a0f1-4177-9a36-b0d631bfdd38")
    List<SmObjectImpl> mImpactDependsOn = null;

    @objid ("5794d192-3862-4c7f-a8d9-69d652cc4ec2")
    List<SmObjectImpl> mAttached = null;

    @objid ("01da5172-6bbc-4f19-ba90-1c822b308758")
    public  ModelElementData(ModelElementSmClass smClass) {
        super(smClass);
    }

}
