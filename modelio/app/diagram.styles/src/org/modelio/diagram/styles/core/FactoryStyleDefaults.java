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

package org.modelio.diagram.styles.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.diagram.styles.core.StyleKey.FillMode;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey.ShowNameMode;
import org.modelio.diagram.styles.core.StyleKey.ShowStereotypeMode;
import org.modelio.diagram.styles.plugin.DiagramStyles;

/**
 * An implementation of IDefaulValuesProvider used by FactoryStyle as its ultimate value provider.<br>
 * The implementation is based on initialized constants.
 * <p>
 * This implementation must be maintained when MetaKeys set of values is modified !
 * 
 * @author pvlaemyn
 */
@objid ("8551243a-1926-11e2-92d2-001ec947c8cc")
public class FactoryStyleDefaults {
    @objid ("85512446-1926-11e2-92d2-001ec947c8cc")
    private static final Integer DEFAULT_LINEWIDTH = 1;

    @objid ("85512448-1926-11e2-92d2-001ec947c8cc")
    private static final Boolean DEFAULT_SHOWTAGSMODE = false;

    @objid ("8551244a-1926-11e2-92d2-001ec947c8cc")
    private static final Integer DEFAULT_LINERADIUS = 0;

    @objid ("2769e52a-1927-11e2-92d2-001ec947c8cc")
    private static final LinePattern DEFAULT_LINEPATTERN = LinePattern.LINE_SOLID;

    @objid ("2769e52c-1927-11e2-92d2-001ec947c8cc")
    private static final FillMode DEFAULT_FILLMODE = StyleKey.FillMode.SOLID;

    @objid ("2769e52e-1927-11e2-92d2-001ec947c8cc")
    private static final RepresentationMode DEFAULT_REPMODE = StyleKey.RepresentationMode.STRUCTURED;

    @objid ("2769e530-1927-11e2-92d2-001ec947c8cc")
    private static final ShowNameMode DEFAULT_SHOWNAMEMODE = StyleKey.ShowNameMode.SIMPLE;

    @objid ("2769e532-1927-11e2-92d2-001ec947c8cc")
    private static final ShowStereotypeMode DEFAULT_SHOWSTEREOTYPEMODE = StyleKey.ShowStereotypeMode.NONE;

    @objid ("2769e534-1927-11e2-92d2-001ec947c8cc")
    private static final ConnectionRouterId DEFAULT_ROUTINGMODE = ConnectionRouterId.ORTHOGONAL;

    @objid ("856aa80b-bea1-43a3-b4e9-33d261e5c56f")
    private static final Color DEFAULT_FILLCOLOR = org.eclipse.draw2d.ColorConstants.white;

    @objid ("1345ae5d-c7fa-4173-80a9-19ed049ede4a")
    private static final Color DEFAULT_PENCOLOR = org.eclipse.draw2d.ColorConstants.darkGray;

    @objid ("143ac048-b9e2-44d5-88d6-28248200c885")
    private static final Color DEFAULT_TEXTCOLOR = org.eclipse.draw2d.ColorConstants.black;

    @objid ("374f78f8-150f-4826-8f23-93f389ddb0df")
    private static final Font DEFAULT_MEDIUMFONT = new Font(Display.getDefault(), "Arial", 10, 0);

    /**
     * Provide a default value for 'sKey'. The default value resolution is based on Metakey matching if sKey has a MetaKey,otherwise
     * it is based on sKey required type for the value. Note that in this latter case, only a few 'types' support default values
     * @param sKey @return
     */
    @objid ("8551244f-1926-11e2-92d2-001ec947c8cc")
    public static Object getDefaultValue(StyleKey sKey) {
        if (sKey.getMetakey() != null) {
            return FactoryStyleDefaults.getDefaultValue(sKey.getMetakey());
        } else {
            return FactoryStyleDefaults.getDefaultValue(sKey.getType());
        }
    }

    @objid ("85512454-1926-11e2-92d2-001ec947c8cc")
    private static Object getDefaultValue(Class<?> type) {
        if (type == Font.class) {
            return FactoryStyleDefaults.DEFAULT_MEDIUMFONT;
        }
        
        // Should not happen if factory.settings properly defined
        DiagramStyles.LOG.warning("FactoryStyleDefaults: cannot guess a default for type '%s'", type.getSimpleName());
        return null;
    }

    @objid ("8551245b-1926-11e2-92d2-001ec947c8cc")
    private static Object getDefaultValue(MetaKey metaKey) {
        if (MetaKey.FILLCOLOR.equals(metaKey)) {
            return FactoryStyleDefaults.DEFAULT_FILLCOLOR;
        }
        
        if (MetaKey.FILLMODE.equals(metaKey)) {
            return FactoryStyleDefaults.DEFAULT_FILLMODE;
        }
        
        if (MetaKey.FONT.equals(metaKey)) {
            return FactoryStyleDefaults.DEFAULT_MEDIUMFONT;
        }
        
        if (MetaKey.LINECOLOR.equals(metaKey)) {
            return FactoryStyleDefaults.DEFAULT_PENCOLOR;
        }
        
        if (MetaKey.LINEWIDTH.equals(metaKey)) {
            return FactoryStyleDefaults.DEFAULT_LINEWIDTH;
        }
        
        if (MetaKey.LINERADIUS.equals(metaKey)) {
            return FactoryStyleDefaults.DEFAULT_LINERADIUS;
        }
        
        if (MetaKey.REPMODE.equals(metaKey)) {
            return FactoryStyleDefaults.DEFAULT_REPMODE;
        }
        
        if (MetaKey.SHOWNAME.equals(metaKey)) {
            return FactoryStyleDefaults.DEFAULT_SHOWNAMEMODE;
        }
        
        if (MetaKey.SHOWSTEREOTYPES.equals(metaKey)) {
            return FactoryStyleDefaults.DEFAULT_SHOWSTEREOTYPEMODE;
        }
        
        if (MetaKey.SHOWTAGS.equals(metaKey)) {
            return FactoryStyleDefaults.DEFAULT_SHOWTAGSMODE;
        }
        
        if (MetaKey.TEXTCOLOR.equals(metaKey)) {
            return FactoryStyleDefaults.DEFAULT_TEXTCOLOR;
        }
        
        if (MetaKey.LINEPATTERN.equals(metaKey)) {
            return FactoryStyleDefaults.DEFAULT_LINEPATTERN;
        }
        
        if (MetaKey.CONNECTIONROUTER.equals(metaKey)) {
            return FactoryStyleDefaults.DEFAULT_ROUTINGMODE;
        }
        
        if (MetaKey.WRAPLABEL.equals(metaKey)) {
            return false;
        }
        
        if (MetaKey.HYPERREFLINK.equals(metaKey)) {
            // No reference by default
            return null;
        }
        
        // Should not happen
        DiagramStyles.LOG.warning("FactoryStyleDefaults(): cannot guess a default value for unknown metakey '%s'", metaKey);
        return null;
    }

}
