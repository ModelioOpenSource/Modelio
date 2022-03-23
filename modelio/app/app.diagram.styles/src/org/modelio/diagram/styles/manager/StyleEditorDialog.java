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
package org.modelio.diagram.styles.manager;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.nebula.jface.tablecomboviewer.TableComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.modelio.diagram.styles.core.FactoryStyle;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.NamedStyle;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.diagram.styles.viewer.StyleEditPanel;
import org.modelio.diagram.styles.viewer.StyleEditPanelUIData;
import org.modelio.platform.core.picking.IModelioPickingService;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.platform.ui.CoreFontRegistry;
import org.modelio.platform.ui.UIImages;
import org.modelio.platform.ui.dialog.ModelioDialog;
import org.modelio.platform.ui.swt.ColoredInputDialog;
import org.modelio.vbasic.files.FileUtils;

/**
 * The style editor dialog is used to edit named styles.
 */
@objid ("85bc6e0a-1926-11e2-92d2-001ec947c8cc")
public class StyleEditorDialog extends ModelioDialog {
    @objid ("85bc6e0d-1926-11e2-92d2-001ec947c8cc")
    private StyleEditPanelUIData model;

    @objid ("28502f9a-33a9-4a20-8a1c-d1ce87d801e0")
    private IModelioPickingService pickingService;

    @objid ("9806ece0-2d56-4a79-a594-4b4eef17cf49")
    private StyleEditPanel styleEditPanel;

    @objid ("b150705f-1b18-4b45-a566-4af8277228b7")
    private SashForm mainSash;

    @objid ("2c08dd23-b7a4-41f8-95d5-57ba6a078384")
    private Button saveButton;

    @objid ("869ec9d8-6f60-4ad7-a1ec-a95a34814b74")
    private Button normButton;

    @objid ("bab397a2-6a88-42f7-a398-7f5b010b5967")
    private Button restoreButton;

    @objid ("1293c8c0-69f7-47c0-a046-4ae3c8c4f1c4")
    private Label title;

    @objid ("66b10fd4-6e88-4564-8717-2e0fda80de36")
    private ColumnViewer styleEditor;

    @objid ("f271a41c-d14a-44d0-b684-277f69c1b0d5")
    private Label infos;

    @objid ("2ce16ca5-5204-4972-9325-acb150d37c9b")
    private static final Image CREATE_STYLE_ICON = DiagramStyles.getImageDescriptor("icons/createstyle.png").createImage();

    @objid ("608d946f-471c-4d57-a607-6f607b2abdbe")
    private static final Image CREATE_THEME_ICON = DiagramStyles.getImageDescriptor("icons/createtheme.png").createImage();

    @objid ("6e5f7b92-22fc-4864-8813-d12c9a383275")
    private static final Image STYLE_ICON = DiagramStyles.getImageDescriptor("icons/elementstyle.png").createImage();

    @objid ("94bf4f72-6cd5-4cf8-a453-c7f5c10b202e")
    private static final Image THEME_ICON = DiagramStyles.getImageDescriptor("icons/diagramtheme.png").createImage();

    @objid ("ddd7d4c2-23ff-43e0-b3b1-1ed2bd7ca0c6")
    private ColumnViewer parentStyleCombo;

    @objid ("f1ce3943-b9e7-4708-86ca-616b9b97b68b")
    private ColumnViewer styleViewer;

    @objid ("242dfba5-655e-4faf-80a1-1dd5864ec84d")
    private Label titleIcon;

    /**
     * C'tor.
     * @param parentShell the parent SWT shell
     * @param pickingService Modelio picking service
     */
    @objid ("85bed06b-1926-11e2-92d2-001ec947c8cc")
    public  StyleEditorDialog(Shell parentShell, IModelioPickingService pickingService) {
        super(parentShell);
        this.pickingService = pickingService;
        
        final NamedStyle editedStyle = new NamedStyle("new style", FactoryStyle.getInstance());
        ISymbolViewModel viewModel = new SharedStyleEditorModel(editedStyle);
        this.model = new StyleEditPanelUIData(viewModel, editedStyle, true);
        
        setBlockOnOpen(false);
        
    }

