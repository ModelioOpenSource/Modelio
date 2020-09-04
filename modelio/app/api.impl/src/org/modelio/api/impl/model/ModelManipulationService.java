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

package org.modelio.api.impl.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelManipulationService;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("4e1c6024-bf6b-41b2-9a78-d8dc740732da")
public class ModelManipulationService implements IModelManipulationService {
    @objid ("146be801-7b74-4a4f-8bef-0d8a507dd842")
    @Override
    public MObject clone(final MObject element) {
        return MTools.getModelTool().cloneElement(element);
    }

    @objid ("ce71e38c-cc5a-43a5-8325-78dcffc30efc")
    @Override
    public List<MObject> copyTo(final Collection<? extends MObject> elements, final MObject to) {
        return MTools.getModelTool().copyElements(new ArrayList<>(elements), to);
    }

    @objid ("e9ee5816-7e05-491d-96a5-f70f05e00130")
    @Override
    public void moveTo(final Collection<? extends MObject> elements, final MObject to) {
        moveTo(elements, to, null);
    }

    @objid ("8b3041fe-1056-498b-a5f1-4ca282f2e116")
    @Override
    public void moveTo(Collection<? extends MObject> elements, MObject to, MObject oldParentHint) {
        MTools.getModelTool().moveElements(new ArrayList<>(elements), to, oldParentHint);
    }

}
