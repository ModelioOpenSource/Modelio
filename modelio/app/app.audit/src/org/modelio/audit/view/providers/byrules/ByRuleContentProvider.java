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

package org.modelio.audit.view.providers.byrules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.engine.impl.AuditDiagnostic;
import org.modelio.audit.view.model.AuditRuleModel;

@objid ("a66bef42-419a-4df3-916c-cc59bd8e496f")
public class ByRuleContentProvider implements ITreeContentProvider {
    @objid ("0b4ab8b7-fa9d-46d2-8496-6306d006b71e")
    private String jobId;

    @objid ("e19aa905-396d-4927-b5e6-4ed052687ab1")
    private Map<String, AuditRuleModel> elementsMap;

    @objid ("0c3d56ce-b71c-45d1-8cfe-6cc3e097db70")
    public ByRuleContentProvider(String jobId) {
        this.jobId = jobId;
        this.elementsMap = new HashMap<>();
    }

    @objid ("50cb486a-67bc-46f2-a0a7-3b0bd190cc72")
    @Override
    public void dispose() {
        this.elementsMap.clear();
    }

    @objid ("185cc098-d646-4b7d-a9be-327d1abed511")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // Empty
    }

    @objid ("0c1f4072-892e-45cc-8a3b-80d8850abd28")
    @Override
    public Object[] getElements(Object inputElement) {
        AuditDiagnostic diagnostic = (AuditDiagnostic) inputElement;
        
        List<IAuditEntry> entries = diagnostic.getEntries(this.jobId);
        
        this.elementsMap.clear();
        
        for (int i = 0; i < entries.size(); i++) {
            IAuditEntry entry = entries.get(i);
            AuditRuleModel model = this.elementsMap.get(entry.getRuleId());
        
            if (model == null) {
                model = new AuditRuleModel();
                model.rule = entry.getRuleId();
                model.severity = entry.getSeverity();
                this.elementsMap.put(entry.getRuleId(), model);
            }
        
            model.entries.add(entry);
        }
        return this.elementsMap.values().toArray();
    }

    @objid ("5896405d-c418-4808-947b-8e896d8746f5")
    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof AuditRuleModel) {
            return ((AuditRuleModel) parentElement).entries.toArray();
        }
        return null;
    }

    @objid ("f9742413-7f40-496a-816e-e4c4c692042b")
    @Override
    public Object getParent(Object element) {
        return null;
    }

    @objid ("0f3bfb34-425f-4ca4-b443-b7eeb9055d2d")
    @Override
    public boolean hasChildren(Object element) {
        return element instanceof AuditRuleModel && !((AuditRuleModel) element).entries.isEmpty();
    }

}
