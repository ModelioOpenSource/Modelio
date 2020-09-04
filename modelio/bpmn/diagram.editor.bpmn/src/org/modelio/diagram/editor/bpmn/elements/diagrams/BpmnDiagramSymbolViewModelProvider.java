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

package org.modelio.diagram.editor.bpmn.elements.diagrams;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.MetamodelLabels;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.common.abstractdiagram.LayoutAssistantStyleKeys;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder.IEntryFilter;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.utils.i18n.BundledMessages;

@objid ("999d3289-3593-4aa7-8459-bdb1edecf8b0")
public class BpmnDiagramSymbolViewModelProvider {
    @objid ("123a60f9-7783-4041-a8a5-e169e6c767f7")
    public static ISymbolViewModel create(IStyle editedStyle, GmAbstractDiagram input) {
        BundledMessages i18n = DiagramElements.I18N;
        
        SymbolViewContentBuilder b = new SymbolViewContentBuilder(MetamodelLabels.getString(input.getRelatedMClass().getName()));
        
        IEntryFilter gridVisibleFilter = b.filterEquals(GmBpmnDiagramStyleKeys.VIEWGRID, Boolean.TRUE);
        
        b
                .add(b.createThemeChooserItem())
                .add(b.createStyleItem(GmBpmnDiagramStyleKeys.SHOW_SMARTLINK_HANDLE))
                .add(b.createLabelItem(i18n.getString("symbol.Diagram.group.snap"))
                        .add(b.createStyleItem(GmBpmnDiagramStyleKeys.VIEWGRID))
                        .add(b.createStyleItem(GmBpmnDiagramStyleKeys.GRIDSPACING).filter((style, context) -> style.getBoolean(GmBpmnDiagramStyleKeys.VIEWGRID) || style.getBoolean(GmBpmnDiagramStyleKeys.SNAPTOGRID)))
                        .add(b.createStyleItem(GmBpmnDiagramStyleKeys.GRIDCOLOR).filter(gridVisibleFilter))
                        .add(b.createStyleItem(GmBpmnDiagramStyleKeys.GRIDALPHA).filter(gridVisibleFilter))
                        .add(b.createStyleItem(GmBpmnDiagramStyleKeys.SNAPTOGRID))
                        .add(b.createStyleItem(GmBpmnDiagramStyleKeys.SNAPTOGEOMETRY))
        
                )
                .add(b.createLabelItem(i18n.getString("symbol.Diagram.group.background"))
                        .add(b.createStyleItem(GmBpmnDiagramStyleKeys.FILLCOLOR))
                        .add(b.createStyleItem(GmBpmnDiagramStyleKeys.FILLIMAGE))
                        .add(b.createStyleItem(GmBpmnDiagramStyleKeys.FILLALPHA)
                                .filter((style, context) -> style.getProperty(GmBpmnDiagramStyleKeys.FILLCOLOR) != null || style.getProperty(GmBpmnDiagramStyleKeys.FILLIMAGE) != null))
                        .add(b.createStyleItem(GmBpmnDiagramStyleKeys.SHOW_PAGES))
                        .add(b.createStyleItem(GmBpmnDiagramStyleKeys.PAGE_SIZE)
                                .filter(b.filterEquals(GmBpmnDiagramStyleKeys.SHOW_PAGES, Boolean.TRUE))))
                .add(b.createLabelItem(i18n.getString("symbol.Diagram.group.layout"))
                        .add(b.createStyleItem(LayoutAssistantStyleKeys.ENABLED))
                        .add(b.createStyleItem(GmBpmnDiagramStyleKeys.HORIZONTAL_LANES)
                                .filter((style, context) -> input.getRelatedElement() instanceof BpmnProcessDesignDiagram))
                        .setNextChildrenFilter(b.filterEquals(LayoutAssistantStyleKeys.ENABLED, Boolean.TRUE))
                        .add(b.createStyleItem(LayoutAssistantStyleKeys.AVOIDBENDDPOINTS))
                        .add(b.createStyleItem(LayoutAssistantStyleKeys.KEEP_DIST_ON_RESIZE))
                        .add(b.createStyleItem(LayoutAssistantStyleKeys.MINDIST))
        
        );
        return b.build(editedStyle, input);
    }

}
