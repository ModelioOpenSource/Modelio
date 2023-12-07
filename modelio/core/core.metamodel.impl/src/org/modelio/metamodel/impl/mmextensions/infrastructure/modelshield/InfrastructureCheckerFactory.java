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
package org.modelio.metamodel.impl.mmextensions.infrastructure.modelshield;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.ICheckerFactory;

@objid ("868af67e-fd9c-4694-a37b-3645213c0fd1")
public class InfrastructureCheckerFactory implements ICheckerFactory {
    /**
     * Create and register the model shield checkers.
     * @param plan the model shield registry where checkers must be registered.
     * @param metamodel the current metamodel.
     */
    @objid ("9d7c1edb-662a-402d-926f-118bdad1fb9b")
    @Override
    public void createCheckers(final IModelShieldRegistry plan, MMetamodel metamodel) {
        new E297Checker().register(plan,metamodel);
        
    }

}
