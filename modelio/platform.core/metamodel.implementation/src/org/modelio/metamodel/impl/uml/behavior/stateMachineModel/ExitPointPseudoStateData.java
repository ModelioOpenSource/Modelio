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
package org.modelio.metamodel.impl.uml.behavior.stateMachineModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00501928-c4bf-1fd8-97fe-001ec947cd2a")
public class ExitPointPseudoStateData extends AbstractPseudoStateData {
    @objid ("6ae3f522-e2ae-4d06-b86c-c915bdc55643")
     SmObjectImpl mExitOf;

    @objid ("b89bf20a-87e2-455f-ac53-595ccc4181ea")
     List<SmObjectImpl> mConnection = null;

    @objid ("39ea9764-4dc7-4836-97c8-bf6d775a583b")
     SmObjectImpl mExitOfMachine;

    @objid ("eb918763-b0c3-45db-a717-3092b5d5a5c6")
    public ExitPointPseudoStateData(ExitPointPseudoStateSmClass smClass) {
        super(smClass);
    }

}
