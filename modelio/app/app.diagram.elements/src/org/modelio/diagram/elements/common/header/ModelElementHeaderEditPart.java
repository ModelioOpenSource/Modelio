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
package org.modelio.diagram.elements.common.header;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.common.edition.TextDirectEditManager;
import org.modelio.diagram.elements.common.label.base.AutoFitOnEditEditPolicy;
import org.modelio.diagram.elements.core.figures.ChainedLayout;
import org.modelio.diagram.elements.core.figures.labelum.LabelumFigure;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.DefaultElementDirectEditPolicy;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.ShowStereotypeMode;

/**
 * Manages a GmModelElementHeader. The unique editing policy is the text edition of the main label.
 */
@objid ("7e7134d7-1dec-11e2-8cad-001ec947c8cc")
public class ModelElementHeaderEditPart extends AbstractNodeEditPart {
    @objid ("7e7134d9-1dec-11e2-8cad-001ec947c8cc")
    protected static final List<Image> emptyImageList = Collections.emptyList();

    /**
     * Default constructor.
     */
    @objid ("0350116f-4efc-425b-9f59-9fe8bd0d9426")
    public  ModelElementHeaderEditPart() {
        super();
    }

    /**
     * Get the main label figure.
     * <p>
     * The main label usually displays the element name.
     * @return the main label figure.
     */
    @objid ("7e739715-1dec-11e2-8cad-001ec947c8cc")
    public LabelumFigure getMainLabelFigure() {
        return getHeaderFigure(getFigure()).getMainLabelFigure();
    }

    /**
     * By default model element headers are not selectable.
     */
    @objid ("7e7134e1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("7e73970b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void performRequest(Request req) {
        if (req.getType() == RequestConstants.REQ_DIRECT_EDIT) {
            GmModelElementHeader gm = (GmModelElementHeader) getModel();
            if (gm.getRelatedElement() == null || gm.getRelatedElement().isShell() || gm.getRelatedElement().isDeleted()
                    || !gm.getRelatedElement().getStatus().isModifiable() || this.getViewer().getControl() == null) {
                return;
            }
        
            final IEditableText editableText = ((GmModel) getModel()).getEditableText();
            if (editableText != null) {
                IHeaderFigure headerFigure = getHeaderFigure(getFigure());
                TextDirectEditManager
                .forLabelum(
                        this,
                        headerFigure,
                        editableText.getText(),
                        !headerFigure.isWrapped())
                .show();
            }
        } else {
            super.performRequest(req);
        }
        
    }

    @objid ("7e739711-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(IGmObject.PROPERTY_LABEL)) {
            refreshVisuals();
        }
        
        super.propertyChange(evt);
        
    }

    @objid ("7e73971c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new DefaultElementDirectEditPolicy());
        installEditPolicy(AutoFitOnEditEditPolicy.ROLE, new AutoFitOnEditEditPolicy());
        
    }

    /**
     * Creates a {@link WrappedHeaderFigure}.
     */
    @objid ("7e73971f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createFigure() {
        final GmModelElementHeader gm = (GmModelElementHeader) getModel();
        // Create the header figure
        final IHeaderFigure headerFigure;
        if (isFlat(gm)) {
            headerFigure = new FlatHeaderFigure();
        } else {
            headerFigure = new WrappedHeaderFigure();
        }
        
        // Set style dependent properties
        refreshFromStyle(headerFigure, getModelStyle());
        
        // Set style independent properties
        headerFigure.setLineWidth(0);
        
        // Main label
        refreshLabel(headerFigure);
        
        // Tagged values
        refreshTaggedValues(headerFigure);
        
        // Stereotypes
        ShowStereotypeMode mode = getStereotypeMode(gm);
        refreshStereotypes(headerFigure, mode);
        
        // Keyword
        refreshMetaclassKeyword(headerFigure, gm, mode);
        
        // Metaclass icon
        refreshMetaclassIcon(headerFigure, gm, mode);
        return headerFigure;
    }

    /**
     * Get the {@link IHeaderFigure} managed by this edit part.
     * <p>
     * Default implementation casts the argument to <code>IHeaderFigure</code> and returns it.
     * <p>
     * Sub classes that may put the <code>IHeaderFigure</code> inside another figure and modify the implementation.
     * @param aFigure a figure, usually {@link #getFigure()}.
     * @return the {@link IHeaderFigure}.
     */
    @objid ("22dbeaaa-2086-4b4c-9531-c5e6149dc779")
    protected IHeaderFigure getHeaderFigure(IFigure aFigure) {
        return (IHeaderFigure) aFigure;
    }

