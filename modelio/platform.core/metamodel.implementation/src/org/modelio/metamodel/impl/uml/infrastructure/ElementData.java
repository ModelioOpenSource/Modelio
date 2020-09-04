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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0086e58e-c4be-1fd8-97fe-001ec947cd2a")
public abstract class ElementData extends SmObjectData {
    @objid ("4138a423-8540-4f41-be06-0e4db32df010")
     List<SmObjectImpl> mDiagramElement = null;

    @objid ("16142263-c945-4a32-98fc-769c0f0683bb")
     List<SmObjectImpl> mAddedToQuery = null;

    @objid ("6a05d705-77de-4f20-837e-4e7b863d5ace")
     List<SmObjectImpl> mCausedImpact = null;

    @objid ("faa14fac-2aed-4cdd-a470-380925ad4bb4")
    public ElementData(ElementSmClass smClass) {
        super(smClass);
    }

}
