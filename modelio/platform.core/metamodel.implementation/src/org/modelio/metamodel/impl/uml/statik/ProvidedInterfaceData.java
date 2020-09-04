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
package org.modelio.metamodel.impl.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("001a1ddc-c4bf-1fd8-97fe-001ec947cd2a")
public class ProvidedInterfaceData extends UmlModelElementData {
    @objid ("64268079-fea1-4655-88f6-b81c56806e5c")
     List<SmObjectImpl> mProvidedElement = null;

    @objid ("13bac10f-d84f-49e8-8e7c-14223f038299")
     SmObjectImpl mProviding;

    @objid ("5cb8d20f-3887-423d-89b6-dba64e8d970e")
     List<SmObjectImpl> mConsumer = null;

    @objid ("440d6a97-39cd-41ff-b7ed-c044f7127659")
     List<SmObjectImpl> mNaryConsumer = null;

    @objid ("986c27a6-ec1c-4c75-a0f8-0229e88caae8")
    public ProvidedInterfaceData(ProvidedInterfaceSmClass smClass) {
        super(smClass);
    }

}
