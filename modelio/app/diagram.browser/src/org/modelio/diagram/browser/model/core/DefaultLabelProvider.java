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

package org.modelio.diagram.browser.model.core;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.modelio.core.ui.swt.images.AbstractModelioElementLabelProvider;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.core.ui.swt.images.ElementStyler;
import org.modelio.core.ui.swt.images.FragmentImageService;
import org.modelio.core.ui.swt.images.FragmentStyledLabelProvider;
import org.modelio.diagram.browser.plugin.DiagramBrowser;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.visitors.DefaultInfrastructureVisitor;
import org.modelio.ui.UIColor;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.osgi.framework.Bundle;

/**
 * StyledCellLabelProvider for the diagram browser view.
 */
@objid ("00375384-0d4f-10c6-842f-001ec947cd2a")
public class DefaultLabelProvider extends AbstractModelioElementLabelProvider {
    @objid ("00377d14-0d4f-10c6-842f-001ec947cd2a")
    private final Map<String, Image> images = new HashMap<>();

    @objid ("0037965a-0d4f-10c6-842f-001ec947cd2a")
    private final ImageDescriptor linkOverlayImageDescriptor;

    @objid ("00379cf4-0d4f-10c6-842f-001ec947cd2a")
    private static final Color GRAY = Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);

    @objid ("0037acf8-0d4f-10c6-842f-001ec947cd2a")
    private static final Color DARK_GRAY = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY);

    @objid ("009733c6-1f1a-10c7-842f-001ec947cd2a")
    private TreeViewer browserView;

    /**
     * Default constructor, initializing the view.
     * 
     * @param browserView The diagram browser view.
     */
    @objid ("0037bc7a-0d4f-10c6-842f-001ec947cd2a")
    public DefaultLabelProvider(TreeViewer browserView) {
        this();
        this.browserView = browserView;
    }

    @objid ("0037e678-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public void dispose() {
        super.dispose();
        for (Image image : this.images.values()) {
            image.dispose();
        }
        this.images.clear();
        this.browserView = null;
    }

    @objid ("0038052c-0d4f-10c6-842f-001ec947cd2a")
    private DefaultLabelProvider() {
        Bundle imageBundle = Platform.getBundle(DiagramBrowser.PLUGIN_ID);
        
        URL bitmapUrl = FileLocator.find(imageBundle, new Path("icons/opened_folder.png"), null);
        this.images.put("OPENEDFOLDER", ImageDescriptor.createFromURL(bitmapUrl).createImage());
        
        bitmapUrl = FileLocator.find(imageBundle, new Path("icons/closed_folder.png"), null);
        this.images.put("CLOSEDFOLDER", ImageDescriptor.createFromURL(bitmapUrl).createImage());
        
        bitmapUrl = FileLocator.find(imageBundle, new Path("icons/opened_set.png"), null);
        this.images.put("OPENEDSET", ImageDescriptor.createFromURL(bitmapUrl).createImage());
        
        bitmapUrl = FileLocator.find(imageBundle, new Path("icons/closed_set.png"), null);
        this.images.put("CLOSEDSET", ImageDescriptor.createFromURL(bitmapUrl).createImage());
        
        bitmapUrl = FileLocator.find(imageBundle, new Path("icons/refoverlay.png"), null);
        this.linkOverlayImageDescriptor = ImageDescriptor.createFromURL(bitmapUrl);
    }

    @objid ("28530a8a-4ab5-11e2-a4d3-002564c97630")
    @Override
    public boolean showAsReference(Object object) {
        return object instanceof DiagramRef;
    }

    @objid ("28530a90-4ab5-11e2-a4d3-002564c97630")
    @Override
    public String getText(Object object) {
        return getStyledText(object).getString();
    }

    @objid ("28530a96-4ab5-11e2-a4d3-002564c97630")
    @Override
    public Image getImage(Object object) {
        if (object instanceof VirtualFolder) {
            // A folder image is either a folder image (closed or opened) or the standard image of its represented element
            final VirtualFolder vf = (VirtualFolder) object;
            MObject element = vf.getRepresentedElement();
            if (element == null) {
                if (this.browserView.getExpandedState(object) == true) {
                    return this.images.get("OPENEDFOLDER");
                } else {
                    return this.images.get("CLOSEDFOLDER");
                }
            } else {
                return ElementImageService.getIcon(element);
            }
        } else if (object instanceof DiagramRef) {
            return ElementImageService.getIcon(((DiagramRef) object).getReferencedDiagram());
        } else if (object instanceof DiagramSet) {
            if (this.browserView.getExpandedState(object) == true) {
                return this.images.get("OPENEDSET");
            } else {
                return this.images.get("CLOSEDSET");
            }
        } else if (object instanceof MObject) {
            return ElementImageService.getIcon(((MObject) object));
        } else if (object instanceof IProjectFragment) {
            IProjectFragment fragment = (IProjectFragment) object;
            return FragmentImageService.getImage(fragment);
        }
        return null;
    }

    /**
     * Get the background color for the given element in the given state
     * 
     * @param obj the element
     * @return its background color
     */
    @objid ("2859251b-4ab5-11e2-a4d3-002564c97630")
    private static Color getBackground(Object obj) {
        return null;
    }

    /**
     * Return the foreground color for the given element according to those rules:
     * <ul>
     * <li>Modifiable model elements font color is black #000000.</li>
     * <li>Non-modifiable model component elements font color is dark grey #606060.</li>
     * <li>Incomplete model elements font color is light red #FF8080.</li>
     * <li>Ramc model elements font color is modified yellow #A0A000.</li>
     * </ul>
     * 
     * @return a Color.
     */
    @objid ("28592520-4ab5-11e2-a4d3-002564c97630")
    private static Color getForeground(Object obj) {
        if (obj instanceof VirtualFolder) {
            return DefaultLabelProvider.DARK_GRAY;
        } else if (obj instanceof DiagramSet) {
            MObject diagramSet = (DiagramSet) obj;
            if (diagramSet.getStatus().isRamc()) {
                return DefaultLabelProvider.GRAY;
            }
        } else if (obj instanceof MObject) {
            MObject element = (MObject) obj;
            if (element.getStatus().isModifiable()) {
                return UIColor.MODIFIABLE_ELEMENT_FG;
            } else if (element.getStatus().isRamc()) {
                return UIColor.RAMC_ELEMENT_FG;
            } else if (element.getStatus().isShell()) {
                return UIColor.SHELL_ELEMENT_FG;
            } else {
                return UIColor.NONMODIFIABLE_ELEMENT_FG;
            }
        } else if (obj instanceof IProjectFragment) {
            IProjectFragment fragment = (IProjectFragment) obj;
            switch (fragment.getType()) {
            case EXML:
            case EXML_SVN:
                return UIColor.BLACK;
            case RAMC:
            case MDA:
                return UIColor.RAMC_ELEMENT_FG;
            case EXML_URL:
                return UIColor.NONMODIFIABLE_ELEMENT_FG;
            default:
                DiagramBrowser.LOG.warning("No color found for fragment type: " + fragment.getType());
                break;
            }
        }
        return UIColor.BLACK;
    }

    @objid ("17bf7e67-c452-481e-b5a0-b911cd97e3ae")
    @Override
    public StyledString getStyledText(Object object) {
        // Get the right styled string
        if (object == null) {
            return new StyledString("<null>");
        } else if (object instanceof MObject) {
            return LabelTextProvider.getLabel((MObject) object);
        } else if (object instanceof VirtualFolder) {
            VirtualFolder folder = (VirtualFolder) object;
            if (folder.getRepresentedElement() == null) {
                return new StyledString(folder.getName());
            } else {
                return LabelTextProvider.getLabel(folder.getRepresentedElement());
            }
        } else if (object instanceof DiagramRef) {
            return LabelTextProvider.getLabel(((DiagramRef) object).getReferencedDiagram());
        } else if (object instanceof IProjectFragment) {
            return FragmentStyledLabelProvider.getStyledText((IProjectFragment) object);
        }
        return new StyledString();
    }

    /**
     * Helping class to compute an element label text . Note: DefaultMetamodelVisitor propagates missing visitXXXX methods up in the inheritance tree
     */
    @objid ("0039d7e4-0d4f-10c6-842f-001ec947cd2a")
    static class LabelTextProvider extends DefaultInfrastructureVisitor {
        @objid ("003a0124-0d4f-10c6-842f-001ec947cd2a")
        private static final LabelTextProvider INSTANCE = new LabelTextProvider();

        /**
         * This one is called for any kind of diagram
         */
        @objid ("003a3892-0d4f-10c6-842f-001ec947cd2a")
        @Override
        public Object visitAbstractDiagram(AbstractDiagram theAbstractDiagram) {
            final StyledString symbol = new StyledString();
            symbol.append(theAbstractDiagram.getName(), ElementStyler.getStyler(theAbstractDiagram));
            return symbol;
        }

        @objid ("003a78fc-0d4f-10c6-842f-001ec947cd2a")
        @Override
        public Object visitDiagramSet(DiagramSet theDiagramSet) {
            final StyledString symbol = new StyledString();
            symbol.append(theDiagramSet.getName(), ElementStyler.getStyler(theDiagramSet));
            return symbol;
        }

        /**
         * Essential termination code. at the top of the inheritance tree let's return null
         */
        @objid ("003aadb8-0d4f-10c6-842f-001ec947cd2a")
        @Override
        public Object visitElement(Element theElement) {
            final StyledString symbol = new StyledString();
            symbol.append(theElement.getName(), ElementStyler.getStyler(theElement));
            return symbol;
        }

        @objid ("de87e206-8f0d-4380-b7fc-b9782d64cc45")
        public static StyledString getLabel(MObject element) {
            return (StyledString) element.accept(LabelTextProvider.INSTANCE);
        }

    }

}
