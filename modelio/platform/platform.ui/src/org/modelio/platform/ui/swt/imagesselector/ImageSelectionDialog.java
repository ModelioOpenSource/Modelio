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
package org.modelio.platform.ui.swt.imagesselector;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryGroupRenderer;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryItemRenderer;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.platform.ui.FontBuilder;
import org.modelio.platform.ui.UIImages;
import org.modelio.platform.ui.UIThreadRunner;
import org.modelio.platform.ui.dialog.ModelioDialog;
import org.modelio.platform.ui.plugin.UI;

@objid ("c277be9d-6de0-49a0-944d-45631156e726")
public class ImageSelectionDialog extends ModelioDialog {
    @objid ("8ee4a395-c7e0-466a-a178-6d80c74bbe87")
    private Gallery gallery;

    @objid ("7c35916b-863c-4b1c-b615-8030e445cf8d")
    private final ImageLibrary imagesLibrary;

    @objid ("1f280ec3-af26-4b35-b787-963058d32c2e")
    private LocalResourceManager rm;

    @objid ("3e10e7df-08ee-462d-a16a-779aad918d49")
    private Text searchText;

    @objid ("aa3ba8a9-8e76-4cb0-950d-49a87e59db2f")
    private static ImageRegistry thumbnailsCache = new ImageRegistry();

    @objid ("d88f8aaa-f485-4a67-9328-573e761ddb1b")
    private Font galleryFont;

    @objid ("9c3cd5ee-4c23-4cf6-86ec-2052646b42dd")
    public  ImageSelectionDialog(Shell parentShell, final ImageLibrary imagesLibrary) {
        super(parentShell);
        this.imagesLibrary = imagesLibrary;
        
    }

    @objid ("2507e83f-58bf-442f-982c-bb38149d668c")
    @Override
    protected void addButtonsInButtonBar(Composite parent) {
        addDefaultButtons(parent);
    }

    @objid ("b575335a-5c76-4cb0-9ea7-3656e212da38")
    @Override
    protected Control createContentArea(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(2, false));
        
        this.searchText = new Text(composite, SWT.BORDER);
        this.searchText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        this.searchText.addTraverseListener((TraverseEvent e) -> {
            if (e.detail == SWT.TRAVERSE_RETURN) {
                e.doit = false;
            } else if (e.detail == SWT.TRAVERSE_ESCAPE) {
                e.doit = false;
            }
        });
        
        this.searchText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                onFilterItems(ImageSelectionDialog.this.searchText.getText());
            }
        });
        
        Label searchImage = new Label(composite, SWT.NONE);
        searchImage.setImage(UIImages.SEARCH);
        
        this.rm = new LocalResourceManager(JFaceResources.getResources());
        this.gallery = new Gallery(composite, SWT.V_SCROLL | SWT.VIRTUAL);
        
        // Renderers
        DefaultGalleryGroupRenderer gr = new DefaultGalleryGroupRenderer();
        gr.setMinMargin(2);
        gr.setAutoMargin(false);
        gr.setAnimation(false);
        this.gallery.setGroupRenderer(gr);
        
        DefaultGalleryItemRenderer ir = new DefaultGalleryItemRenderer();
        ir.setShowLabels(true);
        
        this.galleryFont = new FontBuilder().from(this.gallery.getFont()).scale((float) 0.8).withAllocator(this.rm).build();
        ir.setFont(this.galleryFont);
        this.gallery.setFont(this.galleryFont);
        this.gallery.setItemRenderer(ir);
        
        show(null);
        
        this.gallery.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
        
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        return composite;
    }

    @objid ("b5222349-853d-427d-84aa-818732f2a3a6")
    @Override
    protected void init() {
        setTitle(UI.I18N.getString("ImageSelectionDialog.title"));
        setMessage(UI.I18N.getMessage("ImageSelectionDialog.subtitle", this.imagesLibrary.getName()));
        
    }

    @objid ("871c2f38-ad40-4094-b004-0060240392da")
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(UI.I18N.getString("ImageSelectionDialog.title"));
        
    }

    @objid ("a804e14b-cf68-4c79-b879-c2e95ba3d2a4")
    public URL getSelectedItem() {
        if (this.gallery.getSelection().length == 1) {
            return this.imagesLibrary.getImage((String) this.gallery.getSelection()[0].getData());
        }
        return null;
    }

    @objid ("c42c37e6-0c6a-493a-b26c-2dc450e0ee0e")
    public void onFilterItems(String filter) {
        if (!filter.equals(this.gallery.getData("filter"))) {
            this.gallery.setData("filter", filter);
        
            this.gallery.removeAll();
            show(filter);
        
            this.gallery.redraw();
        }
        
    }

    @objid ("64294844-4392-46fc-82bf-0d73121e8074")
    private void show(String filter) {
        List<GalleryItem> itemList = new ArrayList<>();
        UIThreadRunner.asynExec(this.gallery, () -> {
            for (String category : this.imagesLibrary.getCategories()) {
                // One Gallery group by category
                GalleryItem group = new GalleryItem(this.gallery, SWT.NONE);
                group.setText(category); // $NON-NLS-1$
                group.setExpanded(true);
        
                for (String imageId : this.imagesLibrary.getImages(category)) {
                    String label = this.imagesLibrary.getLabel(imageId);
                    if(filter == null || "".equals(filter)  || (label.toLowerCase() != null && label.contains(filter.toLowerCase()))) {
                        // One Gallery item per image
                        GalleryItem item = new GalleryItem(group, SWT.NONE);
                        URL url = this.imagesLibrary.getImageThumbnail(imageId);
        
                        Image thumbnailImage = this.thumbnailsCache.get(url.toString());
                        if (thumbnailImage == null) {
                            thumbnailImage = ImageDescriptor.createFromURL(url).createImage();
                            this.thumbnailsCache.put(url.toString(), thumbnailImage);
                        }
                        item.setImage(thumbnailImage);
                        item.setText(label); // $NON-NLS-1$
                        item.setData(imageId);
        
        
                    }
        
                }
                itemList.add(group);
        
            }
            this.gallery.update();
        });
        
    }

    @objid ("a13f091a-e96a-480b-803c-0eff5dbb9835")
    @Override
    public boolean close() {
        // this.thumbnailsCache.dispose();
        // this.thumbnailsCache = null;
        return super.close();
    }

    @objid ("88f26794-f6bc-4a34-98c7-4d1f77535dc1")
    @Override
    protected Point getInitialSize() {
        return new Point(550, 500);
    }

}
