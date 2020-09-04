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

@objid ("004f999e-c4bf-1fd8-97fe-001ec947cd2a")
public class EntryPointPseudoStateData extends AbstractPseudoStateData {
    @objid ("aec1f055-cfd4-4f0a-b087-d1a67131a85a")
     SmObjectImpl mEntryOf;

    @objid ("c35790cb-6de6-407a-8ff0-c75b5d044ab5")
     List<SmObjectImpl> mConnection = null;

    @objid ("4e7e4886-7af9-4338-8730-d42d092eceb6")
     SmObjectImpl mEntryOfMachine;

    @objid ("915ebd82-1709-40e7-9d36-37365f7737fa")
    public EntryPointPseudoStateData(EntryPointPseudoStateSmClass smClass) {
        super(smClass);
    }

}
