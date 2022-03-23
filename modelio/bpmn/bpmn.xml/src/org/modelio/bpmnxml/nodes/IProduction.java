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
package org.modelio.bpmnxml.nodes;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("be6e853d-b911-403c-99c4-1af9c8d7ba48")
public interface IProduction {
    @objid ("5d9fac72-6b4f-42c0-97e5-04b4413d0924")
    void setElements(Map<String, Object> elementsMap);

}
