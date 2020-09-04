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

package org.modelio.diagram.editor.statik.elements.collab.v0;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmUseCase when its representation mode is
 * RepresentationMode.STRUCTURED
 */
@objid ("34647ab9-55b7-11e2-877f-002564c97630")
class CollaborationStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("a61712de-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("COLLABORATION_REPMODE", MetaKey.REPMODE);

    /**
     * Fill color.
     */
    @objid ("a61712e1-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("COLLABORATION_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Fill mode.
     */
    @objid ("a61712e4-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("COLLABORATION_FILLMODE", MetaKey.FILLMODE);

    /**
     * Line color.
     */
    @objid ("a61712e7-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("COLLABORATION_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width.
     */
    @objid ("a61712ea-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("COLLABORATION_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Text font.
     */
    @objid ("a61712ed-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("COLLABORATION_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("a61712f0-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("COLLABORATION_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Display name.
     */
    @objid ("a6189969-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = createStyleKey("COLLABORATION_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Display stereotypes.
     */
    @objid ("a618996c-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("COLLABORATION_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values.
     */
    @objid ("a618996f-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("COLLABORATION_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Display class visibility.
     */
    @objid ("a6189972-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWVISIBILITY = createStyleKey("COLLABORATION_SHOWVISIBILITY",
                                                                 MetaKey.SHOWVISIBILITY);

    /**
     * Filter on features
     */
    @objid ("a6189975-55c2-11e2-9337-002564c97630")
    public static final StyleKey FEATURES = createStyleKey("COLLABORATION_FEATURES", MetaKey.VISIBILITYFILTER);

    /**
     * Inner classes visualization mode: labels, diagram or none.
     */
    @objid ("a6189978-55c2-11e2-9337-002564c97630")
    public static final StyleKey INNERVIEWMODE = createStyleKey("COLLABORATION_INNERVIEWMODE",
                                                                MetaKey.InnerGroup.INNERVIEWMODE);

    /**
     * Style keys for all internal structure group members.
     * <p>
     * INTERNALSVIEWMODE and INTERNALS are used for internal structure zone too.
     * 
     * @author cmarin
     */
    @objid ("34660161-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static class InternalStructure extends StaticAbstractStyleKeyProvider {
        /**
         * Internal structure visualization mode: labels, diagram or none.
         */
        @objid ("a618997b-55c2-11e2-9337-002564c97630")
         static final StyleKey INTERNALSVIEWMODE = createStyleKey("COLLABORATION_INTERNALSVIEWMODE",
                                                                 MetaKey.InternalGroup.INTVIEWMODE);

        /**
         * Text color.
         */
        @objid ("a618997e-55c2-11e2-9337-002564c97630")
         static final StyleKey TEXTCOLOR = createStyleKey("COLLABORATION_INTERNAL_TEXTCOLOR",
                                                         MetaKey.InternalGroup.INTTEXTCOLOR);

        /**
         * Font
         */
        @objid ("a6189981-55c2-11e2-9337-002564c97630")
         static final StyleKey FONT = createStyleKey("COLLABORATION_INTERNAL_FONT",
                                                    MetaKey.InternalGroup.INTFONT);

        /**
         * Stereotype display mode.
         */
        @objid ("a6189984-55c2-11e2-9337-002564c97630")
         static final StyleKey SHOWSTEREOTYPES = createStyleKey("COLLABORATION_INTERNAL_SHOWSTEREOTYPES",
                                                               MetaKey.InternalGroup.INTSHOWSTEREOTYPES);

        /**
         * Display tagged values.
         */
        @objid ("a6189987-55c2-11e2-9337-002564c97630")
         static final StyleKey SHOWTAGS = createStyleKey("COLLABORATION_INTERNAL_SHOWTAGS",
                                                        MetaKey.InternalGroup.INTSHOWTAGS);

        /**
         * Auto unmask internal structure content. Boolean type.
         */
        @objid ("a618998a-55c2-11e2-9337-002564c97630")
         static final StyleKey AUTOUNMASK = createStyleKey("COLLABORATION_INTERNAL_AUTOUNMASK",
                                                          MetaKey.InternalGroup.INTAUTOUNMASK);

    }

    /**
     * Style keys for all inner classifiers group members.
     * 
     * @author cmarin
     */
    @objid ("34660185-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static class Inner extends StaticAbstractStyleKeyProvider {
        /**
         * Text color.
         */
        @objid ("a618998f-55c2-11e2-9337-002564c97630")
        public static final StyleKey TEXTCOLOR = createStyleKey("COLLABORATION_INNER_TEXTCOLOR",
                                                                MetaKey.InnerGroup.INNERTEXTCOLOR);

        /**
         * Font
         */
        @objid ("a6189992-55c2-11e2-9337-002564c97630")
        public static final StyleKey FONT = createStyleKey("COLLABORATION_INNER_FONT",
                                                           MetaKey.InnerGroup.INNERFONT);

        /**
         * Stereotype display mode.
         */
        @objid ("a61a2009-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWSTEREOTYPES = createStyleKey("COLLABORATION_INNER_SHOWSTEREOTYPES",
                                                                      MetaKey.InnerGroup.INNERSHOWSTEREOTYPES);

        /**
         * Display tagged values.
         */
        @objid ("a61a200c-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWTAGS = createStyleKey("COLLABORATION_INNER_SHOWTAGS",
                                                               MetaKey.InnerGroup.INNERSHOWTAGS);

    }

}
