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
package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00718aa4-ec87-1098-b22e-001ec947cd2a")
public class EnumeratedPropertyTypeData extends PropertyTypeData {
    @objid ("7cd41f0d-5477-4460-8f98-4fe0b0c37e73")
     List<SmObjectImpl> mLitteral = null;

    @objid ("9fee42f7-dc0f-4b65-881f-ee366a34acd4")
     List<SmObjectImpl> mOccurenceConfigParam = null;

    @objid ("e67e9d94-3fb6-4ad2-ad0d-978dc587a973")
    public EnumeratedPropertyTypeData(EnumeratedPropertyTypeSmClass smClass) {
        super(smClass);
    }

}
