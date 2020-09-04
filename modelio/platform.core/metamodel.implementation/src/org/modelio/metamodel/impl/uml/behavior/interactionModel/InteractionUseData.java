/* 
 * Copyright 2013-2019 Modeliosoft
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
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0048846a-c4bf-1fd8-97fe-001ec947cd2a")
public class InteractionUseData extends InteractionFragmentData {
    @objid ("64bb8651-4926-4571-95d7-bb0b9fb831ee")
     Object mEndLineNumber = 0;

    @objid ("24944af9-9db5-4c85-a4e1-af05ebf72773")
     List<SmObjectImpl> mActualGate = null;

    @objid ("bceda5db-a5ef-4663-bb98-263e2629868c")
     SmObjectImpl mRefersTo;

    @objid ("7b36823a-dc94-41ab-bbc1-a2eea626581d")
    public InteractionUseData(InteractionUseSmClass smClass) {
        super(smClass);
    }

}
