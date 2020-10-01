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
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.GmReference;
import org.modelio.diagram.elements.core.model.IGmModelRelated;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.platform.ui.CoreFontRegistry;

@objid ("ee7ca53f-4a98-4e5d-b3b6-79f0a03cd7b7")
public class GmPropView {
    @objid ("4e966285-c87c-40eb-b368-6a87c19b670d")
    private TableViewer gmProps;

    @objid ("05501e77-5c2f-43ba-8e82-c346fd8c6ef5")
    private Font boldFont;

    @objid ("a877daa3-89c7-4019-8e78-5382cb8ab70b")
    public GmPropView(Composite parent) {
        this.gmProps = new TableViewer(parent, SWT.V_SCROLL);
        
        final Table table = this.gmProps.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        this.boldFont = CoreFontRegistry.getModifiedFont(table.getFont(), SWT.BOLD, 1.0f);
        
        TableViewerColumn colKey = new TableViewerColumn(this.gmProps, SWT.NONE);
        colKey.getColumn().setWidth(200);
        colKey.getColumn().setText("Gm Property");
        colKey.setLabelProvider(new ColumnLabelProvider() {
            @SuppressWarnings ("unchecked")
            @Override
            public String getText(Object element) {
                return ((Entry<String, String>) element).getKey();
            }
        
            @Override
            public Font getFont(Object element) {
                return getText(element).startsWith(" ") ? null : GmPropView.this.boldFont;
            }
        });
        
        TableViewerColumn colValue = new TableViewerColumn(this.gmProps, SWT.NONE);
        colValue.getColumn().setWidth(200);
        colValue.getColumn().setText("Value");
        colValue.setLabelProvider(new ColumnLabelProvider() {
            @SuppressWarnings ("unchecked")
            @Override
            public String getText(Object element) {
                return ((Entry<String, String>) element).getValue();
            }
        
            @Override
            public Font getFont(Object element) {
                return ((Entry<String, String>) element).getKey().startsWith(" ") ? null : GmPropView.this.boldFont;
            }
        });
        
        this.gmProps.setContentProvider(new GmPropsContentProvider());
    }

    @objid ("1f07d5bb-a711-4cd1-9e93-b9720f3d02a8")
    public TableViewer getTableViewer() {
        return this.gmProps;
    }

    @objid ("cc1ac923-a0a8-4c97-9b72-80140243a398")
    public void setInput(GmAbstractObject gmModel) {
        this.gmProps.setInput(gmModel);
    }

    @objid ("d8f24c52-a119-42f7-99a1-65ba30fde2c5")
    private static class GmPropsContentProvider implements IStructuredContentProvider {
        @objid ("63f61dee-38b0-435a-bdc4-90a5a84aa00a")
         List<Entry<String,String>> properties;

        @objid ("232c561c-d340-402d-887c-cdf8e779b4f3")
        @Override
        public void dispose() {
            this.properties = null;
        }

        @objid ("0e980eb8-96ea-40dc-aa15-7b97e6506738")
        @Override
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            this.properties = new ArrayList<>();
            
            if (newInput == null) {
                return;
            }
            
            if (newInput instanceof GmReference) {
                GmReference<?> gm = (GmReference<?>) newInput;
                this.properties.add(new XEntry("GmReference", ""));
            
                IGmModelRelated target = gm.getReferencedModel();
            
                this.properties.add(new XEntry("    resolved valid", Formatter.toString(gm.isReferencedModelValid())));
            
                this.properties.add(new XEntry("    target diagram ref", Formatter.toString(gm.getTargetDiagramRef())));
                this.properties.add(new XEntry("    target diagram", Formatter.toString((target != null) ? target.getDiagram() : null)));
            
                this.properties.add(new XEntry("    target model element ref", Formatter.toString(gm.getTargetElementRef())));
                this.properties.add(new XEntry("    target model element", Formatter.toString((target != null) ? gm.getReferencedModel() : null)));
            
            }
            
            if (newInput instanceof GmCompositeNode) {
                GmCompositeNode gm = (GmCompositeNode) newInput;
                this.properties.add(new XEntry("GmCompositeNode", ""));
                this.properties.add(new XEntry("    # children", Formatter.toString(gm.getChildren().size())));
            
            }
            
            if (newInput instanceof GmNodeModel) {
            
                GmNodeModel gm = (GmNodeModel) newInput;
            
                this.properties.add(new XEntry("GmNodeModel", ""));
            
                // visible
                this.properties.add(new XEntry("    Visible", Formatter.toString(gm.isVisible())));
            
                // role in compo
                this.properties.add(new XEntry("    Composition role", Formatter.toString(gm.getRoleInComposition())));
            
                // # og incoming and outgoing links
                this.properties.add(new XEntry("    # incoming links", Formatter.toString(gm.getEndingLinks().size())));
                this.properties.add(new XEntry("    # outgoing links", Formatter.toString(gm.getStartingLinks().size())));
            }
            
            // GmLink properties
            if (newInput instanceof GmLink) {
            
                GmLink gm = (GmLink) newInput;
            
                this.properties.add(new XEntry("GmLink", ""));
            
                this.properties.add(new XEntry("    # extensions", Formatter.toString(gm.getExtensions().size())));
                this.properties.add(new XEntry("    # visible extensions", Formatter.toString(gm.getVisibleExtensions().size())));
                this.properties.add(new XEntry("    # source", Formatter.toString(gm.getFrom())));
                if (gm.getFrom() != null) {
                    this.properties.add(new XEntry("      # source diagram", Formatter.toString(gm.getFrom().getDiagram())));
                }
            
                this.properties.add(new XEntry("    # target", Formatter.toString(gm.getTo())));
                if (gm.getTo() != null) {
                    this.properties.add(new XEntry("      # target diagram", Formatter.toString(gm.getTo().getDiagram())));
                }
            }
            
            // GmModel properties
            if (newInput instanceof GmModel) {
                GmModel gm = (GmModel) newInput;
            
                this.properties.add(new XEntry("GmModel", ""));
            
                // represented element
                this.properties.add(new XEntry("    Represented element", Formatter.toString(gm.getRepresentedElement())));
            
                // related element
                this.properties.add(new XEntry("    Related element", Formatter.toString(gm.getRelatedElement())));
            
                // Representation mode
                this.properties.add(new XEntry("    Representation mode", Formatter.toString(gm.getRepresentationMode())));
            
            }
            
            if (newInput instanceof GmAbstractObject) {
                GmAbstractObject gm = (GmAbstractObject) newInput;
                this.properties.add(new XEntry("GmAbstractObject", ""));
                this.properties.add(new XEntry("    Owner diagram", Formatter.toString(gm.getDiagram())));
                this.properties.add(new XEntry("    Layout Data", Formatter.toString(gm.getLayoutData())));
                this.properties.add(new XEntry("    Editable", Formatter.toString(gm.isUserEditable())));
                this.properties.add(new XEntry("    Major version", Formatter.toString(gm.getMajorVersion())));
            }
        }

        @objid ("5b124c9f-ebeb-424b-930d-f6b30be6bd15")
        @Override
        public Object[] getElements(Object inputElement) {
            return this.properties.toArray();
        }

    }

}
