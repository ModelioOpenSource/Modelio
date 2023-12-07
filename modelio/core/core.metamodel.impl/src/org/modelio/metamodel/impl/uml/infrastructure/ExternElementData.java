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

package org.modelio.metamodel.impl.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("b77438d0-b4b9-436b-ae73-660cf660e733")
public class ExternElementData extends ModelElementData {
    @objid ("e835a36b-fbfc-427a-89e4-994d564c84b7")
    Object mProvider = "";

    @objid ("79619f02-1d07-4aaa-a3fe-f8ae9eb8bb42")
    Object mExternId = "";

    @objid ("488d681f-a40f-4509-987b-1876cb3f7178")
    Object mLocation = "";

    @objid ("c2a94584-b1ef-42ec-85b3-19acbfe75b5e")
    SmObjectImpl mOwner;

    @objid ("ae961d8d-02f9-4c5a-b5a4-b1c039ba11d3")
    public  ExternElementData(ExternElementSmClass smClass) {
        super(smClass);
    }

}
