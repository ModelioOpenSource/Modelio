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

package org.modelio.audit.view.dialog.auditEntry;

import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.help.IWorkbenchHelpSystem;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.extension.IAuditConfigurationPlan;
import org.modelio.audit.plugin.Audit;
import org.modelio.audit.view.model.DiagnosticFormatter;
import org.modelio.core.rcp.system.ModelioHelpSystem;
import org.modelio.core.ui.swt.labelprovider.UniversalLabelProvider;
import org.modelio.ui.UIImages;
import org.modelio.ui.dialog.ModelioDialog;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class implements the dialog box that display the audit entry details
 */
@objid ("ffcbbb53-d68f-4d4d-b868-1a12100e058e")
public class AuditEntryDialog extends ModelioDialog {
    @objid ("2646c25b-46bf-478d-8ab9-ea1a1ee3fa08")
    private static final String HREF_BASE = "org.modelio.documentation.vaudit/html/Modeler_audit_rules_";

    /**
     * The main composite of the dialog box
     */
    @objid ("1cd0e3bb-361c-40b8-9744-ea3efc8dbfe5")
    private Composite area = null;

    @objid ("469796b0-1de9-415b-90d0-9be78d01102e")
    private Browser browser;

    /**
     * The audit entry that is displayed.
     */
    @objid ("a2044026-6ad8-40f1-9675-df9e929fef23")
    private IAuditEntry entry = null;

    @objid ("a0895edb-2968-451c-8e03-9051f74b7c32")
    private IModelioNavigationService navigationService;

    @objid ("715a1820-8493-49c9-9508-1d024135563f")
    private URL ruleUrl;

    @objid ("76bf7442-cb69-4d57-b227-5b9c23c96c21")
    private IAuditConfigurationPlan auditConfigurationPlan;

    /**
     * Create an AuditEntryDialog instance.
     * 
     * @param parentShell The parent shell.
     * @param entry The audit entry that must be displayed.
     * @param modelingSession The modeling session.
     * @param navigationService the navigation service, to select elements in the model.
     */
    @objid ("ee8bda1c-0bd0-4a3d-ad46-1d04dbf089c2")
    public AuditEntryDialog(Shell parentShell, IAuditEntry entry, ICoreSession modelingSession, IModelioNavigationService navigationService, IAuditConfigurationPlan auditConfigurationPlan) {
        super(parentShell);
        this.entry = entry;
        this.navigationService = navigationService;
        this.auditConfigurationPlan = auditConfigurationPlan;
    }

