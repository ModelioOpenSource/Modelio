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

package org.modelio.edition.dialogs.dialog.panels.operation.params;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.modelio.core.ui.MetamodelLabels;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.core.ui.treetable.combo.EnumComboBoxCellEditor;
import org.modelio.core.ui.treetable.combo.LabelsComboBoxCellEditor;
import org.modelio.core.ui.treetable.element.SingleElementCellEditor;
import org.modelio.edition.dialogs.dialog.panels.operation.IOperationPropertyModel;
import org.modelio.edition.dialogs.plugin.EditionDialogs;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.PassingMode;
import org.modelio.ui.UIImages;
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * Reusable panel to edit operation's parameters in a table.
 * <p>
 * Needs an {@link IOperationPropertyModel} as input.
 * </p>
 */
@objid ("45eb4159-1cae-4674-a4c2-6b2937b1a0e4")
public class OperationParametersPanel implements IPanelProvider {
    @objid ("e53db672-c727-429d-8d0e-19aa47a790b8")
    private static final String[] CARD_VALUES = new String[] { "0..1", "1", "0..*" };

    @objid ("e1697af3-bef1-4d54-93db-99c023c97089")
    private IOperationPropertyModel opModel;

    @objid ("eb2d77ee-14d9-4e9f-a6b5-d02627b604e3")
    private TableViewer parametersTable;

    @objid ("7e88b33a-8be6-42d0-86ee-eb6f40032313")
    private Composite container;

    @objid ("efd07d51-de19-41ca-bb78-50e0bb9e680a")
    private Button addParameterButton;

    @objid ("7f3598c4-b73c-40ec-824d-97b6c2773912")
    private Button addReturnParameterButton;

    @objid ("303411be-6ab4-4619-b479-ad5e57bd2fd7")
    private Button removeButton;

    @objid ("9bd7881c-0b07-49f3-af5b-9f193182ef96")
    private Button moveUpButton;

    @objid ("43e6abc5-835e-421a-ab82-ff0d01326b3e")
    private Button moveDownButton;

    @objid ("4fdfd999-5f32-4c76-bf60-af79d8cede27")
    private static final Image CREATEPARAMETER_ICON = EditionDialogs.getImageDescriptor("icons/createparameter.png").createImage();

    @objid ("1442becc-30bc-42cb-bea1-de6f900d2a56")
    private static final Image CREATERETURNPARAMETER_ICON = EditionDialogs.getImageDescriptor("icons/createreturnparameter.png").createImage();

