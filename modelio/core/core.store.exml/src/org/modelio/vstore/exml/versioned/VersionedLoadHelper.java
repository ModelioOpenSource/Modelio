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
package org.modelio.vstore.exml.versioned;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vstore.exml.common.IExmlBase;
import org.modelio.vstore.exml.common.LoadHelper;

/**
 * Load helper for versioned EXML repositories.
 */
@objid ("6d10bc88-2e72-11e2-8aaa-001ec947ccaf")
class VersionedLoadHelper extends LoadHelper {
    @objid ("679724a9-2e7b-11e2-8aaa-001ec947ccaf")
    private final IVersionStatusInitializer statusInitializer;

    /**
     * initialize the helper.
     * @param exmlBase the EXML repository
     * @param statusInitializer the element status initializer
     * @param loadReadWrite <code>true</code> if the repository is read/write, <code>false</code> if read only.
     */
    @objid ("679724aa-2e7b-11e2-8aaa-001ec947ccaf")
    public  VersionedLoadHelper(IExmlBase exmlBase, IVersionStatusInitializer statusInitializer, boolean loadReadWrite) {
        super(Objects.requireNonNull(exmlBase), loadReadWrite);
        
        this.statusInitializer = Objects.requireNonNull(statusInitializer);
        
    }

    @objid ("679724b0-2e7b-11e2-8aaa-001ec947ccaf")
    @Override
    public void initObjectFlags(IModelLoader modelLoader, SmObjectImpl obj) {
        this.statusInitializer.loadStatus(obj, modelLoader);
        
        if (!this.loadReadWrite) {
            modelLoader.setRStatus(obj, 0, IRStatus.USERWRITE, 0);
        }
        
    }

}
