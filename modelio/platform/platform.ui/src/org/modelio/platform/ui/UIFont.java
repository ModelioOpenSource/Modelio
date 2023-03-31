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
package org.modelio.platform.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

/**
 * The UIFont fonts are pre-allocated resources for Modelio that can be used without having to deal with their lifecycle (they are available once Modelio starts and only freed when Modelio stops).<br/>
 * 
 * <b>Modelio developers must use these fonts wherever it is possible prior to defining 'customs' by {@link CoreFontRegistry#getModifiedFont(Font, int, float)} .</b>
 * <p>
 * The font face is the <u>one of the system default font</u>. The NORMAL size is the size of the system default font. All other scale factors are relative to this NORMAL font size which scale is considered to be 1.0.
 * </p>
 * 
 * <p>
 * The naming indicates the properties of the font:
 * <ul>
 * <li>XXSMALL, XSMALL, SMALL, NORMAL, LARGE, XLARGE, XXLARGE is relative to the font scale</li>
 * <li>the 'B' suffix indicates a <b>bold</b> font.</li>
 * <li>the 'I' suffix indicates an <i>italic</i> font.</li>
 * <li>the 'BI' indicates a <b><i>bold italic</i></b> font.</li>
 * </ul>
 * </p>
 */
@objid ("c1ada416-5e66-11e2-a8be-00137282c51b")
public interface UIFont {
    @objid ("0906e369-5e67-11e2-a8be-00137282c51b")
    public static final float LARGE_SIZE = 1.20f;

    @objid ("81a7fca6-73ce-4b0b-b395-ec01ef596405")
    public static final float NORMAL_SIZE = 1.0f;

    @objid ("0904810f-5e67-11e2-a8be-00137282c51b")
    public static final float SMALL_SIZE = 0.9f;

    @objid ("0906e36b-5e67-11e2-a8be-00137282c51b")
    public static final float XLARGE_SIZE = 1.4f;

    @objid ("8d1d075e-61f6-4115-9a2c-fb2babfeed0a")
    public static final float XXLARGE_SIZE = 1.8f;

    @objid ("b064e3e1-131d-4425-87f0-2812ade3e3ee")
    public static final float XSMALL_SIZE = 0.8f;

    @objid ("bfc629b9-503d-41ac-a792-4330c300a8c1")
    public static final float XXSMALL_SIZE = 0.7f;

    @objid ("53e42258-4ba1-4864-954c-c24d78cf9c2b")
    public static final FontBuilder BASE_FONT_BUILDER = new FontBuilder()
                .withAllocator(JFaceResources.getResources())
                .from(Display.getCurrent().getSystemFont());

    @objid ("9e8a78dd-5274-4ce6-a8f1-67976dba1a82")
    public static final Font NORMAL = BASE_FONT_BUILDER.build();

    @objid ("f5749718-664a-4d13-b8a4-783ff572b6b2")
    public static final Font NORMALB = BASE_FONT_BUILDER.copy().addStyle(SWT.BOLD).build();

    @objid ("214c3dfe-8f4c-424e-bda2-8177084e2959")
    public static final Font NORMALI = BASE_FONT_BUILDER.copy().addStyle(SWT.ITALIC).build();

    @objid ("bcaa7cbb-3266-4f45-9507-09138db6f3c3")
    public static final Font NORMALBI = BASE_FONT_BUILDER.copy().addStyle(SWT.ITALIC|SWT.BOLD).build();

    @objid ("549eb1a8-bcb2-42df-bbc4-d71a8eea1446")
    public static final Font SMALL = BASE_FONT_BUILDER.copy().scale(SMALL_SIZE).build();

    @objid ("5869fbbe-c17f-401f-82b0-9a9399b67645")
    public static final Font XSMALL = BASE_FONT_BUILDER.copy().scale(XSMALL_SIZE).build();

    @objid ("8db71bab-b5ec-4d51-8178-f36049fa3aac")
    public static final Font XXSMALL = BASE_FONT_BUILDER.copy().scale(XXSMALL_SIZE).build();

    @objid ("04d62f4b-cf20-45e1-9875-67bb3e17fb6a")
    public static final Font LARGE = BASE_FONT_BUILDER.copy().scale(LARGE_SIZE).build();

    @objid ("d4bba029-2eba-46ca-b605-acc19f57fb82")
    public static final Font XLARGE = BASE_FONT_BUILDER.copy().scale(XLARGE_SIZE).build();

    @objid ("327b5e7d-8be3-42c8-bcd1-cc6479526d6f")
    public static final Font XXLARGE = BASE_FONT_BUILDER.copy().scale(XXLARGE_SIZE).build();

