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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/

package org.modelio.metamodel.impl.diagrams;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00678cde-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class AbstractDiagramData extends ModelElementData {
    @objid ("41e8749c-0b36-4c81-a86c-76b9c43bc12e")
    Object mUiDataVersion = 0;

    @objid ("efdb6f9e-a69e-4920-ae01-b9950bd929a6")
    Object mUiData = "";

    @objid ("7641e38b-48aa-44a4-a0db-cf958529da67")
    Object mPreviewData = "";

    @objid ("0a8793ee-ae8e-4693-b24c-ddc59ac51770")
    List<SmObjectImpl> mRepresented = null;

    @objid ("dfed637b-db42-4458-acfb-c148d7c36dba")
    List<SmObjectImpl> mReferencingSet = null;

    @objid ("e86d19ce-989e-4da0-a918-7066f59a2fc0")
    SmObjectImpl mOrigin;

    @objid ("ed9a91d6-0fea-4d71-9166-9050274ec500")
    public  AbstractDiagramData(AbstractDiagramSmClass smClass) {
        super(smClass);
    }

}
