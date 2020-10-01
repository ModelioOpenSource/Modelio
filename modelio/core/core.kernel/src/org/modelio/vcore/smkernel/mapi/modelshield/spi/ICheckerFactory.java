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

package org.modelio.vcore.smkernel.mapi.modelshield.spi;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.services.IMetamodelDependentService;

/**
 * <p>Service that may be provided by metamodel extensions to register model shield checkers.</p>
 */
@objid ("09e92eb0-c0ff-444f-b5f4-34129f791805")
public interface ICheckerFactory extends IMetamodelDependentService {
    /**
     * Factory implementation that makes nothing.
     */
    @objid ("4bc8f7d7-3d4b-4114-b962-b935b3b5be68")
    public static final ICheckerFactory NONE = new ICheckerFactory() {
		@Override
		public void createCheckers(IModelShieldRegistry plan, MMetamodel metamodel) {
			// do nothing
		}
	};

    /**
     * Create and register the model shield checkers.
     * 
     * @param plan the model shield registry where checkers must be registered.
     * @param metamodel the current metamodel.
     */
    @objid ("cc3bccbe-7e09-4500-bc0d-b6af4a975933")
    void createCheckers(final IModelShieldRegistry plan, MMetamodel metamodel);

}