    /**
     * Get whether each stereotype is in its own &lt;&lt; >> or all are in the same &lt;&lt;a, b, c ...>>.
     * <p>
     * By default asks to {@link GmModelElementHeader#isDisplayStereotypesAsStack()}, may be redefined in sub classes.
     * @return
     * <li><i>true</i>: each stereotype is in its &lt;&lt; >>.<br>
     * <li><i>false</i>: all stereotypes will be in a single &lt;&lt;a, b, c ...>> label
     */
    @objid ("8f3f5a12-b6c9-48dc-941b-628481899947")
    protected boolean isDisplayStereotypesAsStack(GmModelElementHeader gm) {
        return gm.isDisplayStereotypesAsStack();
    }

    /**
     * Get whether the given model must be displayed as a flat header or a stacked wrapped header.
     * <p>
     * The default implementation directly asks the question to the model.
     * @param gm the graphic model
     * @return <i>true</i> to display it flat.
     */
    @objid ("d3cd668d-b667-40fd-ab72-a5ba4c70f45e")
    protected boolean isFlat(GmModelElementHeader gm) {
        return gm.isFlat();
    }

    @objid ("7e739726-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, IStyle style) {
        final IHeaderFigure headerFigure = getHeaderFigure(aFigure);
        
        // Pen and brush options are managed by the superclass
        super.refreshFromStyle(headerFigure, style);
        
        // We have to deal with stereotype mode and show/hide for name, stereotypes and tags
        ShowStereotypeMode mode = getStereotypeMode((GmModelElementHeader) getModel());
        refreshLabel(headerFigure);
        refreshStereotypes(headerFigure, mode);
        refreshTaggedValues(headerFigure);
        refreshWrapping(headerFigure, style);
        
    }

    @objid ("7e739739-1dec-11e2-8cad-001ec947c8cc")
    protected void refreshMetaclassIcon(final IHeaderFigure headerFigure, final GmModelElementHeader gm, ShowStereotypeMode mode) {
        ArrayList<Image> icons = new ArrayList<>(1);
        
        if (gm.isShowMetaclassIcon() && mode != ShowStereotypeMode.NONE && mode != ShowStereotypeMode.TEXT) {
            icons.add(gm.getMetaclassIcon());
        }
        headerFigure.setLeftIcons(icons);
        
    }

    @objid ("7e73973f-1dec-11e2-8cad-001ec947c8cc")
    protected void refreshMetaclassKeyword(final IHeaderFigure headerFigure, final GmModelElementHeader gm, ShowStereotypeMode mode) {
        if (gm.isShowMetaclassKeyword() && mode != ShowStereotypeMode.NONE && mode != ShowStereotypeMode.ICON && gm.getStereotypesLabel().isEmpty()) {
            headerFigure.setKeywordLabel("<<" + gm.getMetaclassKeyword() + ">>");
        } else {
            headerFigure.setKeywordLabel(null);
        }
        
    }

    /**
     * To be called when the stereotype mode changes or when the applied stereotypes change.
     * @param aFigure The figure to update.
     * @param mode the stereotype display mode.
     */
    @objid ("7e73972e-1dec-11e2-8cad-001ec947c8cc")
    protected final void refreshStereotypes(final IHeaderFigure aFigure, ShowStereotypeMode mode) {
        GmModelElementHeader gm = (GmModelElementHeader) getModel();
        
        switch (mode) {
        case ICON:
            aFigure.setRightIcons(gm.getStereotypeIcons());
            aFigure.setTopLabel("");
            break;
        case TEXT:
            aFigure.setRightIcons(ModelElementHeaderEditPart.emptyImageList);
            aFigure.setTopLabel(computeStereotypeText(gm));
            break;
        case TEXTICON:
            aFigure.setRightIcons(gm.getStereotypeIcons());
            aFigure.setTopLabel(computeStereotypeText(gm));
            break;
        default:
        case NONE:
            aFigure.setRightIcons(ModelElementHeaderEditPart.emptyImageList);
            aFigure.setTopLabel("");
            break;
        }
        
    }

    @objid ("7e739733-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshVisuals() {
        final IHeaderFigure aFigure = getHeaderFigure(getFigure());
        final GmModelElementHeader gm = (GmModelElementHeader) getModel();
        
        ShowStereotypeMode mode = getStereotypeMode(gm);
        
        // Layout data
        final Object layoutData = gm.getLayoutData();
        if (layoutData != null) {
            final IFigure parentFig = aFigure.getParent();
            if (ChainedLayout.getRootLayout(parentFig) instanceof BorderLayout
                    && !(layoutData instanceof Integer)) {
                // Happens when a node switches from structured to simple mode.
                // The structured mode owner needs a Rectangle and the simple mode one needs a BorderLayout constant.
                parentFig.setConstraint(aFigure, BorderLayout.CENTER);
            } else {
                try {
                    parentFig.setConstraint(aFigure, layoutData);
                } catch (RuntimeException e) {
                    // invalid constraint
                    // FIXME : this is only to workaround a ClassCastException. The exception needs to be debugged
                    DiagramElements.LOG.warning("%s has invalid '%s' layout constraint for %s parent: %s", this, layoutData, getParent(), e.toString());
                    DiagramElements.LOG.debug(e);
                }
            }
        }
        
        // Main label
        refreshLabel(aFigure);
        
        // Tagged values
        refreshTaggedValues(aFigure);
        
        // Stereotypes
        refreshStereotypes(aFigure, mode);
        
        // Metaclass Keyword
        refreshMetaclassKeyword(aFigure, gm, mode);
        
        // Metaclass Icon
        refreshMetaclassIcon(aFigure, gm, mode);
        
    }

