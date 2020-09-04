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

package org.modelio.app.model.imp.impl.ui;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.app.core.metamodel.MetamodelExtensionPoint;
import org.modelio.gproject.data.project.FragmentType;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.gproject.GProject;
import org.modelio.model.browser.view.BrowserView;
import org.modelio.model.browser.view.panel.BrowserContentProvider;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Content provider for the tree viewer.
 */
@objid ("14166fc9-202f-4d98-9826-f6c29837527c")
class ImportModelContentProvider extends BrowserContentProvider {
    @objid ("6d3c56a7-cfc9-4a06-b34f-8ce65c679c11")
    private final MetamodelExtensionPoint<ITreeContentProvider> contentProviderExtensions = new MetamodelExtensionPoint<>(BrowserView.CONTENTPROVIDER_EXTENSION_POINT_ID);

    @objid ("2481bf13-8cb6-4f7c-8a2d-9c94d0f5a844")
    public ImportModelContentProvider() {
        super();
        setShowModuleFragments(true);
    }

    @objid ("acdf23f9-a63f-45cf-8403-795345d9f331")
    @Override
    public void inputChanged(Viewer currentViewer, Object oldInput, Object newInput) {
        // Register extensions
        if (newInput != null && newInput instanceof GProject) {
            GProject project = (GProject) newInput;
            ICoreSession modelingSession = project.getSession();
            for (MMetamodelFragment fragment : modelingSession.getMetamodel().getFragments()) {
                ITreeContentProvider subContentProvider = this.contentProviderExtensions.get(fragment);
                if (subContentProvider != null) {
                    registerExtension(fragment, subContentProvider);
                }
            }
        }
        
        super.inputChanged(currentViewer, oldInput, newInput);
    }

    @objid ("24a2ad74-b9c4-4858-bf38-b3b9cdfa7706")
    @Override
    public Object[] getElements(Object parent) {
        if (parent instanceof GProject) {
            GProject project = (GProject) parent;
        
            return getFragments(project).toArray();
        }
        return super.getElements(parent);
    }

    /**
     * Ignore RAMC, MDA and HTTP fragments.
     */
    @objid ("23145f42-c48c-4c16-a184-bb2c1d2af8b4")
    private List<IProjectFragment> getFragments(GProject project) {
        List<IProjectFragment> fragments = new ArrayList<>();
        for (IProjectFragment iProjectFragment : project.getFragments()) {
            // Ignore MDA and RAMC fragments
            if (iProjectFragment.getType() == FragmentType.MDA || iProjectFragment.getType() == FragmentType.RAMC || iProjectFragment.getType() == FragmentType.EXML_URL) {
                continue;
            } else {
                fragments.add(iProjectFragment);
            }
        }
        return fragments;
    }

    @objid ("13dc6126-3020-45ef-8ce5-9fa2b18f2b7a")
    @Override
    public Object[] getChildren(Object parent) {
        List<Object> children = new ArrayList<>();
        for (Object child : super.getChildren(parent)) {
            if (!(child instanceof MObject) || ((MObject) child).getMClass().isCmsNode()) {
                children.add(child);
            }
        }
        return children.toArray();
    }

}
