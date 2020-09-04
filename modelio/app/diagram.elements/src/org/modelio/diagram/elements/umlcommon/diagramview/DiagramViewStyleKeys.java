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

package org.modelio.diagram.elements.umlcommon.diagramview;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.style.ElementsAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmDiagramView}.
 * 
 * @author cmarin
 */
@objid ("8137b18b-1dec-11e2-8cad-001ec947c8cc")
public class DiagramViewStyleKeys extends ElementsAbstractStyleKeyProvider {
    /**
     * Fill color
     */
    @objid ("8137b18d-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey FILLCOLOR = createStyleKey("NOTE_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Line color
     */
    @objid ("8137b190-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey LINECOLOR = createStyleKey("NOTE_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("8137b193-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey LINEWIDTH = createStyleKey("NOTE_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Text font
     */
    @objid ("8137b196-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey FONT = createStyleKey("NOTE_FONT", MetaKey.FONT);

    /**
     * Text color
     */
    @objid ("8137b199-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey TEXTCOLOR = createStyleKey("NOTE_TEXTCOLOR", MetaKey.TEXTCOLOR);

}
