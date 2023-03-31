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
package org.modelio.platform.model.ui.panels.scope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.OwnerDrawLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Table;
import org.modelio.api.modelio.model.scope.MutableElementScope;
import org.modelio.api.ui.swt.SelectionHelper;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.platform.mda.infra.MdaResources;
import org.modelio.platform.model.ui.plugin.CoreUi;
import org.modelio.platform.model.ui.swt.images.MetamodelImageService;
import org.modelio.platform.ui.UIColor;
import org.modelio.platform.ui.UIImages;
import org.modelio.platform.ui.panel.IPanelListener;
import org.modelio.platform.ui.panel.IPanelProvider;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * Panel provider editing a List of {@link MutableElementScope} POJOs. The panel features:
 * <ul>
 * <li>a table showing the list of selected scopes</li>
 * <li>columns in the table to choose whether to include sub-classes or sub-stereotypes in the scope definition
 * <li>
 * <li>columns are directly editable with proposal on completion</li>
 * <li>two buttons add/delete to add or remove a scope to the list</li>
 * <li>the panel listeners are called when the list is modified whatever the modification</li>
 * </ul>
 */
@objid ("8273ed38-891e-49c1-9c1d-d36808edf259")
public class ScopeEditionPanel implements IPanelProvider {
    @objid ("278e35a2-ad4f-456b-a8a3-00cb8d28c2dd")
    private final PanelControler controler;

    @objid ("e0a9c2b2-79db-4853-bcdf-865742c7ed94")
    public  ScopeEditionPanel(ICoreSession session) {
        this.controler = new PanelControler(session);
    }

    @objid ("79ff675c-5b8e-465c-93d3-048705c78057")
    @Override
    public void addListener(IPanelListener l) {
        this.controler.addListener(l);
    }

    @objid ("9914570b-44af-4b22-90d7-2c8b7f02b137")
    @Override
    public Composite createPanel(Composite parent) {
        return this.controler.createUi(parent);
    }

    @objid ("7e45e5f0-ffd9-4eaa-b4c8-277ea3b098f0")
    @Override
    public void dispose() {
        this.controler.dispose();
    }

    @objid ("7557b166-742b-4633-8654-ac488fa3fc9f")
    @Override
    public String getHelpTopic() {
        return null;
    }

    @objid ("ab59b4c2-a129-4f08-89a4-96f93fb0e44a")
    @Override
    public List<MutableElementScope> getInput() {
        return this.controler.getData();
    }

    @objid ("366930d0-cad2-4d1e-b94d-67cbca3b8a6e")
    @Override
    public Control getPanel() {
        return this.controler.getUi();
    }

    @objid ("136d9eea-0cdb-435b-8ce3-d2826df4480c")
    @Override
    public boolean isRelevantFor(Object obj) {
        return false;
    }

    @objid ("b0bc5cbe-bfe1-437e-8240-eb2169aab622")
    @Override
    public void removeListener(IPanelListener l) {
        this.controler.removeListener(l);
    }

    @objid ("4f349367-3371-4bce-a633-af3af3e8c0a3")
    @SuppressWarnings ("unchecked")
    @Override
    public void setInput(Object input) {
        if (input instanceof List<?>) {
            this.controler.setData((List<MutableElementScope>) input);
        } else {
            this.controler.setData(null);
        }
        
    }

    @objid ("18939feb-c923-4307-8875-6aed6459b883")
    public void setEnabled(boolean editable) {
        this.controler.setEnabled(editable);
    }

    @objid ("80667bb7-4113-40d0-a5f7-a7ff3c6ca07f")
    private static boolean isApplicableOn(Stereotype stereotype, MClass metaclass) {
        String baseMetaclass = stereotype.getBaseClassName();
        MClass stereotypeMetaclass = metaclass.getMetamodel().getMClass(baseMetaclass);
        return metaclass.hasBase(stereotypeMetaclass);
    }

    @objid ("e9b577b2-9949-4054-a864-2c681cb5562a")
    private static class PanelControler {
        @objid ("04cd5e26-d9d7-4587-9948-8825328fc06b")
        private boolean isEditable = true;