    @objid ("85bed06f-1926-11e2-92d2-001ec947c8cc")
    @Override
    public Control createContentArea(Composite parent) {
        // The content area is made of
        // - a tree viewer showing the style hierarchy, on the left
        // - a Style editor panel on the right
        // - a StyleViewer table
        // - a description area
        // - a toolbar
        //
        this.mainSash = new SashForm(parent, SWT.HORIZONTAL);
        this.mainSash.setLayout(GridLayoutFactory.fillDefaults().create());
        this.mainSash.setLayoutData(GridDataFactory.defaultsFor(this.mainSash).align(SWT.FILL, SWT.FILL).grab(true, true).create());
        
        // Create the style viewer
        Composite styleViewerPanel = createStyleViewerPanel(this.mainSash);
        styleViewerPanel.setLayoutData(GridDataFactory.defaultsFor(styleViewerPanel).create());
        
        // Create the style editor
        Composite styleEditorPanel = createStyleEditorPanel(this.mainSash);
        styleEditorPanel.setLayoutData(GridDataFactory.defaultsFor(styleEditorPanel).create());
        
        //
        this.styleEditor.addSelectionChangedListener(event -> {
            styleViewerSelectionChanged(SelectionHelper.toList(event.getSelection(), Object.class));
        });
        
        this.mainSash.setWeights(new int[] { 20, 80 });
        return this.mainSash;
    }

    @objid ("85c3951b-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void init() {
        setTitle(DiagramStyles.I18N.getString("EditStylesDialog.SubTitle"));
        setMessage(DiagramStyles.I18N.getString("EditStylesDialog.Message"));
        getShell().setText(DiagramStyles.I18N.getString("EditStylesDialog.Title"));
        
    }

    @objid ("85c3951e-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.CLOSE_LABEL, false);
    }

    /**
     * Set the edited style
     * @param editedStyle the edited style
     */
    @objid ("85c39522-1926-11e2-92d2-001ec947c8cc")
    void onSelectStyle(IStyle editedStyle) {
        if (editedStyle != null) {
            NamedStyle editedNamedStyle = (NamedStyle) editedStyle;
        
            this.model = new StyleEditPanelUIData(new SharedStyleEditorModel(editedNamedStyle), editedNamedStyle, true);
        
            if (editedNamedStyle.isTheme()) {
                this.title.setText(DiagramStyles.I18N.getMessage("EditStylesDialog.CurrentTheme", editedNamedStyle.getName()));
                this.titleIcon.setImage(StyleEditorDialog.THEME_ICON);
            } else {
                this.title.setText(DiagramStyles.I18N.getMessage("EditStylesDialog.CurrentStyle", editedNamedStyle.getName()));
                this.titleIcon.setImage(StyleEditorDialog.STYLE_ICON);
            }
        
            StringBuilder sb = new StringBuilder();
            sb.append(DiagramStyles.I18N.getMessage("EditStylesDialog.Provider", editedNamedStyle.getProvider()));
        
            if (!editedNamedStyle.getApplicability().isEmpty()) {
                sb.append(" [");
                sb.append(editedNamedStyle.getApplicability().stream().collect(Collectors.joining(",")));
                sb.append("]");
            }
        
            this.infos.setText(sb.toString());
        
            // Update the combo's comparator according to the edited style's nature
            ((StyleViewerComparator) this.parentStyleCombo.getComparator()).setThemeFirst(editedNamedStyle.isTheme());
        
            // Filter the edited style from the parent combo, a style can't inherit from itself
            final StyleManager sm = DiagramStyles.getStyleManager();
            this.parentStyleCombo.setInput(sm.getAvailableStyles().stream()
                    .filter(s -> s != editedNamedStyle.getName())
                    .map(styleName -> sm.getStyle(styleName))
                    .collect(Collectors.toList()));
            if (editedNamedStyle.getCascadedStyle() instanceof NamedStyle) {
                this.parentStyleCombo.setSelection(new StructuredSelection((editedNamedStyle.getCascadedStyle())));
            } else {
                this.parentStyleCombo.setSelection(new StructuredSelection());
            }
            this.parentStyleCombo.getControl().setEnabled(!editedNamedStyle.getName().equals(DiagramStyles.ROOT_STYLE_NAME));
        } else {
            this.model = new StyleEditPanelUIData();
            this.title.setText(" ");
            this.infos.setText(" ");
            this.parentStyleCombo.setSelection(new StructuredSelection());
        }
        
        if (this.styleEditor != null) {
            if (this.styleEditor instanceof TreeViewer) {
                TreeViewer treeViewer = (TreeViewer) this.styleEditor;
                final Object[] expandedCategories = treeViewer.getExpandedElements();
        
                // change the data model, this will collapse all categories which is not user friendly :(
                this.styleEditPanel.setInput(this.model);
        
                // try to expand the categories that were previously expanded => this is user friendly :)
                treeViewer.getTree().setRedraw(false);
                for (final Object o : expandedCategories) {
                    treeViewer.setExpandedState(o, true);
                }
                treeViewer.getTree().setRedraw(true);
            } else {
                this.styleEditPanel.setInput(this.model);
            }
        }
        
    }

