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

package org.modelio.audit.view;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.audit.engine.core.AuditEntry;
import org.modelio.audit.engine.core.AuditRunnerStatus;
import org.modelio.audit.engine.core.IAuditDiagnostic;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.engine.core.IAuditListener;
import org.modelio.audit.engine.core.IAuditMonitor;
import org.modelio.audit.engine.impl.AuditDiagnostic;
import org.modelio.audit.service.IAuditService;
import org.modelio.audit.view.dialog.auditEntry.AuditEntryDialog;
import org.modelio.audit.view.model.AuditElementModel;
import org.modelio.audit.view.providers.AuditProviderFactory.AuditViewMode;
import org.modelio.audit.view.providers.AuditProviderFactory;
import org.modelio.audit.view.statusbar.StatusBar;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.vcore.session.api.ICoreSession;

@objid ("62d0667f-0a56-4df6-91b0-3bfb5195a2f8")
public class AuditPanelProvider implements IPanelProvider, IAuditListener, IAuditMonitor {
    @objid ("ce77b1a5-a0e2-432d-9a2c-01b10536e591")
    public boolean autoSelectInExplorer;

    @objid ("7fb2c34e-5b76-46ab-916e-27f53c94a904")
     volatile boolean redrawScheduled = false;

    @objid ("2d0198fb-6e14-4171-9f6b-ab2fcdfe711f")
    private MApplication application;

    @objid ("6da009ba-0c4c-4441-b07f-a6e469bdcc34")
    private EModelService emService;

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

    @objid ("42607bd7-1f45-42da-89e6-4ab8a2b7ccfa")
    private AuditProviderFactory providerFactory;

    @objid ("79d91f6c-6d12-4827-9176-0a0684101300")
    private IAuditService auditService;

    @objid ("604a9c84-52c1-433f-b8c2-01edd3eb57f3")
    private Composite area;

    @objid ("ee199f72-316d-492e-be41-9de5ae91bb7d")
     TreeViewer auditTable = null;

    @objid ("2be0a7b8-d1c3-4ac0-950a-0adace72a52b")
    private List<TreeViewerColumn> columns;

    @objid ("89485182-43f9-4265-9401-27972fff1177")
    public AuditPanelProvider(IAuditService auditService, ICoreSession newModelingSession, IMModelServices newModelService, IModelioNavigationService newNavigationService, MApplication application, EModelService emService, String jobId) {
        this.modelingSession = newModelingSession;
        this.modelService = newModelService;
        this.navigationService = newNavigationService;
        this.autoSelectInExplorer = true;
        this.application = application;
        this.emService = emService;
        this.auditService = auditService;
        this.providerFactory = new AuditProviderFactory(jobId, auditService.getConfigurationModel().getAuditConfigurationPlan());
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

    @objid ("413d25a2-249a-4c1d-ae11-886e01461a35")
    public boolean isAutoSelectInExplorer() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.autoSelectInExplorer;
    }

    @objid ("5703c551-425e-4241-8da9-066cfa817cfa")
    public void setAutoSelectInExplorer(boolean value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.autoSelectInExplorer = value;
    }

    @objid ("9111731f-af16-448e-971c-0e3c867670be")
    @Override
    public void setInput(Object input) {
        if (input instanceof IAuditDiagnostic) {
            if (this.auditDiagnostic != null) {
                this.auditDiagnostic.removeAuditListener(this);
                this.auditService.removeAuditMonitor(this);
            }
        
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

    @objid ("67495b1f-5530-412b-a0fe-db389fc78ebb")
    public void setAuditViewMode(AuditViewMode mode) {
        this.providerFactory.setViewMode(mode);
        this.auditTable.setContentProvider(this.providerFactory.getContentProvider());
        
        while (this.auditTable.getTree().getColumnCount() > 0) {
            this.auditTable.getTree().getColumn(0).dispose();
        }
        
        for (int i = 0; i < this.providerFactory.getColumns(); i++) {
            TreeViewerColumn column = createTreeViewerColumn(this.auditTable, this.providerFactory.getColumnName(i),
                    this.providerFactory.getDefaultColumnSize(i));
            column.setLabelProvider(this.providerFactory.getLabelProvider(i));
        }
        
        this.auditTable.refresh(true);
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
                            AuditPanelProvider.this.auditStatus.doRefreshDiagnostic(diagnostic);
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
        
        newTree.setContentProvider(this.providerFactory.getContentProvider());
        
        for (int i = 0; i < this.providerFactory.getColumns(); i++) {
            TreeViewerColumn column = createTreeViewerColumn(newTree, this.providerFactory.getColumnName(i),
                    this.providerFactory.getDefaultColumnSize(i));
            column.setLabelProvider(this.providerFactory.getLabelProvider(i));
            this.columns.add(column);
        }
        
        newTree.getTree().addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (isAutoSelectInExplorer()) {
                    TreeSelection selection = (TreeSelection) AuditPanelProvider.this.auditTable.getSelection();
                    if (selection.getFirstElement() instanceof AuditEntry) {
                        final IAuditEntry entry = (AuditEntry) selection.getFirstElement();
                        if (entry.getElement() != null) {
                            AuditPanelProvider.this.navigationService.fireNavigate(entry.getElement());
                        }
                    } else if (selection.getFirstElement() instanceof AuditElementModel) {
                        final AuditElementModel entry = (AuditElementModel) selection.getFirstElement();
                        if (entry.element != null) {
                            AuditPanelProvider.this.navigationService.fireNavigate(entry.element);
                        }
                    }
                }
            }
        
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // Nothing to do
            }
        });
        
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
        return null;
    }

}