        @objid ("316d4f9f-6025-47e0-818c-b5e5f4d260a0")
        private Image inheritIcon;

        @objid ("d7c79966-53f6-48a5-971b-76f532065c66")
        private Image noinheritIcon;

        @objid ("a1ec45f0-0763-4996-aa99-71b86b3a4ce8")
        private PanelUI ui;

        @objid ("a3b05ef0-e949-48cb-a508-0e718df2fd74")
        private final List<IPanelListener> listeners = new ArrayList<>();

        @objid ("f9c89b67-9d58-4598-a2e9-2cfb4c9158e0")
        private List<MutableElementScope> data;

        @objid ("5ea1609f-dedf-4bec-ab40-c797fc36244c")
        private final MMetamodel metamodel;

        @objid ("c1f15a5a-e0f5-4076-80fe-69d9a2c422c7")
        private ICoreSession session;

        @objid ("00d5b8ae-fbf5-40b9-a823-06c157e7e01c")
        public  PanelControler(ICoreSession session) {
            this.session = session;
            this.metamodel = session.getMetamodel();
            
        }

        @objid ("29df7fe9-73c5-4b8d-ba42-050e5a822f42")
        public void setEnabled(boolean editable) {
            this.isEditable = editable;
            this.ui.update(this.data,this.isEditable);
            
        }

        @objid ("08e76efe-8316-4757-8739-8afee00df9b3")
        public List<MutableElementScope> getData() {
            return this.data;
        }

        @objid ("a18558da-0f2d-4b7e-9d9a-3c1ad745084a")
        public void setData(List<MutableElementScope> data) {
            this.data = data;
            if (this.ui != null) {
                this.ui.update(this.data,this.isEditable);
            }
            
        }

        @objid ("6f825935-fe83-4beb-901d-8af36687fe60")
        public Composite createUi(Composite parent) {
            this.ui = new PanelUI(parent, this);
            this.ui.update(this.data,this.isEditable);
            return this.ui.getComposite();
        }

        @objid ("abbaa5f8-9db5-4ab4-beec-cec90658f4db")
        public Control getUi() {
            return this.ui.getComposite();
        }

        @objid ("68709597-15ee-43eb-8853-bffa3deb5188")
        public void dispose() {
            this.ui.dispose();
            this.ui = null;
            
        }

        @objid ("875ec98d-bd4a-4e71-8677-66224748fdc8")
        private void fireListeners(List<MutableElementScope> scopeData, boolean isValidate) {
            this.listeners.forEach(l -> l.dataChanged(scopeData, isValidate));
        }

        @objid ("24636c4c-ef61-4c1d-8aa7-0b2456e6c6d9")
        public synchronized void addListener(IPanelListener l) {
            if (this.listeners.contains(l)) {
                throw new IllegalArgumentException(l + " Listener already registered");
            }
            this.listeners.add(l);
            
        }

        @objid ("9ab922d5-28dd-4c55-9d52-1e69e7538f64")
        public synchronized void removeListener(IPanelListener l) {
            this.listeners.remove(l);
        }

        /**
         * Called when a new scope is added to the list
         */
        @objid ("acefc10e-bb83-444e-a095-236d9fd9dcc9")
        private void onScopeAdded() {
            MutableElementScope scope = new MutableElementScope(this.metamodel.getMClass(NameSpace.class), null);
            scope.setWithSubClasses(true);
            this.data.add(scope);
            this.ui.update(this.data,this.isEditable);
            fireListeners(this.data, true);
            
        }

        /**
         * Called when the metaclass of a Scope from the list is modified.
         * @param scope the modified scope
         * @param value the modified metaclass
         */
        @objid ("fe960591-769a-4406-949f-cdf36a8660e7")
        private void onScopeMetaclassChanged(MutableElementScope scope, MClass value) {
            MClass oldValue = scope.getMetaclass();
            if (!Objects.equals(oldValue, value)) {
                scope.setMetaclass(value);
                if (value != null && value.isAbstract()) {
                    scope.setWithSubClasses(true);
                }
            
                if (scope.getStereotype() != null) {
                    Stereotype stereotype = scope.getStereotype();
            
                    if (!isApplicableOn(stereotype, value)) {
                        scope.setStereotype(null);
                    }
                }
            
                this.ui.update(this.data,this.isEditable);
            }
            fireListeners(this.data, true);
            
        }

