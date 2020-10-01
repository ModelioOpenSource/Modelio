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

package org.modelio.diagram.elements.umlcommon.namespaceuse;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.style.ElementsAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmNamespaceUse}.
 */
@objid ("817cd5cc-1dec-11e2-8cad-001ec947c8cc")
public class GmNamespaceUseStyleKeys extends ElementsAbstractStyleKeyProvider {
    /**
     * Routing mode: bendpoint, orthogonal, ...
     */
    @objid ("817cd5ce-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey CONNECTIONROUTER = createStyleKey("NAMESPACEUSE_ROUTINGMODE",
                                                                   MetaKey.CONNECTIONROUTER);

    /**
     * Line color
     */
    @objid ("817cd5d1-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey LINECOLOR = createStyleKey("NAMESPACEUSE_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("817cd5d4-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey LINEWIDTH = createStyleKey("NAMESPACEUSE_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Text font.
     */
    @objid ("817cd5d7-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey FONT = createStyleKey("NAMESPACEUSE_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("817cd5da-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey TEXTCOLOR = createStyleKey("NAMESPACEUSE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Line pattern
     */
    @objid ("817cd5dd-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey LINEPATTERN = createStyleKey("NAMESPACEUSE_LINEPATTERN", MetaKey.LINEPATTERN);

}
