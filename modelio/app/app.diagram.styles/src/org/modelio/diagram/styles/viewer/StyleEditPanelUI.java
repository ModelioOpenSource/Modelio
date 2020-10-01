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

package org.modelio.diagram.styles.viewer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.IStyleChangeListener;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.diagram.styles.editingsupport.StyleCellLabelProvider;
import org.modelio.diagram.styles.editingsupport.StylePropertyEditingSupport;
import org.modelio.diagram.styles.editingsupport.key.KeyTableLabelProvider;
import org.modelio.diagram.styles.editingsupport.key.KeyTreeLabelProvider;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.platform.core.picking.IModelioPickingService;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.platform.model.ui.swt.contribitem.SwtContributionItem;
import org.modelio.platform.ui.UIColor;

@objid ("249a7a87-924f-4f95-b6f6-f2b455c509bc")
class StyleEditPanelUI implements IStyleChangeListener {
    @objid ("7ffaf729-42c5-416f-abff-16fdce06c09a")
    private final boolean tableMode;

    @objid ("d347a428-9f58-4fa7-8513-9aa5ecc71353")
    private StyleEditPanelUIData model = new StyleEditPanelUIData();

    @objid ("eeed5dc3-e0bb-4814-9ad9-68b40b90811a")
    private IModelioPickingService modelioPickingService;

    @objid ("a69d5e8b-ec5e-4418-8bd7-5227a347afd6")
    private StyleEditPanelController controller;

    @objid ("3cad4e57-92e0-416c-943e-2fb1ef193139")
     ColumnViewer viewer;

    @objid ("8e84fea0-7733-4b8b-ba40-653caa127ae8")
    private Label descriptionText;

    @objid ("2e2affc0-fd90-4bf6-be91-78b58586496d")
    private SashForm sash;

    @objid ("acf41017-3387-4442-b74e-ea4ab8249f1f")
    private MenuManager contextualMenu;

    @objid ("69fdb0da-adfe-4ca2-8794-b8ab8d37ffc3")
    private StyleEditPanelSelection selectionComputations;

    @objid ("01b7e78d-2b0e-4144-8cec-e3bd296e96b4")
    private ViewerColumn col1;

    /**
     * @return the Modelio picking service
     */
    @objid ("7d656369-8c84-412a-9605-6a47389ae59b")
    public IModelioPickingService getPickingService() {
        return this.modelioPickingService;
    }

    /**
     * Called when a property changed in the edited style. See IStyleChangeListener.
     */
    @objid ("315c3151-cec4-4024-b38e-0ee99d31ae23")
    @Override
    public void styleChanged(IStyle changedStyle) {
        styleChanged();
    }

    /**
     * @return the currently edited style.
     */
    @objid ("3d3fa04a-5ce1-427a-a44f-9f8f0d533734")
    public IStyle getEditedStyle() {
        return this.model.getStyleData();
    }

    @objid ("0b5c4210-98cf-4e1e-88f8-92d8feda2980")
    public StyleEditPanelUI(StyleEditPanelController controller, boolean tableMode) {
        this.controller = controller;
        this.tableMode = tableMode;
    }

    /**
     * Convenience to create and configure a column
     */
    @objid ("16853d41-4c39-4351-9f2c-67ffef1f0cd3")
    private ViewerColumn createTreeViewerColumn(StyleEditPanelUI ui, String title, int bound) {
        if (this.tableMode) {
            final TableViewerColumn column = new TableViewerColumn((TableViewer) ui.viewer, SWT.NONE);
            column.getColumn().setText(title);
            column.getColumn().setWidth(bound);
            column.getColumn().setResizable(true);
            column.getColumn().setMoveable(true);
            return column;
        } else {
            final TreeViewerColumn column = new TreeViewerColumn((TreeViewer) ui.viewer, SWT.NONE);
            column.getColumn().setText(title);
            column.getColumn().setWidth(bound);
            column.getColumn().setResizable(true);
            column.getColumn().setMoveable(true);
            return column;
        }
    }

