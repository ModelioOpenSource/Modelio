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

package org.modelio.vstore.exml.versioned.load.sax;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vstore.exml.common.ILoadHelper;
import org.modelio.vstore.exml.local.loader.sax.SaxExmlLoader;
import org.modelio.vstore.exml.versioned.load.IVersionedExmlLoader;
import org.modelio.vstore.exml.versioned.load.sax.local.SaxLocalExmlLoader;
import org.xml.sax.InputSource;

/**
 * SAX model loader.
 * <p>
 * Usage: instantiate and the call {@link #load(InputSource, InputSource, IModelLoader)} for each EXML file in the repository.
 */
@objid ("c7fa47da-3fbb-11e2-87cb-001ec947ccaf")
public class SaxVersionedExmlLoader extends SaxExmlLoader implements IVersionedExmlLoader {
    @objid ("15bc3019-3fc4-11e2-87cb-001ec947ccaf")
    private SaxLocalExmlLoader localFileLoader;

    /**
     * Initialize the SAX loader.
     * 
     * @param loadHelper a load helper
     */
    @objid ("c7fa47e0-3fbb-11e2-87cb-001ec947ccaf")
    public SaxVersionedExmlLoader(ILoadHelper loadHelper) {
        super(loadHelper);
        
        this.localFileLoader = new SaxLocalExmlLoader(loadHelper);
        setDependencyContentHook(this.localFileLoader);
    }

    /**
     * Load an EXML resource from an XML {@link InputSource}.
     * 
     * @param is the EXML source.
     * @param localIs the EXML local data resource
     * @param loader the API to use to load the content.
     * @return the loaded CMS node.
     * @throws java.io.IOException in case of failure
     * @throws org.modelio.vcore.model.DuplicateObjectException if another object with the same identifier as a loaded object already exists in another repository.
     */
    @objid ("c7fa47e5-3fbb-11e2-87cb-001ec947ccaf")
    @Override
    public SmObjectImpl load(final InputSource is, final InputSource localIs, IModelLoader loader) throws DuplicateObjectException, IOException {
        if (localIs != null) {
            this.localFileLoader.load(localIs, loader);
        } else {
            this.localFileLoader.clear();
        }
        return super.load(is, loader);
    }

}
