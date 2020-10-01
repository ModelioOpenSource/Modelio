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

package org.modelio.semantic.browser.panel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.gproject.data.project.FragmentType;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.gproject.GProject;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.session.api.model.change.IModelChangeListener;
import org.modelio.vcore.session.api.model.change.IModelChangeSupport;
import org.modelio.vcore.session.api.model.change.IStatusChangeEvent;
import org.modelio.vcore.session.api.model.change.IStatusChangeListener;
import org.modelio.vcore.smkernel.ISmMeta;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.mapi.MAttribute;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Semantic content provider for the model browser.
 * <p>
 * This implementation is basically a standard BrowserModelContentProvider where redefined methods return semantic features as SmNode instances. Semantic features of a MObject represents the SmAttribute and SmDependendy of the object metaclass.
 * </p>
 * <p>
 * The {@link #getElements(Object)} method returns the tree roots, from the "local roots" if defined, or from the GProject itself.
 * </p>
 */
@objid ("29743854-5936-42d5-a8c2-687e04073541")
public class SmBrowserContentProvider implements ITreeContentProvider, IModelChangeListener, IStatusChangeListener {
    @objid ("3c3f17ae-56e5-4476-941b-a9d557a53bb8")
    private AtomicReference<ViewRefresher> viewRefresher = new AtomicReference<>();

    @objid ("37d433a0-d8d0-4ddb-a038-3c13933a5dc2")
    private Viewer viewer;

    @objid ("87a2d998-3327-4852-9f2d-2f08eb18a53d")
    @Override
    public Object getParent(final Object child) {
        Object parent = null;
        
        if (child instanceof SmNode) {
            parent = ((SmNode) child).getObj();
        }  else if (child instanceof MObject) {
            SmDepVal depVal = ((ISmMeta) child).getCompositionRelation();
            
            if (depVal != null) {
                parent = new SmNode(depVal.value, depVal.dep.getSymetric());
            } else {
                parent = ((GProject) this.viewer.getInput()).getFragment((MObject) child);
            }
        }
        return parent;
    }

    /**
     * Get children
     * <ul>
     * <li>for a fragment => return the roots (MObject)</li>
     * <li>for a MObject => return the list of its dependencies 'SmDepVal"</li>
     * <li>for a DepVal => return the list of its values (MObject)
     * </ul>
     */
    @objid ("baf6d88e-2ef4-4c14-97d3-870efd68c79e")
    @Override
    public Object[] getChildren(final Object parent) {
        if (parent instanceof MObject) {
            return getChildren((MObject) parent).toArray();
        }
        
        if (parent instanceof IProjectFragment) {
            return getChildren((IProjectFragment) parent).toArray();
        }
        
        if (parent instanceof SmNode) {
            SmNode node = (SmNode) parent;
            return node.getContent().toArray();
        }
        return Collections.EMPTY_LIST.toArray();
    }

    @objid ("691f4fd1-973c-409f-945e-61b234d57db4")
    @Override
    public boolean hasChildren(final Object parent) {
        if (parent instanceof SmNode) {
            return ((SmNode) parent).getFeature() instanceof MDependency;
        }
        if (parent instanceof MObject) {
            return true;
        }
        return true;
    }

    @objid ("cf8c0f92-969e-427b-8f6c-4e1984a89479")
    @Override
    public void statusChanged(IStatusChangeEvent event) {
        scheduleRefresh();
    }

    @objid ("c73951e2-45df-4c8e-a86f-203d68d8fad1")
    @Override
    public void modelChanged(IModelChangeEvent event) {
        scheduleRefresh();
    }

    /**
     * Get elements, viewer input is a GProject => return the fragments
     */
    @objid ("e1da3110-e83a-46b9-ae72-9aeb7a488545")
    @Override
    public Object[] getElements(Object parent) {
        if (parent != null && parent instanceof GProject) {
            return getFragments((GProject) parent).toArray();
        }
        // Nothing to return yet
        return new Object[0];
    }

    @objid ("dfe9cbd5-b2ca-45d1-86d1-e75b829eb46b")
    private List<IProjectFragment> getFragments(GProject project) {
        List<IProjectFragment> fragments = new ArrayList<>();
        for (IProjectFragment iProjectFragment : project.getFragments()) {
            fragments.add(iProjectFragment);
        }
        Collections.sort(fragments, new FragmentComparator());
        return fragments;
    }

    @objid ("69d018d9-d056-4729-b6c2-4b2e37cb71ab")
    @Override
    public void inputChanged(final Viewer currentViewer, final Object oldInput, final Object newInput) {
        this.viewer = currentViewer;
        
        // Unregister model change listener on the old input
        if (oldInput != null && oldInput instanceof GProject) {
            ICoreSession session = ((GProject) oldInput).getSession();
            if (session != null) {
                session.getModelChangeSupport().removeModelChangeListener(this);
                session.getModelChangeSupport().removeStatusChangeListener(this);
            }
        } else if (oldInput != null && oldInput instanceof ICoreSession) {
            ICoreSession session = (ICoreSession) oldInput;
            session.getModelChangeSupport().removeModelChangeListener(this);
            session.getModelChangeSupport().removeStatusChangeListener(this);
        }
        
        // Register model change listener on the new input
        if (newInput != null && newInput instanceof GProject) {
            IModelChangeSupport changeSupport = ((GProject) newInput).getSession().getModelChangeSupport();
            changeSupport.addModelChangeListener(this);
            changeSupport.addStatusChangeListener(this);
        
        } else if (newInput != null && newInput instanceof ICoreSession) {
            IModelChangeSupport changeSupport = ((ICoreSession) newInput).getModelChangeSupport();
            changeSupport.addModelChangeListener(this);
            changeSupport.addStatusChangeListener(this);
        }
    }

    @objid ("0f187ba5-a9d2-429e-9ce6-61a98ea1392a")
    private void scheduleRefresh() {
        ViewRefresher newRefresher = new ViewRefresher();
        if (this.viewRefresher.compareAndSet(null, newRefresher)) {
            this.viewer.getControl().getDisplay().asyncExec(newRefresher);
        }
    }

    @objid ("48438868-7f03-45df-9a2f-f7543b3b4cd5")
    void doRefreshViewer() {
        this.viewRefresher.set(null);
        
        if (this.viewer != null && !this.viewer.getControl().isDisposed()) {
            this.viewer.refresh();
        }
    }

    @objid ("77bc1da7-83eb-47b1-b26c-05f0565dda2a")
    private Collection<Object> getChildren(MObject mObj) {
        final List<Object> smChildren = new ArrayList<>();
        
        for (final MAttribute mAtt : mObj.getMClass().getAttributes(true)) {
            smChildren.add(new SmNode(mObj, mAtt));
        }
        for (final MDependency mDep : mObj.getMClass().getDependencies(true)) {
            SmNode smNode = new SmNode(mObj, mDep);
            if (!smNode.getContent().isEmpty()) {
                smChildren.add(smNode);
            }
        }
        return smChildren;
    }

    @objid ("9720acbc-517e-420c-8d3b-53ecc9c3b312")
    private Collection<MObject> getChildren(IProjectFragment fragment) {
        return fragment.getRoots();
    }

    @objid ("df256539-dff8-4066-8046-0b1ebf23a3e9")
    private static class FragmentComparator implements Comparator<IProjectFragment> {
        @objid ("172e9dbc-6aab-4e10-a80a-45038685c198")
        public FragmentComparator() {
            // Empty constructor
        }

        @objid ("ebca4474-e779-4c18-8e2c-01551d0593ed")
        @Override
        public int compare(IProjectFragment f1, IProjectFragment f2) {
            if (f1.getType() == f2.getType()) {
                return f1.getId().compareTo(f2.getId());
            } else {
                return FragmentComparator.getTypeWeight(f1.getType()) - FragmentComparator.getTypeWeight(f2.getType());
            }
        }

        @objid ("42fe47c4-f945-4d7e-9a15-a084aca456b5")
        private static int getTypeWeight(FragmentType type) {
            switch (type) {
            case EXML:
                return 0;
            case EXML_SVN:
                return 1;
            case RAMC:
                return 2;
            case EXML_URL:
                return 3;
            case MDA:
                return 4;
            default:
                return 99;
            }
        }

    }

    @objid ("4b68035d-0cb3-4fbc-912c-5d45861f5c07")
    private class ViewRefresher implements Runnable {
        @objid ("8c9ead83-83aa-43ed-b3ed-41afb77ecb16")
        public ViewRefresher() {
            // nothing
        }

        @objid ("efac2162-dfbc-4cb9-b473-3e525813fcfb")
        @Override
        public void run() {
            doRefreshViewer();
        }

    }

}