    @objid ("85c39525-1926-11e2-92d2-001ec947c8cc")
    @Override
    public boolean close() {
        onSelectStyle(null);
        return super.close();
    }

    @objid ("85c3952a-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected Point getInitialSize() {
        return new Point(800, 800);
    }

    /**
     * This method is called by the selection change listener (StyleViewerSelectionChangedListener) of the style viewer when the selection changes in it. The method updates the buttons status (enabled/disabled) along with the description field displayed
     * text.
     */
    @objid ("85c3952f-1926-11e2-92d2-001ec947c8cc")
    protected void styleViewerSelectionChanged(final List<Object> selectedElements) {
        boolean onlyStyleKeys = true;
        
        for (final Object o : selectedElements) {
            onlyStyleKeys &= (o instanceof ISymbolViewItem) && ((ISymbolViewItem) o).getStyleKey() != null;
        }
        
        if (onlyStyleKeys) {
            if (selectedElements.isEmpty()) {
                this.restoreButton.setEnabled(false);
        
            } else if (selectedElements.size() == 1) {
                this.restoreButton.setEnabled(true);
        
            } else {
                this.restoreButton.setEnabled(true);
        
            }
        } else {
            this.restoreButton.setEnabled(false);
        
        }
        
    }

    @objid ("85c39536-1926-11e2-92d2-001ec947c8cc")
    private Composite createStyleEditorPanel(final Composite parent) {
        final Composite composite = new Composite(parent, SWT.BORDER);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());
        
        this.titleIcon = new Label(composite, SWT.NONE);
        this.titleIcon.setImage(StyleEditorDialog.STYLE_ICON);
        this.titleIcon.setLayoutData(GridDataFactory.defaultsFor(this.titleIcon).align(SWT.FILL, SWT.FILL).grab(false, false).create());
        
        this.title = new Label(composite, SWT.NONE);
        this.title.setText("");
        // Use a big Font for title
        this.title.setFont(CoreFontRegistry.getModifiedFont(this.title.getFont(), SWT.NONE, 1.4f));
        this.title.setLayoutData(GridDataFactory.defaultsFor(this.title).align(SWT.FILL, SWT.FILL).grab(true, false).span(2, 1).create());
        
        this.infos = new Label(composite, SWT.NONE);
        this.infos.setText("");
        this.infos.setFont(CoreFontRegistry.getModifiedFont(this.infos.getFont(), SWT.ITALIC, 0.8f));
        this.infos.setLayoutData(GridDataFactory.defaultsFor(this.infos).align(SWT.FILL, SWT.FILL).grab(true, false).span(3, 1).indent(20, 0).create());
        
        // Add the parent style combo
        Label parentStyleLabel = new Label(composite, SWT.NONE);
        parentStyleLabel.setText(DiagramStyles.I18N.getString("EditStylesDialog.ParentStyleCombo.label"));
        parentStyleLabel.setLayoutData(GridDataFactory.defaultsFor(parentStyleLabel).align(SWT.FILL, SWT.FILL).span(2, 1).indent(5, 5).create());
        
