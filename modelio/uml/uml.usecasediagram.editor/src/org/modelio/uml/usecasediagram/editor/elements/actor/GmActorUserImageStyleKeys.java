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

package org.modelio.uml.usecasediagram.editor.elements.actor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.usecasediagram.editor.style.UseCaseAbstractStyleKeyProvider;

@objid ("7334c1c6-fe3d-4146-b35e-ec2dc90db763")
public class GmActorUserImageStyleKeys extends UseCaseAbstractStyleKeyProvider {
    @objid ("683e0599-f66e-4928-9de7-1ab339e83f74")
    public static final StyleKey REPMODE = createStyleKey("ACTOR_REPMODE", MetaKey.REPMODE);

    @objid ("5d1004ec-35e2-417b-b1a0-235ee735a85f")
    public static final StyleKey FONT = createStyleKey("ACTOR_FONT", MetaKey.FONT);

    @objid ("b921ea2f-ada0-4314-8efc-8f7302300e27")
    public static final StyleKey TEXTCOLOR = createStyleKey("ACTOR_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("b8d14df9-9f2c-4afd-885c-a40d3c96a82f")
    public static final StyleKey SHOWNAME = createStyleKey("ACTOR_SHOWNAME", MetaKey.SHOWNAME);

    @objid ("37cbd9dd-d460-4012-85ca-07c8c7afa897")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("ACTOR_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    @objid ("4ed60861-05d3-4b33-bf90-0e525f39eb81")
    public static final StyleKey SHOWTAGS = createStyleKey("ACTOR_SHOWTAGS", MetaKey.SHOWTAGS);

}
