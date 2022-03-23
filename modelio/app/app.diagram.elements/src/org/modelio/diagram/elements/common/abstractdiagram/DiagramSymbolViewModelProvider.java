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
package org.modelio.diagram.elements.common.abstractdiagram;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder.IEntryFilter;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder.LabelItemBuilder;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.platform.model.ui.MetamodelLabels;

/**
 * Symbol view builder for diagrams.
 * <p>
 * This class may be subclasses to customize the produced symbol view.
 * @since 3.7
 */
@objid ("4447d771-4fe2-48dc-b367-106a39b79840")
public class DiagramSymbolViewModelProvider {
    /**
     * Create the symbol view.
     * @param editedStyle the edited style
     * @param input the selected diagram
     * @return the produced symbol view.
     */
    @objid ("84d6d04c-dd22-41a7-8a0e-9e89d3a36247")
    public ISymbolViewModel create(IStyle editedStyle, GmAbstractDiagram input) {
        SymbolViewContentBuilder b = new SymbolViewContentBuilder(MetamodelLabels.getString(input.getRelatedMClass().getName()));
        
        b
        .add(b.createThemeChooserItem())
        .add(b.createStyleItem(GmAbstractDiagramStyleKeys.SHOW_SMARTLINK_HANDLE))
        .add(createGeometrySnappingSection(b))
        .add(createBackgroundSection(b))
        .add(createLayoutAssistantSection(b));
        
        addMoreItems(b, editedStyle, input);
        return b.build(editedStyle, input);
    }

    /**
     * Hook for subclasses to add more entries to the symbol view.
     * @param input
     * @param editedStyle
     * @param b the symbol view builder
     */
    @objid ("7265e64f-163f-4eb6-a866-45eb6f472d19")
    protected void addMoreItems(SymbolViewContentBuilder b, IStyle editedStyle, IGmDiagram input) {
        // Do nothing by default.
    }

    /**
     * Create the layout assistant group for the symbol view.
     * <p>
     * The group is not added to the symbol view.
     * @param b the symbol view builder
     * @return the layout assistant group
     */
    @objid ("86eeb21a-709d-4f22-aec5-ee8c00ca88ae")
    protected LabelItemBuilder createLayoutAssistantSection(SymbolViewContentBuilder b) {
        return b.createLabelItem(DiagramElements.I18N.getString("symbol.Diagram.group.layout"))
                                        .add(b.createStyleItem(LayoutAssistantStyleKeys.ENABLED))
                                        .setNextChildrenFilter(b.filterEquals(LayoutAssistantStyleKeys.ENABLED, Boolean.TRUE))
                                        .add(b.createStyleItem(LayoutAssistantStyleKeys.AVOIDBENDDPOINTS))
                                        .add(b.createStyleItem(LayoutAssistantStyleKeys.KEEP_DIST_ON_RESIZE))
                                        .add(b.createStyleItem(LayoutAssistantStyleKeys.MINDIST));
    }

    /**
     * Create the "Background" group for the symbol view.
     * <p>
     * The group is not added to the symbol view.
     * @param b the symbol view builder
     * @return the "Background" group
     */
    @objid ("75f4b67f-2266-4a7c-bcdb-2f0566777128")
    protected LabelItemBuilder createBackgroundSection(SymbolViewContentBuilder b) {
        LabelItemBuilder group = b.createLabelItem(DiagramElements.I18N.getString("symbol.Diagram.group.background"))
                .add(b.createStyleItem(GmAbstractDiagramStyleKeys.FILLCOLOR))
                .add(b.createStyleItem(GmAbstractDiagramStyleKeys.FILLIMAGE))
                .add(b.createStyleItem(GmAbstractDiagramStyleKeys.FILLALPHA)
                        .filter((style, context) -> style.getProperty(GmAbstractDiagramStyleKeys.FILLCOLOR) != null || style.getProperty(GmAbstractDiagramStyleKeys.FILLIMAGE) != null))
                .add(b.createStyleItem(GmAbstractDiagramStyleKeys.SHOW_PAGES))
                .add(b.createStyleItem(GmAbstractDiagramStyleKeys.PAGE_SIZE)
                        .filter(b.filterEquals(GmAbstractDiagramStyleKeys.SHOW_PAGES, Boolean.TRUE)));
        return group;
    }

    /**
     * Create the geometry snapping group for the symbol view.
     * <p>
     * The group is not added to the symbol view.
     * @param b the symbol view builder
     * @return the geometry snapping group.
     */
    @objid ("b660b5f8-9530-49f7-b529-f703ae0a3133")
    protected LabelItemBuilder createGeometrySnappingSection(SymbolViewContentBuilder b) {
        IEntryFilter gridVisibleFilter = b.filterEquals(GmAbstractDiagramStyleKeys.VIEWGRID, Boolean.TRUE);
        
        LabelItemBuilder group = b.createLabelItem(DiagramElements.I18N.getString("symbol.Diagram.group.snap"))
                .add(b.createStyleItem(GmAbstractDiagramStyleKeys.VIEWGRID))
                .add(b.createStyleItem(GmAbstractDiagramStyleKeys.GRIDSPACING).filter( (style, context) 
                        -> style.getBoolean(GmAbstractDiagramStyleKeys.VIEWGRID) 
                        || style.getBoolean(GmAbstractDiagramStyleKeys.SNAPTOGRID)))
                .add(b.createStyleItem(GmAbstractDiagramStyleKeys.GRIDCOLOR).filter(gridVisibleFilter))
                .add(b.createStyleItem(GmAbstractDiagramStyleKeys.GRIDALPHA).filter(gridVisibleFilter))
                .add(b.createStyleItem(GmAbstractDiagramStyleKeys.SNAPTOGRID))
                .add(b.createStyleItem(GmAbstractDiagramStyleKeys.SNAPTOGEOMETRY));
        return group;
    }

    /**
     * Create a section that contains style keys which have a local value but are not related to the diagram.
     * <p>
     * The group is not added to the symbol view.
     * @return the geometry snapping group.
     * @param b the symbol view builder
     * @deprecated Experimental, not used on 3.7.1
     */
    @objid ("3630cf1a-3c0f-4f02-8a67-df13bdd73fba")
    @Deprecated
    protected void addLocallyDefinedSection(SymbolViewContentBuilder b, IStyle editedStyle, IGmDiagram input) {
        LabelItemBuilder group = b.createLabelItem(DiagramElements.I18N.getString("symbol.Diagram.group.local"));
        List<StyleKey> inputStyleKeys = input.getStyleKeys();
        
        for (StyleKey definedKey : editedStyle.getLocalKeys()) {
            if (!inputStyleKeys.contains(definedKey)) {
                group.add(b.createStyleItem(definedKey));
            }
        }
        
        if (! group.getChildren().isEmpty()) {
            b.add(group);
        }
        
    }

}
