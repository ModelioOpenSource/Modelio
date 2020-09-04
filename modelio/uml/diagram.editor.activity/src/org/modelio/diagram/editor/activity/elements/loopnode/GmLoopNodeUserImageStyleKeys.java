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

package org.modelio.diagram.editor.activity.elements.loopnode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmLoopnode when its representation mode is RepresentationMode.IMAGE
 */
@objid ("4cabb3b5-2fc6-42cf-a9cd-41aa016211a2")
public class GmLoopNodeUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("22f178c9-8aa3-4686-80fa-878f20b7d141")
     static final StyleKey REPMODE = GmLoopNodeStructuredStyleKeys.REPMODE;

    /**
     * Text font.
     */
    @objid ("5790a55c-57f9-476e-955c-2cc16ebca31f")
     static final StyleKey FONT = GmLoopNodeStructuredStyleKeys.FONT;

    /**
     * Text color.
     */
    @objid ("d632d580-4e15-4e36-9a07-681e621beb3e")
     static final StyleKey TEXTCOLOR = GmLoopNodeStructuredStyleKeys.TEXTCOLOR;

    /**
     * Show stereotypes.
     */
    @objid ("6d8832a8-2906-4603-b70e-6916a601e956")
     static final StyleKey SHOWSTEREOTYPES = GmLoopNodeStructuredStyleKeys.SHOWSTEREOTYPES;

    /**
     * Show tagged values
     */
    @objid ("f5bf77de-8c6e-4697-ac6b-7c17e720598a")
     static final StyleKey SHOWTAGS = GmLoopNodeStructuredStyleKeys.SHOWTAGS;

    @objid ("01da1f3c-c71d-4920-a7e4-549c394a8fea")
     static final StyleKey AUTOSHOWPINS = GmLoopNodeStructuredStyleKeys.AUTOSHOWPINS;

}