        /**
         * Called when one or several scopes are removed from the list.
         * @param selectedScopes the scopes being removed from the list.
         */
        @objid ("de9f605f-e34b-433e-b8e1-ad6ff52b633d")
        private void onScopeRemoved(List<MutableElementScope> selectedScopes) {
            this.data.removeAll(selectedScopes);
            this.ui.update(this.data,this.isEditable);
            fireListeners(this.data, true);
            
        }

        /**
         * Called when the scope list selection changes.
         */
        @objid ("df51a219-0f75-41c4-afb0-02cd5bae0880")
        private void onScopeSelected(List<MutableElementScope> selectedScopes) {
            if(this.isEditable) {
                this.ui.scopeRemoveButton.setEnabled(selectedScopes.size() > 0);
            }
            
        }

        /**
         * Called when the sterotype value of a Scope from the list is modified.
         * @param value either a {@link String} or a {@link Stereotype}
         */
        @objid ("aba5c8b4-35fc-4ae9-b987-627ebaa44517")
        private void onScopeStereotypeChanged(MutableElementScope scope, Object value) {
            Stereotype stereotype = null;
            
            if (value instanceof Stereotype) {
                stereotype = (Stereotype) value;
            } else if (value instanceof String) {
                // look for the best stereotype for the current metaclass
                stereotype = findBestStereotype(this.session, scope, (String) value);
            } else {
                throw new IllegalArgumentException(String.valueOf(value));
            }
            
            if (stereotype != null) {
                scope.setStereotype(stereotype);
                if (stereotype.isIsAbstract()) {
                    scope.setWithSubStereotypes(true);
                }
            
                if (!isApplicableOn(stereotype, scope.getMetaclass())) {
                    String baseMetaclass = stereotype.getBaseClassName();
                    MClass stereotypeMetaclass = this.metamodel.getMClass(baseMetaclass);
                    scope.setMetaclass(stereotypeMetaclass);
                    if (stereotypeMetaclass != null && stereotypeMetaclass.isAbstract()) {
                        scope.setWithSubClasses(true);
                    }
                }
            }
            
            this.ui.update(this.data,this.isEditable);
            
            fireListeners(this.data, true);
            
        }

        @objid ("9b4a6fd7-7cde-47f3-89e6-5bae23e9a864")
        private boolean canEditScope(MutableElementScope element) {
            return element != null;
        }

        @objid ("aba2fdd0-0e4f-4e25-ac94-8541bae7558c")
        private Stereotype findBestStereotype(ICoreSession session, MutableElementScope scope, String aName) {
            Stereotype stereotype = null;
            for (Stereotype stereo : session.getModel().findByClass(Stereotype.class)) {
                if (stereo.getName().equals(aName)) {
                    String baseClassName = stereo.getBaseClassName();
                    MClass stereotypeMetaclass = this.metamodel.getMClass(baseClassName);
                    if (stereotypeMetaclass != null) {
                        if (stereotypeMetaclass.equals(scope.getMetaclass())) {
                            // direct return
                            return stereo;
                        } else if (isApplicableOn(stereotype, scope.getMetaclass())) {
                            // retain as best match
                            stereotype = stereo;
                        }
                    }
            
                    if (stereotype == null) {
                        // retain as best match
                        stereotype = stereo;
                    }
                }
            }
            return stereotype;
        }

        @objid ("73a95c95-80fc-4f26-9ae4-1d4e53a4296e")
        private void onScopeChanged(MutableElementScope scope) {
            this.ui.update(this.data,this.isEditable);
            fireListeners(this.data, true);
            
        }

    }

    @objid ("b2fdee21-c11e-4570-9d1f-7722d72b2111")
    private static class PanelUI {
        @objid ("5f70b173-fbfb-4dfa-8285-1356fb934813")
        private final Composite top;

