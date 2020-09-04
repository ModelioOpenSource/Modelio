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

package org.modelio.audit.view.providers.byelement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.engine.impl.AuditDiagnostic;
import org.modelio.audit.view.model.AuditElementModel;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("a7543aa8-8a12-4d0e-9ce2-910ffa689f02")
public class ByElementContentProvider implements ITreeContentProvider {
    @objid ("9b6cc6c7-fd11-4759-8c66-3179f9976718")
    private String jobId;

    @objid ("1bd398b3-eb7a-4bbb-8152-aa497d6a4236")
    private Map<MObject, AuditElementModel> elementsMap;

    @objid ("f481d599-3c86-45f1-acee-51a9c389e4a8")
    public ByElementContentProvider(String jobId) {
        this.jobId = jobId;
        this.elementsMap = new HashMap<>();
    }

    @objid ("1c5a0474-e3f2-40a4-a1c7-ec39f0dd5eab")
    @Override
    public void dispose() {
        this.elementsMap.clear();
    }

    @objid ("836b0a0c-d066-40a1-a69b-2da718f5aa10")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // Empty
    }

    @objid ("778261c4-cf91-4340-9305-2bc48aa25f8a")
    @Override
    public Object[] getElements(Object inputElement) {
        AuditDiagnostic diagnostic = (AuditDiagnostic) inputElement;
        
        List<IAuditEntry> entries = diagnostic.getEntries(this.jobId);
        
        this.elementsMap.clear();
        
        for (int i = 0; i < entries.size(); i++) {
            IAuditEntry entry = entries.get(i);
            AuditElementModel model = this.elementsMap.get(entry.getElement());
        
            if (model == null) {
                model = new AuditElementModel();
                model.element = entry.getElement();
                this.elementsMap.put(entry.getElement(), model);
            }
        
            model.entries.add(entry);
        
        }
        return this.elementsMap.values().toArray();
    }

    @objid ("088b450a-eea1-47ac-bcca-e90bbce95712")
    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof AuditElementModel) {
            return ((AuditElementModel) parentElement).entries.toArray();
        }
        return null;
    }

    @objid ("1afe5e31-3510-4333-9059-c819fa541e41")
    @Override
    public Object getParent(Object element) {
        return null;
    }

    @objid ("1cab390a-fb4d-4618-9d2c-f8074c37b6ee")
    @Override
    public boolean hasChildren(Object element) {
        return element instanceof AuditElementModel && !((AuditElementModel) element).entries.isEmpty();
    }

}
