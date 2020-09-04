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
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00859e2c-c4be-1fd8-97fe-001ec947cd2a")
public class ConstraintData extends UmlModelElementData {
    @objid ("d768eb70-0b5c-4d82-a8f8-bb7448da2831")
     Object mBaseClass = "";

    @objid ("81a53081-a620-4c98-bf1a-c73c13ce47f6")
     Object mBody = "";

    @objid ("0c7568aa-1721-4b8d-a238-1962f1074fd3")
     Object mLanguage = "";

    @objid ("b3e4b944-5a6e-4f6a-abc5-3c751d89e2e6")
     List<SmObjectImpl> mConstrainedElement = null;

    @objid ("a3ab7b49-7e10-4e6b-b51b-c3712237036c")
    public ConstraintData(ConstraintSmClass smClass) {
        super(smClass);
    }

}
