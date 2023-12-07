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
/**
 *
 */
package org.modelio.app.project.conf.dialog.libraries.local.property;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.modelio.app.project.conf.dialog.ProjectModel;
import org.modelio.app.project.conf.plugin.AppProjectConfExt;
import org.modelio.gproject.data.ramc.IModelComponentInfos;
import org.modelio.gproject.data.ramc.IModelComponentInfos.ExportedFile;
import org.modelio.platform.ui.UIColor;
import org.modelio.vbasic.version.Version;

/**
 * Ramc Property Composite Show the properties of the select module component
 * 
 * @author xzhang
 */
@objid ("9b577266-79f6-46bf-b051-892f87db13b6")
public class RamcPropertyComposite extends Composite {
    @objid ("88d66e80-764a-40aa-af16-be9c440dd721")
    protected Button closeButton = null;

    @objid ("e0ab963b-e7d4-4a4a-b998-b2a2bb131c22")
    private Label ramcNameLabel = null;

    @objid ("25fde578-fcf2-4281-9643-570faeed8b37")
    private Label ramcVersionLabel = null;

    @objid ("c73fb199-04c7-424f-9f32-dce5fa9e3676")
    private Label ramcVersionHistoryLabel = null;

    @objid ("fbbf73d5-ca6a-44c8-9974-80b7f3ff52e1")
    private Label ramcDependenciesLabel = null;

    @objid ("e45fc431-1434-43ac-b5f8-973903b5c806")
    protected Text ramcNameText = null;

    @objid ("93f42ff2-e5ca-464d-93c2-017400ea02d4")
    protected Text ramcVersionText = null;

    @objid ("07770b9d-8873-4a8b-a8a5-9c05d5d19fb1")
    protected Browser ramcVersionHistoryText = null;

    @objid ("40de9800-b75b-4177-a832-bdbd937a8ebe")
    private TableViewer ramcDependenciesList = null;

    @objid ("7c915b04-c67c-44e4-8cb2-5fbf58e707e8")
    protected Label ramcFilesLabel = null;

    @objid ("a00dcda3-83c4-4bda-b241-021f50518f3c")
    protected TableViewer ramcFilesList = null;

    @objid ("9f7e7fed-bf15-4792-8eb3-0ab44f9d8b0c")
    private Label ramcContributingModulesLabel = null;

    @objid ("48c8a485-c48d-49e6-aa48-fdfa296a1449")
    private TableViewer ramcContributingModulesList = null;

    @objid ("c84dd041-d607-496b-b4eb-d0aef3cdde57")
    private Label ramcProviderLabel = null;

    @objid ("e01e38fa-3e48-4bcd-94bf-97e04be1e643")
    protected Text ramcProviderText = null;

    @objid ("2ded6f35-1d02-4839-9c89-1199d16dbd32")
    protected IModelComponentInfos fragmentInfos = null;

    @objid ("a5eb8d02-5186-4279-afd0-ad4052c8c930")
    private final ProjectModel projectAdapter;

    @objid ("f8934f5a-f189-499d-8cd3-5722773ba2e0")
    public  RamcPropertyComposite(final Composite parent, final int style, final IModelComponentInfos fragmentInfos, final ProjectModel projectAdapter) {
        super(parent, style);
        this.fragmentInfos = fragmentInfos;
        this.projectAdapter = projectAdapter;
        final GridLayout mainAreaLayout = new GridLayout(2, false);
        mainAreaLayout.horizontalSpacing = 1; mainAreaLayout.verticalSpacing = 1;
        setLayout(mainAreaLayout);
        createControls();
        if (this.fragmentInfos != null) {
            refresh();
        }
        
    }

    @objid ("7710d5ba-8ca0-4b8c-85f6-56c2cc761612")
    private void createControls() {
        createRamcNameField(this);
        createRamcVersionField(this);
        createVersionHistoryField(this);
        createProviderField(this);
        createDependenciesField(this);
        createContributingModulesField(this);
        createFilesField(this);
        
    }

    @objid ("7770dc89-5b15-4a29-b8df-ced143abc47b")
    private void createRamcNameField(final Composite area) {
        // model component name field:
        this.ramcNameLabel = new Label(area, SWT.NONE);
        this.ramcNameLabel.setText(AppProjectConfExt.I18N.getString("RamcPropertyDialog.RamcName"));
        
        this.ramcNameText = new Text(area, SWT.BORDER);
        this.ramcNameText.setEditable(false);
        
        this.ramcNameText.setForeground(UIColor.EDITOR_ROTEXT_FG);
        this.ramcNameText.setBackground(UIColor.TEXT_READONLY_BG);
        
        final GridData gd_ramcNameLabel = new GridData(SWT.LEFT, SWT.UP, false, false, 1, 1);
        this.ramcNameLabel.setLayoutData(gd_ramcNameLabel);
        
        final GridData gd_ramcNameText = new GridData(SWT.FILL, SWT.UP, true, false, 1, 1);
        this.ramcNameText.setLayoutData(gd_ramcNameText);
        
    }