        @objid ("feb674bf-55c7-4408-88e4-638d1095016b")
        private TableViewerColumn metaclassCol;

        @objid ("36eedb68-263e-4ec2-b4d9-9effaa4fc6fb")
        protected final Button scopeAddButton;

        @objid ("ceffdf4f-af15-4eca-ab1c-c8c8c9d4bc1a")
        protected final Button scopeRemoveButton;

        @objid ("6a47bc91-fd8d-47f8-a92c-e29a36ebc2cf")
        private TableViewer scopeTable;

        @objid ("4eeb6852-ee80-459e-a1f2-0d83f6f8fed3")
        private TableViewerColumn stereotypeCol;

        @objid ("730dae9f-cd84-41b8-945c-a743c93b7de4")
        private TableViewerColumn withSubClassCol;

        @objid ("3d8dc66a-9d47-4a47-ae90-bcc78ee39915")
        private TableViewerColumn withSubStereoCol;

        @objid ("2c730e71-47d8-4e58-9936-565be32c87f3")
        private Image inheritIcon;

        @objid ("ad2ebc1f-fd8d-4431-8ab7-dd98e459d5f8")
        private Image noinheritIcon;

        @objid ("162d0354-cbf4-4ed4-9202-9999cb5c5297")
        private final PanelControler controller;

