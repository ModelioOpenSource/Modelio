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

package org.modelio.audit.engine.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.AuditEntry;
import org.modelio.audit.engine.core.IAuditDiagnostic;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.engine.core.IAuditListener;
import org.modelio.audit.engine.core.IRule;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("61091295-2dfe-4e8b-8cd8-f422c2aeb463")
public class AuditDiagnostic implements IAuditDiagnostic {
    @objid ("4dc1dd97-37d0-4dfd-bc4f-e6dd0c82fcdb")
    private int nErrors = 0;

    @objid ("ce459afc-e563-4a7f-b119-00027d0328ae")
    private int nWarnings = 0;

    @objid ("af29eec9-837c-4af5-92bb-01a7fc000d4b")
    private int nTips = 0;

    @objid ("131a6d56-f00f-49f1-94d8-1cb8b0ed6e0d")
    private List<IAuditEntry> entries;

    @objid ("93fde6ab-1a7f-4e96-980d-0014c1f24c79")
    private List<IAuditListener> auditListeners = new ArrayList<>();

    /**
     * Default constructor.
     */
    @objid ("4dc825d2-d00b-49c3-952d-f14df049dc42")
    public AuditDiagnostic() {
        this.entries = new ArrayList<>();
    }

    @objid ("189bd0a7-afcf-4500-8a06-1f6854f47558")
    @Override
    public synchronized List<IAuditEntry> getEntries() {
        return this.entries;
    }

    @objid ("308e961f-d900-49fc-aa6a-5ddf506fb612")
    @Override
    public synchronized List<IAuditEntry> getEntries(String jobId) {
        if ("".equals(jobId)) {
            return getEntries();
        }
        
        List<IAuditEntry> jobEntries = new ArrayList<>();
        for (IAuditEntry entry : this.entries) {
            if (entry.getJobId().equals(jobId)) {
                jobEntries.add(entry);
            }
        }
        return jobEntries;
    }

    @objid ("d05e581e-e7ab-423c-abad-e5170bc5b73d")
    private IAuditEntry getDiagnosticEntry(List<IAuditEntry> diagnosticEntries, IAuditEntry searchedEntry) {
        int index = diagnosticEntries.indexOf(searchedEntry);
        if (index >= 0) {
            return diagnosticEntries.get(index);
        }
        // else
        return null;
    }

    /**
     * Add audit entries in the audit diagnostic.
     * <p>
     * If the entry is already present the passed argument is not added. If the entry is 'success' removing the previosu one(s) from
     * the diagnostic
     * 
     * @param diagnosticEntries The entries to be added.
     * @param session a modeling session
     * @return <code>true</code> if the current entry list changed, and listeners must be notified.
     */
    @objid ("2fa99adc-9027-4112-9596-54654acc2679")
    public synchronized boolean postDiagnostic(List<IAuditEntry> diagnosticEntries, ICoreSession session) {
        boolean notifyListeners = false;
        
        Map<MObject, AuditSeverity> elementMap = new HashMap<>();
        
        // Analyze the existing entries
        for (int i = this.entries.size() - 1; i >= 0; i--) {
            IAuditEntry entry = this.entries.get(i);
            MObject element = entry.getElement();
            if (element == null || element.isDeleted()) {
                doRemoveEntry(i);
                notifyListeners = true;
            } else if (diagnosticEntries.contains(entry)) {
                IAuditEntry diagnosticEntry = getDiagnosticEntry(diagnosticEntries, entry);
                diagnosticEntries.remove(diagnosticEntry);
        
                if (diagnosticEntry.getSeverity() == AuditSeverity.AuditSuccess) {
                    doRemoveEntry(i);
                    elementMap.put(diagnosticEntry.getElement(), maxSeverity(elementMap.get(diagnosticEntry.getElement()), AuditSeverity.AuditSuccess));
                } else {
                    elementMap.put(diagnosticEntry.getElement(), maxSeverity(elementMap.get(diagnosticEntry.getElement()), diagnosticEntry.getSeverity()));
                    doReplaceEntry(diagnosticEntry, i);
                }
        
                notifyListeners = true;
            }
        }
        
        for (IAuditEntry diagnosticEntry : diagnosticEntries) {
            if (diagnosticEntry.getSeverity() != AuditSeverity.AuditSuccess) {
                doAddEntry(diagnosticEntry);
                notifyListeners = true;
                elementMap.put(diagnosticEntry.getElement(),
                        maxSeverity(elementMap.get(diagnosticEntry.getElement()), diagnosticEntry.getSeverity()));
            }
        }
        
        if (!diagnosticEntries.isEmpty()) {
            updateElementsStatus(elementMap);
        }
        
        // notify audit listeners
        if (notifyListeners) {
            fireAuditModelChanged();
        }
        return notifyListeners;
    }

