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
package org.modelio.uml.statikdiagram.editor.elements.constraint;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * Style keys for Constraint (both node and link).
 * 
 * @author fpoyer
 */
@objid ("81223c72-1dec-11e2-8cad-001ec947c8cc")
public class GmConstraintStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("81223c74-1dec-11e2-8cad-001ec947c8cc")
    static final StyleKey CONNECTIONROUTER = createStyleKey("CONSTRAINT_CONNECTIONROUTER",
                                                                MetaKey.CONNECTIONROUTER);

    @objid ("81223c76-1dec-11e2-8cad-001ec947c8cc")
    static final StyleKey FILLCOLOR = createStyleKey("CONSTRAINT_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("81223c78-1dec-11e2-8cad-001ec947c8cc")
    static final StyleKey FILLMODE = createStyleKey("CONSTRAINT_FILLMODE", MetaKey.FILLMODE);

    @objid ("81223c7a-1dec-11e2-8cad-001ec947c8cc")
    static final StyleKey LINECOLOR = createStyleKey("CONSTRAINT_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("81223c7c-1dec-11e2-8cad-001ec947c8cc")
    static final StyleKey LINEWIDTH = createStyleKey("CONSTRAINT_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("81223c7e-1dec-11e2-8cad-001ec947c8cc")
    static final StyleKey FONT = createStyleKey("CONSTRAINT_FONT", MetaKey.FONT);

    @objid ("81223c80-1dec-11e2-8cad-001ec947c8cc")
    static final StyleKey TEXTCOLOR = createStyleKey("CONSTRAINT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("81223c82-1dec-11e2-8cad-001ec947c8cc")
    static final StyleKey SHOWSTEREOTYPES = createStyleKey("CONSTRAINT_SHOWSTEREOTYPES",
                                                               MetaKey.SHOWSTEREOTYPES);

    @objid ("81223c84-1dec-11e2-8cad-001ec947c8cc")
    static final StyleKey SHOWTAGS = createStyleKey("CONSTRAINT_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("81223c86-1dec-11e2-8cad-001ec947c8cc")
    static final StyleKey LINEPATTERN = createStyleKey("CONSTRAINT_LINEPATTERN", MetaKey.LINEPATTERN);

    @objid ("0c733044-56aa-439f-8a3a-0477f0e5eb72")
    static final StyleKey ALIGNMENT = createStyleKey("CONSTRAINT_ALIGNMENT", HAlign.class);

}
