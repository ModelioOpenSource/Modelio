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
package org.modelio.audit.view;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.modelio.audit.engine.core.AuditEntry;
import org.modelio.audit.engine.core.AuditRunnerStatus;
import org.modelio.audit.engine.core.IAuditDiagnostic;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.engine.core.IAuditListener;
import org.modelio.audit.engine.core.IAuditMonitor;
import org.modelio.audit.engine.impl.AuditDiagnostic;
import org.modelio.audit.extension.IAuditConfigurationPlan;
import org.modelio.audit.plugin.Audit;
import org.modelio.audit.service.IAuditService;
import org.modelio.audit.view.dialog.auditEntry.AuditEntryDialog;
import org.modelio.audit.view.model.AuditElementModel;
import org.modelio.audit.view.model.AuditRuleModel;
import org.modelio.audit.view.model.AuditTypeModel;
import org.modelio.audit.view.providers.AuditProviderFactory;
import org.modelio.audit.view.providers.AuditProviderFactory.AuditViewMode;
import org.modelio.audit.view.statusbar.StatusBar;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.platform.ui.panel.IPanelProvider;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("62d0667f-0a56-4df6-91b0-3bfb5195a2f8")
public class AuditPanelProvider implements IPanelProvider, IAuditListener, IAuditMonitor {
    @objid ("7fb2c34e-5b76-46ab-916e-27f53c94a904")
    volatile boolean redrawScheduled = false;

    @objid ("2d0198fb-6e14-4171-9f6b-ab2fcdfe711f")
    private MApplication application;

    @objid ("6da009ba-0c4c-4441-b07f-a6e469bdcc34")
    private EModelService emService;

    @objid ("b40e7069-684c-4b6c-8730-a71c4e307f28")
    private static final String ALL_JOBS = "";

    @objid ("c5ac634c-da10-416a-8825-3342500390f8")
    private Composite area;

    @objid ("1f9b28c8-2fc9-4087-90f2-72789e1a5ce0")
    TreeViewer auditTable = null;

    @objid ("a10bd229-2bc4-40ae-a060-d9811c12af3a")
    private List<TreeViewerColumn> columns;

    @objid ("0301313f-8345-4a1a-bab7-5eb284d70141")
    private String jobId;

    @objid ("fa78603f-c9fc-4e62-918b-b9493b9b4440")
    ICoreSession modelingSession;

    @objid ("cf28f2f1-cf87-4845-82ce-d9e22434eb59")
    private IMModelServices modelService;

    @objid ("4ed02965-590a-4f7d-b729-9b96161950ca")
    private AuditDiagnostic auditDiagnostic;

    @objid ("458d715e-45aa-4969-899a-c2eb271ff9af")
    StatusBar auditStatus;

    @objid ("9411252a-40e4-4cc5-bf5a-9caecdc79a7a")
    public IModelioNavigationService navigationService;

    /**
     * Provider factory: uses a late-initialization pattern. Only access this attribute using {@link #getProviderFactory()}
     */
    @objid ("42607bd7-1f45-42da-89e6-4ab8a2b7ccfa")
    private AuditProviderFactory providerFactory;

    @objid ("79d91f6c-6d12-4827-9176-0a0684101300")
    private IAuditService auditService;

    @objid ("5282ac7b-7d88-4a09-ab65-082fdc4ce984")
    private List<MObject> scope;

    @objid ("89485182-43f9-4265-9401-27972fff1177")
    public  AuditPanelProvider(IAuditService auditService, ICoreSession newModelingSession, IMModelServices newModelService, IModelioNavigationService newNavigationService, MApplication application, EModelService emService) {
        this.modelingSession = newModelingSession;
        this.modelService = newModelService;
        this.navigationService = newNavigationService;
        
        this.application = application;
        this.emService = emService;
        this.auditService = auditService;
        
        this.jobId = ALL_JOBS;
        this.columns = new ArrayList<>();
        
    }

    @objid ("ed7e239b-2e76-4505-a921-981c45e8d74f")
    @Override
    public Object createPanel(Composite parent) {
        this.area = new Composite(parent, SWT.NO_REDRAW_RESIZE);
        final FormLayout layout = new FormLayout();
        this.area.setLayout(layout);
        
        this.auditTable = createAuditTable(this.area);
        this.auditStatus = createAuditStatus(this.area);
        
        // attachments for audit table
        final FormData fd = new FormData();
        fd.left = new FormAttachment(0, 0);
        fd.right = new FormAttachment(100, 0);
        fd.top = new FormAttachment(0, 0);
        fd.bottom = new FormAttachment(this.auditStatus.getComposite(), 0);
        this.auditTable.getTree().setLayoutData(fd);
        
        // attachments for audit status
        final FormData fd2 = new FormData();
        fd2.left = new FormAttachment(0, 0);
        fd2.right = new FormAttachment(100, 0);
        fd2.bottom = new FormAttachment(100, 0);
        this.auditStatus.getComposite().setLayoutData(fd2);
        return this.area;
    }

