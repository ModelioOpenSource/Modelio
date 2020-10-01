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

package org.modelio.diagram.browser.model.byset;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.diagram.browser.model.AllDiagramsNode;
import org.modelio.diagram.browser.model.core.DiagramRef;
import org.modelio.diagram.browser.model.core.VirtualFolder;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.visitors.DefaultInfrastructureVisitor;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The 'BySet' model displays the IDiagramSet hierarchy of the project. The root
 * node is the root diagram set named as the project along with a unique
 * VirtualFolder 'all diagrams' that itself displays all the diagrams as a flat
 * list.
 */
@objid ("00457914-0d4f-10c6-842f-001ec947cd2a")
public class BySetContentProvider implements ITreeContentProvider {
    @objid ("0079f64e-1f1a-10c7-842f-001ec947cd2a")
    private AllDiagramsNode allDiagramsFolder;

    @objid ("0079fdc4-1f1a-10c7-842f-001ec947cd2a")
    private GProject project;

    @objid ("0045b406-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public void inputChanged(Viewer v, Object oldInput, Object newInput) {
        // Nothing to do, data is the project set in C'tor
    }

    @objid ("0045db0c-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public void dispose() {
        // Empty
    }

    @objid ("0045f1b4-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public Object[] getElements(Object inputElement) {
        Set<Object> fragments = new HashSet<>();
        
        for (DiagramSet p : this.project.getSession().getModel().findByClass(DiagramSet.class, IModel.NODELETED)) {
            IProjectFragment fragment = this.project.getFragment(p);
            if (fragment != null) {
                fragments.add(fragment);
            }
        }
        
        fragments.add(this.allDiagramsFolder);
        return fragments.toArray();
    }

    @objid ("0046311a-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public Object getParent(Object child) {
        if (child instanceof AbstractDiagram) {
            return this.allDiagramsFolder;
        } else if (child instanceof DiagramRef) {
            return ((DiagramRef) child).getReferenceOwner();
        } else if (child instanceof DiagramSet) {
            final DiagramSet parent = ((DiagramSet) child).getParent();
            if (parent != null) {
                return parent;
            } else {
                return this.project.getFragment((DiagramSet) child);
            }
        }
        return null;
    }

    @objid ("00465a32-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public Object[] getChildren(Object parent) {
        // the children of a diagram set are its sub-set and its diagram
        // references
        if (parent instanceof DiagramSet) {
            DiagramSet theDiagramSet = (DiagramSet) parent;
            ArrayList<Object> objects = new ArrayList<>();
        
            // add owned sub sets
            objects.addAll(theDiagramSet.getSub());
        
            // add referenced diagrams (as links)
            for (AbstractDiagram diagram : theDiagramSet.getReferencedDiagram()) {
                objects.add(new DiagramRef(diagram, theDiagramSet));
            }
            return objects.toArray();
        } else if (parent instanceof AllDiagramsNode) {
            // the 'all diagrams' pseudo node knows about its children
            return ((VirtualFolder) parent).getChildren(parent);
        } else if (parent instanceof IProjectFragment) {
            List<DiagramSet> diagramSets = new ArrayList<>();
        
            for (MObject root : ((IProjectFragment) parent).getRoots()) {
                if (root instanceof AbstractProject) {
                    DiagramSet diagramRoot = ((AbstractProject) root).getDiagramRoot();
                    if (diagramRoot != null) {
                        diagramSets.add(diagramRoot);
                    }
                }
            }
        
            return diagramSets.toArray();
        }
        // other cases are not expected
        return new Object[0];
    }

    @objid ("0046901a-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public boolean hasChildren(Object parent) {
        // a diagram ref has no children
        if (parent instanceof DiagramRef) {
            return false;
        } else if (parent instanceof DiagramSet) {
            // the children of a diagram set are its sub-set and its diagram
            // references
            DiagramSet theDiagramSet = (DiagramSet) parent;
            return (theDiagramSet.getSub().size() + theDiagramSet.getReferencedDiagram().size()) > 0;
        } else if (parent instanceof AllDiagramsNode) {
            // the 'all diagrams' pseudo node knows about its children
            return ((VirtualFolder) parent).hasChildren(parent);
        } else if (parent instanceof IProjectFragment) {
            return true;
        } else if (parent instanceof AbstractProject) {
            return ((AbstractProject) parent).getDiagramRoot() != null;
        }
        
        // other cases are not expected
        return false;
    }

    /**
     * Constructor initializing the project.
     * @param showFragments
     * 
     * @param project an opened GProject.
     */
    @objid ("0046b8ce-0d4f-10c6-842f-001ec947cd2a")
    public BySetContentProvider(GProject project) {
        this.project = project;
        this.allDiagramsFolder = new AllDiagramsNode(this.project, null);
    }

    /**
     * Helping class to compute the parent of an Element. This class implement
     * the 'parent' concept for this kind of browser content provider (ie
     * 'parent' notion might differ from metamodel's) For example this 'flat'
     * model does not support DiagramSet which therefore return not parent Note:
     * DefaultMetamodelVisitor propagates missing visitXXXX methods up in the
     * inheritance tree
     */
    @objid ("0046dff2-0d4f-10c6-842f-001ec947cd2a")
    static class ParentProvider extends DefaultInfrastructureVisitor {
        @objid ("0047086a-0d4f-10c6-842f-001ec947cd2a")
         static ParentProvider _instance = new ParentProvider();

        @objid ("0047110c-0d4f-10c6-842f-001ec947cd2a")
        public static Object getParent(Element e) {
            return ((MObject) e).isValid() ? e.accept(_instance) : null;
        }

        /**
         * This one is called for any kind of diagram
         */
        @objid ("00473d08-0d4f-10c6-842f-001ec947cd2a")
        @Override
        public Object visitAbstractDiagram(AbstractDiagram theAbstractDiagram) {
            return theAbstractDiagram.getOrigin();
        }

        /**
         * Essential termination code. at the top of the inheritance tree let's
         * return null
         */
        @objid ("00477cbe-0d4f-10c6-842f-001ec947cd2a")
        @Override
        public Object visitElement(Element theElement) {
            return null;
        }

    }

}
