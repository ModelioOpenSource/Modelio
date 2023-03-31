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
package org.modelio.app.project.ui.views.workspace;

import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.app.project.ui.views.workspace.WorkspaceTreeView.ProjectCache;
import org.modelio.gproject.core.IGProject;

/**
 * Workspace tree view content provider.
 */
@objid ("81684ae5-992d-41fa-9cde-50d9c022d2d0")
public class WksContentProvider implements ITreeContentProvider {
    @objid ("31b12f71-45ab-4e2c-9e0d-ef558aa80569")
    @Override
    public void dispose() {
        // nothing to do
    }

    @objid ("6558d770-7dbb-4539-b53d-696126d0e1f8")
    @Override
    public Object[] getChildren(final Object parent) {
        if (parent instanceof IGProject) {
            IGProject project = (IGProject) parent;
            return project.getParts().toArray();
        }
        return Collections.EMPTY_LIST.toArray();
    }

    @objid ("a6075c22-78da-43c4-bf58-823553451b36")
    @Override
    public Object[] getElements(final Object parent) {
        assert (parent instanceof ProjectCache);
        return ((ProjectCache) parent).getProjects();
    }

    @objid ("c3cdd8f9-5b39-4b92-a760-c1a1e07eab55")
    @Override
    public Object getParent(final Object child) {
        return null;
    }

    @objid ("f97fcaa2-0554-4302-a0f5-4122e6d3ef35")
    @Override
    public boolean hasChildren(final Object parent) {
        return false;
    }

    @objid ("305cb4d2-9186-4f3c-9cae-1686c3c0e7a7")
    @Override
    public void inputChanged(final Viewer v, final Object oldInput, final Object newInput) {
        // nothing to do
    }

}
