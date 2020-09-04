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

/**
 *
 */
package org.modelio.app.project.conf.dialog.libraries.local.property;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.vbasic.version.VersionedItem;

/**
 * @author xzhang
 */
@objid ("ac9748cb-5e9e-439c-abc4-0bd8ee8ed71e")
public class ContributingModulesContentProvider implements IStructuredContentProvider {
    @objid ("2736a72d-95dd-40c0-b4b7-178cbc89807b")
    @SuppressWarnings("unchecked")
    @Override
    public Object[] getElements(Object inputElement) {
        List<VersionedItem<?>> elements = (List<VersionedItem<?>>) inputElement;
        return elements.toArray(new VersionedItem[elements.size()]);
    }

    @objid ("ed801a67-dab4-4f19-84ee-76ec1d1faede")
    @Override
    public void dispose() {
        // Nothing to do
    }

    @objid ("fc07bc3c-2d53-4f91-9b43-faea8ea5c929")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // Nothing to do
    }

}
