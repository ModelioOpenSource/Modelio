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
package org.modelio.platform.project.services;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.modelio.gproject.fragment.FragmentMigrationNeededException;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.fragment.migration.IFragmentMigrator;
import org.modelio.platform.project.plugin.AppProjectCore;
import org.modelio.platform.ui.dialog.ModelioDialog;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.model.spi.mm.IMigrationStepDescription;
import org.modelio.vcore.model.spi.mm.MmVersionComparator;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor.Difference;

/**
 * Dialog box that asks for migration of a model fragment.
 * 
 * @author cmarin
 */
@objid ("6f434a7c-2449-4e33-802b-e14b8e646f26")
class ConfirmMigrationDialog extends ModelioDialog {
    @objid ("324740b4-c079-4e5d-b26c-980c7401950f")
    private final Data data;

    /**
     * @param toMigrate the migration informations
     * @param parentShell a parent SWT shell
     */
    @objid ("2da6a151-9a2f-4645-b3e2-747e533a77e2")
    public  ConfirmMigrationDialog(Shell parentShell, Data data) {
        super(parentShell);
        this.data = Objects.requireNonNull(data);
        
    }

    @objid ("45f4f366-ee8e-40f8-a605-838a1345f0b4")
    @Override
    public void init() {
        getShell().setText(AppProjectCore.I18N.getMessage("ConfirmMigrationDialog.title"));
        setTitle(AppProjectCore.I18N.getMessage("ConfirmMigrationDialog.header",
                this.data.fragmentId,
                this.data.summary));
        
    }

    @objid ("922537cd-c58d-4649-84c7-00e8838ded10")
    @Override
    public Control createContentArea(Composite parent) {
        final FormToolkit tk = new FormToolkit(parent.getDisplay());
        parent.addDisposeListener(ev -> tk.dispose());
        
        GridDataFactory fillGrabGridData = GridDataFactory.fillDefaults().grab(true, false);
        GridDataFactory labelGrabGridData = GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.BOTTOM);
        
        ScrolledForm scrolledForm = tk.createScrolledForm(parent);
        GridDataFactory.defaultsFor(scrolledForm).align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(scrolledForm);
        
        final Composite body = scrolledForm.getBody();
        GridLayout bodyLayout = new GridLayout();
        bodyLayout.verticalSpacing = 10;
        body.setLayout(bodyLayout);
        body.setBackground(tk.getColors().getBackground());
        
        // Question
        String conclusion = this.data.summary + "\n\n" + AppProjectCore.I18N.getMessage("ConfirmMigrationDialog.conclusion.msg",
                this.data.getFragmentId());
        
        {
            Text label = tk.createText(body, conclusion, SWT.MULTI | SWT.WRAP);
            label.setEditable(false);
            labelGrabGridData.applyTo(label);
        }
        
        // Important user infos area
        if (!this.data.requiredUserActions.isEmpty()) {
            Section area = tk.createSection(body, ExpandableComposite.TITLE_BAR | ExpandableComposite.EXPANDED);
            // ExpandableComposite.TWISTIE
            area.setText(AppProjectCore.I18N.getMessage("ConfirmMigrationDialog.required.title", this.data.getFragmentId()));
            fillGrabGridData.applyTo(area);
        
            Composite areaBody = tk.createComposite(area, SWT.WRAP);
            areaBody.setLayout(new GridLayout());
        
            boolean isXml = this.data.requiredUserActions.contains("<form>");
            if (isXml) {
                FormText formText = tk.createFormText(areaBody, false);
                fillGrabGridData.applyTo(formText);
                try {
                    formText.setText(this.data.requiredUserActions, isXml, true);
                } catch (RuntimeException e) {
                    formText.setText(this.data.requiredUserActions, false, false);
                }
            } else {
                Text label = tk.createText(areaBody, this.data.requiredUserActions, SWT.MULTI | SWT.WRAP);
                label.setEditable(false);
                labelGrabGridData.applyTo(label);
            }
        
            area.setClient(areaBody);
        }
        
