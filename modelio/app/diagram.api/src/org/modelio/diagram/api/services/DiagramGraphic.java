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

package org.modelio.diagram.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.diagram.api.style.StyleHandle;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.NamedStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Base class for all diagram graphics.
 */
@objid ("48b9efb2-eca7-4db3-a021-e488ae58e118")
public abstract class DiagramGraphic implements IDiagramGraphic {
    @objid ("446289cf-6ba7-4e09-9475-9c7aebf01987")
    protected final DiagramHandle diagramHandle;

    /**
     * Initializer
     * 
     * @param diagramHandle the diagram handle
     */
    @objid ("e352f549-5a98-4dc3-bdea-8e7e4193393d")
    public DiagramGraphic(DiagramHandle diagramHandle) {
        this.diagramHandle = diagramHandle;
    }

    @objid ("22d4c4f1-353e-4417-b202-61a24cf7009f")
    @Override
    public String toString() {
        return String.format("%s(%s)", this.getClass().getSimpleName(), getElement());
    }

    @objid ("a75e268b-6e5a-4bb6-9ce1-81cdcc834739")
    @Override
    public boolean isSelected() {
        final GraphicalEditPart editPart = this.diagramHandle.getEditPart(getModel());
        return editPart.getSelected() != EditPart.SELECTED_NONE;
    }

    @objid ("f88d5f63-38a7-498b-b331-0470f61c3047")
    @Override
    public boolean isPrimarySelected() {
        final GraphicalEditPart editPart = this.diagramHandle.getEditPart(getModel());
        return editPart.getSelected() == EditPart.SELECTED_PRIMARY;
    }

    @objid ("6b33b4e0-7728-43cd-b987-26435fca62a3")
    @Override
    public String getProperty(final String property) {
        final StyleKey key = resolveStyleKey(property);
        
        if (key != null) {
            final IStyle style = getModel().getDisplayedStyle();
            return StyleKeyTypeConverter.convertToString(key, style.getProperty(key));
        }
        return null;
    }

    @objid ("2e2d1a58-54f7-42b5-8136-c3134b4dee48")
    @Override
    public IStyleHandle getStyle() {
        final IStyle cascadedStyle = getModel().getDisplayedStyle().getCascadedStyle();
        if (cascadedStyle instanceof NamedStyle) {
            final NamedStyle style = (NamedStyle) cascadedStyle;
            final StyleHandle newStyle = new StyleHandle(style);
            return newStyle;
        } else {
            return null;
        }
    }

    @objid ("1ac41701-cb47-4335-bb0a-811779b69e17")
    @Override
    public void setStyle(final IStyleHandle style) {
        final IStyle namedStyle = style != null ? DiagramStyles.getStyleManager().getStyle(style.getName()) : getModel().getDiagram().getPersistedStyle();
        getModel().getDisplayedStyle().setCascadedStyle(namedStyle);
    }

    @objid ("982caea5-364d-4d4a-a6c4-a9cfc5985ca1")
    @Override
    public List<String> getLocalPropertyNames() {
        final List<String> ret = new ArrayList<>();
        for (final StyleKey key : getModel().getDisplayedStyle().getLocalKeys()) {
            ret.add(key.getId());
        }
        return ret;
    }

    /**
     * Get a StyleKey from its name, or its MetaKey name.
     */
    @objid ("6ec13287-6a3d-4c1a-b86d-0e783d0cf18a")
    private StyleKey resolveStyleKey(final String name) {
        // Look for a property using this StyleKey
        StyleKey foundKey = StyleKey.getInstance(name);
        if (foundKey == null) {
            // No StyleKey found, look for a MetaKey and then a StyleKey
            final MetaKey meta = MetaKey.getInstance(name);
            if (meta != null) {
                foundKey = getModel().getStyleKey(meta);
            }
        }
        return foundKey;
    }

    @objid ("e1a6a98f-7250-43fb-9f4b-c276febd986b")
    @Override
    public void setProperty(final String property, final String stringValue) {
        final StyleKey key = resolveStyleKey(property);
        
        if (key != null) {
            getModel().getDisplayedStyle()
                    .setProperty(key, StyleKeyTypeConverter.convertFromString(key, stringValue));
        }
    }

    @objid ("e77a46aa-6312-4fad-a596-23eeeecb23c1")
    @Override
    public void resetLocalProperties() {
        getModel().getDisplayedStyle().reset();
    }

    @objid ("3eb19e20-f8ee-4948-adc6-084ae2053af2")
    @Override
    public void normalizeLocalProperties() {
        getModel().getDisplayedStyle().normalize();
    }

    @objid ("eb748292-1620-4bc8-8015-042ca2a3ca2e")
    @Override
    public MObject getHyperLink() {
        StyleKey styleKey = getModel().getStyleKey(MetaKey.HYPERREFLINK);
        
        if (styleKey != null) {
            final MRef ref = getModel().getDisplayedStyle().getProperty(styleKey);
            if (ref != null) {
                IMModelServices svc = getModel().getDiagram().getModelManager().getModelServices();
                return svc.findByRef(ref);
            }
        }
        return null;
    }

    @objid ("3a082084-eef8-48bf-ba64-41595b5320af")
    @Override
    public void setHyperLink(MObject obj) {
        StyleKey styleKey = getModel().getStyleKey(MetaKey.HYPERREFLINK);
        if (styleKey != null) {
            getModel().getDisplayedStyle().setProperty(styleKey, new MRef(obj));
        }
    }

    /**
     * @return the represented graphic model.
     */
    @objid ("2c5d39ba-d3d6-4c39-bd6f-e2f38b624a1c")
    public abstract IGmObject getModel();

    @objid ("9939c657-fc97-45db-b2d1-033767d85e67")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        final IGmObject model = getModel();
        
        result = prime * result + (model == null ? 0 : model.hashCode());
        return result;
    }

    @objid ("8e54eb74-e433-4754-9f00-a85bb4a58056")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj.getClass() != getClass())) {
            return false;
        }
        
        final DiagramGraphic other = (DiagramGraphic) obj;
        return Objects.equals(getModel(), other.getModel());
    }

}
