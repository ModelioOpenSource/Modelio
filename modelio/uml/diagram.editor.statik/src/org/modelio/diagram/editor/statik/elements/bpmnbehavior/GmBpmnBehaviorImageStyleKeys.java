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

package org.modelio.diagram.editor.statik.elements.bpmnbehavior;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a {@link GmBpmnBehavior} when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("341cc38b-55b7-11e2-877f-002564c97630")
public class GmBpmnBehaviorImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a5fa14ed-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmBpmnBehaviorStructuredStyleKeys.REPMODE;

    @objid ("a5fa14ef-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = GmBpmnBehaviorStructuredStyleKeys.FONT;

    @objid ("a5fa14f1-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmBpmnBehaviorStructuredStyleKeys.TEXTCOLOR;

    @objid ("a5fa14f3-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnBehaviorStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a5fa14f5-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmBpmnBehaviorStructuredStyleKeys.SHOWTAGS;

}
