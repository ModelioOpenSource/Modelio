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

package org.modelio.diagram.editor.statik.elements.signal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmSendSignalAction when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("368e5fbf-55b7-11e2-877f-002564c97630")
public class GmSignalImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a79e00ec-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmSignalStructuredStyleKeys.REPMODE;

    @objid ("a79e00ee-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = GmSignalStructuredStyleKeys.FONT;

    @objid ("a79e00f0-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmSignalStructuredStyleKeys.TEXTCOLOR;

    @objid ("a79e00f2-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = GmSignalStructuredStyleKeys.SHOWNAME;

    @objid ("a79e00f4-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmSignalStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a79e00f6-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmSignalStructuredStyleKeys.SHOWTAGS;

    @objid ("a79e00f8-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWVISIBILITY = GmSignalStructuredStyleKeys.SHOWVISIBILITY;

}
