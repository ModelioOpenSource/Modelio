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

package org.modelio.audit.view.providers.flat;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.audit.engine.impl.AuditDiagnostic;

@objid ("62010bf6-89c7-424a-bbb6-b43036a22777")
public class FlatContentProvider implements ITreeContentProvider {
    @objid ("73d17f06-ebbd-4f0f-b5d0-4b1e22298248")
    private String jobId;

    @objid ("481042a7-2d03-4750-a53c-3a05f01e0b8a")
    public FlatContentProvider(String jobId) {
        this.jobId = jobId;
    }

    @objid ("e333f238-191f-47d4-9c37-6053407040a2")
    @Override
    public void dispose() {
        // Empty
    }

    @objid ("d086f60e-9545-44df-be90-40956ded69a5")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // Empty
    }

    @objid ("176f8498-3515-4780-bc80-006be3af566d")
    @Override
    public Object[] getElements(Object inputElement) {
        AuditDiagnostic diagnostic = (AuditDiagnostic) inputElement;
        return diagnostic.getEntries(this.jobId).toArray();
    }

    @objid ("384f929c-d36c-4cf6-9cf1-a4f6dbe34c36")
    @Override
    public Object[] getChildren(Object parentElement) {
        return null;
    }

    @objid ("58ae8576-e591-48e1-b349-f29543248e11")
    @Override
    public Object getParent(Object element) {
        return null;
    }

    @objid ("80676362-5e89-4815-a7b0-7ed0e88628b2")
    @Override
    public boolean hasChildren(Object element) {
        return false;
    }

}
