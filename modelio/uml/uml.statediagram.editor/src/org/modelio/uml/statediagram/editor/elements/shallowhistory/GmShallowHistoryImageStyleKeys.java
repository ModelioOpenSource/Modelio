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
package org.modelio.uml.statediagram.editor.elements.shallowhistory;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmShallowHistory when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("f57025ba-55b6-11e2-877f-002564c97630")
public class GmShallowHistoryImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("813145cc-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("SHALLOWHISTORY_REPMODE", MetaKey.REPMODE);

    @objid ("813145ce-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("SHALLOWHISTORY_FONT", MetaKey.FONT);

    @objid ("813145d0-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("SHALLOWHISTORY_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("813145d2-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("SHALLOWHISTORY_SHOWSTEREOTYPES",
                                                                          MetaKey.SHOWSTEREOTYPES);

    @objid ("813145d4-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("SHALLOWHISTORY_SHOWTAGS", MetaKey.SHOWTAGS);

}
