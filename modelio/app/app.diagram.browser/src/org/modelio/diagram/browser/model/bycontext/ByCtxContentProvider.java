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

package org.modelio.diagram.browser.model.bycontext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.diagram.browser.model.core.DiagramRef;
import org.modelio.diagram.browser.model.core.VirtualFolder;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.visitors.DefaultInfrastructureVisitor;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;

/**
 * The 'ByContext' model displays the diagrams grouped by owners
 */
@objid ("002bc776-0d4f-10c6-842f-001ec947cd2a")
public class ByCtxContentProvider implements ITreeContentProvider {
    @objid ("007f5972-107c-10c6-842f-001ec947cd2a")
     Map<Element, VirtualFolder> elementFolders = new HashMap<>();

    @objid ("003f18f8-e065-10ce-896b-001ec947cd2a")
    private GProject project;

    @objid ("002d0230-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public void inputChanged(Viewer v, Object oldInput, Object newInput) {
        // Nothing to do, data is the project set in C'tor
    }

    @objid ("002d2b52-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public void dispose() {
        // Empty
    }

    @objid ("002d42b8-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public Object[] getElements(Object inputElement) {
        ArrayList<Object> children = new ArrayList<>();
        
        // the element folders
        updateElementFolders();
        
        children.addAll(this.elementFolders.values());
        return children.toArray();
    }

    @objid ("002d7f44-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public Object getParent(Object child) {
        if (child instanceof AbstractDiagram) {
            Element context = ((AbstractDiagram) child).getOrigin();
            if (context == null)
                return null;
            else
                return this.elementFolders.get(context);
        }
        if (child instanceof Element)
            return ParentProvider.getParent((Element) child);
        
        if (child instanceof DiagramRef)
            return ((DiagramRef) child).getReferenceOwner();
        return null;
    }

    @objid ("002da9ce-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public Object[] getChildren(Object parent) {
        if (parent instanceof Element)
            return ChildrenProvider.getChildren((Element) parent);
        if (parent instanceof VirtualFolder)
            return ((VirtualFolder) parent).getChildren(parent);
        return new Object[0];
    }

    @objid ("002dde80-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public boolean hasChildren(Object parent) {
        if (parent instanceof Element)
            return ChildrenProvider.hasChildren((Element) parent);
        if (parent instanceof VirtualFolder)
            return ((VirtualFolder) parent).hasChildren(parent);
        return false;
    }

    @objid ("002e0a04-0d4f-10c6-842f-001ec947cd2a")
    public ByCtxContentProvider(GProject project) {
        this.project = project;
    }

