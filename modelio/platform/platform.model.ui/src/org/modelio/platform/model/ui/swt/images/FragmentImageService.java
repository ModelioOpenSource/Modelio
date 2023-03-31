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
package org.modelio.platform.model.ui.swt.images;

import java.net.URL;
import java.util.HashMap;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.swt.graphics.Image;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.core.IGPartState.GPartStateEnum;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.data.project.GProjectPartDescriptor.GProjectPartType;
import org.modelio.platform.model.ui.plugin.CoreUi;
import org.osgi.framework.Bundle;

/**
 * Service to get an image for model fragments.
 */
@objid ("40ae8cf1-3304-4650-aee6-e0c14e7ecee9")
public class FragmentImageService {
    @objid ("d128fdd9-cc53-45ae-8fef-c61a48070718")
    private static final String IMAGES_PATH = "icons/";

    @objid ("5338ecba-7339-4eb3-8584-b36ea545c269")
    private static final HashMap<GProjectPartType, Image> upImages = new HashMap<>();

    @objid ("1652cf73-fb39-4bf7-97b1-e9a619892f8f")
    private static final HashMap<GProjectPartType, Image> downImages = new HashMap<>();

    @objid ("58e3b2c8-4d9e-4f23-822f-e77b3f382dee")
    private static final Bundle bundle = Platform.getBundle(CoreUi.PLUGIN_ID);

    @objid ("7c21f799-7ebd-43ab-9e7a-baabb82260e7")
    private static final Image UNDEFINED_FRAGMENT_IMAGE;

    @objid ("de03b58f-ea5f-41de-b1eb-d01899de132d")
    private static final Image UNDEFINED_FRAGMENT_DOWN_IMAGE;

    @objid ("7df60a46-e7d1-48e8-bc89-fb88b2a426b8")
    private static final Image FRAG_STATE_DOWN;

    @objid ("78da952e-b110-42ef-86cd-3776cd676757")
    private static final Image FRAG_STATE_MOUNTING;

    @objid ("06696843-548e-4c3e-bcee-db8bd1ff79c9")
    private static final Image FRAG_STATE_UP;

    @objid ("7cdc2bc2-51c7-4eb0-b705-79225037652b")
    private static final Image FRAG_STATE_UP_LIGHT;

    /**
     * Get the image for a model fragment, taking its {@link GPartStateEnum} into account.
     * @param fragment a model fragment.
     * @return an image or <code>null</code> for a <code>null</code> fragment.
     */
    @objid ("3fb5d705-6662-42b8-8f8a-01a6e9e32217")
    public static Image getImage(IGModelFragment fragment) {
        // null object special case
        if (fragment == null) {
            return null;
        } else {
            Image image = null;
            if (fragment.getState().getValue() == GPartStateEnum.DOWN) {
                image = FragmentImageService.downImages.get(fragment.getType());
            } else {
                image = FragmentImageService.upImages.get(fragment.getType());
            }
            if (image == null) {
                CoreUi.LOG.warning("No image found for fragment type: " + fragment.getType());
                if (fragment.getState().getValue() == GPartStateEnum.DOWN) {
                    image = FragmentImageService.UNDEFINED_FRAGMENT_IMAGE;
                } else {
                    image = FragmentImageService.UNDEFINED_FRAGMENT_DOWN_IMAGE;
                }
            }
            return image;
        }
        
    }

    @objid ("0c09451c-4624-4144-b180-38daa29ba685")
    private static Image loadImage(String imageFileName) {
        ImageDescriptor desc = null;
        Image image = null;
        
        // Get the relative file name
        final StringBuilder path = new StringBuilder(FragmentImageService.IMAGES_PATH);
        path.append(imageFileName);
        
        final IPath imagePath = new Path(path.toString());
        final URL url = FileLocator.find(FragmentImageService.bundle, imagePath, null);
        assert (url != null);
        
        if (url != null) {
            desc = ImageDescriptor.createFromURL(url);
            image = desc.createImage();
            assert (image != null);
        }
        return image;
    }

    /**
     * Get the image for {@link GPartStateEnum}.
     * @param fragmentState a fragment state.
     * @return an image or <code>null</code> for a <code>null</code> state.
     */
    @objid ("8acb7ee0-8813-43d3-afe1-35bb72db6dd3")
    public static Image getStateImage(GPartStateEnum fragmentState) {
        if (fragmentState == null) {
            return null;
        }
        
        switch (fragmentState) {
        case DOWN:
        case INSTANTIATED:
            return FragmentImageService.FRAG_STATE_DOWN;
        case MOUNTING:
            return FragmentImageService.FRAG_STATE_MOUNTING;
        case MOUNTED:
            return FragmentImageService.FRAG_STATE_UP;
        default:
            return null;
        }
        
    }

