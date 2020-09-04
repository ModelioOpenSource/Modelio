/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.model.property.stereotype.creator;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmClass;

@objid ("8e0f5ebf-9654-4eb2-8d3b-07a18b258d23")
class LocalProfileCreator {
    @objid ("d76525b2-615b-4b2d-a292-93f1ea7430fb")
    private CoreSession session;

    @objid ("9f555dd7-a0de-4006-bd57-52ac5da19955")
    public LocalProfileCreator(IProjectService projectService) {
        this.session = (CoreSession) projectService.getSession();
    }

    /**
     * @param fragment the fragment to create the local profile into.
     * @return profile to create stereotype
     */
    @objid ("8d9dadb0-7e6f-414e-956a-cfbf602207d4")
    public Profile execute(IProjectFragment fragment) {
        ModuleComponent module = getFirstModule(fragment);
        Profile ownerProfile = null;
        if (module != null) {
            ownerProfile = getProfileToCreateStereotype(module);
            if (ownerProfile == null) {
                ownerProfile = createLocalProfile(fragment, module);
            }
        }
        return ownerProfile;
    }

    @objid ("349b6d80-75ef-4943-bcbb-12c1fd04266d")
    private ModuleComponent getFirstModule(IProjectFragment fragment) {
        for (MObject root : fragment.getRoots()) {
            if (root instanceof ModuleComponent) {
                return (ModuleComponent) root;
            }
        }
        return null;
    }

    @objid ("f161478d-4264-460a-9217-8e20f2d0f039")
    private Profile getProfileToCreateStereotype(ModuleComponent module) {
        if (!module.getOwnedProfile().isEmpty()) {
            // Get LocalProfile if have
            for (Profile profile : module.getOwnedProfile()) {
                if (("LocalProfile").equals(profile.getName())) {
                    return profile;
                }
            }
            return module.getOwnedProfile().get(0);
        }
        return null;
    }

    @objid ("b9d69598-b0a5-4554-9f39-71bec7cdf101")
    private Profile createLocalProfile(IProjectFragment fragment, ModuleComponent localModule) {
        Profile ownerProfile;
        // Create the local profile:
        Profile profile = (Profile) this.session.getSmFactory().createObject((SmClass) localModule.getMClass().getMetamodel().getMClass(Profile.class), fragment.getRepository());
        profile.setName("LocalProfile");
        localModule.getOwnedProfile().add(profile);
        ownerProfile = profile;
        return ownerProfile;
    }

}
