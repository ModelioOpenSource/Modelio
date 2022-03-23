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
import org.modelio.vcore.model.api.IRepositoryRootGetter;
import org.modelio.vcore.model.api.IRepositoryRootGetterService;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.services.MetamodelExtensionPoint;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Get the composition roots of a repository.
 * 
 * @author cmarin
 * @since 3.6
 */
@objid ("8a6e4038-9201-458f-9bbe-a848f77d4979")
public class CompositionRootGetter implements IRepositoryRootGetterService {
    @objid ("1501f848-3a03-4c86-8c2b-3e24312fe38e")
    private ICoreSession session;

    @objid ("6420aa08-9130-44f3-b728-e76f627151d5")
    private final MetamodelExtensionPoint<IRepositoryRootGetter> metamodelExtensionPoint;

    /**
     * @param rootGetters the metamodel service provider
     * @param proj the project
     */
    @objid ("62093964-a33b-4650-8a39-7b35b1344e9f")
    public  CompositionRootGetter(ICoreSession proj) {
        this.session = proj;
        this.metamodelExtensionPoint = new MetamodelExtensionPoint<>();
        
    }

    @objid ("628259be-8514-4ad1-b9be-95d9b8c197c6")
    @Override
    public Collection<MObject> getRootElements(IRepository repository) {
        Collection<MObject> ret = new ArrayList<>();
        
        SmMetamodel mm = this.session.getMetamodel();
        for (MMetamodelFragment mmf : mm.getFragments()) {
            IRepositoryRootGetter svc = this.metamodelExtensionPoint.getService(mmf);
            if (svc != null) {
                ret.addAll(svc.getRootElements(repository));
            }
        }
        return ret;
    }

    @objid ("1d69bd1a-92ae-4e67-97a0-1822dbbdcd8f")
    @Override
    public MetamodelExtensionPoint<IRepositoryRootGetter> getMetamodelExtensionPoint() {
        return this.metamodelExtensionPoint;
    }

}
