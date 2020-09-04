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

package org.modelio.diagram.editor.statik.elements.operationgroup;

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
import org.modelio.diagram.styles.core.StyleKey.UmaskByVisibilityStragegy;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * EditPart for {@link GmOperationGroup}.
 * <p>
 * All behavior is currently inherited from {@link GroupEditPart}. This class may be deleted if no more behavior is to
 * be added.
 */
@objid ("3601fe19-55b7-11e2-877f-002564c97630")
public class OperationGroupEditPart extends GroupEditPart {
    @objid ("3601fe1d-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        IGmObject model = getModel();
        if (model.getLayoutData() != null) {
            getFigure().getParent().setConstraint(getFigure(), model.getLayoutData());
        }
    }

    @objid ("3601fe20-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return selectableOnlyWithParent();
    }

    @objid ("3601fe25-55b7-11e2-877f-002564c97630")
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

    @objid ("fd37bcc2-f2e9-43c5-a8bd-5b13ac959558")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(GmGroup.PROP_REFRESH_FROM_OBMODEL, new GroupRefreshFromModelEditPolicy(this::getExpectedChildren));
    }

    @objid ("572ee6c5-49b5-45dd-aab8-c2599e450c48")
    private List<? extends MObject> getExpectedChildren(MObject t) {
        Classifier classifier = (Classifier) t;
        List<Operation> classOperations = classifier.getOwnedOperation();
        
        // Unmask missing children
        GmOperationGroup gmGroup = (GmOperationGroup) getModel();
        final StyleKey.UmaskByVisibilityStragegy mode = gmGroup.getVisibilityFilter(UmaskByVisibilityStragegy.ALL);
        
        switch (mode) {
        case ALL:
            return classOperations;
        case ALL_PUBLIC:
            return classOperations
                    .stream()
                    .filter(att -> att.getVisibility() == VisibilityMode.PUBLIC)
                    .collect(Collectors.toList());
        
        case ALL_NON_PRIVATE:
            return classOperations
                    .stream()
                    .filter(att -> att.getVisibility() != VisibilityMode.PRIVATE)
                    .collect(Collectors.toList());
        case MANUAL:
            // accept all already unmasked elements, sorted in the model order
            return ((GmGroup) gmGroup).getChildren()
                    .stream()
                    .map(GmNodeModel::getRelatedElement)
                    .sorted(Comparator.comparingInt(el -> classOperations.indexOf(el)))
                    .collect(Collectors.toList());
        default:
            // do nothing.
            return null;
        }
    }

}
