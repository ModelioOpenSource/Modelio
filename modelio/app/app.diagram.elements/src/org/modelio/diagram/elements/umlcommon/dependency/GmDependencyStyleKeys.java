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
package org.modelio.diagram.elements.umlcommon.dependency;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.style.ElementsAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmDependency} like graphic links.
 * <p>
 * One instance must exist for each class using it.
 * 
 * @author cmarin
 */
@objid ("81270132-1dec-11e2-8cad-001ec947c8cc")
public class GmDependencyStyleKeys extends ElementsAbstractStyleKeyProvider {
    /**
     * Routing mode: bendpoint, orthogonal, ...
     */
    @objid ("81270134-1dec-11e2-8cad-001ec947c8cc")
    final StyleKey CONNECTIONROUTER;

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("8129636e-1dec-11e2-8cad-001ec947c8cc")
    final StyleKey DRAWLINEBRIDGES;

    /**
     * Text font.
     */
    @objid ("81296362-1dec-11e2-8cad-001ec947c8cc")
    final StyleKey FONT;

    /**
     * Line color
     */
    @objid ("81270137-1dec-11e2-8cad-001ec947c8cc")
    final StyleKey LINECOLOR;

    /**
     * Line width
     */
    @objid ("81296359-1dec-11e2-8cad-001ec947c8cc")
    final StyleKey LINEWIDTH;

    /**
     * Line pattern
     */
    @objid ("8129635c-1dec-11e2-8cad-001ec947c8cc")
    final StyleKey LINEPATTERN;

    /**
     * Line corners radius
     */
    @objid ("8129635f-1dec-11e2-8cad-001ec947c8cc")
    final StyleKey LINERADIUS;

    /**
     * Display name
     */
    @objid ("81296371-1dec-11e2-8cad-001ec947c8cc")
    final StyleKey SHOWLABEL;

    /**
     * Stereotype display mode.
     */
    @objid ("81296368-1dec-11e2-8cad-001ec947c8cc")
    public final StyleKey SHOWSTEREOTYPES;

    /**
     * Display tagged values
     */
    @objid ("8129636b-1dec-11e2-8cad-001ec947c8cc")
    final StyleKey SHOWTAGS;

    /**
     * Text color.
     */
    @objid ("81296365-1dec-11e2-8cad-001ec947c8cc")
    final StyleKey TEXTCOLOR;

    /**
     * Instantiates a Dependency like style key provider
     * @param prefix a prefix for style key names.
     * It is advised to use the uppercase metaclass name as prefix.
     */
    @objid ("ff1488f7-b7a9-4d3f-aca4-f91d5c0bb7f5")
    public  GmDependencyStyleKeys(String prefix) {
        this.CONNECTIONROUTER = createStyleKey(prefix+"_ROUTINGMODE",
                MetaKey.CONNECTIONROUTER);
        
        this.LINECOLOR = createStyleKey(prefix+"_LINECOLOR", MetaKey.LINECOLOR);
        
        this.LINEWIDTH = createStyleKey(prefix+"_LINEWIDTH", MetaKey.LINEWIDTH);
        
        this.LINEPATTERN = createStyleKey(prefix+"_LINEPATTERN", MetaKey.LINEPATTERN);
        
        this.LINERADIUS = createStyleKey(prefix+"_LINERADIUS", MetaKey.LINERADIUS);
        
        this.FONT = createStyleKey(prefix+"_FONT", MetaKey.FONT);
        
        this.TEXTCOLOR = createStyleKey(prefix+"_TEXTCOLOR", MetaKey.TEXTCOLOR);
        
        this.SHOWSTEREOTYPES = createStyleKey(prefix+"_SHOWSTEREOTYPES",
                MetaKey.SHOWSTEREOTYPES);
        
        this.SHOWTAGS = createStyleKey(prefix+"_SHOWTAGS", MetaKey.SHOWTAGS);
        
        this.DRAWLINEBRIDGES = createStyleKey(prefix+"_DRAWLINEBRIDGES",
                MetaKey.DRAWLINEBRIDGES);
        
        this.SHOWLABEL = createStyleKey(prefix+"_SHOWLABEL", MetaKey.SHOWLABEL);
        
    }

}
