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
package org.modelio.metamodel.impl.bpmn.rootElements;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("007a84d8-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnGroupData extends BpmnArtifactData {
    @objid ("8af156f6-6349-4707-82b7-f2a06d874299")
     Object mCategory = "";

    @objid ("5bcf7164-45e1-4eb5-a611-1935fd8417cf")
     List<SmObjectImpl> mCategorized = null;

    @objid ("f358a6ec-c813-4546-892a-eac5de409c52")
    public BpmnGroupData(BpmnGroupSmClass smClass) {
        super(smClass);
    }

}
