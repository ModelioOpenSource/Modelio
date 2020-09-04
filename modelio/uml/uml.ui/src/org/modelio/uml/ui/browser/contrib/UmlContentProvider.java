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

package org.modelio.uml.ui.browser.contrib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.core.ui.swt.labelprovider.IModelContainer;
import org.modelio.core.ui.swt.labelprovider.LinkContainer;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.mda.Project;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Base content provider contributor for UML/BPMN elements.<br/>
 * This provider is only usable as an extension to the browser model content provider, NOT as a STANDALONE ITreeContentProvider implementation.
 */
@objid ("8719ea2a-06a8-42ad-bbd6-0e8536e76a0e")
public class UmlContentProvider implements ITreeContentProvider {
    @objid ("42904045-ebc4-47dc-a17d-ea825cca6821")
    private static final StandardModelProviderVisitor visitor = new StandardModelProviderVisitor();

    @objid ("e594a69c-10d6-479a-83fb-5f3ced72b5d0")
    @Override
    public void dispose() {
        // Nothing to do.
    }

    @objid ("6c446810-2570-4ded-9f1f-a5e0c2742f5e")
    @Override
    public Object[] getChildren(final Object parent) {
        // Special case: children of a fragment => the typed project(s)
        if (parent instanceof IProjectFragment) {
            return getFragmentRoots((IProjectFragment) parent).toArray();
        }
        
        // General case: MObject
        if (parent instanceof MObject) {
            return getChildren((MObject) parent);
        }
        
        // No children
        return Collections.EMPTY_LIST.toArray();
    }

    @objid ("7b6d3abf-a4a9-4dcd-9068-24298ba3ecab")
    @Override
    public Object[] getElements(final Object parent) {
        // This content provider being only a contributor to the browser model content provider,
        // it is not expected to provide 'root' elements => nothing to return
        return new Object[0];
    }

    @objid ("5aa030f8-2117-4ddd-a6d9-14ee2f62d89f")
    @Override
    public Object getParent(final Object child) {
        // General case: MObject
        if (child instanceof MObject) {
            return getParent((MObject) child);
        }
        
        // No parent found
        return null;
    }

    @objid ("f233effb-f72e-4088-af6f-fa17a5ae4eeb")
    @Override
    public boolean hasChildren(final Object parent) {
        if (parent instanceof IProjectFragment) {
            return hasChildren((IProjectFragment) parent);
        }
        
        // General case: MObject
        if (parent instanceof MObject) {
            return hasChildren((MObject) parent);
        }
        
        // Not expected, returning yes allows for next getChildren() call in any case, might help debugging :)
        return true;
    }

    @objid ("7ea2543f-c653-4cd9-a2f9-c018f7c027b4")
    @Override
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
        // This content provider being only a contributor to the browser model content provider
        // it does not deal with input => nothing to do
    }

    /**
     * Get children for a MObject
     */
    @objid ("413ed989-58e2-4da6-9b35-b22e3f047672")
    private Object[] getChildren(MObject mObj) {
        // First collect elements
        List<Object> results = new ArrayList<>();
        results.addAll(UmlContentProvider.visitor.getElements(mObj));
        
        // Second collect links. If there are links group them in a LinkContainer instance added to the results.
        List<MObject> links = UmlContentProvider.visitor.getLinks(mObj);
        if (!links.isEmpty()) {
            results.add(new LinkContainer(mObj, links));
        }
        return results.toArray();
    }

    /**
     * Get parent of a MObject
     */
    @objid ("0103aac9-a134-4c24-992d-efe7bb51d069")
    private Object getParent(final MObject child) {
        // Special case: BpmnBoundaryEvent
        if (child instanceof BpmnBoundaryEvent && ((BpmnBoundaryEvent) child).getAttachedToRef() != null) {
            return ((BpmnBoundaryEvent) child).getAttachedToRef();
        }
        
        if (child instanceof BpmnLane) {
            // Lane may belong to BpmnProcess BpmnRolesContainer or parent Lane
            BpmnLane l = (BpmnLane) child;
            BpmnLaneSet laneSet = l.getLaneSet();
            if (laneSet != null) {
                if (laneSet.getParentLane() != null) {
                    return laneSet.getParentLane();
                } else if (laneSet.getProcess() != null) {
                    return new BpmnRolesContainer(laneSet.getProcess(), Collections.emptyList());
                }
            }
        }
        
        // General case: MObject
        
        MObject owner = child.getCompositionOwner();
        if (owner != null) {
            // look for owner container
            if (UmlContentProvider.visitor.getLinks(owner).contains(child)) {
                return new LinkContainer(owner, null);
            } else {
                List<Object> elements = UmlContentProvider.visitor.getElements(owner);
                for (Object elt : elements) {
                    if (elt.equals(child)) {
                        break;
                    } else if (elt instanceof IModelContainer) {
                        IModelContainer<?> container = (IModelContainer<?>) elt;
                        if (container.getContents().contains(child)) {
                            return container;
                        }
                    }
                }
            }
            return owner;
        } else if (!child.getMClass().areOrphansAllowed()) {
            UmlUi.LOG.warning("%s: Orphan %s discovered.", getClass().getSimpleName(), child);
        }
        
        // No parent found
        return null;
    }

    @objid ("f3ff7094-e4ce-4cf8-aade-b053dc6982df")
    private boolean hasChildren(final MObject parent) {
        return !(UmlContentProvider.visitor.getElements(parent).isEmpty() && UmlContentProvider.visitor.getLinks(parent).isEmpty());
    }

    /**
     * Has children? for a IProjectFragment
     */
    @objid ("f6859913-4102-422b-bef4-19fa5d789809")
    private boolean hasChildren(IProjectFragment fragment) {
        return !getFragmentRoots(fragment).isEmpty();
    }

    /**
     * Get children for a IProjectFragment
     */
    @objid ("bd28830d-de7d-41f0-b597-33bf28f9a0da")
    private List<Object> getFragmentRoots(IProjectFragment fragment) {
        List<Object> ret = new ArrayList<>();
        
        IRepository repository = fragment.getRepository();
        if (repository != null) {
            for (MObject root : fragment.getRoots()) {
                if (root.isValid() && root instanceof Project) {
                    ret.add(root);
                }
            }
        }
        return ret;
    }

}
