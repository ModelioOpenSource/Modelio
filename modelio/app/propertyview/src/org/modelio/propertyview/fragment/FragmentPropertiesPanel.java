/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.propertyview.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.gproject.fragment.AbstractFragment;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.fragment.exml.ExmlFragment;
import org.modelio.gproject.fragment.ramcfile.MdaFragment;
import org.modelio.gproject.fragment.ramcfile.RamcFileFragment;
import org.modelio.gproject.fragment.url.UrlFragment;
import org.modelio.propertyview.fragment.model.AbstractFragmentPropertyModel;
import org.modelio.propertyview.fragment.model.ExmlFragmentPropertyModel;
import org.modelio.propertyview.fragment.model.MdaFragmentPropertyModel;
import org.modelio.propertyview.fragment.model.RamcFileFragmentPropertyModel;
import org.modelio.propertyview.fragment.model.SvnFragmentPropertyModel;
import org.modelio.propertyview.fragment.model.UrlFragmentPropertyModel;
import org.modelio.propertyview.plugin.PropertyViewPlugin;
import org.modelio.ui.panel.IPanelProvider;

/**
 * Panel in property view displaying fragment properties
 */
@objid ("54d0ec33-4d33-43e4-b213-f647cd1ca16d")
public class FragmentPropertiesPanel implements IPanelProvider {
    @objid ("9ce41242-bd88-4914-9617-d964872dc911")
    private static final int TABLE_WIDTH = 600;

    @objid ("5e86fe4f-9280-4b2a-804b-e410861c863e")
    private Composite composite;

    @objid ("c6a2b5f2-6bc0-42a5-a3b2-a1cc7afb6980")
    private IProjectFragment element;

    @objid ("13061ea3-a573-4b7b-9379-ba4f8a501500")
    private TableViewer viewer;

    @objid ("5cb21e63-aa8d-4b91-8830-c94a6adcd1c0")
    @Override
    public boolean isRelevantFor(Object input) {
        if (input instanceof ISelection) {
            return SelectionHelper.getFirst((ISelection) input, IProjectFragment.class) != null;
        } else {
            return input instanceof IProjectFragment;
        }
    }

    @objid ("20d46bf2-6ecc-4e93-b5c3-e430ef6b36a7")
    @Override
    public String getHelpTopic() {
        return PropertyViewPlugin.I18N.getString("FragmentPropertiesPanel.HELP_TOPIC");
    }

    @objid ("40279309-ef4b-4767-9fab-a888755946aa")
    @Override
    public Object createPanel(Composite parent) {
        this.composite = new Composite(parent, SWT.NONE);
        
        FillLayout layout = new FillLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        this.composite.setLayout(layout);
        
        this.viewer = new TableViewer(this.composite);
        createColumns(this.viewer);
        this.viewer.setContentProvider(new IStructuredContentProvider() {
            @Override
            public Object[] getElements(Object inputElement) {
                List<FragmentProperty> fragmentProperties = new ArrayList<>();
                for (Entry<String, String> property : ((AbstractFragmentPropertyModel)inputElement).getPropertyList().entrySet()) {
                    fragmentProperties.add(new FragmentProperty(property.getKey(), property.getValue()));
                }
                return fragmentProperties.toArray();
            }
        });
        
        Table table = this.viewer.getTable();
        table.setSize(table.computeSize(TABLE_WIDTH, 5));
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        return this.composite;
    }

