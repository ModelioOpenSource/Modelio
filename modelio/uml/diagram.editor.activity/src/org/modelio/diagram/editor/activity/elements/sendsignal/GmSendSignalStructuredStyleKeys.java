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

package org.modelio.diagram.editor.activity.elements.sendsignal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmSendSignalAction when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("2b4cb5e1-55b6-11e2-877f-002564c97630")
public class GmSendSignalStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d06f71ea-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("SENDSIGNALACTION_REPMODE", MetaKey.REPMODE);

    @objid ("d06f71ec-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("SENDSIGNALACTION_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d06f71ee-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("SENDSIGNALACTION_FILLMODE", MetaKey.FILLMODE);

    @objid ("d070f889-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("SENDSIGNALACTION_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d070f88b-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("SENDSIGNALACTION_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d070f88d-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("SENDSIGNALACTION_FONT", MetaKey.FONT);

    @objid ("d070f88f-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("SENDSIGNALACTION_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d070f891-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("SENDSIGNALACTION_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("d070f893-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("SENDSIGNALACTION_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("d070f895-55c0-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPINS = createStyleKey("SENDSIGNALACTION_AUTOSHOWPINS", MetaKey.AUTOSHOWPINS);

}
