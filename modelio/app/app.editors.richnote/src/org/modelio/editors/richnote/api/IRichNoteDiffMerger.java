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
package org.modelio.editors.richnote.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;

/**
 * Rich note diff/merge service.
 * @author cmarin
 */
@objid ("78fbc219-0219-4a88-b1f1-278caeae044e")
public interface IRichNoteDiffMerger {
    /**
     * Compare the first element with the second one.
     * <p>
     * The merge result is saved in the first element.
     * @param element The element to diff/merge into.
     * @param compareWith The element compared with.
     */
    @objid ("ffa67f4c-0306-459b-9af5-087748ba947a")
    void diffMerge(final Element element, final Element compareWith);
}

