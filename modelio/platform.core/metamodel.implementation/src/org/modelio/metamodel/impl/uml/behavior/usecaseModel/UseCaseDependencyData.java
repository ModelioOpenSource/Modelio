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
package org.modelio.metamodel.impl.uml.behavior.usecaseModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00595600-c4bf-1fd8-97fe-001ec947cd2a")
public class UseCaseDependencyData extends UmlModelElementData {
    @objid ("9b68cabb-ac11-4827-8a27-34829cfd4b96")
     SmObjectImpl mOrigin;

    @objid ("edf2ee11-4573-45fb-927b-ab7f780b0783")
     List<SmObjectImpl> mExtensionLocation = null;

    @objid ("797406fa-cdd2-4e89-b026-41326ccd6ae6")
     SmObjectImpl mTarget;

    @objid ("8e0f19ce-38f2-4a14-8036-bf2214b1d008")
    public UseCaseDependencyData(UseCaseDependencySmClass smClass) {
        super(smClass);
    }

}
