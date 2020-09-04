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

package org.modelio.diagram.editor.state.elements.fork;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmFork when its representation mode is RepresentationMode.IMAGE
 */
@objid ("f534a319-55b6-11e2-877f-002564c97630")
public class GmForkStateImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("811d6fb1-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("FORK_REPMODE", MetaKey.REPMODE);

    @objid ("811d6fb3-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("FORK_FONT", MetaKey.FONT);

    @objid ("811d6fb5-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("FORK_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("811d6fb7-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("FORK_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    @objid ("811d6fb9-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("FORK_SHOWTAGS", MetaKey.SHOWTAGS);

}
