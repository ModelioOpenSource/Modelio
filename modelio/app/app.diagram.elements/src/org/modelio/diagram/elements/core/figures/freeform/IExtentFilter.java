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

package org.modelio.diagram.elements.core.figures.freeform;

import java.util.function.Predicate;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;

/**
 * Filter to compute children figure extent.
 * @author cma
 * @since 3.7
 */
@objid ("179633d7-e950-435b-be37-4fa7ea0ca3fa")
public interface IExtentFilter extends Predicate<IFigure> {
    /**
     * Default filter that filters nothing.
     */
    @objid ("0a4cd405-e15c-4440-b816-88cc2ca45989")
    public static final IExtentFilter NONE = f -> true;

}
