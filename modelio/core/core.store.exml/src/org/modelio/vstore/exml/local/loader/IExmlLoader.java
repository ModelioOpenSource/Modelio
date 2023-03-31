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
package org.modelio.vstore.exml.local.loader;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vstore.exml.common.index.IndexException;
import org.xml.sax.InputSource;

/**
 * Interface for classes that can load an EXML resource.
 */
@objid ("2b037de0-3faf-11e2-87cb-001ec947ccaf")
public interface IExmlLoader {
    /**
     * Load an EXML resource from an XML {@link InputSource}.
     * @param is the EXML source.
     * @param loader the API to use to load the content.
     * @return the loaded CMS node.
     * @throws IOException in case of failure
     * @throws DuplicateObjectException if another object with the same identifier as a loaded object already exists in another repository.
     * @throws IndexException in case of error accessing the indexes
     */
    @objid ("2b11cbb4-3faf-11e2-87cb-001ec947ccaf")
    SmObjectImpl load(final InputSource is, IModelLoader loader) throws IOException, DuplicateObjectException, IndexException;
}

