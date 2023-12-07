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
import java.util.function.Function;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TreeItem;
import org.modelio.platform.model.ui.plugin.CoreUi;
import org.modelio.vcore.smkernel.DeadObjectException;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.StatusState;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MStatus;

/**
 * {@link StyledCellLabelProvider Styled label provider} to use to draw {@link SmObjectImpl} icons with their CMS and audit status.
 */
@objid ("00872c2e-b6e9-100f-85b1-001ec947cd2a")
public class ElementDecoratedStyledLabelProvider extends StyledCellLabelProvider {
    @objid ("cd68c80d-5a72-4854-8557-96f72cb8a162")
    private static final int ICON_WIDTH = 24;

    @objid ("5b37aa19-5ef6-4281-af35-69d17d5b2d49")
    private static final int ICON_HEIGHT = 24;

    @objid ("376f0b6b-e6c9-4a9c-b5b9-5412a04d36ff")
    private static final int DECO_WIDTH = 8;

    @objid ("8fbdf0a7-2d5a-4284-ac14-76c7b94c97e9")
    private static final int DECO_HEIGHT = 8;

    @objid ("0086f33a-b6e9-100f-85b1-001ec947cd2a")
    private boolean showCms;

    @objid ("0086f420-b6e9-100f-85b1-001ec947cd2a")
    private boolean showAudit;

    @objid ("0086fe3e-b6e9-100f-85b1-001ec947cd2a")
    private static final String IMAGES_PATH = "images/";

    /**
     * Used when the element to display is not directly an MObject
     */
    @objid ("0afe3c8a-be7e-4ffa-a654-a992059daa49")
    private Function<Object, MObject> mObjectConverter;

    @objid ("009482fc-f04f-100f-85b1-001ec947cd2a")
    private final IModelioElementLabelProvider baseProvider;

    @objid ("a979a3d0-e6c0-4c89-9e7a-d2c307a8ea49")
    private static final Image reference = ElementDecoratedStyledLabelProvider.loadImage("refoverlay8x8.png");

    @objid ("d6b5d719-73f4-42b9-a0b2-4f727db0aed6")
    private static final Image cmsReadWrite = ElementDecoratedStyledLabelProvider.loadImage("cms_readwrite8x8.png");

    @objid ("2ae5ea08-17d2-4b76-8c98-1e914a2641df")
    private static final Image cmsReadOnly = ElementDecoratedStyledLabelProvider.loadImage("cms_readonly8x8.png");

    @objid ("546e75b4-9842-4548-81b2-6be3e14b6459")
    private static final Image cmsToAdd = ElementDecoratedStyledLabelProvider.loadImage("cms_toadd8x8.png");

    @objid ("0c7d1c0b-0a00-4cad-a928-efede1f8b96b")
    private static final Image cmsConflict = ElementDecoratedStyledLabelProvider.loadImage("cms_conflict8x8.png");

    @objid ("526953da-2a03-42a0-8254-b208f8263cec")
    private static final Image cmsModified = ElementDecoratedStyledLabelProvider.loadImage("cms_modified8x8.png");

    @objid ("b90d1082-6099-4939-891b-05188d5eaa6f")
    private static final Image placeholder = ElementDecoratedStyledLabelProvider.loadImage("placeholder36x24.png");

    @objid ("013a975a-7d13-4e39-8f8f-c47daee5d1f5")
    private static final Image auditError = ElementDecoratedStyledLabelProvider.loadImage("auditerror8x8.png");

    @objid ("5aca4657-22b2-48c1-9648-eeb567eafbf7")
    private static final Image auditWarning = ElementDecoratedStyledLabelProvider.loadImage("auditwarning8x8.png");

    @objid ("aeb06f43-35ad-4625-88bd-56a074a8f0f3")
    private static final Image auditAdvice = ElementDecoratedStyledLabelProvider.loadImage("auditadvice8x8.png");

    @objid ("3b106793-d28c-4511-8af2-0e8842bccb6d")
    private static final Image cmsReadWriteNoLock = ElementDecoratedStyledLabelProvider.loadImage("cms_readwrite.nolock8x8.png");

    @objid ("16af6b27-274c-4872-9f73-542e3a05448b")
    private static final Image cmsModifiedNoLock = ElementDecoratedStyledLabelProvider.loadImage("cms_modified.nolock8x8.png");

    @objid ("3c30b31e-6852-4dbb-a569-b1384f9dcc70")
    private static final Image userReadOnly = ElementDecoratedStyledLabelProvider.loadImage("user_readonly8x8.png");

