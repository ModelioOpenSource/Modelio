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
package org.modelio.audit.engine.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;
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
    /**
     * The current diagnostic state
     * <p>
     * <h2>Thread safety rules</h2>
     * <ul>
     * <li>this member must be read once per method
     * <li>once completely built this state must not be modified anymore
     * <li>Instead of modify the existing state, rebuild a new state then swap the current and the new state.
     * <li>The methods that swap state must be "synchronized" to avoid concurrent modifications.
     * </ul>
     */
    @objid ("d60d76e8-c3fd-4bb9-8d5f-422000a2bb0d")
    private volatile State state;

    @objid ("93fde6ab-1a7f-4e96-980d-0014c1f24c79")
    private final List<IAuditListener> auditListeners = new CopyOnWriteArrayList<>();

    /**
     * Default constructor.
     */
    @objid ("4dc825d2-d00b-49c3-952d-f14df049dc42")
    public  AuditDiagnostic() {
        this.state = new State(100);
    }

    @objid ("189bd0a7-afcf-4500-8a06-1f6854f47558")
    @Override
    public Collection<IAuditEntry> getEntries() {
        return this.state.entries;
    }

    @objid ("308e961f-d900-49fc-aa6a-5ddf506fb612")
    @Override
    public Collection<IAuditEntry> getEntries(String jobId) {
        if (jobId.isEmpty()) {
            return getEntries();
        }
        
        List<IAuditEntry> jobEntries = new ArrayList<>();
        State curState = this.state;
        for (IAuditEntry entry : curState.entries) {
            if (entry.getJobId().equals(jobId)) {
                jobEntries.add(entry);
            }
        }
        return jobEntries;
    }

    @objid ("12fe4b54-e788-4c05-b6e4-62eb27ef45d1")
    @Override
    public Stream<IAuditEntry> streamEntries(String jobId) {
        if (jobId==null || jobId.isEmpty()) {
            return getEntries().stream();
        }
        return getEntries().stream().filter(en -> jobId.equals(en.getJobId()));
    }

    /**
     * Find and remove the diagnostic with same element and rule from the given list.
     * @param diagnosticEntries a list of diagnostic entries
     * @param searchedEntry the entry to search for same element+rule
     * @return the found and removed entry or null.
     */
    @objid ("d05e581e-e7ab-423c-abad-e5170bc5b73d")
    private static IAuditEntry pollDiagnosticEntry(List<IAuditEntry> diagnosticEntries, IAuditEntry searchedEntry) {
        int index = diagnosticEntries.indexOf(searchedEntry);
        if (index >= 0) {
            IAuditEntry ret = diagnosticEntries.remove(index);
            return ret;
        }
        // else
        return null;
    }

    /**
     * Add audit entries in the audit diagnostic.
     * <p>
     * For each entry:<ul>
     * <li>If the entry is already present the passed argument is not added. <br>
     * <li>If the new entry is 'success' remove the previous one(s) from the diagnostic.
     * </ul>
     * @param postedDiagnosticEntries The entries to be added.
     * @param session a modeling session
     * @return <code>true</code> if the current entry list changed, and listeners must be notified.
     */
    @objid ("2fa99adc-9027-4112-9596-54654acc2679")
    public synchronized boolean postDiagnostic(Collection<IAuditEntry> postedDiagnosticEntries, ICoreSession session) {
        boolean changed = false;
        
        // read existing state once
        State curState = this.state;
        
        Map<MObject, AuditSeverity> elementMap = new HashMap<>();
        State newState = new State(postedDiagnosticEntries.size() + curState.entries.size());
        List<IAuditEntry> remainingEntries = new ArrayList<>(postedDiagnosticEntries);
        
        // Analyze the existing entries
        for (IAuditEntry existingEntry : curState.entries) {
            IAuditEntry foundPostedEntry;
            MObject element = existingEntry.getElement();
            if (element == null || element.isDeleted()) {
                // obsolete entry: don't add to new state
                changed = true;
            } else if ((foundPostedEntry = pollDiagnosticEntry(remainingEntries, existingEntry)) != null) {
                // modified entry
                elementMap.merge(foundPostedEntry.getElement(), foundPostedEntry.getSeverity(), AuditDiagnostic::maxSeverity);
        
                if (foundPostedEntry.getSeverity() != AuditSeverity.AuditSuccess) {
                    newState.addEntry(foundPostedEntry);
                }
        
                changed = true;
            } else {
                // unchanged entry, add it to new state as is.
                newState.addEntry(existingEntry);
            }
        }
        
        for (IAuditEntry diagnosticEntry : remainingEntries) {
            if (diagnosticEntry.getSeverity() != AuditSeverity.AuditSuccess) {
                newState.addEntry(diagnosticEntry);
                changed = true;
                elementMap.merge(diagnosticEntry.getElement(), diagnosticEntry.getSeverity(), AuditDiagnostic::maxSeverity);
            }
        }
        
        
        
        // notify audit listeners
        if (changed) {
            // Swap current and new state
            this.state = newState;
        
            updateElementsStatus(elementMap);
            fireAuditModelChanged();
        }
        return changed;
    }

    /**
     * Register an audit listener.
     * @param listener the listener to add.
     */
    @objid ("984297ab-baed-43d0-9100-e0b836f3204c")
    @Override
    public void addAuditListener(IAuditListener listener) {
        this.auditListeners.add(listener);
    }

    /**
     * Unregister an audit listener.
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
        return this.state.nErrors;
    }

    @objid ("f6611c7d-4b45-4b21-ba05-f5a43c41c6a7")
    @Override
    public int getWarningCount() {
        return this.state.nWarnings;
    }

    @objid ("c65d9277-8b57-4f9a-8197-b859b4f42810")
    @Override
    public int getTipCount() {
        return this.state.nTips;
    }

    /**
     * Purge current results from disabled or obsolete rules
     * @param configuredRules configuration rules.
     */
    @objid ("3bf3a592-6d40-4ce8-b35e-99d13a195d0a")
    public synchronized void auditPlanChanged(Map<String, IRule> configuredRules) {
        boolean notifyListeners = false;
        
        Map<MObject, AuditSeverity> elementMap = new HashMap<>();
        final State curState = this.state;
        State newState = new State(curState.entries.size());
        
        // Process the existing entries
        for (IAuditEntry entry : curState.entries) {
            IRule rule = configuredRules.get(entry.getRuleId());
            if (rule == null) {
                elementMap.putIfAbsent(entry.getElement(), AuditSeverity.AuditSuccess);
                notifyListeners = true;
            } else if (rule.getSeverity() != entry.getSeverity()){
                // Severity changed, make modified copy
                IAuditEntry diagnosticEntry = new AuditEntry(
                        entry.getRuleId(),
                        rule.getSeverity(),
                        entry.getElement(),
                        entry.getLinkedObjects());
        
                newState.addEntry(diagnosticEntry);
        
                elementMap.put(diagnosticEntry.getElement(), diagnosticEntry.getSeverity());
        
                notifyListeners = true;
            } else {
                // no change, copy the entry directly
                newState.addEntry(entry);
            }
        }
        
        if (notifyListeners) {
            this.state = newState;
        
            updateElementsStatus(elementMap);
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
        final State curState = this.state;
        final Map<MObject, AuditSeverity> elementMap = new HashMap<>(curState.entries.size());
        
        for (IAuditEntry entry : curState.entries) {
            MObject element = entry.getElement();
            elementMap.put(element, AuditSeverity.AuditSuccess);
        }
        
        this.state = new State(100);
        
        updateElementsStatus(elementMap);
        fireAuditModelChanged();
        
    }

    /**
     * Update the audit status flags on the audited elements.
     * @param auditStatusMap a map of elements with their audit status.
     */
    @objid ("83fdcdb9-5698-400b-97f9-a7040b9c142e")
    private static void updateElementsStatus(Map<MObject, AuditSeverity> auditStatusMap) {
        if (auditStatusMap.isEmpty())
            return;
        
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
    private static AuditSeverity maxSeverity(AuditSeverity severity, AuditSeverity newSeverity) {
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
    public synchronized void purgeJob(String jobId) {
        String jid = jobId != null ? jobId : "";
        
        final State curState = this.state;
        State newState = new State(curState.entries.size());
        
        for (IAuditEntry entry : curState.entries) {
            if (! entry.getJobId().equals(jid)) {
                newState.addEntry(entry);
            }
        }
        
        this.state = newState;
        
    }

    /**
     * The current diagnostic state
     * <p>
     * <h2>Thread safety rules</h2>
     * <ul>
     * <li>this member must be read once per method
     * <li>once completely built this state must not be modified anymore
     * <li>Instead of modify the existing state, rebuild a new state then swap the current and the new state.
     * <li>The methods that swap state must be "synchronized" to avoid concurrent modifications.
     * </ul>
     */
    @objid ("d640274d-04dc-49d1-af7f-790ded5b3fb8")
    private static class State {
        @objid ("4dc1dd97-37d0-4dfd-bc4f-e6dd0c82fcdb")
        int nErrors;

        @objid ("ce459afc-e563-4a7f-b119-00027d0328ae")
        int nWarnings;

        @objid ("af29eec9-837c-4af5-92bb-01a7fc000d4b")
        int nTips;

        @objid ("131a6d56-f00f-49f1-94d8-1cb8b0ed6e0d")
        List<IAuditEntry> entries;

        @objid ("d965dbb8-360c-4bc8-88b9-f67007846e98")
        public  State(int capacity) {
            this.entries = new ArrayList<>(capacity);
        }

        @objid ("0d3101b3-cadc-4c98-b550-4651375c10a1")
        public void addEntry(IAuditEntry entry) {
            this.entries.add(entry);
            addToStats(entry);
            
        }

        @objid ("84d67780-c57a-4a76-bdca-5e6bed66418b")
        private void addToStats(IAuditEntry entry) {
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
            case AuditSuccess:
                break;
            }
            
        }

    }

}
