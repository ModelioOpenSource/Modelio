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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("000cfae4-c4bf-1fd8-97fe-001ec947cd2a")
public class GeneralizationData extends UmlModelElementData {
    @objid ("8886a3fb-bc3c-490d-a781-46bdfea71582")
     Object mDiscriminator = "";

    @objid ("b9c712cb-881f-4dfa-b49a-285bf3cfd709")
     SmObjectImpl mSuperType;

    @objid ("408374fb-7796-488a-baed-c3c443ac949f")
     SmObjectImpl mSubType;

    @objid ("8f990905-9ae2-4393-9763-9b47acdc0eef")
    public GeneralizationData(GeneralizationSmClass smClass) {
        super(smClass);
    }

}
