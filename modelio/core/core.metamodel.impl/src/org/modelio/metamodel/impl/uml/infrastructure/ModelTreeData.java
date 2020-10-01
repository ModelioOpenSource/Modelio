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
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00899130-c4be-1fd8-97fe-001ec947cd2a")
public abstract class ModelTreeData extends UmlModelElementData {
    @objid ("db709e97-0654-4b91-b355-264a2eaecad8")
     SmObjectImpl mOwner;

    @objid ("398e8902-d1f2-4db5-b1d8-52e4d699c117")
     List<SmObjectImpl> mOwnedElement = null;

    @objid ("34b30f36-de0f-4828-970d-ff0b0c2b5bd6")
    public ModelTreeData(ModelTreeSmClass smClass) {
        super(smClass);
    }

}