    /**
     * Register an audit listener.
     * 
     * @param listener the listener to add.
     */
    @objid ("984297ab-baed-43d0-9100-e0b836f3204c")
    @Override
    public void addAuditListener(IAuditListener listener) {
        this.auditListeners.add(listener);
    }

    /**
     * Unregister an audit listener.
     * 
     * @param listener the listener to remove.
     */
    @objid ("9a81dff1-a01e-4b23-b6a6-524599b598fb")
    @Override
    public void removeAuditListener(IAuditListener listener) {
        this.auditListeners.remove(listener);
    }

    @objid ("815b1ffc-7ee0-4d73-b90e-4a150b47b046")
    @Override
    public int getErrorCount() {
        return this.nErrors;
    }

    @objid ("f6611c7d-4b45-4b21-ba05-f5a43c41c6a7")
    @Override
    public int getWarningCount() {
        return this.nWarnings;
    }

    @objid ("c65d9277-8b57-4f9a-8197-b859b4f42810")
    @Override
    public int getTipCount() {
        return this.nTips;
    }

    @objid ("0d3101b3-cadc-4c98-b550-4651375c10a1")
    private void doAddEntry(IAuditEntry entry) {
        this.entries.add(entry);
        switch (entry.getSeverity()) {
        case AuditError:
            this.nErrors++;
            break;
        case AuditWarning:
            this.nWarnings++;
            break;
        case AuditAdvice:
            this.nTips++;
            break;
        default:
            break;
        }
    }

    @objid ("9904e062-e885-43ba-be28-027572ab346d")
    private void doReplaceEntry(IAuditEntry entry, int index) {
        switch (this.entries.get(index).getSeverity()) {
        case AuditError:
            this.nErrors--;
            break;
        case AuditWarning:
            this.nWarnings--;
            break;
        case AuditAdvice:
            this.nTips--;
            break;
        default:
            break;
        }
        
        this.entries.set(index, entry);
        
        switch (entry.getSeverity()) {
        case AuditError:
            this.nErrors++;
            break;
        case AuditWarning:
            this.nWarnings++;
            break;
        case AuditAdvice:
            this.nTips++;
            break;
        default:
            break;
        }
    }

    @objid ("72e7cc63-00a6-460c-ad03-6806824fb9e8")
    private void doRemoveEntry(int index) {
        switch (this.entries.get(index).getSeverity()) {
        case AuditError:
            this.nErrors--;
            break;
        case AuditWarning:
            this.nWarnings--;
            break;
        case AuditAdvice:
            this.nTips--;
            break;
        default:
            break;
        }
        
        this.entries.remove(index);
    }

