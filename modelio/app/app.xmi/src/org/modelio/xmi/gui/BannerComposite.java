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

package org.modelio.xmi.gui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.modelio.xmi.util.ResourceLoader;

/**
 * This classe defines the banner (as a SWT composite) of XMI dialogs
 * @author ebrosse
 */
@objid ("9c5477ec-e6ed-4ca7-8692-3f46ee19ee6d")
public class BannerComposite extends Composite {
    @objid ("f473a0cd-ef40-47a4-8819-8f4d16618f7c")
    private final String bandeauName = "bandeau176x80.png";

    @objid ("59a21a00-61a8-4e6d-a05b-c9f9ab517fca")
    private final int minHeight = 80;

    @objid ("4d2f3f36-3832-41c4-be0e-7daa8ecc2dc9")
    private Label label = null;

    /**
     * The constructor of banner itself
     * 
     * @param parent : the SWT composite owner of the banner
     * @param style : SWT style of the banner
     * @param title : the title of the banner
     * @param description : the description associated to the banner
     */
    @objid ("fba1a4c6-83ad-4aae-b0b2-d529dde3ca21")
    public BannerComposite(final Composite parent, final int style, final String title, final String description) {
        super(parent, style);
        Image image = ResourceLoader.getInstance().getImage(this.bandeauName);
        
        setLayout(new FormLayout());
        this.label = new Label(this, SWT.NONE);
        this.label.setImage(image);
        
        final FormData fd_label = new FormData();
        fd_label.left = new FormAttachment(0, 0);
        fd_label.top = new FormAttachment(0, 0);
        this.label.setLayoutData(fd_label);
        
        StyledText text = new StyledText(this, SWT.NONE);
        text.setEnabled(false);
        String theTitle = "\n" + title + "\n";
        String htmlText = theTitle + description;
        text.setText(htmlText);
        
        StyleRange style1 = new StyleRange();
        style1.start = 0;
        style1.length = theTitle.length();
        style1.fontStyle = SWT.BOLD;
        style1.foreground = new Color(null, 96, 171, 171);
        text.setStyleRange(style1);
        
        StyleRange style2 = new StyleRange();
        style2.start = theTitle.length();
        style2.length = description.length();
        style2.fontStyle = SWT.ITALIC;
        style2.foreground = new Color(null, 160, 160, 160);
        text.setStyleRange(style2);
        
        final FormData fd_text = new FormData();
        fd_text.left = new FormAttachment(this.label, 0);
        fd_text.top = new FormAttachment(0, 0);
        fd_text.right = new FormAttachment(100, 0);
        fd_text.bottom = new FormAttachment(0, this.minHeight);
        fd_text.width = 100;
        text.setLayoutData(fd_text);
        
        
        this.addDisposeListener(new DisposeListener() {
            @Override
            public void widgetDisposed(DisposeEvent e) {
                if (((BannerComposite.this.label != null))
                    && (BannerComposite.this.label.getImage() != null)) {
                    BannerComposite.this.label.getImage().dispose();
                }
            }
        });
    }

    @objid ("90bb77af-3592-4ede-b020-961b7f67f0f1")
    public int getMinHeight() {
        return this.minHeight;
    }

}
