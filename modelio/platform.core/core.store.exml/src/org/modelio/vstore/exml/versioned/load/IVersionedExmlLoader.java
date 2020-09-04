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

package org.modelio.vstore.exml.versioned.load;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.xml.sax.InputSource;

/**
 * Interface for classes that can load a versioned EXML resource with its non versioned part.
 */
@objid ("21c3677b-4078-11e2-87cb-001ec947ccaf")
public interface IVersionedExmlLoader {
    /**
     * Load a CMS node from an EXML resource.
     * @param is the versioned EXML part
     * @param localIs the non versioned part
     * @param aModelLoader model loading API
     * @return the loaded CMS node
     * @throws java.io.IOException in case of I/O error
     * @throws org.modelio.vcore.model.DuplicateObjectException if a loaded object already exist in another repository.
     */
    @objid ("ddea431e-407a-11e2-87cb-001ec947ccaf")
    SmObjectImpl load(final InputSource is, final InputSource localIs, IModelLoader aModelLoader) throws DuplicateObjectException, IOException;

}
