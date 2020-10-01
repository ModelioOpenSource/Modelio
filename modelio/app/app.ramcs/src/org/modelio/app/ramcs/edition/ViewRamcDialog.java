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

package org.modelio.app.ramcs.edition;

import java.nio.file.Files;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.modelio.api.module.IModule;
import org.modelio.app.ramcs.plugin.AppRamcs;
import org.modelio.gproject.ramc.core.model.ModelComponent;
import org.modelio.gproject.ramc.core.packaging.IModelComponentContributor.ExportedFileEntry;
import org.modelio.platform.model.ui.swt.images.BasicModelElementLabelProvider;
import org.modelio.platform.ui.UIColor;
import org.modelio.platform.ui.UIImages;
import org.modelio.platform.ui.dialog.ModelioDialog;
import org.modelio.platform.ui.htmleditor.HtmlComposer;
import org.modelio.vbasic.version.Version;

/**
 * Dialog box to view ramc properties.
 */
@objid ("74916f47-4dbf-4126-9b01-87906a5e5452")
public class ViewRamcDialog extends ModelioDialog {
    @objid ("3193f51f-8ae5-4ca3-ae4c-65bc5de24676")
    protected RamcModel dataModel = null;

    @objid ("f04b9a60-a377-4773-9ff4-04b4a1bf5637")
    protected Text ramcPathText = null;

    @objid ("3e06e552-7159-46da-aa0f-181ceec9bfa6")
    protected Text ramcNameText = null;

    @objid ("4b164719-c4e8-46bd-bb2d-25e5b2df8fef")
    protected Text ramcVersionText = null;

    @objid ("5f838844-20bc-4cbd-b17a-8073582447ef")
    protected HtmlComposer ramcDescriptionText = null;

    @objid ("0a914c0f-aafc-4213-a457-457eaaa7d2b4")
    protected TableViewer dependenciesTable = null;

    @objid ("1f322a19-9ec6-48fb-8d77-179fac51ff10")
    protected TableViewer manifestationsTable = null;

    @objid ("d341da93-e5d1-4bce-b631-01f2262b6b86")
    protected TableViewer ramcFilesList = null;

    @objid ("a1ee571d-95ae-4408-8f15-06f13f40e5da")
    protected Button ramcPathButton;

    @objid ("3fc619d9-6f14-451a-94c8-741187b78630")
    protected Button addFilesButton;

    @objid ("cbd1de4c-2052-4753-ac4e-d6eb4d182a7d")
    protected Button removeFilesButton;

    @objid ("9d1fc12c-9df3-4f43-8f28-71069346c510")
    protected CheckboxTableViewer contributorsTable;

    @objid ("69d717d1-392f-401d-823b-9a26b69976df")
    protected Text ramcProviderText = null;

    @objid ("0a4a1e08-2f53-488e-8840-e9e2aff1795d")
    protected Button relativizeFilesButton;

    @objid ("2095437a-2bc9-422c-b1fc-b21961dd17f5")
    protected Button resolveFilesButton;

    /**
     * C'tor.
     * 
     * @param parentShell the parent shell, or <code>null</code> to create a top-level shell.
     * @param dataModel the data model of the ramc to be viewed.
     */
    @objid ("540085eb-beef-42b5-8535-e210066e2960")
    public ViewRamcDialog(Shell parentShell, RamcModel dataModel) {
        super(parentShell);
        this.dataModel = dataModel;
        setShellStyle(SWT.MODELESS | SWT.DIALOG_TRIM | SWT.RESIZE);
    }

