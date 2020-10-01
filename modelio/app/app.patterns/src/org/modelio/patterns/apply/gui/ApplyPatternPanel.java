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

package org.modelio.patterns.apply.gui;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.patterns.model.information.ConstantParameter;
import org.modelio.patterns.model.information.ElementParameter;
import org.modelio.patterns.model.information.Parameter;
import org.modelio.patterns.model.information.RootParameter;
import org.modelio.patterns.model.information.StringParameter;
import org.modelio.patterns.plugin.Patterns;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.platform.model.ui.swt.textelement.ITextElementSelectionListener;
import org.modelio.platform.model.ui.swt.textelement.TextElement;
import org.modelio.platform.ui.UIColor;
import org.modelio.platform.ui.panel.IPanelListener;
import org.modelio.platform.ui.panel.IPanelProvider;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("a8b75fbb-03ba-420b-bb9d-62f4f1c1d69b")
public class ApplyPatternPanel implements IPanelProvider {
    @objid ("4e4221c2-667f-4637-a066-a3dd38c76940")
    private ApplyPatternController controller;

    @objid ("23c8cc99-0ac9-4d10-9db5-897e934c30d0")
    @Override
    public boolean isRelevantFor(Object obj) {
        return obj instanceof ModelElement;
    }

    @objid ("035060f1-ec5a-4c75-a509-2275fd1f8d77")
    @Override
    public Control createPanel(Composite parent) {
        return this.controller.createUi(parent);
    }

    @objid ("0e7059e0-658b-4fdd-98ef-39da42ece42d")
    @Override
    public Control getPanel() {
        return this.controller.getUi();
    }

    @objid ("53008472-b24b-4762-a7e8-a9543b4064e7")
    @Override
    public String getHelpTopic() {
        return Patterns.I18N.getString("ApplyPattern.helptopic");
    }

    @objid ("acd5c4a6-dc90-44b2-ba44-fe4fff170030")
    @Override
    public Object getInput() {
        return this.controller.getData();
    }

    @objid ("b8cfea0b-db44-485c-bad7-495f0f3d977f")
    @Override
    public void setInput(Object input) {
        if (input instanceof ApplyPatternData) {
            this.controller.setData((ApplyPatternData) input);
        } else {
            this.controller.setData(null);
        }
    }

    @objid ("fbeae370-f26c-4d6e-b9f8-3b0bb27decf1")
    @Override
    public void addListener(IPanelListener l) {
        this.controller.addListener(l);
    }

    @objid ("82eb783b-5d66-47de-a928-8804474da704")
    @Override
    public void dispose() {
        this.controller.dispose();
    }

    @objid ("875d00da-e315-4603-a35d-9b2f5da1dd6d")
    @Override
    public void removeListener(IPanelListener l) {
        this.controller.removeListener(l);
    }

    @objid ("d6941e40-aa7d-4c78-b1ab-1083bf84ceb9")
    public ApplyPatternPanel() {
        this.controller = new ApplyPatternController();
    }

    @objid ("91db72e9-6945-436d-9dad-17558e98aa8b")
    private static class ApplyPatternPanelUI {
        @objid ("0d135f60-3d5c-4a4a-9c20-f38ef1629e73")
        private Composite composite = null;

        @objid ("4244155c-0fdf-4ef9-953a-43aa5520d070")
        private ApplyPatternController controller;

        @objid ("b2ee9144-d9e5-4cbc-88d1-e59547242dcd")
        private TableViewer editableParameters;

        @objid ("3d30e3fc-2241-41a4-965b-06a7d64673ae")
        private TableViewer constantParameters;

        @objid ("e84bf18b-ce86-46c4-b615-ce889c62f913")
        public ApplyPatternPanelUI(ApplyPatternController controller) {
            this.controller = controller;
        }

