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

package org.modelio.uml.usecasediagram.editor.elements.usecasediagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.common.abstractdiagram.LayoutAssistantStyleKeys;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder.IEntryFilter;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.platform.model.ui.MetamodelLabels;
import org.modelio.platform.utils.i18n.BundledMessages;

@objid ("0c383a0b-bdb2-4648-bf60-907796dc3427")
class UseCaseDiagramSymbolViewModelProvider {
    @objid ("22bb60c9-fabc-4354-8c40-2c702419058e")
    public static ISymbolViewModel create(IStyle editedStyle, GmAbstractDiagram input) {
        BundledMessages i18n = DiagramElements.I18N;
        
        
        
        SymbolViewContentBuilder b = new SymbolViewContentBuilder(MetamodelLabels.getString(input.getRelatedMClass().getName()));
        
        IEntryFilter gridVisibleFilter = b.filterEquals(GmUseCaseDiagramStyleKeys.VIEWGRID, Boolean.TRUE);
        
        b
                .add(b.createThemeChooserItem())
                .add(b.createStyleItem(GmUseCaseDiagramStyleKeys.SHOW_SMARTLINK_HANDLE))
                .add(b.createStyleItem(GmUseCaseDiagramStyleKeys.SHOW_SYSTEM))
                .add(b.createLabelItem(i18n.getString("symbol.Diagram.group.snap"))
                        .add(b.createStyleItem(GmUseCaseDiagramStyleKeys.VIEWGRID))
                        .add(b.createStyleItem(GmUseCaseDiagramStyleKeys.GRIDSPACING).filter((style, context) -> style.getBoolean(GmUseCaseDiagramStyleKeys.VIEWGRID) || style.getBoolean(GmUseCaseDiagramStyleKeys.SNAPTOGRID)))
                        .add(b.createStyleItem(GmUseCaseDiagramStyleKeys.GRIDCOLOR).filter(gridVisibleFilter))
                        .add(b.createStyleItem(GmUseCaseDiagramStyleKeys.GRIDALPHA).filter(gridVisibleFilter))
                        .add(b.createStyleItem(GmUseCaseDiagramStyleKeys.SNAPTOGRID))
                        .add(b.createStyleItem(GmUseCaseDiagramStyleKeys.SNAPTOGEOMETRY))
        
                )
                .add(b.createLabelItem(i18n.getString("symbol.Diagram.group.background"))
                        .add(b.createStyleItem(GmUseCaseDiagramStyleKeys.FILLCOLOR))
                        .add(b.createStyleItem(GmUseCaseDiagramStyleKeys.FILLIMAGE))
                        .add(b.createStyleItem(GmUseCaseDiagramStyleKeys.FILLALPHA)
                                .filter((style, context) -> style.getProperty(GmUseCaseDiagramStyleKeys.FILLCOLOR) != null || style.getProperty(GmUseCaseDiagramStyleKeys.FILLIMAGE) != null))
                        .add(b.createStyleItem(GmUseCaseDiagramStyleKeys.SHOW_PAGES))
                        .add(b.createStyleItem(GmUseCaseDiagramStyleKeys.PAGE_SIZE)
                                .filter(b.filterEquals(GmUseCaseDiagramStyleKeys.SHOW_PAGES, Boolean.TRUE))))
                .add(b.createLabelItem(i18n.getString("symbol.Diagram.group.layout"))
                        .add(b.createStyleItem(LayoutAssistantStyleKeys.ENABLED))
                        .setNextChildrenFilter(b.filterEquals(LayoutAssistantStyleKeys.ENABLED, Boolean.TRUE))
                        .add(b.createStyleItem(LayoutAssistantStyleKeys.AVOIDBENDDPOINTS))
                        .add(b.createStyleItem(LayoutAssistantStyleKeys.KEEP_DIST_ON_RESIZE))
                        .add(b.createStyleItem(LayoutAssistantStyleKeys.MINDIST))
        
        );
        return b.build(editedStyle, input);
    }

}