        @objid ("7677ec01-d237-4b08-aa81-548a985ae62f")
        public  PanelUI(Composite parent, PanelControler controller) {
            this.inheritIcon = CoreUi.getImageDescriptor("icons/inherit.png").createImage();
            this.noinheritIcon = CoreUi.getImageDescriptor("icons/noinherit.png").createImage();
            
            this.controller = controller;
            
            this.top = new Composite(parent, SWT.NONE);
            final GridLayout topLayout = new GridLayout(2, false);
            topLayout.marginHeight = 0;topLayout.marginWidth = 0;
            
            this.top.setLayout(topLayout);
            
            // Scope table
            Composite tableComposite = new Composite(top, SWT.NONE);
            tableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
            
            TableColumnLayout tableLayout = new TableColumnLayout();
            tableComposite.setLayout(tableLayout);
            
            
            this.scopeTable = new TableViewer(tableComposite, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
            this.scopeTable.getTable().setHeaderVisible(true);
            this.scopeTable.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            this.scopeTable.setContentProvider(new ArrayContentProvider());
            this.scopeTable.getTable().setHeaderBackground(UIColor.SWT_WIDGET_BACKGROUND);
            
            this.scopeTable.addSelectionChangedListener(event -> {
                List<MutableElementScope> selectedScopes = SelectionHelper.toList(this.scopeTable.getSelection(), MutableElementScope.class);
                this.controller.onScopeSelected(selectedScopes);
            });
            
            
            
            final PanelControler lcontroller = this.controller;
            final Table scopeSwtTable = this.scopeTable.getTable();
            ColumnViewerToolTipSupport.enableFor(this.scopeTable);
            
            // Metaclass column
            this.metaclassCol = new TableViewerColumn(this.scopeTable, SWT.NONE);
            this.metaclassCol.getColumn().setText(CoreUi.I18N.getString("ScopeEditionPanel.metaclass.column"));
            this.metaclassCol.getColumn().setToolTipText(CoreUi.I18N.getString("ScopeEditionPanel.metaclass.column.tooltip"));
            this.metaclassCol.setLabelProvider(new ColumnLabelProvider() {
                @Override
                public String getText(Object element) {
                    MutableElementScope p = (MutableElementScope) element;
                    return p.getMetaclass().getName();
                }
            
                @Override
                public Image getImage(Object element) {
                    return MetamodelImageService.getIcon(((MutableElementScope) element).getMetaclass());
                }
            
                @Override
                public String getToolTipText(Object element) {
                    return CoreUi.I18N.getString("ScopeEditionPanel.metaclass.column.tooltip");
                }
            });
            this.metaclassCol.getColumn().setWidth(200);
            
            
            tableLayout.setColumnData(this.metaclassCol.getColumn(), new ColumnWeightData(2, 30, true));
            this.metaclassCol.setEditingSupport(new MetaclassEditSupport(this.scopeTable, lcontroller, scopeSwtTable));
            
            // With inherited metaclass column
            this.withSubClassCol = new TableViewerColumn(this.scopeTable, SWT.NONE);
            this.withSubClassCol.getColumn().setText(CoreUi.I18N.getString("ScopeEditionPanel.withSubClass.column"));
            this.withSubClassCol.getColumn().setToolTipText(CoreUi.I18N.getString("ScopeEditionPanel.withSubClass.tooltip"));
            this.withSubClassCol.getColumn().setResizable(false);
            this.withSubClassCol.setLabelProvider(new CellLabelProvider() {
                @Override
                public void update(ViewerCell cell) {
                    MutableElementScope s = (MutableElementScope) cell.getElement();
                    cell.setImage((s.withSubClasses) ? PanelUI.this.inheritIcon : PanelUI.this.noinheritIcon);
                }
            
                @Override
                public String getToolTipText(Object element) {
                    MutableElementScope s = (MutableElementScope) element;
                    return s.isWithSubClasses() ? CoreUi.I18N.getString("ScopeEditionPanel.withSubClass.tooltip.yes")
                            : CoreUi.I18N.getString("ScopeEditionPanel.withSubClass.tooltip.no");
                }
            });
            
            
            this.withSubClassCol.getColumn().setWidth(200);
            this.withSubClassCol.setEditingSupport(new WithSubClassesEditingSupport(this.scopeTable, controller));
            
            tableLayout.setColumnData(this.withSubClassCol.getColumn(), new ColumnPixelData(24, false, true));
            
            // Stereotype column
            this.stereotypeCol = new TableViewerColumn(this.scopeTable, SWT.NONE);
            this.stereotypeCol.getColumn().setText(CoreUi.I18N.getString("ScopeEditionPanel.stereotype.column"));
            this.stereotypeCol.setLabelProvider(new StereotypeColumnLabelProvider());
            this.stereotypeCol.getColumn().setToolTipText(CoreUi.I18N.getString("ScopeEditionPanel.stereotype.column.tooltip"));
            this.stereotypeCol.setEditingSupport(
                    new StereotypeEditSupport(this.scopeTable, scopeSwtTable, lcontroller));
            
            this.stereotypeCol.getColumn().setWidth(200);
            
            tableLayout.setColumnData(this.stereotypeCol.getColumn(), new ColumnWeightData(2, 30, true));
            
            // With sub stereotypes column
            this.withSubStereoCol = new TableViewerColumn(this.scopeTable, SWT.NONE);
            this.withSubStereoCol.getColumn().setText(CoreUi.I18N.getString("ScopeEditionPanel.withSubStereotypes.column"));
            this.withSubStereoCol.getColumn().setToolTipText(CoreUi.I18N.getString("ScopeEditionPanel.withSubStereotypes.tooltip"));
            this.withSubStereoCol.getColumn().setResizable(false);
            
            this.withSubStereoCol.setLabelProvider(new CellLabelProvider() {
                @Override
                public void update(ViewerCell cell) {
                    MutableElementScope s = (MutableElementScope) cell.getElement();
                    cell.setImage((s.withSubStereotypes) ? PanelUI.this.inheritIcon : PanelUI.this.noinheritIcon);
                }
            
                @Override
                public String getToolTipText(Object element) {
                    MutableElementScope s = (MutableElementScope) element;
                    return s.withSubStereotypes ? CoreUi.I18N.getString("ScopeEditionPanel.withSubStereotypes.tooltip.yes")
                            : CoreUi.I18N.getString("ScopeEditionPanel.withSubStereotypes.tooltip.no");
                }
            });
            
            this.withSubStereoCol.setEditingSupport(new WithSubStereotypeEditSupport(this.scopeTable, controller));
            this.withSubStereoCol.getColumn().setWidth(200);
            
            tableLayout.setColumnData(this.withSubStereoCol.getColumn(), new ColumnPixelData(24, false, true));
            
            // Button composite
            Composite buttons = new Composite(top, SWT.NONE);
            final GridLayout buttonsLayout = new GridLayout(1, false);
            buttonsLayout.marginHeight = buttonsLayout.marginWidth = buttonsLayout.verticalSpacing = 0;
            buttons.setLayout(buttonsLayout);
            
            buttons.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
            
            // Add scope
            this.scopeAddButton = new Button(buttons, SWT.FLAT);
            this.scopeAddButton.setImage(UIImages.ADD);
            this.scopeAddButton.setToolTipText(CoreUi.I18N.getString("ScopeEditionPanel.add.tooltip"));
            this.scopeAddButton.setEnabled(true);
            this.scopeAddButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
            this.scopeAddButton.addListener(SWT.Selection, event -> this.controller.onScopeAdded());
            
            // Remove scope
            this.scopeRemoveButton = new Button(buttons, SWT.FLAT);
            this.scopeRemoveButton.setImage(UIImages.DELETE);
            this.scopeRemoveButton.setToolTipText(CoreUi.I18N.getString("ScopeEditionPanel.remove.tooltip"));
            this.scopeRemoveButton.setEnabled(false);
            this.scopeRemoveButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
            this.scopeRemoveButton.addListener(SWT.Selection, event -> {
                List<MutableElementScope> selectedScopes = SelectionHelper.toList(this.scopeTable.getSelection(), MutableElementScope.class);
                this.controller.onScopeRemoved(selectedScopes);
            });
            
        }

        @objid ("3596190b-ced8-480f-8c1f-d084e8eabcd3")
        public void update(List<MutableElementScope> data, boolean editable) {
            if (data != null) {
                // Update scope
                this.scopeTable.setInput(data);
                this.scopeTable.refresh(true);
            }
            
            this.scopeAddButton.setEnabled(editable);
            this.scopeRemoveButton.setEnabled(editable);
            this.scopeTable.getControl().setEnabled(editable);
            
        }

        @objid ("2fbec8bb-40f3-453f-8a19-f5a3971f3f16")
        public void dispose() {
            if (this.inheritIcon != null)
                this.inheritIcon.dispose();
            if (this.noinheritIcon != null)
                this.noinheritIcon.dispose();
            this.top.dispose();
            
        }

        @objid ("b7e0ceae-285c-46aa-8ed1-f1b1b2152831")
        public Composite getComposite() {
            return this.top;
        }

        @objid ("3c16341d-8685-4fea-9ad4-7f7fd407614e")
        private static final class StereotypeColumnLabelProvider extends ColumnLabelProvider {
            @objid ("c1309bb3-0b76-4941-9b85-2d60d159b0b3")
            @Override
            public String getText(Object element) {
                MutableElementScope p = (MutableElementScope) element;
                if (p.getStereotype() != null) {
                    return p.getStereotype().getName();
                }
                return "";
            }

            @objid ("ec194bff-d12d-46b0-aaa3-2b1cffda68a8")
            public  StereotypeColumnLabelProvider() {
                
            }

            @objid ("fc032aea-5775-48b2-95d1-f256dad2f0e1")
            @Override
            public Image getImage(Object element) {
                MutableElementScope p = (MutableElementScope) element;
                if (p.getStereotype() != null) {
                    final Image icon = MdaResources.getIcon(p.getStereotype());
                    return icon != null ? icon : null;
                }
                return null;
            }

            @objid ("8ebeb087-2332-493b-b941-21da2b7a79ec")
            @Override
            public String getToolTipText(Object element) {
                return CoreUi.I18N.getString("ScopeEditionPanel.stereotype.column.tooltip");
            }

        }

        @objid ("123eaf60-6b18-4208-b11c-c774a9c955a7")
        private static final class StereotypeEditSupport extends EditingSupport {
            @objid ("5513febc-28bb-4f93-9075-80ffba1a3048")
            private final Table scopeSwtTable;

            @objid ("3b4cd560-5354-4a69-b7dc-eae1e8ed9fa4")
            private final PanelControler lcontroller;

            @objid ("b4fa920b-0775-4df8-a4cb-d0b71230b55e")
            private  StereotypeEditSupport(ColumnViewer viewer, Table scopeSwtTable, PanelControler lcontroller) {
                super(viewer);
                this.scopeSwtTable = scopeSwtTable;
                this.lcontroller = lcontroller;
                
            }

            @objid ("d9cab2e5-5e1c-4dd3-b5d9-cd1f95cc7ceb")
            @Override
            protected void setValue(Object element, Object value) {
                this.lcontroller.onScopeStereotypeChanged((MutableElementScope) element, value);
            }

            @objid ("b389a721-1c6a-456e-94cd-e3cebd3365da")
            @Override
            protected Object getValue(Object element) {
                MutableElementScope scope = (MutableElementScope) element;
                Stereotype stereotype = scope.getStereotype();
                
                if (stereotype == null) {
                    return "";
                }
                return stereotype.getName();
            }

            @objid ("821241fb-8f80-4510-a3e4-10712d4e5bcb")
            @Override
            protected CellEditor getCellEditor(Object element) {
                MClass metaclass = ((MutableElementScope) element).getMetaclass();
                
                Predicate<Stereotype> stereotypeFilter = (metaclass == null) ? null : (stereotype) -> isApplicableOn(stereotype, metaclass);
                return new StereotypeCellEditor(
                        this.scopeSwtTable,
                        SWT.TOP | SWT.LEFT,
                        getAvailableStereotypes(),
                        stereotypeFilter);
                
            }

            @objid ("fc71af2c-10c5-4f57-9193-85339ad3e07d")
            @Override
            protected boolean canEdit(Object element) {
                return this.lcontroller.canEditScope((MutableElementScope) element);
            }

            @objid ("f59ee044-bc6a-4402-8902-b1e4b24cee6a")
            private Collection<Stereotype> getAvailableStereotypes() {
                return this.lcontroller.session.getModel().findByClass(Stereotype.class);
            }

        }

        @objid ("ec96e88c-839a-42bb-ae11-bd51724c69c7")
        private static final class MetaclassEditSupport extends EditingSupport {
            @objid ("0ac97053-34b2-4aa2-b627-dc90dc43b1de")
            private final Table scopeSwtTable;

            @objid ("7a21ad24-b706-4075-b318-3ac901106f93")
            private final PanelControler lcontroller;

            @objid ("0bb5f386-7806-43c4-9c6b-65708a48a4a4")
            private  MetaclassEditSupport(ColumnViewer viewer, PanelControler lcontroller, Table scopeSwtTable) {
                super(viewer);
                this.lcontroller = lcontroller;
                this.scopeSwtTable = scopeSwtTable;
                
            }

            @objid ("71f28a71-232d-43dc-9999-b673608de08c")
            @Override
            protected void setValue(Object element, Object value) {
                this.lcontroller.onScopeMetaclassChanged((MutableElementScope) element, (MClass) value);
            }

            @objid ("7dada232-ecc8-4e91-b6d9-0453d246037c")
            @Override
            protected Object getValue(Object element) {
                return ((MutableElementScope) element).getMetaclass();
            }

            @objid ("58ea62fe-dc25-4bd6-95f0-a1d826c07856")
            @Override
            protected CellEditor getCellEditor(Object element) {
                return new MetaclassCellEditor(this.scopeSwtTable, this.lcontroller.metamodel);
            }

            @objid ("571c275b-9d79-4b3d-872c-b64e79913401")
            @Override
            protected boolean canEdit(Object element) {
                return this.lcontroller.canEditScope((MutableElementScope) element);
            }

        }

        @objid ("fa8a4498-ba49-4a68-a20c-b0303026b36a")
        private static final class WithSubStereotypeEditSupport extends EditingSupport {
            @objid ("1b2e2ec0-1103-43a7-9d7e-547d28776e84")
            private final PanelControler controller;

            @objid ("c745a95e-0e36-425a-81e8-32703a0305df")
            private  WithSubStereotypeEditSupport(ColumnViewer viewer, PanelControler controller) {
                super(viewer);
                this.controller = controller;
                
            }

            @objid ("5c895dea-5fc9-4901-ac41-83373e2b0b47")
            @Override
            protected void setValue(Object element, Object value) {
                MutableElementScope mutableScope = (MutableElementScope) element;
                mutableScope.setWithSubStereotypes((boolean) value);
                this.controller.onScopeChanged(mutableScope);
                
            }

            @objid ("c660ab5d-8f64-463b-8bba-d43467fc7943")
            @Override
            protected Object getValue(Object element) {
                return ((MutableElementScope) element).isWithSubStereotypes();
            }

            @objid ("89c85920-be77-4453-90d2-55c9394046f7")
            @Override
            protected CellEditor getCellEditor(Object element) {
                return new CheckboxCellEditor();
            }

            @objid ("972a95fe-5963-48ee-9886-4228a6156b5e")
            @Override
            protected boolean canEdit(Object element) {
                MutableElementScope s = (MutableElementScope) element;
                Stereotype stereotype = s.getStereotype();
                return stereotype != null && !stereotype.isIsAbstract() && this.controller.canEditScope(s);
            }

        }

        @objid ("242dbb5e-c14e-431f-8abe-6318a9a14cb7")
        private static final class WithSubClassesEditingSupport extends EditingSupport {
            @objid ("5b586f04-6230-4fd1-8ec0-564f89386bb8")
            private final PanelControler controller;

            @objid ("ca6a424b-a71c-4744-a4c2-6fb2657b4cc6")
            private  WithSubClassesEditingSupport(ColumnViewer viewer, PanelControler controller) {
                super(viewer);
                this.controller = controller;
                
            }

            @objid ("17f422e8-d91a-4164-a2bf-134d69275aac")
            @Override
            protected void setValue(Object element, Object value) {
                MutableElementScope mutableScope = (MutableElementScope) element;
                mutableScope.setWithSubClasses((boolean) value);
                this.controller.onScopeChanged(mutableScope);
                
            }

            @objid ("9621a3f4-44bd-4e26-8dd5-a38c33b0a14a")
            @Override
            protected Object getValue(Object element) {
                return ((MutableElementScope) element).isWithSubClasses();
            }

            @objid ("28b67ddd-086f-411c-88a2-5daa4c713f7c")
            @Override
            protected CellEditor getCellEditor(Object element) {
                return new CheckboxCellEditor();
            }

            @objid ("2e4fb942-1867-43a1-a2e3-fb0de308a24f")
            @Override
            protected boolean canEdit(Object element) {
                MutableElementScope s = (MutableElementScope) element;
                MClass metaclass = s.getMetaclass();
                return metaclass != null && !metaclass.isAbstract() && this.controller.canEditScope(s);
            }

        }

        /**
         * Workaround bug on Windows where room for images is always reserved on the column even if no item on the column has image.
         * 
         * @author cma
         */
        @objid ("648116f7-dca5-4073-9618-6fd356cfddc9")
        private static abstract class WorkaroundColumnLabelProvider extends OwnerDrawLabelProvider implements IColorProvider {
            /**
             * @param element the represented element
             * @return the text to display
             */
            @objid ("38f37206-2bcb-44d0-9ca1-eefdb00558e7")
            protected abstract String getText(Object element);

            /**
             * Return the text size
             */
            @objid ("bd54150f-87ca-4c54-b3dc-908f8959118e")
            @Override
            protected void measure(Event event, Object element) {
                Point size = event.gc.textExtent(getText(element));
                event.setBounds(new Rectangle(event.x, event.y, size.x, size.y));
                
            }

            /**
             * Draw text on top left
             */
            @objid ("b8e7ef61-4573-4b56-a7f0-6a25be93ec56")
            @Override
            protected void paint(Event event, Object element) {
                Rectangle bounds = event.getBounds();
                event.gc.drawText(getText(element), bounds.x, bounds.y, true);
                
            }

            @objid ("769163b4-c146-4eb0-9735-c4642a96bab3")
            @Override
            public Color getBackground(Object element) {
                return null;
            }

            @objid ("54cdbb69-a304-48af-b631-b0bf31f54708")
            @Override
            public Color getForeground(Object element) {
                return null;
            }

        }

    }

}
