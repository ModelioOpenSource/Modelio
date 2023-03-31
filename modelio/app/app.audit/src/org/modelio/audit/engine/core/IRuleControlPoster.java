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
package org.modelio.audit.engine.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Service passed to {@link IRule} in order to post controls depending on model changes.
 */
@objid ("58e9abc1-0cf7-4a1a-ab23-2383f3e29c14")
public interface IRuleControlPoster {
    /**
     * Post a check.
     * @param control the check to run
     * @param element the element to run the check on.
     */
    @objid ("798dbc1e-3797-4416-af20-2917d9ee7c1b")
    void postControl(IControl control, MObject element);
}