        // Details area
        if (!this.data.steps.isEmpty()) {
            Section area = tk.createSection(body, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE | ExpandableComposite.COMPACT);
            // ExpandableComposite.TWISTIE
            area.setText(AppProjectCore.I18N.getMessage("ConfirmMigrationDialog.detailarea.title",
                    this.data.getFragmentId()));
            fillGrabGridData.applyTo(area);
        
            Composite areaBody = tk.createComposite(area, SWT.WRAP);
            areaBody.setLayout(new GridLayout());
        
            Text label = tk.createText(areaBody, this.data.getDetails(), SWT.MULTI | SWT.WRAP);
            label.setEditable(false);
            labelGrabGridData.applyTo(label);
        
            area.setClient(areaBody);
        }
        return scrolledForm;
    }

    @objid ("4fe0a1a3-c0df-43e1-8afe-8a1e234a0d56")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        BundledMessages i18n = AppProjectCore.I18N;
        
        createButton(parent, IDialogConstants.YES_ID, i18n.getMessage("ConfirmMigrationDialog.button.yes"), false);
        createButton(parent, IDialogConstants.YES_TO_ALL_ID, i18n.getMessage("ConfirmMigrationDialog.button.yes_all"), false);
        // createButton(parent, IDialogConstants.NO_ID, i18n.getMessage("ConfirmMigrationDialog.button.no"), false);
        // createButton(parent, IDialogConstants.NO_TO_ALL_ID, i18n.getMessage("ConfirmMigrationDialog.button.no_all"), false);
        createButton(parent, IDialogConstants.CANCEL_ID, i18n.getMessage("ConfirmMigrationDialog.button.abort"), true)
                .setToolTipText(i18n.getMessage("ConfirmMigrationDialog.button.abort.tooltip"));
        
    }

    @objid ("d75f59bf-a160-42c9-ab4c-796d4625acd2")
    @Override
    protected void buttonPressed(int buttonId) {
        setReturnCode(buttonId);
        close();
        
    }

    @objid ("9e086005-699b-410b-b1f8-e17702e58b73")
    public  ConfirmMigrationDialog(Shell parentShell, IProjectFragment toMigrate, MetamodelVersionDescriptor targetMetamodel) throws IOException {
        this(parentShell, new Data(toMigrate, targetMetamodel));
    }

    @objid ("eb20b3c8-838e-4c70-ba00-04ebbcab86dd")
    @Override
    protected Point getInitialSize() {
        return new Point(800, 600);
    }

    @objid ("0da929b8-a81b-4ed3-9221-2eee61cdeb7c")
    public static class Data {
        @objid ("5c05fd34-30c0-46e1-8231-788a251e8cc1")
        final String fragmentId;

        @objid ("cfdd9e64-5e55-4792-bdc1-15c5c7c34d0a")
        final String summary;

        @objid ("e505556c-4227-40b2-a6bb-38fa2c697be5")
        final String requiredUserActions;

        @objid ("e3d70c2d-4307-4729-9afb-4cbc2425ebb7")
        private final String details;

        @objid ("2acaa7fe-9ece-405e-adde-6bbffdfa13a2")
        private final List<IMigrationStepDescription> steps;

        @objid ("f9ad6f2a-39b4-4613-827d-e036acd3894b")
        public  Data(IProjectFragment toMigrate, MetamodelVersionDescriptor targetMmVersion) throws IOException {
            FragmentMigrationNeededException ex = (FragmentMigrationNeededException) toMigrate.getDownError();
            IFragmentMigrator migrator = toMigrate.getMigrator(targetMmVersion);
            
            this.fragmentId = toMigrate.getId();
            this.requiredUserActions = migrator.getRequiredUserActions();
            this.steps = migrator.getStepsDescription();
            this.summary = computeSummary(ex);
            this.details = computeDetails(ex.getFragmentVersion(), targetMmVersion);
            
        }

        @objid ("3aa962f7-3a7d-4554-a81f-16367b724af0")
        public String getFragmentId() {
            return this.fragmentId;
        }

        @objid ("da9b8c7e-379f-46f0-8d6c-5d83ef5b94a3")
        private String computeSummary(FragmentMigrationNeededException ex) {
            return ex.getMessage();
        }

        @objid ("9adbea28-261b-47f8-8011-9ebafe033b6f")
        private String computeDetails(MetamodelVersionDescriptor fromMmVersion, MetamodelVersionDescriptor toVersion) {
            MmVersionComparator comp = MmVersionComparator
                    .withSource(fromMmVersion)
                    .withTarget(toVersion)
                    .withCommonRemoved();
            
            StringBuilder s = new StringBuilder();
            
            Collection<Difference> diffs = toVersion.getIncompatibilities(fromMmVersion, false);
            
            s.append(AppProjectCore.I18N.getMessage(
                    "ConfirmMigrationDialog.details",
                    this.fragmentId,
                    comp.getSource(),
                    comp.getTarget(),
                    compileDiffs(diffs, toVersion)));
            
            for (IMigrationStepDescription step : this.steps) {
                s.append("\n - ");
                s.append(step.getStepDescription());
            }
            return s.toString();
        }

        @objid ("3d4aaf0f-2493-4491-802a-db7f4d589506")
        private String compileDiffs(Collection<Difference> checkRes, MetamodelVersionDescriptor currentMm) {
            String msg = checkRes.stream()
                    .map(entry -> {
                        String fragName = entry.neededMmFragment.getName();
                        Version fragVersion = entry.neededMmFragment.getVersion();
                        switch (entry.type) {
                        case older:
                            return AppProjectCore.I18N.getMessage("ConfirmMigrationDialog.diff.older",
                                    fragName, fragVersion, currentMm.getVersion(fragName));
                        case missing:
                            return AppProjectCore.I18N.getMessage("ConfirmMigrationDialog.diff.missing",
                                    fragName, fragVersion);
                        case newer:
                            return AppProjectCore.I18N.getMessage("ConfirmMigrationDialog.diff.future",
                                    fragName, fragVersion, currentMm.getVersion(fragName));
                        case olderCompatibleBuild:
                            return AppProjectCore.I18N.getMessage("ConfirmMigrationDialog.diff.older.compat",
                                    fragName, fragVersion, currentMm.getVersion(fragName));
            
                        case same:
                        default:
                            return null;
                        }
            
                    })
                    .filter(s -> s != null)
                    .collect(Collectors.joining("\n  - ", "  - ", ""));
            return msg;
        }

        @objid ("ab506700-1271-461e-8567-368363115c57")
        public String getDetails() {
            return this.details;
        }

    }

}
