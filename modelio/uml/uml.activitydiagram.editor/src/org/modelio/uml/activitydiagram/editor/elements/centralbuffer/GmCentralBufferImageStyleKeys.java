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
package org.modelio.uml.activitydiagram.editor.elements.centralbuffer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmCentralBuffer when its representation mode is RepresentationMode.IMAGE
 */
@objid ("29e5d2d2-55b6-11e2-877f-002564c97630")
public class GmCentralBufferImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d19f1850-55c0-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("CENTRALBUFFER_REPMODE", MetaKey.REPMODE);

    @objid ("d19f1852-55c0-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("CENTRALBUFFER_FONT", MetaKey.FONT);

    @objid ("d19f1854-55c0-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("CENTRALBUFFER_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d19f1856-55c0-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("CENTRALBUFFER_SHOWSTEREOTYPES",
                MetaKey.SHOWSTEREOTYPES);

    @objid ("d19f1858-55c0-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("CENTRALBUFFER_SHOWTAGS", MetaKey.SHOWTAGS);

}