    @objid ("d7b6c18a-196f-48bf-96cf-e27e18acdb1b")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.CANCEL_ID, AppRamcs.I18N.getString("EditRamcDialog.Close"), false);
    }

    @objid ("b4b7f6f5-d624-4bf8-a2d3-36f503e5d08f")
    @Override
    public Control createContentArea(Composite parent) {
        Composite area = new Composite(parent, SWT.NONE);
        area.setLayoutData(new GridData(GridData.FILL_BOTH));
        area.setLayout(new GridLayout());
        
        TabFolder tabFolder = new TabFolder(area, SWT.NONE);
        tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        TabItem mainTab = new TabItem(tabFolder, SWT.NONE);
        mainTab.setText(AppRamcs.I18N.getString("EditRamcDialog.MainTab"));
        TabItem filesTab = new TabItem(tabFolder, SWT.NONE);
        filesTab.setText(AppRamcs.I18N.getString("EditRamcDialog.FilesTab"));
        
        Composite mainArea = new Composite(tabFolder, SWT.NONE);
        mainTab.setControl(mainArea);
        mainArea.setLayout(new GridLayout(3, false));
        createRamcNameField(mainArea);
        createRamcVersionField(mainArea);
        createDescriptionField(mainArea);
        createProviderField(mainArea);
        createManifestationsField(mainArea);
        createDependenciesField(mainArea);
        createContributorsField(mainArea);
        
        Composite filesArea = new Composite(tabFolder, SWT.NONE);
        filesTab.setControl(filesArea);
        filesArea.setLayout(new GridLayout(2, false));
        createFilesField(filesArea);
        refresh();
        return area;
    }

    @objid ("8d2475d1-bfa3-4049-8498-345b7b9ef33c")
    @Override
    public void init() {
        setLogoImage(null);
        
        // Put the messages in the banner area
        setTitle(AppRamcs.I18N.getString("EditRamcDialog.ViewRamcDialogTitle"));
        setMessage(AppRamcs.I18N.getString("EditRamcDialog.ViewRamcMessage"));
        
        this.ramcDescriptionText.setEditable(false);
        this.ramcDescriptionText.setEnabled(false);
    }

    @objid ("e4886bcd-fa2d-47f8-a0d4-86061afe19b8")
    protected boolean close(int code) {
        setReturnCode(code);
        return this.close();
    }

    @objid ("1b042a95-fc1a-4397-b485-6e345b9ce553")
    private void createDependenciesField(Composite area) {
        // Label
        Label ramcDependenciesLabel = new Label(area, SWT.NONE);
        ramcDependenciesLabel.setText(AppRamcs.I18N.getString("EditRamcDialog.RamcDependencies.label"));
        ramcDependenciesLabel.setLayoutData(new GridData(SWT.LEFT, SWT.UP, false, false, 1, 1));
        
        // Table viewer
        this.dependenciesTable = new TableViewer(area, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
        final GridData gdTable = new GridData(SWT.FILL, SWT.FILL, true, true);
        gdTable.heightHint = 50;
        this.dependenciesTable.getTable().setLayoutData(gdTable);
        this.dependenciesTable.getTable().setToolTipText(AppRamcs.I18N.getString("EditRamcDialog.RamcDependencies.tooltip"));
        this.dependenciesTable.getTable().setForeground(UIColor.EDITOR_ROTEXT_FG);
        this.dependenciesTable.getTable().setBackground(UIColor.TEXT_READONLY_BG);
        
        // Droparea indicator
        Label targetLabel = new Label(area, SWT.NONE);
        targetLabel.setImage(UIImages.DROPAREA);
        final GridData gdTarget = new GridData(SWT.LEFT, SWT.UP, false, false, 1, 1);
        gdTarget.heightHint = 21;
        gdTarget.widthHint = 21;
        targetLabel.setLayoutData(gdTarget);
        
        // Branch providers
        this.dependenciesTable.setContentProvider(new ArrayContentProvider());
        this.dependenciesTable.setLabelProvider(new BasicModelElementLabelProvider() {
            @Override
            public Image getImage(Object obj) {
                final ModelComponent model = (ModelComponent) obj;
                return super.getImage(model.getArtifact());
            }
        
            @Override
            public String getText(Object obj) {
                final ModelComponent model = (ModelComponent) obj;
                return model.getName() + model.getVersion().toString(" V.R.C");
            }
        });
    }

    @objid ("0eeafa47-4cec-4c48-ab86-d2f2b5bd0d7e")
    private void createDescriptionField(Composite area) {
        // Label
        Label ramcDescriptionLabel = new Label(area, SWT.NONE);
        ramcDescriptionLabel.setText(AppRamcs.I18N.getString("EditRamcDialog.RamcDescription.label"));
        final GridData descriptionGD = new GridData(SWT.LEFT, SWT.UP, false, false);
        descriptionGD.heightHint = 200;
        ramcDescriptionLabel.setLayoutData(descriptionGD);
        
        // Text
        this.ramcDescriptionText = new HtmlComposer(area, SWT.BORDER | SWT.MULTI);
        this.ramcDescriptionText.setEditable(false);
        // this.ramcDescriptionText.setToolTipText(AppRamcs.I18N.getString("EditRamcDialog.RamcDescription.tooltip"));
        
        this.ramcDescriptionText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        this.ramcDescriptionText.setForeground(UIColor.EDITOR_ROTEXT_FG);
        this.ramcDescriptionText.setBackground(UIColor.TEXT_READONLY_BG);
        
        Composite emptyComposite = new Composite(area, SWT.NONE);
        final GridData gd = new GridData(SWT.LEFT, SWT.UP, false, false, 1, 1);
        gd.heightHint = 21;
        gd.widthHint = 21;
        emptyComposite.setLayoutData(gd);
    }

    @objid ("68c34ec8-b347-4330-a9e2-1535ae4cbdec")
    private void createRamcVersionField(Composite area) {
        // Label
        Label ramcVersionLabel = new Label(area, SWT.NONE);
        ramcVersionLabel.setText(AppRamcs.I18N.getString("EditRamcDialog.RamcVersion.label"));
        ramcVersionLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        
        // Text
        this.ramcVersionText = new Text(area, SWT.BORDER);
        this.ramcVersionText.setEditable(false);
        this.ramcVersionText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        this.ramcVersionText.setToolTipText(AppRamcs.I18N.getString("EditRamcDialog.RamcVersion.tooltip"));
        
        this.ramcVersionText.setForeground(UIColor.EDITOR_ROTEXT_FG);
        this.ramcVersionText.setBackground(UIColor.TEXT_READONLY_BG);
        
        Composite emptyComposite = new Composite(area, SWT.NONE);
        final GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd.heightHint = 21;
        gd.widthHint = 21;
        emptyComposite.setLayoutData(gd);
    }

    @objid ("76f0a7dd-8197-44a3-814c-d6e6964dbe8c")
    private void createRamcNameField(Composite area) {
        // Label
        Label ramcNameLabel = new Label(area, SWT.NONE);
        ramcNameLabel.setText(AppRamcs.I18N.getString("EditRamcDialog.RamcName.label"));
        ramcNameLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        
        // Text
        this.ramcNameText = new Text(area, SWT.BORDER);
        this.ramcNameText.setEditable(false);
        this.ramcNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        this.ramcNameText.setToolTipText(AppRamcs.I18N.getString("EditRamcDialog.RamcName.tooltip"));
        this.ramcNameText.setForeground(UIColor.EDITOR_ROTEXT_FG);
        this.ramcNameText.setBackground(UIColor.TEXT_READONLY_BG);
        
        Composite emptyComposite = new Composite(area, SWT.NONE);
        final GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd.heightHint = 21;
        gd.widthHint = 21;
        emptyComposite.setLayoutData(gd);
    }

    @objid ("d498e17f-0729-4124-b739-b94eac03beca")
    protected void refresh() {
        this.ramcNameText.setText(this.dataModel.getName());
        Version ramcVersion = this.dataModel.getVersion();
        if (ramcVersion == null) {
            this.ramcVersionText.setText("");
        } else {
            String buildVersion = String.format("%02d", ramcVersion.getBuildVersion());
            this.ramcVersionText.setText(ramcVersion.getMajorVersion() + "." + ramcVersion.getMinorVersion() + "." + buildVersion);
        }
        this.ramcDescriptionText.setHtml(this.dataModel.getDescription());
        this.ramcProviderText.setText(this.dataModel.getProvider());
        this.dependenciesTable.setInput(this.dataModel.getRequiredModelComponents());
        this.manifestationsTable.setInput(this.dataModel.getExportedElements());
        this.ramcFilesList.setInput(this.dataModel.getExportedFiles());
        this.contributorsTable.setInput(this.dataModel.getContributorCandidates());
    }

    @objid ("9e5730f4-2768-473d-8a15-a0737a481d78")
    protected Version getVersion() {
        return new Version(this.ramcVersionText.getText());
    }

    @objid ("9deb24f5-c423-4df8-b275-0db953eaf6c6")
    protected String getDescription() {
        return this.ramcDescriptionText.getHtml();
    }

    @objid ("f5ccd16e-a5aa-4efd-a8af-22bb18d5ede3")
    protected String getRamcPath() {
        return this.ramcPathText.getText();
    }

    @objid ("b90e13f3-7153-4aab-bc61-b6b69ce11df0")
    protected void invalidateRamcVersion(@SuppressWarnings("unused") boolean invalid) {
        this.ramcVersionText.setForeground(UIColor.EDITOR_ROTEXT_FG);
    }

    @objid ("54dcea16-2e2d-4ab6-8503-c411bec88234")
    protected void invalidateRamcPath(@SuppressWarnings("unused") boolean invalid) {
        this.ramcPathText.setForeground(UIColor.EDITOR_ROTEXT_FG);
    }

    @objid ("e73a05e7-4ad2-4197-a6cf-7258439d4757")
    protected String getRamcName() {
        return this.ramcNameText.getText();
    }

    @objid ("64e6fe04-2b34-44b9-86a9-e2fc9d1357a4")
    protected void invalidateRamcName(@SuppressWarnings("unused") boolean invalid) {
        this.ramcNameText.setForeground(UIColor.EDITOR_ROTEXT_FG);
    }

    @objid ("84dbf008-aaa3-4ef5-88c4-dc70bbf3075e")
    private void createManifestationsField(Composite parent) {
        // Label
        Label label = new Label(parent, SWT.NONE);
        label.setText(AppRamcs.I18N.getString("EditRamcDialog.RamcManifestationLabel.label"));
        label.setLayoutData(new GridData(SWT.LEFT, SWT.UP, false, false, 1, 1));
        
        // List
        this.manifestationsTable = new TableViewer(parent, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
        final GridData gdTable = new GridData(SWT.FILL, SWT.FILL, true, true);
        gdTable.heightHint = 100;
        this.manifestationsTable.getTable().setLayoutData(gdTable);
        this.manifestationsTable.getTable()
                .setToolTipText(AppRamcs.I18N.getString("EditRamcDialog.RamcManifestationLabel.tooltip"));
        
        // Drop indicator
        Label target = new Label(parent, SWT.NONE);
        target.setImage(UIImages.DROPAREA);
        final GridData gdTarget = new GridData(SWT.LEFT, SWT.UP, false, false, 1, 1);
        gdTarget.heightHint = 21;
        gdTarget.widthHint = 21;
        target.setLayoutData(gdTarget);
        
        // Branch list providers
        this.manifestationsTable.setContentProvider(new ArrayContentProvider());
        this.manifestationsTable.setLabelProvider(new BasicModelElementLabelProvider(true));
        
        this.manifestationsTable.getTable().setForeground(UIColor.EDITOR_ROTEXT_FG);
        this.manifestationsTable.getTable().setBackground(UIColor.TEXT_READONLY_BG);
    }

    @objid ("d51f4ebc-3f62-422a-a203-668b96fc15b5")
    private void createFilesField(Composite area) {
        // Files list
        this.ramcFilesList = new TableViewer(area, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);
        final Table table = this.ramcFilesList.getTable();
        
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        this.ramcFilesList.setContentProvider(new ArrayContentProvider());
        
        // First column is for the local file path
        TableViewerColumn col1 = new TableViewerColumn(this.ramcFilesList, SWT.NONE);
        ColumnViewerToolTipSupport.enableFor(this.ramcFilesList);
        
        col1.getColumn().setWidth(200);
        col1.getColumn().setText(AppRamcs.I18N.getString("EditRamcDialog.LocalFile"));
        col1.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                return ((ExportedFileEntry) element).getFileToExport().toString();
            }
        
            @Override
            public String getToolTipText(Object element) {
                ExportedFileEntry entry = ((ExportedFileEntry) element);
        
                Path fileToExport = entry.getFileToExport();
                Path realPath = ViewRamcDialog.this.dataModel.getProjectPath().resolve(fileToExport).normalize();
                if ( Files.exists(realPath)) {
                    if (realPath.equals(fileToExport)) {
                        return realPath.toString();
                    } else {
                        return AppRamcs.I18N.getMessage("EditRamcDialog.LocalFile.resolved", realPath.toString());
                    }
                }
                return AppRamcs.I18N.getMessage("EditRamcDialog.LocalFile.missing", realPath.toString());
            }
        });
        
        // Second column is for the deployment relative path
        TableViewerColumn col2 = new TableViewerColumn(this.ramcFilesList, SWT.NONE);
        col2.getColumn().setWidth(200);
        col2.getColumn().setText(AppRamcs.I18N.getString("EditRamcDialog.DeploymentPath"));
        col2.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                return ((ExportedFileEntry) element).getExportPath();
            }
        });
        col2.setEditingSupport(new EditingSupport(this.ramcFilesList) {
        
            @Override
            protected CellEditor getCellEditor(Object element) {
                return new TextCellEditor(table);
            }
        
            @Override
            protected boolean canEdit(Object element) {
                return ViewRamcDialog.this.dataModel.isEditable();
            }
        
            @Override
            protected Object getValue(Object element) {
                return ((ExportedFileEntry) element).getExportPath();
            }
        
            @Override
            protected void setValue(Object element, Object value) {
                ((ExportedFileEntry) element).setExportPath((String) value);
                getViewer().refresh();
            }
        
        });
        
        this.ramcFilesList.getTable().setForeground(UIColor.EDITOR_ROTEXT_FG);
        this.ramcFilesList.getTable().setBackground(UIColor.TEXT_READONLY_BG);
        
        Composite buttonComposite = new Composite(area, SWT.NONE);
        GridLayout l_buttonComposite = new GridLayout(1, false);
        l_buttonComposite.horizontalSpacing = 0;
        l_buttonComposite.marginWidth = 0;
        buttonComposite.setLayout(l_buttonComposite);
        
        final GridData gd_buttonComposite = new GridData(SWT.LEFT, SWT.FILL, false, true);
        // gd_buttonComposite.widthHint = 21;
        buttonComposite.setLayoutData(gd_buttonComposite);
        
        this.addFilesButton = new Button(buttonComposite, SWT.NONE);
        this.addFilesButton.setImage(UIImages.FILECHOOSE);
        this.addFilesButton.setToolTipText(AppRamcs.I18N.getMessage("EditRamcDialog.button.addfiles.tooltip"));
        this.addFilesButton.setEnabled(false);
        
        this.removeFilesButton = new Button(buttonComposite, SWT.NONE);
        this.removeFilesButton.setImage(UIImages.DELETE);
        this.removeFilesButton.setToolTipText(AppRamcs.I18N.getMessage("EditRamcDialog.button.removefiles.tooltip"));
        this.removeFilesButton.setEnabled(false);
        
        this.relativizeFilesButton = new Button(buttonComposite, SWT.NONE);
        this.relativizeFilesButton.setText(AppRamcs.I18N.getMessage("EditRamcDialog.button.relativize.label"));
        //this.relativizeFilesButton.setImage(UIImages.MINIMIZE);
        this.relativizeFilesButton.setToolTipText(AppRamcs.I18N.getMessage("EditRamcDialog.button.relativize.tooltip"));
        this.relativizeFilesButton.setEnabled(false);
        
        this.resolveFilesButton = new Button(buttonComposite, SWT.NONE);
        this.resolveFilesButton.setText(AppRamcs.I18N.getMessage("EditRamcDialog.button.resolve.label"));
        //this.resolveFilesButton.setImage(UIImages.MINIMIZE);
        this.resolveFilesButton.setToolTipText(AppRamcs.I18N.getMessage("EditRamcDialog.button.resolve.tooltip"));
        this.resolveFilesButton.setEnabled(false);
        
        GridDataFactory gdFact = GridDataFactory.defaultsFor(this.relativizeFilesButton)
        .hint(this.removeFilesButton.computeSize(-1, -1));
        //.minSize(UIImages.DELETE.getBounds().width, UIImages.DELETE.getBounds().height)
        gdFact.applyTo(this.relativizeFilesButton);
        gdFact.applyTo(this.resolveFilesButton);
        
        final FileDialog filesChooser = new FileDialog(getShell(), SWT.OPEN | SWT.MULTI);
        final Path projectPath = this.dataModel.getProjectPath();
        filesChooser.setFilterPath(projectPath != null ? projectPath.toString() : "");
        
        Label exportedFileDescription = new Label(area, SWT.WRAP);
        exportedFileDescription.setText(AppRamcs.I18N.getString("EditRamcDialog.ExportedFileDescription"));
        exportedFileDescription.setForeground(org.modelio.platform.ui.UIColor.LABEL_TIP_FG);
        final GridData gd_exportedFileDescription = new GridData(SWT.FILL, SWT.UP, true, false, 2, 1);
        exportedFileDescription.setLayoutData(gd_exportedFileDescription);
    }

    @objid ("0a238302-c9b4-4e06-bea1-1e4d29c41788")
    private void createContributorsField(Composite area) {
        // Label
        Label label = new Label(area, SWT.NONE);
        label.setText(AppRamcs.I18N.getString("EditRamcDialog.Contributors.label"));
        label.setLayoutData(new GridData(SWT.LEFT, SWT.UP, false, false, 1, 1));
        
        // Table viewer
        this.contributorsTable = CheckboxTableViewer.newCheckList(area, SWT.CHECK | SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
        
        final GridData gdTable = new GridData(SWT.FILL, SWT.FILL, true, true);
        gdTable.heightHint = 50;
        this.contributorsTable.getTable().setLayoutData(gdTable);
        // this.contributorsTable.getTable().setForeground(UIColor.EDITOR_ROTEXT_FG);
        // this.contributorsTable.getTable().setBackground(UIColor.TEXT_READONLY_BG);
        this.contributorsTable.getTable().setEnabled(false);
        this.contributorsTable.getTable().setToolTipText(AppRamcs.I18N.getString("EditRamcDialog.Contributors.tooltip"));
        
        Composite emptyComposite = new Composite(area, SWT.NONE);
        final GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd.heightHint = 21;
        gd.widthHint = 21;
        emptyComposite.setLayoutData(gd);
        
        // Branch providers
        this.contributorsTable.setContentProvider(new ArrayContentProvider());
        this.contributorsTable.setLabelProvider(new LabelProvider() {
            @Override
            public Image getImage(Object obj) {
                final IModule module = (IModule) obj;
                return module.getModuleImage();
            }
        
            @Override
            public String getText(Object obj) {
                final IModule module = (IModule) obj;
                String moduleName = module.getName();
                // Display stored version if any, module version otherwise
                String moduleVersion = ViewRamcDialog.this.dataModel.getContributingModules().get(moduleName);
                if (moduleVersion == null) {
                    moduleVersion = module.getVersion().toString();
                }
                return moduleName + " " + moduleVersion;
            }
        });
        
        this.contributorsTable.setCheckStateProvider(new ICheckStateProvider() {
        
            @Override
            public boolean isGrayed(Object obj) {
                return false;
            }
        
            @Override
            public boolean isChecked(Object obj) {
                String n = ((IModule) obj).getName();
                return ViewRamcDialog.this.dataModel.getContributingModules().containsKey(n);
            }
        });
    }

    @objid ("2a6b9a8a-22f0-4fb2-bb70-67c62cbb04e8")
    @Override
    protected String getHelpId() {
        return AppRamcs.I18N.getString("EditRamcDialog.HelpId");
    }

    @objid ("8089e241-2d0e-4491-b175-d09d89658f00")
    @Override
    protected Point getInitialSize() {
        return new Point(800, 800);
    }

    @objid ("dc16a046-3b45-4642-9685-ab37c37eb25f")
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setMinimumSize(getInitialSize());
        newShell.setText(AppRamcs.I18N.getString("EditRamcDialog.ViewRamcDialogTitle"));
    }

    @objid ("45588860-67fd-49e9-b8a2-d293844cee40")
    private void createProviderField(Composite area) {
        // Label
        Label label = new Label(area, SWT.NONE);
        label.setText(AppRamcs.I18N.getString("EditRamcDialog.RamcProvider.label"));
        label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        
        // Text
        this.ramcProviderText = new Text(area, SWT.BORDER);
        this.ramcProviderText.setEditable(false);
        this.ramcProviderText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        this.ramcProviderText.setToolTipText(AppRamcs.I18N.getString("EditRamcDialog.RamcProvider.tooltip"));
        
        this.ramcProviderText.setForeground(UIColor.EDITOR_ROTEXT_FG);
        this.ramcProviderText.setBackground(UIColor.TEXT_READONLY_BG);
        
        Composite emptyComposite = new Composite(area, SWT.NONE);
        final GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd.heightHint = 21;
        gd.widthHint = 21;
        emptyComposite.setLayoutData(gd);
    }

    @objid ("7be6fd67-016a-40e9-8761-5036ebea413c")
    protected String getProvider() {
        return this.ramcProviderText.getText();
    }

    @objid ("c8aa9c66-c2d7-4327-8214-5e39e365ee81")
    protected void invalidateProviderVersion(@SuppressWarnings("unused") boolean invalid) {
        this.ramcProviderText.setForeground(UIColor.EDITOR_ROTEXT_FG);
    }

}
