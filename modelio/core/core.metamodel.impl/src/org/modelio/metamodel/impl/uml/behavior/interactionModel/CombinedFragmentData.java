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
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperator;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0043a60c-c4bf-1fd8-97fe-001ec947cd2a")
public class CombinedFragmentData extends InteractionFragmentData {
    @objid ("86a89fe4-32da-4f07-a626-2b7f1021383c")
     Object mOperator = InteractionOperator.SEQOP;

    @objid ("1f06420d-72d9-4e80-8386-cb046db34ae8")
     List<SmObjectImpl> mOperand = null;

    @objid ("7ab52351-4a7e-4498-a32c-cb1c98058ea8")
     List<SmObjectImpl> mFragmentGate = null;

    @objid ("cb82ef30-08c4-408c-a34b-d24c000265b9")
    public CombinedFragmentData(CombinedFragmentSmClass smClass) {
        super(smClass);
    }

}