    @objid ("af3c08f6-851e-48fc-947c-c73c356c400e")
    public static final Font SMALLB = BASE_FONT_BUILDER.copy().scale(SMALL_SIZE).addStyle(SWT.BOLD).build();

    @objid ("a6184795-05f9-49cf-b998-c268bb763f05")
    public static final Font XSMALLB = BASE_FONT_BUILDER.copy().scale(XSMALL_SIZE).addStyle(SWT.BOLD).build();

    @objid ("e472d608-bc7f-4518-8894-cbd746f3e6fd")
    public static final Font XXSMALLB = BASE_FONT_BUILDER.copy().scale(XXSMALL_SIZE).addStyle(SWT.BOLD).build();

    @objid ("1f16e1ea-7df2-4c5a-8810-73bb98d43f02")
    public static final Font LARGEB = BASE_FONT_BUILDER.copy().scale(LARGE_SIZE).addStyle(SWT.BOLD).build();

    @objid ("ac9a4454-d573-49e6-abf0-214f39a34445")
    public static final Font XLARGEB = BASE_FONT_BUILDER.copy().scale(XLARGE_SIZE).addStyle(SWT.BOLD).build();

    @objid ("96804d05-8c85-4532-92a4-661b4d21b0dd")
    public static final Font XXLARGEB = BASE_FONT_BUILDER.copy().scale(XXLARGE_SIZE).addStyle(SWT.BOLD).build();

    @objid ("04135b01-5541-4a34-b4d3-4cccc2e6541e")
    public static final Font SMALLI = BASE_FONT_BUILDER.copy().scale(SMALL_SIZE).addStyle(SWT.ITALIC).build();

    @objid ("07b9c158-6e47-4097-bbf9-ff0ff3fe4971")
    public static final Font XSMALLI = BASE_FONT_BUILDER.copy().scale(XSMALL_SIZE).addStyle(SWT.ITALIC).build();

    @objid ("e965bc52-a041-4cc1-873a-dd30bad464c9")
    public static final Font XXSMALLI = BASE_FONT_BUILDER.copy().scale(XXSMALL_SIZE).addStyle(SWT.ITALIC).build();

    @objid ("5221789f-7320-4d46-8f2f-b460b1b617b3")
    public static final Font LARGEI = BASE_FONT_BUILDER.copy().scale(LARGE_SIZE).addStyle(SWT.ITALIC).build();

    @objid ("e676a720-7689-43ce-99c4-c58da3dff2d6")
    public static final Font XLARGEI = BASE_FONT_BUILDER.copy().scale(XLARGE_SIZE).addStyle(SWT.ITALIC).build();

    @objid ("3069ba91-fd7c-43d1-973c-3430525124fe")
    public static final Font XXLARGEI = BASE_FONT_BUILDER.copy().scale(XXLARGE_SIZE).addStyle(SWT.ITALIC).build();

    @objid ("1a7edf29-b6b0-49ae-bb93-85e8272634f0")
    public static final Font SMALLBI = BASE_FONT_BUILDER.copy().scale(SMALL_SIZE).addStyle(SWT.BOLD | SWT.ITALIC).build();

    @objid ("8d1f185f-2bc3-4f8d-be24-4b513a20f7d9")
    public static final Font XSMALLBI = BASE_FONT_BUILDER.copy().scale(XSMALL_SIZE).addStyle(SWT.BOLD | SWT.ITALIC).build();

    @objid ("6d10cea4-92fd-4052-b25e-59603d646f4b")
    public static final Font XXSMALLBI = BASE_FONT_BUILDER.copy().scale(XXSMALL_SIZE).addStyle(SWT.BOLD | SWT.ITALIC).build();

    @objid ("75d5f56d-76b8-4e46-8ea2-709462062f47")
    public static final Font LARGEBI = BASE_FONT_BUILDER.copy().scale(LARGE_SIZE).addStyle(SWT.BOLD | SWT.ITALIC).build();

    @objid ("3c61482d-232f-46c4-ad7d-5930ad4d6fa6")
    public static final Font XLARGEBI = BASE_FONT_BUILDER.copy().scale(XLARGE_SIZE).addStyle(SWT.BOLD | SWT.ITALIC).build();

    @objid ("8e36949e-743e-4e36-a16c-8303ad32cee0")
    public static final Font XXLARGEBI = BASE_FONT_BUILDER.copy().scale(XXLARGE_SIZE).addStyle(SWT.BOLD | SWT.ITALIC).build();
}

