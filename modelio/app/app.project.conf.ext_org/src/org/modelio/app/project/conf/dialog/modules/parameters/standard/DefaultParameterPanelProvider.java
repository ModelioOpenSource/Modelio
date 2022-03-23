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
package org.modelio.app.project.conf.dialog.modules.parameters.standard;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.modelio.api.module.IModule;
import org.modelio.api.module.parameter.IParameterGroupModel;
import org.modelio.api.module.parameter.IParameterModel;
import org.modelio.app.project.conf.plugin.AppProjectConfExt;
import org.modelio.platform.ui.UIColor;
import org.modelio.platform.ui.panel.IPanelProvider;

@objid ("f235d564-b537-4a35-9b49-8e8b0a3134e9")
public class DefaultParameterPanelProvider implements IPanelProvider {
    @objid ("e92a18dd-3c48-4835-be06-6982eebe3f96")
    protected TreeViewer parameterViewer;

    @objid ("419279ef-db45-4ab1-a360-84897765a5ea")
    protected Composite compo;

    @objid ("81394143-2656-4fb1-8203-36d07670c5ea")
    protected Text descriptionLabel;

    @objid ("df26b5b0-70d8-459e-b296-5f2c6a3cea8d")
    @Override
    public Control createPanel(Composite parent) {
        this.compo = new Composite(parent, SWT.NONE);
        this.compo.setLayout(new GridLayout());
        
        this.parameterViewer = new TreeViewer(this.compo, SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        this.parameterViewer.getTree().setHeaderVisible(true);
        this.parameterViewer.getTree().setLinesVisible(true);
        
        // Create columns
        String[] columnTitles = { AppProjectConfExt.I18N.getMessage("ParameterSection.NameColumn"), AppProjectConfExt.I18N.getMessage("ParameterSection.ValueColumn"), AppProjectConfExt.I18N.getMessage("ParameterSection.ScopeColumn") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        int[] columnInitialWidths = { 300, 500, 100 };
        TreeViewerColumn col1 = DefaultParameterPanelProvider.createTreeViewerColumn(this.parameterViewer, columnTitles[0], columnInitialWidths[0]);
        col1.setLabelProvider(new NameLabelProvider());
        
        TreeViewerColumn col2 = DefaultParameterPanelProvider.createTreeViewerColumn(this.parameterViewer, columnTitles[1], columnInitialWidths[1]);
        col2.setLabelProvider(new ValueLabelProvider());
        col2.setEditingSupport(new ParametersEditingSupport(this.parameterViewer));
        
        TreeViewerColumn col3 = DefaultParameterPanelProvider.createTreeViewerColumn(this.parameterViewer, columnTitles[2], columnInitialWidths[2]);
        col3.setLabelProvider(new ScopeLabelProvider());
        
        this.parameterViewer.setContentProvider(new ParametersContentProvider());
        this.parameterViewer.setAutoExpandLevel(2);
        this.parameterViewer.setInput(null);
        GridData gd1 = new GridData(SWT.FILL, SWT.FILL, true, true);
        this.parameterViewer.getTree().setLayoutData(gd1);
        
        this.descriptionLabel = new Text(this.compo, SWT.WRAP);
        this.descriptionLabel.setForeground(UIColor.LABEL_TIP_FG);
        GridData gd2 = new GridData(SWT.FILL, SWT.FILL, false, false);
        gd2.heightHint = 60;
        gd2.widthHint = 123; // Workaround: an arbitrary width must be set here to avoid layout problems
        this.descriptionLabel.setLayoutData(gd2);
        
        addParameterDescriptionSectionUpdater();
        return this.compo;
    }

    @objid ("1a558263-b7c1-4a7c-98af-829a2fada2b0")
    private static TreeViewerColumn createTreeViewerColumn(TreeViewer viewer, String title, int bound) {
        final TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.CENTER);
        column.getColumn().setText(title);
        column.getColumn().setWidth(bound);
        column.getColumn().setResizable(true);
        column.getColumn().setMoveable(true);
        return column;
    }

    @objid ("930a7ef0-c04a-43d7-bb7b-aff52ca7db46")
    @Override
    public Control getPanel() {
        return this.compo;
    }

    @objid ("479ce1a6-c742-4e27-b000-3204204561e0")
    @Override
    public String getHelpTopic() {
        return null;
    }

    @objid ("0bb6db3f-1fb7-47fa-8b43-ddd9a0df849b")
    @Override
    public Object getInput() {
        return this.parameterViewer.getInput();
    }

    @objid ("4e6d8817-89be-42d1-880e-08690f9e6af2")
    @Override
    public void setInput(Object input) {
        this.parameterViewer.setInput(input);
        if (input instanceof Collection<?>) {
            final IModule module = (IModule) ((Collection<?>) input).toArray()[0];
            this.descriptionLabel.setText(module.getDescription());
        }
        
    }

    @objid ("273ef203-ac9d-4a8c-af80-a9e6b706e269")
    @Override
    public void dispose() {
        this.parameterViewer.getTree().dispose();
        this.parameterViewer = null;
        this.compo.dispose();
        this.compo = null;
        
    }

    /**
     * Panel is relevant when input is a module with at least one parameter.
     */
    @objid ("110f3e41-d36b-4c5b-a8aa-4d63f1d450d5")
    @Override
    public boolean isRelevantFor(Object input) {
        return input instanceof IModule && !((IModule) input).getModuleContext().getConfiguration().getParameters().isEmpty();
    }

    /**
     * Add parameter section updater When selection change in the section, the description label text will be updated
     * @param descriptionLabel
     * @param parameterViewer
     */
    @objid ("1fd67aa3-250f-49a9-a24a-3a1a95639a8c")
    private void addParameterDescriptionSectionUpdater() {
        this.parameterViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                // Reset description
                DefaultParameterPanelProvider.this.descriptionLabel.setText(""); //$NON-NLS-1$
        
                ISelection selection = event.getSelection();
                if (selection instanceof IStructuredSelection) {
                    IStructuredSelection structuredSelection = (IStructuredSelection) selection;
                    if (structuredSelection.size() == 1) {
                        Object obj = structuredSelection.getFirstElement();
        
                        if (obj instanceof IParameterModel) {
                            // Fill the module's description
                            IParameterModel param = (IParameterModel) obj;
        
                            DefaultParameterPanelProvider.this.descriptionLabel.setText(param.getDescription());
                            return;
                        } else if (obj instanceof IParameterGroupModel) {
                            // No description
                            return;
                        }
                    }
                }
                // Any other case, use module description
                final Object input = DefaultParameterPanelProvider.this.parameterViewer.getInput();
                if (input instanceof Collection<?>) {
                    final IModule module = (IModule) ((Collection<?>) input).toArray()[0];
                    DefaultParameterPanelProvider.this.descriptionLabel.setText(module.getDescription());
                }
            }
        });
        
    }

}
