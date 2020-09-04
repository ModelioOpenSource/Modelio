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

package org.modelio.vcore.model.api;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Service that get composition roots of a repository.
 * 
 * @author cmarin
 * @since 3.6
 */
@objid ("46eab2d6-2e25-45e1-982b-285fa9e8d0df")
public interface IRepositoryRootGetter {
    /**
     * @param repository the repository to populate.
     * @return the created elements.
     */
    @objid ("59ace55c-3017-4671-8a0b-4079e9be6c2a")
    Collection<MObject> getRootElements(IRepository repository);

}
