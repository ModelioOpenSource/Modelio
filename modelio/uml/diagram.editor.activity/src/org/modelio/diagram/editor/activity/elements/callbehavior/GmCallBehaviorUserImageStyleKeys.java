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

package org.modelio.diagram.editor.activity.elements.callbehavior;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmCallBehavior when its representation mode is RepresentationMode.IMAGE
 */
@objid ("855e5087-ca17-41a8-8c6b-3d8e0425bdce")
public class GmCallBehaviorUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("8a688856-e7cc-46b4-96d4-d2722d53beff")
     static final StyleKey REPMODE = GmCallBehaviorStructuredStyleKeys.REPMODE;

    @objid ("9c207482-6c2d-47ba-a741-3d50b9282699")
     static final StyleKey FONT = GmCallBehaviorStructuredStyleKeys.FONT;

    @objid ("a38a57d4-5811-4738-8d28-8ff37e9b76b8")
     static final StyleKey TEXTCOLOR = GmCallBehaviorStructuredStyleKeys.TEXTCOLOR;

    @objid ("44fb46f0-b526-4095-9957-7c23e28b3927")
     static final StyleKey SHOWSTEREOTYPES = GmCallBehaviorStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("0bcde6c1-b64b-4ad0-96b2-21f90fe56dda")
     static final StyleKey SHOWTAGS = GmCallBehaviorStructuredStyleKeys.SHOWTAGS;

    @objid ("4b6df75e-dccd-406c-9c66-705643f6765d")
     static final StyleKey AUTOSHOWPINS = GmCallBehaviorStructuredStyleKeys.AUTOSHOWPINS;

}
