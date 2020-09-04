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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0086407a-c4be-1fd8-97fe-001ec947cd2a")
public class DependencyData extends ModelElementData {
    @objid ("8fb91430-f0dc-4466-a34c-27d5c1e263bc")
     SmObjectImpl mImpacted;

    @objid ("2d2f50b1-1bf8-4dbd-b98c-31f1641692a0")
     SmObjectImpl mDependsOn;

    @objid ("c1b97965-dbad-4176-9937-69478b36310b")
    public DependencyData(DependencySmClass smClass) {
        super(smClass);
    }

}
