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

package org.modelio.metamodel.impl.mda;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractProjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0066673c-c4bf-1fd8-97fe-001ec947cd2a")
public class ProjectData extends AbstractProjectData {
    @objid ("4ea4883f-7060-47ff-80af-8c342805513f")
    Object mProjectContext = "";

    @objid ("c32bc505-f179-4516-b914-d75b6ca72bb7")
    Object mProjectDescr = "";

    @objid ("b299b93b-2122-4b36-8446-3d078c866bfc")
    List<SmObjectImpl> mModel = null;

    @objid ("5a7dee31-aea0-47e7-a99e-4842ffef35fa")
    public  ProjectData(ProjectSmClass smClass) {
        super(smClass);
    }

}
