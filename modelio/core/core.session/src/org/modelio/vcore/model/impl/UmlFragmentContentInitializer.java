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

package org.modelio.vcore.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.api.IRepositoryContentInitializer;
import org.modelio.vcore.model.api.IRepositoryContentInitializerService;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.services.MetamodelExtensionPoint;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;

/**
 * Initialize new fragments content.
 * <p>
 * Creates the Project, diagram root and the root package.
 */
@objid ("65e21068-ab36-11e1-8392-001ec947ccaf")
public class UmlFragmentContentInitializer implements IRepositoryContentInitializerService {
    @objid ("ee9a6920-d598-4eeb-921c-d096c02f54a4")
    private final MetamodelExtensionPoint<IRepositoryContentInitializer> metamodelExtensionPoint;

    /**
     * C'tor.
     */
    @objid ("49bc2adb-ab3f-11e1-8392-001ec947ccaf")
    public UmlFragmentContentInitializer() {
        this.metamodelExtensionPoint = new MetamodelExtensionPoint<>();
    }

    /**
     * Populate the given repository using the given session.
     * 
     * @param fragmentName the fragment name
     * @param s the session to use
     * @param repository the repository to populate.
     * @return the created objects.
     */
    @objid ("2c59465a-32f2-11e2-9905-001ec947ccaf")
    @Override
    public Collection<MObject> populate(String fragmentName, ICoreSession s, IRepository repository) {
        Collection<ISmMetamodelFragment> fragments = s.getMetamodel().getSortedFragments();
        Collection<MObject> ret = new ArrayList<>();
        
        for (ISmMetamodelFragment f : fragments) {
            IRepositoryContentInitializer svc = this.metamodelExtensionPoint.getService(f);
            if (svc != null) {
                ret.addAll(svc.populate(fragmentName, s, repository));
            }
        }
        return ret;
    }

    @objid ("4b583f31-27a0-459e-b4f5-dfc74569eb0d")
    @Override
    public MetamodelExtensionPoint<IRepositoryContentInitializer> getMetamodelExtensionPoint() {
        return this.metamodelExtensionPoint;
    }

}
