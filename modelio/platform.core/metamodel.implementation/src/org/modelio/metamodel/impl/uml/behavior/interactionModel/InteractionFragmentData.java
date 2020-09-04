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
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0047676a-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class InteractionFragmentData extends UmlModelElementData {
    @objid ("bbfab4d4-43ed-4bad-9fda-f65e1208ca9b")
     Object mLineNumber = -1;

    @objid ("97cdedf7-f704-4f6c-ab6d-55221db6e4da")
     SmObjectImpl mEnclosingOperand;

    @objid ("8fb1b7df-f632-4172-918e-89ac603cb630")
     SmObjectImpl mEnclosingInteraction;

    @objid ("f7fa7641-7031-499b-be50-6785e190d710")
     List<SmObjectImpl> mCovered = null;

    @objid ("f372e125-190b-48ff-9c1a-f3ee4b2d2eae")
    public InteractionFragmentData(InteractionFragmentSmClass smClass) {
        super(smClass);
    }

}
