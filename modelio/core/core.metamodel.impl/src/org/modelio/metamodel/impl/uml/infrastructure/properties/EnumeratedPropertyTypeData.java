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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00718aa4-ec87-1098-b22e-001ec947cd2a")
public class EnumeratedPropertyTypeData extends PropertyTypeData {
    @objid ("97e64163-4565-423b-ba3b-50d5cbf6b407")
    List<SmObjectImpl> mLitteral = null;

    @objid ("8de808e4-48f0-4c12-b427-6c1715849241")
    List<SmObjectImpl> mOccurenceConfigParam = null;

    @objid ("49737b5f-aada-43d7-80b2-27e6f5fb3b68")
    public  EnumeratedPropertyTypeData(EnumeratedPropertyTypeSmClass smClass) {
        super(smClass);
    }

}
