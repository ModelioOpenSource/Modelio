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

package org.modelio.api.impl.diagrams;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.DataFormatException;
import org.modelio.api.modelio.diagram.IDGDynamicDecorator.IOverwrittenProperties;
import org.modelio.api.modelio.diagram.IDGDynamicDecorator;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.services.StyleKeyTypeConverter;
import org.modelio.diagram.elements.common.abstractdiagram.IDynamicStyler;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.styles.core.DynamicStyle;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.FillMode;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Implementation of {@link IDynamicStyler} delegating style computing to an {@link IDGDynamicDecorator}.
 */
@objid ("73d523d1-995e-4766-ab0d-1fdebac70b12")
public class DGDynamicStyler implements IDynamicStyler {
    /**
     * Guard against infinite loops, only one {@link GmModel} must be decorated at a time.
     */
    @objid ("79927adf-0357-4fbe-8eea-7d7d5e3add87")
    private boolean decorationInProgress;

    @objid ("9acb5f15-7058-47a9-9981-98449800b503")
    private IDGDynamicDecorator dgDecorator;

    @objid ("83a75466-c51a-482e-aafb-edfec5fc9f7d")
    private OverwrittenProperties properties = new OverwrittenProperties();

    @objid ("ddb84bae-1da0-41eb-a109-f4e8011cecca")
    public DGDynamicStyler(IDGDynamicDecorator dgDecorator) {
        this.dgDecorator = dgDecorator;
        this.decorationInProgress = false;
    }

    @objid ("b2474e4b-5290-4887-befe-e514420183e6")
    @Override
    public IStyle getStyle(GmModel gmModel, IStyle originalStyle) {
        if (this.decorationInProgress) {
            return originalStyle;
        }
        this.decorationInProgress = true;
        
        // Reset properties
        this.properties.init(gmModel, originalStyle);
        
        this.dgDecorator.decorate(this.properties);
        
        this.decorationInProgress = false;
        return this.properties.isEmpty() ? originalStyle : this.properties.getDynamicStyle();
    }

    @objid ("c91eaae8-d724-4457-8ef6-50d17084d735")
    private static class OverwrittenProperties implements IOverwrittenProperties {
        @objid ("a90b46ef-53d4-436f-908c-32adbe6a4024")
        private MObject element;

        @objid ("25625bd2-5871-48b4-808e-ef533a224cad")
        private GmModel gmModel;

        @objid ("f30f300f-fc35-443b-9803-fd0242cba8de")
        private IStyle originalStyle;

        /**
         * Lazy-loading field, keep it empty until needed.
         */
        @objid ("2aa0996b-c17f-49d6-add4-20ea4615b593")
        private DynamicStyle styleOverwrite;

        @objid ("2af55935-b056-4e17-a1a8-0ff3d3f20dad")
        public void init(GmModel model, IStyle style) {
            this.element = model.getRelatedElement();
            this.gmModel = model;
            this.originalStyle = style;
            this.styleOverwrite = null;
        }

        @objid ("6782efc1-43b0-40ad-a663-12a8064271c7")
        @Override
        public IDiagramGraphic getDG(IDiagramHandle diagram) {
            return DGFactory.getInstance().getDiagramGraphic(diagram, this.gmModel);
        }

        @objid ("950de295-fa2c-40d3-9401-d571db14c553")
        @Override
        public MObject getElement() {
            return this.element;
        }

        @objid ("1ef5f9ec-c0b9-4a9b-a413-3329ad754cdb")
        @Override
        public void setProperty(String property, String value) {
            final StyleKey key = resolveStyleKey(property);
            
            if (key != null) {
                overloadProperty(key, StyleKeyTypeConverter.convertFromString(key, value));
            }
        }

        @objid ("618d7ac7-cf5f-48a5-bc2f-26b4704bb978")
        @Override
        public void setDrawLineBridges(boolean value) {
            final StyleKey styleKey = this.gmModel.getStyleKey(MetaKey.DRAWLINEBRIDGES);
            if (styleKey == null) {
                return;
            }
            overloadProperty(styleKey, value);
        }

        @objid ("d623640e-5d27-4ca2-a4a5-869635e83969")
        @Override
        public void setFont(String value) throws DataFormatException {
            final StyleKey styleKey = this.gmModel.getStyleKey(MetaKey.FONT);
            if (styleKey == null) {
                return;
            }
            overloadProperty(styleKey, StyleKeyTypeConverter.convertFromString(styleKey, value));
        }

        @objid ("67f1d413-55c6-4d72-a895-5c48315778fa")
        @Override
        public void setLineColor(String value) {
            final StyleKey styleKey = this.gmModel.getStyleKey(MetaKey.LINECOLOR);
            if (styleKey == null) {
                return;
            }
            overloadProperty(styleKey, StyleKeyTypeConverter.convertFromString(styleKey, value));
        }

