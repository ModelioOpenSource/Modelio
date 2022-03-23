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
package org.modelio.vcore.model.api;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.services.IMetamodelDependentService;

@objid ("3fe77b9c-5404-4fe1-932c-f7d33f2c1749")
public interface IRepositoryContentInitializer extends IMetamodelDependentService {
    /**
     * Populate the given repository using the given session.
     * <p>
     * The repository must be mount and a model transaction be open.
     * @param modelName the fragment name
     * @param session the session to use
     * @param repository the repository to populate.
     * @return the created objects.
     */
    @objid ("bd1cf53b-cb1b-4521-a9ff-151c4648cfb0")
    Collection<MObject> populate(String modelName, ICoreSession session, IRepository repository);

}