        @objid ("63c075c1-b050-4591-8fdd-dcc16dadc901")
        public Control createUI(Composite parent) {
            this.composite = new Composite(parent, SWT.NONE);
            this.composite.setLayoutData(new GridData(GridData.FILL_BOTH));
            
            this.composite.setLayout(new GridLayout(1, false));
            
            Label parametersLabel = new Label(this.composite, SWT.NONE);
            parametersLabel.setText(Patterns.I18N.getString("ApplyPatternPanel.Parameters.label"));
            
            Composite parametersTable = createEditableParametersTable(this.composite);
            GridData parametersGD = new GridData(SWT.FILL, SWT.FILL, true, true);
            parametersGD.minimumHeight = 150;
            parametersTable.setLayoutData(parametersGD);
            
            Label constantsLabel = new Label(this.composite, SWT.NONE);
            constantsLabel.setText(Patterns.I18N.getString("ApplyPatternPanel.Constants.label"));
            
            Composite constantsTable = createConstantParametersTable(this.composite);
            GridData constantsGD = new GridData(SWT.FILL, SWT.FILL, true, false);
            constantsGD.minimumHeight = 150;
            constantsTable.setLayoutData(constantsGD);
            return this.composite;
        }

        @objid ("8a9569ae-2a7a-4200-920d-06b1c6ce9ce8")
        public void update(ApplyPatternData rtPattern) {
            if (rtPattern != null) {
                // Dispatch parameters between the two tables
                List<Parameter> ep = new ArrayList<>();
                List<Parameter> cp = new ArrayList<>();
                for (Parameter param : rtPattern.getPattern().getParameters()) {
                    if (param instanceof ConstantParameter) {
                        cp.add(param);
                    } else {
                        ep.add(param);
                    }
                }
            
                this.editableParameters.setInput(ep);
                this.constantParameters.setInput(cp);
            } else {
                this.editableParameters.setInput(Collections.emptyList());
                this.constantParameters.setInput(Collections.emptyList());
            }
        }

        @objid ("b6a844be-0083-40ae-b207-65064587e6d1")
        public void dispose() {
            this.composite.dispose();
        }

