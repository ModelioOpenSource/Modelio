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

package org.modelio.diagram.editor.statik.elements.attribute;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmAttribute
 */
@objid ("40b6dc45-4e08-4b86-9881-61ea27454fd6")
public class GmAttributeStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Text font.
     */
    @objid ("245566e2-1bfc-4422-8efd-3f9006372e1a")
     static final StyleKey FONT = createStyleKey("ATTRIBUTE_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("ab995d95-50e4-4e09-86c9-4fdb17f171f0")
     static final StyleKey TEXTCOLOR = createStyleKey("ATTRIBUTE_TEXTCOLOR", MetaKey.TEXTCOLOR);

}
