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

package org.modelio.model.property.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.app.core.IModelioEventService;
import org.modelio.app.core.IModelioService;
import org.modelio.app.core.events.ModelioEvent;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.gproject.data.project.FragmentType;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.model.property.stereotype.creator.StereotypeEditor;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("a9ed8069-f8fc-4c5e-ad6e-63d679d4f9c5")
public class CreateStereotypeHandler implements IModelioService {
    @objid ("64639f03-e39a-40c5-8a49-0fa3ab53e312")
    @Inject
    protected IProjectService projectService;

    /**
     * (non-Javadoc)
     * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
     */
    @objid ("425771fe-03eb-46d4-950e-6d1d5aef6cdb")
    @CanExecute
    public final boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        if ((this.projectService.getSession() == null) || selection.isEmpty())
            return false;
        if (this.projectService.getOpenedProject() == null)
            return false;
        if (selection.size() > 1)
            return false;
        Profile profile = null;
        profile = getSelectedProfile(selection);
        if (profile == null) {
            IProjectFragment fragment = getSelectedFragment(selection);
            if (fragment == null)
                return false;
            ModuleComponent module = getFirstModule(fragment);
            if (module == null)
                return false;
            profile = getProfileToCreateStereotype(module);
        }
        if ((profile != null) && !profile.isModifiable())
            return false;
        return true;
    }

    @objid ("8e69612f-fb41-4891-8cf3-0ccab327efeb")
    private Profile getSelectedProfile(IStructuredSelection selection) {
        if (selection.size() == 1) {
            Object selectedObject = selection.getFirstElement();
            if (selectedObject instanceof Profile)
                return (Profile) selectedObject;
        }
        return null;
    }

    /**
     * (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @objid ("f5ed7cf9-32b3-4aca-b39f-2563405ab10f")
    @Execute
    public final void execute(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, IMModelServices mmServices, IModelioEventService eventService) {
        Profile profile = getSelectedProfile(selection);
        List<ModelElement> elements = new ArrayList<>();
        IProjectFragment fragment = getSelectedFragment(selection);
        if (profile == null) {
            elements = getSelectedModelElements(selection);
            ModuleComponent module = getFirstModule(fragment);
            profile = getProfileToCreateStereotype(module);
        }
        StereotypeEditor stereotypeEditor = new StereotypeEditor(this.projectService, mmServices);
        Stereotype newStecreotype = stereotypeEditor.create(profile, elements, fragment, true);
        if (newStecreotype != null) {
            eventService.postAsyncEvent(this, ModelioEvent.NAVIGATE_ELEMENT, Arrays.asList(newStecreotype));
        }
    }

    @objid ("200e5614-cf0d-43d8-bbe2-0864b1455031")
    private List<ModelElement> getSelectedModelElements(IStructuredSelection selection) {
        List<ModelElement> selectedElements = new ArrayList<>();
        List<Object> selectedObjects = selection.toList();
        for (Object selectedObject : selectedObjects) {
            if ((selectedObject instanceof ModelElement) && ((ModelElement) selectedObject).isModifiable()) {
                selectedElements.add((ModelElement) selectedObject);
            }
        }
        return selectedElements;
    }

    @objid ("416d92ed-d46e-42c0-8c70-16679eb9b5fc")
    private IProjectFragment getSelectedFragment(IStructuredSelection selection) {
        IProjectFragment fragment = null;
        if (selection.size() == 1) {
            Object selectedObject = selection.getFirstElement();
            if (selectedObject instanceof IProjectFragment) {
                if ((((IProjectFragment) selectedObject).getType() == FragmentType.EXML) || (((IProjectFragment) selectedObject).getType() == FragmentType.EXML_SVN)) {
                    fragment = (IProjectFragment) selectedObject;
                }
            } else if ((selectedObject instanceof ModelElement) && ((ModelElement) selectedObject).isModifiable()) {
                fragment = this.projectService.getOpenedProject().getFragment((ModelElement) selectedObject);
            }
        }
        return fragment;
    }

    @objid ("5cf20ffe-32a4-4d95-8758-9a21a6da348c")
    private ModuleComponent getFirstModule(IProjectFragment fragment) {
        for (MObject root : fragment.getRoots()) {
            if (root instanceof ModuleComponent)
                return (ModuleComponent) root;
        }
        return null;
    }

    @objid ("dcdf524b-8480-4245-a29e-79518445928b")
    private Profile getProfileToCreateStereotype(ModuleComponent module) {
        if (!module.getOwnedProfile().isEmpty()) {
            // Get LocalProfile if have
            for (Profile profile : module.getOwnedProfile()) {
                if (("LocalProfile").equals(profile.getName()))
                    return profile;
            }
            return module.getOwnedProfile().get(0);
        }
        return null;
    }

    @objid ("6132d44a-a20c-4b03-ac10-228a151d3c3d")
    @Override
    public String getName() {
        return "Create stereotype handler";
    }

}
