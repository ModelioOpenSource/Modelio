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

package org.modelio.diagram.editor.usecase.elements.usecasedependency;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.usecase.style.UseCaseAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

@objid ("5e7f9806-55b7-11e2-877f-002564c97630")
public class GmUseCaseDependencyStyleKeys extends UseCaseAbstractStyleKeyProvider {
    @objid ("d9b7ee69-55c2-11e2-9337-002564c97630")
    public static final StyleKey CONNECTIONROUTER = createStyleKey("USECASEDEPENDENCY_ROUTINGMODE",
                                                                   MetaKey.CONNECTIONROUTER);

    @objid ("d9b7ee6c-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("USECASEDEPENDENCY_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d9b7ee6f-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("USECASEDEPENDENCY_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d9b7ee72-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINERADIUS = createStyleKey("USECASEDEPENDENCY_LINERADIUS",
                                                             MetaKey.LINERADIUS);

    @objid ("d9b7ee75-55c2-11e2-9337-002564c97630")
    public static final StyleKey DRAWLINEBRIDGES = createStyleKey("USECASEDEPENDENCY_DRAWLINEBRIDGES",
                                                                  MetaKey.DRAWLINEBRIDGES);

    @objid ("d9b7ee78-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("USECASEDEPENDENCY_FONT", MetaKey.FONT);

    @objid ("d9b7ee7b-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("USECASEDEPENDENCY_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d9b7ee7e-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("USECASEDEPENDENCY_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    @objid ("d9b7ee81-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("USECASEDEPENDENCY_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("d9b7ee84-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEPATTERN = createStyleKey("USECASEDEPENDENCY_LINEPATTERN",
                                                              MetaKey.LINEPATTERN);

}
