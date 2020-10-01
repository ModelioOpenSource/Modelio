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
package org.modelio.metamodel.impl.uml.behavior.stateMachineModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0053d27a-c4bf-1fd8-97fe-001ec947cd2a")
public class StateData extends StateVertexData {
    @objid ("b5ad37cf-2722-4bfb-9537-95637d0fb52c")
     List<SmObjectImpl> mExitPoint = null;

    @objid ("dfaeab7f-9a4a-4872-81ba-4eebabde65df")
     List<SmObjectImpl> mDeffered = null;

    @objid ("7198b967-59e2-4d96-9f2b-f407eb2a24db")
     List<SmObjectImpl> mInternal = null;

    @objid ("3b82ad85-31d1-4e5b-91e2-354ef5cb60bc")
     List<SmObjectImpl> mEntryPoint = null;

    @objid ("db860dcf-c74b-4aa9-8edb-df77f58162a3")
     List<SmObjectImpl> mOwnedRegion = null;

    @objid ("9499cc59-ce31-4c1d-a660-3fa58789f5fd")
     List<SmObjectImpl> mRequiredStateOf = null;

    @objid ("9f7a6ca1-220e-44e8-8cc7-d4478d559e0e")
     List<SmObjectImpl> mConnection = null;

    @objid ("1a5359cd-01b9-4ca8-a407-d8ff1ba0a71d")
     SmObjectImpl mSubMachine;

    @objid ("2ddadcb1-ef3a-4df8-a112-229829efc108")
    public StateData(StateSmClass smClass) {
        super(smClass);
    }

}
