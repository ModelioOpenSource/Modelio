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
package org.modelio.diagram.elements.umlcommon.externdocument;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.style.ElementsAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmExternDocument}.
 */
@objid ("815b74d0-1dec-11e2-8cad-001ec947c8cc")
public class GmExternDocumentStyleKeys extends ElementsAbstractStyleKeyProvider {
    /**
     * Fill color
     */
    @objid ("815b74d2-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey FILLCOLOR = createStyleKey("EXTERNDOCUMENT_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Fill mode
     */
    @objid ("815b74d5-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey FILLMODE = createStyleKey("EXTERNDOCUMENT_FILLMODE", MetaKey.FILLMODE);

    /**
     * Line color
     */
    @objid ("815b74d8-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey LINECOLOR = createStyleKey("EXTERNDOCUMENT_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("815b74db-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey LINEWIDTH = createStyleKey("EXTERNDOCUMENT_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Text font
     */
    @objid ("815b74de-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey FONT = createStyleKey("EXTERNDOCUMENT_FONT", MetaKey.FONT);

    /**
     * Text color
     */
    @objid ("815b74e1-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey TEXTCOLOR = createStyleKey("EXTERNDOCUMENT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Line style
     */
    @objid ("815b74e4-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey LINEPATTERN = createStyleKey("EXTERNDOCUMENT_LINEPATTERN",
                                                                  MetaKey.LINEPATTERN);

}
