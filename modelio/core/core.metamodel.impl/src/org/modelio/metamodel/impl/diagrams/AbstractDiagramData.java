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

package org.modelio.metamodel.impl.diagrams;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00678cde-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class AbstractDiagramData extends ModelElementData {
    @objid ("0d48815e-2c51-45a4-b7bb-b19f2c36fb11")
    Object mUiDataVersion = 0;

    @objid ("0c66d60e-05da-4464-8723-9305406a33fd")
    Object mUiData = "";

    @objid ("68873bfa-f5df-484b-a9cb-e4bdda1a3901")
    Object mPreviewData = "";

    @objid ("4da17199-9568-4299-956c-609bfc7af13b")
    Object mJsStructure = "";

    @objid ("0f499f53-f33c-4239-92fc-265367ba7d52")
    List<SmObjectImpl> mRepresented = null;

    @objid ("0a91c58e-6a73-48f2-a048-437f98ddec15")
    List<SmObjectImpl> mReferencingSet = null;

    @objid ("6759823e-a52e-4c48-9828-d0a32f7b128e")
    SmObjectImpl mOrigin;

    @objid ("bf1092c7-4691-44ec-922e-d2ef106f8cf8")
    public  AbstractDiagramData(AbstractDiagramSmClass smClass) {
        super(smClass);
    }

}