    @objid ("50a30124-75dd-459e-a93e-82f2eff43e27")
    @Override
    public Composite getPanel() {
        return this.area;
    }

    @objid ("91dc8ffb-d3b0-4a62-9fa0-cfc5d8b76997")
    @Override
    public void dispose() {
        this.auditDiagnostic.removeAuditListener(this);
        this.auditService.removeAuditMonitor(this);
        this.modelingSession = null;
        
    }

    /**
     * This method is called when the diagnostic changes
     */
    @objid ("eba36d32-192b-466f-9c74-1eb4ef6b2f95")
    @Override
    public void auditModelChanged(final IAuditDiagnostic diagnostic) {
        refresh(diagnostic);
    }

    @objid ("fbf3de03-4c34-40aa-8306-1ad67b3c4420")
    public void setTitleImage(String path) {
        List<MPart> parts = this.emService.findElements(this.application, AuditView.VIEW_ID, MPart.class, new ArrayList<String>());
        for (MPart part : parts) {
            if (part.getObject() instanceof AuditView) {
                part.setIconURI("platform:/plugin/org.modelio.audit/" + path);
            }
        }
        
    }

    @objid ("5a8f4d61-4e70-4eb9-9980-deabba7f244c")
    public Object getSelectedDiagnosticEntry() {
        TreeSelection selection = (TreeSelection) this.auditTable.getSelection();
        return selection.getFirstElement();
    }

    @objid ("9111731f-af16-448e-971c-0e3c867670be")
    @Override
    public void setInput(Object input) {
        if (input instanceof IAuditDiagnostic) {
            // Unplug the previous diagnostic
            if (this.auditDiagnostic != null) {
                this.auditDiagnostic.removeAuditListener(this);
                this.auditService.removeAuditMonitor(this);
                this.auditDiagnostic = null;
            }
        
            // Plug in the new diagnostic
            this.auditDiagnostic = (AuditDiagnostic) input;
            this.auditDiagnostic.addAuditListener(this);
            this.auditService.addAuditMonitor(this);
            this.auditTable.setInput(this.auditDiagnostic);
        }
        
    }

    @objid ("042aadf4-92de-4340-90cd-0f75b70ffe94")
    @Override
    public Object getInput() {
        return this.auditDiagnostic;
    }

    @objid ("6656bb87-ca37-4f2c-a4a9-b6f8525d9536")
    public TreeViewer getTreeViewer() {
        return this.auditTable;
    }

    /**
     * This method is called when the audit runner status changes
     */
    @objid ("259e0471-abcb-4500-9f73-04c2feb8a2f8")
    @Override
    public void status(final AuditRunnerStatus status, final int queueSize) {
        refresh(status, queueSize);
    }

    @objid ("37131d50-a26d-4a7b-a4ba-0b03011181b2")
    public void refresh(final AuditRunnerStatus status, final int queueSize) {
        final TreeViewer lAuditTable = this.auditTable;
        
        if (!lAuditTable.getTree().isDisposed()) {
            lAuditTable.getTree().getDisplay().asyncExec(new Runnable() {
                @Override
                public void run() {
                    if (!lAuditTable.getTree().isDisposed()) {
                        AuditPanelProvider.this.auditStatus.doRefreshStatus(status, queueSize);
                    }
                }
            });
        }
        
    }

