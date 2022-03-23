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
package org.modelio.uml.activitydiagram.editor.elements.activityfinal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmActivityfinal when its representation mode is RepresentationMode.IMAGE
 */
@objid ("29a8c9f3-55b6-11e2-877f-002564c97630")
public class GmActivityFinalImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d16088aa-55c0-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = GmActivityFinalStructuredStyleKeys.REPMODE;

    @objid ("d16088ac-55c0-11e2-9337-002564c97630")
    public static final StyleKey FONT = GmActivityFinalStructuredStyleKeys.FONT;

    @objid ("d16088ae-55c0-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = GmActivityFinalStructuredStyleKeys.TEXTCOLOR;

    @objid ("d16088b0-55c0-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = GmActivityFinalStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("d16088b2-55c0-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = GmActivityFinalStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the element name label.
     */
    @objid ("d16088b4-55c0-11e2-9337-002564c97630")
    public static final StyleKey SHOWLABEL = GmActivityFinalStructuredStyleKeys.SHOWLABEL;

}
