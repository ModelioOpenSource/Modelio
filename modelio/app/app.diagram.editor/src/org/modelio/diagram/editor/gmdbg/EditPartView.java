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

package org.modelio.diagram.editor.gmdbg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.platform.ui.CoreFontRegistry;

@objid ("8b183257-3424-413c-9856-f0469d59f5ca")
public class EditPartView {
    @objid ("67ff7527-1899-4fe4-a465-a8ad27d2c4fe")
    private TableViewer figureProps;

    @objid ("bc186c24-2870-44d5-8835-72bd814e9cae")
    private Font boldFont;

    @objid ("f511834e-db1e-4c7a-8c59-25eef5b3f206")
    public EditPartView(Composite parent) {
        this.figureProps = new TableViewer(parent, SWT.V_SCROLL);
        
        final Table table = this.figureProps.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        this.boldFont = CoreFontRegistry.getModifiedFont(table.getFont(), SWT.BOLD, 1.0f);
        
        TableViewerColumn colKey = new TableViewerColumn(this.figureProps, SWT.NONE);
        colKey.getColumn().setWidth(200);
        colKey.getColumn().setText("EditPart Property");
        colKey.setLabelProvider(new ColumnLabelProvider() {
            @SuppressWarnings ("unchecked")
            @Override
            public String getText(Object element) {
                return ((Entry<String, String>) element).getKey();
            }
        
            @Override
            public Font getFont(Object element) {
                return getText(element).startsWith(" ") ? null : EditPartView.this.boldFont;
            }
        });
        
        TableViewerColumn colValue = new TableViewerColumn(this.figureProps, SWT.NONE);
        colValue.getColumn().setWidth(150);
        colValue.getColumn().setText("Value");
        colValue.setLabelProvider(new ColumnLabelProvider() {
            @SuppressWarnings ("unchecked")
            @Override
            public String getText(Object element) {
                return ((Entry<String, String>) element).getValue();
            }
        
            @Override
            public Font getFont(Object element) {
                return ((Entry<String, String>) element).getKey().startsWith(" ") ? null : EditPartView.this.boldFont;
            }
        });
        
        this.figureProps.setContentProvider(new FigureContentProvider());
    }

    @objid ("a8cc46db-acbb-4adc-a8cb-ec94c0674021")
    public TableViewer getTableViewer() {
        return this.figureProps;
    }

    @objid ("b3142137-4738-4fd1-99cb-5bdee1a41aff")
    public void setInput(GraphicalEditPart ep) {
        this.figureProps.setInput(ep);
    }

    @objid ("7a65c200-7873-4db9-b881-f94de62fdbc1")
    private static class FigureContentProvider implements IStructuredContentProvider {
        @objid ("1d99976c-cb4f-459a-9e3a-10887fdd202d")
         List<Entry<String,String>> properties;

        @objid ("ec317497-f85d-4799-9f19-e03e7fdc244e")
        @Override
        public void dispose() {
            this.properties = null;
        }

        @objid ("6676a768-c399-47db-8973-c9afac8dbdf3")
        @Override
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            this.properties = new ArrayList<>();
            
            if (newInput == null) {
                return;
            }
            
            if (newInput instanceof AbstractGraphicalEditPart) {
                AbstractGraphicalEditPart ep = (AbstractGraphicalEditPart) newInput;
            
                this.properties.add(new XEntry("Type", ep.getClass().getSimpleName()));
            
                if (ep instanceof AbstractNodeEditPart) {
                    AbstractNodeEditPart nodeEditPart = (AbstractNodeEditPart) ep;
                    int i = 0;
                    for (EditPolicy p : nodeEditPart.getInstalledPolicies()) {
                        this.properties.add(new XEntry("    policy " + i, p.getClass().getSimpleName()));
                        i++;
                    }
                }
            
                if (ep instanceof LinkEditPart) {
                    LinkEditPart linkEditPart = (LinkEditPart) ep;
                    int i = 0;
                    for (EditPolicy p : linkEditPart.getInstalledPolicies()) {
                        this.properties.add(new XEntry("    policy " + i, p.getClass().getSimpleName()));
                        i++;
                    }
                }
            
                this.properties.add(new XEntry("    parent edit part", ep.getParent().getClass().getSimpleName()));
            
            }
        }

        @objid ("2226635d-2a1a-4893-b09c-d6659b8efbd1")
        @Override
        public Object[] getElements(Object inputElement) {
            return this.properties.toArray();
        }

    }

}
