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

package org.modelio.app.update.checker.dialog;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.app.update.repo.UpdateDescriptor;

@objid ("06c7bfc6-beb7-4270-b84c-97bf0c6cef66")
public class UpdatePanelDataModel {
    @objid ("0ed8a062-a447-4525-a959-1afca1ab699f")
    private Collection<UpdateDescriptor> availableUpdates;

    @objid ("44f0c9e8-091f-4bcb-9111-54d760023c52")
    private Set<UpdateDescriptor> selectedUpdates;

    @objid ("5cd86339-2565-458d-89f8-700e8a4a3321")
    public UpdatePanelDataModel(Collection<UpdateDescriptor> availableUpdates) {
        this.availableUpdates = availableUpdates;
        this.selectedUpdates = new HashSet<>();
    }

    @objid ("1aec3e19-812e-4ba9-8af2-7280782a7c38")
    public Collection<UpdateDescriptor> getAvailableUpdates() {
        return this.availableUpdates;
    }

    @objid ("6fd99b66-006f-40ce-a0b7-5583b92e89e3")
    public Collection<UpdateDescriptor> getSelectedUpdates() {
        return this.selectedUpdates;
    }

    @objid ("72258a29-7ad2-4dc0-b384-47dd6f366082")
    public boolean isSelected(UpdateDescriptor descriptor) {
        return this.selectedUpdates.contains(descriptor);
    }

    @objid ("cd34f945-b2d6-4e4b-943c-a46eb9726978")
    public void selectUpdate(UpdateDescriptor descriptor) {
        this.selectedUpdates.add(descriptor);
    }

    @objid ("942a6cdc-43b7-4e0c-bef9-0c1929086928")
    public void unSelectUpdate(UpdateDescriptor descriptor) {
        this.selectedUpdates.remove(descriptor);
    }

}
