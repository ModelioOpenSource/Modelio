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

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("008d9532-c4be-1fd8-97fe-001ec947cd2a")
public class StereotypeData extends ModelElementData {
    @objid ("10728182-e525-438d-9835-2ce163fdbc65")
    Object mImage = "";

    @objid ("17625c52-a537-405e-98c6-b5aaaee641eb")
    Object mIcon = "";

    @objid ("73883295-60ee-4855-aa45-11a7771a7788")
    Object mIsHidden = false;

    @objid ("c07bf5c1-8621-4425-84a5-393944cc5bc0")
    Object mIsAbstract = false;

    @objid ("66650ed6-1f1e-4d43-a5db-ef62713cb81b")
    Object mLabelKey = "";

    @objid ("f18859dc-1e7c-451e-aba2-8c97ab55f1c5")
    Object mBaseClassName = "";

    @objid ("fbe4768f-b0e9-43da-ba07-57f3c1a4a639")
    SmObjectImpl mDefinedTable;

    @objid ("595062ba-8fa9-4805-ab3f-c2c93a7176b7")
    List<SmObjectImpl> mDefinedResourceType = null;

    @objid ("febfc682-f031-47a8-96c4-5e46407ea2de")
    SmObjectImpl mOwner;

    @objid ("9b25e96b-d993-445e-aa6e-7e162d347329")
    SmObjectImpl mParent;

    @objid ("b04fbcb9-2e48-4899-9c82-167410f7c744")
    List<SmObjectImpl> mDefinedTagType = null;

    @objid ("4edafcaa-dfe4-4d68-97fa-d8451b622acc")
    List<SmObjectImpl> mChild = null;

    @objid ("baf08654-80dd-4289-a984-1ea5630422cb")
    List<SmObjectImpl> mDefinedNoteType = null;

    @objid ("ce695e1b-fbac-4c73-9b12-8942bf3a85e9")
    List<SmObjectImpl> mExtendedElement = null;

    @objid ("e8e534c7-3cd8-4afe-8a0c-7e3309ed30f7")
    public  StereotypeData(StereotypeSmClass smClass) {
        super(smClass);
    }

}
