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

package org.modelio.metamodel.impl.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0022bc4e-c4bf-1fd8-97fe-001ec947cd2a")
public class NaryLinkData extends UmlModelElementData {
    @objid ("89ce6b7c-5c75-4c49-8b7c-38862f1dcddb")
    List<SmObjectImpl> mNaryLinkEnd = null;

    @objid ("0f1fbb9e-5d8e-4c53-a63c-d4c0af53ac83")
    SmObjectImpl mModel;

    @objid ("69b2b288-560b-498e-9797-05f71965e3dc")
    List<SmObjectImpl> mRealizedInformationFlow = null;

    @objid ("f0304ff2-606d-4780-b746-4851696ef665")
    List<SmObjectImpl> mSent = null;

    @objid ("9ee597e0-0c71-459c-b41f-b6969e746ccc")
    public  NaryLinkData(NaryLinkSmClass smClass) {
        super(smClass);
    }

}
