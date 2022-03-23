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
package org.modelio.model.browser.view.panel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.gproject.data.project.FragmentType;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.platform.model.ui.swt.labelprovider.IModelContainer;
import org.modelio.platform.model.ui.swt.labelprovider.LinkContainer;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.session.api.model.change.IModelChangeListener;
import org.modelio.vcore.session.api.model.change.IModelChangeSupport;
import org.modelio.vcore.session.api.model.change.IStatusChangeEvent;
import org.modelio.vcore.session.api.model.change.IStatusChangeListener;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default content provider for the model browser.
 * <p>
 * When setting a GProject or an ICoreSession as input, it registers a model
 * change listener refreshing the viewer.
 * </p>
 * <p>
 * The {@link #getElements(Object)} method returns the tree roots, from the
 * "local roots" if defined, or from the GProject itself.
 * </p>
 * <p>
 * Delegates most of its job to sub-content providers defined for each
 * {@link MMetamodelFragment}. Use
 * {@link #registerExtension(MMetamodelFragment, ITreeContentProvider)} and
 * {@link #unregisterExtension(MMetamodelFragment)} to manage these sub-content
 * providers.
 * </p>
 */
@objid ("001a0cb6-dd16-1fab-b27f-001ec947cd2a")
public class BrowserContentProvider implements IModelChangeListener, IStatusChangeListener, ITreeContentProvider {
    @objid ("ff36377b-9fb0-4b83-8951-1fb16435876c")
    private boolean showProjects = true;

    @objid ("eece2eec-d3d3-4fdd-a843-b854fe957d88")
    volatile boolean isEditorActive = false;

    @objid ("dd25a781-9a09-44bd-9652-0fc21fbbf294")
    private static final String CLASS = "class";

    @objid ("20211fc1-7f61-495a-a360-30be47195113")
    private boolean showModuleFragments = false;

    @objid ("b3ebb255-adb0-4f9a-801d-2e245b31f21f")
    private final AtomicReference<ViewRefresher> viewRefresher = new AtomicReference<>();

    @objid ("97aaacfb-d8fb-4fd5-8b6e-fa9cc2cec994")
    private GProject openedProject;

    @objid ("aa020c33-b0a1-484f-a267-124f1dc2aaa1")
    private List<Object> localRoots = new ArrayList<>();

    @objid ("65ab6d53-4da7-4bac-a7a7-5efff4072b0c")
    private Viewer viewer;

    @objid ("1402303d-117e-4e90-9d35-5bd9c3a314e9")
    private Map<String, ITreeContentProvider> extensions = new HashMap<>();

    @objid ("58fc2cc8-bb7a-4f71-b79b-3ef3dc4cd878")
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
            this.openedProject = (GProject) newInput;
        
            IModelChangeSupport changeSupport = ((GProject) newInput).getSession().getModelChangeSupport();
            changeSupport.addModelChangeListener(this);
            changeSupport.addStatusChangeListener(this);
        } else if (newInput != null && newInput instanceof ICoreSession) {
            this.openedProject = null;
        
            IModelChangeSupport changeSupport = ((ICoreSession) newInput).getModelChangeSupport();
            changeSupport.addModelChangeListener(this);
            changeSupport.addStatusChangeListener(this);
        } else {
            this.openedProject = null;
        }
        
        for (ITreeContentProvider contentProvider : this.extensions.values()) {
            contentProvider.inputChanged(currentViewer, oldInput, newInput);
        }
        
    }

    @objid ("a1b68b72-805e-4178-9036-92739784b671")
    @Override
    public void dispose() {
        for (ITreeContentProvider contentProvider : this.extensions.values()) {
            contentProvider.dispose();
        }
        
    }

    @objid ("0ade0bbe-2c18-463f-a215-0027baa332e7")
    @Override
    public Object[] getElements(final Object parent) {
        // If any local roots are declared, return them
        if (this.localRoots != null && this.localRoots.size() > 0) {
            return this.localRoots.toArray();
        }
        
        // No local root, get all fragments
        if (parent != null && parent instanceof GProject) {
            return getFragments((GProject) parent).toArray();
        }
        
        // Nothing to return yet
        return new Object[0];
    }

    @objid ("20da7dc0-bc98-4a7c-86ae-6a1e0b74943f")
    @Override
    public Object getParent(final Object child) {
        if (child instanceof IProjectFragment) {
            return this.viewer.getInput();
        } else if (child instanceof IModelContainer) {
            return ((IModelContainer<?>) child).getOwner();
        } else if (child instanceof MObject) {
            MObject element = (MObject) child;
            if (!element.isValid()) {
                // No need to ask extensions for a dead element 
                return null;        
            }
        
            // Delegate parent resolution to the extension that added it
            ITreeContentProvider extension = this.extensions.get(element.getMClass().getOrigin().getName());
            Object owner = extension != null ? extension.getParent(element) : element.getCompositionOwner();
            if (owner instanceof MObject) {
                if (!this.showProjects && isProject((MObject) owner)) {
                    return getParent(owner);
                } else {
                    return owner;
                }
            } else if (owner != null) {
                return owner;
            } else if (this.openedProject != null) {
                return this.openedProject.getFragment(element);
            }
        }
        return null;
    }

    @objid ("7b52bff5-96e4-435b-9c8a-1f5547d4f12a")
    @Override
    public Object[] getChildren(final Object parent) {
        if (parent instanceof IProjectFragment) {
            IProjectFragment fragment = (IProjectFragment) parent;
            return getFragmentRoots(fragment).toArray();
        } else if (parent instanceof IModelContainer) {
            return ((IModelContainer<?>) parent).getContents().toArray();
        }
        
        // Delegate children resolution
        List<Object> ret = new ArrayList<>();
        List<LinkContainer> links = new ArrayList<>();
        List<AbstractDiagram> diagrams = new ArrayList<>();
        for (ITreeContentProvider contentProvider : this.extensions.values()) {
            for (Object child : contentProvider.getChildren(parent)) {
                if (child instanceof LinkContainer) {
                    links.add((LinkContainer) child);
                } else if (child instanceof AbstractDiagram) {
                    diagrams.add((AbstractDiagram) child);
                } else {
                    ret.add(child);
                }
            }
        }
        if (!diagrams.isEmpty()) {
            ret.addAll(diagrams);
        }
        
        // Merge link containers
        if (!links.isEmpty()) {
            LinkContainer mergedLinks = new LinkContainer((MObject) parent, new ArrayList<>());
            for (LinkContainer lc : links) {
                mergedLinks.getContents().addAll(lc.getContents());
            }
            ret.add(mergedLinks);
        }
        return ret.toArray();
    }

    @objid ("3be7ab25-36d6-44e6-8493-f179516df4da")
    @Override
    public boolean hasChildren(final Object parent) {
        if (parent instanceof IProjectFragment) {
            IProjectFragment fragment = (IProjectFragment) parent;
            if (!getFragmentRoots(fragment).isEmpty()) {
                return true;
            }
        } else if (parent instanceof IModelContainer) {
            return ((IModelContainer<?>) parent).getContents().size() > 0;
        }
        
        // Delegate children resolution
        for (ITreeContentProvider contentProvider : this.extensions.values()) {
            if (contentProvider.hasChildren(parent)) {
                return true;
            }
        }
        return false;
    }

    @objid ("feca0f54-e66d-4c6e-9846-14006730c436")
    @Override
    public void modelChanged(final IModelChangeEvent event) {
        scheduleRefresh();
    }

    @objid ("2f458ffd-68ce-4717-b170-1520af1b8b77")
    public List<Object> getLocalRoots() {
        return this.localRoots;
    }

    @objid ("af68056b-fa2e-49e7-b700-10d30878ff9d")
    public void setLocalRoots(List<Object> localRoots) {
        this.localRoots = localRoots;
        if (this.viewer != null && !this.viewer.getControl().isDisposed()) {
            this.viewer.refresh();
        }
        
    }

    @objid ("dc85e8c3-5bcb-4c60-bcfa-73f06a077f3e")
    void doRefreshViewer() {
        this.viewRefresher.set(null);
        
        if (this.viewer != null
                && this.openedProject != null
                && this.openedProject.isOpen()
                && !this.viewer.getControl().isDisposed()
                && !this.isEditorActive) {
            this.viewer.refresh();
        }
        
    }

    @objid ("d50727aa-2e6b-4dae-9c5c-d5ca0d558d92")
    private void scheduleRefresh() {
        ViewRefresher newRefresher = new ViewRefresher();
        if (this.viewRefresher.compareAndSet(null, newRefresher)) {
            this.viewer.getControl().getDisplay().asyncExec(newRefresher);
        }
        
    }

    @objid ("9e6c3fff-b1b6-4d8e-8653-350e6bcd1c3e")
    @Override
    public void statusChanged(IStatusChangeEvent event) {
        scheduleRefresh();
    }

    @objid ("52b87d3b-cfbe-427f-a444-4a21417f6374")
    private List<Object> getFragmentRoots(IProjectFragment fragment) {
        List<Object> ret = new ArrayList<>();
        
        for (ITreeContentProvider contentProvider : this.extensions.values()) {
            for (Object root : contentProvider.getChildren(fragment)) {
                if (root instanceof AbstractProject) {
                    if (this.showProjects) {
                        ret.add(root);
                    } else {
                        // Take children of those projects...
                        ret.addAll(Arrays.asList(getChildren(root)));
                    }
                } else {
                    ret.add(root);
                }
            }
        }
        return ret;
    }

    @objid ("2795e64c-28ef-43f2-b82a-eea53b2f0694")
    private List<IProjectFragment> getFragments(GProject project) {
        List<IProjectFragment> fragments = new ArrayList<>();
        for (IProjectFragment iProjectFragment : project.getFragments()) {
            if (!isShowModuleFragments()) {
                // Ignore MDA fragments
                if (iProjectFragment.getType() == FragmentType.MDA) {
                    continue;
                }
            }
            fragments.add(iProjectFragment);
        }
        Collections.sort(fragments, new FragmentComparator());
        return fragments;
    }

    /**
     * @return true if MDA models are displayed.
     */
    @objid ("58f56eac-0069-4f1f-945f-564885dffcb1")
    public boolean isShowModuleFragments() {
        return this.showModuleFragments;
    }

    /**
     * @param showModuleFragments true if MDA models are to displayed.
     */
    @objid ("345701fe-e1b5-40d4-aaa3-97b89b4069a9")
    public void setShowModuleFragments(boolean showModuleFragments) {
        this.showModuleFragments = showModuleFragments;
    }

    /**
     * @return true if projects are displayed.
     */
    @objid ("2bde233e-e49b-4045-89b2-9def9436c902")
    public boolean isShowProjects() {
        return this.showProjects;
    }

    @objid ("2fa495ed-4490-44d0-ba8f-a97f019863c5")
    private boolean isProject(MObject element) {
        return element instanceof AbstractProject;
    }

    @objid ("ae58cc5e-e230-4813-95a8-cb0c94dfd423")
    public void registerExtension(MMetamodelFragment fragment, ITreeContentProvider contentProvider) {
        this.extensions.put(fragment.getName(), contentProvider);
    }

    @objid ("07cc7f68-d05b-4ec5-bc88-a40579f95193")
    public void unregisterExtension(MMetamodelFragment fragment) {
        this.extensions.remove(fragment.getName());
    }

    @objid ("f529ccd0-9ad7-4178-91e6-eac7fead32d1")
    public ITreeContentProvider getExtension(String mmFragmentName) {
        return this.extensions.get(mmFragmentName);
    }

    @objid ("3976546c-0369-4210-9227-2fcd043ed4e0")
    private class ViewRefresher implements Runnable {
        @objid ("a135a24b-5079-4418-80bd-260b1acfc3fe")
        public  ViewRefresher() {
            // nothing
        }

        @objid ("069ad896-68a8-41d7-8c83-43ef69fdcda0")
        @Override
        public void run() {
            doRefreshViewer();
        }

    }

    @objid ("b780e61b-8b01-4bc0-925d-f2bdabdd4a0d")
    private static class FragmentComparator implements Comparator<IProjectFragment> {
        @objid ("2403e2ad-9b9f-4ce9-a792-acc866bbe802")
        public  FragmentComparator() {
            // Empty constructor
        }

        @objid ("d4fc43be-60b9-42ce-92c6-20faf04f9813")
        @Override
        public int compare(IProjectFragment f1, IProjectFragment f2) {
            if (f1.getType() == f2.getType()) {
                return f1.getId().compareTo(f2.getId());
            } else {
                return FragmentComparator.getTypeWeight(f1.getType()) - FragmentComparator.getTypeWeight(f2.getType());
            }
            
        }

        @objid ("0e80d258-ce9c-43b3-936d-13bfdd6b2d18")
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

}