    @objid ("d41bade6-2694-4025-a4fb-7de0df02f6dc")
    private void createRamcVersionField(final Composite area) {
        // model component version field:
        this.ramcVersionLabel = new Label(area, SWT.NONE);
        this.ramcVersionLabel.setText(AppProjectConfExt.I18N.getString("RamcPropertyDialog.RamcVersion"));
        
        this.ramcVersionText = new Text(area, SWT.BORDER);
        this.ramcVersionText.setEditable(false);
        
        this.ramcVersionText.setForeground(UIColor.EDITOR_ROTEXT_FG);
        this.ramcVersionText.setBackground(UIColor.TEXT_READONLY_BG);
        
        final GridData gd_ramcVersionLabel = new GridData(SWT.LEFT, SWT.UP, false, false, 1, 1);
        this.ramcVersionLabel.setLayoutData(gd_ramcVersionLabel);
        
        final GridData gd_ramcVersionText = new GridData(SWT.FILL, SWT.UP, true, false, 1, 1);
        this.ramcVersionText.setLayoutData(gd_ramcVersionText);
        
    }

    @objid ("d4ec6f14-896e-44a3-8729-4de3127b0fcf")
    private void createVersionHistoryField(final Composite area) {
        // model component name field:
        this.ramcVersionHistoryLabel = new Label(area, SWT.NONE);
        this.ramcVersionHistoryLabel.setText(AppProjectConfExt.I18N.getString("RamcPropertyDialog.RamcVersionHistory"));
        
        this.ramcVersionHistoryText = new Browser(area, SWT.BORDER | SWT.MULTI);
        
        this.ramcVersionHistoryText.setForeground(UIColor.EDITOR_ROTEXT_FG);
        this.ramcVersionHistoryText.setBackground(UIColor.TEXT_READONLY_BG);
        
        final GridData gd_ramcDescriptionLabel = new GridData(SWT.LEFT, SWT.UP, false, false);
        this.ramcVersionHistoryLabel.setLayoutData(gd_ramcDescriptionLabel);
        
        final GridData gd_ramcDescriptionText = new GridData(SWT.FILL, SWT.FILL, true, true);
        this.ramcVersionHistoryText.setLayoutData(gd_ramcDescriptionText);
        gd_ramcDescriptionText.heightHint = 80;
        
    }

    @objid ("f54f4f90-35ee-46fe-b644-efb1f6bccc8a")
    private void createDependenciesField(final Composite area) {
        // Ramc dependencies field:
        this.ramcDependenciesLabel = new Label(area, SWT.NONE);
        this.ramcDependenciesLabel.setText(AppProjectConfExt.I18N.getString("RamcPropertyDialog.RamcDependencies"));
        
        initDependenciesListViewer(area);
        
        final GridData gd_ramcDependenciesLabel = new GridData(SWT.LEFT, SWT.UP, false, false, 1, 1);
        this.ramcDependenciesLabel.setLayoutData(gd_ramcDependenciesLabel);
        
    }

    @objid ("73b873d8-79fd-4435-a5e5-b53d85b90891")
    private void createFilesField(final Composite area) {
        // Ramc file field:
        this.ramcFilesLabel = new Label(area, SWT.NONE);
        this.ramcFilesLabel.setText(AppProjectConfExt.I18N.getString("RamcPropertyDialog.RamcFilesLabel"));
        
        initFilesListViewer(area);
        final GridData gd_ramcFilesLabel = new GridData(SWT.LEFT, SWT.UP, false, false, 1, 1);
        this.ramcFilesLabel.setLayoutData(gd_ramcFilesLabel);
        
    }