    @objid ("09200b06-2bf8-42d5-8c88-de8a005dd5bc")
    private static final Image cmsToDelete = ElementDecoratedStyledLabelProvider.loadImage("cms_todelete8x8.png");

    @objid ("92698eb5-27cd-443a-9b8c-e26613ab0186")
    private static final Image invalidDeleted = ElementDecoratedStyledLabelProvider.loadImage("invalid_deleted8x8.png");

    @objid ("9a74f864-7076-4b27-9b6c-20c77735a679")
    private static final Image invalidBeingDeleted = ElementDecoratedStyledLabelProvider.loadImage("invalid_beingdeleted8x8.png");

    @objid ("138de558-8057-4f3f-8728-f7f3c1b36a70")
    private static final Image invalidDead = ElementDecoratedStyledLabelProvider.loadImage("invalid_dead8x8.png");

    // ------------------------------------------------------------------------------------
    /**
     * Initialize a new styled label provider.
     * @param baseProvider the provider used to compute the label.
     */
    @objid ("0086f894-b6e9-100f-85b1-001ec947cd2a")
    public  ElementDecoratedStyledLabelProvider(IModelioElementLabelProvider baseProvider) {
        this(baseProvider, true, true);
    }

    /**
     * Initialize a new styled label provider.
     * @param baseProvider the provider used to compute the label.
     * @param showCms display the CMS state
     * @param showAudit display the audit state
     */
    @objid ("0086f948-b6e9-100f-85b1-001ec947cd2a")
    public  ElementDecoratedStyledLabelProvider(IModelioElementLabelProvider baseProvider, boolean showCms, boolean showAudit) {
        this.baseProvider = baseProvider;
        
        configure(showCms, showAudit);
        
    }

    /**
     * Reconfigure this styled provider.
     * @param newShowCms <code>true</code> to display the CMS state.
     * @param newShowAudit <code>true</code> to display the audit state.
     */
    @objid ("0086f9e8-b6e9-100f-85b1-001ec947cd2a")
    public final void configure(boolean newShowCms, boolean newShowAudit) {
        this.showCms = newShowCms;
        this.showAudit = newShowAudit;
        
    }

    /**
     * Set the converter to use to convert a tree content provider object to a MObject.
     * <p>
     * Used when the displayed elements are not directly MObjects.
     * <p>
     * This converter is used to get the element status and audit status.
     * It is <b>not</b> used when delegating to the {@link #baseProvider} .
     * @param mObjectConverter a conversion function
     * @return this instance to chain calls
     */
    @objid ("1c4faf81-2d30-48b8-b85b-790c0d889382")
    public ElementDecoratedStyledLabelProvider setMObjectConverter(Function<Object, MObject> mObjectConverter) {
        this.mObjectConverter = mObjectConverter;
        return this;
    }

    @objid ("e5167dab-d42a-405e-8283-ea29e5a30414")
    protected MObject convertToMObject(Object object) {
        if (this.mObjectConverter != null)
            return this.mObjectConverter.apply(object);
        
        if (object instanceof MObject)
            return (MObject) object;
        return null;
    }

