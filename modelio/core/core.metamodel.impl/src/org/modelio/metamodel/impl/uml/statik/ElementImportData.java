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
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("000879a6-c4bf-1fd8-97fe-001ec947cd2a")
public class ElementImportData extends UmlModelElementData {
    @objid ("db5fb2cf-94c3-4b6b-b8cb-288b0fa7f4de")
     Object mVisibility = VisibilityMode.PRIVATE;

    @objid ("a97d0f00-8c07-4245-88be-4b434fcb57bc")
     SmObjectImpl mImportingNameSpace;

    @objid ("8738fc74-3afb-4627-a713-52ba32acb0be")
     SmObjectImpl mImportedElement;

    @objid ("8aca47ea-1801-445c-a5b7-d33a62a652c7")
     SmObjectImpl mImportingOperation;

    @objid ("681b512b-8424-4c9e-811c-5625e52f76f6")
    public ElementImportData(ElementImportSmClass smClass) {
        super(smClass);
    }

}