        @objid ("311a1138-cec7-4f80-9df4-08bb6c6bd68f")
        private Composite createEditableParametersTable(Composite parent) {
            Composite contents = new Composite(parent, SWT.NONE);
            
            TableColumnLayout tableColumnLayout = new TableColumnLayout();
            contents.setLayout(tableColumnLayout);
            
            this.editableParameters = new TableViewer(contents, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
            // this.parameters.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            this.editableParameters.setContentProvider(new ArrayContentProvider());
            
            // Get the SWT Table that's inside the TableViewer
            Table table = this.editableParameters.getTable();
            
            // Show the column headers
            table.setHeaderVisible(true);
            table.setLinesVisible(false);
            
            // Parameter label
            TableViewerColumn labelColumn = new TableViewerColumn(this.editableParameters, SWT.NONE);
            labelColumn.getColumn().setWidth(200);
            labelColumn.getColumn().setText(Patterns.I18N.getString("ApplyPatternPanel.parameter.label.column.label"));
            labelColumn.getColumn().setToolTipText(Patterns.I18N.getString("ApplyPatternPanel.parameter.label.column.tooltip"));
            labelColumn.getColumn().setAlignment(SWT.CENTER);
            labelColumn.setLabelProvider(new ColumnLabelProvider() {
            
                @Override
                public Color getForeground(Object element) {
                    return UIColor.NONMODIFIABLE_ELEMENT_FG;
                }
            
                @Override
                public String getText(Object element) {
                    Parameter p = (Parameter) element;
                    return p.getLabel();
                }
            });
            
            // Parameter type
            TableViewerColumn typeColumn = new TableViewerColumn(this.editableParameters, SWT.NONE);
            typeColumn.getColumn().setWidth(200);
            typeColumn.getColumn().setText(Patterns.I18N.getString("ApplyPatternPanel.parameter.type.column.label"));
            typeColumn.getColumn().setToolTipText(Patterns.I18N.getString("ApplyPatternPanel.parameter.type.column.tooltip"));
            typeColumn.getColumn().setAlignment(SWT.CENTER);
            typeColumn.setLabelProvider(new ColumnLabelProvider() {
            
                @Override
                public Color getForeground(Object element) {
                    return UIColor.NONMODIFIABLE_ELEMENT_FG;
                }
            
                @Override
                public String getText(Object element) {
                    Parameter p = (Parameter) element;
                    if (p instanceof ConstantParameter) {
                        return "Constant";
                    } else if (p instanceof StringParameter) {
                        return "String";
                    } else if (p instanceof RootParameter) {
                        return "Root";
                    } else if (p instanceof ElementParameter) {
                        return "Element";
                    } else {
                        return "Unknown";
                    }
                }
            });
            
            // Parameter value
            TableViewerColumn valueColumn = new TableViewerColumn(this.editableParameters, SWT.NONE);
            valueColumn.getColumn().setWidth(200);
            valueColumn.getColumn().setText(Patterns.I18N.getString("ApplyPatternPanel.parameter.value.column.label"));
            valueColumn.getColumn().setToolTipText(Patterns.I18N.getString("ApplyPatternPanel.parameter.value.column.tooltip"));
            valueColumn.getColumn().setAlignment(SWT.LEFT);
            valueColumn.setLabelProvider(new ColumnLabelProvider() {
            
                @Override
                public Color getForeground(Object element) {
                    return (element instanceof ConstantParameter) ? UIColor.NONMODIFIABLE_ELEMENT_FG : UIColor.MODIFIABLE_ELEMENT_FG;
                }
            
                @Override
                public String getText(Object element) {
                    Parameter p = (Parameter) element;
                    Object value = ApplyPatternPanelUI.this.controller.data.getParameterValues().get(p.getName());
            
                    if (value instanceof MObject) {
                        return ((MObject) value).getName();
                    } else {
                        return Objects.toString(value);
                    }
                }
            
                @Override
                public Image getImage(Object element) {
                    Parameter p = (Parameter) element;
                    Object value = ApplyPatternPanelUI.this.controller.data.getParameterValues().get(p.getName());
            
                    if (value instanceof MObject) {
                        return ElementImageService.getIcon((MObject) value);
                    } else {
                        return null;
                    }
                }
            
                @Override
                public String getToolTipText(Object element) {
                    Parameter p = (Parameter) element;
                    return p.getDescription();
                }
            });
            
            valueColumn.setEditingSupport(new ParameterValueEditingSupport(this.editableParameters, this.controller));
            
            ColumnViewerToolTipSupport.enableFor(this.editableParameters, ToolTip.NO_RECREATE);
            
            tableColumnLayout.setColumnData(labelColumn.getColumn(), new ColumnWeightData(50, 200, true));
            tableColumnLayout.setColumnData(typeColumn.getColumn(), new ColumnWeightData(10, 90, true));
            tableColumnLayout.setColumnData(valueColumn.getColumn(), new ColumnWeightData(50, 200, true));
            return contents;
        }

        @objid ("0dd1152b-787a-4fdd-ba6d-78b2167845cd")
        private Composite createConstantParametersTable(Composite parent) {
            Composite contents = new Composite(parent, SWT.NONE);
            
            TableColumnLayout tableColumnLayout = new TableColumnLayout();
            contents.setLayout(tableColumnLayout);
            
            this.constantParameters = new TableViewer(contents, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
            // this.constantParameters.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            this.constantParameters.setContentProvider(new ArrayContentProvider());
            
            // Get the SWT Table that's inside the TableViewer
            Table table = this.constantParameters.getTable();
            
            // Show the column headers
            table.setHeaderVisible(true);
            table.setLinesVisible(false);
            
            // Label label
            TableViewerColumn labelColumn = new TableViewerColumn(this.constantParameters, SWT.NONE);
            labelColumn.getColumn().setWidth(200);
            labelColumn.getColumn().setText(Patterns.I18N.getString("ApplyPatternPanel.constant.label.column.label"));
            labelColumn.getColumn().setToolTipText(Patterns.I18N.getString("ApplyPatternPanel.constant.label.column.tooltip"));
            labelColumn.getColumn().setAlignment(SWT.CENTER);
            labelColumn.setLabelProvider(new ColumnLabelProvider() {
            
                @Override
                public Color getForeground(Object element) {
                    return UIColor.NONMODIFIABLE_ELEMENT_FG;
                }
            
                @Override
                public String getText(Object element) {
                    Parameter p = (Parameter) element;
                    return p.getLabel();
                }
            });
            
            // Parameter type
            TableViewerColumn typeColumn = new TableViewerColumn(this.constantParameters, SWT.NONE);
            typeColumn.getColumn().setWidth(200);
            typeColumn.getColumn().setText(Patterns.I18N.getString("ApplyPatternPanel.constant.type.column.label"));
            typeColumn.getColumn().setToolTipText(Patterns.I18N.getString("ApplyPatternPanel.constant.type.column.tooltip"));
            typeColumn.getColumn().setAlignment(SWT.CENTER);
            typeColumn.setLabelProvider(new ColumnLabelProvider() {
            
                @Override
                public Color getForeground(Object element) {
                    return UIColor.NONMODIFIABLE_ELEMENT_FG;
                }
            
                @Override
                public String getText(Object element) {
                    Parameter p = (Parameter) element;
                    if (p instanceof ConstantParameter) {
                        return "Constant";
                    } else if (p instanceof StringParameter) {
                        return "String";
                    } else if (p instanceof RootParameter) {
                        return "Root";
                    } else if (p instanceof ElementParameter) {
                        return "Element";
                    } else {
                        return "Unknown";
                    }
                }
            });
            
            // Parameter value
            TableViewerColumn valueColumn = new TableViewerColumn(this.constantParameters, SWT.NONE);
            valueColumn.getColumn().setWidth(200);
            valueColumn.getColumn().setText(Patterns.I18N.getString("ApplyPatternPanel.constant.value.column.label"));
            valueColumn.getColumn().setToolTipText(Patterns.I18N.getString("ApplyPatternPanel.constant.value.column.tooltip"));
            valueColumn.getColumn().setAlignment(SWT.LEFT);
            valueColumn.setLabelProvider(new ColumnLabelProvider() {
            
                @Override
                public Color getForeground(Object element) {
                    return (element instanceof ConstantParameter) ? UIColor.NONMODIFIABLE_ELEMENT_FG : UIColor.MODIFIABLE_ELEMENT_FG;
                }
            
                @Override
                public String getText(Object element) {
                    Parameter p = (Parameter) element;
                    Object value = ApplyPatternPanelUI.this.controller.data.getParameterValues().get(p.getName());
            
                    if (value instanceof MObject) {
                        return ((MObject) value).getName();
                    } else {
                        return value.toString();
                    }
                }
            
                @Override
                public Image getImage(Object element) {
                    Parameter p = (Parameter) element;
                    Object value = ApplyPatternPanelUI.this.controller.data.getParameterValues().get(p.getName());
            
                    if (value instanceof MObject) {
                        return ElementImageService.getIcon((MObject) value);
                    } else {
                        return null;
                    }
                }
            
                @Override
                public String getToolTipText(Object element) {
                    Parameter p = (Parameter) element;
                    return p.getDescription();
                }
            });
            
            ColumnViewerToolTipSupport.enableFor(this.constantParameters, ToolTip.NO_RECREATE);
            
            tableColumnLayout.setColumnData(labelColumn.getColumn(), new ColumnWeightData(50, 200, true));
            tableColumnLayout.setColumnData(typeColumn.getColumn(), new ColumnWeightData(10, 90, true));
            tableColumnLayout.setColumnData(valueColumn.getColumn(), new ColumnWeightData(50, 200, true));
            return contents;
        }

    }

    @objid ("d3fdf0b6-0c81-4e55-9131-33ae0492a907")
    private static class ApplyPatternController {
        @objid ("a003e2d3-4ad1-47b9-a6bd-f0eb0cc8476f")
        private ApplyPatternPanelUI ui;

        @objid ("4e5bb2df-8518-442b-a546-9d144f84023e")
        private List<IPanelListener> listeners = new ArrayList<>();

        @objid ("48cc8e90-5cc6-42a7-a412-077348be4a81")
        private ApplyPatternData data;

        @objid ("d884647e-5ab2-49e6-96fd-f547f2be94f5")
        public ApplyPatternData getData() {
            return this.data;
        }

        @objid ("ff362f91-4395-4b67-a8f5-ffe2dae734e5")
        public void setData(ApplyPatternData data) {
            this.data = data;
            if (this.ui != null) {
                this.ui.update(this.data);
            }
        }

        @objid ("d96cb856-3d28-4018-8284-17d497a659a3")
        public Control createUi(Composite parent) {
            this.ui = new ApplyPatternPanelUI(this);
            Control control = this.ui.createUI(parent);
            this.ui.update(this.data);
            return control;
        }

        @objid ("8d9f7945-e9b9-4209-9299-9c5347d786c5")
        public Control getUi() {
            return this.ui.composite;
        }

        @objid ("58300652-3ad5-4930-a14f-02a325f6067c")
        public void dispose() {
            this.ui.dispose();
            this.ui = null;
        }

        @objid ("2dbf76e9-dd50-4b65-8465-2dd404e77073")
        private void fireListeners(ApplyPatternData data, boolean isValidate) {
            this.listeners.forEach(l -> l.dataChanged(data, isValidate));
        }

        @objid ("c87a5f8e-e49a-40da-b0d3-9f4820a4dfed")
        public synchronized void removeListener(IPanelListener l) {
            this.listeners.remove(l);
        }

        @objid ("772eae6b-7411-4222-9339-a11e50101169")
        public synchronized void addListener(IPanelListener l) {
            if (this.listeners.contains(l)) {
                throw new InvalidParameterException("Listener already registered");
            }
            this.listeners.add(l);
        }

        @objid ("e6af107a-e56c-4254-bc1f-d42f8e758ea1")
        public void onParameterValueChange(Parameter element, Object newValue) {
            Object oldValue = this.data.getParameterValues().get(element.getName());
            if (Objects.equals(oldValue, newValue) == false) {
                this.data.getParameterValues().put(element.getName(), newValue);
                this.ui.update(this.data);
                fireListeners(this.data, true);
            }
        }

    }

    @objid ("f44eb4de-4c7f-4ea4-96c4-567845f9d0e8")
    private static class ParameterValueEditingSupport extends EditingSupport {
        @objid ("a0a990aa-897e-45ea-9ef7-e05e767d5fee")
        private final ApplyPatternController controller;

        @objid ("b70bb663-0c3a-4c3a-ab25-50268c93ae99")
        public ParameterValueEditingSupport(TableViewer viewer, ApplyPatternController controller) {
            super(viewer);
            this.controller = controller;
        }

        @objid ("672f2a7d-f09e-4a96-a403-cbba7054645a")
        @Override
        protected CellEditor getCellEditor(Object element) {
            Table table = ((TableViewer) getViewer()).getTable();
            if (element instanceof StringParameter) {
                return new TextCellEditor(table);
            } else {
                return new TextCellEditor(table) {
                    private TextElement textElement;
            
                    @Override
                    protected Control createControl(Composite parent) {
                        ICoreSession session = ParameterValueEditingSupport.this.controller.data.getSession();
            
                        this.textElement = new TextElement(parent, SWT.NONE, false);
                        this.textElement.activateCompletion(session);
                        this.textElement.activatePicking(ParameterValueEditingSupport.this.controller.data.getPickingService());
            
                        this.textElement.getAcceptedMetaclasses().add(session.getMetamodel().getMClass(((Parameter) element).getMetaclass()));
                        return this.textElement.getTextControl();
                    }
            
                    @Override
                    public void activate() {
                        super.activate();
            
                        this.textElement.addListener(new ITextElementSelectionListener() {
                            @Override
                            public void selectedElementChanged(MObject oldElement, MObject newElement) {
                                setValue(newElement);
                                fireApplyEditorValue();
                                deactivate();
                            }
                        });
                    }
            
                    @Override
                    protected void doSetFocus() {
                        super.doSetFocus();
                        this.textElement.getTextControl().selectAll();
                        this.textElement.getTextControl().setFocus();
                    }
            
                    @Override
                    protected void doSetValue(Object value) {
                        this.textElement.setValue((MObject) value);
                    }
            
                    @Override
                    protected Object doGetValue() {
                        return this.textElement.getValue();
                    }
            
                    @Override
                    protected boolean dependsOnExternalFocusListener() {
                        return false;
                    }
                };
            }
        }

        @objid ("d175240d-c331-45f3-9a4b-28608f73e560")
        @Override
        protected boolean canEdit(Object element) {
            return !(element instanceof ConstantParameter);
        }

        @objid ("76fcd096-0256-4457-b3c4-b18a5652d510")
        @Override
        protected Object getValue(Object element) {
            return this.controller.data.getParameterValues().get(((Parameter) element).getName());
        }

        @objid ("8289069f-4234-46af-8caf-c58314d2b56b")
        @Override
        protected void setValue(Object element, Object userInputValue) {
            this.controller.onParameterValueChange((Parameter) element, userInputValue);
        }

    }

}
