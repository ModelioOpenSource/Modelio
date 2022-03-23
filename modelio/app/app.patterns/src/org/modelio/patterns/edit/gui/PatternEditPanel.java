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
package org.modelio.patterns.edit.gui;

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
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.patterns.model.ProfileUtils;
import org.modelio.patterns.model.ProfileUtils.PatternDesignerStereotypes;
import org.modelio.patterns.model.RuntimePattern;
import org.modelio.patterns.model.information.ConstantParameter;
import org.modelio.patterns.model.information.ElementParameter;
import org.modelio.patterns.model.information.Parameter;
import org.modelio.patterns.model.information.RootParameter;
import org.modelio.patterns.model.information.StringParameter;
import org.modelio.patterns.plugin.Patterns;
import org.modelio.platform.model.ui.swt.images.MetamodelImageService;
import org.modelio.platform.ui.UIColor;
import org.modelio.platform.ui.panel.IPanelListener;
import org.modelio.platform.ui.panel.IPanelProvider;

@objid ("01a67f33-0eac-4d24-a302-36346d4874a6")
public class PatternEditPanel implements IPanelProvider {
    @objid ("75a29f4c-5984-405d-85bd-e10b884e85ec")
    private Controller controller;

    @objid ("e24888cf-0898-4931-8169-fc5ad96a57a6")
    public  PatternEditPanel() {
        this.controller = new Controller();
    }

    @objid ("e0d56aff-ebfd-40e2-8db3-e98dd09dc764")
    @Override
    public boolean isRelevantFor(Object obj) {
        return obj instanceof Package && ((Package) obj).isStereotyped(ProfileUtils.MODULE_NAME, PatternDesignerStereotypes.PATTERN);
    }

    @objid ("03c72e3d-5314-4ae2-818a-3d871fd5f0e5")
    @Override
    public Control createPanel(Composite parent) {
        return this.controller.createUi(parent);
    }

    @objid ("7af522c6-7ca8-4d74-8f90-6d5ad912d9d4")
    @Override
    public Control getPanel() {
        return this.controller.getUi();
    }

    @objid ("97d2c8a1-9503-459d-a061-fab1c4befb02")
    @Override
    public String getHelpTopic() {
        return Patterns.I18N.getString("PatternEdit.helptopic");
    }

    @objid ("d7fe7192-b837-4ccf-902a-cd5f3a102f11")
    @Override
    public Object getInput() {
        return this.controller.getData();
    }

    @objid ("ba89d3d2-c0b6-41c4-8f0f-81b9ebe32577")
    @Override
    public void setInput(Object input) {
        if (input instanceof RuntimePattern) {
            this.controller.setData(input);
        } else if (input instanceof Package
                && ((Package) input).isStereotyped(ProfileUtils.MODULE_NAME, PatternDesignerStereotypes.PATTERN)) {
            this.controller.setData(new RuntimePattern((Package) input));
        } else {
            this.controller.setData(null);
        }
        
    }

    @objid ("3d076ec8-c4db-4603-a27a-e030c35bbdd2")
    @Override
    public void addListener(IPanelListener l) {
        this.controller.addListener(l);
    }

    @objid ("ab4096ee-01cc-4a5b-876e-60a963682bcb")
    @Override
    public void dispose() {
        this.controller.dispose();
    }

    @objid ("d9a01bec-0269-4c1e-885e-35ce0ea57af4")
    @Override
    public void removeListener(IPanelListener l) {
        this.controller.removeListener(l);
    }

    @objid ("e85285d7-c842-4931-acf7-d7d27cf77a0a")
    private static class PanelUI {
        @objid ("1cdc637b-c198-4c65-b37f-02631b4c8c8f")
        private Composite composite = null;

        @objid ("b1e3ae61-b085-44a0-818d-d9e91f2949ee")
        private Controller controller;

        @objid ("852c8301-033b-405a-a2de-1f760f10abc3")
        private Text nameText;

        @objid ("123c7e54-9818-4a04-9c99-2c0f6118723e")
        private Text versionText;

