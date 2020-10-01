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

package org.modelio.metamodel.impl.mmextensions.infrastructure.populator;

import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.vcore.model.api.IRepositoryContentInitializer;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.impl.GenericFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Initialize new fragments content.
 * <p>
 * Creates the LocalModule.
 */
@objid ("749e812c-a6ba-40b9-ab61-694009c6fe4b")
public class InfrastructurePopulator implements IRepositoryContentInitializer {
    /**
     * Populate the given repository using the given session.
     * 
     * @param fragmentName the fragment name
     * @param s the session to use
     * @param repository the repository to populate.
     * @return the created objects.
     */
    @objid ("d24dd1e1-0eb1-4539-a19b-1185de465b32")
    @Override
    public Collection<MObject> populate(String fragmentName, ICoreSession s, IRepository repository) {
        Collection<MObject> ret = new ArrayList<>();
        GenericFactory gf = s.getModel().getGenericFactory();
        
        
        // Create LocalModule
        ModuleComponent localModule = gf.create(ModuleComponent.class, repository);
        localModule.setName("LocalModule");
        ret.add(localModule);
        
        // Create LocalProfile
        Profile localProfile = gf.create(Profile.class, repository);
        localProfile.setName("LocalProfile");
        localModule.getOwnedProfile().add(localProfile);
        ret.add(localProfile);
        return ret;
    }

}