    /**
     * Get the image for a fragment descriptor.
     * @param fragment a fragment descriptor.
     * @return an image or <code>null</code> for a <code>null</code> descriptor.
     */
    @objid ("6bbbd64d-3ec5-46a6-b1b7-d86cdd90d1b7")
    public static Image getImage(GProjectPartDescriptor fragment) {
        // null object special case
        if (fragment == null) {
            return null;
        } else {
            return getImage(fragment.getType());
        }
        
    }

    /**
     * Get the image for a fragment type.
     * @param type a fragment type.
     * @return an image.
     */
    @objid ("240c3a63-d851-4229-883e-13e5d5380a50")
    public static Image getImage(GProjectPartType type) {
        Image image = FragmentImageService.upImages.get(type);
        if (image == null) {
            CoreUi.LOG.warning("No image found for fragment type: " + type);
            image = FragmentImageService.UNDEFINED_FRAGMENT_DOWN_IMAGE;
        }
        return image;
    }

static {
                        UNDEFINED_FRAGMENT_IMAGE = loadImage("undefinedfragment.png");
    
                        FragmentImageService.upImages.put(GProjectPartType.EXMLFRAGMENT, loadImage("exmlfragment.png"));
                        FragmentImageService.upImages.put(GProjectPartType.RAMC, loadImage("ramcfragment.png"));
                        FragmentImageService.upImages.put(GProjectPartType.MODULE, loadImage("mdafragment.png"));
                        FragmentImageService.upImages.put(GProjectPartType.HTTPFRAGMENT, loadImage("httpfragment.png"));
                        FragmentImageService.upImages.put(GProjectPartType.SVNFRAGMENT, loadImage("svnfragment.png"));
    
                        final IPath downOverlayPath = new Path(FragmentImageService.IMAGES_PATH + "down_indicator.png");
                        final URL downOverlayUrl = FileLocator.find(FragmentImageService.bundle, downOverlayPath, null);
                        assert (downOverlayUrl != null) : "missing file icons/down_indicator.png";
    
                        final ImageDescriptor downOverlay = ImageDescriptor.createFromURL(downOverlayUrl);
    
                        UNDEFINED_FRAGMENT_DOWN_IMAGE = (new DecorationOverlayIcon(FragmentImageService.UNDEFINED_FRAGMENT_IMAGE, downOverlay,
                                IDecoration.BOTTOM_RIGHT)).createImage();
    
                        FragmentImageService.downImages.put(GProjectPartType.EXMLFRAGMENT, (new DecorationOverlayIcon(FragmentImageService.upImages.get(GProjectPartType.EXMLFRAGMENT), downOverlay,
                                IDecoration.BOTTOM_RIGHT)).createImage());
    
                        FragmentImageService.downImages.put(GProjectPartType.RAMC, (new DecorationOverlayIcon(FragmentImageService.upImages.get(GProjectPartType.RAMC), downOverlay,
                                IDecoration.BOTTOM_RIGHT)).createImage());
    
                        FragmentImageService.downImages.put(GProjectPartType.MODULE, (new DecorationOverlayIcon(FragmentImageService.upImages.get(GProjectPartType.MODULE), downOverlay,
                                IDecoration.BOTTOM_RIGHT)).createImage());
    
                        FragmentImageService.downImages.put(GProjectPartType.HTTPFRAGMENT, (new DecorationOverlayIcon(FragmentImageService.upImages.get(GProjectPartType.HTTPFRAGMENT), downOverlay,
                                IDecoration.BOTTOM_RIGHT)).createImage());
    
                        FragmentImageService.downImages.put(GProjectPartType.SVNFRAGMENT, (new DecorationOverlayIcon(FragmentImageService.upImages.get(GProjectPartType.SVNFRAGMENT), downOverlay,
                                IDecoration.BOTTOM_RIGHT)).createImage());
    
                        // fragment state images
                        FRAG_STATE_DOWN = loadImage("fragment_state_down.png");
                        FRAG_STATE_MOUNTING = loadImage("fragment_state_mounting.png");
                        FRAG_STATE_UP = loadImage("fragment_state_up.png");
                        FRAG_STATE_UP_LIGHT = loadImage("fragment_state_up_light.png");
                    }
    
}
