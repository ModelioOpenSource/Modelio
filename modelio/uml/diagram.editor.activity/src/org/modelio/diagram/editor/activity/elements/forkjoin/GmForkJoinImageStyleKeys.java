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

package org.modelio.diagram.editor.activity.elements.forkjoin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmForkjoin when its representation mode is RepresentationMode.IMAGE
 */
@objid ("2a8483eb-55b6-11e2-877f-002564c97630")
public class GmForkJoinImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d20a362d-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("FORKJOIN_REPMODE", MetaKey.REPMODE);

    @objid ("d20a362f-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("FORKJOIN_FONT", MetaKey.FONT);

    @objid ("d20a3631-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("FORKJOIN_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d20a3633-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("FORKJOIN_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("d20a3635-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("FORKJOIN_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("d20a3637-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("FORKJOIN_SHOWLABEL", MetaKey.SHOWLABEL);

}
