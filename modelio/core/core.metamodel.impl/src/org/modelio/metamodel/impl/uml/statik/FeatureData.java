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

@objid ("000b775a-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class FeatureData extends UmlModelElementData {
    @objid ("aaeb430a-abb0-4211-8490-515b4fa8eb64")
     Object mVisibility = VisibilityMode.PUBLIC;

    @objid ("6c8bf38f-8c5d-4f7f-b027-eddb5bf4bc1b")
     Object mIsClass = false;

    @objid ("c473548e-bf69-4f47-9eb6-b6734004b56d")
     Object mIsAbstract = false;

    @objid ("300eab32-b89b-4740-bc73-ea7654018218")
    public FeatureData(FeatureSmClass smClass) {
        super(smClass);
    }

}