    /**
     * Refresh the wrapping mode from the figure.
     * @param fig the header's figure.
     * @param style the current style.
     * @return if the wrapping mode changed
     */
    @objid ("e4c00ec5-ef25-44b1-a89b-074c840053c5")
    protected boolean refreshWrapping(IHeaderFigure fig, IStyle style) {
        GmModelElementHeader gm = (GmModelElementHeader) getModel();
        
        StyleKey propertyKey = gm.getStyleKey(MetaKey.WRAPLABEL);
        boolean wrap = propertyKey != null ? style.getBoolean(propertyKey) : !isFlat(gm);
        return fig.setWrapped(wrap);
    }

    @objid ("955d12ef-238c-42a7-a3a2-d978d9a8f3d0")
    private String computeStereotypeText(GmModelElementHeader gm) {
        if (gm.getStereotypesLabel().isEmpty()) {
            return "";
        }
        
        final StringBuilder labels = new StringBuilder();
        if (isDisplayStereotypesAsStack(gm)) {
            // Each stereotype in <<s1>> <<s2>>
            for (String s : gm.getStereotypesLabel()) {
                if (labels.length() > 2) {
                    labels.append("\u200B"); // ZERO WIDTH SPACE
                }
        
                labels.append("<<");
                labels.append(s.replace(" ", "\u00A0")); // replace spaces by non breaking spaces
                labels.append(">>");
            }
        } else {
            // All stereotypes in <<s1, s2>>
            labels.append("<<");
            for (String s : gm.getStereotypesLabel()) {
                if (labels.length() > 2) {
                    labels.append(", ");
                }
        
                labels.append(s.replace(" ", "\u00A0")); // replace spaces by non breaking spaces
            }
            labels.append(">>");
        }
        return labels.toString();
    }

    @objid ("eca498ab-3609-4a5b-af18-99cc12a6328f")
    private String computeTagsLabel(GmModelElementHeader gm) {
        List<String> taggedValuesLabels = gm.getTaggedValueLabels();
        
        if (taggedValuesLabels.isEmpty()) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder(taggedValuesLabels.size() * 20);
        
        // add new label figures
        for (String s : taggedValuesLabels) {
            if (sb.length() != 0) {
                sb.append(", ");
            }
        
            sb.append(s.replace(" ", "\u00A0")); // replace spaces by non breaking spaces
        }
        return sb.toString();
    }

    @objid ("1bf5dbbb-b4f6-4d72-b42a-6577b6c7cba7")
    private ShowStereotypeMode getStereotypeMode(final GmModelElementHeader gm) {
        ShowStereotypeMode mode = ShowStereotypeMode.NONE;
        StyleKey stylekey = gm.getStyleKey(MetaKey.SHOWSTEREOTYPES);
        if (stylekey != null) {
            mode = gm.getDisplayedStyle().getProperty(stylekey);
            if (mode == null) {
                mode = ShowStereotypeMode.NONE;
            }
        }
        return mode;
    }

    @objid ("7e739736-1dec-11e2-8cad-001ec947c8cc")
    private void refreshLabel(IHeaderFigure headerFigure) {
        GmModelElementHeader gm = (GmModelElementHeader) getModel();
        
        // Ask the gm if the label is shown
        if (gm.isShowLabel()) {
            headerFigure.setMainLabel(gm.getMainLabel());
        } else {
            headerFigure.setMainLabel("");
        }
        
    }

    /**
     * Refresh the tagged values zone.
     * @param aFigure The figure to update
     */
    @objid ("7e739745-1dec-11e2-8cad-001ec947c8cc")
    private void refreshTaggedValues(IHeaderFigure aFigure) {
        GmModelElementHeader gm = (GmModelElementHeader) getModel();
        
        StyleKey showTagKey = gm.getStyleKey(MetaKey.SHOWTAGS);
        boolean mode = showTagKey != null ? gm.getDisplayedStyle().getProperty(showTagKey) : false;
        if (mode) {
            aFigure.setBottomLabel(computeTagsLabel(gm));
        } else {
            aFigure.setBottomLabel("");
        }
        
    }

}
