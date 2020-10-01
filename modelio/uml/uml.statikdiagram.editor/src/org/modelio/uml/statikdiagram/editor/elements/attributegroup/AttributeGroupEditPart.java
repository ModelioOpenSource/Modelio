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

package org.modelio.uml.statikdiagram.editor.elements.attributegroup;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.common.group.GroupEditPart;
import org.modelio.diagram.elements.common.group.GroupRefreshFromModelEditPolicy;
import org.modelio.diagram.elements.core.figures.borders.TLBRBorder;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * EditPart for {@link GmAttributeGroup}.
 * <p>
 * All behavior is currently inherited from {@link GroupEditPart}. This class may be deleted if no more behavior is to be added.
 * 
 * @author cmarin
 */
@objid ("3402d2b9-55b7-11e2-877f-002564c97630")
public class AttributeGroupEditPart extends GroupEditPart {
    @objid ("3404595b-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        IGmObject model = getModel();
        if (model.getLayoutData() != null) {
            getFigure().getParent().setConstraint(getFigure(), model.getLayoutData());
        }
    }

    @objid ("3404595e-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return selectableOnlyWithParent();
    }

    @objid ("34045963-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        super.refreshFromStyle(aFigure, style);
        final GmModel gmModel = getModel();
        TLBRBorder border = new TLBRBorder(true, false, false, false);
        if (gmModel.getStyleKey(MetaKey.LINECOLOR) != null) {
            border.setColor(style.getColor(gmModel.getStyleKey(MetaKey.LINECOLOR)));
        }
        if (gmModel.getStyleKey(MetaKey.LINEWIDTH) != null) {
            border.setWidth(style.getInteger(gmModel.getStyleKey(MetaKey.LINEWIDTH)));
        }
        aFigure.setBorder(border);
    }

    @objid ("57bbb2d4-c167-4d53-a601-470fb9e20a63")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(GmModel.PROP_REFRESH_FROM_OBMODEL, new GroupRefreshFromModelEditPolicy(this::getExpectedChildren));
    }

    @objid ("8af90ebf-b540-4491-9c0d-d430890223b1")
    private List<? extends MObject> getExpectedChildren(MObject t) {
        Classifier classifier = (Classifier) t;
        List<Attribute> classAttributes = classifier.getOwnedAttribute();
        
        // Unmask missing children
        GmAttributeGroup gmGroup = (GmAttributeGroup) getModel();
        final StyleKey.UmaskByVisibilityStragegy mode = gmGroup.getVisibilityFilter();
        if (mode == null) {
            return classAttributes;
        }
        
        switch (mode) {
        case ALL:
            return classAttributes;
        case ALL_PUBLIC:
            return classAttributes
                    .stream()
                    .filter(att -> att.getVisibility() == VisibilityMode.PUBLIC)
                    .collect(Collectors.toList());
        
        case ALL_NON_PRIVATE:
            return classAttributes
                    .stream()
                    .filter(att -> att.getVisibility() != VisibilityMode.PRIVATE)
                    .collect(Collectors.toList());
        case MANUAL:
            // accept all already unmasked elements, sorted in the model order
            return ((GmGroup) gmGroup).getChildren()
                    .stream()
                    .map(GmNodeModel::getRelatedElement)
                    .sorted(Comparator.comparingInt(el -> classAttributes.indexOf(el)))
                    .collect(Collectors.toList());
        default:
            // do nothing.
            return null;
        }
    }

}
