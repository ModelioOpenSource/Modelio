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
package org.modelio.app.model.merge.internal;

import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.widgets.WidgetFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.model.merge.plugin.AppModelMerge;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.platform.model.ui.swt.labelprovider.UniversalLabelProvider;
import org.modelio.platform.ui.dialog.ModelioDialog2;
import org.modelio.platform.ui.dialog.PolluxFieldBuilder;
import org.modelio.platform.ui.dialog.PolluxWidgetConfigurator;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("f36105de-59bd-4d91-b5cb-43cd0b95d07f")
public class MergeElementsDialog extends ModelioDialog2 {
    @objid ("8e059a11-5a76-4b83-91be-0d2f282eeded")
    private static final BundledMessages I18N = AppModelMerge.I18N;

    @objid ("a070fe41-75a5-4161-990d-d0abcc830b2a")
    private final MergeElementsData dataModel = new MergeElementsData();

    @objid ("46ab10ff-4113-47e5-a3e1-28303376d8aa")
    private StructuredViewer targetElementComboViewer;

    @objid ("9d70ad31-57b2-4a95-bea8-fa2b29304ea4")
    private TableViewer sourceElementsListViewer;

    @objid ("09dc4ac0-daba-41a7-9ac3-b405ce994e0d")
    private UniversalLabelProvider universalLabelProvider;

    @objid ("86b22d06-967a-4b91-aad9-d2b70ea5a393")
    private Button deleteReflexiveButton;

    @objid ("b06eb543-68ac-4709-bda9-eaba3dddff2d")
    private IModelioNavigationService navigationService;

    @objid ("371f4a1c-5291-48e8-9baa-47f02a13ca7c")
    public  MergeElementsDialog(Shell parentShell, IModelioNavigationService navigationService) {
        super(parentShell);
        this.universalLabelProvider = new UniversalLabelProvider();
        this.navigationService = navigationService;
        
    }

    @objid ("ddec54d2-a3db-4432-b27f-937ab34be087")
    public void setInput(List<MObject> list) {
        Objects.requireNonNull(list);
        this.dataModel.setInput(list);
        this.dataModel.setTarget(list.isEmpty() ? null : list.get(0));
        
    }

    @objid ("04c04d77-3213-4bf2-8118-f327d0f0213d")
    public MergeElementsData getDataModel() {
        return this.dataModel;
    }

    @objid ("40f804b1-6032-4c70-825b-fe08af21296c")
    @Override
    protected void addButtonsInButtonBar(Composite parent) {
        super.addDefaultButtons(parent);
    }

