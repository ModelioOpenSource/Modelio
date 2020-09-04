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

package org.modelio.bpmnxml.exporter.service.processor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("157bb798-0e11-49f4-adcf-d726f7c48d26")
public interface IBPMNProcessor {
    @objid ("4436080a-6438-4d1a-9a7d-7901db0f5c47")
    Object process(Object context, Object modelioElement);

}
