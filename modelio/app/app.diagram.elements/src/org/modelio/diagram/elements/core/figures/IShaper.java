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
package org.modelio.diagram.elements.core.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Path;

/**
 * Interface used by {@link ShapedFigure} to get its {@link Path} from its bounds.
 * <p>
 * <h1>Beware</h1>
 * The created {@link Path} must be disposed when not used anymore.
 */
@objid ("7fa7266e-1dec-11e2-8cad-001ec947c8cc")
public interface IShaper {
    /**
     * Create a SWT Path fitting the given rectangle.
     * <p>
     * The returned Path must be disposed when not used anymore.
     * @param rect the bounds the created path must fit.
     * @return the created SWT path.
     */
    @objid ("7fa7266f-1dec-11e2-8cad-001ec947c8cc")
    Path createShapePath(Rectangle rect);

    @objid ("7fa72674-1dec-11e2-8cad-001ec947c8cc")
    Insets getShapeInsets(Rectangle rect);

}
