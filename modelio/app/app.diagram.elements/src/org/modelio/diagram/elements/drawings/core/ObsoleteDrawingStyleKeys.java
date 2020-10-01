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

package org.modelio.diagram.elements.drawings.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.style.ElementsAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

@objid ("87388d8a-3293-4db0-8fbb-1d1cb164d3fb")
public class ObsoleteDrawingStyleKeys extends ElementsAbstractStyleKeyProvider {
    /**
     * Hyperlink
     */
    @objid ("9984e4a6-8793-425e-a17e-5a688b004811")
    public static final StyleKey RECT_HYPERLINK = createStyleKey("RECTANGLE_HYPERLINK", MetaKey.HYPERREFLINK);

    /**
     * Hyperlink
     */
    @objid ("8cafdd4a-b3e4-4da8-bd82-59d96e3f5595")
    public static final StyleKey TEXT_HYPERLINK = createStyleKey("DRAWTEXT_HYPERLINK", MetaKey.HYPERREFLINK);

    /**
     * Hyperlink
     */
    @objid ("c727c30b-279f-4c2e-8f46-303ab31f38f7")
    public static final StyleKey ELLIPSE_HYPERLINK = createStyleKey("ELLIPSE_HYPERLINK", MetaKey.HYPERREFLINK);

    /**
     * Hyperlink
     */
    @objid ("193ff5ce-599c-4e44-8df3-8411ad9d63fe")
    public static final StyleKey LINE_HYPERLINK = createStyleKey("DRAWLINE_HYPERLINK", MetaKey.HYPERREFLINK);

}
