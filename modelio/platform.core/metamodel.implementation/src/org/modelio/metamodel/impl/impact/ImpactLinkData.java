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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.impl.impact;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("f1080505-52a8-4f93-a180-fddc182cfd72")
public class ImpactLinkData extends ModelElementData {
    @objid ("d112faf4-c2a2-46bc-ab78-bd5465587b5b")
     SmObjectImpl mDependsOn;

    @objid ("435941f0-8c10-4d12-96c0-eddc18ae01b6")
     SmObjectImpl mImpacted;

    @objid ("7ec19aba-ef54-495b-8280-3ee6c5051af0")
     List<SmObjectImpl> mCauses = null;

    @objid ("bd411fb7-165e-4c32-bbdd-de5f07f07cc3")
     SmObjectImpl mOwner;

    @objid ("f467d2a6-c4b9-48d6-ab01-27551a86faf2")
    public ImpactLinkData(ImpactLinkSmClass smClass) {
        super(smClass);
    }

}
