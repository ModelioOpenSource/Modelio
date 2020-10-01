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

package org.modelio.semantic.browser.panel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.TextStyle;
import org.modelio.platform.model.ui.swt.labelprovider.UniversalLabelProvider2;
import org.modelio.platform.ui.CoreColorRegistry;
import org.modelio.platform.ui.CoreFontRegistry;
import org.modelio.semantic.browser.plugin.SemanticBrowser;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.SmStatus;
import org.modelio.vcore.smkernel.mapi.MAttribute;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmFeature;

/**
 * Browser tree label provider.
 */
@objid ("e9c5c32a-7001-4fe3-b54f-144fdd5c1229")
public class SmBrowserLabelProvider extends UniversalLabelProvider2 {
    @objid ("77bec57e-6348-4316-a244-5ef0be1aa55d")
    private static final Image MDEPENDENCY = SemanticBrowser.getImageDescriptor("icons/meta/metaassoc.png").createImage(true);

    @objid ("8a72ebd9-090f-4765-8509-5de035a9c658")
    private static final Image MATTRIBUTE = SemanticBrowser.getImageDescriptor("icons/meta/metaatt.png").createImage(true);

    @objid ("f74d3396-019a-46a3-9b57-dffb3ed3f425")
    private final Styler metaStyler;

    @objid ("41490dc9-7110-4b68-ae02-27eee8c7a939")
    protected final Color METACOLOR = CoreColorRegistry.getColor(new RGB(191,0,255));

    @objid ("e16473a5-35ee-48b8-88ba-436779289d8c")
    private static final Image MCOMPOSITION = SemanticBrowser.getImageDescriptor("icons/meta/metacomp.png").createImage();

    @objid ("8e8d76df-5122-4393-b398-fb2e05355ad9")
    private static final Font italicFont = CoreFontRegistry.getModifiedFont(normalFont, SWT.ITALIC, 1.0f);

    /**
     * Default c'tor.
     */
    @objid ("8638e297-970a-4584-b3e7-199c1b8a5177")
    public SmBrowserLabelProvider() {
        super();
        
        this.metaStyler =  new Styler() {
            @Override
            public void applyStyles(TextStyle textStyle) {
                textStyle.foreground = SmBrowserLabelProvider.this.METACOLOR;
                textStyle.font = SmBrowserLabelProvider.italicFont;
            }
        };
    }

    @objid ("bd101365-36a2-46eb-89b7-1470af39b21b")
    @Override
    public Image getImage(Object obj) {
        if (obj != null && obj instanceof SmNode) {
            final SmFeature feature = ((SmNode) obj).getFeature();
            if (feature instanceof MAttribute) {
                return MATTRIBUTE;
            } else if (feature instanceof MDependency) {
                return ((MDependency)feature).isComposition()  ? MCOMPOSITION : MDEPENDENCY;
            } else {
                return MDEPENDENCY;
            }
        } else {
            return super.getImage(obj);
        }
    }

    @objid ("94c5dd09-4e59-40a6-b1f9-69dd895cf950")
    @Override
    public StyledString getStyledText(Object obj) {
        if (obj != null) {
            if (obj instanceof SmNode) {
                final SmNode node = (SmNode) obj;
                if (node.getFeature() instanceof MAttribute) {
                    SmObjectImpl smObj = (SmObjectImpl) node.getObj();
                    final MAttribute statusSmAtt = smObj.getClassOf().statusAtt();
                    final MAttribute mAtt = (MAttribute) node.getFeature();
        
                    final StyledString s = new StyledString(mAtt.getName(), this.metaStyler);
                    Object attVal = smObj.mGet(mAtt);
                    if (mAtt == statusSmAtt) {
                        long lstatus = smObj.getData().getStatus();
                        String status = SmStatus.toString(lstatus);
                        s.append(String.format(" = %s (%#16X)", status, lstatus));
                    } else {
                        s.append(" = " + attVal);
                    }
                    s.setStyle(0, s.length(), this.metaStyler);
                    s.getStyleRanges();
                    return s;
                }
        
                if (node.getFeature() instanceof MDependency) {
                    final MDependency mDep = (MDependency) node.getFeature();
                    return new StyledString(mDep.getName() + " [" + node.getContent().size() + "]", this.metaStyler);
                }
            } else if (obj instanceof MObject) {
                return super.getStyledText(obj).append(" : ").append(((MObject) obj).getMClass().getName());
            }
        }
        return super.getStyledText(obj);
    }

}
