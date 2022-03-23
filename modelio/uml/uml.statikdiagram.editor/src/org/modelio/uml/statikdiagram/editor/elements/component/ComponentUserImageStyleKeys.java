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
package org.modelio.uml.statikdiagram.editor.elements.component;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * {@link GmComponent} style keys for the stereotype image representation mode.
 * 
 * @author cmarin
 */
@objid ("0581f05a-8884-49d2-9d44-2d98e8f872b4")
public class ComponentUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("89fcd0b7-c2a7-419a-849f-cecbcc07ded1")
    static final StyleKey REPMODE = ComponentStructuredStyleKeys.REPMODE;

    @objid ("4ce9449c-45f2-483c-932e-587681388a3e")
    static final StyleKey FONT = ComponentStructuredStyleKeys.FONT;

    @objid ("b231cc2a-5df7-4c2f-b427-97e72fb8fa3f")
    static final StyleKey TEXTCOLOR = ComponentStructuredStyleKeys.TEXTCOLOR;

    @objid ("16b4c8d6-62d4-403b-86d9-5db1931293f4")
    static final StyleKey SHOWNAME = ComponentStructuredStyleKeys.SHOWNAME;

    @objid ("35be21c3-2870-41c8-b978-d455c3c87c33")
    static final StyleKey SHOWSTEREOTYPES = ComponentStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("be4af42f-e1c8-4ce0-a1d3-211247c216e7")
    static final StyleKey SHOWTAGS = ComponentStructuredStyleKeys.SHOWTAGS;

    @objid ("3964dd77-ad9d-4b00-8eeb-37da00dfb14b")
    static final StyleKey SHOWPORTS = ComponentStructuredStyleKeys.SHOWPORTS;

}
