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

package org.modelio.audit.view.providers.bytype;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.audit.engine.core.IAuditDiagnostic;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.audit.view.model.AuditTypeModel;

@objid ("2707586a-c43b-4288-b78e-7158b5d1727e")
public class ByTypeContentProvider implements ITreeContentProvider {
    @objid ("5d5faa87-c9c9-4932-ab81-5e60e187c456")
    private String jobId;

    @objid ("09550d27-6afb-45c1-971c-e7f24c341a2e")
    private AuditTypeModel error;

    @objid ("cd7d3353-85c4-4986-add8-c224e104b3cd")
    private AuditTypeModel warning;

    @objid ("f1acf7db-367c-435b-8049-815d4e1f6554")
    private AuditTypeModel infos;

    @objid ("cd375493-a4fe-4dfd-ab76-73fa63b33792")
    public ByTypeContentProvider(String jobId) {
        this.jobId = jobId;
        
        this.error = new AuditTypeModel();
        this.error.severity = AuditSeverity.AuditError;
        
        this.warning = new AuditTypeModel();
        this.warning.severity = AuditSeverity.AuditWarning;
        
        this.infos = new AuditTypeModel();
        this.infos.severity = AuditSeverity.AuditAdvice;
    }

    @objid ("4bde8283-11dd-417e-9580-1547296aa33e")
    @Override
    public void dispose() {
        // Nothing to dispose
    }

    @objid ("79e4dd1a-66b4-40ec-980b-fcb2f986ac74")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // Nothing to change
    }

    @objid ("96605a13-1fc3-4e65-9dc0-154be54564a3")
    @Override
    public Object[] getElements(Object inputElement) {
        IAuditDiagnostic diagnostic = (IAuditDiagnostic) inputElement;
        
        List<IAuditEntry> entries = diagnostic.getEntries(this.jobId);
        
        this.error.entries.clear();
        this.warning.entries.clear();
        this.infos.entries.clear();
        
        for (int i = 0; i < entries.size(); i++) {
            IAuditEntry entry = entries.get(i);
            if (entry.getSeverity().equals(AuditSeverity.AuditError)) {
                this.error.entries.add(entry);
            } else if (entry.getSeverity().equals(AuditSeverity.AuditWarning)) {
                this.warning.entries.add(entry);
            } else if (entry.getSeverity().equals(AuditSeverity.AuditAdvice)) {
                this.infos.entries.add(entry);
            }
        }
        return new Object[] { this.error, this.warning, this.infos };
    }

    @objid ("12e7416d-4b59-4607-9e64-fd39b96dcf35")
    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof AuditTypeModel) {
            return ((AuditTypeModel) parentElement).entries.toArray();
        }
        return null;
    }

    @objid ("a0c9cfb4-863c-49a4-bd67-f7f1705aba65")
    @Override
    public Object getParent(Object element) {
        return null;
    }

    @objid ("bb9b33a6-2ffc-499f-8916-b4126afbc3dc")
    @Override
    public boolean hasChildren(Object element) {
        return element instanceof AuditTypeModel && !((AuditTypeModel) element).entries.isEmpty();
    }

}
