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

package org.modelio.diagram.editor.activity.elements.interruptible;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmInterruptible when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("2ab557e8-55b6-11e2-877f-002564c97630")
public class GmInterruptibleStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d225ad6a-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("INTERRUPTIBLE_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d225ad6c-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("INTERRUPTIBLE_LINEWIDTH", MetaKey.LINEWIDTH);

}
