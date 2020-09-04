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
package org.modelio.metamodel.impl.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("2eae30e5-5566-403a-a2fc-fb71b87360b4")
public class LinkData extends UmlModelElementData {
    @objid ("6b5b78ef-6c18-4e82-baf3-99bb2e9e2a72")
     SmObjectImpl mModel;

    @objid ("59111878-f8e3-4d1a-be54-f52ff8c1f548")
     List<SmObjectImpl> mLinkEnd = null;

    @objid ("6ab9680e-1c50-40f9-8312-8183a8582fea")
     SmObjectImpl mSent;

    @objid ("17e4e557-e7d0-4796-b8d3-afa55cc2635e")
    public LinkData(LinkSmClass smClass) {
        super(smClass);
    }

}
