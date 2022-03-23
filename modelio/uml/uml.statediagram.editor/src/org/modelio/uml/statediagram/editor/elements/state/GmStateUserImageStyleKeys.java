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
package org.modelio.uml.statediagram.editor.elements.state;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmState when its representation mode is RepresentationMode.IMAGE
 */
@objid ("47d44733-c3eb-4286-a0b4-be747e465d1f")
public class GmStateUserImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("a87f9c3d-14e9-495d-b0f5-55170be57e82")
    static final StyleKey REPMODE = GmStateStructuredStyleKeys.REPMODE;

    @objid ("030eeb48-60d5-4c77-8167-2d83a5519403")
    static final StyleKey FONT = GmStateStructuredStyleKeys.FONT;

    @objid ("d6c29302-5658-43a6-94ad-10a2a71e0d1a")
    static final StyleKey TEXTCOLOR = GmStateStructuredStyleKeys.TEXTCOLOR;

    @objid ("9db7c8f9-d7c3-40ca-8f11-81febcea575a")
    static final StyleKey SHOWSTEREOTYPES = GmStateStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("9deac244-9ca4-4743-b204-46f6d0841c09")
    static final StyleKey SHOWTAGS = GmStateStructuredStyleKeys.SHOWTAGS;

    @objid ("b993ed4b-c9f8-4a1a-ac67-cd2605cf5c2c")
    static final StyleKey AUTOSHOWPOINTS = GmStateStructuredStyleKeys.AUTOSHOWPOINTS;

}