    @objid ("2602088e-7cf4-44dd-be05-77cf60faa853")
    private void initDependenciesListViewer(final Composite area) {
        this.ramcDependenciesList = new TableViewer(area, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
        
        this.ramcDependenciesList.setContentProvider(new DependenciesContentProvider());
        this.ramcDependenciesList.setLabelProvider(new DependenciesLabelProvider(this.projectAdapter.getLocalLibraryFragments()));
        
        this.ramcDependenciesList.getTable().setForeground(UIColor.EDITOR_ROTEXT_FG);
        this.ramcDependenciesList.getTable().setBackground(UIColor.TEXT_READONLY_BG);
        
        final GridData gd_ramcDependenciesList = new GridData(SWT.FILL, SWT.FILL, true, false);
        gd_ramcDependenciesList.heightHint = 60;
        this.ramcDependenciesList.getTable().setLayoutData(gd_ramcDependenciesList);
        
    }

    @objid ("e610daa5-9072-4796-ad7f-c909173e7e1f")
    private void initFilesListViewer(final Composite area) {
        this.ramcFilesList = new TableViewer(area, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
        
        this.ramcFilesList.setContentProvider(new FilesContentProvider());
        this.ramcFilesList.setLabelProvider(new FilesLabelProvider());
        
        this.ramcFilesList.getTable().setForeground(UIColor.EDITOR_ROTEXT_FG);
        this.ramcFilesList.getTable().setBackground(UIColor.TEXT_READONLY_BG);
        
        final GridData gd_ramcFilesList = new GridData(SWT.FILL, SWT.FILL, true, true);
        gd_ramcFilesList.heightHint = 50;
        this.ramcFilesList.getTable().setLayoutData(gd_ramcFilesList);
        
    }

    @objid ("1f7a0917-e653-4e24-85f9-a027c54e259d")
    public void refresh() {
        this.ramcNameText.setText(this.fragmentInfos.getName());
        
        final Version ramcVersion = this.fragmentInfos.getVersion();
        if (ramcVersion == null) {
            this.ramcVersionText.setText("");
        } else {
            final String buildVersion = String.format("%02d", ramcVersion.getBuildVersion());
            this.ramcVersionText.setText(ramcVersion.getMajorVersion() +
                    "." +
                    ramcVersion.getMinorVersion() +
                    "." +
                    buildVersion);
        }
        
        
        this.ramcVersionHistoryText.setText(this.fragmentInfos.getDescription());
        this.ramcProviderText.setText(this.fragmentInfos.getProvider());
        this.ramcDependenciesList.setInput(this.fragmentInfos.getRequiredModelComponents());
        this.ramcContributingModulesList.setInput(this.fragmentInfos.getContributingModules());
        this.ramcFilesList.setInput(getRamcExportedFiles());
        
    }

    @objid ("91918d06-28cb-4f2d-9896-cf0a13567bd8")
    private Set<File> getRamcExportedFiles() {
        final Set<File> exportedFiles = new HashSet<>();
        for (final ExportedFile ef : this.fragmentInfos.getExportedFiles()) {
            exportedFiles.add(ef.getPath().toFile());
        }
        return exportedFiles;
    }

    @objid ("496542ce-f54e-4720-969c-2eb6388213a3")
    public void setFragmentInfos(final IModelComponentInfos fragmentInfos) {
        this.fragmentInfos = fragmentInfos;
    }

    @objid ("6d8a3002-5ac5-4db5-a871-57019b5db2c4")
    private void createContributingModulesField(final Composite area) {
        // Ramc contributing modules field
        this.ramcContributingModulesLabel = new Label(area, SWT.NONE);
        this.ramcContributingModulesLabel.setText(AppProjectConfExt.I18N.getString("RamcPropertyDialog.RamcContributingModules"));
        
        initContributingModulesListViewer(area);
        
        final GridData gd_ramcContributingModulesLabel = new GridData(SWT.LEFT, SWT.UP, false, false, 1, 1);
        this.ramcContributingModulesLabel.setLayoutData(gd_ramcContributingModulesLabel);
        
    }

    @objid ("8bd431ac-9e32-42d4-b216-3364e7d59b2a")
    private void initContributingModulesListViewer(final Composite area) {
        this.ramcContributingModulesList = new TableViewer(area, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
        
        this.ramcContributingModulesList.setContentProvider(new ContributingModulesContentProvider());
        this.ramcContributingModulesList.setLabelProvider(new ContributingModulesLabelProvider(this.projectAdapter.getModules()));
        
        this.ramcContributingModulesList.getTable().setForeground(UIColor.EDITOR_ROTEXT_FG);
        this.ramcContributingModulesList.getTable().setBackground(UIColor.TEXT_READONLY_BG);
        
        final GridData gd_ramcContributingModulesList = new GridData(SWT.FILL, SWT.FILL, true, false);
        gd_ramcContributingModulesList.heightHint = 60;
        this.ramcContributingModulesList.getTable().setLayoutData(gd_ramcContributingModulesList);
        
    }

    @objid ("9359a3ed-3ee8-453c-bd4e-a3c8f7c84e82")
    private void createProviderField(final Composite area) {
        // model component provider field:
        this.ramcProviderLabel = new Label(area, SWT.NONE);
        this.ramcProviderLabel.setText(AppProjectConfExt.I18N.getString("RamcPropertyDialog.RamcProvider"));
        
        this.ramcProviderText = new Text(area, SWT.BORDER);
        this.ramcProviderText.setEditable(false);
        
        this.ramcProviderText.setForeground(UIColor.EDITOR_ROTEXT_FG);
        this.ramcProviderText.setBackground(UIColor.TEXT_READONLY_BG);
        
        this.ramcProviderLabel.setLayoutData(new GridData(SWT.LEFT, SWT.UP, false, false, 1, 1));
        this.ramcProviderText.setLayoutData(new GridData(SWT.FILL, SWT.UP, true, false, 1, 1));
        
    }

}
