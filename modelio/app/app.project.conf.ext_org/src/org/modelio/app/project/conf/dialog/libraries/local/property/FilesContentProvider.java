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

import java.io.File;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

@objid ("b469cc26-67b5-48ae-ac46-17ba1eedd599")
class FilesContentProvider implements IStructuredContentProvider {
    @objid ("130c62b9-4b56-4aa9-8530-58f6c851450b")
    @SuppressWarnings("unchecked")
    @Override
    public Object[] getElements(Object inputElement) {
        Set<File> files = (Set<File>) inputElement;
        return files.toArray(new File[files.size()]);
    }

    @objid ("88b570e0-d5ba-4045-8bfd-2a3bc7e9e76c")
    @Override
    public void dispose() {
        // Nothing to do
    }

    @objid ("6cc7ffcf-1801-440e-9620-1d3f4b146aa6")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // Nothing to do
    }

}
