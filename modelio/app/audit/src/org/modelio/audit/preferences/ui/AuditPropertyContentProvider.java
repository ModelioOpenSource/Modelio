/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.audit.preferences.ui;

import java.util.TreeSet;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.audit.preferences.model.AuditCategory;
import org.modelio.audit.preferences.model.AuditConfigurationModel;

@objid ("20e2ac59-0709-43cf-bf56-97ab93c4d734")
public class AuditPropertyContentProvider implements ITreeContentProvider {
    @objid ("8cd17ba2-0ccc-4265-a8c2-0342d1c90595")
    @Override
    public void dispose() {
        // Empty
    }

    @objid ("30c8a653-dbaf-42c5-a9cc-cb8a90bdb383")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // Empty
    }

    @objid ("b9a0d0fe-f8da-4d12-b097-7bec03ba3f8f")
    @Override
    public Object[] getElements(Object inputElement) {
        AuditConfigurationModel model = (AuditConfigurationModel) inputElement;
        // Sort categories...
        return new TreeSet<>(model.getCategories()).toArray();
    }

    @objid ("244d0a5b-63e4-4c2f-a53f-47f73f26fea2")
    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof AuditCategory) {
            return ((AuditCategory) parentElement).getRules().toArray();
        }
        return null;
    }

    @objid ("f8855ea6-4db0-4739-ad62-9f477208800a")
    @Override
    public Object getParent(Object element) {
        return null;
    }

    @objid ("a460c9a9-c2d8-481b-89ac-051ef02794ae")
    @Override
    public boolean hasChildren(Object element) {
        if (element instanceof AuditCategory) {
            return ((AuditCategory) element).getRules().size() > 0;
        }
        return false;
    }

}