    /**
     * @return the edited style model.
     */
    @objid ("566c47bf-5e83-487a-a040-57d015fbc8c3")
    public StyleEditPanelUIData getModel() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.model;
    }

    /**
     * This will create the columns for the style table viewer
     */
    @objid ("d9104cce-fec4-47f8-bc0e-ece2572772fa")
    private void createColumns(final StyleEditPanelUI ui) {
        final String[] columnTitles = {
                DiagramStyles.I18N.getString("StylesViewer.Property"),
                DiagramStyles.I18N.getString("StylesViewer.Value") };
        final int[] columnInitialWidths = { 150, 150 };
        
        // First column is for the style key name
        this.col1 = createTreeViewerColumn(ui,
                columnTitles[0],
                columnInitialWidths[0]);
        
        if (this.tableMode) {
            this.col1.setLabelProvider(new KeyTableLabelProvider(
                    () -> getModel().getStyleTreeModel(),
                    () -> getModel().getStyleData()));
        } else {
            this.col1.setLabelProvider(new KeyTreeLabelProvider(
                    () -> getModel().getStyleTreeModel(),
                    () -> getModel().getStyleData()));
        
        }
        
        // Second column is for the style key type
        ViewerColumn col2 = createTreeViewerColumn(ui, columnTitles[1], columnInitialWidths[1]);
        col2.setLabelProvider(new StyleCellLabelProvider(
                this.viewer,
                this.tableMode,
                () -> getModel().getStyleTreeModel()));
        col2.setEditingSupport(new StylePropertyEditingSupport(this.viewer));
    }

    /**
     * Called when a property changed in the edited style See IStyleChangeListener.
     */
    @objid ("2126129c-3a2c-4c1e-b7a8-6503d9f96a9e")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        styleChanged();
    }

    @objid ("559a43b2-1c24-49b0-894e-eeb907284d93")
    public Control createUI(Composite parent) {
        this.sash = new SashForm(parent, SWT.VERTICAL);
        
        if (this.tableMode) {
            TableViewer tableViewer = new TableViewer(this.sash,
                    SWT.HIDE_SELECTION | SWT.MULTI
                            | SWT.H_SCROLL | SWT.V_SCROLL
                            | SWT.FULL_SELECTION);
            this.viewer = tableViewer;
            tableViewer.getTable().setHeaderVisible(true);
            tableViewer.getTable().setLinesVisible(true);
            this.viewer.setContentProvider(new StyleDataTableContentProvider(this.model));
        } else {
            TreeViewer treeviewer = new TreeViewer(this.sash,
                    SWT.HIDE_SELECTION | SWT.MULTI
                            | SWT.H_SCROLL | SWT.V_SCROLL
                            | SWT.FULL_SELECTION);
            this.viewer = treeviewer;
            treeviewer.getTree().setHeaderVisible(true);
            treeviewer.getTree().setLinesVisible(true);
            this.viewer.setContentProvider(new StyleDataTreeContentProvider(this.model));
        }
        
        ColumnViewerToolTipSupport.enableFor(this.viewer, ToolTip.NO_RECREATE);
        
        // Layout the viewer
        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        this.viewer.getControl().setLayoutData(gridData);
        
        // Create columns
        createColumns(this);
        
        // Description
        this.descriptionText = new Label(this.sash, SWT.WRAP | SWT.V_SCROLL);
        this.descriptionText.setForeground(UIColor.LABEL_TIP_FG);
        
        this.sash.setWeights(new int[] { 90, 10 });
        
        this.viewer.addSelectionChangedListener((SelectionChangedEvent event) -> {
            ISymbolViewItem o = SelectionHelper.getFirst(event.getSelection(), ISymbolViewItem.class);
            this.controller.onPropertySelection(o);
        });
        
        // Some computations that are observed
        this.selectionComputations = new StyleEditPanelSelection(this.viewer, this::getEditedStyle);
        
        this.contextualMenu = new MenuManager();
        buildContextualMenu(this.contextualMenu);
        
        Menu menu = this.contextualMenu.createContextMenu(this.viewer.getControl());
        this.viewer.getControl().setMenu(menu);
        return this.sash;
    }

    /**
     * Set the model for the viewer. The 'null' value means no model. The Viewer actually uses a fake empty model in this case, displaying an empty table.
     * @param model the model used by this viewer.
     */
    @objid ("257c7bd2-1239-4e57-b9d1-3e2fa3f29512")
    public void update(StyleEditPanelUIData data) {
        // unregister Style listener from previous model
        if (this.model != null && this.model.getStyleData() != null) {
            this.model.getStyleData().removeListener(this);
        }
        
        if (!this.sash.isDisposed()) {
            // set model
            if (data == null) {
                this.model = new StyleEditPanelUIData();
                this.viewer.setInput(this.model);
                // do not register a listener
            } else {
                this.model = data;
                this.viewer.setInput(this.model);
        
                // register as Style listener
                if (this.model.getStyleData() != null) {
                    this.model.getStyleData().addListener(this);
                }
            }
            if (!this.tableMode) {
                final TreeViewerColumn column = (TreeViewerColumn) this.col1;
                column.getColumn().pack();
            }
        }
    }

    @objid ("992eebbc-aaa7-416e-b80c-f52f53f7d72c")
    public void dispose() {
        // unregister Style listener from previous model
        if (this.model != null && this.model.getStyleData() != null) {
            this.model.getStyleData().removeListener(this);
        }
    }

    @objid ("79c96ed3-29f6-4637-bee1-272d29f048f9")
    public void setDescription(String s) {
        this.descriptionText.setText(s);
    }

    @objid ("d4ca52e3-14ae-4522-b8fc-1a019c1cf046")
    private void styleChanged() {
        if (!this.viewer.getControl().isDisposed()) {
            this.viewer.refresh(true);
        
            this.controller.onStyleChanged();
        } else {
            // Sometimes, this listener is called when the tree viewer is already disposed. Make sure to unregister it.
            this.model.getStyleData().removeListener(this);
        }
    }

    @objid ("ee861f58-552a-400e-be9d-02efd8441387")
    SashForm getSash() {
        return this.sash;
    }

    /**
     * Add entries to the contextual menu
     * 
     * @param popupMenu the contextual menu to fill.
     */
    @objid ("22d302ce-9e17-4c4b-9d66-6800061cd842")
    private void buildContextualMenu(MenuManager popupMenu) {
        final StyleEditPanelController lcontroller = this.controller;
        
        // Normalize command
        SwtContributionItem normalizeAction = new SwtContributionItem();
        normalizeAction.setText(DiagramStyles.I18N.getString("EditStylesDialog.NormalizeButton.label"));
        normalizeAction.setTooltipText(DiagramStyles.I18N.getString("EditStylesDialog.NormalizeButton.tooltip"));
        normalizeAction.setImageDescriptor(getImage(DiagramStyles.I18N.getMessage("EditStylesDialog.NormalizeButton.image")));
        normalizeAction.setAction(() -> lcontroller.onNormalize(getSelection()));
        popupMenu.add(normalizeAction);
        
        // Reset command
        SwtContributionItem resetAction = new SwtContributionItem();
        resetAction.setAction(() -> lcontroller.onReset(getSelection()));
        resetAction.setText(DiagramStyles.I18N.getString("EditStylesDialog.RestoreButton.label"));
        resetAction.setTooltipText(DiagramStyles.I18N.getString("EditStylesDialog.RestoreButton.tooltip"));
        resetAction.setImageDescriptor(getImage(DiagramStyles.I18N.getMessage("EditStylesDialog.RestoreButton.image")));
        popupMenu.add(resetAction);
        
        // Update enablement on menu show
        popupMenu.addMenuListener((IMenuManager manager) -> {
            boolean enabledAndAnyModified = this.selectionComputations.containsModifiedProperties() && this.selectionComputations.containsOnlySymbolViewItems();
        
            // manual updates
            resetAction.setEnabled(enabledAndAnyModified);
            normalizeAction.setEnabled(enabledAndAnyModified);
        
            // force the menu to update from contribution items
            resetAction.update();
            normalizeAction.update();
        });
    }

    /**
     * Get the contextual menu manager.
     * <p>
     * The caller may add entries to the menu.
     * 
     * @return the contextual menu manager.
     */
    @objid ("8cfe4e8e-bbf9-45de-8d8d-404289c46ebc")
    public MenuManager getContextualMenu() {
        return this.contextualMenu;
    }

    @objid ("7f915774-502c-4f39-aedb-e8fca22fc48c")
    IStructuredSelection getSelection() {
        return this.viewer.getStructuredSelection();
    }

    @objid ("ac159987-e20c-44c9-9309-309019fba869")
    public StyleEditPanelSelection getSelectionComputations() {
        return this.selectionComputations;
    }

    @objid ("2a95b2a8-037c-4fec-8b28-e20dbc3cef4f")
    private static ImageDescriptor getImage(String path) {
        return DiagramStyles.getImageDescriptor(path);
    }

}