    @objid ("64014652-828c-4b75-b1c4-abf04a87ec97")
    public void refresh(final IAuditDiagnostic diagnostic) {
        if (!this.redrawScheduled) {
            final TreeViewer lAuditTable = this.auditTable;
            if (!lAuditTable.getTree().isDisposed()) {
                this.redrawScheduled = true;
                lAuditTable.getTree().getDisplay().asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        if (!lAuditTable.getTree().isDisposed()) {
                            lAuditTable.refresh();
                            AuditPanelProvider.this.auditStatus.doRefreshDiagnostic(diagnostic, AuditPanelProvider.this.getScopeLabel());
                        }
                        AuditPanelProvider.this.redrawScheduled = false;
                    }
                });
            }
        }
        
    }

    @objid ("c6420205-1e5b-433f-acba-5b5e488dafd3")
    private TreeViewer createAuditTable(Composite parent) {
        TreeViewer newTree = new TreeViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        newTree.getTree().setHeaderVisible(true);
        newTree.getTree().setLinesVisible(true);
        
        newTree.setContentProvider(getProviderFactory().getContentProvider());
        
        for (int i = 0; i < getProviderFactory().getColumns(); i++) {
            TreeViewerColumn column = createTreeViewerColumn(newTree, getProviderFactory().getColumnName(i),
                    getProviderFactory().getDefaultColumnSize(i));
            column.setLabelProvider(getProviderFactory().getLabelProvider(i));
            this.columns.add(column);
        }
        
        newTree.getTree().addMouseListener(new MouseListener() {
        
            @Override
            public void mouseUp(MouseEvent e) {
                // Nothing to do
            }
        
            @Override
            public void mouseDown(MouseEvent e) {
                // Nothing to do
            }
        
            @Override
            public void mouseDoubleClick(MouseEvent e) {
                TreeSelection selection = (TreeSelection) AuditPanelProvider.this.auditTable.getSelection();
                if (selection.getFirstElement() instanceof AuditEntry) {
                    final AuditEntryDialog dialog = new AuditEntryDialog(Display.getCurrent().getActiveShell(),
                            (AuditEntry) selection.getFirstElement(), AuditPanelProvider.this.modelingSession,
                            AuditPanelProvider.this.navigationService, AuditPanelProvider.this.auditService.getConfigurationModel().getAuditConfigurationPlan());
                    dialog.open();
                }
            }
        });
        return newTree;
    }

    @objid ("ce2da079-ce32-40ce-93ec-45ed4a4b6d12")
    private StatusBar createAuditStatus(Composite parent) {
        final StatusBar statusBar = new StatusBar(parent, SWT.NONE);
        return statusBar;
    }

    @objid ("0f3aef86-074c-4648-875f-8e86a3e643c8")
    private TreeViewerColumn createTreeViewerColumn(TreeViewer parent, String title, int bound) {
        final TreeViewerColumn column = new TreeViewerColumn(parent, SWT.DEFAULT);
        column.getColumn().setText(title);
        if (bound > 0) {
            column.getColumn().setWidth(bound);
        }
        column.getColumn().setResizable(true);
        column.getColumn().setMoveable(true);
        return column;
    }

    @objid ("1f6d423a-3386-4de2-a6c5-bb89a7901254")
    @Override
    public boolean isRelevantFor(Object obj) {
        return true;
    }

    @objid ("bb891903-9679-4937-8818-3e7c7232c53f")
    @Override
    public String getHelpTopic() {
        return Audit.I18N.getString("Audit.CheckerView.HelpId");
    }

    /**
     * Prints a text representation of the current diagnostic to 'out'
     */
    @objid ("e59a749b-30e4-4c22-a481-c801dd6c9a80")
    public void printDiagnostic(PrintStream out) {
        ITreeContentProvider cp = (ITreeContentProvider) getProviderFactory().getContentProvider();
        AuditEntryLineFormatter formatter = new AuditEntryLineFormatter(this.auditService.getConfigurationModel().getAuditConfigurationPlan());
        
        // Loop on top level elements returned by the current provider ensures the currently displayed structure and ordering.
        for (Object e : cp.getElements(this.getInput())) {
            Collection<IAuditEntry> entries;
        
            // Sadly there are no 'smart' accessors to get the entries ...
            if (e instanceof AuditTypeModel)
                entries = ((AuditTypeModel) e).entries;
            else if (e instanceof AuditElementModel)
                entries = ((AuditElementModel) e).entries;
            else if (e instanceof AuditRuleModel)
                entries = ((AuditRuleModel) e).entries;
            else if (e instanceof AuditEntry)
                entries = Arrays.asList((AuditEntry) e);
            else
                entries = Collections.EMPTY_LIST;
        
            // Produce output (text lines)
            for (IAuditEntry entry : entries) {
                out.println(formatter.getText(e, entry));
            }
        }
        
    }

    /*
         * Re-configure the table columns, content and label providers
         */
    @objid ("b9c7f5b7-4396-4ede-b74d-7fa2e190c814")
    private void reconfigure() {
        this.auditTable.setContentProvider(getProviderFactory().getContentProvider());
        while (this.auditTable.getTree().getColumnCount() > 0) {
            this.auditTable.getTree().getColumn(0).dispose();
        }
        
        for (int i = 0; i < getProviderFactory().getColumns(); i++) {
            TreeViewerColumn column = createTreeViewerColumn(this.auditTable, getProviderFactory().getColumnName(i),
                    getProviderFactory().getDefaultColumnSize(i));
            column.setLabelProvider(getProviderFactory().getLabelProvider(i));
        }
        
        refresh(this.auditDiagnostic);
        
        this.auditTable.refresh(true);
        
    }

    @objid ("61b574b0-1c0c-4e3c-bad8-13cd4d23da16")
    protected String getScopeLabel() {
        if (this.scope == null) {
            return "";
        } else if (this.scope.size() == 1) {
            return String.format(Audit.I18N.getMessage("Audit.StatusBar.OneSelectedElement", this.scope.get(0).getName()));
        } else {
            return String.format(Audit.I18N.getMessage("Audit.StatusBar.SelectedElements", this.scope.size()));
        }
        
    }

    /**
     * Set the scope of the displayed audit results.
     * @param scope the list of elements that are in the scope of the displayed audit. <null> value means no scope ie global audit contents are displayed.
     * @param jobId only contents matching 'jobId' are displayed. Should be null when scope is null.
     */
    @objid ("04ae5a57-fc19-4ef0-b24f-f3f7e35d9e71")
    public void setScope(List<MObject> scope, String jobId) {
        this.scope = scope;
        this.jobId = jobId;
        if (scope != null) {
            getProviderFactory().setJobId(jobId != null ? jobId : ALL_JOBS);
        } else {
            getProviderFactory().setJobId(ALL_JOBS);
        }
        
        reconfigure();
        
    }

    @objid ("5b6f27db-873e-4234-a37f-4138d858167f")
    public void setViewMode(AuditViewMode mode) {
        getProviderFactory().setViewMode(mode);
        reconfigure();
        
    }

    /**
     * Lazy accessor to provider factory.
     * @return the initialized provider factory
     */
    @objid ("3e3e017a-d2e7-443d-a2e9-0ab330d09ad9")
    private AuditProviderFactory getProviderFactory() {
        if (this.providerFactory == null) {
            this.providerFactory = new AuditProviderFactory(this.jobId, this.auditService.getConfigurationModel().getAuditConfigurationPlan());
        }
        return this.providerFactory;
    }

    @objid ("712f6a29-c48e-40f9-be0a-cdd1e7a9b44d")
    private static class AuditEntryLineFormatter {
        @objid ("e1a5f605-5437-4607-b922-cbb43af8a663")
        private final SimpleDateFormat dateFormatter = new SimpleDateFormat("H:mm:ss");

        @objid ("26d3e611-fa2e-4dd6-a2b2-67468cd300bc")
        private IAuditConfigurationPlan auditConfigurationPlan;

        @objid ("3ddb8c95-9b4a-434b-a9a0-62b9949f2902")
        public  AuditEntryLineFormatter(IAuditConfigurationPlan auditConfigurationPlan) {
            this.auditConfigurationPlan = auditConfigurationPlan;
        }

        @objid ("4a2ca583-a0ec-4faa-b178-8f53ffef8904")
        public String getText(Object auditModel, IAuditEntry entry) {
            String ruleIdString = entry.getRuleId();
            String severityString = entry.getSeverity().getLabel();
            String elementString = String.format("%-12s %-16s", entry.getElement().getMClass().getName(), "'" + entry.getElement().getName() + "'");
            String timeString = this.dateFormatter.format(entry.getTimestamp());
            
            String pattern = this.auditConfigurationPlan.getMessage(entry.getRuleId());
            String messageString = MessageFormat.format(pattern, makeInfos(entry.getLinkedObjects()));
            
            // Output line layout depends on displayed mode
            if (auditModel instanceof AuditTypeModel)
                return String.format("%-8s %s %-7s %s", severityString, elementString, ruleIdString, messageString);
            
            else if (auditModel instanceof AuditElementModel)
                return String.format("%s %-8s %-8s %-7s %s", elementString, timeString, severityString, ruleIdString, messageString);
            
            else if (auditModel instanceof AuditRuleModel)
                return String.format("%-7s %-8s %-8s %s %s", ruleIdString, severityString, timeString, elementString, messageString);
            
            else if (auditModel instanceof AuditEntry)
                return String.format("%-8s %-8s %-7s %s %s", timeString, severityString, ruleIdString, elementString, messageString);
            else
                return String.format("%s", entry.toString());
            
        }

        @objid ("c8028a47-d03f-4149-a1a4-43f126491d7c")
        private Object[] makeInfos(List<Object> linkedObjects) {
            List<Object> infos = new ArrayList<>();
            for (Object o : linkedObjects) {
            
                if (o instanceof MObject) {
                    MObject element = (MObject) o;
                    infos.add(element.getName());
                } else {
                    infos.add(o != null ? o.toString() : "<null>");
                }
            }
            return infos.toArray();
        }

    }

}
