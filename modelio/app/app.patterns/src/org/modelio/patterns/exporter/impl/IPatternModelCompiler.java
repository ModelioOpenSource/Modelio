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
package org.modelio.patterns.exporter.impl;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.pattern.IPatternService.PatternException;
import org.modelio.patterns.model.RuntimePattern;

@objid ("0cdd9c0b-6a9a-4412-b48f-3225487ef9af")
public interface IPatternModelCompiler {
    @objid ("273ebdb9-98ed-4aad-b3ee-382ef2a4d0f4")
    void exportPattern(RuntimePattern pattern) throws PatternException;

}