        @objid ("24bee9ed-11e8-4423-b06d-5a72b14b5f00")
        private Text descriptionText;

        @objid ("c58c13fd-5e77-443e-b126-dd2cdb341a1a")
        private Text categoryText;

        @objid ("b6c2df17-0cf8-4877-9ba0-150b0db95942")
        private TableViewer parameters;

        @objid ("7b2b1c5f-b823-4295-85fd-510b097b375b")
        private TabItem idTabItem;

        @objid ("514365b8-ecce-4b26-a871-179d346dab21")
        private TabItem paramsTabItem;

        @objid ("3cd8364b-7b64-44d3-a84f-5b88aaf228ae")
        public  PanelUI(Controller controller) {
            this.controller = controller;
        }

        @objid ("4e3ee2dc-6197-401c-b937-3418bf2a00eb")
        public Control createUI(Composite parent) {
            this.composite = new Composite(parent, SWT.NONE);
            this.composite.setLayoutData(new GridData(GridData.FILL_BOTH));
            
            FillLayout layout = new FillLayout(SWT.HORIZONTAL);
            layout.marginHeight = 8;
            this.composite.setLayout(layout);
            
            TabFolder tabFolder = new TabFolder(this.composite, SWT.NONE);
            
            this.idTabItem = createIdentificationTab(tabFolder);
            this.idTabItem.setText(Patterns.I18N.getString("PatternEditPanel.IdentificationTab.label"));
            
            this.paramsTabItem = createParametersTab(tabFolder);
            this.paramsTabItem.setText(Patterns.I18N.getString("PatternEditPanel.ParameterTab.label"));
            return this.composite;
        }

        @objid ("1c340e30-c301-432f-b03b-58bff78519ad")
        public void update(RuntimePattern rtPattern) {
            if (rtPattern != null) {
                this.nameText.setText(rtPattern.getName());
                this.versionText.setText(rtPattern.getVersion());
                this.descriptionText.setText(rtPattern.getDescription());
                this.categoryText.setText(rtPattern.getCategory());
                this.parameters.setInput(rtPattern.getParameters());
                //this.iconPreviewLabel.setImage(rtPattern.getIcon());
            
                boolean editable = rtPattern.getModelPattern() == null || rtPattern.getModelPattern().isModifiable();
                this.idTabItem.getControl().setEnabled(editable);
                this.paramsTabItem.getControl().setEnabled(editable);
            } else {
                this.nameText.setText("");
                this.versionText.setText("");
                this.descriptionText.setText("");
                this.categoryText.setText("");
                this.parameters.setInput(Collections.emptyList());
            }
            
        }

        @objid ("485fb606-842c-471f-989a-b8f9feaef130")
        public void dispose() {
            this.composite.dispose();
        }

