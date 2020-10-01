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
package org.modelio.metamodel.impl.bpmn.objects;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("000442fa-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnDataStoreData extends BpmnItemAwareElementData {
    @objid ("8bea33ba-8df2-45a0-aa33-828a7bc1069f")
     Object mCapacity = 0;

    @objid ("f544b99d-fa37-419f-ad69-e3fbaad4dca1")
     Object mIsUnlimited = false;

    @objid ("5bfab947-b402-4514-a68b-a5e31636fa7b")
    public BpmnDataStoreData(BpmnDataStoreSmClass smClass) {
        super(smClass);
    }

}