    @objid ("f54a3ef9-d532-4722-bf89-18bf08b5a725")
    @Override
    public Object createPanel(final Composite parent) {
        this.container = new Composite(parent, SWT.BORDER);
        final GridLayout gl_parametersArea = new GridLayout(1, false);
        gl_parametersArea.verticalSpacing = 2;
        gl_parametersArea.marginWidth = 2;
        gl_parametersArea.marginHeight = 2;
        this.container.setLayout(gl_parametersArea);
        
        final Composite toolbarArea = new Composite(this.container, SWT.NONE);
        final GridLayout gl_labelArea = new GridLayout(6, false);
        gl_labelArea.marginHeight = 2;
        gl_labelArea.marginWidth = 2;
        gl_labelArea.horizontalSpacing = 2;
        toolbarArea.setLayout(gl_labelArea);
        toolbarArea.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        
        final Label parametersPropertiesLabel = new Label(toolbarArea, SWT.NO_REDRAW_RESIZE);
        parametersPropertiesLabel.setText(EditionDialogs.I18N.getString("OperationEditPanel.opParameters"));
        final GridData gdParametersPropertiesLabel = new GridData(SWT.FILL, SWT.CENTER, true, true);
        parametersPropertiesLabel.setLayoutData(gdParametersPropertiesLabel);
        
        // The Add parameter button
        this.addParameterButton = new Button(toolbarArea, SWT.FLAT);
        this.addParameterButton.setImage(OperationParametersPanel.CREATEPARAMETER_ICON);
        this.addParameterButton.setEnabled(true);
        this.addParameterButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
        this.addParameterButton.setToolTipText(EditionDialogs.I18N.getString("OperationEditPanel.addParameterButton.tooltip"));
        this.addParameterButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                addParameter();
            }
        });
        
        // The Add return parameter button
        this.addReturnParameterButton = new Button(toolbarArea, SWT.FLAT);
        this.addReturnParameterButton.setImage(OperationParametersPanel.CREATERETURNPARAMETER_ICON);
        this.addReturnParameterButton.setEnabled(true);
        this.addReturnParameterButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
        this.addReturnParameterButton.setToolTipText(EditionDialogs.I18N.getString("OperationEditPanel.addReturnParameterButton.tooltip"));
        this.addReturnParameterButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                addReturnParameter();
            }
        });
        
        // The Remove parameter button
        this.removeButton = new Button(toolbarArea, SWT.FLAT);
        this.removeButton.setImage(UIImages.DELETE);
        this.removeButton.setEnabled(false);
        this.removeButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
        this.removeButton.setToolTipText(EditionDialogs.I18N.getString("OperationEditPanel.removeParameterButton.tooltip"));
        this.removeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                removeSelectedParameters();
            }
        
        });
        
        // The move parameter up button
        this.moveUpButton = new Button(toolbarArea, SWT.FLAT);
        this.moveUpButton.setImage(UIImages.UPARROW);
        this.moveUpButton.setEnabled(false);
        this.moveUpButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
        this.moveUpButton.setToolTipText(EditionDialogs.I18N.getString("OperationEditPanel.moveParameterUpButton.tooltip"));
        this.moveUpButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                moveUpSelectedParameters();
            }
        
        });
        
        // The move parameter down button
        this.moveDownButton = new Button(toolbarArea, SWT.FLAT);
        this.moveDownButton.setImage(UIImages.DOWNARROW);
        this.moveDownButton.setEnabled(false);
        this.moveDownButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
        this.moveDownButton.setToolTipText(EditionDialogs.I18N.getString("OperationEditPanel.moveParameterDownButton.tooltip"));
        this.moveDownButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                moveDownSelectedParameters();
            }
        });
        
        // The parameters table itself
        this.parametersTable = new TableViewer(this.container, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        final Table table = this.parametersTable.getTable();
        
        // Initialize columns
        createColumns(this.parametersTable);
        
        // Set the content provider
        this.parametersTable.setContentProvider(ArrayContentProvider.getInstance());
        this.parametersTable.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        this.parametersTable.getTable().addKeyListener(new KeyListener() {
        
            @Override
            public void keyReleased(KeyEvent e) {
            }
        
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.keyCode == SWT.DEL) {
                    removeSelectedParameters();
                } else if (e.keyCode == 'u' && (e.stateMask & SWT.CTRL) == SWT.CTRL) {
                    moveUpSelectedParameters();
                } else if (e.keyCode == 'd' && (e.stateMask & SWT.CTRL) == SWT.CTRL) {
                    moveDownSelectedParameters();
                }
        
            }
        });
        
        // GTK3 workaround: table headers wrecks down cell editors on first table row !!!
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            table.setHeaderVisible(true);
        }
        table.setLinesVisible(true);
        return this.container;
    }

    @objid ("34fd7c27-9d5d-4eba-83d6-251e8e8de620")
    @Override
    public Object getPanel() {
        return this.container;
    }

    @objid ("f23dec76-17f7-455e-b13b-0600c192ca39")
    @Override
    public void setInput(final Object input) {
        if (input instanceof IOperationPropertyModel) {
            this.opModel = (IOperationPropertyModel) input;
            this.parametersTable.setInput(this.opModel.getParameters());
        } else {
            this.opModel = null;
            this.parametersTable.setInput(null);
        }
        
        setReadOnly(this.opModel != null && !this.opModel.isModifiable());
    }

    @objid ("b52eca23-74c6-4f49-afbc-bdc86f9bc2f8")
    @Override
    public Object getInput() {
        return this.opModel;
    }

    @objid ("35466d65-d706-48b7-b7ce-d011e7f4089a")
    IOperationPropertyModel getOperationModel() {
        return this.opModel;
    }

    /**
     * Get the list of the parameters currently selected in the parameters table.
     * @return
     */
    @objid ("05c8302a-c030-473f-a2e1-bbc5618ff99b")
    private List<Parameter> getSelectedParameters() {
        final List<Parameter> selectedParameters = new ArrayList<>();
        final IStructuredSelection selection = this.parametersTable.getStructuredSelection();
        for (final ParameterPropertyModel pm : SelectionHelper.toList(selection, ParameterPropertyModel.class)) {
            selectedParameters.add(pm.getParameter());
        }
        return selectedParameters;
    }

    @objid ("c7d7d3d9-dbfe-4a63-93a9-7455569c9432")
    private void clearParametersSelection() {
        this.parametersTable.setSelection(null);
    }

    @objid ("36d65254-afef-4ba7-af10-76337e13cd1c")
    private void selectParameters(final List<Parameter> selectedParameters) {
        final List<ParameterPropertyModel> currentInput = (List<ParameterPropertyModel>) this.parametersTable.getInput();
        final List<ParameterPropertyModel> selection = new ArrayList<>();
        for (final ParameterPropertyModel pm : currentInput) {
            if (selectedParameters.contains(pm.getParameter())) {
                selection.add(pm);
            }
        }
        this.parametersTable.setSelection(new StructuredSelection(selection));
    }

    @objid ("e69e8154-7f2c-4214-a4db-662087260e83")
    private void update() {
        this.parametersTable.refresh();
    }

    @objid ("ce854905-daf7-481e-aaa5-4f9b04b7061e")
    private void setReadOnly(final boolean ro) {
        this.addParameterButton.setEnabled(!ro);
        
        this.removeButton.setEnabled(!ro);
        this.moveDownButton.setEnabled(!ro);
        this.moveUpButton.setEnabled(!ro);
        this.parametersTable.getTable().setEnabled(!ro);
        
        final boolean returnParameterExists = !getCurrentParameters().isEmpty()
                && getCurrentParameters().get(0).isReturn();
        this.addReturnParameterButton.setEnabled(!ro && !returnParameterExists);
    }

    @objid ("841bdf05-2957-4ec7-9433-3803d7e9c446")
    private void addParameter() {
        getOperationModel().addParameter();
        
        final List<ParameterPropertyModel> parameters = getOperationModel().getParameters();
        this.parametersTable.setInput(parameters);
        this.parametersTable.setSelection(new StructuredSelection(parameters.get(parameters.size() - 1)));
    }

    @objid ("a44982ed-1691-4bfd-8cae-fff4004735db")
    private void moveDownSelectedParameters() {
        final List<Parameter> selectedParameters = getSelectedParameters();
        this.opModel.moveParametersDown(selectedParameters);
        selectParameters(selectedParameters);
    }

    @objid ("7a1c9d9e-2adc-401d-bd82-386bb6d9e80d")
    private void moveUpSelectedParameters() {
        final List<Parameter> selectedParameters = getSelectedParameters();
        this.opModel.moveParametersUp(selectedParameters);
        selectParameters(selectedParameters);
    }

    @objid ("12b29b49-f601-4c11-916a-95fe550e27c8")
    private void removeSelectedParameters() {
        this.opModel.removeParameter(getSelectedParameters());
        final List<ParameterPropertyModel> parameters = getOperationModel().getParameters();
        this.parametersTable.setInput(parameters);
        if (!parameters.isEmpty()) {
            this.parametersTable.setSelection(new StructuredSelection(parameters.get(0)));
        }
    }

    @objid ("1e020e94-21d2-4ffb-adf7-a70211984176")
    @Override
    public boolean isRelevantFor(final Object obj) {
        return obj instanceof IOperationPropertyModel;
    }

    @objid ("db2ab7eb-3212-478a-b2a7-7f191e68ab1d")
    @Override
    public String getHelpTopic() {
        return null;
    }

    @objid ("3bc6075a-4bc0-4e4d-8b22-59bddbbd7be7")
    @Override
    public void dispose() {
        // nothing to do
    }

    @objid ("7898be5d-fd9d-48d4-ace5-cd0e90ddd7e1")
    private void createColumns(final TableViewer viewer) {
        createIconColumn(viewer);
        createNameColumn(viewer);
        createTypeColumn(viewer);
        createCardColumn(viewer);
        createPassingModeColumn(viewer);
        createDefaultValueColumn(viewer);
    }

    @objid ("5fd3477b-f5e9-4ad5-ac6a-8b1c0ae02555")
    private void createDefaultValueColumn(final TableViewer viewer) {
        // create a column for the parameter Value
        final TableViewerColumn valueColumn = new TableViewerColumn(viewer, SWT.NONE);
        
        valueColumn.getColumn().setWidth(200);
        valueColumn.getColumn().setText(EditionDialogs.I18N.getString("OperationEditPanel.pValue"));
        valueColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(final Object element) {
                final ParameterPropertyModel m = (ParameterPropertyModel) element;
                return m.getDefaultValue();
            }
        });
        valueColumn.setEditingSupport(new EditingSupport(this.parametersTable) {
            private final CellEditor editor = new TextCellEditor(OperationParametersPanel.this.parametersTable.getTable());
        
            @Override
            protected CellEditor getCellEditor(final Object element) {
                return this.editor;
            }
        
            @Override
            protected boolean canEdit(final Object element) {
                final ParameterPropertyModel m = (ParameterPropertyModel) element;
                return m.isReturn() == false;
            }
        
            @Override
            protected Object getValue(final Object element) {
                final ParameterPropertyModel m = (ParameterPropertyModel) element;
                return m.getDefaultValue();
            }
        
            @Override
            protected void setValue(final Object element, final Object value) {
                final ParameterPropertyModel m = (ParameterPropertyModel) element;
                m.setDefaultValue((String) value);
            }
        
        });
    }

    @objid ("e1794fe9-a10e-4de8-b641-c4b5faaeb0c1")
    private void createPassingModeColumn(final TableViewer viewer) {
        // create a column for the parameter Passing mode
        final TableViewerColumn passingColumn = new TableViewerColumn(viewer, SWT.NONE);
        
        passingColumn.getColumn().setWidth(100);
        passingColumn.getColumn().setText(EditionDialogs.I18N.getString("OperationEditPanel.pPassing"));
        passingColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(final Object element) {
                final ParameterPropertyModel m = (ParameterPropertyModel) element;
                final PassingMode mode = m.getPassing();
                if (mode != null) {
                    return MetamodelLabels.getString(mode.toString());
                } else {
                    return "";
                }
            }
        });
        
        passingColumn.setEditingSupport(new EditingSupport(this.parametersTable) {
            private final EnumComboBoxCellEditor editor = new EnumComboBoxCellEditor(OperationParametersPanel.this.parametersTable.getTable(), PassingMode.class, SWT.SINGLE);
        
            @Override
            protected CellEditor getCellEditor(final Object element) {
                return this.editor;
            }
        
            @Override
            protected boolean canEdit(final Object element) {
                final ParameterPropertyModel m = (ParameterPropertyModel) element;
                return !m.isReturn();
            }
        
            @Override
            protected Object getValue(final Object element) {
                final ParameterPropertyModel m = (ParameterPropertyModel) element;
                return m.getPassing();
            }
        
            @Override
            protected void setValue(final Object element, final Object value) {
                final ParameterPropertyModel m = (ParameterPropertyModel) element;
                m.setPassingMode((PassingMode) value);
            }
        
        });
    }

    @objid ("4d6a2b9a-5601-41b4-a83e-c2aa4fa70944")
    private void createCardColumn(final TableViewer viewer) {
        // create a column for the parameter Card
        final TableViewerColumn cardColumn = new TableViewerColumn(viewer, SWT.NONE);
        
        cardColumn.getColumn().setWidth(80);
        cardColumn.getColumn().setText(EditionDialogs.I18N.getString("OperationEditPanel.pCard"));
        cardColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(final Object element) {
                final ParameterPropertyModel m = (ParameterPropertyModel) element;
                return m.getCard();
            }
        });
        
        cardColumn.setEditingSupport(new EditingSupport(this.parametersTable) {
            private final LabelsComboBoxCellEditor editor = new LabelsComboBoxCellEditor(OperationParametersPanel.this.parametersTable.getTable(), OperationParametersPanel.CARD_VALUES, true, SWT.DROP_DOWN | SWT.SINGLE | SWT.V_SCROLL | SWT.H_SCROLL);
        
            @Override
            protected CellEditor getCellEditor(final Object element) {
                final ParameterPropertyModel m = (ParameterPropertyModel) element;
                final List<String> items = new ArrayList<>(Arrays.asList(OperationParametersPanel.CARD_VALUES));
                if (!items.contains(m.getCard())) {
                    items.add(m.getCard());
                }
                this.editor.setItems(items.toArray(new String[1]));
                return this.editor;
            }
        
            @Override
            protected boolean canEdit(final Object element) {
                return true;
            }
        
            @Override
            protected Object getValue(final Object element) {
                final ParameterPropertyModel m = (ParameterPropertyModel) element;
                return m.getCard();
            }
        
            @Override
            protected void setValue(final Object element, final Object value) {
                final ParameterPropertyModel m = (ParameterPropertyModel) element;
                m.setCard((String) value);
            }
        
        });
    }

    @objid ("4a4a1e25-32a0-475e-8013-8db8b1734241")
    private void createTypeColumn(final TableViewer viewer) {
        // create a column for the parameter Type
        final TableViewerColumn typeColumn = new TableViewerColumn(viewer, SWT.NONE);
        
        typeColumn.getColumn().setWidth(180);
        typeColumn.getColumn().setText(EditionDialogs.I18N.getString("OperationEditPanel.pType"));
        typeColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(final Object element) {
                final ParameterPropertyModel m = (ParameterPropertyModel) element;
                return m.getType() != null ? m.getType().getName() : "null";
            }
        });
        
        typeColumn.setEditingSupport(new EditingSupport(this.parametersTable) {
            private final SingleElementCellEditor editor = new SingleElementCellEditor(OperationParametersPanel.this.parametersTable.getTable());
        
            @Override
            protected CellEditor getCellEditor(final Object element) {
        
                final ParameterPropertyModel m = (ParameterPropertyModel) element;
                final Parameter p = m.getParameter();
        
                final MClass metaclass = p.getMClass().getMetamodel().getMClass(GeneralClass.class);
                this.editor.getTextElement().getAcceptedMetaclasses().clear();
                this.editor.getTextElement().getAcceptedMetaclasses().add(metaclass);
        
                this.editor.getTextElement().activateCompletion(null);
                this.editor.getTextElement().activateCompletion(CoreSession.getSession(p));
        
                this.editor.getTextElement().setAcceptNullValue(true);
                return this.editor;
            }
        
            @Override
            protected boolean canEdit(final Object element) {
                return true;
            }
        
            @Override
            protected Object getValue(final Object element) {
                final ParameterPropertyModel m = (ParameterPropertyModel) element;
                return m.getType();
            }
        
            @Override
            protected void setValue(final Object element, final Object value) {
                final ParameterPropertyModel m = (ParameterPropertyModel) element;
                m.setType(value);
            }
        
        });
    }

    @objid ("0d770127-0e04-43ad-be6a-c100c18d7f70")
    private void createNameColumn(final TableViewer viewer) {
        // create a column for the parameter Name
        final TableViewerColumn nameColumn = new TableViewerColumn(viewer, SWT.NONE);
        
        nameColumn.getColumn().setWidth(180);
        nameColumn.getColumn().setText(EditionDialogs.I18N.getString("OperationEditPanel.pName"));
        nameColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(final Object element) {
                final ParameterPropertyModel m = (ParameterPropertyModel) element;
                return m.getName();
            }
        });
        
        nameColumn.setEditingSupport(new EditingSupport(this.parametersTable) {
            private final CellEditor editor = new TextCellEditor(OperationParametersPanel.this.parametersTable.getTable());
        
            @Override
            protected CellEditor getCellEditor(final Object element) {
                return this.editor;
            }
        
            @Override
            protected boolean canEdit(final Object element) {
                final ParameterPropertyModel m = (ParameterPropertyModel) element;
                return m.isReturn() == false;
            }
        
            @Override
            protected Object getValue(final Object element) {
                final ParameterPropertyModel m = (ParameterPropertyModel) element;
                return m.getName();
            }
        
            @Override
            protected void setValue(final Object element, final Object value) {
                final ParameterPropertyModel m = (ParameterPropertyModel) element;
                m.setName((String) value);
            }
        
        });
    }

    @objid ("b772e2ab-03ad-414d-8997-d48b938dccaa")
    private void createIconColumn(final TableViewer viewer) {
        // create a column for the parameter icon
        final TableViewerColumn iconColumn = new TableViewerColumn(viewer, SWT.NONE);
        iconColumn.getColumn().setWidth(UIImages.PLACEHOLDER.getImageData().width + 6);
        iconColumn.getColumn().setText("");
        iconColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public Image getImage(final Object element) {
                final ParameterPropertyModel m = (ParameterPropertyModel) element;
                return m.getIcon();
            }
        
            @Override
            public String getText(final Object element) {
                return "";
            }
        });
    }

    /**
     * Get the currently displayed parameters, ie the parameter table current input. Note that this value is different from the parameters list returned by the operation model which is always a new and different instance.
     * @return
     */
    @objid ("68c403d2-7a0b-4491-a225-bf603beb3af5")
    @SuppressWarnings ("unchecked")
    private List<ParameterPropertyModel> getCurrentParameters() {
        return (List<ParameterPropertyModel>) this.parametersTable.getInput();
    }

    @objid ("0f173071-df7b-40a3-b2c3-5d946a55b72f")
    private void addReturnParameter() {
        getOperationModel().addReturnParameter();
        
        final List<ParameterPropertyModel> parameters = getOperationModel().getParameters();
        this.parametersTable.setInput(parameters);
        this.parametersTable.setSelection(new StructuredSelection(parameters.get(0)));
    }

}