        @objid ("333eb1c6-00a5-457d-a178-b50e9086a32d")
        private TabItem createIdentificationTab(TabFolder tabFolder) {
            TabItem idTabItem = new TabItem(tabFolder, SWT.NONE);
            Composite contents = new Composite(tabFolder, SWT.NONE);
            contents.setLayout(new GridLayout(2, false));
            
            Label nameLabel = new Label(contents, SWT.NONE);
            nameLabel.setText(Patterns.I18N.getString("PatternEditPanel.Name.label"));
            nameLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
            
            this.nameText = new Text(contents, SWT.BORDER);
            this.nameText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
            this.nameText.setToolTipText(Patterns.I18N.getString("PatternEditPanel.Name.tooltip"));
            
            this.nameText.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    PanelUI.this.controller.onNameChange(((Text) e.widget).getText());
                }
            });
            
            this.nameText.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
                        PanelUI.this.controller.onNameChange(((Text) e.widget).getText());
                    }
                }
            });
            
            Label versionLabel = new Label(contents, SWT.NONE);
            versionLabel.setText(Patterns.I18N.getString("PatternEditPanel.Version.label"));
            versionLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
            
            this.versionText = new Text(contents, SWT.BORDER);
            this.versionText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
            this.versionText.setToolTipText(Patterns.I18N.getString("PatternEditPanel.Version.tooltip"));
            
            this.versionText.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    PanelUI.this.controller.onVersionChange(((Text) e.widget).getText());
                }
            });
            
            this.versionText.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
                        PanelUI.this.controller.onVersionChange(((Text) e.widget).getText());
                    }
                }
            });
            
            /*
            Label iconLabel = new Label(contents, SWT.NONE);
            iconLabel.setText(Patterns.I18N.getString("PatternEditPanel.Icon.label"));
            iconLabel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
            
            Composite iconZone = new Composite (contents, SWT.NONE);
            iconZone.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
            iconZone.setLayout(new FillLayout(SWT.HORIZONTAL));
            this.iconPreviewLabel = new Label(iconZone, SWT.NONE);
            
            this.chooseIconButton = new Button(iconZone, SWT.NONE);
            this.chooseIconButton.setImage(UIImages.CHOOSER);
            this.chooseIconButton.addSelectionListener(new SelectionListener() {
            
                @Override
                public void widgetSelected(SelectionEvent e) {
                    FileDialog dialog = new FileDialog(tabFolder.getShell(), SWT.APPLICATION_MODAL | SWT.OPEN);
                    dialog.setFilterNames(new String[] { "png", "bmp", "jpg" });
                    dialog.setFilterExtensions(new String[] { "*.png", "*.bmp", "*.jpg" });
            
                    String result = dialog.open();
                    if (result != null) {
                        PanelUI.this.controller.onIconPathChange(result);
                    }
                }
            
                @Override
                public void widgetDefaultSelected(SelectionEvent e) {
                    // Nothing to do
                }
            });*/
            
            Label descriptionLabel = new Label(contents, SWT.NONE);
            descriptionLabel.setText(Patterns.I18N.getString("PatternEditPanel.Description.label"));
            descriptionLabel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
            
            this.descriptionText = new Text(contents, SWT.BORDER | SWT.MULTI);
            this.descriptionText.setEditable(true);
            this.descriptionText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            this.descriptionText.setToolTipText(Patterns.I18N.getString("PatternEditPanel.Description.tooltip"));
            
            this.descriptionText.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    PanelUI.this.controller.onDescriptionChange(((Text) e.widget).getText());
                }
            });
            
            this.descriptionText.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
                        PanelUI.this.controller.onDescriptionChange(((Text) e.widget).getText());
                    }
                }
            });
            
            Label categoryLabel = new Label(contents, SWT.NONE);
            categoryLabel.setText(Patterns.I18N.getString("PatternEditPanel.Category.label"));
            descriptionLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
            
            this.categoryText = new Text(contents, SWT.BORDER);
            this.categoryText.setEditable(true);
            this.categoryText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
            this.categoryText.setToolTipText(Patterns.I18N.getString("PatternEditPanel.Category.tooltip"));
            
            this.categoryText.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    PanelUI.this.controller.onCategoryChange(((Text) e.widget).getText());
                }
            });
            
            this.categoryText.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
                        PanelUI.this.controller.onCategoryChange(((Text) e.widget).getText());
                    }
                }
            });
            
            idTabItem.setControl(contents);
            return idTabItem;
        }

        @objid ("4441d6d1-8294-47ab-b88d-3d2e330bb408")
        private TabItem createParametersTab(TabFolder tabFolder) {
            TabItem paramsTabItem = new TabItem(tabFolder, SWT.NONE);
            Composite contents = new Composite(tabFolder, SWT.NONE);
            
            TableColumnLayout tableColumnLayout = new TableColumnLayout();
            contents.setLayout(tableColumnLayout);
            
            this.parameters = new TableViewer(contents, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
            this.parameters.setContentProvider(new ArrayContentProvider());
            
            // Get the SWT Table that's inside the TableViewer
            Table table = this.parameters.getTable();
            
            // Show the column headers
            table.setHeaderVisible(true);
            
            // Parameter Name
            TableViewerColumn nameColumn = new TableViewerColumn(this.parameters, SWT.NONE);
            nameColumn.getColumn().setText(Patterns.I18N.getString("PatternEditPanel.parameter.name.column.label"));
            nameColumn.getColumn().setToolTipText(Patterns.I18N.getString("PatternEditPanel.parameter.name.column.tooltip"));
            nameColumn.getColumn().setAlignment(SWT.CENTER);
            nameColumn.setLabelProvider(new ColumnLabelProvider() {
            
                @Override
                public Color getForeground(Object element) {
                    return UIColor.NONMODIFIABLE_ELEMENT_FG;
                }
            
                @Override
                public String getText(Object element) {
                    Parameter p = (Parameter) element;
                    return p.getName();
                }
            });
            
            // Parameter type
            TableViewerColumn typeColumn = new TableViewerColumn(this.parameters, SWT.NONE);
            typeColumn.getColumn().setText(Patterns.I18N.getString("PatternEditPanel.parameter.type.column.label"));
            typeColumn.getColumn().setToolTipText(Patterns.I18N.getString("PatternEditPanel.parameter.type.column.tooltip"));
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
                    }
                    if (p instanceof StringParameter) {
                        return "String";
                    }
                    if (p instanceof RootParameter) {
                        return "Root";
                    }
                    if (p instanceof ElementParameter) {
                        return "Element";
                    }
            
                    return p.getName();
                }
            
                @Override
                public Image getImage(Object element) {
                    Parameter p = (Parameter) element;
                    if (! (p instanceof StringParameter)) {
                        return MetamodelImageService.getIcon(p.getMetaclass());
                    }
                    return null;
                }
            });
            
            // Parameter Label
            TableViewerColumn labelColumn = new TableViewerColumn(this.parameters, SWT.NONE);
            labelColumn.getColumn().setText(Patterns.I18N.getString("PatternEditPanel.parameter.label.column.label"));
            labelColumn.getColumn().setToolTipText(Patterns.I18N.getString("PatternEditPanel.parameter.label.column.tooltip"));
            labelColumn.getColumn().setAlignment(SWT.LEFT);
            labelColumn.setLabelProvider(new ColumnLabelProvider() {
            
                @Override
                public Color getForeground(Object element) {
                    return (element instanceof ConstantParameter) ? UIColor.NONMODIFIABLE_ELEMENT_FG : UIColor.MODIFIABLE_ELEMENT_FG;
                }
            
            
                @Override
                public String getText(Object element) {
                    Parameter p = (Parameter) element;
                    return p.getLabel();
                }
            });
            
            labelColumn.setEditingSupport(new ParameterLabelEditingSupport(this.parameters, this.controller));
            
            // Parameter description
            TableViewerColumn descriptionColumn = new TableViewerColumn(this.parameters, SWT.NONE);
            descriptionColumn.getColumn().setText(Patterns.I18N.getString("PatternEditPanel.parameter.description.column.label"));
            descriptionColumn.getColumn().setToolTipText(Patterns.I18N.getString("PatternEditPanel.parameter.description.column.tooltip"));
            descriptionColumn.getColumn().setAlignment(SWT.LEFT);
            descriptionColumn.setLabelProvider(new ColumnLabelProvider() {
            
                @Override
                public Color getForeground(Object element) {
                    return (element instanceof ConstantParameter) ? UIColor.NONMODIFIABLE_ELEMENT_FG : UIColor.MODIFIABLE_ELEMENT_FG;
                }
            
            
                @Override
                public String getText(Object element) {
                    Parameter p = (Parameter) element;
                    return p.getDescription();
                }
            });
            
            descriptionColumn.setEditingSupport(new ParameterDescriptionEditingSupport(this.parameters, this.controller));
            
            tableColumnLayout.setColumnData(nameColumn.getColumn(), new ColumnWeightData(30, 150, true));
            tableColumnLayout.setColumnData(typeColumn.getColumn(),  new ColumnWeightData(10, 90, true));
            tableColumnLayout.setColumnData(labelColumn.getColumn(), new ColumnWeightData(50, 200, true));
            tableColumnLayout.setColumnData(descriptionColumn.getColumn(), new ColumnWeightData(50, 200, true));
            
            paramsTabItem.setControl(contents);
            return paramsTabItem;
        }

    }

    @objid ("48b4582e-051e-4872-a0e5-8a9c59d4870b")
    private static class Controller {
        @objid ("988d7637-07bb-4cc2-be8d-f0da891e8e05")
        private RuntimePattern rtPattern;

        @objid ("30db4ce5-056d-4577-98c1-1857b039db4b")
        private PanelUI ui;

        @objid ("9a331786-5864-4cad-9093-648359f006ee")
        private List<IPanelListener> listeners = new ArrayList<>();

        @objid ("59f95c4e-7593-47e6-8457-b8a4f253d4c3")
        public Object getData() {
            return this.rtPattern;
        }

        @objid ("5c1c07a8-1eaa-4af0-90e9-8a079d070f5f")
        public void setData(Object data) {
            this.rtPattern = (RuntimePattern) data;
            if (this.ui != null) {
                this.ui.update(this.rtPattern);
            }
            
        }

        @objid ("667c7ead-35c1-4073-9bba-b345c7689061")
        public Control createUi(Composite parent) {
            this.ui = new PanelUI(this);
            Control control = this.ui.createUI(parent);
            this.ui.update(this.rtPattern);
            return control;
        }

        @objid ("85b568e1-4894-4a75-866f-7cc1dfc1caeb")
        public Control getUi() {
            return this.ui.composite;
        }

        @objid ("d3f11748-51a6-4059-ba08-82fa4d60df86")
        public void dispose() {
            this.ui.dispose();
            this.ui = null;
            
        }

        @objid ("13ce79bd-a72f-419e-a87c-f87137126386")
        private void fireListeners(RuntimePattern rtPattern, boolean isValidate) {
            this.listeners.forEach(l -> l.dataChanged(rtPattern, isValidate));
        }

        @objid ("62e7c206-adc2-431d-997a-7e02d22becd5")
        public synchronized void removeListener(IPanelListener l) {
            this.listeners.remove(l);
        }

        @objid ("04836ad2-404c-4349-8842-401753278d03")
        public synchronized void addListener(IPanelListener l) {
            if (this.listeners.contains(l)) {
                throw new InvalidParameterException("Listener already registered");
            }
            this.listeners.add(l);
            
        }

        @objid ("48302a1b-f7a7-4a15-97ea-1f468a5769c2")
        public void onCategoryChange(String newValue) {
            String oldValue = this.rtPattern.getCategory();
            if (Objects.equals(oldValue, newValue) == false) {
                this.rtPattern.setCategory(newValue);
                this.ui.update(this.rtPattern);
                fireListeners(this.rtPattern, true);
            }
            
        }

        @objid ("720b4d6c-552f-48aa-ab92-424c97495e13")
        public void onDescriptionChange(String newValue) {
            String oldValue = this.rtPattern.getDescription();
            if (Objects.equals(oldValue, newValue) == false) {
                this.rtPattern.setDescription(newValue);
                this.ui.update(this.rtPattern);
                fireListeners(this.rtPattern, true);
            }
            
        }

        @objid ("db40f410-e0a6-4d60-bc46-7ba78c2a088f")
        public void onVersionChange(String newValue) {
            String oldValue = this.rtPattern.getVersion();
            if (Objects.equals(oldValue, newValue) == false) {
                this.rtPattern.setVersion(newValue);
                this.ui.update(this.rtPattern);
                fireListeners(this.rtPattern, true);
            }
            
        }

        @objid ("bd4ecb0a-0c5b-4f30-b922-7ec0aa86d9e2")
        public void onNameChange(String newValue) {
            String oldValue = this.rtPattern.getName();
            if (Objects.equals(oldValue, newValue) == false) {
                this.rtPattern.setName(newValue);
                this.ui.update(this.rtPattern);
                fireListeners(this.rtPattern, true);
            }
            
        }

        @objid ("60c31e8e-20e2-4c48-8cbd-05e1a12c2e04")
        public void onParameterLabelChange(Parameter element, String newValue) {
            String oldValue = element.getLabel();
            if (Objects.equals(oldValue, newValue) == false) {
                element.setLabel(newValue);
                this.ui.update(this.rtPattern);
                fireListeners(this.rtPattern, true);
            }
            
        }

        @objid ("57a6122c-d576-4bc8-b307-9324ef319521")
        public void onIconPathChange(String newValue) {
            String oldValue = this.rtPattern.getIconPath();
            if (Objects.equals(oldValue, newValue) == false) {
                this.rtPattern.setIconPath(newValue);
                this.ui.update(this.rtPattern);
                fireListeners(this.rtPattern, true);
            }
            
        }

        @objid ("a10cd53b-4b29-4935-9541-4078c79a4038")
        public void onParameterDescriptionChange(Parameter element, String newValue) {
            String oldValue = element.getDescription();
            if (Objects.equals(oldValue, newValue) == false) {
                element.setDescription(newValue);
                this.ui.update(this.rtPattern);
                fireListeners(this.rtPattern, true);
            }
            
        }

    }

    @objid ("03608c7e-d31b-4b35-a60c-ce10c58c4ae9")
    private static class ParameterLabelEditingSupport extends EditingSupport {
        @objid ("e2c9c997-e02e-4051-b233-703fcae71908")
        private final Controller controller;

        @objid ("a6125910-9e8d-449e-a620-f808d45abaf5")
        private final CellEditor editor;

        @objid ("b04cf8bc-1562-42c2-b40c-ac5ae2341c6b")
        public  ParameterLabelEditingSupport(TableViewer viewer, Controller controller) {
            super(viewer);
            this.controller = controller;
            this.editor = new TextCellEditor(viewer.getTable());
            
        }

        @objid ("c19c666a-4301-430b-92b3-3d5ed66fdee5")
        @Override
        protected CellEditor getCellEditor(Object element) {
            return this.editor;
        }

        @objid ("425d122a-105d-405c-b275-979a85b334f7")
        @Override
        protected boolean canEdit(Object element) {
            return !(element instanceof ConstantParameter);
        }

        @objid ("1650f032-0511-4625-90e1-6fc45317be88")
        @Override
        protected Object getValue(Object element) {
            return ((Parameter) element).getLabel();
        }

        @objid ("b25a42b1-9adf-4b84-8c21-9b1f2d288282")
        @Override
        protected void setValue(Object element, Object userInputValue) {
            this.controller.onParameterLabelChange((Parameter) element, String.valueOf(userInputValue));
        }

    }

    @objid ("97f4fb8f-9aa4-43dd-81f8-030f1548f77e")
    private static class ParameterDescriptionEditingSupport extends EditingSupport {
        @objid ("1f6c757f-2192-4a9f-a26c-e02392b23c46")
        private final Controller controller;

        @objid ("ff6f93a9-36b9-47a9-a900-22f6bef0e61b")
        private final CellEditor editor;

        @objid ("eb10e5a9-8dae-41e0-be4f-7d2694130c0a")
        public  ParameterDescriptionEditingSupport(TableViewer viewer, Controller controller) {
            super(viewer);
            this.controller = controller;
            this.editor = new TextCellEditor(viewer.getTable());
            
        }

        @objid ("e2e599cf-db27-4b24-a94d-7b920350bb1f")
        @Override
        protected CellEditor getCellEditor(Object element) {
            return this.editor;
        }

        @objid ("011dac4d-d927-4336-82d0-a773a379417b")
        @Override
        protected boolean canEdit(Object element) {
            return !(element instanceof ConstantParameter);
        }

        @objid ("9aba95ca-9323-4151-95a2-993f01d85ee5")
        @Override
        protected Object getValue(Object element) {
            return ((Parameter) element).getDescription();
        }

        @objid ("d586660e-18ef-420b-a6b5-090c8fa4f364")
        @Override
        protected void setValue(Object element, Object userInputValue) {
            this.controller.onParameterDescriptionChange((Parameter) element, String.valueOf(userInputValue));
        }

    }

}
