/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.communication.elements.communicationnode.v0;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.communication.style.CommunicationAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmCommunicationNode when its representation mode is RepresentationMode.IMAGE
 */
@objid ("7a608ba9-55b6-11e2-877f-002564c97630")
class GmCommunicationNodeImageStyleKeys extends CommunicationAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("9c9dccdb-55c1-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("COMMUNICATIONNODE_REPMODE", MetaKey.REPMODE);

    /**
     * Text font.
     */
    @objid ("9c9df3e9-55c1-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("COMMUNICATIONNODE_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("9c9df3ec-55c1-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("COMMUNICATIONNODE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Display stereotype.
     */
    @objid ("9c9e1af9-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("COMMUNICATIONNODE_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values.
     */
    @objid ("9c9e1afc-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("COMMUNICATIONNODE_SHOWTAGS", MetaKey.SHOWTAGS);

}