        this.parentStyleCombo = new TableComboViewer(composite);
        this.parentStyleCombo.getControl().setLayoutData(GridDataFactory.defaultsFor(this.parentStyleCombo.getControl()).align(SWT.FILL, SWT.FILL).grab(true, false).hint(SWT.DEFAULT, UIImages.PLACEHOLDER.getImageData().height).create());
        this.parentStyleCombo.getControl().setToolTipText(DiagramStyles.I18N.getString("EditStylesDialog.ParentStyleCombo.tooltip"));
        this.parentStyleCombo.setContentProvider(new ArrayContentProvider());
        this.parentStyleCombo.setLabelProvider(new StyleLabelProvider());
        this.parentStyleCombo.setComparator(new StyleViewerComparator(false));
        this.parentStyleCombo.addSelectionChangedListener(event -> {
            StyleManager styleManager = DiagramStyles.getStyleManager();
            // hack : seems this.model.getStyleData() may be a duplicated of an existing managed NamedStyle, lookup by name
            NamedStyle editedStyle = this.model.getStyleData() != null ? styleManager.findStyle(((NamedStyle) this.model.getStyleData()).getName()) : null;
            NamedStyle parentStyle = SelectionHelper.getFirst(this.parentStyleCombo.getSelection(), NamedStyle.class);
            if (editedStyle != null && parentStyle != null && parentStyle != editedStyle.getCascadedStyle()) {
                editedStyle.setCascadedStyle(parentStyle);
                this.styleViewer.refresh();
                this.styleViewer.setSelection(new StructuredSelection(editedStyle), true);
            }
        });
        
        // Add the style viewer table
        this.styleEditPanel = StyleEditPanel.newTreePanel();
        this.styleEditPanel.createPanel(composite);
        this.styleEditor = this.styleEditPanel.getViewer();
        this.styleEditPanel.getPanel().setLayoutData(GridDataFactory.defaultsFor(this.styleEditPanel.getPanel()).align(SWT.FILL, SWT.FILL).grab(true, true).span(3, 1).create());
        