    @objid ("a0db6fe0-929b-45c4-94c5-8ddecbb99982")
    @Override
    protected Control createContentArea(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        PolluxWidgetConfigurator.configureContainer(composite);
        //GridLayoutFactory.fillDefaults().spacing(0, 0).applyTo(composite);
        
        PolluxWidgetConfigurator.configureHeaderField(WidgetFactory.label(SWT.NONE)
                .text(I18N.getString("dialog.title"))
                .create(composite));
        
        GridDataFactory fillDefaults = GridDataFactory.fillDefaults().grab(true, true);
        if (true) {
            // just create the "choose target" list
            new PolluxFieldBuilder(composite).createMultiField(
                    I18N.getString("dialog.target.label"),
                    () -> createTargetElementCombo(composite),
                    I18N.getString("dialog.target.caption"));
        } else {
            // Create in 2 columns
            // - a "choose targets" list
            // - a "sources" read only list
            Composite splitter = new Composite(composite, SWT.NONE);
            PolluxWidgetConfigurator.configureContainer(splitter);
            fillDefaults.applyTo(splitter);
            splitter.setLayout(GridLayoutFactory.fillDefaults().spacing(0, 0).numColumns(2).equalWidth(true).create());
            Composite col1 = new Composite(splitter, SWT.NONE);
            Composite col2 = new Composite(splitter, SWT.NONE);
        
            fillDefaults.applyTo(col1);
            fillDefaults.applyTo(col2);
        
            PolluxWidgetConfigurator.configureContainer(col1);
            PolluxWidgetConfigurator.configureContainer(col2);
        
            new PolluxFieldBuilder(col1).createMultiField(
                    I18N.getString("dialog.target.label"),
                    () -> createTargetElementCombo(col1),
                    I18N.getString("dialog.target.caption"));
        
            PolluxFieldBuilder col2FieldBuilder = new PolluxFieldBuilder(col2);
            col2FieldBuilder.createMultiField(
                    I18N.getString("dialog.sources.label"),
                    () -> createSourceElementsList(col2),
                    I18N.getString("dialog.sources.caption"));
        
            Color colorInactive = parent.getDisplay().getSystemColor(SWT.COLOR_WIDGET_DISABLED_FOREGROUND);
            col2FieldBuilder.getLastCreatedLabel().setForeground(colorInactive);
            col2FieldBuilder.getLastCreatedField().setForeground(colorInactive);
            col2FieldBuilder.getLastCreatedCaption().setForeground(colorInactive);
        }
        
        if (true) {
        
            PolluxFieldBuilder col3FieldBuilder = new PolluxFieldBuilder(composite);
        
            /*PolluxWidgetConfigurator.configureHeaderField(WidgetFactory.label(SWT.NONE)
                    .text("Options")
                    .create(composite));*/
        
            this.deleteReflexiveButton = col3FieldBuilder.createSimpleField(
                    "" /*I18N.getString("dialog.delete-reflexives.label")*/,
                    () -> WidgetFactory.button(SWT.CHECK)
                        .text(I18N.getString("dialog.delete-reflexives.label"))
                        .create(composite),
                    I18N.getString("dialog.delete-reflexives.caption"));
        
            PolluxWidgetConfigurator.configureStyleForFieldLabel(this.deleteReflexiveButton);
        
            this.deleteReflexiveButton.addListener(SWT.Selection,
                    ev -> this.dataModel.setDeleteReflexiveLinks(this.deleteReflexiveButton.getSelection()));
        }
        
        composite.addDisposeListener(ev -> this.universalLabelProvider.dispose());
        return composite;
    }

    @objid ("f05b9c1a-11fd-4337-9fc8-64cb95377dba")
    private Control createSourceElementsList(Composite composite) {
        this.sourceElementsListViewer = new TableViewer(composite);
        this.sourceElementsListViewer.setContentProvider(new ArrayContentProvider());
        this.sourceElementsListViewer.setLabelProvider(this.universalLabelProvider);
        this.sourceElementsListViewer.getTable().setEnabled(false);
        return this.sourceElementsListViewer.getControl();
    }

    @objid ("3221ce3d-bcfe-4c04-b232-e0628b39c967")
    private Control createTargetElementCombo(Composite composite) {
        this.targetElementComboViewer = new TableViewer(composite);
        this.targetElementComboViewer.setContentProvider(new ArrayContentProvider());
        this.targetElementComboViewer.addSelectionChangedListener(this::onTargetComboChange);
        this.targetElementComboViewer.setLabelProvider(this.universalLabelProvider);
        return this.targetElementComboViewer.getControl();
    }

    @objid ("31e25eb4-4283-45e9-a04d-623e99911174")
    private void onTargetComboChange(SelectionChangedEvent ev) {
        MObject target = (MObject) this.targetElementComboViewer.getStructuredSelection().getFirstElement();
        this.dataModel.setTarget(target);
        this.navigationService.fireNavigate(target);
        if (this.sourceElementsListViewer != null)
        this.sourceElementsListViewer.setInput(this.dataModel.streamTargets().toArray());
        
        getButton(IDialogConstants.OK_ID).setEnabled(this.dataModel.getTarget() != null);
        
    }

    @objid ("eb4b2a94-03df-4456-ab36-324c772a65b9")
    @Override
    protected void init() {
        setTitle(I18N.getString("dialog.title"));
        
        this.targetElementComboViewer.setInput(this.dataModel.getInput());
        this.targetElementComboViewer.setSelection(new StructuredSelection(this.dataModel.getTarget()));
        if (this.sourceElementsListViewer != null)
        this.sourceElementsListViewer.setInput(this.dataModel.streamTargets().toArray());
        getButton(IDialogConstants.OK_ID).setEnabled(this.dataModel.getTarget() != null);
        
    }

}
