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

package org.modelio.diagram.editor.statik.elements.classifier.style;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.plugin.DiagramEditorStatik;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder.ItemBuilder;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder;
import org.modelio.diagram.styles.core.StyleKey.InternalsViewMode;
import org.modelio.utils.i18n.BundledMessages;

@objid ("0c122319-cb0d-490f-aca9-ec95a95a2550")
public class ClassifierSymbolModelBuilder {
    /**
     * Access to 'diagram.editor.statik' plugin messages bundle.
     */
    @objid ("d8f4eabe-fe3e-44c4-a5ff-133a82f9fac4")
    protected static final BundledMessages staticI18n = DiagramEditorStatik.I18N;

    @objid ("7fdfd3e3-d278-443d-a962-878e3a98d417")
    public static ItemBuilder<?> createInnerClassesSection(ClassifierInnerGroupKeys Inner) {
        SymbolViewContentBuilder builder = new SymbolViewContentBuilder("");
        return builder.createLabelItem(DiagramEditorStatik.I18N.getString("symbol.Classifier.group.InnerClasses"))
                                        .add(builder.createStyleItem(Inner.INNERVIEWMODE))
                                        .setNextChildrenFilter(builder.filter(Inner.INNERVIEWMODE,  v -> v == InternalsViewMode.LIST))
                                        .add(builder.createStyleItem(Inner.FONT))
                                        .add(builder.createStyleItem(Inner.SHOWNAME))
                                        .add(builder.createStyleItem(Inner.SHOWSTEREOTYPES))
                                        .add(builder.createStyleItem(Inner.SHOWTAGS))
                                        .add(builder.createStyleItem(Inner.SHOWVISIBILITY))
                                        .add(builder.createStyleItem(Inner.TEXTCOLOR)).filter(builder.structuredModeFilter);
    }

    @objid ("f43f3be9-ab37-4797-ac32-531080dbc4f1")
    public static ItemBuilder<?> createAttributesSection(ClassifierAttributeKeys Attribute) {
        SymbolViewContentBuilder builder = new SymbolViewContentBuilder("");
        return builder.createLabelItem(DiagramEditorStatik.I18N.getString("symbol.Classifier.group.Attributes"))
                                        .add(builder.createStyleItem(Attribute.ATTGROUPVISIBLE))
                                        .setNextChildrenFilter(builder.filterEquals(Attribute.ATTGROUPVISIBLE, true))
                                        .add(builder.createStyleItem(Attribute.SHOWSTEREOTYPES))
                                        .add(builder.createStyleItem(Attribute.SHOWTAGS))
                                        .add(builder.createStyleItem(Attribute.SHOWVISIBILITY))
                                        .filter(builder.structuredModeFilter);
    }

    @objid ("69baa1f4-fca1-4b6d-812c-2aa12b59f984")
    public static ItemBuilder<?> createOperationsSection(ClassifierOperationKeys Operation) {
        SymbolViewContentBuilder builder = new SymbolViewContentBuilder("");
        return builder.createLabelItem(DiagramEditorStatik.I18N.getString("symbol.Classifier.group.Operations"))
                                        .add(builder.createStyleItem(Operation.OPERATIONGROUPVISIBLE))
                                        .setNextChildrenFilter(builder.filterEquals(Operation.OPERATIONGROUPVISIBLE, true))
                                        .add(builder.createStyleItem(Operation.SHOWSIGNATURE))
                                        .add(builder.createStyleItem(Operation.SHOWSTEREOTYPES))
                                        .add(builder.createStyleItem(Operation.SHOWTAGS))
                                        .add(builder.createStyleItem(Operation.SHOWVISIBILITY))
                                        .add(builder.createStyleItem(Operation.WRAPLABEL))
                                        .filter(builder.structuredModeFilter);
    }

    @objid ("b24b4076-62ce-414b-988a-f8408ac33221")
    public static ItemBuilder<?> createInternalStructureSection(ClassifierInternalStructureKeys InternalStructure) {
        SymbolViewContentBuilder builder = new SymbolViewContentBuilder("");
        return builder.createLabelItem(DiagramEditorStatik.I18N.getString("symbol.Classifier.group.InternalStructure"))
                                        .add(builder.createStyleItem(InternalStructure.INTERNALSVIEWMODE))
                                        .add(builder.createStyleItem(InternalStructure.AUTOUNMASK).filter(
                                                InternalStructure.INTERNALSVIEWMODE,
                                                v -> !Objects.equals(v, InternalsViewMode.NONE)))
                                        .setNextChildrenFilter(builder.filter(InternalStructure.INTERNALSVIEWMODE,  v -> v == InternalsViewMode.LIST))
                                        .add(builder.createStyleItem(InternalStructure.FONT))
                                        .add(builder.createStyleItem(InternalStructure.SHOWSTEREOTYPES))
                                        .add(builder.createStyleItem(InternalStructure.SHOWTAGS))
                                        .add(builder.createStyleItem(InternalStructure.TEXTCOLOR))
                                        .filter(builder.structuredModeFilter);
    }

}
