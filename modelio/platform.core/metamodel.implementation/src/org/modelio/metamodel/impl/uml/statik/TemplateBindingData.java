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

@objid ("001cc6ae-c4bf-1fd8-97fe-001ec947cd2a")
public class TemplateBindingData extends UmlModelElementData {
    @objid ("f322f99e-4b76-481b-8905-918ac12a437f")
     List<SmObjectImpl> mParameterSubstitution = null;

    @objid ("94b6c2e6-3bbc-424f-b7e5-1116c3cbf1db")
     SmObjectImpl mBoundOperation;

    @objid ("d8a37f78-c9ed-42b0-a89c-d18512a152d9")
     SmObjectImpl mInstanciatedTemplateOperation;

    @objid ("14b758cc-0f8e-487f-bda8-8e1b0528c232")
     SmObjectImpl mInstanciatedTemplate;

    @objid ("ec15deb3-62cc-447d-96de-5bda66961c63")
     SmObjectImpl mBoundElement;

    @objid ("fbf224d9-f078-4ad3-8d7d-c8589048e4eb")
    public TemplateBindingData(TemplateBindingSmClass smClass) {
        super(smClass);
    }

}
