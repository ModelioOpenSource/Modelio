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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.MethodPassingMode;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0014d9d0-c4bf-1fd8-97fe-001ec947cd2a")
public class OperationData extends BehavioralFeatureData {
    @objid ("c04129e2-0758-4a31-bdff-f3851d2593c6")
     Object mConcurrency = false;

    @objid ("c4808ed8-886b-4c68-a488-831cdf8a6a4e")
     Object mFinal = false;

    @objid ("ba31feb5-6cfb-4c87-a883-f90c0d5c249e")
     Object mPassing = MethodPassingMode.METHODOUT;

    @objid ("a0f6e88f-269d-4068-aeb4-10756ec56121")
     List<SmObjectImpl> mOwnedImport = null;

    @objid ("741b6e44-eed8-4dcf-bbcb-d1fa21d2233a")
     List<SmObjectImpl> mThrown = null;

    @objid ("b6bf4f4f-11cd-48e7-9d2b-4872a8f2f35b")
     List<SmObjectImpl> mRedefinition = null;

    @objid ("12b7bf7a-e2fc-4aac-906e-7d70e5ac6c49")
     List<SmObjectImpl> mExample = null;

    @objid ("1b0997d9-d1a8-4b4b-9cde-c9016cd02454")
     List<SmObjectImpl> mSRepresentation = null;

    @objid ("5a172787-8340-42f1-8ded-cbb6fc3ae564")
     List<SmObjectImpl> mOwnedBehavior = null;

    @objid ("b70abe55-fd4d-4309-8440-b5ccd5b48589")
     List<SmObjectImpl> mIO = null;

    @objid ("1ceaf78e-40fd-4b42-9d64-5608b463ec8b")
     List<SmObjectImpl> mTemplateInstanciation = null;

    @objid ("b8bed8ed-7f02-45bd-8fa7-5089fc47a3f9")
     SmObjectImpl mOwner;

    @objid ("dcabf681-9a52-4486-8d40-b693d25ddbc7")
     List<SmObjectImpl> mOwnedPackageImport = null;

    @objid ("e5847ec3-85ba-4e20-9c81-a58d82b8b4f9")
     SmObjectImpl mReturn;

    @objid ("7c55b2bb-07e8-4f1c-9845-f73dc576fb88")
     List<SmObjectImpl> mInstanciatingBinding = null;

    @objid ("af36a793-6571-40c3-a22f-40254c1aa180")
     List<SmObjectImpl> mUsage = null;

    @objid ("aae2df30-eaf7-486d-8ed9-d9273376e2e0")
     List<SmObjectImpl> mTemplate = null;

    @objid ("1b67e639-fc85-4d65-aace-ff27fb40e4cc")
     List<SmObjectImpl> mOccurence = null;

    @objid ("9a8f7fdb-646e-4f74-b26f-265c90d2f87f")
     List<SmObjectImpl> mInvoker = null;

    @objid ("b4588755-b265-4a87-9c30-d6e3fc0b4b27")
     List<SmObjectImpl> mCommunicationUsage = null;

    @objid ("7ad3ab2d-65c3-4235-8bc6-ce9e10be2981")
     List<SmObjectImpl> mOwnedCollaborationUse = null;

    @objid ("d33558d7-ae5a-42be-b27a-774f4ff5b335")
     SmObjectImpl mRedefines;

    @objid ("efe0f3fa-9856-4d03-9714-87ad59a97200")
     List<SmObjectImpl> mCallingAction = null;

    @objid ("629c115c-59c1-4f33-8c97-6bac55c9aa8c")
     List<SmObjectImpl> mEntryPointAction = null;

    @objid ("df8df440-214d-4c7b-87ec-4bd53e56be01")
    public OperationData(OperationSmClass smClass) {
        super(smClass);
    }

}
