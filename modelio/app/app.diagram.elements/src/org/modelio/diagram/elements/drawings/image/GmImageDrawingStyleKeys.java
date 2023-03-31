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
package org.modelio.diagram.elements.drawings.image;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.drawings.rectangle.GmRectangleDrawing;
import org.modelio.diagram.elements.style.ElementsAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmRectangleDrawing}.
 */
@objid ("e1d3e3bf-db94-4049-bdd5-739d5f311319")
public class GmImageDrawingStyleKeys extends ElementsAbstractStyleKeyProvider {
    @objid ("53fc6f6b-c72b-4a85-8e7c-6acff9bd5d52")
    public static final StyleKey FILLALPHA = createStyleKey("DRAWIMAGE_FILLALPHA", MetaKey.FILLALPHA);

}
