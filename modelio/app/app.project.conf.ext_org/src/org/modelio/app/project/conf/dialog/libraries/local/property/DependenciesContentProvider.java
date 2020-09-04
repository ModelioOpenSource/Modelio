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

package org.modelio.app.project.conf.dialog.libraries.local.property;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.vbasic.version.VersionedItem;

@objid ("f3bf9875-1d15-456f-ae63-06df6e7cf485")
class DependenciesContentProvider implements IStructuredContentProvider {
    @objid ("e8f517d9-9d43-4e4f-bba8-42c3d9c1c340")
    @SuppressWarnings("unchecked")
    @Override
    public Object[] getElements(Object inputElement) {
        List<VersionedItem<?>> elements = (List<VersionedItem<?>>) inputElement;
        return elements.toArray(new VersionedItem[elements.size()]);
    }

    @objid ("cadc8d61-3ffa-4686-9cf3-4dc9403506f8")
    @Override
    public void dispose() {
        // Nothing to do
    }

    @objid ("ec10a2f4-5e6a-417e-a7f7-28e09cd64821")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // Nothing to do
    }

}