    /**
     * Purge current results from disabled or obsolete rules
     * 
     * @param configuredRules configuration rules.
     */
    @objid ("3bf3a592-6d40-4ce8-b35e-99d13a195d0a")
    public synchronized void auditPlanChanged(Map<String, IRule> configuredRules) {
        boolean notifyListeners = false;
        
        Map<MObject, AuditSeverity> elementMap = new HashMap<>();
        
        // Process the existing entries
        for (int i = this.entries.size() - 1; i >= 0; i--) {
            IAuditEntry entry = this.entries.get(i);
            if (!configuredRules.containsKey(entry.getRuleId())) {
                elementMap.put(entry.getElement(), AuditSeverity.AuditSuccess);
                doRemoveEntry(i);
                notifyListeners = true;
            } else {
                IAuditEntry diagnosticEntry = new AuditEntry(entry.getRuleId(), configuredRules.get(entry.getRuleId())
                        .getSeverity(), entry.getElement(), entry.getLinkedObjects());
        
                doReplaceEntry(diagnosticEntry, i);
                elementMap.put(diagnosticEntry.getElement(), diagnosticEntry.getSeverity());
        
                notifyListeners = true;
            }
        }
        
        updateElementsStatus(elementMap);
        
        if (notifyListeners) {
            fireAuditModelChanged();
        }
    }

    @objid ("5c873292-7f96-410a-ba2d-f3adbb6fe8ed")
    protected void fireAuditModelChanged() {
        // notify audit listeners
        for (IAuditListener listener : this.auditListeners) {
            listener.auditModelChanged(this);
        }
    }

    @objid ("c602bb98-7f92-428e-9ead-650e6fae24f5")
    public synchronized void clear() {
        // Reset counters
        this.nErrors = 0;
        this.nWarnings = 0;
        this.nTips = 0;
        
        Map<MObject, AuditSeverity> elementMap = new HashMap<>();
        
        for (int i = this.entries.size() - 1; i >= 0; i--) {
            IAuditEntry entry = this.entries.get(i);
            MObject element = entry.getElement();
            elementMap.put(element, AuditSeverity.AuditSuccess);
        
        }
        
        updateElementsStatus(elementMap);
        
        this.entries.clear();
        fireAuditModelChanged();
    }

    /**
     * Update the audit status flags on the audited elements.
     * 
     * @param auditStatusMap a map of elements with their audit status.
     */
    @objid ("83fdcdb9-5698-400b-97f9-a7040b9c142e")
    private void updateElementsStatus(Map<MObject, AuditSeverity> auditStatusMap) {
        for (Entry<MObject, AuditSeverity> entry : auditStatusMap.entrySet()) {
            final long on;
            final long off;
            switch (entry.getValue()) {
            case AuditSuccess:
                on = 0;
                off = IRStatus.AUDIT1 | IRStatus.AUDIT2;
                break;
            case AuditAdvice:
                on = IRStatus.AUDIT1;
                off = IRStatus.AUDIT2;
        
                break;
            case AuditWarning:
                on = IRStatus.AUDIT2;
                off = IRStatus.AUDIT1;
                break;
            case AuditError:
                on = IRStatus.AUDIT1 | IRStatus.AUDIT2;
                off = 0;
                break;
            default:
                throw new AssertionError(entry.getValue());
            }
        
            ((SmObjectImpl) entry.getKey()).setRStatus(on, off, 0);
        }
    }

    @objid ("aa63e1c3-fa14-4333-978f-bb602a46cf21")
    private AuditSeverity maxSeverity(AuditSeverity severity, AuditSeverity newSeverity) {
        if (severity == null) {
            return newSeverity;
        } else if (severity.equals(AuditSeverity.AuditError) || newSeverity.equals(AuditSeverity.AuditError)) {
            return AuditSeverity.AuditError;
        } else if (severity.equals(AuditSeverity.AuditWarning) || newSeverity.equals(AuditSeverity.AuditWarning)) {
            return AuditSeverity.AuditWarning;
        } else if (severity.equals(AuditSeverity.AuditAdvice) || newSeverity.equals(AuditSeverity.AuditAdvice)) {
            return AuditSeverity.AuditAdvice;
        }
        return AuditSeverity.AuditSuccess;
    }

    @objid ("d06d5867-524f-4615-9011-ede89d84caf2")
    public void purgeJob(String jobId) {
        String jid = jobId != null ? jobId : "";
        
        for (int index = this.entries.size() - 1; index >= 0; index--) {
            IAuditEntry entry = this.entries.get(index);
            if (entry.getJobId().equals(jid)) {
                doRemoveEntry(index);
            }
        }
    }

}
