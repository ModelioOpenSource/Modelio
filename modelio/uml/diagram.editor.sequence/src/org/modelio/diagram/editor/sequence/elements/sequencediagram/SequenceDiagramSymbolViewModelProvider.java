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

package org.modelio.diagram.editor.sequence.elements.sequencediagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramSymbolViewModelProvider;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder;
import org.modelio.diagram.styles.core.IStyle;

/**
 * Symbol view builder for the sequence diagram background.
 * <p>
 * Add "Show sequence" style key.
 * @author cma
 * @since 3.7.1
 */
@objid ("1891f2d1-0f58-40f5-90cf-c9ee6d1df38d")
class SequenceDiagramSymbolViewModelProvider extends DiagramSymbolViewModelProvider {
    @objid ("07174a8b-964f-45c3-a2ee-fc5905941723")
    @Override
    protected void addMoreItems(SymbolViewContentBuilder b, IStyle editedStyle, IGmDiagram input) {
        b.add(b.createLabelItem(GmSequenceDiagramStyleKeys.SHOWSEQUENCE.getCategory())
                .add(b.createStyleItem(GmSequenceDiagramStyleKeys.SHOWSEQUENCE)));
    }

}
