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

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.patterns.exporter.PatternModelAnalysis;

@objid ("f1a25876-e0fb-4565-a3f8-e9a12ff5dc13")
public interface IPatternModelAnalyser {
    @objid ("66d400d2-eb34-44f8-826a-bac5d10b220d")
    PatternModelAnalysis runAnalysis(Package modelPattern) throws IOException;

}
