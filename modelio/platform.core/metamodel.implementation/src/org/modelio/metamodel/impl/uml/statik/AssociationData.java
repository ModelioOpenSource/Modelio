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

@objid ("60b053d8-9a17-42c3-83bc-888ce3e68a7d")
public class AssociationData extends UmlModelElementData {
    @objid ("c9583c98-a0b1-42cf-8dc0-8ae846cdbc28")
     List<SmObjectImpl> mOccurence = null;

    @objid ("3397cca3-fc0e-49a1-80a4-3208090d3dad")
     List<SmObjectImpl> mEnd = null;

    @objid ("66916f03-cfb0-409d-a5be-5fda040ccf0c")
     SmObjectImpl mLinkToClass;

    @objid ("552465d2-bbd8-4f6d-97e8-c8b5125ffd18")
    public AssociationData(AssociationSmClass smClass) {
        super(smClass);
    }

}