    /**
     * Paint the cell.
     * 
     * There are four decorations which are DECO_WIDTH x DECO_HEIGHT pixels images, laid around the main icon image.<br/>
     * The main icon is a ICON_WIDTH x ICON_WIDTH pixels image.
     * 
     * The decoration are laid out as described here:
     * <ol>
     * <li>the 'CMS' indicator at north-west position</li>
     * <li>the 'reference' indicator at south-west position</li>
     * <li>the 'audit' indicator at north-east position</li>
     * <li>the 'invalid object' indicator at south-easy position</li>
     * </ol>
     */
    @objid ("0086fb28-b6e9-100f-85b1-001ec947cd2a")
    @Override
    protected final void paint(final Event event, final Object obj) {
        super.paint(event, obj);
        Rectangle cellImageBounds = getCellImageBounds(event);
        
        // Hope this does not happen : event.x is not the same on Windows or Linux since Eclipse 4.11 .
        int curX = (cellImageBounds != null) ? cellImageBounds.x : event.x;
        final int curY = event.y;
        
        // Draw reference decorator
        if (this.baseProvider.showAsReference(obj)) {
            final Image refDecoration = getReferenceDecoration(obj);
            if (refDecoration != null) {
                event.gc.drawImage(refDecoration, 0, 0, refDecoration.getBounds().width, refDecoration.getBounds().height, curX, curY + event.height - DECO_HEIGHT - 1, DECO_WIDTH, DECO_HEIGHT);
            }
        }
        
        MObject modelObject = convertToMObject(obj);
        
        // Draw CMS
        if (this.showCms && (modelObject != null)) {
            final Image cmsDecoration = getCmsDecoration(modelObject);
            if (cmsDecoration != null) {
                event.gc.drawImage(cmsDecoration, 0, 0, cmsDecoration.getBounds().width, cmsDecoration.getBounds().height, curX, curY + 1, DECO_WIDTH, DECO_HEIGHT);
            }
            curX += DECO_WIDTH + 1;
        }
        
        // Draw icon
        final Image icon = this.baseProvider.getImage(obj);
        if (icon != null && !icon.isDisposed()) {
            event.gc.drawImage(icon, 0, 0, icon.getImageData().width, icon.getImageData().height, curX, curY, ICON_WIDTH, ICON_HEIGHT);
            curX += ICON_WIDTH;
        }
        
        // Draw audit
        if (this.showAudit && (modelObject instanceof SmObjectImpl)) {
            Image auditDecoration = getAuditDecoration((SmObjectImpl) modelObject);
            if (auditDecoration != null) {
                event.gc.drawImage(auditDecoration, 0, 0, auditDecoration.getBounds().width, auditDecoration.getBounds().height, curX - 1, curY + 1, DECO_WIDTH, DECO_HEIGHT);
            }
        }
        
        // Draw invalid objects
        if (modelObject instanceof SmObjectImpl) {
            try {
                final ISmObjectData data = ((SmObjectImpl) modelObject).getData();
                if (data.hasAllStatus(IRStatus.DELETED) == StatusState.TRUE) {
                    event.gc.drawImage(ElementDecoratedStyledLabelProvider.invalidDeleted, 0, 0, ElementDecoratedStyledLabelProvider.invalidDeleted.getBounds().width, ElementDecoratedStyledLabelProvider.invalidDeleted.getBounds().height, curX - 1,
                            curY + event.height - DECO_HEIGHT - 1, DECO_WIDTH, DECO_HEIGHT);
        
                    event.gc.setForeground(event.display.getSystemColor(SWT.COLOR_DARK_RED));
                    event.gc.drawString("deleted", curX + DECO_WIDTH, curY + event.height/2 , true);
                    }
                if (data.hasAllStatus(IRStatus.BEINGDELETED) == StatusState.TRUE) {
                    event.gc.drawImage(ElementDecoratedStyledLabelProvider.invalidBeingDeleted, 0, 0, ElementDecoratedStyledLabelProvider.invalidBeingDeleted.getBounds().width, ElementDecoratedStyledLabelProvider.invalidBeingDeleted.getBounds().height,
                            curX - 1, curY + event.height - DECO_HEIGHT - 1, DECO_WIDTH, DECO_HEIGHT);
        
                    event.gc.setForeground(event.display.getSystemColor(SWT.COLOR_DARK_RED));
                    event.gc.drawString("being deleted", curX + DECO_WIDTH, curY + event.height/2 , true);
                }
        
        
            } catch (@SuppressWarnings ("unused") DeadObjectException e) {
                // Draw dead elements with a specific style
                event.gc.setAlpha(200);
                event.gc.drawImage(ElementDecoratedStyledLabelProvider.invalidDead, 0, 0, ElementDecoratedStyledLabelProvider.invalidDead.getBounds().width, ElementDecoratedStyledLabelProvider.invalidDead.getBounds().height, curX - 1,
                        curY + event.height - DECO_HEIGHT - 1, DECO_WIDTH, DECO_HEIGHT);
                event.gc.setForeground(event.display.getSystemColor(SWT.COLOR_DARK_RED));
                event.gc.drawString("dead", curX + DECO_WIDTH, curY + event.height/2 , true);
            }
        }
        
    }

    @objid ("0086ff4c-b6e9-100f-85b1-001ec947cd2a")
    private static final Image loadImage(String imageFileName) {
        ImageDescriptor desc = null;
        Image image = null;
        
        // Get the relative file name
        final StringBuilder path = new StringBuilder(ElementDecoratedStyledLabelProvider.IMAGES_PATH);
        path.append(imageFileName);
        
        final IPath imagePath = new Path(path.toString());
        final URL url = FileLocator.find(CoreUi.getContext().getBundle(), imagePath, null);
        assert (url != null);
        
        if (url != null) {
            desc = ImageDescriptor.createFromURL(url);
            image = desc.createImage();
            assert (image != null);
        }
        return image;
    }

