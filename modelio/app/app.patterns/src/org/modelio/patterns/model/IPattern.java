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

package org.modelio.patterns.model;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.pattern.IPatternService.PatternException;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This interface represents a runtime pattern, ready to be applied on a model.
 */
@objid ("b97f2cdd-8da3-4b1c-b1a9-7edb420dff31")
public interface IPattern {
    /**
     * Apply a pattern on the model.
     * 
     * @param root the element to create the model in.
     * @param coreSession the current model manipulation session.
     * @param parameters the parameters to configure the pattern from.
     * @throws org.modelio.api.modelio.pattern.IPatternService.PatternException if the pattern creation fails.
     */
    @objid ("ca011653-4be0-4d01-80d2-e0af28bb62af")
    void createModel(MObject root, ICoreSession coreSession, Map<String, Object> parameters) throws PatternException;

}
