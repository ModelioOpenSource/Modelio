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
package org.modelio.uml.statediagram.editor.elements.join;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmJoin when its representation mode is RepresentationMode.IMAGE
 */
@objid ("f554ae72-55b6-11e2-877f-002564c97630")
public class GmJoinImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("8174694b-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("JOIN_REPMODE", MetaKey.REPMODE);

    @objid ("8174694d-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("JOIN_FONT", MetaKey.FONT);

    @objid ("8174905a-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("JOIN_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("8174905c-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("JOIN_SHOWSTEREOTYPES",
                                                                      MetaKey.SHOWSTEREOTYPES);

    @objid ("8174b769-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("JOIN_SHOWTAGS", MetaKey.SHOWTAGS);

}