        // Add the toolbar
        final Composite editionToolbar = createStyleEditorToolBar(composite);
        editionToolbar.setLayoutData(GridDataFactory.defaultsFor(editionToolbar).align(SWT.FILL, SWT.FILL).grab(true, false).span(3, 1).create());
        return composite;
    }

    @objid ("85c3953c-1926-11e2-92d2-001ec947c8cc")
    private Composite createStyleViewerPanel(final Composite parent) {
        Composite styleViewerPanel = new Composite(parent, SWT.BORDER);
        styleViewerPanel.setLayout(GridLayoutFactory.swtDefaults().spacing(0, 2).margins(2, 2).create());
        
        Composite toolbar = createStyleViewerToolBar(styleViewerPanel);
        toolbar.setLayoutData(GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.FILL).grab(true, false).create());
        
        // Style tree viewer
        this.styleViewer = new TableViewer(styleViewerPanel, SWT.BORDER);
        this.styleViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        this.styleViewer.setContentProvider(new StyleContentProvider());
        this.styleViewer.setComparator(new StyleViewerComparator(true));
        this.styleViewer.setLabelProvider(new StyleLabelProvider());
        this.styleViewer.setInput(DiagramStyles.getStyleManager());
        
        this.styleViewer.addSelectionChangedListener(event -> {
            onSelectStyle(SelectionHelper.getFirst(event.getSelection(), IStyle.class));
        });
        
        // Handle cascaded style change by drag & drop
        final Transfer[] transferTypes = new Transfer[] { TextTransfer.getInstance() };
        this.styleViewer.addDragSupport(DND.DROP_MOVE, transferTypes, new DragSourceListener() {
            @Override
            public void dragStart(DragSourceEvent event) {
                event.doit = (SelectionHelper.size(StyleEditorDialog.this.styleViewer.getSelection()) == 1);
            }
        
            @Override
            public void dragSetData(DragSourceEvent event) {
                final IStyle draggedStyle = SelectionHelper.getFirst(StyleEditorDialog.this.styleViewer.getSelection(), IStyle.class);
                if (draggedStyle != null) {
                    if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
                        event.data = ((NamedStyle) draggedStyle).getName();
                    }
                }
            }
        
            @Override
            public void dragFinished(DragSourceEvent event) {
                // nothing to do
            }
        });
        this.styleViewer.addDropSupport(DND.DROP_MOVE, transferTypes, new ViewerDropAdapter(this.styleViewer) {
            @Override
            public boolean performDrop(Object data) {
                final StyleManager styleManager = DiagramStyles.getStyleManager();
                final IStyle droppedStyle = styleManager.getStyle((String) data);
                final IStyle targetStyle = getTargetStyle(getCurrentTarget());
        
                droppedStyle.setCascadedStyle(targetStyle);
                StyleEditorDialog.this.styleViewer.refresh();
                StyleEditorDialog.this.styleViewer.setSelection(new StructuredSelection(droppedStyle));
                return true;
            }
        
            /**
             * @return <code>true</code> if the hovered target style is different than the dropped style and its current cascaded style.
             */
            @Override
            public boolean validateDrop(Object target, int operation, TransferData transferType) {
                final IStyle droppedStyle = (IStyle) getSelectedObject();
                final IStyle targetStyle = getTargetStyle(target);
                return droppedStyle != null && targetStyle != null && !targetStyle.equals(droppedStyle) && !targetStyle.equals(droppedStyle.getCascadedStyle()) && !(targetStyle instanceof FactoryStyle);
            }
        
            /**
             * @return the style hovered by the mouse, taking the location into account.
             */
            private IStyle getTargetStyle(Object target) {
                if (target == null) {
                    return null;
                }
                switch (getCurrentLocation()) {
                case LOCATION_AFTER:
                case LOCATION_BEFORE:
                    return ((IStyle) target).getCascadedStyle();
                case LOCATION_ON:
                    return ((IStyle) target);
                case LOCATION_NONE:
                default:
                    return null;
                }
            }
        });
        return styleViewerPanel;
    }

    @objid ("85c39542-1926-11e2-92d2-001ec947c8cc")
    private Composite createStyleEditorToolBar(final Composite styleEditorPanel) {
        // Create editor toolbar and buttons
        // Catalog buttons
        final Composite editionToolbar = new Composite(styleEditorPanel, SWT.NONE);
        editionToolbar.setLayout(new RowLayout(SWT.HORIZONTAL));
        
        // "Save" button
        this.saveButton = new Button(editionToolbar, SWT.PUSH);
        this.saveButton.setText(DiagramStyles.I18N.getString("EditStylesDialog.SaveButton.label"));
        this.saveButton.setToolTipText(DiagramStyles.I18N.getString("EditStylesDialog.SaveButton.tooltip"));
        this.saveButton.addListener(SWT.Selection, e -> {
            StyleEditPanelUIData uiData = getUiData();
            if (uiData != null) {
                DiagramStyles.getStyleManager().save((NamedStyle) uiData.getStyleData());
            }
        });
        
        // "Normalize" button
        this.normButton = new Button(editionToolbar, SWT.PUSH);
        this.normButton.setText(DiagramStyles.I18N.getString("EditStylesDialog.NormalizeButton.label"));
        this.normButton.setToolTipText(DiagramStyles.I18N.getString("EditStylesDialog.NormalizeButton.tooltip"));
        this.normButton.addListener(SWT.Selection, e -> {
            StyleEditPanelUIData uiData = getUiData();
            if (uiData != null) {
                uiData.getStyleData().normalize();
            }
        });
        
        // "Restore" button
        this.restoreButton = new Button(editionToolbar, SWT.PUSH);
        this.restoreButton.setText(DiagramStyles.I18N.getString("EditStylesDialog.RestoreButton.label"));
        this.restoreButton.setToolTipText(DiagramStyles.I18N.getString("EditStylesDialog.RestoreButton.tooltip"));
        this.restoreButton.addListener(SWT.Selection, e -> {
            StyleEditPanelUIData uiData = getUiData();
            if (uiData != null) {
                final IStyle editedStyle = uiData.getStyleData();
                for (final ISymbolViewItem o : SelectionHelper.toList(this.styleEditor.getSelection(), ISymbolViewItem.class)) {
                    if (o.getStyleKey() != null) {
                        editedStyle.removeProperty(o.getStyleKey());
                    }
                }
            }
        });
        return editionToolbar;
    }

    @objid ("734e7137-f980-4c7b-9976-f096154d761a")
    private StyleEditPanelUIData getUiData() {
        return this.styleEditPanel.getInput();
    }

    @objid ("25760040-4da0-43ed-9253-29bf3b0ea3bb")
    private Composite createStyleViewerToolBar(Composite styleViewerPanel) {
        // Button bar
        final ToolBar toolbar = new ToolBar(styleViewerPanel, SWT.HORIZONTAL);
        
        // Fill the toolbar
        ToolItem createThemeButton = new ToolItem(toolbar, SWT.FLAT);
        createThemeButton.setImage(StyleEditorDialog.CREATE_THEME_ICON);
        createThemeButton.setToolTipText(DiagramStyles.I18N.getString("EditStylesDialog.CreateThemeButton.tooltip"));
        createThemeButton.addListener(SWT.Selection, (e) -> {
            onCreateStyle(true);
        });
        
        ToolItem createStyleButton = new ToolItem(toolbar, SWT.FLAT);
        createStyleButton.setImage(StyleEditorDialog.CREATE_STYLE_ICON);
        createStyleButton.setToolTipText(DiagramStyles.I18N.getString("EditStylesDialog.CreateStyleButton.tooltip"));
        createStyleButton.addListener(SWT.Selection, (e) -> {
            onCreateStyle(false);
        });
        
        ToolItem importStyleButton = new ToolItem(toolbar, SWT.FLAT);
        importStyleButton.setImage(UIImages.FILECHOOSE);
        importStyleButton.setToolTipText(DiagramStyles.I18N.getString("EditStylesDialog.ImportStyleButton.tooltip"));
        importStyleButton.addListener(SWT.Selection, (e) -> {
            onImportStyle();
        });
        return toolbar;
    }

    @objid ("2e2afe11-fbef-4fe9-8f36-2200024b99c8")
    private void onCreateStyle(boolean isTheme) {
        String suffix = isTheme ? ".Theme" : ".Style";
        
        NamedStyle parentStyle = getSelectedStyle();
        if (parentStyle == null) {
            parentStyle = isTheme ? DiagramStyles.getStyleManager().getDefaultTheme() : DiagramStyles.getStyleManager().getDefaultStyle();
        }
        
        final IInputValidator validator = (String newText) -> {
            // Check name is valid and unique
            if (!NamedStyle.isValidName(newText)) {
                return DiagramStyles.I18N.getMessage("$CreateStyleDialog.Error.BadStyleName" + suffix, NamedStyle.NAME_PATTERN.pattern());
            } else if (DiagramStyles.getStyleManager().findStyle(newText) != null) {
                return DiagramStyles.I18N.getMessage("CreateStyleDialog.Error.DuplicateStyleName", newText);
            }
            return null;
        };
        
        final ColoredInputDialog dlg = new ColoredInputDialog(
                this.styleViewer.getControl().getShell(),
                DiagramStyles.I18N.getMessage("$CreateStyleDialog.Title" + suffix, parentStyle.getName()),
                DiagramStyles.I18N.getString("$CreateStyleDialog.Prompt" + suffix),
                DiagramStyles.I18N.getString("$CreateStyleDialog.DefaultName" + suffix),
                validator);
        
        dlg.open();
        
        final String name = dlg.getValue();
        if (name == null) {
            return;
        }
        
        final NamedStyle newStyle = DiagramStyles.getStyleManager().createStyle(name, parentStyle.getName(), isTheme);
        if (newStyle != null) {
            this.styleViewer.refresh();
            this.styleViewer.setSelection(new StructuredSelection(newStyle), true);
        }
        
    }

    @objid ("2c49f859-133c-428e-a63c-697eb2ba240b")
    private NamedStyle getSelectedStyle() {
        return SelectionHelper.getFirst(this.styleViewer.getSelection(), NamedStyle.class);
    }

    @objid ("b8b7cbfa-4c33-4ce2-9c61-404da17e70bf")
    private void onImportStyle() {
        Shell shell = getParentShell();
        FileDialog dialog = new FileDialog(shell, SWT.OPEN);
        dialog.setText(DiagramStyles.I18N.getString("ImportStylesDialog.Title"));
        String[] ext = { "*.style;*.theme", "*.theme", "*.style" };
        String[] extNames = { DiagramStyles.I18N.getString("ImportStylesDialog.ThemeStyle.extension"), DiagramStyles.I18N.getString("ImportStylesDialog.Theme.extension"), DiagramStyles.I18N.getString("ImportStylesDialog.Style.extension") };
        dialog.setFilterNames(extNames);
        dialog.setFilterExtensions(ext);
        String fileName = dialog.open();
        
        if (fileName != null && !fileName.isEmpty()) {
            File file = new File(fileName);
            if (file.exists() && file.isFile()) {
                try {
                    NamedStyle importedStyle = DiagramStyles.getStyleManager().importStyle(file.toPath());
                    if (importedStyle != null) {
                        this.styleViewer.refresh();
                        this.styleViewer.setSelection(new StructuredSelection(importedStyle), true);
                    }
                } catch (FileAlreadyExistsException e) {
                    MessageDialog.openError(shell,
                            DiagramStyles.I18N.getString("ImportStylesDialog.Error"),
                            DiagramStyles.I18N.getMessage("ImportStylesDialog.FileAlreadyExistsException", file.getName(), FileUtils.getLocalizedMessage(e)));
                } catch (IOException e) {
                    MessageDialog.openError(shell,
                            DiagramStyles.I18N.getString("ImportStylesDialog.Error"),
                            DiagramStyles.I18N.getMessage("ImportStylesDialog.IOException", file.getName(), FileUtils.getLocalizedMessage(e)));
                }
            }
        }
        
    }

    @objid ("85c5f78e-1926-11e2-92d2-001ec947c8cc")
    private static class StyleLabelProvider extends LabelProvider {
        @objid ("85c5f78f-1926-11e2-92d2-001ec947c8cc")
        @Override
        public String getText(Object element) {
            if (element instanceof NamedStyle) {
                return ((NamedStyle) element).getName();
            } else {
                return super.getText(element);
            }
            
        }

        @objid ("ce754385-7e5c-44fa-bd94-3033fad528d7")
        @Override
        public Image getImage(Object element) {
            if (element instanceof NamedStyle) {
                return ((NamedStyle) element).isTheme() ? StyleEditorDialog.THEME_ICON : StyleEditorDialog.STYLE_ICON;
            } else {
                return super.getImage(element);
            }
            
        }

    }

    @objid ("e402e5f8-e637-47d5-b7fe-612df3357216")
    private static class StyleContentProvider implements IStructuredContentProvider {
        @objid ("157ea4b8-5fd7-4aba-ae70-5bd753da2297")
        @Override
        public Object[] getElements(Object inputElement) {
            final StyleManager sm = (StyleManager) inputElement;
            return sm.getAvailableStyles().stream()
                    .map(styleName -> sm.getStyle(styleName))
                    .collect(Collectors.toList()).toArray();
            
        }

    }

    @objid ("a0bfad2c-adbb-4557-a2d1-80ea0c37ecb1")
    private static class StyleViewerComparator extends ViewerComparator {
        @objid ("bbb7c7d9-1d62-40e6-b0b0-87ce39dcaad5")
        private boolean areThemeFirst;

        @objid ("e2a69e22-0bdf-4f25-a547-feac61846a4c")
        @Override
        public int category(Object element) {
            if (element instanceof NamedStyle) {
                if (((NamedStyle) element).isTheme()) {
                    return this.areThemeFirst ? 0 : 1;
                } else {
                    return this.areThemeFirst ? 1 : 0;
                }
            } else {
                return 2;
            }
            
        }

        @objid ("727877b6-ad50-460c-b6f2-ff5f7b0ac96b")
        public  StyleViewerComparator(boolean areThemeFirst) {
            this.areThemeFirst = areThemeFirst;
        }

        @objid ("3de59475-8e4a-4413-8d2b-3287c5dbf532")
        public void setThemeFirst(boolean areThemeFirst) {
            this.areThemeFirst = areThemeFirst;
        }

    }

}
