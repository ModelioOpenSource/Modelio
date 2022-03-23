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
package org.modelio.uml.activitydiagram.editor.elements.acceptsignal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmAcceptsignal when its representation mode is RepresentationMode.IMAGE
 */
@objid ("4b543bf1-dac1-4655-bd6b-da4d917a310d")
public class GmAcceptSignalUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("f92c8113-ba50-4a2d-afed-388129069098")
    static final StyleKey REPMODE = GmAcceptSignalStructuredStyleKeys.REPMODE;

    @objid ("1ef8eb36-e9a5-4425-a93f-2fc9cf0d7258")
    static final StyleKey FONT = GmAcceptSignalStructuredStyleKeys.FONT;

    @objid ("907d4c77-05cb-46ba-b66e-0d987c915b21")
    static final StyleKey TEXTCOLOR = GmAcceptSignalStructuredStyleKeys.TEXTCOLOR;

    @objid ("adc01049-c96c-42b1-b343-db90f8dcba87")
    static final StyleKey SHOWSTEREOTYPES = GmAcceptSignalStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("2c51c866-eae7-415c-8c76-5e1726ebb0be")
    static final StyleKey SHOWTAGS = GmAcceptSignalStructuredStyleKeys.SHOWTAGS;

    @objid ("0102fb4a-2781-4e53-a113-e690ee01e271")
    static final StyleKey AUTOSHOWPINS = GmAcceptSignalStructuredStyleKeys.AUTOSHOWPINS;

}
