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

package org.modelio.uml.usecasediagram.editor.elements.usecase.v0;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.usecasediagram.editor.style.UseCaseAbstractStyleKeyProvider;

@objid ("5e6ecf06-55b7-11e2-877f-002564c97630")
class GmUseCaseSimpleStyleKeys extends UseCaseAbstractStyleKeyProvider {
    @objid ("d9c5aa1c-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("USECASE_REPMODE", MetaKey.REPMODE);

    @objid ("d9c5aa1f-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("USECASE_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d9c5aa22-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("USECASE_FILLMODE", MetaKey.FILLMODE);

    @objid ("d9c5aa25-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("USECASE_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d9c5aa28-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("USECASE_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d9c5aa2b-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("USECASE_FONT", MetaKey.FONT);

    @objid ("d9c5aa2e-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("USECASE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d9c5aa31-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = createStyleKey("USECASE_SHOWNAME", MetaKey.SHOWNAME);

    @objid ("d9c730ab-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("USECASE_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    @objid ("d9c730ae-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("USECASE_SHOWTAGS", MetaKey.SHOWTAGS);

}