    @objid ("00051cca-d823-100f-85b1-001ec947cd2a")
    @Override
    public final void update(ViewerCell cell) {
        final Object obj = cell.getElement();
        
        if (obj != null) {
            cell.setImage(ElementDecoratedStyledLabelProvider.placeholder);
            final StyledString s = this.baseProvider.getStyledText(obj);
            cell.setText(s.getString());
            cell.setStyleRanges(s.getStyleRanges());
        }
        
        super.update(cell);
        
    }

    /**
     * Get the decoration image to be used to reflect the CMS state of the given element.
     * @param obj the element to be decorated.
     */
    @objid ("25104a14-4bb3-4ef2-b178-ab7f4c287885")
    protected Image getCmsDecoration(MObject obj) {
        try {
            final MStatus status = obj.getStatus();
        
            if (status.isCmsManaged()) {
                if (status.isCmsConflict()) {
                    return ElementDecoratedStyledLabelProvider.cmsConflict;
                } else if (status.isCmsToDelete()) {
                    return ElementDecoratedStyledLabelProvider.cmsToDelete;
                }
        
                if (status.isCmsModified() || status.isDirty()) {
                    // Display the object as CMS modified if either modified in memory or on disk working copy.
                    if (status.isLockingNeeded()) {
                        return ElementDecoratedStyledLabelProvider.cmsModified;
                    } else {
                        return ElementDecoratedStyledLabelProvider.cmsModifiedNoLock;
                    }
                }
        
                if (!status.isUserWrite()) {
                    return ElementDecoratedStyledLabelProvider.userReadOnly;
                } else if (status.isCmsReadOnly()) {
                    return ElementDecoratedStyledLabelProvider.cmsReadOnly;
                } else if (status.isLockingNeeded()) {
                    return ElementDecoratedStyledLabelProvider.cmsReadWrite;
                } else {
                    return ElementDecoratedStyledLabelProvider.cmsReadWriteNoLock;
                }
            } else if (status.isCmsToAdd()) {
                return ElementDecoratedStyledLabelProvider.cmsToAdd;
            }
        } catch (@SuppressWarnings ("unused") DeadObjectException e) {
            // Do not add cms flag for dead elements
            return null;
        }
        return null;
    }

    @objid ("66bce7a8-1d9a-4264-9d78-7a0261784a26")
    @Override
    public final String getToolTipText(Object object) {
        return this.baseProvider.getToolTipText(object);
    }

    /**
     * Get the decoration image to be used for elements that are shown as references.
     * @param obj the element to be decorated.
     */
    @objid ("2571d843-9b20-43f9-842b-9f80d5fec1bd")
    protected Image getReferenceDecoration(Object obj) {
        return ElementDecoratedStyledLabelProvider.reference;
    }

    @objid ("b0b4c062-dba9-4424-85f0-e8188615029e")
    private Image getAuditDecoration(SmObjectImpl mObj) {
        try {
        
            final ISmObjectData data = mObj.getData();
            int audit = 0;
            if (data.hasAllStatus(IRStatus.AUDIT1) == StatusState.TRUE) {
                audit += 1;
            }
            if (data.hasAllStatus(IRStatus.AUDIT2) == StatusState.TRUE) {
                audit += 2;
            }
        
            switch (audit) {
            case 1:
                return ElementDecoratedStyledLabelProvider.auditAdvice;
            case 2:
                return ElementDecoratedStyledLabelProvider.auditWarning;
            case 3:
                return ElementDecoratedStyledLabelProvider.auditError;
            case 0:
            default:
                return null;
            } // end switch
        } catch (@SuppressWarnings ("unused") DeadObjectException e) {
            // Do not add audit flag for dead elements
            return null;
        }
        
    }

    @objid ("e00c44e8-345a-44ea-8869-4d999c5166d8")
    private Rectangle getCellImageBounds(final Event event) {
        Rectangle cellImageBounds = null;
        if (event.item instanceof TableItem) {
            TableItem tableItem = (TableItem) event.item;
            // check the item has an image if you don't want an async JVM crash
            if (tableItem.getImage(event.index) != null) {
                cellImageBounds = tableItem.getImageBounds(event.index);
            }
        } else {
            TreeItem treeItem = (TreeItem) event.item;
            // check the item has an image if you don't want an async JVM crash
            if (treeItem.getImage(event.index) != null) {
                cellImageBounds = treeItem.getImageBounds(event.index);
            }
        }
        return cellImageBounds;
    }

}
