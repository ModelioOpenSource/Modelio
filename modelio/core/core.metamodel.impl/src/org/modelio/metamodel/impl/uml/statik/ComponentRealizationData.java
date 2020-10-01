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
package org.modelio.metamodel.impl.uml.statik;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("085ab992-591c-4bfe-a9dd-e52568b31bc1")
public class ComponentRealizationData extends UmlModelElementData {
    @objid ("bde5853b-b810-499f-9dd3-ce3c24dfccff")
     SmObjectImpl mRealizingClassifier;

    @objid ("8520b53a-7369-43b7-8ae4-7b05330e0a2e")
     SmObjectImpl mAbstraction;

    @objid ("47111a81-d6b5-4662-83ad-74936512dc13")
    public ComponentRealizationData(ComponentRealizationSmClass smClass) {
        super(smClass);
    }

}
