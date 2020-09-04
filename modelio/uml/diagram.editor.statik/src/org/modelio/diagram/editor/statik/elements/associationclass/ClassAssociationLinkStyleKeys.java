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

package org.modelio.diagram.editor.statik.elements.associationclass;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmClassAssociationLink}.
 * 
 * @author cmarin
 */
@objid ("33f3908f-55b7-11e2-877f-002564c97630")
public class ClassAssociationLinkStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Connection routing mode.
     */
    @objid ("a58496cc-55c2-11e2-9337-002564c97630")
     static final StyleKey CONNECTIONROUTER = createStyleKey("CLASSASSOC_CONNECTIONROUTER",
                                                            MetaKey.CONNECTIONROUTER);

    /**
     * Line color.
     */
    @objid ("a58496cf-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("CLASSASSOC_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width.
     */
    @objid ("a58496d2-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("CLASSASSOC_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line corners radius
     */
    @objid ("a58496d5-55c2-11e2-9337-002564c97630")
     static final StyleKey LINERADIUS = createStyleKey("CLASSASSOC_LINERADIUS", MetaKey.LINERADIUS);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("a58496d8-55c2-11e2-9337-002564c97630")
     static final StyleKey DRAWLINEBRIDGES = createStyleKey("CLASSASSOC_DRAWLINEBRIDGES",
                                                           MetaKey.DRAWLINEBRIDGES);

}