        @objid ("e4ba8244-753c-42a6-87dd-53fa5df5098b")
        @Override
        public void setLinePattern(int value) {
            final StyleKey styleKey = this.gmModel.getStyleKey(MetaKey.LINEPATTERN);
            if (styleKey == null) {
                return;
            }
            LinePattern pattern;
            
            switch (value) {
            case 0:
                pattern = LinePattern.LINE_SOLID;
                break;
            case 1:
                pattern = LinePattern.LINE_DASH;
                break;
            case 2:
                pattern = LinePattern.LINE_DOT;
                break;
            case 3:
                pattern = LinePattern.LINE_DASHDOT;
                break;
            case 4:
                pattern = LinePattern.LINE_DASHDOTDOT;
                break;
            default:
                pattern = LinePattern.LINE_SOLID;
            }
            
            overloadProperty(styleKey, pattern);
        }

        @objid ("73e708eb-3145-4ed1-89f6-bdaa6de4b079")
        @Override
        public void setLineRadius(int value) {
            final StyleKey styleKey = this.gmModel.getStyleKey(MetaKey.LINERADIUS);
            if (styleKey == null) {
                return;
            }
            
            overloadProperty(styleKey, value);
        }

        @objid ("2994aca2-38fa-4df7-b918-0d592310af58")
        @Override
        public void setLineWidth(int value) {
            final StyleKey styleKey = this.gmModel.getStyleKey(MetaKey.LINEWIDTH);
            if (styleKey == null) {
                return;
            }
            
            overloadProperty(styleKey, value);
        }

        @objid ("4a0d4b87-caea-4208-8dfc-e8577df146e5")
        @Override
        public void setTextColor(String value) {
            final StyleKey styleKey = this.gmModel.getStyleKey(MetaKey.TEXTCOLOR);
            if (styleKey == null) {
                return;
            }
            overloadProperty(styleKey, StyleKeyTypeConverter.convertFromString(styleKey, value));
        }

        @objid ("b75f669d-8f86-4bfa-990c-c0bae6d6cccc")
        @Override
        public void setFillColor(String value) {
            final StyleKey styleKey = this.gmModel.getStyleKey(MetaKey.FILLCOLOR);
            if (styleKey == null) {
                return;
            }
            
            overloadProperty(styleKey, StyleKeyTypeConverter.convertFromString(styleKey, value));
        }

        @objid ("937b5909-6214-49cf-8a4e-e9c88c6d68c3")
        @Override
        public void setFillMode(int value) {
            final StyleKey styleKey = this.gmModel.getStyleKey(MetaKey.FILLMODE);
            if (styleKey == null) {
                return;
            }
            
            FillMode pattern;
            
            switch (value) {
            case 0:
                pattern = FillMode.TRANSPARENT;
                break;
            case 1:
                pattern = FillMode.SOLID;
                break;
            case 2:
                pattern = FillMode.GRADIENT;
                break;
            default:
                pattern = FillMode.GRADIENT;
            }
            
            overloadProperty(styleKey, pattern);
        }

        /**
         * Get a StyleKey from its name, or its MetaKey name.
         */
        @objid ("6759764c-60a9-49a4-949c-75fc6984db7a")
        private StyleKey resolveStyleKey(final String name) {
            // Look for a property using this StyleKey
            StyleKey foundKey = StyleKey.getInstance(name);
            if (foundKey == null) {
                // No StyleKey found, look for a MetaKey and then a StyleKey
                final MetaKey meta = MetaKey.getInstance(name);
                if (meta != null) {
                    foundKey = this.gmModel.getStyleKey(meta);
                }
            }
            return foundKey;
        }

        @objid ("a9ec8b58-7654-4041-a391-b06c1f30406a")
        @Override
        public AbstractDiagram getDiagram() {
            return this.gmModel.getDiagram().getRelatedElement();
        }

        /**
         * Get the overwriting style, instantiating a new style if necessary.
         */
        @objid ("e09127fd-4b7a-4f50-9fb4-726093437e7c")
        private DynamicStyle getDynamicStyle() {
            if (this.styleOverwrite == null) {
                this.styleOverwrite = new DynamicStyle(this.originalStyle);
            }
            return this.styleOverwrite;
        }

        /**
         * @return <code>true</code> if at least one property is overwritten.
         */
        @objid ("ee9b41a3-0d90-4de1-85a8-eb1481add07d")
        private boolean isEmpty() {
            return this.styleOverwrite == null;
        }

        @objid ("302d32db-078d-43b2-99da-7d6993ed3ab7")
        private void overloadProperty(final StyleKey styleKey, Object value) {
            getDynamicStyle().setLocalProperty(styleKey, value);
        }

    }

}
