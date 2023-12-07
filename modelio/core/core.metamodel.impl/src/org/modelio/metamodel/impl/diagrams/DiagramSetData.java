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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.impl.diagrams;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("006e66a8-c4bf-1fd8-97fe-001ec947cd2a")
public class DiagramSetData extends ModelElementData {
    @objid ("2e269d6e-316a-4307-8881-1bf61a513070")
    List<SmObjectImpl> mSub = null;

    @objid ("5b9be396-f82a-40d0-a800-bfcfc63b928f")
    SmObjectImpl mParent;

    @objid ("1b5b53e7-de28-4224-bcae-05c80ff75729")
    List<SmObjectImpl> mReferencedDiagram = null;

    @objid ("959b09e5-0eb8-489b-b857-069006f1acb0")
    SmObjectImpl mOwner;

    @objid ("960ab947-1da5-428a-9fd2-399345ffe5d2")
    public  DiagramSetData(DiagramSetSmClass smClass) {
        super(smClass);
    }

}
