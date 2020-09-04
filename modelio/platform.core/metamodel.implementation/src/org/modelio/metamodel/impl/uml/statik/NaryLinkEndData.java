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
package org.modelio.metamodel.impl.uml.statik;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00768072-17e8-10a1-88a0-001ec947cd2a")
public class NaryLinkEndData extends UmlModelElementData {
    @objid ("e8804c80-7687-45eb-b825-912def865e19")
     Object mIsOrdered = false;

    @objid ("72c8f896-4ef2-4f59-9603-30c09899b492")
     Object mIsUnique = false;

    @objid ("eac03c3a-706b-4793-bf04-236bc81ed18f")
     Object mMultiplicityMax = "1";

    @objid ("17ed44cd-cb8f-4d30-a1d9-ea85cce1c3bb")
     Object mMultiplicityMin = "0";

    @objid ("a339d404-a22a-4c1d-9909-b7f6db8196e2")
     SmObjectImpl mSource;

    @objid ("01029927-8379-461a-9b38-91efffae391a")
     SmObjectImpl mNaryLink;

    @objid ("8d86a03e-fe50-4ec5-85e4-e5eef842283a")
     SmObjectImpl mConsumer;

    @objid ("4257132f-054e-45e5-9440-485f8576a2fd")
     SmObjectImpl mProvider;

    @objid ("776c76d3-2fec-4cc6-81a7-57aeedc59362")
    public NaryLinkEndData(NaryLinkEndSmClass smClass) {
        super(smClass);
    }

}