    @objid ("0c7ec3b5-220f-4532-ab09-d27aff4bb3e2")
    private void createColumns(final TableViewer tableViewer) {
        String[] titles = { PropertyViewPlugin.I18N.getString("fragment.content.column.property"), PropertyViewPlugin.I18N.getString("fragment.content.column.value") };
        
        TableViewerColumn col = new TableViewerColumn(tableViewer, SWT.NONE);
        col.getColumn().setText(titles[0]);
        col.getColumn().setWidth(TABLE_WIDTH / 2);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object elem) {
                FragmentProperty f = (FragmentProperty) elem;
                return f.getName();
            }
        });
        
        col = new TableViewerColumn(tableViewer, SWT.NONE);
        col.getColumn().setText(titles[1]);
        col.getColumn().setWidth(TABLE_WIDTH / 2);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object elem) {
                FragmentProperty f = (FragmentProperty) elem;
                return f.getValue();
            }
        });
    }

    @objid ("ccd736fa-7c11-4b36-ad74-9bb2626c56f8")
    @Override
    public Object getPanel() {
        return this.composite;
    }

    @objid ("2eecc433-268a-4038-b3ec-7ad83f2a4d4e")
    @Override
    public Object getInput() {
        return this.element;
    }

    @objid ("cb3ec5dd-3f41-4d76-92df-024571339916")
    @Override
    public void setInput(Object input) {
        AbstractFragment fragment;
        if (input instanceof ISelection) {
            fragment = SelectionHelper.getFirst((ISelection) input, AbstractFragment.class);
        } else if (input instanceof IProjectFragment) {
            fragment = (AbstractFragment) input;
        } else {
            fragment = null;
        }
        
        if (fragment != null) {
            this.element = fragment;
            displayTableData(fragment);
        }
    }

    /**
     * Display differents fragment properties according to the fragment type
     * 
     * @param fragment the fragment to display
     */
    @objid ("5ce639d4-c2fa-4bf6-ab60-38ddd73539ba")
    private void displayTableData(AbstractFragment fragment) {
        AbstractFragmentPropertyModel dataModel = null;
        switch (fragment.getType()) {
        case EXML:
            dataModel = new ExmlFragmentPropertyModel((ExmlFragment)fragment);
            break;
        case EXML_SVN:
            dataModel = new SvnFragmentPropertyModel(fragment);
            break;
        case EXML_URL:
            dataModel = new UrlFragmentPropertyModel((UrlFragment)fragment);
            break;
        case MDA:
            dataModel = new MdaFragmentPropertyModel((MdaFragment)fragment);
            break;
        case RAMC:
            dataModel = new RamcFileFragmentPropertyModel((RamcFileFragment)fragment);
            break;
        default:
            dataModel = new AbstractFragmentPropertyModel(fragment);
            break;
        }
        
        this.viewer.setInput(dataModel);
        this.viewer.refresh();
    }

    @objid ("0f949e74-fdd6-484b-80e5-7afc528ad49a")
    @Override
    public void dispose() {
    }

    /**
     * Class representing table elements
     * First column is the property name
     * Second column is the property value
     */
    @objid ("5a33c1fb-47e9-4589-8afc-7bd877091196")
    public class FragmentProperty {
        @objid ("38b20809-f7ee-458a-8938-2ff4d8616853")
        private String name;

        @objid ("1b38b7b4-a17e-4775-b206-ee804ae85269")
        private String value;

        /**
         * An instance of this class represent a table line
         * 
         * @param name the property name
         * @param value the property value
         */
        @objid ("7b0bdcf1-a8c1-4fee-ae7f-478d6a20be43")
        public FragmentProperty(String name, String value) {
            this.name = name;
            this.value = value;
        }

        /**
         * Set property name
         * 
         * @param name the property name
         */
        @objid ("bb4a8918-405c-4006-9460-de789d33dcb9")
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Get property name
         * 
         * @return the property name
         */
        @objid ("9025ec17-221c-472f-83a9-2455890bc0d0")
        public String getName() {
            return this.name;
        }

        /**
         * Set property value
         * 
         * @param value the property value
         */
        @objid ("f741c6f2-e723-4932-91ed-44e235f9f2ee")
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Get property value
         * 
         * @return the property value
         */
        @objid ("38735efb-2619-4f2d-a27e-e569542a588c")
        public String getValue() {
            return this.value;
        }

    }

}
