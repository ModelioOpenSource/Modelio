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
import java.util.Arrays;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.common.embeddeddiagram.GmEmbeddedDiagram;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLayer;

@objid ("dd3e99ab-5e44-4ebb-8608-d09b0bb05638")
public class GmTreeView {
    @objid ("ce794a59-36dc-4ad4-a4f5-9c1fca8d2ea6")
    private TreeViewer treeViewer;

    @objid ("0cce793b-db6b-47be-bbe3-88a8608d149a")
    public GmTreeView(Composite parent) {
        this.treeViewer = new TreeViewer(parent, SWT.V_SCROLL | SWT.H_SCROLL);
        this.treeViewer.setLabelProvider(new GmTreeLabelProvider());
        this.treeViewer.setContentProvider(new GmTreecontentProvider());
    }

    @objid ("197af0dd-3b5d-4269-8ec3-8b9c5301c435")
    public TreeViewer getTreeViewer() {
        return this.treeViewer;
    }

    @objid ("edd4b1a1-0bb0-4d1b-b80a-1aea3ef6cdc0")
    public void setInput(IGmDiagram gmDiagram) {
        this.treeViewer.setInput(Arrays.asList(gmDiagram));
    }

    @objid ("b9151ffe-3eef-4e60-b882-b6731fd4ec0f")
    public void setSelection(StructuredSelection selection, boolean reveal) {
        this.treeViewer.setSelection(selection, reveal);
    }

    @objid ("e6971b4b-9194-4086-bf3a-7b0a0ebc75af")
    class GmTreeLabelProvider extends LabelProvider {
        @objid ("5cfa8149-ef10-4146-9615-72493495a53e")
        @Override
        public String getText(Object element) {
            return Formatter.toString(element);
        }

        @objid ("7022cd87-0d2e-41e6-ab83-1a8572a953e7")
        @Override
        public Image getImage(Object element) {
            if (element instanceof GmModel) {
                GmModel gm = (GmModel) element;
                if (gm.getRepresentedElement() != null) {
                    return ElementImageService.getIcon(gm.getRepresentedElement());
                }
            }
            return null;
        }

    }

    @objid ("85167c36-29d3-4dd8-8f7f-02338df76459")
    class GmTreecontentProvider implements ITreeContentProvider {
        @objid ("0802929b-c494-4f7f-b40f-eceef6d673c0")
        @Override
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            // Do nothing
        }

        @objid ("d4085c7d-4fa9-41a7-8760-e5baddaf5355")
        @Override
        public Object[] getElements(Object inputElement) {
            return ((List<?>) inputElement).toArray();
        }

        @objid ("ac5d7713-cad0-4323-a153-ff1669bb9cf3")
        @Override
        public Object[] getChildren(Object parent) {
            List<Object> results = new ArrayList<>();
            
            if (parent instanceof GmEmbeddedDiagram) {
                for (GmNodeModel child : ((GmEmbeddedDiagram) parent).getVisibleChildren()) {
                    results.add(child.getDiagram());
                }
            } else if (parent instanceof GmCompositeNode) {
                results.addAll(((GmCompositeNode) parent).getChildren());
            } else if (parent instanceof IGmDrawingLayer) {
                IGmDrawingLayer layer = (IGmDrawingLayer) parent;
                results.addAll(layer.getNodes());
                results.addAll(layer.getStartingDrawingLinks());
            }
            
            if (parent instanceof IGmLinkable) {
                results.addAll(((IGmLinkable) parent).getStartingLinks());
                results.addAll(((IGmLinkable) parent).getEndingLinks());
            }
            
            if (parent instanceof GmAbstractDiagram) {
                // results.addAll(((GmAbstractDiagram) parent).getAllDrawings());
                results.addAll(((IGmDiagram) parent).getDrawingLayers());
            }
            return results.toArray();
        }

        @objid ("12f89144-09f2-43c0-b437-ff3809812edd")
        @Override
        public Object getParent(Object element) {
            if (element instanceof GmLink) {
                return ((GmLink) element).getFrom();
            } else if (element instanceof GmNodeModel) {
                return ((GmNodeModel) element).getParent();
            } else {
                return null;
            }
        }

        @objid ("7e1e8aeb-14cd-44a8-abaa-b67d045a6243")
        @Override
        public boolean hasChildren(Object element) {
            if (element instanceof GmEmbeddedDiagram) {
                return (!((GmEmbeddedDiagram) element).getVisibleChildren().isEmpty());
            
            } else if (element instanceof GmCompositeNode) {
                return (!((GmCompositeNode) element).getChildren().isEmpty());
            
            } else if (element instanceof IGmLinkable) {
                final IGmLinkable gmNode = (IGmLinkable) element;
                return !(gmNode.getStartingLinks().isEmpty() && gmNode.getEndingLinks().isEmpty());
            
            } else if (element instanceof IGmDrawingLayer) {
                IGmDrawingLayer layer = (IGmDrawingLayer) element;
                return !layer.getNodes().isEmpty();
            
            } else {
                return false;
            }
        }

    }

}
