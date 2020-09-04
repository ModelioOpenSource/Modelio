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

package org.modelio.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * The UIFont contains defines the normalized scale factors to be used for fonts in Modelio General GUI<br>
 * 
 * 
 * All scale factors are relative to the system default font size which scale is considered to be 1.0.
 * 
 * To get a resized font use @link {@link CoreFontRegistry#getModifiedFont(Font, int, float)}.
 */
@objid ("c1ada416-5e66-11e2-a8be-00137282c51b")
public interface UIFont {
    @objid ("0906e369-5e67-11e2-a8be-00137282c51b")
    public static final float LARGE_SIZE = 1.40f;

    @objid ("81a7fca6-73ce-4b0b-b395-ec01ef596405")
    public static final float NORMAL_SIZE = 1.0f;

    @objid ("0904810f-5e67-11e2-a8be-00137282c51b")
    public static final float SMALL_SIZE = 0.9f;

    @objid ("0906e36b-5e67-11e2-a8be-00137282c51b")
    public static final float XLARGE_SIZE = 1.8f;

    @objid ("b064e3e1-131d-4425-87f0-2812ade3e3ee")
    public static final float XSMALL_SIZE = 0.8f;

    @objid ("bfc629b9-503d-41ac-a792-4330c300a8c1")
    public static final float XXSMALL_SIZE = 0.7f;

}