    @objid ("002e2b38-0d4f-10c6-842f-001ec947cd2a")
    private void updateElementFolders() {
        if (this.project == null)
            return;
        for (AbstractDiagram diagram : this.project.getSession().getModel().findByClass(AbstractDiagram.class, IModel.NODELETED)) {
            Element context = diagram.getOrigin();
            if (context == null) {
                continue;
            }
        
            if (!this.elementFolders.containsKey(context)) {
                final Element filteringContext = context;
        
                VirtualFolder newFolder = new VirtualFolder(filteringContext) {
                    @Override
                    public Object[] getChildren(Object parentElement) {
                        ArrayList<Object> objects = new ArrayList<>();
                        VirtualFolder folder = (VirtualFolder) parentElement;
                        Element element = (ModelElement) folder.getDelegate();
                        if (element instanceof ModelElement) {
                            for (AbstractDiagram d : ((ModelElement) element).getProduct()) {
                                objects.add(d);
                            }
                        }
                        return objects.toArray();
                    }
        
                    @Override
                    public Object[] getElements(Object inputElement) {
                        return new Object[0];
                    }
        
                    @Override
                    public Object getParent(Object element) {
                        return null;
                    }
        
                    @Override
                    public boolean hasChildren(Object parentElement) {
                        VirtualFolder folder = (VirtualFolder) parentElement;
                        Element element = (ModelElement) folder.getDelegate();
                        if (element instanceof ModelElement)
                            return !((ModelElement) element).getProduct().isEmpty();
                        return false;
                    }
        
                    @Override
                    public void dispose() {
                        // Nothing to dispose
        
                    }
        
                    @Override
                    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
                        // Nothing to do
        
                    }
        
                };
                newFolder.setRepresentedElement(filteringContext);
                newFolder.setName("");
                this.elementFolders.put(context, newFolder);
            }
        }
    }

    @objid ("002e3a2e-0d4f-10c6-842f-001ec947cd2a")
    public void modelChanged(IModelChangeEvent event) {
        // we are interested in deletion as we need to clean up the
        // elementFolders map
        if (event.getDeleteEvents().size() > 0) {
            for (Element key : this.elementFolders.keySet()) {
                if (!key.isValid()) {
                    this.elementFolders.remove(key);
                    break;
                }
            }
        }
    }

    /**
     * Helping class to compute the children of an Element. This class implement
     * the 'children' concept for this particular kind of browser content
     * provider (ie 'children' notion might differ from metamodel's one)
     */
    @objid ("002e7246-0d4f-10c6-842f-001ec947cd2a")
    static class ChildrenProvider extends DefaultInfrastructureVisitor {
        @objid ("002e9b5e-0d4f-10c6-842f-001ec947cd2a")
        private static final Object[] NOCHILDREN = new Object[0];

        @objid ("002ebb5c-0d4f-10c6-842f-001ec947cd2a")
        private static ChildrenProvider _instance = new ChildrenProvider();

        @objid ("002ec21e-0d4f-10c6-842f-001ec947cd2a")
        public static Object[] getChildren(Element e) {
            Object ret = e.accept(_instance);
            return (ret != null) ? (Object[]) ret : NOCHILDREN;
        }

        @objid ("002ef8b0-0d4f-10c6-842f-001ec947cd2a")
        public static boolean hasChildren(Element e) {
            Object ret = e.accept(_instance);
            return (ret != null) ? ((Object[]) ret).length > 0 : false;
        }

        @objid ("002f2222-0d4f-10c6-842f-001ec947cd2a")
        @Override
        public Object visitDiagramSet(DiagramSet theDiagramSet) {
            ArrayList<Object> objects = new ArrayList<>();
            
            // add owned sub sets
            objects.addAll(theDiagramSet.getSub());
            
            // add referenced diagrams (as links)
            for (AbstractDiagram diagram : theDiagramSet.getReferencedDiagram()) {
                objects.add(new DiagramRef(diagram, theDiagramSet));
            }
            return objects.toArray();
        }

    }

    /**
     * Helping class to compute the parent of an Element. This class implement
     * the 'parent' concept for this kind of browser content provider (ie
     * 'parent' notion might differ from metamodel's) For example this 'flat'
     * model does not support DiagramSet which therefore return not parent Note:
     * DefaultMetamodelVisitor propagates missing visitXXXX methods up in the
     * inheritance tree
     */
    @objid ("002f5684-0d4f-10c6-842f-001ec947cd2a")
    static class ParentProvider extends DefaultInfrastructureVisitor {
        @objid ("002f7dee-0d4f-10c6-842f-001ec947cd2a")
         static ParentProvider INSTANCE = new ParentProvider();

        @objid ("002f85b4-0d4f-10c6-842f-001ec947cd2a")
        public static Object getParent(Element e) {
            return (e.isValid()) ? e.accept(INSTANCE) : null;
        }

        /**
         * This one is called for any kind of diagram
         */
        @objid ("002fb0c0-0d4f-10c6-842f-001ec947cd2a")
        @Override
        public Object visitAbstractDiagram(AbstractDiagram theAbstractDiagram) {
            return theAbstractDiagram.getOrigin();
        }

        /**
         * Essential termination code. at the top of the inheritance tree let's
         * return null
         */
        @objid ("002ff058-0d4f-10c6-842f-001ec947cd2a")
        @Override
        public Object visitElement(Element theElement) {
            return null;
        }

    }

}
