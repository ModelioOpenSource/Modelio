/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.elements.core.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.text.AbstractFlowBorder;

/**
 * Same as {@link org.eclipse.draw2d.MarginBorder} for {@link FlowFigure}.
 */
@objid ("c17d97a7-f8b9-49a3-bf86-b79d6dcc1a2a")
public final class MarginFlowBorder extends AbstractFlowBorder {
    @objid ("0ff83c49-d6e4-4063-93ab-25004d024691")
    private Insets inset;

    /**
     * Constructor.
     * 
     * @param inset the insets.
     */
    @objid ("c39432a8-08eb-414a-9537-e4e7d2aa0bae")
    public MarginFlowBorder(final Insets inset) {
        this.inset = inset;
    }

    @objid ("1be4c4f4-2358-4a02-a3f1-ab51ebecf3da")
    @Override
    public Insets getInsets(final IFigure figure) {
        return this.inset;
    }

}
