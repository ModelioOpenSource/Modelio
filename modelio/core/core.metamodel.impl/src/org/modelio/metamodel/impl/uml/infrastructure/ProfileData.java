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

@objid ("008ce3b2-c4be-1fd8-97fe-001ec947cd2a")
public class ProfileData extends ModelElementData {
    @objid ("f619ac4c-7b6f-4108-9180-d8528c7eb134")
    List<SmObjectImpl> mDefinedStereotype = null;

    @objid ("1ad0e6c9-f2fd-4ccd-b047-48334a17d011")
    List<SmObjectImpl> mOwnedReference = null;

    @objid ("45e802f9-6f31-468d-b640-cf16e12b1606")
    SmObjectImpl mOwnerModule;

    @objid ("fcfe08a1-8217-429e-a04d-92818df0c820")
    List<SmObjectImpl> mDefinedType = null;

    @objid ("1478e97e-c7c3-4a35-a1f8-ed6a6dba3df0")
    public  ProfileData(ProfileSmClass smClass) {
        super(smClass);
    }

}