    /**
     * Add buttons to the buttons bar in the bottom of the dialog.
     * <p>
     * Here we just need to have a "close" button.
     * 
     * @param parent the parent composite of the dialog.
     */
    @objid ("9e74a8b4-c643-493e-87c5-2e4dd72bfc17")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, Audit.I18N.getString("AuditEntryDialog.Close"), true);
    }

    /**
     * This is the main method that is called to construct the GUI content of the box.
     * 
     * @param parent the parent composite of the dialog.
     */
    @objid ("41cec314-ccf4-4b15-9587-3ad91fcec70f")
    @Override
    public Control createContentArea(Composite parent) {
        this.area = new Composite(parent, SWT.NONE);
        this.area.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        GridLayout layout = new GridLayout(1, false);
        this.area.setLayout(layout);
        
        Group descriptionGroup = createEntryDescription(this.entry);
        GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, false);
        descriptionGroup.setLayoutData(layoutData);
        
        Group linkedElementsGroup = createLinkedElementsList();
        layoutData = new GridData(SWT.FILL, SWT.FILL, true, false);
        linkedElementsGroup.setLayoutData(layoutData);
        
        Group ruleDocumentationGroup = createRuleDocumentation(this.entry.getRuleId());
        layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
        ruleDocumentationGroup.setLayoutData(layoutData);
        return this.area;
    }

    /**
     * Init is called when the dialog box is opened.
     */
    @objid ("9b78947d-5751-48d5-9a45-c9849d71b467")
    @Override
    public void init() {
        setLogoImage(null);
        // Put the messages in the banner area
        getShell().setText(Audit.I18N.getString("AuditEntryDialog.DialogTitle"));
        setTitle(Audit.I18N.getString("AuditEntryDialog.DialogTitle"));
        getShell().setSize(600, 700);
        getShell().setMinimumSize(600, 550);
    }

    /**
     * Create part of the dialog that display the tip message of the entry.
     * <p>
     * It is displayed in HTML format. The message given by the entry may contains HTML tags. There is no need to provide HTML headers they are provided by the method.
     */
    @objid ("4776c938-0268-41ae-84d3-1b7a8b8434b9")
    private Group createRuleDocumentation(String ruleId) {
        Group group = new Group(this.area, SWT.NONE);
        group.setText(ruleId);
        GridLayout gl = new GridLayout(1, false);
        gl.verticalSpacing = 0;
        group.setLayout(gl);
        
        this.browser = new Browser(group, SWT.BORDER);
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        this.browser.setLayoutData(gd);
        this.browser.setMenu(new Menu(this.browser));
        this.browser.setJavascriptEnabled(false);
        
        String href = AuditEntryDialog.HREF_BASE + ruleId + ".html";
        IWorkbenchHelpSystem hs = ModelioHelpSystem.getInstance();
        this.ruleUrl = hs.resolve(href, false);
        this.browser.setUrl(this.ruleUrl.toString());
        return group;
    }

    /**
     * Create part of the dialog that display the description of the rule that caused the audit report.
     * <p>
     * The displayed information are
     * <ul>
     * <li>Id of the plan that contains the rule.</li>
     * <li>Id of the rule.</li>
     * <li>Element concerned by the rule.</li>
     * <li>Description of the rule.</li>
     * </ul>
     */
    @objid ("ddd54766-8672-42bf-90f6-f059ea1197ae")
    private Group createEntryDescription(final IAuditEntry entryToDescribe) {
        Group descriptionGroup = new Group(this.area, SWT.NONE);
        descriptionGroup.setText(Audit.I18N.getString("AuditEntryDialog.DescriptionGroup.Label"));
        
        GridLayout layout = new GridLayout(2, false);
        descriptionGroup.setLayout(layout);
        
        // Audit message
        Label auditMessage = new Label(descriptionGroup, SWT.WRAP | SWT.READ_ONLY);
        String message = DiagnosticFormatter.getMessage(entryToDescribe, new UniversalLabelProvider(), this.auditConfigurationPlan);
        auditMessage.setText(message);
        auditMessage.setCapture(false);
        
        // Navigation button
        Button navigationButton = new Button(descriptionGroup, SWT.NONE);
        navigationButton.setImage(UIImages.SELECTINBROWSER);
        
        navigationButton.setToolTipText(Audit.I18N.getString("AuditEntryDialog.ElementLabel.ClickToNavigate"));
        navigationButton.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                AuditEntryDialog.this.navigationService.fireNavigate(entryToDescribe.getElement());
            }
        
            @Override
            public void widgetSelected(SelectionEvent e) {
                AuditEntryDialog.this.navigationService.fireNavigate(entryToDescribe.getElement());
            }
        });
        // set attachments
        GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
        auditMessage.setLayoutData(layoutData);
        
        layoutData = new GridData(SWT.FILL, SWT.FILL, false, true);
        navigationButton.setLayoutData(layoutData);
        return descriptionGroup;
    }

    /**
     * Create the list that contains the elements linked to the audit report.
     * <p>
     * It contains the elements described in the reported error message.
     */
    @objid ("3ef06d0c-6aa2-4712-9505-df0739ef7aa0")
    private Group createLinkedElementsList() {
        Group group = new Group(this.area, SWT.NONE);
        group.setText(Audit.I18N.getString("AuditEntryDialog.LinkedElements.Label"));
        
        group.setLayout(new GridLayout(1, false));
        
        TableViewer linkedElementsList = new TableViewer(group, SWT.BORDER);
        linkedElementsList.setContentProvider(new LinkedElementContentProvider());
        linkedElementsList.setLabelProvider(new LinkedElementLabelProvider());
        linkedElementsList.setInput(this.entry);
        linkedElementsList.getControl().setToolTipText(Audit.I18N.getString("AuditEntryDialog.LinkedElements.Tooltip"));
        
        GridData gd_linkedElementsList = new GridData(SWT.FILL, SWT.FILL, true, true);
        linkedElementsList.getTable().setLayoutData(gd_linkedElementsList);
        linkedElementsList.addSelectionChangedListener(new ISelectionChangedListener() {
        
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                ISelection selection = event.getSelection();
                if (selection instanceof IStructuredSelection) {
                    IStructuredSelection structuredSelection = (IStructuredSelection) selection;
                    if (structuredSelection.size() == 1) {
                        Object data = structuredSelection.getFirstElement();
                        if (data instanceof MObject) {
                            MObject element = (MObject) data;
                            AuditEntryDialog.this.navigationService.fireNavigate(element);
                        }
                    }
                }
        
            }
        });
        return group;
    }

}
